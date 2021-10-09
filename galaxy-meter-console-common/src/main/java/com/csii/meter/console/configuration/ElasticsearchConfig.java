package com.csii.meter.console.configuration;


import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.host:}")
    private String host;

    @Value("${elasticsearch.port:}")
    private int port;

    @Value("${elasticsearch.schema:http}")
    private String schema;

    @Value("${elasticsearch.username:}")
    private String username;

    @Value("${elasticsearch.password:}")
    private String password;

    /**
     * 连接超时时间
     **/
    @Value("${elasticsearch.client.connectTimeOut:3000}")
    private int connectTimeOut;

    /**
     * 连接超时时间
     **/
    @Value("${elasticsearch.client.socketTimeOut:3000}")
    private int socketTimeOut;

    /**
     * 获取连接的超时时间
     **/
    @Value("${elasticsearch.client.connectionRequestTimeOut:2000}")
    private static int connectionRequestTimeOut;

    /**
     * 最大连接数
     **/
    @Value("${elasticsearch.client.maxConnectNum:1000}")
    private static int maxConnectNum;

    /**
     * 最大路由连接数
     **/
    @Value("${elasticsearch.client.maxConnectPerRoute:1000}")
    private static int maxConnectPerRoute;

//    private List<HttpHost> hostList = new ArrayList<>();
//
//    @PostConstruct
//    private void init() {
//        hostList = new ArrayList<>();
//        String[] hostArray = url.split(",");
//        for (String url : hostArray) {
//            hostList.add(new HttpHost(url));
//        }
//    }

    @Bean
    public RestHighLevelClient getRestHighLevelClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));  //es账号密码
        HttpHost httpHost = new HttpHost(host,port,schema);
        RestClientBuilder builder = RestClient.builder(httpHost);
        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpClientBuilder;
        });
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }


}
