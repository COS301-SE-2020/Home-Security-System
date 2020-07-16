package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.ImageDTO;
import com.springboot.SpringBackend.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class ImageToImageDTO implements Converter<Image, ImageDTO> {
    @Override
    public ImageDTO convert(Image x) {
        ImageDTO dto = new ImageDTO();
        dto.setImageId(x.getImageId());
        dto.setImage(x.getImage());
        return dto;
    }
}
