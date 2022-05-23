package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id ="inputUsername")
    private WebElement usernameElement;

    @FindBy (id = "inputPassword")
    private WebElement passwordElement;

    @FindBy (id = "login-button")
    private WebElement submitButton;

    @FindBy (id = "signup-link")
    private WebElement signUp;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login (String username, String password){
        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        submitButton.click();
    }
    public void signUpLink (){
        signUp.click();
    }
}

