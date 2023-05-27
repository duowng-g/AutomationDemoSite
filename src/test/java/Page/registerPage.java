package Page;

import Common.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class registerPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public ValidateHelper validateHelper;
    public registerPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
        wait = new WebDriverWait(driver, 20);
    }
    //khai bao Locators
    By firstName = By.xpath("//input[@placeholder='First Name']");
    By lastName = By.xpath("//input[@placeholder='Last Name']");
    By address = By.xpath("//textarea[@class='form-control ng-pristine ng-untouched ng-valid']");
    By emailAddress = By.xpath("//input[@type='email']");
    By phone = By.xpath("//input[@type='tel']");
    By gender = By.xpath("//input[@value='FeMale']");
    By Cricket = By.xpath("//input[@id='checkbox1']");
    By Movies = By.xpath("//input[@id='checkbox2']");
    By Hockey = By.xpath("//input[@id='checkbox3']");
    By languages_btn = By.xpath("//div[@id='msdd']");
    By VietNamese = By.xpath("(//li[@class='ng-scope'])[41]");
    By Skills = By.xpath("//select[@id='Skills']");
    By selectCountry = By.xpath("//span[@role='combobox']");
    By textBoxSelectCountry = By.xpath("//input[@role='textbox']");
    By resultCountry = By.xpath("//li[@role='treeitem']");
    By yearBox = By.xpath("//select[@id='yearbox']");
    By monthBox = By.xpath("//select[@placeholder='Month']");
    By dayBox = By.xpath("//select[@id='daybox']");
    By password = By.xpath("//input[@id='firstpassword']");
    By confirmPassWord = By.xpath("//input[@id='secondpassword']");
    By submit = By.xpath("//button[@id='submitbtn']");
    By switchTo_btn = By.xpath("//a[normalize-space()='SwitchTo']");
    By parentIFrameAds = By.id("aswift_2");
    By iFrameAds = By.id("ad_iframe");
    By close_btn = By.id("dismiss-button");
    By chooseFile =By.xpath("//div[@class='col-sm-4 col-md-4 col-xs-4']//div[2]");

    public switchToPage Register(String firstname, String lastname, String Address, String emailaddress, String Phone, String skill, String Country, String year, String month, String day, String Password) throws InterruptedException, AWTException {
        validateHelper.waitForPageLoad();
        validateHelper.clickElement(chooseFile);
        Robot robot = new Robot();
        StringSelection str1 = new StringSelection("\"C:\\Users\\MSI PC\\OneDrive - actvn.edu.vn\\Pictures\\Screenshots\\LU.jpg\"");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str1, null);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        validateHelper.sendText(firstName,firstname);
        validateHelper.sendText(lastName,lastname);
        validateHelper.sendText(address,Address);
        validateHelper.sendText(emailAddress, emailaddress);
        validateHelper.sendText(phone, Phone);
        validateHelper.clickRadioAndCheckbox(gender);
        validateHelper.clickRadioAndCheckbox(Cricket);
        validateHelper.clickRadioAndCheckbox(Movies);
        validateHelper.clickRadioAndCheckbox(Hockey);
        Thread.sleep(1000);
        validateHelper.clickElement(languages_btn);
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(VietNamese);
        js.executeScript("arguments[0].scrollIntoView(true);",element);
        js.executeScript("arguments[0].click();",element);
        Select select = new Select(driver.findElement(Skills));
        select.selectByVisibleText(skill);
        validateHelper.clickElement(selectCountry);
        validateHelper.sendText(textBoxSelectCountry,Country);
        validateHelper.clickElement(resultCountry);
        Select select1 = new Select(driver.findElement(yearBox));
        select1.selectByVisibleText(year);
        Select select2 = new Select(driver.findElement(monthBox));
        select2.selectByVisibleText(month);
        Select select3 = new Select(driver.findElement(dayBox));
        select3.selectByVisibleText(day);
        validateHelper.sendText(password,Password);
        validateHelper.sendText(confirmPassWord,Password);
        validateHelper.clickElement(submit);
        validateHelper.clickElement(switchTo_btn);
        return new switchToPage(driver);
    }
}
