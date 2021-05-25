package es.excibit;

import java.io.IOException;

public class ApiVariables {
  
  /** The Constant POPULATE_ID. */
  public static final String POPULATE_ID = "[ID]";
  
  /** The Constant POPULATE_CHANGE_SET_ID. */
  public static final String POPULATE_CHANGE_SET_ID = "[API_ID]";

  /** The Constant MOCK_STATUS. */
  public static final String MOCK_STATUS = "[STATUS]";
  
  /** The Constant POPULATE_USER_ID. */
  public static final String POPULATE_USER_ID = "[USER_ID]";    

  /** The Constant FOLDER_FEATURE. */
  public static final String FOLDER_FEATURE = "[FOLDER_FEATURE]";
  
  /** The Constant ROLLBACK_DATA. */
  public static final String ROLLBACK_DATA = "liquibase/test/[FOLDER_FEATURE]/rollback/rollbackData.sql";
  
  /**
   * Gets the url for mock server rest service.
   *
   * @param requestMapping the request mapping
   * @return the url
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String getMockServerServicesUrl(String requestMapping) throws IOException {
    return new StringBuilder().append(getValue("mockserver.services.service.uri")).append(requestMapping).toString();
  }
  
    /**
   * Gets the url for rest service.
   *   
   * @param hostValue the host value
   * @param requestMapping the request mapping
   
   * @return the url
   
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String getUrl(String hostValue, String requestMapping) throws IOException {
    return new StringBuilder().append(getValue(hostValue)).append(requestMapping).toString();
  }
  
  /**
   * Gets the value.
   *
   * @param key the key
   * @return the value
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String getValue(String key) throws IOException {
    return EnvironmentData.getInstance().getValue(key);
  }
}
