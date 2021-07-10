package org.tyaa.demo.java.springboot.selenium.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SmokeTests {

    @Test
    public void sanityCheck() {

        // List of urls
        List<String> list = new ArrayList<String>();
        list.add("https://www.starbucks.co.uk/");
        list.add("https://www.starbucks.co.uk/404"); //error page
        list.add("https://www.starbucks.de/");
        list.add("https://www.starbucks.fr/");

        for (String str : list) {

            // System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
            // WebDriver driver = new ChromeDriver();
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().setSize(new Dimension(1366, 768));
            driver.get(str);
            System.out.println("Page title is - " + driver.getTitle());

            // sleep
            sleep(3000);

            try {
                driver.findElement(By.xpath("//a[@class='call']")).click();
                System.out.println("f");
            } catch (NoSuchElementException e) {
                System.out.println("n");
            }

            WebElement element1 = driver.findElement(By.xpath("//div[contains(@class,'truste_overlay')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", element1);

            WebElement element = driver.findElement(By.xpath("//div[contains(@class,'truste_box_overlay')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", element);

            try {
                driver.findElement(By.xpath("//div[@class='wrapper js-wrapper']"));
                System.out.println("content is loaded");
            } catch (NoSuchElementException e) {
                System.out.println("content is NOT loaded");
            }

            System.out.println("------------\n\n------------");
            // navigate to each Link on the webpage

            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class,'copy-03 bold primary-navigation-item')]"));
//				    //clicking all links
            for (int i=0; i<elements.size(); i++){
                WebElement el = driver.findElements(By.xpath("//div[contains(@class,'copy-03 bold primary-navigation-item')]")).get(i);
                System.out.println("Link getting clicked"  + el.getText());
                el.click();
                driver.navigate().back();
            }
//				driver.findElement(By.tagName("a")).click();
            sleep(1000);
            // Close browser
            driver.quit();
        }
    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
}
