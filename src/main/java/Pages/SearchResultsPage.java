package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

public class SearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebDriverWait with a timeout of 10 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    //locators for SearchResultPage 
    
    By ResultsMessage= By.xpath("//h2[normalize-space()='Results']");
    By fourthProductt=  By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[4]");
    
    

    // Method to check for "No results found" message
    public boolean isNoResultsMessageDisplayed() {
        try {
            WebElement noResultsMessage = wait.until(ExpectedConditions
                .visibilityOfElementLocated(ResultsMessage));
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method to select the fourth product with explicit wait
    
    public void selectFourthProduct() {
    	
        // Wait until the fourth product is clickable
         wait.until(ExpectedConditions.elementToBeClickable(fourthProductt)).click();
        
        
        // Wait for the new tab and switch to it
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    // Method to check if product results include a specific product
    public boolean isProductDisplayed(String productName) {
        return driver.getPageSource().contains(productName);
    }
}
