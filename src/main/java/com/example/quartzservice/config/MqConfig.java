package com.example.quartzservice.config;

import java.util.HashMap;

public class MqConfig {

    public static HashMap<String, String> map = new HashMap<String, String>();

    static {
        map.put("group1", "queue1");
        map.put("group2", "queue2");
        map.put("group3", "queue3");
        map.put("group4", "queue4");
        map.put("group5", "queue5");
    }

    public static void init() {

    }
}
