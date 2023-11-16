package Pages.ElementsSeparateFromMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Elements {

    @FindBy(id = "cookies-read-more-link")
    private WebElement cookiesAccept;
    public WebElement getcookiesAccept() {
        return cookiesAccept;
    }

}
