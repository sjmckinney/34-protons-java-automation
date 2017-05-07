package com._34protons.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private final String path = "login.php";
    private final String pageTitle = "www.34protons.co.uk | Login";
    private final String errMsgText = "Username or password invalid";
    private final WebDriver driver;
    private final By loadingImgLocator = By.id("loading");
    private final By errMsgLocator = By.id("input_error");

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(how = How.NAME, using = "submit")
    private WebElement submitBtn;

    public LoginPage(WebDriver sharedDriver) {
        super(sharedDriver);
        driver = sharedDriver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        super.load(path);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }

    public void executeLogin(String username, String password) {

        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        submitBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.invisibilityOfElementLocated(loadingImgLocator),
                ExpectedConditions.visibilityOfElementLocated(errMsgLocator)
                )
        );
    }

    public String getErrMsg() {
        return driver.findElement(errMsgLocator).getText();
    }
}
