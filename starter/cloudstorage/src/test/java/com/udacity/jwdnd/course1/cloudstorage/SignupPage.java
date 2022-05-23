package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id ="inputFirstName")
    private WebElement firstNameElement;

    @FindBy(id ="inputLastName")
    private WebElement lastNameElement;

    @FindBy (id ="inputUsername")
    private WebElement usernameElement;

    @FindBy (id = "inputPassword")
    private WebElement passwordElement;

    @FindBy (id = "buttonSignUp")
    private WebElement submitButton;

    @FindBy (id = "login-link")
    private WebElement loginButton;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void registerAndLoginPage (String firstname, String lastname, String username, String password){
        this.firstNameElement.sendKeys(firstname);
        this.lastNameElement.sendKeys(lastname);
        this.usernameElement.sendKeys(username);
        this.passwordElement.sendKeys(password);
        submitButton.click();
    }

}
