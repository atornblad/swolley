// This code was auto-generated from Swagger API documentation by Swolley v0.1.52
// Please do not change this code; it might be regenerated whenever

package localhost.clients;

import function.util.java.Consumer;
import lang.java.Exception;
import localhost.models.GeocodingResponse;
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
public class GisControllerClient {
  
  private RequestQueue volleyRequestQueue;

  /**
   * Creates a new GisControllerClient object
   */
  public GisControllerClient() {
    this(null);
  }

  /**
   * Creates a new GisControllerClient object
   * @param volleyRequestQueue
   */
  public GisControllerClient(RequestQueue volleyRequestQueue) {
    this.volleyRequestQueue = volleyRequestQueue;
  }

  /**
   * Accepts a single string containing an address or the name of a place, and returns latitude/longitude coordinates for that place
   * Uses the POST method to communicate with the /geocode endpoint
   * @param address
   * @param consumer
   * @param errorCallback
   */
  public void geocode(String address, Consumer<GeocodingResponse> consumer, Consumer<Exception> errorCallback) {
  }

  public RequestQueue getVolleyRequestQueue() {
    return this.volleyRequestQueue;
  }
  public void setVolleyRequestQueue(RequestQueue newValue) {
    this.volleyRequestQueue = newValue;
  }

}
