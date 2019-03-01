// This code was auto-generated from Swagger API documentation by Swolley v0.1.52
// Please do not change this code; it might be regenerated whenever

package localhost.clients;

import function.util.java.Consumer;
import lang.java.Exception;
import lang.java.Integer;
import lang.java.Runnable;
import localhost.models.Quiz;
import toolbox.volley.android.com.Volley;
import volley.android.com.Request;
import volley.android.com.RequestQueue;
import volley.android.com.Response;
import volley.android.com.VolleyError;

/**
 * Treasures of Lindängen API
 * Detta REST-API används för att hantera quiz, frågor och svar, användarkonton, filuppladdningar med mera för Treasures of Lindängen
 * @author swolley-v0.1.52
 * @version 20190301-110757
 */
public class QuizControllerClient {
  
  private RequestQueue volleyRequestQueue;

  /**
   * Creates a new QuizControllerClient object
   */
  public QuizControllerClient() {
    this(null);
  }

  /**
   * Creates a new QuizControllerClient object
   * @param volleyRequestQueue
   */
  public QuizControllerClient(RequestQueue volleyRequestQueue) {
    this.volleyRequestQueue = volleyRequestQueue;
  }

  /**
   * Creates a new quiz in the database, and returns the ID of the new quiz
   * Uses the POST method to communicate with the /new_quiz endpoint
   * @param quiz
   * @param consumer
   * @param errorCallback
   */
  public void createQuiz(Quiz quiz, Consumer<Integer> consumer, Consumer<Exception> errorCallback) {
  }

  /**
   * Requests a list of all existing quizzes in the database. This will soon be deprecated, as no client should ever need the full list of quizzes.
   * Uses the GET method to communicate with the /all_quizzes endpoint
   * @param consumer
   * @param errorCallback
   */
  public void listQuizzes(Consumer<Quiz[]> consumer, Consumer<Exception> errorCallback) {
  }

  /**
   * Forwards a JPEG image file to file storage and connects it to a quiz
   * Uses the PUT method to communicate with the /set_quiz_image endpoint
   * @param file
   * @param id
   * @param callback
   * @param errorCallback
   */
  public void uploadQuizImage(byte[] file, int id, Runnable callback, Consumer<Exception> errorCallback) {
  }

  public RequestQueue getVolleyRequestQueue() {
    return this.volleyRequestQueue;
  }
  public void setVolleyRequestQueue(RequestQueue newValue) {
    this.volleyRequestQueue = newValue;
  }

}
