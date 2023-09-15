package com.example.apiroy.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CoverImgService {
    String uploadImage(MultipartFile imageFile) throws IOException;
}
