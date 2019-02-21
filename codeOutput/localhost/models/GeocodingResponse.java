// This code was auto-generated from Swagger API documentation by Swolley v0.1.16
// Please do not change this code; it might be regenerated whenever

package localhost.models;

import json.org.JSONObject;
import localhost.models.GeocodingResponse;
import localhost.models.LatLongPosition;

/**
 * @author swolley-v0.1.16
 * @version 20190221-163730
 */
public class GeocodingResponse {
  
  private LatLongPosition coordinates;
  private String errorMessage;

  /**
   * Creates a new GeocodingResponse object
   */
  public GeocodingResponse() {
  }

  /**
   * Creates a new GeocodingResponse object
   * @param coordinates
   * @param errorMessage
   */
  public GeocodingResponse(LatLongPosition coordinates, String errorMessage) {
    this.coordinates = coordinates;
    this.errorMessage = errorMessage;
  }

  private static GeocodingResponse createFromJsonObject(JSONObject jsonObject) {
    GeocodingResponse result = new GeocodingResponse();
    result.coordinates = null;
    result.errorMessage = jsonObject.getString("errorMessage");
    return result;
  }

  private GeocodingResponse populateJsonObject(JSONObject jsonObject) {
    JSONObject coordinatesJsonObject = new JSONObject();
    this.coordinates.populateJsonObject(coordinatesJsonObject);
    jsonObject.put("coordinates", coordinatesJsonObject);
    jsonObject.put("errorMessage", this.errorMessage);
  }

  public LatLongPosition getCoordinates() {
    return this.coordinates;
  }
  public void setCoordinates(LatLongPosition newValue) {
    this.coordinates = newValue;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }
  public void setErrorMessage(String newValue) {
    this.errorMessage = newValue;
  }

}
