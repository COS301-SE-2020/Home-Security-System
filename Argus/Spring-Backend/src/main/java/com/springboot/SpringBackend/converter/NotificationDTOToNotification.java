package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.NotificationDTO;
import com.springboot.SpringBackend.model.Notification;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class NotificationDTOToNotification implements Converter<NotificationDTO, Notification> {
    @Override
    public Notification convert(NotificationDTO dto) {
        Notification x = new Notification();
        if (dto.getNotificationId() != null  && !StringUtils.isEmpty(dto.getNotificationId())) {
            x.setNotificationId(dto.getNotificationId());
        }
        x.setMessage(dto.getMessage());
        x.setOnDate(dto.getOnDate());
        x.setAtTime(dto.getAtTime());
        x.setImage(dto.getImage());
        x.setUser(dto.getUser());
        return x;
    }
}
