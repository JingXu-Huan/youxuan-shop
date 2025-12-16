package com.example.youxuanshop.oss;

import java.io.*;
import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyuncs.exceptions.ClientException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class OssUtil {
    private OSS ossClient;
    private final String bucketName = "jingxu";
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";

    //初始化调用此方法
    @PostConstruct
    private void initOss() throws ClientException {
        String region = "cn-beijing";
        //获取环境变量的设置
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
        config.setSignatureVersion(SignVersion.V4);
        this.ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
    }

    //bean销毁时调用此方法,关闭连接资源。
    @PreDestroy
    private void destroy() {
        ossClient.shutdown();
    }

    public OssUtil() {
    }

    public String upload(InputStream inputStream, String objectName) {
        ossClient.putObject(bucketName, objectName, inputStream);
        return "https://" + bucketName + ".oss-cn-beijing.aliyuncs.com/" + objectName;
    }

}