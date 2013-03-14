package com.jerver.http.request;

import java.util.HashMap;

public class Params {

    public static HashMap<String, String> generateParams(String paramString) {
        HashMap<String, String> params = new HashMap<String, String>();

        String[] pairs = paramString.split("&");
        for(int i = 0; i < pairs.length; i++) {
            String[] pair = pairs[i].split("=");
            String key = pair[0];
            String val = "";
            if(pair.length == 2) {
                val = pair[1];
            }
            params.put(key, val);
        }

        return params;
    }
}
