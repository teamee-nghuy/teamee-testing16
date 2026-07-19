package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class PersonalPage extends BasePage {

    private static final By IMG_AVT = By.xpath("//div[@class='orangehrm-edit-employee-image']//img[@class='employee-image']");
    private static final By UPLOAD_BTN = By.xpath("//button[contains(@class, 'employee-image-action')]");
    private static final By INPUT_FILE = By.xpath("//input[@type='file']");
    private static final By SAVE_BTN = By.xpath("//button[@type='submit']");
    private static final By SUCCESS_TOAST = By.xpath("//div[contains(@class, 'oxd-toast--success')]");

    public PersonalPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open(String empNum) {
        Allure.step("Open personal detail page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPhotograph/empNumber/" + empNum);
            wait.until(ExpectedConditions.visibilityOfElementLocated(IMG_AVT));
        });
    }

    public void uploadAvatar(String fileName) throws InterruptedException {
        Allure.step("Upload avatar file " + fileName, () -> {
            // Click avt
            WebElement avtImg = wait.until(ExpectedConditions.elementToBeClickable(IMG_AVT));
            highlight(avtImg);
            avtImg.click();
            unhighlight(avtImg);

            // CLick upload button
            WebElement uploadBtn = wait.until(ExpectedConditions.elementToBeClickable(UPLOAD_BTN));
            highlight(uploadBtn);
            uploadBtn.click();
            unhighlight(uploadBtn);

            // Upload avt
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(INPUT_FILE));

            String filePath = new File("src/test/resources/images/" + fileName).getAbsolutePath();
            fileInput.sendKeys(filePath);

            Thread.sleep(2000);

            // Click save button
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BTN));
            highlight(saveBtn);
            saveBtn.click();
            unhighlight(saveBtn);

            Thread.sleep(2000);
        });
    }

    public boolean isAvtUploadSuccessfully(){
        return Allure.step("Check avatar upload successfully", () -> {
            WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            return successToast.isDisplayed();
        });
    }
}