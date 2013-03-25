package com.jerver.http.route;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.Response;

public interface Routable {
    public void resolve(RequestImpl request, Response response);
}
