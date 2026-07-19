package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.xpath("//input[@name='username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    // Define steps
    public void open(){
        Allure.step("Open login page", () -> {
            String url = ConfigReader.get("login.base.url");
            driver.get(url);
            wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
            ScreenshotUtil.takeScreenshot(driver, "login_page");
        });
    }

    public void enterUsername(String username) throws InterruptedException {
        Allure.step("Enter username" + username, () -> {
            WebElement usernameInput = driver.findElement(USERNAME_INPUT);
            highlight(usernameInput);

            usernameInput.sendKeys(username);
            ScreenshotUtil.takeScreenshot(driver, "enter_username");
            Thread.sleep(1000);
            unhighlight(usernameInput);
        });
    }

    public void enterPass(String pass) throws InterruptedException {
        Allure.step("Enter password", () -> {
            WebElement passInput = driver.findElement(PASSWORD_INPUT);
            highlight(passInput);

            passInput.sendKeys(pass);
            ScreenshotUtil.takeScreenshot(driver, "enter_password");
            Thread.sleep(1000);
            unhighlight(passInput);
        });
    }

    public void clickLoginBtn() throws InterruptedException{
        Allure.step("Click login button", () -> {
            WebElement loginBtn = driver.findElement(LOGIN_BUTTON);
            highlight(loginBtn);

            loginBtn.click();
            ScreenshotUtil.takeScreenshot(driver, "click_login_button");
            Thread.sleep(1000);
            unhighlight(loginBtn);
        });
    }

    public void login(String username, String pass) throws InterruptedException{
        open();
        enterUsername(username);
        enterPass(pass);
        clickLoginBtn();
    }
}
