package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WindowType;

public class AppTest {

    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();

        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();

        // Open new tab (Selenium 4 feature)
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
