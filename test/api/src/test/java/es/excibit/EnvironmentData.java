package es.excibit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class EnvironmentData.
 */
public class EnvironmentData {

  /**
   * The instance.
   */
  private static volatile EnvironmentData instance = null;

  /**
   * The properties.
   */
  private Properties properties;

  /**
   * The Constant ENVIRONMENT_PROPERTY.
   */
  private static final String ENVIRONMENT_PROPERTY = "environment.property";

  /**
   * Instantiates a new environment data.
   */
  private EnvironmentData() {

  }

  /**
   * Gets the single instance of EnvironmentData.
   *
   * @return single instance of EnvironmentData
   */
  public static EnvironmentData getInstance() {
    // Lazy and thread-safe
    if (instance == null) {
      synchronized (EnvironmentData.class) {
        if (instance == null) {
          instance = new EnvironmentData();
        }
      }
    }

    return instance;
  }

  /**
   * Gets the value.
   *
   * @param key the key
   * @return the value
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public String getValue(String key) throws IOException {

    if (properties == null) {
      String path = System.getProperty(ENVIRONMENT_PROPERTY);

      InputStream inputStream = new FileInputStream(path);
      properties = new Properties();
      properties.load(inputStream);
    }

    return (String) properties.get(key);

  }

}
