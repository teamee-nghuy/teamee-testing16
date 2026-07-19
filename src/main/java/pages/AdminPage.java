package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage extends BasePage {
    private static final By USERNAME_INPUT = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private static final By USER_ROLE_INPUT = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private static final By ADMIN_ROLE_OPTIONS = By.xpath("//div[@role='option']//span[text()='Admin']");
    private static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");
    private static final By DATA_ROWS = By.xpath("//div[@class='oxd-table-card']");
    private static final By RECORD_COUNT_TEXT = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span");

    public AdminPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public void open() {
        Allure.step("Open admin page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
            wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        });
    }

    public void enterUsername(String username) throws InterruptedException {
        Allure.step("Enter username", () -> {
            WebElement usernameInput = driver.findElement(USERNAME_INPUT);
            highlight(usernameInput);

            usernameInput.sendKeys(username);
            Thread.sleep(1000);
            unhighlight(usernameInput);
        });
    }

    public void selectUserRole(String role) throws InterruptedException{
        Allure.step("Select user role", () -> {
            WebElement roleSelect = driver.findElement(USER_ROLE_INPUT);
            highlight(roleSelect);
            roleSelect.click();
            Thread.sleep(2000);
            unhighlight(roleSelect);

            String xpathRoleOption = "//div[@role='option']//span[text()='" + role + "']";
            WebElement adminRoleOption = driver.findElement(ADMIN_ROLE_OPTIONS);
            highlight(adminRoleOption);
            adminRoleOption.click();

            Thread.sleep(1000);
            unhighlight(adminRoleOption);
        });
    }

    public void clickSearchBtn() throws InterruptedException{
        Allure.step("Click search button", () -> {
            WebElement searchBtn = driver.findElement(SEARCH_BUTTON);
            highlight(searchBtn);

            searchBtn.click();
            Thread.sleep(1000);
            unhighlight(searchBtn);
        });
    }

    public boolean checkNumberOfRecords(){
        return Allure.step("Check number of record", () -> {
            WebElement recordCntText = wait.until(ExpectedConditions.visibilityOfElementLocated(RECORD_COUNT_TEXT));
            String cntText = recordCntText.getText();
            int cntRecord = Integer.parseInt(cntText.replaceAll("\\D+", ""));

            int cntDataRows = driver.findElements(DATA_ROWS).size();
            return cntRecord == cntDataRows;
        });
    }

    public void filterByAdminUser(String username, String role) throws InterruptedException{
        enterUsername(username);
        selectUserRole(role);
        clickSearchBtn();
    }
}
