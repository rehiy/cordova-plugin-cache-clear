package com.anrip.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.util.Log;

@TargetApi(19)
public class CacheClear extends CordovaPlugin {
    private static final String LOG_TAG = "CacheClear";
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("all")) {
            Log.v(LOG_TAG, "Cordova Android CacheClear() called.");
            this.callbackContext = callbackContext;

            final CacheClear self = this;
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        // clear the cache
                        self.webView.clearCache(true);
                        // send success result to cordova
                        PluginResult result = new PluginResult(PluginResult.Status.OK);
                        result.setKeepCallback(false);
                        self.callbackContext.sendPluginResult(result);
                    } catch (Exception e) {
                        String msg = "Error while clearing webview cache.";
                        Log.e(LOG_TAG, msg);
                        // return error answer to cordova
                        PluginResult result = new PluginResult(PluginResult.Status.ERROR, msg);
                        result.setKeepCallback(false);
                        self.callbackContext.sendPluginResult(result);
                    }
                }
            });
            return true;
        }
        return false;
    }

}
