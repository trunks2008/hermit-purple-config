package com.cn.hermimt.purple.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @program: hermit-purple-config
 * @author: Hydra
 * @create: 2022-01-24 10:59
 **/
@Component
public class ScanRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        doScanComponent();
    }

    private void doScanComponent(){
        String rootPath = this.getClass().getResource("/").getPath();
        List<String> fileList = FileScanner.findFileByType(rootPath,null,FileScanner.TYPE_CLASS);
        doFilter(rootPath,fileList);
//        Map<String, Map<Class, String>> pool = VariablePool.getPool();
//        System.out.println(pool);
        EnvInitializer.init();
    }

    private void doFilter(String rootPath, List<String> fileList) {
        rootPath = FileScanner.getRealRootPath(rootPath);
        for (String fullPath : fileList) {
            String shortName = fullPath.replace(rootPath, "")
                    .replace(FileScanner.TYPE_CLASS,"");
            String packageFileName=shortName.replaceAll(Matcher.quoteReplacement(File.separator),"\\.");

            try {
                Class clazz = Class.forName(packageFileName);
                if (clazz.isAnnotationPresent(Component.class)
                        || clazz.isAnnotationPresent(Controller.class)
                        ||clazz.isAnnotationPresent(Service.class)){
//                    System.out.println(packageFileName);
                    VariablePool.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
