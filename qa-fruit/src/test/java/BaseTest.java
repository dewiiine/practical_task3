import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {
    protected WebDriver driver;
    protected DatabaseSteps databaseSteps;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ирина\\Documents\\JAVA projects\\practical_task3\\qa-fruit\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");

        databaseSteps = new DatabaseSteps();

    }

    @AfterEach
    public void tearDown() {
        databaseSteps.closeConnection();

        if (driver != null) {
            driver.quit();
        }
    }
}
