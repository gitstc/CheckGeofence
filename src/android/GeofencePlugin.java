package com.stc.GeofencePlugin;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.location.Location;

public class GeofencePlugin extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("inGeofence")) {
            Location sLoc = new Location("");
            Location dLoc = new Location("");

            JSONObject options = (JSONObject)args.getJSONObject(0);

            float radius = Float.parseFloat(options.optString("radius","0"));

            JSONObject sourceLoc = (JSONObject)options.getJSONObject("source");
            JSONObject destinationLoc = (JSONObject)options.getJSONObject("destination");

            try {
                options = (JSONObject)args.getJSONObject(0);

                radius = Float.parseFloat(options.optString("radius","0"));

                sourceLoc = (JSONObject)options.getJSONObject("source");
                destinationLoc = (JSONObject)options.getJSONObject("destination");
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 1");
                return true;
            }
            try {
                Log.d("FIRAS 1", sourceLoc.getDouble("latitude") + "-" + sourceLoc.getDouble("longitude"));

                sLoc.setLatitude(sourceLoc.getDouble("latitude"));
                sLoc.setLongitude(sourceLoc.getDouble("longitude"));
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 2");
                return true;
            }
            try {
                Log.d("FIRAS 2", destinationLoc.getDouble("latitude") + "-" + destinationLoc.getDouble("longitude"));

                dLoc.setLatitude(destinationLoc.getDouble("latitude"));
                dLoc.setLongitude(destinationLoc.getDouble("longitude"));
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 3");
                return true;
            }
            try {
                float distance = sLoc.distanceTo(dLoc);

                Log.d("Firas 3", "Distance " + distance);
                Log.d("Firas 4", "Radius " + radius);

                JSONObject res = new JSONObject();
                res.put("distance", (int)distance);
                res.put("radius", radius);

                if(distance < radius){
                    res.put("inGeofence", 1);
                }
                else{
                    res.put("inGeofence", 0);
                }
                callbackContext.success(res);
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 4");
            }
			return true;
        }
        else if (action.equals("getDistance")) {
            Location sLoc = new Location("");
            Location dLoc = new Location("");

            JSONObject options = (JSONObject)args.getJSONObject(0);

            JSONObject sourceLoc = (JSONObject)options.getJSONObject("source");
            JSONObject destinationLoc = (JSONObject)options.getJSONObject("destination");

            try {
                options = (JSONObject)args.getJSONObject(0);

                sourceLoc = (JSONObject)options.getJSONObject("source");
                destinationLoc = (JSONObject)options.getJSONObject("destination");
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 1");
                return true;
            }
            try {
                Log.d("FIRAS 1", sourceLoc.getDouble("latitude") + "-" + sourceLoc.getDouble("longitude"));

                sLoc.setLatitude(sourceLoc.getDouble("latitude"));
                sLoc.setLongitude(sourceLoc.getDouble("longitude"));
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 2");
                return true;
            }
            try {
                Log.d("FIRAS 2", destinationLoc.getDouble("latitude") + "-" + destinationLoc.getDouble("longitude"));

                dLoc.setLatitude(destinationLoc.getDouble("latitude"));
                dLoc.setLongitude(destinationLoc.getDouble("longitude"));
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 3");
                return true;
            }
            try {
                float distance = sLoc.distanceTo(dLoc);

                Log.d("Firas 3", "Distance " + distance);

                JSONObject res = new JSONObject();
                res.put("distance", (int)distance);

                callbackContext.success(res);
            }
            catch(Exception ex){
                callbackContext.error(ex.getMessage() + " - 4");
            }
			return true;
		}

		return false;
	}
}