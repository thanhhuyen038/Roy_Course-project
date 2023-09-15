package com.example.apiroy.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apiroy.Service.CoverImgService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CoverImgServiceImpl implements CoverImgService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile imageFile) throws IOException {
        System.out.println("[DEBUT] - START UPLOAD IMAGE");
        Map<?, ?> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();
    }
}
