package org;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class RecruitmentFlow {
    private WebDriver driver;

    public RecruitmentFlow(WebDriver driver) {
        this.driver=driver;
    }

    public void RecruitmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRecruitment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement recruitmentMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[5]/a")));
        recruitmentMenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")));
    }

    public void addCandidate(String firstName, String lastName, String email, String resumePath) {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")).click();

        // Add explicit wait for the first name field to become visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));

        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input")).sendKeys(email);

        WebElement resumeUpload = driver.findElement(By.xpath("//input[@type='file']"));
        resumeUpload.sendKeys(new File(resumePath).getAbsolutePath());

        // Submit
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[8]/button[2]")).click();
    }

    public void takeScreenshot(String path) throws Exception {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(path);
        org.openqa.selenium.io.FileHandler.copy(src, dest);
    }
}
