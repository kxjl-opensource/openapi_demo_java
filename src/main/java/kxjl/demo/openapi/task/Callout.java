package kxjl.demo.openapi.task;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.common.collect.Lists;
import kxjl.demo.openapi.auth.Auth;
import kxjl.demo.openapi.util.Fhc;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Callout {

    private static final String CALLOUT_URL = "https://www.xfyeta.com/openapi/outbound/v1/task/callout";

    public static void main(String[] args) throws IOException, URISyntaxException {
        String token = Auth.auth();
        JSONObject req = buildReq();
        Response response = Fhc.getResponse(req, token, CALLOUT_URL);

        //get id list
        Object ids = JSONPath.eval(JSONObject.parseObject(response.returnContent().asString()),
                "$.result.task_data_ids");
        List idsList = (List) ids;

        //do your want
    }

    private static JSONObject buildReq() {

        //这里的参数是从QueryConfig得到的
        //call_list则是您的数据
        JSONObject req = new JSONObject();
        req.fluentPut("robot_id", 719)
                .fluentPut("line_num", "69101338")
                .fluentPut("call_column", Lists.newArrayList("客户手机号码", "姓名"))
                .fluentPut("call_list", Lists.newArrayList("19900000001", "张三"));
        return req;
    }
}
