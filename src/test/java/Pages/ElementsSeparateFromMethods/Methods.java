package Pages.ElementsSeparateFromMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Methods {

    private WebDriver driver;
    private Elements elements;

    //INIT ELEMENTS
    public Methods(WebDriver driver) {
        this.driver = driver;
        this.elements = new Elements();
        PageFactory.initElements(driver,elements);
    }

    public void acceptElementCookie()
    {
        elements.getcookiesAccept().click();
    }
}
