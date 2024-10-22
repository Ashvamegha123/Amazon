package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCart {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public AddToCart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Set an explicit wait
    }
    
    //locator
    
    By addToCartButton=By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']");
    By cartButtonPopup=By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']");
    By cartButtonIcon= By.xpath("//span[normalize-space()='Cart']");
    By dropdown=       By.xpath("//span[@class='a-dropdown-prompt']");
    By quantityOption= By.xpath("//a[@id='quantity_2']");
    By deleteButton=   By.xpath("//input[@value='Delete']");
    
    
    
    
    // Method to add a product to the cart
    public void addToCart() {
        // Wait for the Add to Cart button to be clickable
         wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        
    }
    
    //method to click on cart button which display on popup
    
    public void cartButtonPopup() {
    	 wait.until(ExpectedConditions.elementToBeClickable(cartButtonPopup)).click();
    	
    }
    
    //method to click on cart button icon
    
    public void cartButtonIcon() {
    	 wait.until(ExpectedConditions.elementToBeClickable(cartButtonIcon)).click();
    	
    }
    
    

    // Method to update the cart quantity
    
    public void updateQuantity(String quantity) throws InterruptedException {
        // Step 1: Click to open the dropdown
        driver.findElement(dropdown).click();
        Thread.sleep(5000);

         driver.findElement(quantityOption).click();
    }
    
   

    // Method to remove a product from the cart
    public void removeFromCart() {
        // Wait for the delete button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    // Method to check if the cart is empty
    public boolean isCartEmpty() {
        // Wait for the page to load completely
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Your Amazon Cart is empty.']")));
        return driver.getPageSource().contains("Your Amazon Cart is empty.");
    }
}
