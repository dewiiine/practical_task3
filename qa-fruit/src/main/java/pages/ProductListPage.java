package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductListPage extends BasePage {

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement buttonAdd;

    public void buttonAddClick(){
        buttonAdd.click();
    }

    @FindBy(xpath = "//input[@placeholder='Наименование']")
    private WebElement productNameField;

    public void productNameField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Наименование']")));
        productNameField.click();
        productNameField.sendKeys("Мандарин");
    }

    @FindBy(xpath = "//select[@class='form-control']//option[text()='Фрукт']")
    private WebElement productTypeList;
    public void productTypeList(){
        productTypeList.click();
    }

    @FindBy(id = "save")
    private WebElement buttonSave;
    public void buttonSaveClick(){
        buttonSave.click();
    }
}