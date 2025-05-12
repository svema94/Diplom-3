package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By constructorBtn = By.xpath(".//p[text()='Конструктор']");
    private final By logoBtn = By.className("AppHeader_header__logo__2D0X2");
    private final By accountBtn = By.xpath(".//p[text()='Личный Кабинет']");

    @Step("Нажатие 'Конструктор'")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorBtn)).click();
    }

    @Step("Нажатие на логотип")
    public void clickLogoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoBtn)).click();
    }

    @Step("Нажатие 'Личный Кабинет'")
    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountBtn)).click();

    }
}
