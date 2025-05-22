package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BasePage {
    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String castParameter(String locator, String... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentpage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresent(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
    }

    public void dismissToAlert(WebDriver driver) {
        waitAlertPresent(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresent(driver).getText();
    }

    public void senkeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresent(driver).sendKeys(keyToSend);
    }

    public void switchToWindowbyID(String windowId, WebDriver driver) {
        Set<String> allId = driver.getWindowHandles();
        for (String id : allId) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowsbyTitle(String expectedTitle, WebDriver driver) {
        Set<String> allId = driver.getWindowHandles();
        for (String id : allId) {
            driver.switchTo().window(id);
            String getTitle = driver.getTitle();
            if (getTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeWindowsButParent(String parentId, WebDriver driver) {
        Set<String> allId = driver.getWindowHandles();
        for (String id : allId) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(id);
        }
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... resParameter) {
        getElement(driver, castParameter(locator, resParameter)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver, locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        getElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSendkey, String... restParameter) {
        getElement(driver, castParameter(locator, restParameter)).clear();
        getElement(driver, castParameter(locator, restParameter)).sendKeys(valueToSendkey);
    }

    public void selectItemInDropdownList(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemInDropdownList(WebDriver driver, String locator, String textItem, String... restParameter) {
        new Select(getElement(driver, castParameter(locator, restParameter))).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(2);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
        sleepInSecond(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... resParameter) {
        return getElement(driver, castParameter(locator, resParameter)).getAttribute(attributeName);
    }

    public static String getPhoneGenerate() {
        Random random = new Random();
        return "09694" + String.format("%05d", random.nextInt(100000));
    }

    public static String getHouseNumberGenerate() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(999));
    }

    public static String generateTaskName() {

        List<String> words = Arrays.asList("Kiểm tra", "Phân tích", "Lập trình", "Hỗ trợ", "Tối ưu", "Cải tiến", "Xử lý", "Đánh giá", "Phát triển", "Tạo mới");
        Random rand = new Random();
        String word1 = words.get(rand.nextInt(words.size()));
        String word2 = words.get(rand.nextInt(words.size()));
        return word1 + " " + word2;
    }

    protected static String getEmailGenerate(String prefix) {
        Random random = new Random();
        return prefix + random.nextInt(9999) + "@gmail.com";
    }

    protected static String getCompanyNameGenerate() {
        Random random = new Random();
        return "Công ty " + random.nextInt(9999);
    }

    public static String getTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return tomorrow.format(formatter);
    }

    public static String getRandomName() {
        String[] firstNames = {"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Phan", "Đặng", "Trương"};
        String[] middleNames = {"Văn", "Thị", "Hữu", "Minh", "Đức", "Ngọc", "Đức", "Hồng"};
        String[] lastNames = {"An", "Bình", "Hạnh", "Khánh", "Tú", "Trang", "Quân", "Ánh", "Nguyệt"};

        Random random = new Random();
        String first = firstNames[random.nextInt(firstNames.length)];
        String middle = middleNames[random.nextInt(middleNames.length)];
        String last = lastNames[random.nextInt(lastNames.length)];

        return first + " " + middle + " " + last + " " + random.nextInt(9999);
    }

    public void sleepInSecond(long sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTextbox(WebDriver driver, String locator) {
        getElement(driver, locator).clear();
    }

    public void clearTextbox(WebDriver driver, String locator, String... resParameter) {
        getElement(driver, locator, resParameter).clear();
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected WebElement getElement(WebDriver driver, String locator, String... resParameter) {
        return driver.findElement(getByLocator(castParameter(locator, resParameter)));
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public void getHexaColorFromRGBA(WebDriver driver, String locator, String rgbaValue) {
        Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator, String... resParameter) {
        return driver.findElements(getByLocator(castParameter(locator, resParameter)));
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator, String... resParameter) {
        if (!getElement(driver, castParameter(locator, resParameter)).isSelected()) {
            getElement(driver, castParameter(locator, resParameter)).click();
        }
    }

    public void unCheckToCheckboxRadio(WebDriver driver, String locator) {
        if (getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(2);
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... resParameter) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, castParameter(locator, resParameter)));
        sleepInSecond(2);
    }

    public void unCheckToCheckboxRadio(WebDriver driver, String locator, String... resParameter) {
        if (getElement(driver, castParameter(locator, resParameter)).isSelected()) {
            getElement(driver, castParameter(locator, resParameter)).click();
        }
    }

    public void overideGlobalTimeout(WebDriver driver, long sleep) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sleep));
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, locator);
        overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        if (elements.size() == 0) {
//            not in DOM & not display
            return false;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
//            In DOM & not display
            return false;
        } else {
//            In DOM & display
            System.out.println("element in DOM");
            return true;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... resParameter) {
        return getElement(driver, castParameter(locator, resParameter)).isDisplayed();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementPresent(WebDriver driver, String locator) {
        try {
            return getListElementNumber(driver,locator) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... resParameter) {
        return getElement(driver, castParameter(locator, resParameter)).isSelected();
    }


    public void switchToIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultPage(WebDriver driver, String locator) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void doubleClick(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... resParameter) {
        new Actions(driver).sendKeys(getElement(driver, castParameter(locator, resParameter)), key).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).build().perform();
    }

    public void scrollToElement(WebDriver driver, String locator, String... resParameter) {
        new Actions(driver).scrollToElement(getElement(driver, castParameter(locator, resParameter))).build().perform();
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... resParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, resParameter))));
    }

    public void clickToMyaccountByAction(WebDriver driver, String locator) {
        new Actions(driver).click(getElement(driver, locator)).perform();
    }

    public void releaseMouse(WebDriver driver) {
        new Actions(driver).release().perform();
    }

    public void waitForElementPresent(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));

    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... resParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, resParameter))));
    }

    public boolean waitForElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitForElementSelected(WebDriver driver, String locator, String... resParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, resParameter))));
    }

    public boolean waitForElementAttribute(WebDriver driver, String locator, String attributeName, String attributeValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(locator), attributeName, attributeValue));
    }

    public boolean waitForElementAttribute(WebDriver driver, String locator, String attributeName, String attributeValue, String... resParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeToBe(getByLocator(castParameter(locator, resParameter)), attributeName, attributeValue));
    }

    private By getByLocator(String prefixLocator) {
        By by;
        if (prefixLocator.toLowerCase().startsWith("css")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toLowerCase().startsWith("id")) {
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toLowerCase().startsWith("xpath")) {
            by = By.xpath(prefixLocator.substring(6));
        } else if (prefixLocator.toLowerCase().startsWith("class")) {
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toLowerCase().startsWith("name")) {
            by = By.name(prefixLocator.substring(5));
        } else {
            throw new RuntimeException("Invalid locator");
        }
        return by;
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

}
