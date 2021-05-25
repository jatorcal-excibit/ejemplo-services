package es.excibit.steps.example;

import es.excibit.Excibank.ExcibankVariables;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * The Class exampleTest.
 */
@Slf4j
@RunWith(SerenityRunner.class)
public class exampleTest {

  @Steps
  private static exampleSteps exampleSteps;
  
  @Given("^userId (.*?) and sql (.*?)$")
  public void given_prerequisites(String userId, String sql) throws Throwable {
    
    try {      
      exampleSteps.setData(userId, sql);      
    } catch (Throwable e) {

      log.info("Error on setData {}. Rolling back database", e.getMessage());

      exampleSteps.rollback();

      throw e;
    }
  }

  @When("^call settings endpoint$")
  public void settings () throws Throwable {
    try {
      exampleSteps.doCallGet(ExcibankVariables.EXAMPLE_REQUEST_MAPPING);
    } catch (Throwable e) {

      log.info("Error on setData {}. Rolling back database", e.getMessage());

      exampleSteps.rollback();

      throw e;
    }
  }

  @Then("^response (.*?) with httpstatus (.*?)$")
  public void response(String jsonPayloadResponseFile, int httpstatus) throws Throwable {
    try {
      exampleSteps.manageResponse(jsonPayloadResponseFile, httpstatus);
    } finally {

      log.info("Ending test. Rolling back database");

      exampleSteps.rollback();
    }
  }

}
