import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Log4j2
public class SolutionTwo {
    public static void main(String[] args) throws InterruptedException {
        var login = "ya.osipova.sis+1@gmail.com";
        var password = "Test1!test";
        String chromeDriverPath = "lib/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://otus.ru");
        driver.findElement(By.cssSelector("button.header2__auth")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var element = driver.findElement(By.xpath("//form[@action = '/login/']//input[@name = 'email']"));
        element.clear();
        element.sendKeys(login);

        element = driver.findElement(By.xpath("//form[@action = '/login/']//input[@name = 'password']"));
        element.clear();
        element.sendKeys(password);

        driver.findElement(By.xpath("//form[@action = '/login/']//button[@type = 'submit']")).submit();

        log.info(driver.manage().getCookies());
        driver.quit();
    }
}
