package com.csii.meter.console.utils;

/**
 * The type O sinfo utils.
 */
public final class OSinfoUtils {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    private static OSinfoUtils instance = new OSinfoUtils();

    private OSinfoUtils() {
    }

    /**
     * Is linux boolean.
     *
     * @return the boolean
     */
    public static boolean isLinux() {
        return OS.contains("linux");
    }

    /**
     * Is mac os boolean.
     *
     * @return the boolean
     */
    public static boolean isMacOS() {
        return OS.contains("mac") && OS.indexOf("os") > 0 && !OS.contains("x");
    }

    /**
     * Is mac osx boolean.
     *
     * @return the boolean
     */
    public static boolean isMacOSX() {
        return OS.contains("mac") && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
    }

    /**
     * Is windows boolean.
     *
     * @return the boolean
     */
    public static boolean isWindows() {
        return OS.contains("windows");
    }

    /**
     * Is os 2 boolean.
     *
     * @return the boolean
     */
    public static boolean isOS2() {
        return OS.contains("os/2");
    }

    /**
     * Is solaris boolean.
     *
     * @return the boolean
     */
    public static boolean isSolaris() {
        return OS.contains("solaris");
    }

    /**
     * Is sun os boolean.
     *
     * @return the boolean
     */
    public static boolean isSunOS() {
        return OS.contains("sunos");
    }

    /**
     * Is mp ei x boolean.
     *
     * @return the boolean
     */
    public static boolean isMPEiX() {
        return OS.contains("mpe/ix");
    }

    /**
     * Is hpux boolean.
     *
     * @return the boolean
     */
    public static boolean isHPUX() {
        return OS.contains("hp-ux");
    }

    /**
     * Is aix boolean.
     *
     * @return the boolean
     */
    public static boolean isAix() {
        return OS.contains("aix");
    }

    /**
     * Is os 390 boolean.
     *
     * @return the boolean
     */
    public static boolean isOS390() {
        return OS.contains("os/390");
    }

    /**
     * Is free bsd boolean.
     *
     * @return the boolean
     */
    public static boolean isFreeBSD() {
        return OS.contains("freebsd");
    }

    /**
     * Is irix boolean.
     *
     * @return the boolean
     */
    public static boolean isIrix() {
        return OS.contains("irix");
    }

    /**
     * Is digital unix boolean.
     *
     * @return the boolean
     */
    public static boolean isDigitalUnix() {
        return OS.contains("digital") && OS.indexOf("unix") > 0;
    }

    /**
     * Is net ware boolean.
     *
     * @return the boolean
     */
    public static boolean isNetWare() {
        return OS.contains("netware");
    }

    /**
     * Is osf 1 boolean.
     *
     * @return the boolean
     */
    public static boolean isOSF1() {
        return OS.contains("osf1");
    }

    /**
     * Is open vms boolean.
     *
     * @return the boolean
     */
    public static boolean isOpenVMS() {
        return OS.contains("openvms");
    }
}

