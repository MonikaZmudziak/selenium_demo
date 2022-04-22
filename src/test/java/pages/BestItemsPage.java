package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class BestItemsPage extends BasePage {

    public BestItemsPage(WebDriver driver){
       // przeniesione do BasePage- PageFactory.initElements(driver, this);
    super(driver);
    }





    @FindBy(css ="#blockbestsellers .product-name") // określenie web elementu
    List<WebElement> productNames; // podajemy czy to 1 element czy lista elementów

    public List<String> getProductNames() { // metoda wyciągająca z web elementów tekst, przerabia go i zwraca nowa listę z tekstami
        return productNames.stream()
                .map(el -> el.getText().trim()) //trim - wyczyści białe spacje z przodu i na końcu tego stringa
                .collect(Collectors.toList());
    }
}
