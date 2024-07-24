package lab05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UVicTest {

    WebDriver browser;

    @BeforeEach
    public void setUp() {
        // Chrome
        System.setProperty("webdriver.chrome.driver", "D:\\UVIC work\\Year 1\\Jan-Apr\\SENG 275\\lab05\\lib\\src\\test\\resources\\chromedriver.exe");
        browser = new ChromeDriver();

        // Firefox
        // System.setProperty("webdriver.gecko.driver", "*****LOCATION OF YOUR WEBDRIVER*****");
        // browser = new FirefoxDriver();

        // Safari
        // browser = new SafariDriver();

        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }


    // Your tests go here

    @Test
    void uvicPageLoads() {
        browser.get("https://www.uvic.ca/");                  //Go to webpage
        assertEquals("Home - University of Victoria", browser.getTitle());  //Assert webpage title is expected
    }

    @Test
    void containsSearchButton(){
        browser.get("https://www.uvic.ca/");                  //Go to webpage

        WebElement searchButton = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]"));                           //Search button


        assertTrue(searchButton.isEnabled());
    }
    @Test
    void buttonToSearchBar(){
        browser.get("https://www.uvic.ca/");                  //Go to webpage
        WebElement searchButton = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]"));                           //Search button
        searchButton.click();                       //Click search button
        new WebDriverWait(browser, 5);
        WebElement searchBar = browser.findElement(By.xpath("/html/body/header/div[1]/div[1]/div/div/form/div/input[1]"));
        assertTrue(searchBar.isEnabled() && searchBar.isDisplayed());               //Check if search bar is enabled and displayed
    }

    @Test
    void inputAppearsInBar(){

        browser.get("https://www.uvic.ca/");                  //Go to webpage
        WebElement searchButton = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]"));                           //Search button
        searchButton.click();                       //Click search button
        new WebDriverWait(browser, 5);
        WebElement searchBar = browser.findElement(By.xpath("/html/body/header/div[1]/div[1]/div/div/form/div/input[1]"));
        searchBar.sendKeys("csc");
        assertEquals("csc", searchBar.getAttribute("value"));               //Check if search bar is enabled and displayed

    }

    @Test
    void enterSearch(){
        browser.get("https://www.uvic.ca/");                  //Go to webpage
        WebElement searchButton = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]"));                           //Search button
        searchButton.click();                       //Click search button
        new WebDriverWait(browser, 5);
        WebElement searchBar = browser.findElement(By.xpath("/html/body/header/div[1]/div[1]/div/div/form/div/input[1]"));
        searchBar.sendKeys("csc");
        searchBar.sendKeys(Keys.ENTER);
        assertEquals("Search - University of Victoria", browser.getTitle());               //Check if search bar is enabled and displayed

    }
    @Test
    void confirmPhoneNumber(){
        browser.get("https://www.uvic.ca/about-uvic/contact-us/index.php");                  //Go to webpage
        WebElement phoneNumber = browser.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div/p[2]/a"));
        assertEquals("250-721-8121",phoneNumber.getText());

    }

}
