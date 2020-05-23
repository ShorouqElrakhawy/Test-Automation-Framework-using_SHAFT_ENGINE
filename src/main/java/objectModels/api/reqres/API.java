package objectModels.api.reqres;

import com.shaft.api.RestActions;
import com.shaft.tools.io.ExcelFileManager;

public class API {
    protected ExcelFileManager testDataReader;
    protected RestActions restObject;
 // status codes
    protected static int successStatusCode = 200;
    protected final static int BFF_successStatusCode = 201;
 // service names
    
   
//    private String registerServiceName="api/register";
//    private String loginServiceName="api/login";
    public API(RestActions restObject, int successStatusCode) {
	try {
	    this.testDataReader = new ExcelFileManager(System.getProperty("testDataFilePath"));
	} catch (java.lang.NullPointerException e) {

	}
	this.restObject = restObject;
	API.successStatusCode = successStatusCode;
    }
    
}
