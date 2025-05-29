package commons;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String EXTENT_PATH = PROJECT_PATH + SEPARATOR + "htmlExtent" + SEPARATOR;
    public static final String JAVA_VERSION = System.getProperty("java.version");

    public static final long SHORT_TIMEOUT = 10;
    public static final long LONG_TIMEOUT = 30;

}
