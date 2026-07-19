package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;

@Epic("OrangeHRM web")
@Feature("Admin module")
public class AdminTest extends BaseTest {
    private AdminPage adminPage;

    @BeforeMethod
    public void loginAndOpenAdminPage() throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login("Admin", "admin123");

        adminPage = new AdminPage(getDriver(), getWait());
        adminPage.open();
    }

    @Story("Management system user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test filter by admin user")
    @Test(description = "Test filter by admin user")
    public void testFilterByAdminUser() throws InterruptedException{
        adminPage.filterByAdminUser("Admin", "");
        Assert.assertTrue(adminPage.checkNumberOfRecords());
    }
}
