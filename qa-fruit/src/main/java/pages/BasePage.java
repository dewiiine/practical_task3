package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectSandboxItem(String itemName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement buttonSandbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("navbarDropdown")));

        buttonSandbox.click();
        buttonSandbox.findElement(By.xpath(String.format("//a[text()='%s']", itemName))).click();
    }
}
