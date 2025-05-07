package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

public class WebDriverFactory {

    public static WebDriver createForName(String browserName) {
        switch (browserName.toUpperCase()) {
            case "CHROME":
                return createChromeDriver();
            case "YANDEX":
                return createYandexDriver();
            default:
                throw new RuntimeException("Нераспознанный браузер: " + browserName);
        }
    }

    public static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public static WebDriver createYandexDriver() {
        String yandexBrowserPath = Objects.requireNonNull(System.getenv("YANDEX_BROWSER_PATH"),
                "Переменная среды YANDEX_BROWSER_PATH не установлена!");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexBrowserPath);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-gpu");

        return new ChromeDriver(options);
    }
}
