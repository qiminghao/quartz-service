package com.example.quartzservice;

import com.example.quartzservice.response.BaseResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.quartz.*;
import org.quartz.impl.RemoteMBeanScheduler;

public class Main {

    public static void main(String[] args) {
        BaseResponse response = BaseResponse.builder()
                .code(1)
                .message("")
                .data("")
                .build();

    }
}
