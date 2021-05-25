package es.excibit;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * SerenityRunner.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class SerenityRunner {	
}