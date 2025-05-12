import util.RestClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;
import util.*;

public class UserProfileTest {

    private WebDriver driver;
    private RestClient api;

    private String email, password, name, accessToken;

    private HomePage home;
    private LoginPage login;
    private PersonalAccountPage profile;

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
        profile = new PersonalAccountPage(driver);

        loginThroughUI();
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
    @DisplayName("Переход в личный кабинет и проверка текста")
    public void goToProfile() {
        home.header.clickAccountButton();
        String text = profile.getProfileText();
        Assert.assertEquals(
                "В этом разделе вы можете изменить свои персональные данные", text);
    }

    @Test
    @DisplayName("Переход из профиля в конструктор по кнопке 'Конструктор'")
    public void backToConstructorViaButton() {
        home.header.clickAccountButton();
        profile.header.clickConstructorButton();
        check("Конструктор отображается",
                new ConstructorPage(driver).isMenuVisible());
    }

    @Test
    @DisplayName("Переход из профиля в конструктор по логотипу")
    public void backToConstructorViaLogo() {
        home.header.clickAccountButton();
        profile.header.clickLogoButton();
        check("Конструктор отображается",
                new ConstructorPage(driver).isMenuVisible());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logout() {
        home.header.clickAccountButton();
        profile.clickLogout();
        Assert.assertTrue("Форма логина должна отображаться",
                login.isLoginFormVisible());
    }

    @Step("Авторизация через UI (ожидание полного входа)")
    private void loginThroughUI() {
        home.open(Config.BASE_URL);
        profile.header.clickAccountButton();
        login.login(email, password);
        Assert.assertTrue(new ConstructorPage(driver).isMenuVisible());
    }
}
