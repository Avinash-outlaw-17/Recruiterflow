package org.Tests;

import org.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddResumeTest {
    @BeforeClass
    public void setUp() throws Exception {
        BaseClass.initialize();
    }

    @Test(enabled = true)
    public void testAddCandidate() throws Exception {
        BaseClass.driver.get(Constants.BASE_URL);
        WebDriverWait wait = new WebDriverWait(BaseClass.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

        LoginPage loginPage = new LoginPage(BaseClass.driver);
        loginPage.login(BaseClass.props.getProperty("username"), BaseClass.props.getProperty("password"));
        Thread.sleep(3000);

        RecruitmentFlow recruitmentPage = new RecruitmentFlow(BaseClass.driver);
        recruitmentPage.navigateToRecruitment();
        Thread.sleep(2000);

        recruitmentPage.addCandidate("Avinash", "Hiremath", "avinashhiremath7@email.com", Constants.RESUME_PATH);
        Thread.sleep(2000);

        recruitmentPage.takeScreenshot(Constants.SCREENSHOT_PATH);

        ReportUtil.sendEmailWithReport(Constants.SCREENSHOT_PATH);
    }

    @AfterClass
    public void tearDown() {
        BaseClass.quitDriver();
    }
}
