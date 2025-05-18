package page.object;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    public final HeaderPage header;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    @Step("Открытие главной страницы")
    public void open(String url) {
        driver.get(url);
    }

    @Step("Клик 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}