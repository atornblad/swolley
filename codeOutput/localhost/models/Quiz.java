// This code was auto-generated from Swagger API documentation by Swolley v0.1.16
// Please do not change this code; it might be regenerated whenever

package localhost.models;

import json.org.JSONObject;
import localhost.models.Question;
import localhost.models.Quiz;

/**
 * @author swolley-v0.1.16
 * @version 20190221-163730
 */
public class Quiz {
  
  private String description;
  private String name;
  private String picURL;
  private Question[] questions;
  private int steps;

  /**
   * Creates a new Quiz object
   */
  public Quiz() {
  }

  /**
   * Creates a new Quiz object
   * @param description
   * @param name
   * @param picURL
   * @param questions
   * @param steps
   */
  public Quiz(String description, String name, String picURL, Question[] questions, int steps) {
    this.description = description;
    this.name = name;
    this.picURL = picURL;
    this.questions = questions;
    this.steps = steps;
  }

  private static Quiz createFromJsonObject(JSONObject jsonObject) {
    Quiz result = new Quiz();
    result.description = jsonObject.getString("description");
    result.name = jsonObject.getString("name");
    result.picURL = jsonObject.getString("picURL");
    result.questions = null;
    result.steps = jsonObject.getInt("steps");
    return result;
  }

  private Quiz populateJsonObject(JSONObject jsonObject) {
    json.org.JSONArray questionsJsonArray = new json.org.JSONArray();
    jsonObject.put("description", this.description);
    jsonObject.put("name", this.name);
    jsonObject.put("picURL", this.picURL);
    for (Question item : this.questions) {
      JSONObject itemJsonObject = new JSONObject();
      item.populateJsonObject(itemJsonObject);
      questionsJsonArray.put(itemJsonObject);
    }
    jsonObject.put("questions", questionsJsonArray);
    jsonObject.put("steps", this.steps);
  }

  public String getDescription() {
    return this.description;
  }
  public void setDescription(String newValue) {
    this.description = newValue;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String newValue) {
    this.name = newValue;
  }

  public String getPicURL() {
    return this.picURL;
  }
  public void setPicURL(String newValue) {
    this.picURL = newValue;
  }

  public Question[] getQuestions() {
    return this.questions;
  }
  public void setQuestions(Question[] newValue) {
    this.questions = newValue;
  }

  public int getSteps() {
    return this.steps;
  }
  public void setSteps(int newValue) {
    this.steps = newValue;
  }

}
