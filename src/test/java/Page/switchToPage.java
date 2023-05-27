package Page;

import Common.ValidateHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class switchToPage {
    private WebDriver driver;
    public WebDriverWait wait;
    public ValidateHelper validateHelper;

    public switchToPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        validateHelper = new ValidateHelper(driver);
    }

    //Locators
    By switchTo_btn = By.xpath("//a[normalize-space()='SwitchTo']");
    By Alerts = By.xpath("(//a[normalize-space()='Alerts'])");
    By Windows = By.xpath("//a[normalize-space()='Windows']");
    By Frames = By.xpath("//a[normalize-space()='Frames']");
    //Alerts
    By AlertwithOK = By.xpath("//button[@class='btn btn-danger']");
    By AlertwithOKandCancle = By.xpath("//a[normalize-space()='Alert with OK & Cancel']");
    By AlertwithOKandCancle_click = By.xpath("//button[@class='btn btn-primary']");
    By AlertwithTextbox = By.xpath("//a[normalize-space()='Alert with Textbox']");
    By AlertwithTextbox_click = By.xpath("//button[@class='btn btn-info']");
    //Windows
    By TabbedWindow_click = By.xpath("(//button[@class='btn btn-info'][normalize-space()='click'])[1]");
    By seperateWindow = By.xpath("//a[normalize-space()='Open New Seperate Windows']");
    By seperateWindow_click = By.xpath("//button[@class='btn btn-primary']");
    //Frames
   By singleIFrame = By.xpath("//iframe[@id='singleframe']");
   By singleIFrame_txt = By.xpath("//input[@type='text']");
   By multipleIFrames = By.xpath("//a[normalize-space()='Iframe with in an Iframe']");
   By parentFrame = By.xpath("//iframe[@src='MultipleFrames.html']");
   By childrenFrame = By.xpath("//iframe[@src='SingleFrame.html']");
   By childrenFrame_txt = By.xpath("//input[@type='text']");
   By Widgets = By.xpath("//a[normalize-space()='Widgets']");

    public void handleAlerts() throws InterruptedException {
        validateHelper.clickElement(Alerts);
        //validateHelper.closeAds();
        validateHelper.clickElement(AlertwithOK);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        validateHelper.clickElement(AlertwithOKandCancle);
        validateHelper.clickElement(AlertwithOKandCancle_click);
        Alert alert1 = driver.switchTo().alert();
        alert1.dismiss();
        validateHelper.clickElement(AlertwithTextbox);
        validateHelper.clickElement(AlertwithTextbox_click);
        Alert alert2 = driver.switchTo().alert();
        alert2.sendKeys("{BS}");
        alert2.sendKeys("Duong");
        alert2.accept();
    }

    public void handleWindows() throws InterruptedException {
        validateHelper.clickElement(switchTo_btn);
        //Open new Tabbed Window
        validateHelper.clickElement(Windows);
        validateHelper.clickElement(TabbedWindow_click);
        String MainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!MainWindow.equals(window)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(MainWindow);
        Thread.sleep(2000);
        //Open new seperate Window
        validateHelper.clickElement(seperateWindow);
        validateHelper.clickElement(seperateWindow_click);
        String MainWindow1 = driver.getWindowHandle();
        Set<String> windows1 = driver.getWindowHandles();
        for (String window : windows1) {
            if (!MainWindow.equals(window)) {
                driver.switchTo().window(window).close();
            }
            driver.switchTo().window(MainWindow1);
            Thread.sleep(2000);
        }
    }
    public widgetsPage handleFrames() throws InterruptedException {
        //handle single IFrame
        validateHelper.clickElement(switchTo_btn);
        validateHelper.clickElement(Frames);
        driver.switchTo().frame(driver.findElement(singleIFrame));
        driver.findElement(singleIFrame_txt).sendKeys("Duong");
        driver.switchTo().defaultContent();
        //Handle multiple IFrames
        validateHelper.clickElement(multipleIFrames);
        driver.switchTo().frame(driver.findElement(parentFrame));
        driver.switchTo().frame(driver.findElement(childrenFrame));
        driver.findElement(childrenFrame_txt).sendKeys("Duong");
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
        validateHelper.clickElement(Widgets);
        return new widgetsPage(driver);
    }
}
