package Testcase;

import Base.baseSetUp;
import Common.PropertiesFile;
import Common.ValidateHelper;
import Page.registerPage;
import Page.switchToPage;
import Page.widgetsPage;
import Page.interactionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;

public class widgetsTest extends baseSetUp {
    private WebDriver driver;
    public ValidateHelper validateHelper;
    public Page.registerPage registerPage;
    public Page.switchToPage switchToPage;
    public widgetsPage widgetsPage;
    @BeforeClass
    public void setUp(){
        driver = new baseSetUp().setDriver("chrome");
    }
    @Test(priority = 1)
    public void testRegister() throws InterruptedException, AWTException {
        PropertiesFile.setPropertiesFile();
        registerPage = new registerPage(driver);
        driver.navigate().to("https://demo.automationtesting.in/Register.html");
        Thread.sleep(2000);
        switchToPage switchToPage = registerPage.Register(PropertiesFile.getPropValue("firstname"),PropertiesFile.getPropValue("lastname"), PropertiesFile.getPropValue("Address"),PropertiesFile.getPropValue("emailaddress"),PropertiesFile.getPropValue("Phone"),PropertiesFile.getPropValue("skill"),PropertiesFile.getPropValue("Country"),PropertiesFile.getPropValue("year"),PropertiesFile.getPropValue("month"),PropertiesFile.getPropValue("day"),PropertiesFile.getPropValue("Password"));
    }
    @Test(priority = 2)
    public void testAlerts() throws InterruptedException {
        switchToPage = new switchToPage(driver);
        switchToPage.handleAlerts();
    }
    @Test(priority = 3)
    public void testWindows() throws InterruptedException {
        switchToPage = new switchToPage(driver);
        switchToPage.handleWindows();
    }
    @Test(priority = 4)
    public void testFrames() throws InterruptedException {
        switchToPage = new switchToPage(driver);
        widgetsPage widgetsPage= switchToPage.handleFrames();
    }
    @Test(priority = 5)
    public void testAccordions() throws InterruptedException {
        widgetsPage = new widgetsPage(driver);
        widgetsPage.testAccordion();
    }
    @Test(priority = 6)
    public void testAutoComplete() throws InterruptedException {
        PropertiesFile.setPropertiesFile();
        widgetsPage = new widgetsPage(driver);
        widgetsPage.testAutoComplete(PropertiesFile.getPropValue("search_autoComplete"),PropertiesFile.getPropValue("researchExpectSearch"));
    }
    @Test(priority = 7)
    public void testDatePicker() throws InterruptedException {
        widgetsPage = new widgetsPage(driver);
        widgetsPage.testDatePicker();
    }
    @Test(priority = 8)
    public void testSliders() throws InterruptedException {
        widgetsPage = new widgetsPage(driver);
        interactionsPage interactionsPage = widgetsPage.testSliders();
    }
}
