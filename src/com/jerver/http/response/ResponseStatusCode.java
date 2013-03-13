package com.jerver.http.response;

import java.util.HashMap;

public class ResponseStatusCode {
    public final static ResponseStatusCode INSTANCE = new ResponseStatusCode();

    private HashMap<Integer, String> statusCode = new HashMap<>();

    private ResponseStatusCode() {
        statusCode.put(100,"Continue");
        statusCode.put(101,"Switching Protocols");
        statusCode.put(102,"Processing");
        statusCode.put(200,"OK");
        statusCode.put(201,"Created");
        statusCode.put(202,"Accepted");
        statusCode.put(203,"Non-Authoritative Information");
        statusCode.put(204,"No Content");
        statusCode.put(205,"Reset Content");
        statusCode.put(206,"Partial Content");
        statusCode.put(207,"Multi-Status");
        statusCode.put(208,"Already Reported");
        statusCode.put(226,"IM Used");
        statusCode.put(300,"Multiple Choices");
        statusCode.put(301,"Moved Permanently");
        statusCode.put(302,"Found");
        statusCode.put(303,"See Other");
        statusCode.put(304,"Not Modified");
        statusCode.put(305,"Use Proxy");
        statusCode.put(306,"Reserved");
        statusCode.put(307,"Temporary Redirect");
        statusCode.put(400,"Bad Request");
        statusCode.put(401,"Unauthorized");
        statusCode.put(402,"Payment Required");
        statusCode.put(403,"Forbidden");
        statusCode.put(404,"Not Found");
        statusCode.put(405,"Method Not Allowed");
        statusCode.put(406,"Not Acceptable");
        statusCode.put(407,"Proxy Authentication Required");
        statusCode.put(408,"Request Timeout");
        statusCode.put(409,"Conflict");
        statusCode.put(410,"Gone");
        statusCode.put(411,"Length Required");
        statusCode.put(412,"Precondition Failed");
        statusCode.put(413,"Request Entity Too Large");
        statusCode.put(414,"Request-URI Too Long");
        statusCode.put(415,"Unsupported Media Type");
        statusCode.put(416,"Requested Range Not Satisfiable");
        statusCode.put(417,"Expectation Failed");
        statusCode.put(422,"Unprocessable Entity");
        statusCode.put(423,"Locked");
        statusCode.put(424,"Failed Dependency");
        statusCode.put(425,"Reserved for WebDAV advanced collections expired proposal");
        statusCode.put(426,"Upgrade Required");
        statusCode.put(428,"Precondition Required");
        statusCode.put(429,"Too Many Requests");
        statusCode.put(431,"Request Header Fields Too Large");
        statusCode.put(500,"Internal Server Error");
        statusCode.put(501,"Not Implemented");
        statusCode.put(502,"Bad Gateway");
        statusCode.put(503,"Service Unavailable");
        statusCode.put(504,"Gateway Timeout");
        statusCode.put(505,"HTTP Version Not Supported");
        statusCode.put(506,"Variant Also Negotiates (Experimental)");
        statusCode.put(507,"Insufficient Storage");
        statusCode.put(508,"Loop Detected");
        statusCode.put(510,"Not Extended");
        statusCode.put(511,"Network Authentication Required");
    }


    public String getPhrase(int statusCodeInt) {
        return statusCode.get(statusCodeInt);
    }

    public String getStatusLine(int statusCodeInt) {
        return "HTTP/1.1 " + statusCodeInt + " " + getPhrase(statusCodeInt);
    }
}
