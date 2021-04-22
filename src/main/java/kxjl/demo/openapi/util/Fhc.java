package kxjl.demo.openapi.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.net.URISyntaxException;

public class Fhc {

    public static Response getResponse(JSONObject req,String token,String url) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.setParameter("token", token);
        return Request.Post(uriBuilder.build())
                .bodyString(req.toJSONString(), ContentType.APPLICATION_JSON)
                .execute();
    }

    public static Response getResponse(JSONObject req,String url) throws IOException {
        return Request.Post(url)
                .bodyString(req.toJSONString(), ContentType.APPLICATION_JSON)
                .execute();
    }
}
