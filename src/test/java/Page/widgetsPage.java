package Page;

import Common.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class widgetsPage {
    private WebDriver driver;
    public ValidateHelper validateHelper;
    public WebDriverWait wait;
    public widgetsPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
        wait = new WebDriverWait(driver, 20);
    }
    //Locators
    By Widgets = By.xpath("//a[normalize-space()='Widgets']");
    //Accordion
    By Accordion = By.xpath("//a[normalize-space()='Accordion']");
    //AutoComplete
    By autoComplete = By.xpath("//a[normalize-space()='AutoComplete']");
    By searchBox_autoComplete = By.xpath("//input[@id='searchbox']");
    //DatePicker
    By datePicker = By.xpath("//a[normalize-space()='Datepicker']");
    By datePicker_Disable = By.xpath("//input[@id='datepicker1']");
    By datePicker_Enable = By.xpath("//input[@id='datepicker2']");
    //Sliders
    By Sliders = By.xpath("//a[normalize-space()='Slider']");
    By slider_handle = By.xpath("//div[@id='slider']//a");
    By Interactions = By.xpath("//a[normalize-space()='Interactions']");

    public void testAccordion() throws InterruptedException {
        validateHelper.clickElement(Accordion);
        for(int i = 2; i <= 4; i++){
            driver.findElement(By.xpath("(//div[@class='panel panel-default'])["+i+"]//b")).click();
            Thread.sleep(2000);
            WebElement Panel = driver.findElement(By.xpath("(//div[@class='panel panel-default'])["+i+"]//div[2]"));
            String className = Panel.getAttribute("class");
            System.out.println(className);
            if(className.contains("panel-collapse collapse in")){
                Assert.assertTrue(true,"Test accordion is PASS");
            }else{
                Assert.assertTrue(false,"Test accordion is PASS");
            }
        }
    }
    public void testAutoComplete(String search,String resultExpectSearch) throws InterruptedException {
        validateHelper.clickElement(Widgets);
        validateHelper.clickElement(autoComplete);
        validateHelper.sendText(searchBox_autoComplete,search);
        List<WebElement> suggestions = driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
        for(WebElement suggestion : suggestions){
            String text = suggestion.getText();
            if(text.equals(resultExpectSearch)){
                suggestion.click();
            }
        }
    }
    public void testDatePicker() throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(Widgets)).build().perform();
        validateHelper.clickElement(datePicker);
        validateHelper.clickElement(datePicker_Disable);
        List<WebElement> days = driver.findElements(By.xpath("//a[@class='ui-state-default']"));
        for(WebElement day : days){
            String text = day.getText();
            if(text.equals("17")){
                day.click();
                break;
            }
        }
        validateHelper.sendText(datePicker_Enable,"05/17/2023");
    }
    public interactionsPage testSliders() throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(Widgets)).build().perform();
        validateHelper.clickElement(Sliders);
        Actions action1 = new Actions(driver);
        action1.dragAndDropBy(driver.findElement(slider_handle),30,0).perform();
        String sliderValue = driver.findElement(slider_handle).getAttribute("style");
        Assert.assertEquals("left: 4%;",sliderValue,"Sliders is PASS");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(Interactions)).build().perform();
        return new interactionsPage(driver);
    }
}
