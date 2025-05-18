package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By nameInput = By.xpath("//fieldset[1]//input");
    private final By emailInput = By.xpath("//fieldset[2]//input");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.className("input__error");
    private final By loginLink = By.className("Auth_link__1fOlj");

    @Step("Ввод имени: {name}")
    public void setName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Переход на страницу логина")
    public void goToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Получение текста ошибки под полем пароля")
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError))
                .getText();
    }

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegister();
    }
}
