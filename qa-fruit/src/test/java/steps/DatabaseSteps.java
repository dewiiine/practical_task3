package steps;

import driverManager.DriverManager;
import io.cucumber.java.ru.И;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.ProductListPage;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class DatabaseSteps {

    private final String url = "jdbc:h2:tcp://localhost:9092/mem:testdb";
    private final String user = "user";
    private final String password = "pass";
    private Connection connection;
    private ProductListPage productListPage;
    private WebDriver driver = DriverManager.getDriver();
    private int productsCount;
    String foodName = "Мандарин";
    String foodType = "Фрукт";
    boolean exotic = false;

    public DatabaseSteps() {
        // В конструкторе подключаемся к базе данных
        try {
            this.connection = java.sql.DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @И("Переход на страницу Товары")
    public void goProductListPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectSandboxItem("Товары");
    }

    @И("Проверка URL страницы Товары")
    public void checkUrl() {
        productListPage = new ProductListPage(driver);
        assertEquals("http://localhost:8080/food", driver.getCurrentUrl(), "Текущий url не совпадает с url страницы списка товаров");
    }

    @И("Получение кол-ва продуктов на странице")
            public void getCountProduct() {
        productsCount = productListPage.getProducts().size();
    }

    @И("Добавление нового товара")
    public void addProduct() {
        productListPage.buttonAddClick();
        productListPage.fillProductNameField(foodName);
        productListPage.selectProductType(foodType);
    }


    @И("Проверка состояния чекбокса")
    public void checkCheckbox() {
        assertFalse(productListPage.getExoticCheckbox().isSelected(), "Неверное состояние чекбокса");
        productListPage.selectExotic(exotic);
    }


    @И("Сохранение нового товара")
    public void saveProduct() {
        productListPage.buttonSaveClick();
    }


    @И("Проверка, что товар добавлен верно")
    public void checkSaveProduct() {
        assertTrue(productListPage.assertNewElement(foodName, foodType, exotic), "Последний продукт не совпадает с переданными данными");
    }


     @И("Сброс данных")
     public void reset() {
         productListPage.selectSandboxItem("Сброс данных");
     }


    @И("Проверяем что количество продуктов до добавления равно количеству после сброса данных")
    public void checkCountProduct() {
        assertEquals(productsCount, productListPage.getProducts().size());
    }


    @И("Получить количество строк по {string}")
    public int getFoodCount(String foodName) {
        String sql = "SELECT COUNT(*) FROM FOOD WHERE FOOD_NAME = ?";
        int count = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, foodName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @И("Удаление последнего добавленного товара {string}")
    public boolean deleteLastAddedFood(String foodName) {
        String sql = "DELETE FROM FOOD WHERE FOOD_ID = (SELECT MAX(FOOD_ID) FROM FOOD WHERE FOOD_NAME = ?)";
        boolean isDeleted = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, foodName);
            int rowsAffected = preparedStatement.executeUpdate();
            isDeleted = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    @И("Проверить, что количество строк равно ожидаемому кол-ву {string} {int}")
    public boolean assertRowsCountByFoodName(String foodName, int expectedCount) {
        return expectedCount == getFoodCount(foodName);
    }

    @И("Закрытие соединения с БД")
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
