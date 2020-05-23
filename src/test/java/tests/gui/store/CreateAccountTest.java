package tests.gui.store;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.validation.Assertions;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import objectModels.gui.store.CreateAccountPage;
import objectModels.gui.store.HomePage;
import objectModels.gui.store.LoginPage;

/**
 * 
 * @author Shorouq Elrakhawy
 *
 */

@Epic("GUI Register -> CreateAccountTest")
public class CreateAccountTest {
    public WebDriver driver;
    private ExcelFileManager testDataReader;
    private HomePage homePage;
    private LoginPage loginPage;
    private CreateAccountPage regPage;
    Faker fakeData = new Faker();  
    String titleInput, firstNameInput, lastNameInput, emailInput, passwordInput, monthInput, yearInput, firstName1Input,
	    lastName1Input, addressInput, cityInput, stateInput, aliasInput, dayInput, zipCode, mobileInput;

    @Test(description = "TC1 - Verify using invalid email format won't navigate to 'Create Account' page")
    @Description("When I enter invalid email formats, Then I can't navigate to 'Create Account' page, And error message is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void verifyRegisteration_usingInvalidEmailFormat_fails() {
	homePage.clickLoginButton();
	for (int i = 1; i < 5; i++) {
	    loginPage.enterEmailAndClickCreateAccountBtn(testDataReader.getCellData("TC1_invalidEmailFormat" + i));
	    Assertions.assertEquals(testDataReader.getCellData("TC1_expectedErrorMessage"),
		    loginPage.getErrorMessage());
	}
    }

    @Test(description = "TC2 - Verify using valid email navigates to 'Create Account' page")
    @Description("When I enter valid email, Then page navigates to 'Create Account' page")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyRegisteration_usingValidEmail_naviagtesToRegisterationPage() {
	homePage.clickLoginButton();
	loginPage.enterEmailAndClickCreateAccountBtn(fakeData.internet().emailAddress());
	WebDriverWait wait = new WebDriverWait(driver, 100);
	ExpectedCondition<Boolean> urlIsCorrect = arg0 -> BrowserActions.getCurrentURL(driver)
		.contains(testDataReader.getCellData("TC2_expectedLink"));
	Assertions.assertTrue(wait.until(urlIsCorrect));
    }

    @Test(description = "TC3 - Verify Registeration is successful when using valid data", dependsOnMethods = {
	    "verifyRegisteration_usingValidEmail_naviagtesToRegisterationPage" })
    @Description("When I enter valid data, Then I will register successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyRegisteration_isSuccessful() {
	regPage.register(list());
	// I will make this method fail
	Assertions.assertEquals(testDataReader.getCellData("TC3_expectedMessage"), regPage.getSuccessMsg());
    }

    private ArrayList<String> list() {
	initializeDymmyData();
	ArrayList<String> list = new ArrayList<String>();
	list.add(titleInput);
	list.add(firstNameInput);
	list.add(lastNameInput);
	list.add(passwordInput);
	list.add(dayInput);
	list.add(monthInput);
	list.add(yearInput);
	list.add(firstName1Input);
	list.add(lastName1Input);
	list.add(addressInput);
	list.add(cityInput);
	list.add(stateInput);
	list.add(zipCode);
	list.add(mobileInput);
	list.add(aliasInput);
	return list;

    }

    private void initializeDymmyData() {
	titleInput = "Mr";
	firstNameInput = fakeData.name().firstName();
	lastNameInput = fakeData.name().lastName();
	passwordInput = fakeData.internet().password(5, 10);
	dayInput = Integer.toString(fakeData.number().numberBetween(1, 31));
	monthInput = Integer.toString(fakeData.number().numberBetween(1, 12));
	yearInput = Integer.toString(fakeData.number().numberBetween(1900, 2020));
	firstName1Input = fakeData.name().firstName();
	lastName1Input = fakeData.name().lastName();
	addressInput = fakeData.address().streetAddress();
	cityInput = fakeData.address().city();
	stateInput = Integer.toString(fakeData.number().numberBetween(1, 50));
	zipCode = fakeData.number().digits(5);
	mobileInput = fakeData.phoneNumber().cellPhone();
	aliasInput = fakeData.internet().emailAddress();
    }

    @BeforeClass
    public void beforeClass() {
	testDataReader = new ExcelFileManager(
		System.getProperty("testDataFolderPath") + "Store/CreateAccountTest.xlsx");
	driver = BrowserFactory.getBrowser();
	BrowserActions.navigateToURL(driver, "http://automationpractice.com/");
	homePage = new HomePage(driver);
	loginPage = new LoginPage(driver);
	regPage = new CreateAccountPage(driver);
    }

}
