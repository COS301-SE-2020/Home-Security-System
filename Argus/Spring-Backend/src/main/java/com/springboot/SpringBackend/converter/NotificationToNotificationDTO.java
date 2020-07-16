package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.NotificationDTO;
import com.springboot.SpringBackend.model.Notification;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotificationToNotificationDTO implements Converter<Notification,NotificationDTO> {
    @Override
    public NotificationDTO convert(Notification x) {
        NotificationDTO dto = new NotificationDTO();
        dto.setNotificationId(x.getNotificationId());
        dto.setMessage(x.getMessage());
        dto.setOnDate(x.getOnDate());
        dto.setAtTime(x.getAtTime());
        dto.setImage(x.getImage());
        dto.setUser(x.getUser());
        return dto;
    }
}
