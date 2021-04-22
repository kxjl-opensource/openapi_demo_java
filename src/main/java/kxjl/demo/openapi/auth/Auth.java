package kxjl.demo.openapi.auth;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import kxjl.demo.openapi.util.Fhc;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class Auth {

    private static final String AUTH_URL = "https://www.xfyeta.com/openapi/oauth/v1/token";
    private static final String YOUR_APP_KEY = "ODM1ZTk4ODAtYTMyZC00ZjBiLTkzMDQtY2VjNWU0ZDUyZWQ5";
    private static final String YOUR_APP_SECRET = "MTM5NUM3NjlGQ0M2REUwN0FBREE3QjUxMkU1Qzg5NUQ";

    public static void main(String[] args) throws IOException {
        auth();
    }

    public static String auth() throws IOException {
        //build request
        JSONObject req = buildReq();

        //exec
        Response response = Fhc.getResponse(req,AUTH_URL);

        //get token
        String token = tokenFrom(response);

        return token;
    }


    private static JSONObject buildReq() {
        JSONObject req = new JSONObject();
        req.fluentPut("app_key", YOUR_APP_KEY)
                .fluentPut("app_secret", YOUR_APP_SECRET);
        return req;
    }

    private static String tokenFrom(Response response) throws IOException {
        JSONObject rspAsJo = JSONObject.parseObject(response.returnContent().asString());
        Object token = JSONPath.eval(rspAsJo, "$.result.token");
        return (String) token;
    }
}
