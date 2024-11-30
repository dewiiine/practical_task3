import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.ProductListPage;

public class ProductsTest extends BaseTest {

    @Test
    public void testFirst() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectSandboxItem("Товары");

        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.buttonAddClick();
        productListPage.productNameField();
        productListPage.productTypeList();
        productListPage.buttonSaveClick();
        productListPage.selectSandboxItem("Сброс данных");

    }
}
