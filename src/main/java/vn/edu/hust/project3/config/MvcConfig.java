package vn.edu.hust.project3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path phoneUploadDir = Paths.get("./phone-images");
        String phoneUploadPath = phoneUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/phone-images/**").addResourceLocations("file:/" + phoneUploadPath + "/");
    }
}
