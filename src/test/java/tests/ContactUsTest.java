package tests;


import enums.MessageSubject;
import model.Message;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ContactUsFormPage;
import pages.TopMenuPage;
import utils.PageTitleUtils;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // aby wykonywać testy w określonej kolejności, widoczne np w raporcie Allure

public class ContactUsTest extends BaseTest {

    //utworzenie Page object
    //konstruktor
    private TopMenuPage topMenuPage;
    private ContactUsFormPage contactUsFormPage;

    @BeforeEach //przed każdym testem
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://automationpractice.pl/index.php");
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        //inicjalizacja konstruktorów
        topMenuPage = new TopMenuPage(driver);
        contactUsFormPage = new ContactUsFormPage(driver);

    }


    //Kliknąć w Contact Us button, wysłać pusty formularz, sprawdzić czy pojawia się ostrzeżenie

    @Test
    @Order(1) // test 1 w kolejce wykonywania
    public void shouldNotAllowToSentEmptyContactUsForm() throws InterruptedException {
        // driver.get("http://automationpractice.pl/index.php");
        //        assertThat(driver.getTitle()).isEqualTo("My Store");  - w klasie matce (BaseTest)



        //Globalny WAIT dla wszystkich elementów (find element)
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait, za każdym razem maksymalnie czekać 10s

        topMenuPage.clickOnContactUsLink();
        // wywołanie konstruktora zamiast dwóch linijek poniżej
//        WebElement contactUsLink = driver.findElement(By.linkText("Contact us"));//F12, ALT+ENTER *znalezienie elementu na stronie
//        contactUsLink.click(); // kliknięcie w link Contact Us


        contactUsFormPage.clickOnSendButton();
        // wywołanie konstruktora zamiast dwóch linijek poniżej
//        WebElement sendButton = driver.findElement(By.id("submitMessage"));//F12 // znalezienie przycisku send
//        sendButton.click(); // kliknięcie w przycisk send


        // Czekanie na elementy
        //1. Zatrzymanie wątku  (zawsze w tym miejscu będziemy czekali X sekund)- unikać uzywania tej opcji

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //2.Implicit wait - ustawiamy globalnie dla całego drivera

//       ZAMIAST TYCH 4 LINIJEK PONIŻEJ Używamy Page object pattern
//        WebElement redAlertBox = driver.findElement(By.className("alert-danger"));
//        //3. Explicit wait - wskazanie na jaki element chcemy poczekać
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOf(redAlertBox));
//        assertThat(redAlertBox.isDisplayed()).isTrue();     // asercja czy red alert box jest widoczny, czy to prawda jest że jest widoczny
        assertThat(contactUsFormPage.isRedAlertBoxDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    public void shouldNotAllowToSendContactUsFormWithEmailOnly() {

        // dwie linijki poniżej zamiast wywołania konstruktora  topMenuPage.clickOnContactUsLink();
        WebElement contactUsLink = driver.findElement(By.linkText("Contact us"));
        contactUsLink.click();

        //Akcja wpisania maila do formularza

        // lub page object zamiast dwóch poniższych linii -> contactUsFormPage.enterEmail("test@test.com");
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("test@test.com"); // sendKeys - wpisanie tekstu do inputa

        // zamiast tych dwóch linijek poniżej można wywołać konstuktor z Page Object Pattern    contactUsFormPage.clickOnSendButton();
        WebElement sendButton = driver.findElement(By.id("submitMessage"));
        sendButton.click();


        WebElement redAlertBox = driver.findElement(By.className("alert-danger"));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(redAlertBox));

        assertThat(redAlertBox.isDisplayed()).isTrue();

    }

    @Test
    @Order(3)
    public void shouldSendContactUsFormWithValidDate(){
        topMenuPage.clickOnContactUsLink();

        Message message = new Message();
        message.setSubject(MessageSubject.WEBMASTER);
        message.setEmail("test@test.com");
        message.setOrderReference("123");
        message.setMessage("jakaś wiadomość");

        contactUsFormPage.sendContactUsForm(message);

        assertThat(contactUsFormPage.isRedGreenAlertBoxDisplayed()).isTrue();




     }




}
