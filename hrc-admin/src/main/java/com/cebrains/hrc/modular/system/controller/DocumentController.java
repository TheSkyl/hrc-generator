package com.cebrains.hrc.modular.system.controller;

import com.cebrains.hrc.config.properties.HRCProperties;
import com.cebrains.hrc.core.util.FileUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码生成
 *
 * @author frank
 * @date 2017-05-05 23:10
 */
@Controller
@RequestMapping("/docd")
public class DocumentController {

    @Resource
    private HRCProperties hrcProperties;

    @Autowired
    Producer producer;

    /**
     * 返回文档
     *
     * @author frank
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/{docName:.+}")
    public void renderPicture(@PathVariable("docName") String docName, HttpServletResponse response) {
        String path = hrcProperties.getFileUploadPath() + "doc/" + docName;
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
