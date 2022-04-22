package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.PageTitleUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    private static final String BASE_URL = "http://automationpractice.pl/index.php";
    //Stringów nie trzymamy w kodzie jako Stringów tylko jako stałe


    protected WebDriver driver; // komunikacja z przeglądarką// ustawić PROTECTED aby inne klasy mogły dziedziczyć


    @BeforeAll // metoda odpalająca się przed wszystkimi testami
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach //przed każdym testem
    public void setupTest() {
        driver = new ChromeDriver();

        driver.get(BASE_URL);//adres URL jest w stałej
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE); // klasa utils w main java


    }

    @AfterEach
    // uruchomi się po każdym teście, zamykamy przeglądarkę i za każdym razem będziemy odpalali nową przeglądarkę
    // każdy test będzie uruchamiany na świeżon odpalonej przeglądarce
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
