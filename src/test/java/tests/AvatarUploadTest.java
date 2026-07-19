package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PersonalPage;
import utils.ConfigReader;

@Epic("OrangeHRM web")
@Feature("PIM - Employee photo")
public class AvatarUploadTest extends BaseTest {
    private static final String EMP_NUM = "7";
    private static final String AVT_TEST = "avt-test.png";

    private PersonalPage personalPage;

    @BeforeMethod
    public void loginAndOpenPersonalPage() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        String username = ConfigReader.get("admin.username");
        String password = ConfigReader.get("admin.password");
        loginPage.login(username, password);

        personalPage = new PersonalPage(getDriver(), getWait());
        personalPage.open(EMP_NUM);
    }

    @Story("Upload avatar")
    @Severity(SeverityLevel.NORMAL)
    @Description("Upload avatar success")
    @Test(description = "Test upload avatar success")
    public void testUploadAvtTest() throws InterruptedException{
        personalPage.uploadAvatar(AVT_TEST);

        boolean isUploaded = personalPage.isAvtUploadSuccessfully();
        Assert.assertTrue(isUploaded, "Avatar upload fail");
    }

}