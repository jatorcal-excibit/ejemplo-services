package es.excibit.steps.example;

import es.excibit.Excibank.ExcibankVariables;
import es.excibit.Preconditions;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import liquibase.exception.LiquibaseException;


/**
 * The Class examplePreconditions.
 */
@Slf4j
public class examplePreconditions extends Preconditions {

  /**
   * The changeSetId.
   **/
  private String changeSetId;

  /**
   * The inserted data.
   */
  private boolean insertedData = false;

  /**
   * Inits the database.
   *
   * @param sql the sql
   * @param userId userId
   
   * @throws SQLException the SQL exception
   * @throws LiquibaseException the liquibase exception
   */
  public void initDatabase(String sql, String userId) throws Exception {

    // Set inits
    insertedData = false;

    // Get Database
    String database = ExcibankVariables.getDatabase();
    String user = ExcibankVariables.getDatabaseUser();
    String password = ExcibankVariables.getDatabasePassword();


    // Replaces changeset value
    changeSetId = UUID.randomUUID().toString();
    String sqlCopied = copyFileExtension(sql, ".sql");

    replacesOnFile(sqlCopied, ExcibankVariables.POPULATE_CHANGE_SET_ID, "" + changeSetId);
    replacesOnFile(sqlCopied, ExcibankVariables.POPULATE_USER_ID, userId);

    executeChangeLog(database, user, password, sqlCopied, "");

    insertedData = true;

  }


  /**
   * Rollback.
   */
  public void rollback() throws Exception {

    log.info("Executing rollback - insertedData Value {}", insertedData);

    if (insertedData) {

      // Get Database
      String database = ExcibankVariables.getDatabase();
      String user = ExcibankVariables.getDatabaseUser();
      String password = ExcibankVariables.getDatabasePassword();

      String sqlCopied = copyFileExtension(ExcibankVariables.ROLLBACK_DATA.replace(ExcibankVariables.FOLDER_FEATURE, "example"), ".sql");

      replacesOnFile(sqlCopied, ExcibankVariables.POPULATE_CHANGE_SET_ID, "" + changeSetId);

      executeChangeLog(database, user, password, sqlCopied, "");
    }
  }

  /**
   * Prepare json files.
   *
   * @param file the file
   * 
   * @throws Exception the exception
   */
  public String prepareJsonFiles(String file) throws Exception {

    String jsonFileCopied = copyFileExtension(file, ".json");

    replacesOnFile(jsonFileCopied, ExcibankVariables.POPULATE_CHANGE_SET_ID, "" + changeSetId);

    return jsonFileCopied;

  }

}
