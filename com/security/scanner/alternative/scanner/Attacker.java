package com.security.scanner.alternative.scanner;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.Open;

import java.util.List;

public class Attacker implements Runnable {

    String vector;
    String url;
    WebDriver driver;

    public Attacker(WebDriver driver, String urlToAttack) {
        this.driver = driver;
        this.url = urlToAttack;
    }

    public Attacker using(String attackVector) {
        this.vector = attackVector;
        return this;
    }

    public void run() {

        List<WebElement> allInputs;
        List<WebElement> allForms;

        allForms = driver.findElements(By.xpath("//form"));
        for (WebElement form : allForms) {
            allInputs = form.findElements(By.xpath(".//input"));
            allInputs.addAll(form.findElements(By.xpath(".//textarea")));
            for (WebElement input : allInputs) {

                if (input.isDisplayed() && input.isEnabled() &&
                        (input.getAttribute("type").equals("text") || input.getTagName().equals("textarea"))) {
                    input.sendKeys(vector);
                }
            }

            form.findElement(By.xpath(".//input[@type='submit']")).click();
            break;
        }
    }
}