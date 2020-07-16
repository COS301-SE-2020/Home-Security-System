package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.VehicleDTO;
import com.springboot.SpringBackend.model.Vehicle;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class VehicleDTOToVehicle implements Converter<VehicleDTO, Vehicle>{
    @Override
    public Vehicle convert(VehicleDTO dto)
    {
        Vehicle x = new Vehicle();
        if (dto.getVehicleId() != null  && !StringUtils.isEmpty(dto.getVehicleId())) {
            x.setVehicleId(dto.getVehicleId());
        }
        x.setListed(dto.getListed());
        x.setCreated(dto.getCreated());
        x.setLicenseNo(dto.getLicenseNo());
        x.setPhoto(dto.getPhoto());
        return x;
    }
}
