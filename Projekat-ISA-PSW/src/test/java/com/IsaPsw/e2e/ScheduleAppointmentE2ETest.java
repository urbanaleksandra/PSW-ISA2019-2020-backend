package com.IsaPsw.e2e;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class ScheduleAppointmentE2ETest {

    private static final String base_url = "http://localhost:4200/";
    private WebDriver browser;
    private FirstPage firstPage;

    @Before
    public void setUp() {
        // instantiate chrome browser
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();

        //maximize window
        browser.manage().window().maximize();

        firstPage = PageFactory.initElements(browser, FirstPage.class);

    }
    @Test //negativan
    public void testLoginFailure() {
        browser.get("http://localhost:4200/login");
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        browser.findElement(By.id("email")).sendKeys("nursee");
        browser.findElement(By.id("password")).sendKeys("123");
        browser.findElement(By.id("loginButton")).click();

        (new WebDriverWait(browser, 7))
                .until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("errorMessage")));
        System.out.println(browser.findElement(By.id("errorMessage")).getText());
        assertEquals(browser.findElement(By.id("errorMessage")).getText(), "Invalid Credentials");

    }

    @Test //pozitivan
    public void testLoginSuccess() {
        browser.get("http://localhost:4200/login");
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        browser.findElement(By.id("email")).sendKeys("nurse");
        browser.findElement(By.id("password")).sendKeys("123");
        browser.findElement(By.id("loginButton")).click();
        WebDriverWait webDriverWait = new WebDriverWait(browser, 35);
        assertNotEquals(browser.getCurrentUrl(),"http://localhost:4200/login");
        assertNotEquals(browser.getCurrentUrl(),"http://localhost:4200/");
    }


    @After
    public void tearDown() {
        browser.quit();
    }
}
