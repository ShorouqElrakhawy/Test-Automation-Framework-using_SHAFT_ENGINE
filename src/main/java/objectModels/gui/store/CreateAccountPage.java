package objectModels.gui.store;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.shaft.gui.element.ElementActions;

public class CreateAccountPage {
    private WebDriver driver;
    
    private By mrTitle = By.id("id_gender1");
    private By mrsTitle = By.id("id_gender2");
    private By firstName= By.id("customer_firstname");
    private By lastName= By.id("customer_lastname");
    private By password= By.id("passwd");
    private By days= By.id("days");
    private By months= By.id("months");
    private By years= By.id("years");
    private By firstName1= By.id("firstname");
    private By lastName1= By.id("lastname");
    private By address= By.id("address1");
    private By city= By.id("city");
    private By state= By.id("id_state");
    private By postcode= By.id("postcode");
    private By mobile= By.id("phone_mobile");
    private By alias= By.id("alias");
    private By registerBtn= By.id("submitAccount");
    private By successfulMsg= By.id("info");

    public CreateAccountPage(WebDriver driver) {
	this.driver = driver;
    }
    private void selectTitle(String title) {
	switch (title) {
	case "Mr":
	    ElementActions.click(driver, mrTitle);
	    break;

	case "Mrs":
	    ElementActions.click(driver,mrsTitle);
	    break;
	}
    }

    public String getSuccessMsg() {
	return ElementActions.getText(driver,successfulMsg);
    }
    
    public void register(ArrayList<String> list) {
	selectTitle(list.get(0));
	ElementActions.type(driver,firstName, list.get(1));
	ElementActions.type(driver,lastName, list.get(2));
	ElementActions.type(driver,password, list.get(3));
	ElementActions.select(driver,days, list.get(4));
	ElementActions.select(driver,months, list.get(5));
	ElementActions.select(driver,years, list.get(6));
	ElementActions.type(driver,firstName1, list.get(7));
	ElementActions.type(driver,lastName1, list.get(8));
	ElementActions.type(driver,address, list.get(9));
	ElementActions.type(driver,city, list.get(10));
	ElementActions.select(driver,state, list.get(11));
	ElementActions.type(driver,postcode, list.get(12));
	ElementActions.type(driver,mobile, list.get(13));
	ElementActions.type(driver,alias, list.get(14));
	ElementActions.click(driver,registerBtn);
    }

}
