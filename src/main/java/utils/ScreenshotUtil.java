package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {
    // Thuoc tinh tao ra folder save evidence
    public static final ThreadLocal<String> testFolder = new ThreadLocal<>();

    // Thuoc tinh de save steps for each test case
    public static final ThreadLocal<Integer> stepCnt = new ThreadLocal<>();

    public static void setTestFolder(String folderPath) {
        testFolder.set(folderPath);

        // Reset stepCnt to 0
        stepCnt.set(0);
    }

    public static void clear() {
        testFolder.remove();
        stepCnt.remove();
    }

    public static void takeScreenshot(WebDriver driver, String stepName) {
        try {
            String testFolderName = testFolder.get();

            String screenshotFolderName = testFolderName + File.separator + "screenshots";
            Files.createDirectories(Paths.get(screenshotFolderName));

            // Increase stepCnt ++
            Integer stepNum = stepCnt.get();
            stepNum = (stepNum == null) ? 1 : stepNum + 1;
            stepCnt.set(stepNum);

            // Dat ten file: step_01_functionName.png
            String fileName = String.format("step_%02d_%s.png", stepNum, stepName);

            // Screenshot -> save cache (RAM)
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Copy picture into folder
            File descFile = new File(screenshotFolderName, fileName);
            Files.copy(srcFile.toPath(), descFile.toPath());


        } catch (IOException e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
        }
    }
}
