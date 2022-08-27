package com.cxf.reggie.controller;

import com.cxf.reggie.common.R;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {



    @GetMapping("/download")
    public ResponseEntity<byte[]> download(String name) throws Exception {
        String fileFullPath = this.getClass().getClassLoader().
                getResource("public/photo/").getPath()+name;

        //1. 先获取到下载文件的inputStream
        InputStream inputStream = new FileInputStream(new File(fileFullPath));

        //2. 开辟一个存放文件的byte数组, 这里老师使用byte[] 是可以支持二进制数据(图片，视频。)
        byte[] bytes = new byte[inputStream.available()];

        //3. 将下载文件的数据，读入到byte[]
        inputStream.read(bytes);

        //public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {}

        //4. 创建返回的HttpStatus
        HttpStatus httpStatus = HttpStatus.OK;

        //5. 创建 headers
        HttpHeaders headers = new HttpHeaders();

        //构建一个ResponseEntity 对象1. 的http响应头headers 2. http响应状态 3. 下载的文件数据
        ResponseEntity<byte[]> responseEntity =
                new ResponseEntity<>(bytes, headers, httpStatus);

        //如果出现找不到文件，解决方法 rebuild project -> 重启tomcat
        return responseEntity;

    }





    @PostMapping("/upload")
    public R<String> upload(@RequestParam(value = "file") MultipartFile file,
                            HttpServletRequest request) throws IOException {
        //接收到提交的文件名
        String originalFilename = file.getOriginalFilename();

        String fileName= UUID.randomUUID().toString()
                +originalFilename.substring(originalFilename.lastIndexOf("."));

        System.out.println("文件名= " + fileName+"  上传成功");

        String filePath = this.getClass().getClassLoader().
                getResource("public/photo/").getPath();

        File dir = new File(filePath);

        if(!dir.exists()){
            dir.mkdirs();
        }

        file.transferTo(new File(dir,fileName));

        return R.success(fileName);
    }
}
