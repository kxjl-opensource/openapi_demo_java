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

public class Task {

    private static final String CREATE_TASK = "https://www.xfyeta.com/openapi/outbound/v1/task/create";
    private static final String PUT_DATA = "https://www.xfyeta.com/openapi/outbound/v1/task/insert";
    private static final String START_TASK = "https://www.xfyeta.com/openapi/outbound/v1/task/start";
    private static final String PAUSE_TASK = "https://www.xfyeta.com/openapi/outbound/v1/task/pause";
    private static final String DELETE_TASK = "https://www.xfyeta.com/openapi/outbound/v1/task/delete";
    private static final String QUERY_TASK = "https://www.xfyeta.com/openapi/outbound/v1/task/query";


    public static void main(String[] args) throws IOException, URISyntaxException {

        String token = Auth.auth();

        Long taskId = createTask(token);

        List<Long> taskDataIds = putData(token,taskId);

        startTask(token,taskId);

        //long time go

        pauseTask(token,taskId);

        deleteTask(token,taskId);

        //view your tasks
        queryTask(token);

    }

    private static void queryTask(String token) throws IOException, URISyntaxException {
        JSONObject req = new JSONObject();
        req.fluentPut("time_begin", 153000000)
                .fluentPut("show_remaining", true);

        Response response = Fhc.getResponse(req, token, QUERY_TASK);
        JSONObject rspAsJo = JSONObject.parseObject(response.returnContent().asString());

        //do your want

        //eg:get processCount
        Object process_count = JSONPath.eval(rspAsJo,
                "$.result.rows[0].process_count");

    }

    private static void deleteTask(String token, Long taskId) throws IOException, URISyntaxException {
        JSONObject req = new JSONObject();
        req.fluentPut("task_id", taskId);
        Fhc.getResponse(req, token, DELETE_TASK);
    }

    private static void pauseTask(String token, Long taskId) throws IOException, URISyntaxException {
        JSONObject req = new JSONObject();
        req.fluentPut("task_id", taskId);
        Fhc.getResponse(req, token, PAUSE_TASK);
    }

    private static void startTask(String token, Long taskId) throws IOException, URISyntaxException {
        JSONObject req = new JSONObject();
        req.fluentPut("task_id", taskId);
        Fhc.getResponse(req, token, START_TASK);
    }

    private static List<Long> putData(String token, Long taskId) throws IOException, URISyntaxException {
        JSONObject req = new JSONObject();
        req.fluentPut("task_id", taskId)
                .fluentPut("call_column", Lists.newArrayList("客户手机号码", "姓名"))
                .fluentPut("call_list", Lists.newArrayList("19900000001", "张三"));

        Response response = Fhc.getResponse(req, token, PUT_DATA);

        Object ids = JSONPath.eval(JSONObject.parseObject(response.returnContent().asString()),
                "$.result.task_data_ids");
        return (List) ids;
    }

    private static Long createTask(String token) throws IOException, URISyntaxException {
        JSONObject req = new JSONObject();
        req.fluentPut("task_name", "test")
                .fluentPut("line_num", "055169101407,055169101406")
                .fluentPut("robot_id", 719)
                .fluentPut("recall_count", 1)
                .fluentPut("time_recall_wait", 600)
                .fluentPut("time_range", Lists.newArrayList("09:00:00-12:00:00", "13:00:00-17:30:00"))
                .fluentPut("time_begin", 1527321492000L)
                .fluentPut("time_begin", 1527325092000L);

        Response response = Fhc.getResponse(req, token, CREATE_TASK);

        Object taskId = JSONPath.eval(JSONObject.parseObject(response.returnContent().asString()),
                "$.result.task_id");
        return (Long) taskId;
    }

}
