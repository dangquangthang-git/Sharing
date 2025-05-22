package Testcases;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reportConfigs.ExtentManager;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Login extends BaseTest {


    @BeforeClass
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-infobars");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        this.driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://id-uat.ipas.com.vn/login?&redirect-app=dsb-web");
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Login(Method method) throws InterruptedException {
        ExtentManager.startTest(method.getName(), "Login DSB");
        ExtentManager.getTest().log(Status.INFO, "STEP01: Clear username textbox");
        driver.findElement(By.xpath("//input[@id='username']")).clear();
        ExtentManager.getTest().log(Status.INFO, "STEP02: Enter to username textbox");
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("ipa\\a.mg");
        ExtentManager.getTest().log(Status.INFO, "STEP03: Clear password textbox");
        driver.findElement(By.xpath("//input[@id='password']")).clear();
        ExtentManager.getTest().log(Status.INFO, "STEP04: Enter to password textbox");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Dsb@2024");
        ExtentManager.getTest().log(Status.INFO, "STEP05: Click to login button");
        driver.findElement(By.xpath("//button[@id='btn-login']")).click();

        Thread.sleep(2000);
        String customerList = driver.findElement(By.xpath("//h1[text()='Danh sách khách hàng']")).getText();
        System.out.println(customerList);
        ExtentManager.getTest().log(Status.INFO, "STEP06: Verify page: " + customerList);
        Assert.assertEquals(customerList.trim(), "Danh sách khách hàng");

    }

}