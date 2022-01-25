package com.cn.hermimt.purple.core;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: hermit-purple-config
 * @author: Hydra
 * @create: 2022-01-24 16:52
 **/
public class EnvInitializer {

    private static Map<String,Object> envMap=new HashMap<>();

    public static void init(){
        String rootPath = EnvInitializer.class.getResource("/").getPath();
        List<String> fileList = FileScanner.findFileByType(rootPath,null,FileScanner.TYPE_YML);
        for (String ymlFilePath : fileList) {
            rootPath = FileScanner.getRealRootPath(rootPath);
            ymlFilePath = ymlFilePath.replace(rootPath, "");
            YamlMapFactoryBean yamlMapFb = new YamlMapFactoryBean();
            yamlMapFb.setResources(new ClassPathResource(ymlFilePath));
            Map<String, Object> map = yamlMapFb.getObject();
            YamlConverter.doConvert(map,null,envMap);
        }
    }

    public static void setEnvMap(Map<String, Object> envMap) {
        EnvInitializer.envMap = envMap;
    }

    public static Map<String, Object> getEnvMap() {
        return envMap;
    }
}
