package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    //Note Elements
    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id="note-title")
    private WebElement noteTitleInputField;
    @FindBy(id="note-description")
    private WebElement noteDescriptionInputField;

    @FindBy(id="buttonSubmitNoteForm")
    private WebElement noteFormSubmitButton;

    @FindBy(id="noteTitleTable")
    private WebElement noteTitleTable;

    @FindBy(id="editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id="buttonDeleteNote")
    private WebElement deleteNoteButton;


    //Credential Elements
    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id="addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id="credential-url")
    private WebElement credentialUrlInputField;

    @FindBy(id="credential-username")
    private WebElement credentialUsernameInputField;

    @FindBy(id="credential-decryptedPassword")
    private WebElement credentialDecryptedPasswordInputField;

    @FindBy(id="buttonSubmitCredentialsForm")
    private WebElement credentialFormSubmitButton;

    @FindBy(id="editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id="buttonDeleteCredential")
    private WebElement buttonDeleteCredential;

    @FindBy(id="usernameOnTable")
    private WebElement usernameOnTable;

    @FindBy(id="credentialTable")
    private WebElement credentialTable;



    //Output elements
    @FindBy(css = "body > div > div > span")
    private WebElement resultMessage;

    @FindBy(css = "body > div > div > span > a")
    private WebElement resultRedirectLink;



    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Tab accesses
    public void accessNoteTab(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOf(notesTab));
        notesTab.click();
    }

    public void accessCredentialTab(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOf(credentialsTab));
        credentialsTab.click();
    }


    //adding Notes and Credentials
    public void addNewNote(String noteTitle, String noteDescription, WebDriver driver){
        accessNoteTab(driver);
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("addNoteButton"))));
        addNoteButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(noteTitleInputField), ExpectedConditions.visibilityOf(noteDescriptionInputField)));
        noteTitleInputField.sendKeys(noteTitle);
        noteDescriptionInputField.sendKeys(noteDescription);

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(noteFormSubmitButton));
        noteFormSubmitButton.click();

    }

    public void addNewCredential(String url, String userName, String password, WebDriver driver){
        accessCredentialTab(driver);
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("credentialTable"))));
        addCredentialButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(credentialUrlInputField),ExpectedConditions.visibilityOf(credentialDecryptedPasswordInputField), ExpectedConditions.visibilityOf(credentialUsernameInputField)));
        credentialUrlInputField.sendKeys(url);
        credentialUsernameInputField.sendKeys(userName);
        credentialDecryptedPasswordInputField.sendKeys(password);

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(credentialFormSubmitButton));
        credentialFormSubmitButton.click();

    }


    public boolean checkResultPage(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOf(resultMessage));

        if (resultMessage.getText().equals("Your changes were successfully saved. Click here to continue.")) {
            resultRedirectLink.click();
            return true;

        } else {
            return false;
        }
    }

    //checking Tables after adding an element
    public boolean checkNotesTable(String expectedNoteTitle, String expectedNoteDescription, WebDriver driver){
        boolean noteTitleAndDescriptionCheck = false;

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("noteTable"))));

        try {
            String noteTitle = editNoteButton.getAttribute("data-noteTitle");
            String noteDescription = editNoteButton.getAttribute("data-noteDescription");

            if (expectedNoteTitle.equals(noteTitle) && expectedNoteDescription.equals(noteDescription)) {
                noteTitleAndDescriptionCheck = true;
            }
        }catch(Exception e){
            noteTitleAndDescriptionCheck = false;

        }
        return noteTitleAndDescriptionCheck;

    }

    public boolean checkCredentialsTable(String expectedUrl, String expectedUserName, String decryptedPassword, WebDriver driver){
        boolean credentialEditAttributesCheck = false;

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOf(credentialTable));

        try {
            String url = editCredentialButton.getAttribute("data-url");
            String username = editCredentialButton.getAttribute("data-username");
            String encryptedPassword = editCredentialButton.getAttribute("data-password");

            if (expectedUrl.equals(url) && expectedUserName.equals(username) && !decryptedPassword.equals(encryptedPassword)) {
                credentialEditAttributesCheck = true;
            }
        }catch(Exception e){
            credentialEditAttributesCheck = false;

        }
        return credentialEditAttributesCheck;

    }

    //editing Notes and Credentials
    public void editNote(String noteTitleEdit, String noteDescriptionEdit, WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(editNoteButton));
        editNoteButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(noteTitleInputField), ExpectedConditions.visibilityOf(noteDescriptionInputField)));
        noteTitleInputField.clear();
        noteTitleInputField.sendKeys(noteTitleEdit);
        noteDescriptionInputField.clear();
        noteDescriptionInputField.sendKeys(noteDescriptionEdit);

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(noteFormSubmitButton));
        noteFormSubmitButton.click();

    }

    public boolean editCredential(String urlEdit, String userNameEdit, String passwordEdit, String decryptedPassword, WebDriver driver){
        boolean passwordCheckEditModal = false;

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        editCredentialButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(credentialUrlInputField),ExpectedConditions.visibilityOf(credentialDecryptedPasswordInputField), ExpectedConditions.visibilityOf(credentialUsernameInputField)));

        String passwordFromEditModal = editCredentialButton.getAttribute("data-decryptedpassword");
        if (passwordFromEditModal.equals(decryptedPassword)) {
            passwordCheckEditModal = true;
        }
        credentialUrlInputField.clear();
        credentialUrlInputField.sendKeys(urlEdit);
        credentialUsernameInputField.clear();
        credentialUsernameInputField.sendKeys(userNameEdit);
        credentialDecryptedPasswordInputField.clear();
        credentialDecryptedPasswordInputField.sendKeys(passwordEdit);

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(credentialFormSubmitButton));
        credentialFormSubmitButton.click();

        return passwordCheckEditModal;
    }


    //deleting Notes and Credentials

    public void deleteNote(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(deleteNoteButton));
        deleteNoteButton.click();
    }

    public void deleteCredential(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonDeleteCredential));
        buttonDeleteCredential.click();
    }

}
