package kxjl.demo.openapi.config;

import com.alibaba.fastjson.JSONObject;
import kxjl.demo.openapi.auth.Auth;
import kxjl.demo.openapi.util.Fhc;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Queue;

public class QueryConfig {

    private static final String QUERY_CONFIG_URL = "https://www.xfyeta.com/openapi/config/v1/query";

    public static void main(String[] args) throws IOException, URISyntaxException {
        String token = Auth.auth();

        JSONObject req = buildReq();

        Response response = Fhc.getResponse(req, token, QUERY_CONFIG_URL);

        String rspAsStr = response.returnContent().asString();
        //do your want...
    }



    private static JSONObject buildReq() {
        JSONObject req = new JSONObject();
        req.fluentPut("type", 1)
                .fluentPut("pageIndex", 1)
                .fluentPut("pageSize", 10);
        return req;
    }


}
