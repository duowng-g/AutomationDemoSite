package Testcase;
import Base.baseSetUp;
import Common.PropertiesFile;
import Page.switchToPage;
import org.openqa.selenium.WebDriver;
import Page.registerPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;

public class registerTest  extends baseSetUp{
    private WebDriver driver;
    public registerPage registerPage;
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
}
