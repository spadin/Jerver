package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SleepyRoute implements Routable{
    private final int milliseconds;

    public SleepyRoute(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void resolve(Request request, Response response) {
        response.setStatusCode(200);
        response.appendHeader("Content-type: " + getContentType());
        response.setBody(getBody());
    }

    public void sleep() {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Failed to sleep");
        }
    }

    public String getFormattedDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public byte[] getBody() {
        sleep();
        return getFormattedDate().getBytes();
    }

    public String getContentType() {
        return "text/plain";
    }
}
