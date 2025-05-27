package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.ItemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files/api")
public class FileAPI {
    // 파일 업로드의 경우
    // 화면단에서 제일 먼저 요청을 받는다.
    // 요청을 받아서 이미지를 저장 후,
    // 다른 서비스에 있는 내용을 호출해야하는 케이스

    // 파일 Read 의 경우
    // 저장된 파일 경로를 받아오기 위해,
    // 다른 서비스에서 먼저 데이터를 받아온 후 (파일 경로 / 파일 이미지),
    // fileController 를 호출해야한다

    // 프론트 단에서 이미지를 넘길 때 파라미터를 함께 fetch 에 담아서 줘야함
    // ex) MemberImg 이면 이미지는 한개만 필요하기 때문에
    // { 이미지 파일, DataType (member)
    // 요청한 컨트롤러에 따른 폴더 구성을 다르게 해야한다.
    // -> MemberImg : member, ItemImg : item, AchievementImg : achievement
    // Achievement 이미지만 썸네일이 필요하지 않음
    // 썸네일 이미지를 따로 받도록 작업이 필요

    // ex) Board 혹은 Event Img 라면 이미지가 여러개 필요하므로
    // { 이미지 파일 리스트, DataType(board / event) }
    // 이미지를 구분할 기준이 따로 있지 않기 때문에 날짜별로 폴더 경로를 구성하여 구분
    // BoardImg -> board/2025/05/09, EventImg -> event/2025/05/09

    private String getDatePath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    @PostMapping("/files-upload")
    public ResponseEntity<Map<String, Object>> uploadFiles(@RequestParam("imgFiles") List<MultipartFile> imgFiles, @RequestParam("dataType") String dataType) throws IOException {
        String basePath = "C:/personalbuddy/";
        String filePath = "images/" + dataType + "/" + getDatePath();
        Map<String, Object> response = new HashMap<>();
        List<String> uuids = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        File file = new File(basePath + filePath);

        if(!file.exists()){
            file.mkdirs();
        }

        for(int i = 0; i < imgFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());

            fileNames.add(uuids.get(i) + "_" + imgFiles.get(i).getOriginalFilename());
            imgFiles.get(i).transferTo(new File(basePath + filePath, uuids.get(i) + "_" + imgFiles.get(i).getOriginalFilename()));

            // 썸네일 저장
            if(imgFiles.get(i).getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(basePath + filePath, "t_" + uuids.get(i) + "_" + imgFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(imgFiles.get(i).getInputStream(), out, 100, 100);
                out.close();
            }
        }
        // 파일의 경로는 동일하게 사용한다
        // 다만 파일의 이름은 여러개로 정해질 수 있어 List 로 전달해야함
        response.put("filePath", filePath);
        log.info("filePath : {}", filePath);
        response.put("fileNames", fileNames);
        response.put("message", "upload success");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/file-upload")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("imgFile") MultipartFile imgFile, @RequestParam("dataType") String dataType) throws IOException {
        String filePath = "C:/personalbuddy/images/" + dataType;
        File file = new File(filePath);
        Map<String, Object> response = new HashMap<>();

        System.out.println("imgFile = " + imgFile.getOriginalFilename());
        System.out.println("dataType = " + dataType);

        if(!file.exists()){
            file.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        imgFile.transferTo(new File(filePath, uuid + "_" + imgFile.getOriginalFilename()));
        String fileName = uuid + "_" + imgFile.getOriginalFilename();

        if (imgFile.getContentType().startsWith("image")) {
            File thumbnailFile = new File(filePath, "t_" + fileName);
            Thumbnails.of(imgFile.getInputStream())
                    .size(100, 100)
                    .toFile(thumbnailFile);
        }

        String memberImgPath = "images/" + dataType;

        response.put("filePath", filePath);
        response.put("fileName", fileName);
        response.put("memberImgPath", memberImgPath);
        response.put("message", "upload success");

        return ResponseEntity.ok(response);
    }

//    @ResponseBody
//    @GetMapping("/display")
//    public byte[] displayEventImg(@RequestParam("filePath") String filePath, @RequestParam("fileName") String fileName) throws IOException {
//        String basePath = "C:/personalbuddy";
//        String fullPath = basePath + "/" + filePath + "/" + fileName;
//        return FileCopyUtils.copyToByteArray(new File(fullPath));
//    }

    @ResponseBody
    @GetMapping("/display")
    public byte[] displayEventImg(@RequestParam("filePath") String filePath,
                                  @RequestParam("fileName") String fileName) throws IOException {
        // filePath가 절대 경로인지 확인하고 중복 방지
        Path fullPath = filePath.startsWith("C:") || filePath.startsWith("/") ?
                Paths.get(filePath, fileName) :
                Paths.get("C:/personalbuddy", filePath, fileName);

        return FileCopyUtils.copyToByteArray(fullPath.toFile());
    }

}
