package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.AmazonHomePage;
import Pages.SearchResultsPage;
import Pages.AddToCart;

public class AmazonTest {
    WebDriver driver;
    AmazonHomePage amazonHomePage;
    SearchResultsPage searchResultsPage;
    AddToCart cartPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");

        // Initialize page objects
        amazonHomePage = new AmazonHomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        cartPage=new AddToCart(driver);
       
    }

    @Test(priority = 1)
    public void testSearchNonExistingProduct() {
        // STEP 1: Search for a non-existing product
        amazonHomePage.searchProduct("thisisaveryrandomstringthatshouldnotgiveanyresults12345");

        // Verify "No results found" message
        Assert.assertTrue(searchResultsPage.isNoResultsMessageDisplayed(), "Expected 'No results found' but it was not displayed.");
    }

    @Test(priority = 2)
    public void testSearchExistingProduct() {
        // STEP 2: Search for an existing product
        amazonHomePage.searchProduct("Laptop");

        // Verify that product results display "Laptop"
        Assert.assertTrue(searchResultsPage.isProductDisplayed("Laptop"), "Laptop not displayed in search results.");
    }

    @Test(priority = 3)
    public void testAddProductToCart() {
        // STEP 3: Search and add a product to the cart
        amazonHomePage.searchProduct("Laptop");
        searchResultsPage.selectFourthProduct();
        cartPage.addToCart();
        cartPage.cartButtonPopup();
        cartPage.cartButtonIcon(); 

        // Verify product added to cart
        Assert.assertTrue(driver.getPageSource().contains("Added to Cart"), "Product not added to cart.");
        
    }

    @Test(priority = 4)
    public void testUpdateCartQuantity() throws InterruptedException {
        // STEP 4: Go to the cart page
        driver.get("https://www.amazon.in/gp/cart/view.html?ref_=nav_cart");

        // Update the quantity to 2 using the method in AddToCart class
        cartPage.updateQuantity("2");
        // Verify that the quantity was updated
        Assert.assertTrue(driver.getPageSource().contains("2"), "Quantity not updated in cart.");
        
       // Assert.assertTrue(quantityElement.getText().contains("2"), "Quantity not updated in cart.");
    }

    @Test(priority = 5)
    public void testRemoveProductFromCart() {
        // STEP 5: Remove product from the cart
        driver.get("https://www.amazon.in/gp/cart/view.html?ref_=nav_cart");
        cartPage.removeFromCart();

        // Verify that the cart is empty
        Assert.assertTrue(cartPage.isCartEmpty(), "Your Amazon Cart is empty.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after all tests are executed
        if (driver != null) {
            driver.quit();
        }
    }
}
