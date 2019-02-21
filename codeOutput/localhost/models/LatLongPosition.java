// This code was auto-generated from Swagger API documentation by Swolley v0.1.16
// Please do not change this code; it might be regenerated whenever

package localhost.models;

import json.org.JSONObject;
import localhost.models.LatLongPosition;

/**
 * @author swolley-v0.1.16
 * @version 20190221-163730
 */
public class LatLongPosition {
  
  private float latitude;
  private float longitude;

  /**
   * Creates a new LatLongPosition object
   */
  public LatLongPosition() {
  }

  /**
   * Creates a new LatLongPosition object
   * @param latitude
   * @param longitude
   */
  public LatLongPosition(float latitude, float longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  private static LatLongPosition createFromJsonObject(JSONObject jsonObject) {
    LatLongPosition result = new LatLongPosition();
    result.latitude = (float)(jsonObject.getDouble("latitude")));
    result.longitude = (float)(jsonObject.getDouble("longitude")));
    return result;
  }

  private LatLongPosition populateJsonObject(JSONObject jsonObject) {
    jsonObject.put("latitude", (double)(this.latitude)));
    jsonObject.put("longitude", (double)(this.longitude)));
  }

  public float getLatitude() {
    return this.latitude;
  }
  public void setLatitude(float newValue) {
    this.latitude = newValue;
  }

  public float getLongitude() {
    return this.longitude;
  }
  public void setLongitude(float newValue) {
    this.longitude = newValue;
  }

}
