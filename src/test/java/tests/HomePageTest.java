package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BestItemsPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class HomePageTest {

    private WebDriver driver; // komunikacja z przeglądarką
    private WebElement element;// obsługa elementów na stronie

    @BeforeAll // metoda odpalająca się przed wszystkimi testami
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach //przed każdym testem
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    // uruchomi się po każdym teście, zamykamy przeglądarkę i za każdym razem będziemy odpalali nową przeglądarkę
    // każdy test będzie uruchamiany na świeżon odpalonej przeglądarce
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test // uruchamianie testu
    public void shouldReturnCorrectPageTitle() {
        driver.get("http://automationpractice.pl/index.php"); // driver.get otwiera wskazaną stronę

        //System.out.println(driver.getTitle());

        assertThat(driver.getTitle()).isEqualTo("My Store");
        //Assertions org.assertj.core.api
        //assertThat() - podajemy obiekt lub wartość, którą chcemy sprawawdzić
        //driver.getTitle() - wejście na stonę i pobranie tytułu
        //isEqualTo(...) - porówaniee tytułu z tekstem w nawiasach

    }

    @Test
    public void shouldSeeBestsellerItemsOnHomePage() {

//        PAGE OBJECT PATTERN
//        BestItemsPage bestItemsPage;
//        bestItemsPage = new BestItemsPage(driver);

        driver.get("http://automationpractice.pl/index.php");

        List<WebElement> productNamesByCssSelector = driver.findElements(By.cssSelector("#blockbestsellers .product-name"));//ALT+ENTER//findElements - zwróci listę elementów, by. - jak chcemy zlokalizować ten element (F12 Chrome (css locator)-> $('#blockbestsellers .product-name'))

        List<WebElement> productNamesByXpath = driver.findElements(By.xpath("//*[@id='blockbestsellers']//*[@class='product-name']"));
        //lub przez xpatha  -> $x('//*[@id="blockbestsellers"]//*[@class="product-name"]') //ALT+ENTER//

        System.out.println(productNamesByCssSelector.size());
        for (WebElement productName : productNamesByCssSelector) {
            System.out.println(productName.getText());

        }

        for (WebElement productName : productNamesByXpath) {
            System.out.println(productName.getText());

        }

//            boolean anyProductHasEmptyName = productNamesByCssSelector.stream()
//                    .anyMatch(el -> el.getText().isEmpty());//czy którykolwiek z elementów jest pusty
//
//            Assertions.assertThat(anyProductHasEmptyName).isTrue(); //lub isFalse

        List<WebElement> productWithEmptyNames = productNamesByCssSelector.stream()
                .filter(el -> el.getText().isEmpty()) //filtruje i wybiera tylko puste
                .collect(Collectors.toList()); // lista pustych

        assertThat(productWithEmptyNames).isNotEmpty(); //assertions.assertThat.... - ALT + ENTER (statyczny import) // mamy produkty które mają pustą nazwę
        //założenie, czy na liście pustych nie ma jakiś elementów /isEmpty - czy lista pustych jest pusta


    }

}