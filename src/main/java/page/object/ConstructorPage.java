package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By bunTab = By.xpath("//span[text()='Булки']");
    private final By sauceTab = By.xpath("//span[text()='Соусы']");
    private final By fillingTab = By.xpath("//span[text()='Начинки']");
    private final By bunSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[contains(text(), 'Булки')]");
    private final By sauceSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[contains(text(), 'Соусы')]");
    private final By fillingSection =  By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[contains(text(), 'Начинки')]");
    private final By menuContainer = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");

    @Step("Клик по табу 'Булки'")
    public void clickBunTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunTab)).click();
    }

    @Step("Клик по табу 'Соусы'")
    public void clickSauceTab() {
        wait.until(ExpectedConditions.elementToBeClickable(sauceTab)).click();
    }

    @Step("Клик по табу 'Начинки'")
    public void clickFillingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingTab)).click();
    }

    @Step("Ожидание отображения контейнера меню")
    public boolean isMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(menuContainer))
                .isDisplayed();
    }

    @Step("Проверка, что секция булок видна в области видимости")
    public boolean isBunSectionVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunSection));
        return isElementInView(element);
    }

    @Step("Проверка, что секция соусов видна в области видимости")
    public boolean isSauceSectionVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sauceSection));
        return isElementInView(element);
    }

    @Step("Проверка, что секция начинок видна в области видимости")
    public boolean isFillingSectionVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingSection));
        return isElementInView(element);
    }

    private boolean isElementInView(WebElement element) {
        Rectangle rect = element.getRect();
        Dimension window = driver.manage().window().getSize();
        return rect.getY() >= 0 && rect.getY() + rect.getHeight() <= window.getHeight();
    }

}
