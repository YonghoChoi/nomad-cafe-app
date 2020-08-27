package com.yonghochoi.nomadcafeapp;

import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;

/**
 * WebChromeClient subclass handles UI-related calls
 * Note: think chrome as in decoration, not the Chrome browser
 */
public class GeoWebChromeClient extends WebChromeClient {
    @Override
    public void onGeolocationPermissionsShowPrompt(String origin,
                                                   GeolocationPermissions.Callback callback) {
        // Always grant permission since the app itself requires location
        // permission and the user has therefore already granted it
        callback.invoke(origin, true, false);
    }
}