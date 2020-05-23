package objectModels.api.reqres;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shaft.api.RestActions;

public class API_Users extends API {
    private RestActions restObject;
    private String usersServiceName = "api/users";

    public API_Users(RestActions restObject) {
	super(restObject, successStatusCode);
	this.restObject = restObject;
    }

    public Response getUsers(String urlArguments) {
	return restObject.performRequest(RestActions.RequestType.GET, successStatusCode, usersServiceName,
		ContentType.ANY, urlArguments);
    }

    public Response getSingleUser(String userID) {
	return restObject.performRequest(RestActions.RequestType.GET, successStatusCode,
		usersServiceName + "/" + userID, ContentType.ANY);
    }

    public String getUserId(String userEmail, String urlArguments) {
	String userID = "";
	List<Object> users = RestActions.getResponseJSONValueAsList(getUsers(urlArguments), "data");
	for (int i = 0; i < users.size(); i++) {
	    if (userEmail.equals(RestActions.getResponseJSONValue(users.get(i), "email"))) {
		userID = RestActions.getResponseJSONValue(users.get(i), "id");
	    }
	}
	return userID;
    }

    public Response createUser(Object body) {
	JsonObject jsonObject = new JsonObject();
	JsonParser parser = new JsonParser();
	JsonElement jsonElement = parser.parse(body.toString());
	jsonObject = jsonElement.getAsJsonObject();
	return restObject.performRequest(RestActions.RequestType.POST, BFF_successStatusCode, usersServiceName,
		jsonObject, ContentType.JSON);
    }

}
