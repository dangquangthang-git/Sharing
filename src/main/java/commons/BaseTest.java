package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite
    public void setupLogging() {
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "WARN");
        System.setProperty("log4j2.loggerContextFactory", "org.apache.logging.log4j.core.impl.Log4jContextFactory");
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        String downloadFiles = GlobalConstants.PROJECT_PATH + File.separator + "src"
                + File.separator + "main"
                + File.separator + "resources"
                + File.separator + "downloadFiles";
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case HCHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless"); // Hoặc "--headless" nếu chưa dùng Chrome 109+
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
                options.addArguments("--remote-allow-origins=*");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);  // Tắt trình quản lý mật khẩu
                prefs.put("profile.password_manager_enabled", false);  // Tắt popup lưu mật khẩu
                prefs.put("download.default_directory", downloadFiles);
                prefs.put("download.prompt_for_download", false);
                prefs.put("safebrowsing.enabled", true);
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("browser is invalid");
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        return driver;
    }
}
