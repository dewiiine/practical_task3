package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductListPage extends BasePage {

    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement buttonAdd;
    @FindBy(xpath = "//input[@placeholder='Наименование']")
    private WebElement productNameField;
    @FindBy(id = "save")
    private WebElement buttonSave;
    @Getter
    @FindBy(id = "exotic")
    private WebElement exoticCheckbox;
    @FindBy(id = "type")
    private WebElement itemTypeDropDown;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public void buttonAddClick() {
        buttonAdd.click();
    }

    public void fillProductNameField(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Наименование']")));
        productNameField.sendKeys(productName);
    }

    public void buttonSaveClick() {
        buttonSave.click();
    }

    public void selectProductType(String productType) {
        itemTypeDropDown.click();
        itemTypeDropDown.findElement(By.xpath(String.format("./option[text()='%s']", productType))).click();
    }

    public List<WebElement> getProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr")));
        return driver.findElements(By.xpath("//tbody/tr"));
    }

    public boolean
    assertNewElement(String name, String type, boolean exotic) {
        List<WebElement> productList = getProducts();
        List<WebElement> lastElement = productList.get(productList.size() - 1).findElements(By.xpath("./td"));
        String actualName = lastElement.get(0).getText();
        String actualType = lastElement.get(1).getText();
        Boolean actualExotic = Boolean.parseBoolean(lastElement.get(2).getText());

        return actualName.equals(name) && actualType.equals(type) && actualExotic.equals(exotic);
    }

    /**
     * Активировать чекбокс в зависимости от получаемого значения.
     *
     * @param isExotic
     */
    public void selectExotic(boolean isExotic) {
        if (isExotic) {
            getExoticCheckbox().click();
        }
    }

}