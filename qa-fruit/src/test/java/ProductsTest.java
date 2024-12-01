import org.junit.jupiter.api.Test;
import pages.ProductListPage;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest extends BaseTest {

    @Test
    public void testFruitAdd() {
        String productName = "Мандарин";
        String productType = "Фрукт";
        boolean exotic = false;

        CommonSteps commonSteps = new CommonSteps(driver);
        commonSteps.getMainPage().selectSandboxItem("Товары");

        ProductListPage productListPage = commonSteps.getProductListPage();
        assertEquals("http://localhost:8080/food", driver.getCurrentUrl(), "Текущий url не совпадает с url страницы списка товаров");

        int productsCount = productListPage.getProducts().size();

        productListPage.buttonAddClick();
        productListPage.fillProductNameField(productName);
        productListPage.selectProductType(productType);

        assertFalse(productListPage.getExoticCheckbox().isSelected(), "Неверное состояние чекбокса");
        productListPage.selectExotic(exotic);

        productListPage.buttonSaveClick();

        assertTrue(productListPage.assertNewElement(productName, productType, exotic), "Последний продукт не совпадает с переданными данными");

        productListPage.selectSandboxItem("Сброс данных");
        // Проверяем что количество продуктов до добавления равно количеству после сброса данных
        assertEquals(productsCount, productListPage.getProducts().size());

    }

    @Test
    public void testVegetableAdd() {
        String productName = "Батат";
        String productType = "Овощ";
        boolean exotic = true;

        CommonSteps commonSteps = new CommonSteps(driver);
        commonSteps.getMainPage().selectSandboxItem("Товары");

        ProductListPage productListPage = commonSteps.getProductListPage();
        assertEquals("http://localhost:8080/food", driver.getCurrentUrl(), "Текущий url не совпадает с url страницы списка товаров");

        int productsCount = productListPage.getProducts().size();

        productListPage.buttonAddClick();
        productListPage.fillProductNameField(productName);
        productListPage.selectProductType(productType);

        assertFalse(productListPage.getExoticCheckbox().isSelected(), "Неверное состояние чекбокса");
        productListPage.selectExotic(exotic);

        productListPage.buttonSaveClick();

        assertTrue(productListPage.assertNewElement(productName, productType, exotic), "Последний продукт не совпадает с переданными данными");

        productListPage.selectSandboxItem("Сброс данных");
        // Проверяем что количество продуктов до добавления равно количеству после сброса данных
        assertEquals(productsCount, productListPage.getProducts().size());

    }
}
