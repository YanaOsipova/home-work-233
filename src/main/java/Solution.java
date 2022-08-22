import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        String chromeDriverPath = "lib/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1400,800","--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver
           .findElement(By.className("w3-gallery"))
           .findElement(By.tagName("div"))
           .findElement(By.className("portfolio-area"))
           .findElements(By.className("portfolio-item2")).get(0)
           .findElement(By.tagName("span"))
           .findElement(By.tagName("a"))
           .findElement(By.className("content-overlay"))
           .click();
        try {
            var webElement = driver.switchTo().parentFrame()
                    .findElement(By.className("pp_pic_holder"));
            if (webElement.isDisplayed()) {
                System.out.println("displayed");
            }
        } catch (Exception exception) {
            System.out.println("not found");
            driver.quit();
        }
        Thread.sleep(4000);
        driver.quit();
    }
}
