package com.example.apiroy.Configs;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary("cloudinary://717436577626688:kH1K6CFHeeOCP0DPZVn9yfurYvo@dlbp1jaju");
    }
}

