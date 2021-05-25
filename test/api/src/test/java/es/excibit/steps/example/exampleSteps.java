package es.excibit.steps.example;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;
import es.excibit.Excibank.ExcibankVariables;
import es.excibit.happi.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import io.restassured.response.Response;

/**
 * The Class exampleSteps.
 */
@RunWith(SerenityRunner.class)
public class exampleSteps {

	/** The rest response. */
	private Response restResponse;

	/** The example preconditions. */
	private examplePreconditions examplePreconditions = new examplePreconditions();

	/** example headers. */
	private Map<String, String> apiHeaders;


	/**
	 * Sets the data.
	 *
	 * @param userId          userId
	 * @param sql             sql
	 * @throws Throwable the throwable
	 */
	public void setData(String userId, String sql) throws Throwable {

	    // Creates headers
		apiHeaders = new HashMap<String, String>();
		apiHeaders.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		apiHeaders.put("Accept", MediaType.APPLICATION_JSON_VALUE);

	      
		// Init Database
		examplePreconditions.initDatabase(sql, userId);

	}

	/**
	 * Makes Get.
	 *
	 * @param requestMapping the request mapping
	 * @throws Throwable the throwable
	 */
	public void doCallGet(String requestMapping) throws Throwable {
	  
		String url = ExcibankVariables.getUrl(requestMapping);
		
		restResponse = rest().relaxedHTTPSValidation().headers(apiHeaders).get(url);

	}

	/**
	 * Manage response.
	 *
	 * @param jsonPayloadResponseFile the json payload response file
	 * @param httpstatus              the httpstatus
	 * @throws Exception
	 */
	public void manageResponse(String jsonPayloadResponseFile, int httpstatus) throws Exception {

		// Check first if statusCode is the expected one
		restResponse.then().statusCode(httpstatus);
		
	}


	/**
	 * Rollback.
	 *
	 * @throws Exception the exception
	 */
	public void rollback() throws Exception {
		examplePreconditions.rollback();
	}

}
