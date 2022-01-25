package com.cn.hermimt.purple.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * @program: hermit-purple-config
 * @author: Hydra
 * @create: 2022-01-21 16:54
 **/
@RestController
@RequestMapping("config")
public class ConfigController {

    @PostMapping("save")
    public String getUser(@RequestBody Map<String,Object> newValue) {
        String ymlContent =(String) newValue.get("yml");
        PropertyTrigger.change(ymlContent);
        return "success";
    }

    @GetMapping("get")
    public String get(){
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        String yamlContent = null;
        try {
            Map<String, Object> envMap = EnvInitializer.getEnvMap();
            Map<String, Object> map = YamlConverter.monoToMultiLayer(envMap, null);
            yamlContent = objectMapper.writeValueAsString(map);
            System.out.println(yamlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yamlContent;
    }
}
