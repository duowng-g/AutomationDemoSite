package Page;

import Common.ValidateHelper;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class interactionsPage {
    private WebDriver driver;
    public WebDriverWait wait;
    public ValidateHelper validateHelper;
    public interactionsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        validateHelper = new ValidateHelper(driver);
    }
    //Locators
    By Interactions = By.xpath("//a[normalize-space()='Interactions']");
    //Drag and Drop
    By dragAndDrop = By.xpath("//a[normalize-space()='Drag and Drop']");
    By dragAndDrop_static = By.xpath("//a[normalize-space()='Static']");
    By dragAndDrop_dynamic = By.xpath("//a[normalize-space()='Dynamic']");
    By Angular_img = By.xpath("//img[@id='angular']");
    By Mongo_img = By.xpath("//img[@id='mongo']");
    By Node_img = By.xpath("//img[@id='node']");
    By dropArea = By.xpath("//div[@id='droparea']");
    //Selectable
    By Selectable = By.xpath("//a[normalize-space()='Selectable']");
    By default1 = By.xpath("//div[@id='Default']//li[1]");
    By default2 = By.xpath("//div[@id='Default']//li[4]");
    By Serialize = By.xpath("//a[normalize-space()='Serialize']");
    //Resizable
    By Resizable = By.xpath("//a[normalize-space()='Resizable']");
    By resizable_btn = By.xpath("//div[@id='resizable']//div[3]");

    public void testDragAndDrop() throws InterruptedException {
        validateHelper.clickElement(dragAndDrop);
        validateHelper.clickElement(dragAndDrop_static);
        Thread.sleep(2000);
        WebElement srcAngular  = driver.findElement(Angular_img);
        WebElement dest = driver.findElement(dropArea);
        Actions actions1 = new Actions(driver);
        actions1.dragAndDrop(driver.findElement(Angular_img),dest).perform();
        Actions actions2 = new Actions(driver);
        actions2.dragAndDrop(driver.findElement(Mongo_img),dest).perform();
        Actions actions3 = new Actions(driver);
        actions3.dragAndDrop(driver.findElement(Node_img),dest).perform();
    }
    public void testSelectable() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(Interactions)).build().perform();
        validateHelper.clickElement(Selectable);
        //Default Functionality
        validateHelper.clickElement(default1);
        validateHelper.clickElement(default2);
        //Serialize
        validateHelper.clickElement(Serialize);
        List<WebElement> listSelectable = driver.findElements(By.xpath("//ul[@class='SerializeFunc']//li[@class='ui-widget-content']"));
        for(WebElement element : listSelectable){
            element.click();
            Thread.sleep(1000);
        }
    }
    public void testResizable() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(Interactions)).build().perform();
        validateHelper.clickElement(Resizable);
        actions.clickAndHold(driver.findElement(resizable_btn)).moveByOffset(50,10).release().build().perform();
    }
}
