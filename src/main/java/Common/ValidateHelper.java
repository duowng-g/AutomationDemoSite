package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ValidateHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    public ValidateHelper(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,20);
    }
    public boolean verifyURL(String URL){
        return driver.getCurrentUrl().contains(URL);
    }
    public void sendText(By element, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).sendKeys(text);
    }
    public void clickElement(By element) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }
    public void clickRadioAndCheckbox(By element){
        Boolean isSelected = driver.findElement(element).isSelected();
        if(isSelected == false){
            driver.findElement(element).click();
        }
    }
    public void waitForPageLoad() {
        ExpectedCondition<Boolean> jsload = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("document.readyPage").toString().equals("complete");
            }
        };
        try {
            wait.until(jsload);
        } catch (Throwable e) {
            System.out.println("Khong load duoc trang");
        }
    }
    public void testCloseAdsGoogle(WebElement frame1) {
        try {
            //Switch to frame with element
            driver.switchTo().frame(frame1);
            //Check button X or Close displays
            List<WebElement> checkButtonX = driver.findElements(By.xpath("//div[@id='dismiss-button']"));
            System.out.println("checkButtonX: " + checkButtonX.size());
            if (((List<?>) checkButtonX).size() > 0) {
                driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
            } else {
                WebElement frame2 = driver.findElement(By.id("ad_iframe"));
                driver.switchTo().frame(frame2);
                Thread.sleep(1000);
                List<WebElement> checkButtonClose = driver.findElements(By.xpath("//div[@id='dismiss-button']//span[normalize-space()='Close']"));
                System.out.println("checkButtonClose: " + checkButtonClose.size());
                if (checkButtonClose.size() > 0) {
                    driver.findElement(By.xpath("//div[@id='dismiss-button']//span[normalize-space()='Close']")).click();
                } else {
                    driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
                }
            }

            driver.switchTo().defaultContent();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeAds(){
        WebElement frame1 = driver.findElement(By.id("aswift_2"));
        driver.switchTo().frame(frame1);
        List<WebElement> checkButtonX = driver.findElements(By.xpath("//div[@id='dismiss-button']"));
        System.out.println("Check button X : " + checkButtonX.size());
        if(checkButtonX.size() > 0) {
            driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
        }else{
            WebElement frame2 = driver.findElement(By.id("ad_iframe"));
            driver.switchTo().frame(frame2);
            List<WebElement> checkButtonClose = driver.findElements(By.xpath("//div[@id='dismiss-button']//span[normalize-space()='Close']"));
            System.out.println("Check button close : " + checkButtonClose.size());
            if(checkButtonClose.size() > 0){
                driver.findElement(By.xpath("//div[@id='dismiss-button']//span[normalize-space()='Close']")).click();
            }else{
                driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
            }
        }
        driver.switchTo().defaultContent();
    }
}
