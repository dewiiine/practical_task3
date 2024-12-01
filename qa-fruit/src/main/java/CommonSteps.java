import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.ProductListPage;

public class CommonSteps {
    private final WebDriver driver;

//    private BasePage currentPage;

    private MainPage mainPage;
    private ProductListPage productListPage;

    public CommonSteps(WebDriver driver) {
        this.driver = driver;
        this.productListPage = new ProductListPage(driver);
        this.mainPage = new MainPage(driver);
    }

    public MainPage getMainPage() {
        this.mainPage = new MainPage(driver);
        return this.mainPage;
    }

    public ProductListPage getProductListPage() {
        this.productListPage = new ProductListPage(driver);
        return this.productListPage;
    }
}
