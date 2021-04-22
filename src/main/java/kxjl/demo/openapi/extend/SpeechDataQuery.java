package kxjl.demo.openapi.extend;

import com.alibaba.fastjson.JSONObject;
import kxjl.demo.openapi.util.BrainResultDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 对应机器人业务能力扩展-语义数据查询-demo
 */
public class SpeechDataQuery {

    public static void main(String[] args) {
        String body = "";

        speechDataQuery(body);

    }


    public static String speechDataQuery(String body) {
        // 获取到body
        System.out.println("接收到数据:" + body);
        // todo 应用方根据拿到数据做相应处理

        // 应用方返回话术对应的参数参数值信息
        List<BrainResultDTO> brainList = new ArrayList<>();
        brainList.add(BrainResultDTO.buildIntegerDTO("reportorRelation", 8));
        brainList.add(BrainResultDTO.buildStringDTO("reportorName", "yeta"));
        return JSONObject.toJSONString(brainList);
    }
}
