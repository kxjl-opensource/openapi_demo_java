package kxjl.demo.openapi.push;

import com.alibaba.fastjson.JSONObject;
import kxjl.demo.openapi.util.BrainResultDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应数据推送
 */
public class DataPush {

    private static final String SUCCESS = "success";

    public static void main(String[] args) {
        String body = "";

        dataPush(body);

    }


    public static String dataPush(String body) {
        // 获取到body
        System.out.println("接收到数据:" + body);
        // todo 应用方根据拿到数据做相应处理


        return SUCCESS;
    }

}
