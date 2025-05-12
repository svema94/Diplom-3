import util.RestClient;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;
import util.*;

public class RegistrationTest {

    private WebDriver driver;
    private RestClient api;

    private String email, name, password, accessToken;

    private HomePage home;
    private LoginPage login;
    private RegistrationPage reg;

    @Before
    public void setUp() {
        email    = TestDataGenerator.email();
        name     = TestDataGenerator.name();
        password = TestDataGenerator.password();

        api = new RestClient(Config.BASE_URL);

        driver = WebDriverFactory.create();
        home   = new HomePage(driver);
        login  = new LoginPage(driver);
        reg    = new RegistrationPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            api.deleteUser(accessToken);
        }
    }


    @Test
    @DisplayName("Успешная регистрация")
    public void successRegistration() {
        home.open(Config.BASE_URL);
        home.clickLoginButton();
        login.goToRegistration();

        reg.register(name, email, password);

        Assert.assertTrue("Форма логина должна появиться", login.isLoginFormVisible());

        login.login(email, password);

        accessToken = api.login(new User(email, password));
    }

    @Test
    @DisplayName("Ошибка при пароле короче 6 символов")
    public void shortPasswordError() {
        String shortPwd = "12345";

        home.open(Config.BASE_URL);
        home.clickLoginButton();
        login.goToRegistration();

        reg.register(name, email, shortPwd);

        Assert.assertEquals("Некорректный пароль", reg.getPasswordErrorText());
    }
}
