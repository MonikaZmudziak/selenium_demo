package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMenuPage extends BasePage {

    public TopMenuPage(WebDriver driver) {
        super(driver);
}


    @FindBy(linkText = "Contact us")
    WebElement contactUsLink; // 1 element

    // metoda pozwalająca kliknięcie w link
    public void clickOnContactUsLink(){
        contactUsLink.click();

    }

}
