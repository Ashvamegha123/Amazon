package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonHomePage {
    WebDriver driver;

    // Constructor
    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    //locators
    
    By input_box= By.id("twotabsearchtextbox");
    By click_srcbtn=By.id("nav-search-submit-button");

    //Action and Method to search for a product
    public void searchProduct(String productName) {
        WebElement searchBox = driver.findElement(input_box);
        
        searchBox.clear(); // Clear the input field
        searchBox.sendKeys(productName);
        
         driver.findElement(click_srcbtn).click();
        
    }
}
