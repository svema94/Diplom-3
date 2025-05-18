package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public final HeaderPage header;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.header = new HeaderPage(driver);
    }

    private final By profileText = By.xpath(
            "//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By logoutBtn = By.xpath("//button[text()='Выход']");

    @Step("Получить текст в личном кабинете")
    public String getProfileText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileText))
                .getText();
    }

    @Step("Клик 'Выход'")
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }
}
