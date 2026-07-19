package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.CsvReader;

import java.io.IOException;
import java.util.List;

//Epic > feature > story > test case

@Epic("OrangeHRM web")
@Feature("Authentication")
public class LoginTest extends BaseTest {

    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login success with Admin user and ***** password and redirect to dashboard page")
    @Test(description = "Test login success")
    public void testLoginSuccess() throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        String username = ConfigReader.get("admin.username");
        String password = ConfigReader.get("admin.password");
        loginPage.login(username, password);

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));
    }

    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login fail with Admin user and ***** password")
    @Test(description = "Test login fail")
    public void testLoginFail() throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login("Admin", "admin1234");

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("dashboard"));
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws IOException {
        String pathFile = "src/test/resources/loginData.csv";
        List<String[]> dataCsv = CsvReader.readCsv(pathFile);
        Object[][] data = CsvReader.toDataProviderArray(dataCsv);
        return data;
    }

    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with data provider")
    @Test(description = "Test login with data csv", dataProvider = "loginData")
    public void testDataLogin(String username, String password, String expectedResult) throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login(username, password);
        String currentUrl = getDriver().getCurrentUrl();

        if (expectedResult.equalsIgnoreCase("success")){
            Assert.assertTrue(currentUrl.contains("dashboard"));
        } else {
            Assert.assertTrue(currentUrl.contains("auth/login"));
        }

    }
}
