package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.VehicleDTO;
import com.springboot.SpringBackend.model.Vehicle;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VehicleToVehicleDTO implements Converter<Vehicle, VehicleDTO> {
    @Override
    public VehicleDTO convert(Vehicle x) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(x.getVehicleId());
        dto.setListed(x.getListed());
        dto.setCreated(x.getCreated());
        dto.setLicenseNo(x.getLicenseNo());
        dto.setPhoto(x.getPhoto());
        return dto;
    }
}
