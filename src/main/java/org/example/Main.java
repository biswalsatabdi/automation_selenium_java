package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Main {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://nextv3.thinktalent.info/";
    private final String username = "satabdibiswal4648@gmail.com";
    private final String password = "13972684";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testFeedbackFlow() {
        try {
            login();
            selectAccount(1);
            navigateToTaskList();
            clickFeedbackCard();
            addRespondent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login() {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginbtn")).click();
    }

    public void selectAccount(int index) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".test_all_account_access_btn_QA"), index));
        List<WebElement> accessButtons = driver.findElements(By.cssSelector(".test_all_account_access_btn_QA"));
        accessButtons.get(index).click();
    }

    public void navigateToTaskList() {
        driver.get(baseUrl + "landing-user/user/task-list");
    }

    public void clickFeedbackCard() throws InterruptedException {
        WebElement feedbackLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div[data-integrationname='FEEDBACK'] a")));
        scrollToAndClick(feedbackLink);
        System.out.println("Feedback card clicked successfully.");
        Thread.sleep(3000);
    }

    public void scrollToAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element);
    }
    public void addRespondent() throws InterruptedException {
        driver.findElement(By.cssSelector("button.btn.next_outline_accent1_btn.respondent_btn.test_respondents_modal_toggle_btn.btn-secondary")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".next_outline_btn.mr-2.test_feedback_addRespondent_btn.btn.btn-secondary")).click();
        driver.findElement(By.cssSelector(".next_input.test_addRespondent_name_input.form-control")).sendKeys("Subhashree");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".next_input.test_addRespondent_email_input.form-control")).sendKeys("subhashreebehera505@gmail.com");
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
