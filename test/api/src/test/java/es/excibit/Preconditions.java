package es.excibit;

import es.excibit.happi.database.DatabasePreconditionsUtils;
import es.excibit.database.ApiMapper;
import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ClassPathResource;
import io.cucumber.messages.internal.com.google.common.io.Resources;
import liquibase.exception.LiquibaseException;

/**
 * The Class Preconditions.
 */
@Slf4j
public class Preconditions {

  /**
   *Generate JWt for authentication endpoints.
   */
  JwtGenerator jwtGenerator = new JwtGenerator();
  
  /**
   * The newCardId.
   */
  private String newCardId;

  /**
   * The newUserId.
   */
  private String newUserId;
  
  /**
   * Execute change log.
   *
   * @param database the database
   * @param user the user
   * @param password the password
   * @param changelog the changelog
   * @param stage the stage
   * @throws SQLException the SQL exception
   * @throws LiquibaseException the liquibase exception
   * @throws Exception Signals that an I/O exception has occurred.
   */
  public void executeChangeLog(String database, String user, String password, String changelog, String stage)
      throws Exception {

    DatabasePreconditionsUtils databasePreconditionsUtils = new DatabasePreconditionsUtils(database, user, password);

    databasePreconditionsUtils.executeChangeLog(changelog, stage);

  }

  /**
   * Execute query.
   *
   * @param database the database
   * @param user the user
   * @param password the password
   * @param query the query
   * @return the string
   * @throws SQLException the SQL exception
   * @throws LiquibaseException the liquibase exception
   * @throws Exception Signals that an I/O exception has occurred.
   */
  @SuppressWarnings("rawtypes")
  public List executeQuery(String database, String user, String password, String query) throws Exception {

    DatabasePreconditionsUtils databasePreconditionsUtils = new DatabasePreconditionsUtils(database, user, password);

    return databasePreconditionsUtils.getValueQuery(query, new ApiMapper());

  }

  /**
   * Replaces on file.
   *
   * @param file the file
   * @param replace the replace
   * @param replacement the replacement
   * @throws Exception Signals that an I/O exception has occurred.
   */
  public void replacesOnFile(String file, String replace, String replacement) throws Exception {
    Charset charset = StandardCharsets.UTF_8;

    String populatePath = Resources.getResource(file).getPath();

    Path path = Paths.get(convertToWinPath(populatePath));

    String content = new String(Files.readAllBytes(path), charset);
    content = content.replace(replace, replacement);
    Files.write(path, content.getBytes(charset));

  }


  /**
   * Prepare mock files.
   *
   * @param file the file
   * @param mockStatus mockStatus
   * @throws Exception the exception
   */
  public String prepareMockFiles(String file)
      throws Exception {

    String ymlFileCopied = copyFileExtension(file, ".yml");

    return ymlFileCopied;

  }

  
  /**
   * copyFile.
   *
   * @param file name file.
   * @param extension extension file.
   * @return the string
   * @throws Exception Signals that an I/O exception has occurred.
   */
  public String copyFileExtension(String file, String extension) throws Exception {

    String filePath = new ClassPathResource(file).getFile().getAbsolutePath();
    String filePathToCopyPath = filePath.substring(0, filePath.length() - extension.length()) + newCardId + extension;

    File originalFile = new File(convertToWinPath(filePath));
    File filePathToCopy = new File(convertToWinPath(filePathToCopyPath));

    FileUtils.copyFile(originalFile, filePathToCopy);

    return file.substring(0, file.length() - extension.length()) + newCardId + extension;
  }


  /**
   * Gets the new card id.
   *
   * @return the new card id
   */
  public String getNewCardId() {
    return newCardId;
  }

  /**
   * Sets the new card id.
   *
   * @param newCardId the new new card id
   */
  public void setNewCardId(String newCardId) {
    this.newCardId = newCardId;
  }

  /**
   * Gets the new user id.
   *
   * @return the new user id
   */
  public String getNewUserId() {
    return newUserId;
  }

  /**
   * Sets the new user id.
   *
   * @param newUserId the new new user id
   */
  public void setNewUserId(String newUserId) {
    this.newUserId = newUserId;
  }


  /**
   * Execute query.
   *
   * @param database the database
   * @param user the user
   * @param password the password
   * @param query the query
   * @param objectClass object's class
   * @return the string
   * @throws SQLException the SQL exception
   * @throws LiquibaseException the liquibase exception
   * @throws Exception Signals that an I/O exception has occurred.
   */
  @SuppressWarnings("rawtypes")
  private Object executeQueryForObject(String database, String user, String password, String query, Class objectClass)
      throws Exception {

    DatabasePreconditionsUtils databasePreconditionsUtils = new DatabasePreconditionsUtils(database, user, password);

    return databasePreconditionsUtils.getValueQueryForObject(query, objectClass);

  }

  /**
   * Copy File.
   *
   * @param filePath the file path
   * @param filePathToCopyPath the filePathToCopyPath
   * @return the string
   * @throws Exception Signals that an I/O exception has occurred.
   */
  public void copyFile(String filePath, String filePathToCopyPath) throws Exception {

    File originalFile = new File(convertToWinPath(filePath));
    File filePathToCopy = new File(convertToWinPath(filePathToCopyPath));

    FileUtils.copyFile(originalFile, filePathToCopy);

  }

  /**
   * Convert to win file path if is needed.
   *
   * @param filePath file path to convert
   * @return converted file path.
   */
  private String convertToWinPath(String filePath) {
    if (System.getProperty("os.name").toLowerCase().contains("win")) {
      filePath = URLDecoder.decode(FilenameUtils.separatorsToWindows(filePath), StandardCharsets.UTF_8);
      filePath = filePath.startsWith(File.separator) ? filePath.substring(1) : filePath;
    }
    return filePath;
  }

}
