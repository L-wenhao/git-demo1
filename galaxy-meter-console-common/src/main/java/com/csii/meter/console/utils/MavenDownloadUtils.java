package com.csii.meter.console.utils;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.Authentication;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;
import org.eclipse.aether.util.repository.AuthenticationBuilder;

public class MavenDownloadUtils {

    /**
     * 建立RepositorySystem
     * @return RepositorySystem
     */
    private static RepositorySystem newRepositorySystem() {
        DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
        locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class );
        //locator.addService(TransporterFactory.class, FileTransporterFactory.class );
        locator.addService(TransporterFactory.class, HttpTransporterFactory.class );
        return locator.getService( RepositorySystem.class );
    }

    /**
     * 创建session
     * @param system RepositorySystem
     * @return RepositorySystemSession
     */
    private static RepositorySystemSession newSession(RepositorySystem system, String target ) {
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
        LocalRepository localRepo = new LocalRepository(target);
        session.setLocalRepositoryManager( system.newLocalRepositoryManager( session, localRepo ) );
        return session;
    }

    /**
     *  从指定maven地址下载指定jar包
     * @param groupId
     * @param artifactId
     * @param version
     * @param repositoryUrl maven库的URL地址
     * @param username 若需要权限，则需使用此参数添加用户名，否则设为null
     * @param password 同上
     * @param target 下载后存放的目录
     * @throws ArtifactResolutionException
     */
    public static String download(String groupId, String artifactId, String version,String repositoryUrl,
                                  String username,String password,String target) throws ArtifactResolutionException {
        RepositorySystem repoSystem = newRepositorySystem();
        RepositorySystemSession session = newSession(repoSystem ,target);
        RemoteRepository central = null;
        if(username==null&&password==null) {
            central = new RemoteRepository.Builder( "central", "default", repositoryUrl).build();
        }else{
            Authentication authentication=new AuthenticationBuilder().addUsername(username).addPassword(password).build();
            central = new RemoteRepository.Builder( "central", "default", repositoryUrl).setAuthentication(authentication).build();
        }
        String mavenArtifact = groupId+":"+artifactId+":"+version;
        Artifact artifact=new DefaultArtifact(mavenArtifact);
        ArtifactRequest artifactRequest=new ArtifactRequest();
        artifactRequest.addRepository(central);
        artifactRequest.setArtifact(artifact);
        repoSystem.resolveArtifact(session, artifactRequest);
        //组装文件路径
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(target).append(PathUtils.UNIX_SEPARATOR_S).append(groupId.replace(".", "/"));
        stringBuffer.append(PathUtils.UNIX_SEPARATOR_S).append(artifactId).append(PathUtils.UNIX_SEPARATOR_S).append(version);
        stringBuffer.append(PathUtils.UNIX_SEPARATOR_S).append(artifactId).append("-").append(version).append(".jar");
        return PathUtils.normalize(stringBuffer.toString());

    }
}
