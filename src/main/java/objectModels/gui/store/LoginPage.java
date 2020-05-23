package objectModels.gui.store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.shaft.gui.element.ElementActions;

public class LoginPage{
    private WebDriver driver;
    private By createAccountBtn = By.id("SubmitCreate");
    private By emailCreateField = By.id("email_create");
    private By emailErrorMessage = By.xpath("//*[@id='create_account_error']/descendant::ol/li");

    public LoginPage(WebDriver driver) {
	this.driver = driver;
    }

    public void enterEmailAndClickCreateAccountBtn(String email) {
	ElementActions.type(driver, emailCreateField, email);
	ElementActions.click(driver, createAccountBtn);
    }

    public String getErrorMessage() {
	return ElementActions.getText(driver, emailErrorMessage);
    }

}
