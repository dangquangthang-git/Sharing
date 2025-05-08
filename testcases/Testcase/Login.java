package Testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {
    WebDriver driver;

    @BeforeClass
    public void before() {
        driver = new ChromeDriver();
        driver.navigate().to("https://id-uat.ipas.com.vn/login?&redirect-app=dsb-web");
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Login() {
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("ipa\\a.mg");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Dsb@2024");
        driver.findElement(By.xpath("//button[@id='btn-login']")).click();
        String customerList = driver.findElement(By.xpath("//h1[text()='Danh sách khách hàng']")).getText();
        Assert.assertEquals(customerList, "Danh sách khách hàng");
    }


}
