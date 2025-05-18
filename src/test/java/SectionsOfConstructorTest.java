import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.ConstructorPage;
import page.object.*;
import util.Config;
import util.WebDriverFactory;

public class SectionsOfConstructorTest {

    private WebDriver driver;
    private HomePage home;
    private ConstructorPage constructor;

    @Before
    public void setUp() {
        driver      = WebDriverFactory.create();
        home        = new HomePage(driver);
        constructor = new ConstructorPage(driver);

        home.open(Config.BASE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Таб 'Булки' отображается")
    public void bunsTabVisible() {
        constructor.isMenuVisible();
        home.header.clickConstructorButton();
        constructor.clickSauceTab(); // переключаемся, потом назад
        constructor.clickBunTab();
        Assert.assertTrue(constructor.isBunSectionVisible());
    }

    @Test
    @DisplayName("Таб 'Соусы' отображается")
    public void saucesTabVisible() {
        constructor.isMenuVisible();
        home.header.clickConstructorButton();
        constructor.clickSauceTab();
        Assert.assertTrue(constructor.isSauceSectionVisible());
    }

    @Test
    @DisplayName("Таб 'Начинки' отображается")
    public void fillingsTabVisible() {
        constructor.isMenuVisible();
        home.header.clickConstructorButton();
        constructor.clickFillingTab();
        Assert.assertTrue(constructor.isFillingSectionVisible());
    }
}
