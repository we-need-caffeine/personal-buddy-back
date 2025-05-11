package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.ItemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files/api/*")
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
    // ex) MemberImg 이면
    // { MemberImg, 파일 경로, 파일 이름 }
    // 날짜 경로를 넣기 전에, MemberImg 인지, BoardImg 인지, AchievementImg 인지 등
    // 요청한 컨트롤러에 따른 폴더 구성을 다르게 해야한다.

    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    @PostMapping("files-upload")
    public ResponseEntity<Map<String, Object>> filesUpload(@RequestParam("imgFiles") List<MultipartFile> imgFiles, @RequestParam("dataType") String dataType) throws IOException {
        String rootPath = "C:/personalbuddy/images/" + dataType + "/" + getPath();
        Map<String, Object> response = new HashMap<>();

        List<String> uuids = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        File file = new File(rootPath);

        if(!file.exists()){
            file.mkdirs();
        }

        for(int i = 0; i < imgFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());

            fileNames.add(uuids.get(i) + "_" + imgFiles.get(i).getOriginalFilename());
            imgFiles.get(i).transferTo(new File(rootPath, uuids.get(i) + "_" + imgFiles.get(i).getOriginalFilename()));

            // 썸네일 저장
            if(imgFiles.get(i).getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuids.get(i) + "_" + imgFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(imgFiles.get(i).getInputStream(), out, 100, 100);
                out.close();
            }
        }
        // 파일의 경로는 동일하게 사용한다
        // 다만 파일의 이름은 여러개로 정해질 수 있어 List 로 전달해야함
        response.put("filePath", rootPath);
        response.put("fileNames", fileNames);
        response.put("message", "upload success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("file-upload")
    @ResponseBody
    public String upload(@RequestParam("imgFile") MultipartFile imgFile, @RequestParam("dataType") String dataType) throws IOException {
        String rootPath = "C:/personalbuddy/images/" + dataType;
        File file = new File(rootPath);

        // uuid 뒤에 memberImg -> memberId
        // uuid 뒤에 AchievementImg -> AchievementId
        // uuid 뒤에 ItemImg -> ItemId
        // uuid 뒤에 InterestDataImg -> InterestDataId

        if(!file.exists()){
            file.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        imgFile.transferTo(new File(rootPath, uuid + "_" + imgFile.getOriginalFilename()));

        // 썸네일
        if(imgFile.getContentType().startsWith("image")){
            FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuid + "_" + imgFile.getOriginalFilename()));
            Thumbnailator.createThumbnail(imgFile.getInputStream(), out, 100, 100);
            out.close();
        }
        return uuid;
    }
}
