package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.converter.VehicleDTOToVehicle;
import com.springboot.SpringBackend.dto.VehicleDTO;
import com.springboot.SpringBackend.model.Vehicle;
import com.springboot.SpringBackend.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("VehicleService")
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepo repo;
    //private VehicleDTOToVehicle dtoToVehicle;

    @Autowired
    public VehicleServiceImpl(VehicleRepo vRepo)
    {
        this.repo = vRepo;
        //this.dtoToVehicle = dto;
    }

    @Override
    public List<Vehicle> listAllVehicles() {
        return (List<Vehicle>) this.repo.findAll();
    }

    @Override
    public Optional<Vehicle> getVehicleByID(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Vehicle createVehicle(Vehicle x) {
        return this.repo.save(x);
    }

    //@Override
    //public Vehicle createVehicleForm(VehicleDTO dto) { return this.createVehicle(dtoToVehicle.convert(dto)); }

    @Override
    public Vehicle updateVehicle(Vehicle x) {
        return this.repo.save(x);
    }

    //@Override
    //public Vehicle updateVehicleForm(VehicleDTO dto) { return this.updateVehicle(dtoToVehicle.convert(dto)); }

    @Override
    public void deleteVehicle(Vehicle x) {
        this.repo.delete(x);
    }

    @Override
    public void deleteVehicleById(Long id) {
        this.repo.deleteById(id);
    }

    /*
    private static VehicleDAO dao;

    public VehicleService() {
		dao = new VehicleDAO();
	}

    @Autowired
	public VehicleDAOImpl getVehicleDao() {
		return dao;
	}

    @Override
    public List<Vehicle> listAllEvents() {
		List<Vehicle> list = dao.findAllMotors();
		return list;
	}

    @Override
    public Vehicle getVehicleById(Long id) {
		Vehicle x = dao.getMotorById(id);
		return x;
	}

    @Override
    public Vehicle createVehicle(Vehicle x) {
		dao.createMotor(x);
		return x;
	}

    @Override
    public Vehicle updateVehicle(Vehicle x) {
		dao.updateMotor(x);
		return x;
	}

    @Override
    public void deleteVehicle(Vehicle x) {
		dao.deleteMotor(x);
    }

    @Override
    public void deleteVehicleById(Long id) {
		dao.deleteMotorById(id);
	}
    */
}
