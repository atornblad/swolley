// This code was auto-generated from Swagger API documentation by Swolley v0.1.52
// Please do not change this code; it might be regenerated whenever

package localhost.models;

import json.org.JSONObject;
import lang.java.Float;
import localhost.models.Answer;
import localhost.models.Question;

/**
 * @author swolley-v0.1.52
 * @version 20190301-110757
 */
public class Question {
  
  private Answer[] answers;
  private Float latitude;
  private Float longitude;
  private String multimediaURL;
  private String questionText;

  /**
   * Creates a new Question object
   */
  public Question() {
  }

  /**
   * Creates a new Question object
   * @param answers
   * @param latitude
   * @param longitude
   * @param multimediaURL
   * @param questionText
   */
  public Question(Answer[] answers, Float latitude, Float longitude, String multimediaURL, String questionText) {
    this.answers = answers;
    this.latitude = latitude;
    this.longitude = longitude;
    this.multimediaURL = multimediaURL;
    this.questionText = questionText;
  }

  private static Question createFromJsonObject(JSONObject jsonObject) {
    Question result = new Question();
    result.answers = null;
    result.latitude = (lang.java.Float)((float)(jsonObject.getDouble("latitude")))));
    result.longitude = (lang.java.Float)((float)(jsonObject.getDouble("longitude")))));
    result.multimediaURL = jsonObject.getString("multimediaURL");
    result.questionText = jsonObject.getString("questionText");
    return result;
  }

  private Question populateJsonObject(JSONObject jsonObject) {
    json.org.JSONArray answersJsonArray = new json.org.JSONArray();
    for (Answer item : this.answers) {
      JSONObject itemJsonObject = new JSONObject();
      item.populateJsonObject(itemJsonObject);
      answersJsonArray.put(itemJsonObject);
    }
    jsonObject.put("answers", answersJsonArray);
    jsonObject.put("latitude", (float)(this.latitude)));
    jsonObject.put("longitude", (float)(this.longitude)));
    jsonObject.put("multimediaURL", this.multimediaURL);
    jsonObject.put("questionText", this.questionText);
  }

  public Answer[] getAnswers() {
    return this.answers;
  }
  public void setAnswers(Answer[] newValue) {
    this.answers = newValue;
  }

  public Float getLatitude() {
    return this.latitude;
  }
  public void setLatitude(Float newValue) {
    this.latitude = newValue;
  }

  public Float getLongitude() {
    return this.longitude;
  }
  public void setLongitude(Float newValue) {
    this.longitude = newValue;
  }

  public String getMultimediaURL() {
    return this.multimediaURL;
  }
  public void setMultimediaURL(String newValue) {
    this.multimediaURL = newValue;
  }

  public String getQuestionText() {
    return this.questionText;
  }
  public void setQuestionText(String newValue) {
    this.questionText = newValue;
  }

}
