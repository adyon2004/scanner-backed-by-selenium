package com.security.scanner.alternative.scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginBuilder implements Runnable{

    WebDriver driver;
    String username;
    String password;

    public LoginBuilder (WebDriver driver, String username, String password)
    {
        this.driver = driver;
        this.username = username;
        this.password = password;
    }

    public void run() {
        WebElement userInput = driver.findElement(By.xpath("//input[@name='username']"));
        userInput.sendKeys("admin");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("password");
        WebElement loginButton = driver.findElement(By.name("Login"));
        loginButton.click();
    }
}
