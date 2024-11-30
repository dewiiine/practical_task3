package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Navbar {
    private WebDriver driver;

    private WebElement buttonHome = driver.findElement(By.xpath("//div[@class='content']//a[@class='navbar-brand']"));
    private WebElement buttonXsdSchemes = driver.findElement(By.xpath("//div[@class='content']//li[a[text()='XSD-схемы']]"));
    private WebElement buttonSandbox = driver.findElement(By.xpath("//div[@class='content']//li[a[text()='Песочница']]"));
    private WebElement buttonApi = driver.findElement(By.xpath("//div[@class='content']//li[a[text()='API']]"));


}
