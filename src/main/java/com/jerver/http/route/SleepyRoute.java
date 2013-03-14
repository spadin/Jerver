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
        response.setStatusCode(404);
        response.appendHeader("Content-type: " + getContentType());
        response.setBody(getBody());
    }

    public byte[] getBody() {
        try {
            Thread.sleep(this.milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date()).getBytes();
    }

    public String getContentType() {
        return "text/plain";
    }
}
