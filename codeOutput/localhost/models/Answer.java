// This code was auto-generated from Swagger API documentation by Swolley v0.1.52
// Please do not change this code; it might be regenerated whenever

package localhost.models;

import json.org.JSONObject;
import localhost.models.Answer;

/**
 * @author swolley-v0.1.52
 * @version 20190301-110757
 */
public class Answer {
  
  private String answerText;
  private boolean correct;
  private String multimediaURL;

  /**
   * Creates a new Answer object
   */
  public Answer() {
  }

  /**
   * Creates a new Answer object
   * @param answerText
   * @param correct
   * @param multimediaURL
   */
  public Answer(String answerText, boolean correct, String multimediaURL) {
    this.answerText = answerText;
    this.correct = correct;
    this.multimediaURL = multimediaURL;
  }

  private static Answer createFromJsonObject(JSONObject jsonObject) {
    Answer result = new Answer();
    result.answerText = jsonObject.getString("answerText");
    result.correct = jsonObject.getBoolean("correct");
    result.multimediaURL = jsonObject.getString("multimediaURL");
    return result;
  }

  private Answer populateJsonObject(JSONObject jsonObject) {
    jsonObject.put("answerText", this.answerText);
    jsonObject.put("correct", this.correct);
    jsonObject.put("multimediaURL", this.multimediaURL);
  }

  public String getAnswerText() {
    return this.answerText;
  }
  public void setAnswerText(String newValue) {
    this.answerText = newValue;
  }

  public boolean getCorrect() {
    return this.correct;
  }
  public void setCorrect(boolean newValue) {
    this.correct = newValue;
  }

  public String getMultimediaURL() {
    return this.multimediaURL;
  }
  public void setMultimediaURL(String newValue) {
    this.multimediaURL = newValue;
  }

}
