import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProductListPage;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest extends BaseTest {

    @Test
    @DisplayName("Добавление товара типа 'Фрукт' (без БД)")
    public void testFruitAdd() {
        // Заполнение данных о новом товаре
        String foodName = "Мандарин";
        String foodType = "Фрукт";
        boolean exotic = true;

        // Переход на страницу "Товары"
        CommonSteps commonSteps = new CommonSteps(driver);
        commonSteps.getMainPage().selectSandboxItem("Товары");

        // Проверка URL страницы "Товары"
        ProductListPage productListPage = commonSteps.getProductListPage();
        assertEquals("http://localhost:8080/food", driver.getCurrentUrl(), "Текущий url не совпадает с url страницы списка товаров");

        // Получение кол-ва продуктов на странице
        int productsCount = productListPage.getProducts().size();

        // Добавление нового товара
        productListPage.buttonAddClick();
        productListPage.fillProductNameField(foodName);
        productListPage.selectProductType(foodType);

        // Проверка состояния чекбокса
        assertFalse(productListPage.getExoticCheckbox().isSelected(), "Неверное состояние чекбокса");
        productListPage.selectExotic(exotic);

        // Сохранение нового товара
        productListPage.buttonSaveClick();

        // Проверка, что товар добавлен верно
        assertTrue(productListPage.assertNewElement(foodName, foodType, exotic), "Последний продукт не совпадает с переданными данными");

        // Сброс данных
        productListPage.selectSandboxItem("Сброс данных");

        // Проверяем что количество продуктов до добавления равно количеству после сброса данных
        assertEquals(productsCount, productListPage.getProducts().size());

    }

    @Test
    @DisplayName("Добавление товара типа 'Овощ' (без БД)")
    public void testVegetableAdd() {
        // Заполнение данных о новом товаре
        String foodName = "Батат";
        String foodType = "Овощ";
        boolean exotic = true;

        // Переход на страницу "Товары"
        CommonSteps commonSteps = new CommonSteps(driver);
        commonSteps.getMainPage().selectSandboxItem("Товары");

        // Проверка URL страницы "Товары"
        ProductListPage productListPage = commonSteps.getProductListPage();
        assertEquals("http://localhost:8080/food", driver.getCurrentUrl(), "Текущий url не совпадает с url страницы списка товаров");

        // Получение кол-ва продуктов на странице
        int productsCount = productListPage.getProducts().size();

        // Добавление нового товара
        productListPage.buttonAddClick();
        productListPage.fillProductNameField(foodName);
        productListPage.selectProductType(foodType);

        // Проверка состояния чекбокса
        assertFalse(productListPage.getExoticCheckbox().isSelected(), "Неверное состояние чекбокса");
        productListPage.selectExotic(exotic);

        // Сохранение нового товара
        productListPage.buttonSaveClick();

        // Проверка, что товар добавлен верно
        assertTrue(productListPage.assertNewElement(foodName, foodType, exotic), "Последний продукт не совпадает с переданными данными");

        // Сброс данных
        productListPage.selectSandboxItem("Сброс данных");

        // Проверяем что количество продуктов до добавления равно количеству после сброса данных
        assertEquals(productsCount, productListPage.getProducts().size());

    }

    @Test
    @DisplayName("Добавление товара типа 'Овощ' (с БД)")
    public void testVegetableAddDatabase() {
        // Данные о новом товаре
        String foodName = "Редиска";
        String foodType = "Овощ";
        boolean exotic = false;

        // Проверка существования и кол-ва товаров с наименованием "Редиска"
        assertTrue(databaseSteps.assertRowsCountByFoodName(foodName, 0));

        // Переход на страницу "Товары"
        CommonSteps commonSteps = new CommonSteps(driver);
        commonSteps.getMainPage().selectSandboxItem("Товары");

        // Проверка URL страницы "Товары"
        ProductListPage productListPage = commonSteps.getProductListPage();
        assertEquals("http://localhost:8080/food", driver.getCurrentUrl(), "Текущий url не совпадает с url страницы списка товаров");

        // Получение кол-ва продуктов на странице
        int productsCount = productListPage.getProducts().size();

        // Добавление нового товара
        productListPage.buttonAddClick();
        productListPage.fillProductNameField(foodName);
        productListPage.selectProductType(foodType);
        // Проверка состояния чекбокса
        assertFalse(productListPage.getExoticCheckbox().isSelected(), "Неверное состояние чекбокса");
        productListPage.selectExotic(exotic);
        // Сохранение нового товара
        productListPage.buttonSaveClick();

        // Проверка, что товар добавлен верно
        assertTrue(productListPage.assertNewElement(foodName, foodType, exotic), "Последний продукт не совпадает с переданными данными");

        // Повторная проверка кол-во товаров с наименованием "Редиска"
        assertTrue(databaseSteps.assertRowsCountByFoodName(foodName, 1));

        // Удаление последнего добавленного товара
        databaseSteps.deleteLastAddedFood("Редиска");

        // Проверка существования и кол-ва товаров с наименованием "Редиска"
        assertTrue(databaseSteps.assertRowsCountByFoodName(foodName, 0));

        // Сброс данных
        productListPage.selectSandboxItem("Сброс данных");

        // Проверяем, что количество продуктов до добавления равно количеству после сброса данных
        assertEquals(productsCount, productListPage.getProducts().size());
    }

    @Test
    @DisplayName("Добавление товара типа 'Фрукт', уже существующего в БД")
    public void testFruitAddDatabase() {
        // Данные о новом товаре
        String productName = "Апельсин";
        String productType = "Фрукт";
        boolean exotic = true;

        // Проверка существования и кол-ва товаров с наименованием "Апельсин"
        assertTrue(databaseSteps.assertRowsCountByFoodName(productName, 1));

        // Переход на страницу "Товары"
        CommonSteps commonSteps = new CommonSteps(driver);
        commonSteps.getMainPage().selectSandboxItem("Товары");

        // Проверка URL страницы "Товары"
        ProductListPage productListPage = commonSteps.getProductListPage();
        assertEquals("http://localhost:8080/food", driver.getCurrentUrl(), "Текущий url не совпадает с url страницы списка товаров");

        // Получение кол-ва продуктов на странице
        int productsCount = productListPage.getProducts().size();

        // Добавление нового товара
        productListPage.buttonAddClick();
        productListPage.fillProductNameField(productName);
        productListPage.selectProductType(productType);
        // Проверка состояния чекбокса
        assertFalse(productListPage.getExoticCheckbox().isSelected(), "Неверное состояние чекбокса");
        productListPage.selectExotic(exotic);
        // Сохранение нового товара
        productListPage.buttonSaveClick();

        // Проверка, что товар добавлен верно
        assertTrue(productListPage.assertNewElement(productName, productType, exotic), "Последний продукт не совпадает с переданными данными");

        // Повторная проверка кол-во товаров с наименованием "Апельсин"
        assertTrue(databaseSteps.assertRowsCountByFoodName(productName, 2));

        // Удаление последнего добавленного товара
        assertTrue(databaseSteps.deleteLastAddedFood(productName), "Удаление не произошло");

        // Проверка существования и кол-ва товаров с наименованием "Апельсин"
        assertTrue(databaseSteps.assertRowsCountByFoodName(productName, 1));

        // Сброс данных
        productListPage.selectSandboxItem("Сброс данных");

        // Проверяем, что количество продуктов до добавления равно количеству после сброса данных
        assertEquals(productsCount, productListPage.getProducts().size());
    }
}
