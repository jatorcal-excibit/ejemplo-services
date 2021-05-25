package es.excibit.steps;

import net.serenitybdd.core.Serenity;

/**
 * The Class Steps.
 */
public class Steps {


  /**
   * Add message on SerenityBDD
   * 
   * @param title
   * @param message
   */
  public void addMessageReport(String title, String message) {
    
    if (!title.isEmpty() && !message.isEmpty()) {
      Serenity.recordReportData().withTitle(title).andContents(message);
    }
    
  }

}
