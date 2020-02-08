package com.IsaPsw.e2e;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstPage {


    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement email;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"loginButton\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"errorMessage\"]")
    private WebElement errorMessage;

    public FirstPage() {
    }

    public FirstPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail(){
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.elementToBeClickable(email));
    }
    public void ensureIsDisplayedLoginButton(){
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmail() {
        return email;
    }

    public void setEmail(WebElement email) {
        this.email = email;
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(WebElement loginButton) {
        this.loginButton = loginButton;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(WebElement errorMessage) {
        this.errorMessage = errorMessage;
    }
}
