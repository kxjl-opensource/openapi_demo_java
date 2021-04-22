package kxjl.demo.openapi.extend;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 对应机器人业务能力扩展-话术动态数据-demo
 */
public class SpeechDynamicDataQuery {


    public static void main(String[] args) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("app_id", "167"); //应用id
        requestMap.put("business_id", "167"); //企业id
        requestMap.put("robot_id", "167"); //话术id
        requestMap.put("call_relation_id", "167"); //业务侧关联id
        speechDynamicDataQuery(JSONObject.toJSONString(requestMap));
    }

    public static String speechDynamicDataQuery(String body) {
        // 获取到body
        System.out.println("接收到数据:" + body);
        // todo 应用方根据拿到数据做相应处理
        Map<String, String> resultMap = new HashMap<>();
        // 这里的key指话术上配置的参数
        resultMap.put("姓名", "张三");
        resultMap.put("性别", "男");
        // 应用方返回对应参数配置的值
        return JSONObject.toJSONString(resultMap);
    }
}
