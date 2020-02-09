package com.IsaPsw.e2e;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ScheduleAppointmentE2ETest {
    @LocalServerPort
    private static int port;
    private static final String base_url = "http://localhost:" + Integer.toString(port);
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

        browser.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
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
        browser.findElement(By.id("email")).sendKeys("cadmin");
        browser.findElement(By.id("password")).sendKeys("cadmin");
        browser.findElement(By.id("loginButton")).click();
        browser.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        assertNotEquals(browser.getCurrentUrl(),"http://localhost:4200/login");
        assertNotEquals(browser.getCurrentUrl(),"http://localhost:4200/");
    }

    @Test //pozitivan
    public void testFindRoomForRequestAppointment() {
        browser.get("http://localhost:4200/login");
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        browser.findElement(By.id("email")).sendKeys("cadmin");
        browser.findElement(By.id("password")).sendKeys("cadmin");
        browser.findElement(By.id("loginButton")).click();
        browser.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        (new WebDriverWait(browser, 40))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("appRequest"))));
        assertEquals(browser.getCurrentUrl(),"http://localhost:4200/cadmin-home-page");
        browser.findElement(By.id("appRequest")).click();
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("requestTable"))));
        browser.findElement(By.id("1")).click();
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("buttonFindRoom"))));
        browser.findElement(By.id("buttonFindRoom")).click();
        assertNotEquals(browser.getCurrentUrl(),"http://localhost:4200/cadmin-home-page");
    }

    @Test //pozitivan
    public void testSearchClinics() {
        browser.get("http://localhost:4200/login");
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        browser.findElement(By.id("email")).sendKeys("masa");
        browser.findElement(By.id("password")).sendKeys("masa");
        browser.findElement(By.id("loginButton")).click();
        browser.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        (new WebDriverWait(browser, 50))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("clinicSearch"))));
        browser.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        assertEquals(browser.getCurrentUrl(),"http://localhost:4200/patient-home-page");
        browser.findElement(By.id("clinicSearch")).click();
        (new WebDriverWait(browser, 50))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("search123"))));
        browser.findElement(By.id("search123")).click();
        (new WebDriverWait(browser, 50))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("tableSearch"))));
    }

    @Test //pozitivan
    public void testFastAppointments() {
        browser.get("http://localhost:4200/login");
        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        browser.findElement(By.id("email")).sendKeys("masa");
        browser.findElement(By.id("password")).sendKeys("masa");
        browser.findElement(By.id("loginButton")).click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        (new WebDriverWait(browser, 50))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("clinicSearch"))));
        browser.findElement(By.id("clinicSearch")).click();
        browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        assertEquals(browser.getCurrentUrl(),"http://localhost:4200/clinics");
        (new WebDriverWait(browser, 50))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.id("here"))));
        browser.findElement(By.id("here")).click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        (new WebDriverWait(browser, 50))
                .until(ExpectedConditions.visibilityOf(browser.findElement(By.name("tip5"))));
        browser.findElement(By.name("tip5")).click();
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}
