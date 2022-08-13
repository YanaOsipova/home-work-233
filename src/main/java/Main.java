import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Main {
    public static void main(String[] args) throws InterruptedException {
        String chromeDriverPath = "lib/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1400,800");
        options.addArguments("disable-gpu");
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1400,800","--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com");
        var element = driver.findElement(By.id("search_form_input_homepage"));
        element.sendKeys("ОТУС");
        element.submit();
        element = driver.findElement(By.id("links"));

        var elements = element.findElement(By.id("r1-0"))
                .findElement(By.tagName("h2"))
                .findElement(By.tagName("a"))
                .findElement(By.tagName("span"));
        var att = elements.getText();
        driver.quit();

        if (!att.contains("Онлайн‑курсы для профессионалов, дистанционное обучение")) {
            throw new RuntimeException("not found");
        }
    }
}
