package com.jerver.http.request;

import java.util.HashMap;

public class Params {

    public static HashMap<String,String> generateParams(String paramString) {
        HashMap<String, String> params = new HashMap<>();

        String[] pairs = paramString.split("&");
        for(int i = 0; i < pairs.length; i++) {
            String[] pair = pairs[i].split("=");
            params.put(pair[0], pair[1]);
        }

        return params;
    }
}
