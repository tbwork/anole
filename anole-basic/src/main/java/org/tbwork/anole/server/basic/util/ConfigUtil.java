package org.tbwork.anole.server.basic.util;

import org.tbwork.anole.loader.enums.OsCategory;
import org.tbwork.anole.loader.util.OsUtil;
import org.tbwork.anole.loader.util.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @program: anole-server
 * @description: Configuration utils
 * @author: tommy.tb
 * @create: 2019-04-01 17:19
 **/
public class ConfigUtil {



    public static void initialize(String directory, List<String> configFileNames){
        if(directory.endsWith("/")){
            directory = StringUtil.concat(directory, "/");
        }
        String path = StringUtil.concat(getRootPath(), directory);
        try{
            File file = new File(path);
            if(!file.exists()){
                file.mkdir();
            }
            for(String configFileName : configFileNames){
                String targetPath = StringUtil.concat(path, configFileName);
                File configFile = new File(targetPath);
                if(!configFile.exists()){
                    configFile.createNewFile();
                    writeToFile(configFile, configFileName);
                }
            }
        }
        catch(Exception e){

        }
    }


    private static void writeToFile(File file, String resourceName) throws Exception {
        InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(resourceName);
        OutputStream outputStream = new FileOutputStream(file);
        byte [] bytes = new byte[(int)file.length()];
        inputStream.read(bytes, 0, bytes.length);
        outputStream.write(inputStream.read(bytes));
        outputStream.close();
    }

    private static String getRootPath(){

        OsCategory osCategory =  OsUtil.getOsCategory();

        String result = null;
        if(osCategory == OsCategory.WINDOWS){
            result =  System.getProperty("user.home");
        }
        else{
            result = "/etc/";
        }
        if(result.endsWith("/")){
            result = StringUtil.concat(result, "/");
        }
        result = StringUtil.concat(result, "anole/");
        return result;
    }

}
