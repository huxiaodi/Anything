package com.test.framework.utils;

import com.test.common.CommonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by hugo on 2016/8/3.
 */
public class FileUploadUtils {
    private String staticUrl;
    private String staticPath;
    private String projectPath;


    public static String FOLDER_USER = "user";    //用户图片目录名



    public String uploadUser(MultipartFile file, String userId) {
        return upload(file, userId, FOLDER_USER);
    }


    public String upload(MultipartFile file, String id, String folder) {
        String url = "";
        try {
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            String finalName = folder + "/" + id + "/" + CommonUtil.uuid() + "." + suffix;
            Path path = Paths.get(staticPath, projectPath, finalName);
            String newFileName = path.toString();
            newFileName = StringUtils.replace(newFileName, "\\", "/");
            File file1 = new File(newFileName);
            System.err.println(file1.getAbsolutePath());
            FileUtils.copyInputStreamToFile(file.getInputStream(),file1);
            if (staticPath.endsWith("/")) {
                url = staticUrl + newFileName.replace(staticPath, "/");
            } else {
                url = staticUrl + newFileName.replace(staticPath, "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }



    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    
    
    public String uploadEntrust(MultipartFile file,String path_uri) {
        String url = "";
        try {
            String filename = file.getOriginalFilename();
            //后缀
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            String finalName = path_uri + "/" + CommonUtil.uuid()+"/" +filename ;
            Path path = Paths.get(staticPath, projectPath, finalName);
            String newFileName = path.toString();
            newFileName = StringUtils.replace(newFileName, "\\", "/");
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(newFileName));
            if (staticPath.endsWith("/")) {
                url = staticUrl + newFileName.replace(staticPath, "/");
            } else {
                url = staticUrl + newFileName.replace(staticPath, "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}