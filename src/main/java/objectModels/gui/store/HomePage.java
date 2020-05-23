package objectModels.gui.store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class HomePage{
    private WebDriver driver;
    private By loginBtn = By.className("login");

    public HomePage(WebDriver driver) {
	this.driver = driver;
    }

    public void clickLoginButton() {
	ElementActions.click(driver, loginBtn);
    }

}
