package pages;

import model.Message;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsFormPage {

    private WebDriverWait wait;

    public ContactUsFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this); // kilka elementów
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // lub super(driver);
    }

    // Page objects - znajdoawnie elementów za pomocą adnotacji @Findby i później są tworzone metody,
    //które obsługują te elementy. Następnie w testach używamy tych page objects i metod, ktore zostały utworzone
    @FindBy(id = "submitMessage")
    WebElement sendButton; // 1 element

    //metoda klikajaca w ten przycisk

    public void clickOnSendButton() {
        sendButton.click();
    }

    @FindBy(className = "alert-danger")
    WebElement redAlertBox;


    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "id_contact")
    WebElement subjectSelect;

    @FindBy(id = "id_order")
    WebElement orderReferenceInput;

    @FindBy(id = "message")
    WebElement messageTextarea;

    @FindBy(className = "alert-success")
    WebElement greenAlertBox;


    // metoda zwracająca boolean, czy alert jest wyświetlony czy nie

    public boolean isRedAlertBoxDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(redAlertBox));
        boolean isDisplayed = false;

        try {
            isDisplayed = redAlertBox.isDisplayed(); // uwaga - metoda isDisplayed zwroci prawdę jeżeli element jest na stronie, natomiast gdy
            //elementu nie będzie na stronie, metoda nie zwróci fałszu tylko metoda rzuci wyjątek - No such element exception
        } catch (NoSuchElementException a) {

        }
        return isDisplayed;

    }

    public boolean isRedGreenAlertBoxDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(greenAlertBox));
        boolean isDisplayed = false;

        try {
            isDisplayed = greenAlertBox.isDisplayed(); // uwaga - metoda isDisplayed zwroci prawdę jeżeli element jest na stronie, natomiast gdy
            //elementu nie będzie na stronie, metoda nie zwróci fałszu tylko metoda rzuci wyjątek - No such element exception
        } catch (NoSuchElementException a) {

        }
        return isDisplayed;

    }


    public void enterEmail() {
        emailInput.sendKeys("test@test.com");
    }


    public void sendContactUsForm(Message message) {
        Select subject = new Select(subjectSelect);
        subject.selectByVisibleText(message.getSubject().getValue());

        emailInput.sendKeys(message.getEmail());
        orderReferenceInput.sendKeys(message.getOrderReference());
        messageTextarea.sendKeys(message.getMessage());
        sendButton.click();


    }

}
