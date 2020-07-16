package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.ImageDTO;
import com.springboot.SpringBackend.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

@Component
public class ImageDTOToImage implements Converter<ImageDTO, Image> {
    @Override
    public Image convert(ImageDTO dto) {
        Image x = new Image();
        if (dto.getImageId() != null  && !StringUtils.isEmpty(dto.getImageId())) {
            x.setImageId(dto.getImageId());
        }
        x.setImage(dto.getImage());
        return x;
    }
}
