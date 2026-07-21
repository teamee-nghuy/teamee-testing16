package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage extends BasePage {
    private static final By ICON_SORT = By.xpath("//div[@role='columnheader'])[2]//i[@class='oxd-icon bi-sort-alpha-down oxd-icon-button__icon oxd-table-header-sort-icon']");
    private static final By ICON_SORT_ASC = By.xpath("(//div[@role='columnheader'])[2]//li[.//span[text() ='Ascending']]");
    private static final By EMP_ROWS = By.xpath("//div[@class='oxd-table-card']");

    public PIMPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        Allure.step("Open PIM page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");

            wait.until(ExpectedConditions.visibilityOfElementLocated(EMP_ROWS));
        });
    }

    public void clickSortByIdAsc() {
        Allure.step("Click sort by ID asc", () -> {
            WebElement iconSort = wait.until(ExpectedConditions.elementToBeClickable(ICON_SORT));
            highlight(iconSort);

            iconSort.click();
            unhighlight(iconSort);

            WebElement iconSortAsc = wait.until(ExpectedConditions.elementToBeClickable(ICON_SORT_ASC));
            highlight(iconSortAsc);
            iconSortAsc.click();
            unhighlight(iconSortAsc);
        });
    }
}
