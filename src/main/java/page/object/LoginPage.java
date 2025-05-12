package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By loginForm    = By.className("Auth_login__3hAey");
    private final By emailInput   = By.xpath("//input[@name='name']");
    private final By passwordInput= By.xpath("//input[@name='Пароль']");
    private final By loginButton  = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPassLink  = By.xpath("//a[text()='Восстановить пароль']");

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Клик 'Войти'")
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Переход на страницу регистрации")
    public void goToRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Логин (email+пароль)")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLogin();
    }

    @Step("Форма логина загружена?")
    public boolean isLoginFormVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm))
                .isDisplayed();
    }
    @Step("Переход на страницу восстановления пароля")
    public void goToResetPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPassLink)).click();
    }
}
