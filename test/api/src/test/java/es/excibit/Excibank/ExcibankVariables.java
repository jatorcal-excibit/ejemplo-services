package es.excibit.Excibank;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import es.excibit.ApiVariables;
import es.excibit.EnvironmentData;
import es.excibit.happi.utils.JsonUtils;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;
import net.minidev.json.JSONObject;
import org.apache.commons.codec.binary.Hex;

public class ExcibankVariables extends ApiVariables {

  /** The Constant EXAMPLE_REQUEST_MAPPING. */
  public static final String EXAMPLE_REQUEST_MAPPING = "/example";

  /**
   * Gets the url for rest service.
   *
   * @param requestMapping the request mapping
   * @return the url
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String getUrl(String requestMapping) throws IOException {	
    String host = EnvironmentData.getInstance().getValue("Excibank.services.service.uri");
    return getUrl(host, requestMapping);
  }


  /**
   * Gets mockserver true/false.
   *
   * @return mockserver value
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static boolean getMockserver() throws IOException {
    return Boolean.valueOf(getValue("Excibank.mockserver"));
  }


  /**
   * Gets database.
   *
   * @return database value
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String getDatabase() throws IOException {
    return getValue("Excibank.database");
  }


  /**
   * Gets the database user.
   *
   * @return the database user
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String getDatabaseUser() throws IOException {
    return getValue("Excibank.database.user");
  }


  /**
   * Gets the database password.
   *
   * @return the database password
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String getDatabasePassword() throws IOException {
    return getValue("Excibank.database.password");
  }


}
