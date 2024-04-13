package org.app.backend.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImgService {
    @Value("${file.upload.directory}")
    private String uploadDirectory;
    public byte[] image(String fileName) throws IOException {
        return Files.readAllBytes(new File(Paths.get("").toAbsolutePath()+"/"+Paths.get(uploadDirectory)+"/"+fileName).toPath());
    }
    public Boolean deleteimage(String fileName)  {
        File file = new File(Paths.get("").toAbsolutePath()+"/"+Paths.get(uploadDirectory)+"/"+fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
    public String addimage(MultipartFile file,String Folder) {
        if (file.isEmpty()) {
            return "";
        }
        try {
            String originalFileName = file.getOriginalFilename(); // Get original file name
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = Folder+"/"+UUID.randomUUID() + fileExtension; // Generate random UUID with original file extension
            String filePath = Paths.get("").toAbsolutePath()+"/"+Paths.get(uploadDirectory)+"/" + fileName; // Construct file path using FOLDER_PATH
            File destFile = new File(filePath);
            file.transferTo(destFile);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
}
}
