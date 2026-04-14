package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class App {

    public static void main(String[] args) throws InterruptedException {

        // ✅ Setup GeckoDriver automatically for Firefox
        WebDriverManager.firefoxdriver().setup();

        // ✅ Set Firefox options
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless"); // Run in headless mode (no UI)

        // ✅ IMPORTANT: Set correct binary path (fix for Jenkins error)
        // You may need to adjust this path if different from the default
        options.setBinary("/usr/bin/firefox-esr"); // For Linux, ensure this is correct
        // On Windows, it would be something like "C:\\Program Files\\Mozilla Firefox\\firefox.exe"

        // ✅ Create FirefoxDriver instance with specified options
        WebDriver driver = new FirefoxDriver(options);

        // ✅ Set browser window size for consistent behavior in Jenkins
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));

        // ✅ Initialize WebDriverWait for explicit waits
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ Initialize Actions class for handling complex user gestures
        Actions actions = new Actions(driver);

        // ------------------ SauceDemo ------------------
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        System.out.println("SauceDemo login successful");

        // ------------------ Automation Exercise ------------------
        driver.switchTo().newWindow(WindowType.TAB); // Open a new tab
        driver.get("https://automationexercise.com/products");

        driver.findElement(By.id("search_product")).sendKeys("Men Tshirt");
        driver.findElement(By.id("submit_search")).click();

        // Wait for the product to be visible
        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-product-id='2']"))
        );

        // Scroll to the product element
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", product);

        // Click the product using JavaScriptExecutor
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", product);

        // Wait for the "View Cart" button to be clickable
        WebElement viewCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#cartModal a[href='/view_cart']"))
        );

        // Click the "View Cart" button
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", viewCart);

        System.out.println("Automation Exercise product added to cart");

        // ------------------ Practice Test Automation ------------------
        driver.switchTo().newWindow(WindowType.TAB); // Open another new tab
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Wait for username field, then enter credentials
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        System.out.println("Practice Test Automation login successful");

        // ------------------ Cleanup ------------------
        // Wait a bit before quitting
        Thread.sleep(3000);
        
        // Close the browser
        driver.quit();
    }
}
