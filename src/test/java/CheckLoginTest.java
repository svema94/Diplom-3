import util.RestClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;
import util.*;

public class CheckLoginTest {

    private WebDriver driver;
    private RestClient api;

    private String email, password, name, accessToken;

    private HomePage home;
    private LoginPage login;

    @Before
    public void setUp() {
        email = TestDataGenerator.email();
        password = TestDataGenerator.password();
        name = TestDataGenerator.name();

        api = new RestClient(Config.BASE_URL);
        accessToken = api.createUser(new User(email, password, name));

        driver = WebDriverFactory.create();
        home = new HomePage(driver);
        login = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
        api.deleteUser(accessToken);
    }

    @Step("Проверка: {0}")
    private void check(String message, boolean condition) {
        Assert.assertTrue(message, condition);
    }

    @Test
    @DisplayName("Логин через кнопку 'Войти в аккаунт' на главной")
    public void loginFromMainButton() {
        home.open(Config.BASE_URL);
        home.clickLoginButton();
        check("Форма логина видима", login.isLoginFormVisible());

        login.login(email, password);

        boolean visible = new ConstructorPage(driver).isMenuVisible();
        check("Конструктор отображается", visible);
    }

    @Test
    @DisplayName("Логин через кнопку 'Личный кабинет'")
    public void loginFromHeader() {
        home.open(Config.BASE_URL);
        home.header.clickAccountButton();
        check("Форма логина видима", login.isLoginFormVisible());

        login.login(email, password);
        check("Конструктор отображается",
                new ConstructorPage(driver).isMenuVisible());
    }

    @Test
    @DisplayName("Логин через ссылку в форме регистрации")
    public void loginViaRegistrationForm() {
        home.open(Config.BASE_URL);
        home.clickLoginButton();
        login.goToRegistration();
        new RegistrationPage(driver).goToLogin();

        login.login(email, password);
        check("Конструктор отображается",
                new ConstructorPage(driver).isMenuVisible());
    }

    @Test
    @DisplayName("Логин через ссылку в форме восстановления пароля")
    public void loginFromResetPasswordPageTest() {
        home.open(Config.BASE_URL);
        home.header.clickAccountButton();

        login.goToResetPassword();
        new RecoverPasswordPage(driver).clickLoginLink();

        login.login(email, password);
        Assert.assertTrue("После логина должен отображаться конструктор",
                new ConstructorPage(driver).isMenuVisible());
    }
}
