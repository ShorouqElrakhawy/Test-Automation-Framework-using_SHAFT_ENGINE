package tests.api.reqres;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.cli.FileActions;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.validation.Assertions;
import com.shaft.validation.Assertions.AssertionComparisonType;
import com.shaft.validation.Assertions.AssertionType;
import com.shaft.validation.Verifications;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import objectModels.api.reqres.API_Users;


/**
 * 
 * @author Shorouq Elrakhawy
 *
 */

@Epic("API -> Users")
public class UsersTest {
    private RestActions apiObject;
    private API_Users usersObject;
    private ExcelFileManager testDataReader;

    @Test(description = "TC4 - Verify user's creation date matches current date according to GMT+0 timezone")
    @Description("When I create new user, Then user's creation date matches current date according to GMT+0 timezone")
    @Severity(SeverityLevel.NORMAL)
    public void verifyUserCreationDate_isCorrect() throws ParseException {
	Response response = usersObject.createUser(
		FileActions.readFromFile(System.getProperty("jsonFolderPath") + "Users/", "TC4_createUser.json"));
	String createDate = RestActions.getResponseJSONValue(response, "createdAt");
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	format.setTimeZone(TimeZone.getTimeZone("Etc/GMT+0"));
	Date d = new Date();
	String formattedTime = format.format(d);
	System.out.println(formattedTime);
	Assertions.assertEquals(formattedTime, createDate, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test(description = "TC5 - Verify user's data is correct")
    @Description("When I click a single user, Then user's data is correct")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyDataOfSingleUser_isCorrect() {
	Response response = usersObject.getSingleUser(
		usersObject.getUserId(testDataReader.getCellData("TC5_email"), testDataReader.getCellData("urlArguments")));
	System.out.println(response.asString());
	Verifications.verifyEquals(testDataReader.getCellData("TC5_expectedFirstName"),
		RestActions.getResponseJSONValue(response, "data.first_name"));
	Verifications.verifyEquals(testDataReader.getCellData("TC5_expectedLastName"),
		RestActions.getResponseJSONValue(response, "data.last_name"));
    }

    @BeforeClass
    public void beforeClass() {
	testDataReader = new ExcelFileManager(System.getProperty("testDataFolderPath") + "Reqres/UsersTest.xlsx");
	apiObject = new RestActions("https://reqres.in/");
	usersObject = new API_Users(apiObject);
    }
}
