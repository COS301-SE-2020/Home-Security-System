package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.dao.PersonVehicleDAOImpl;
import com.springboot.SpringBackend.model.PersonVehicle;
import com.springboot.SpringBackend.repository.PersonVehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("PersonVehicleService")
@Transactional
public class PersonVehicleServiceImpl implements PersonVehicleService{
    private final PersonVehicleRepo repo;

    @Autowired
    public PersonVehicleServiceImpl(PersonVehicleRepo pvRepo)
    {
        this.repo = pvRepo;
    }

    @Override
    public List<PersonVehicle> listAllPV() { return (List<PersonVehicle>) this.repo.findAll(); }

    /*
    private static PersonVehicleDAO dao;

    @Autowired
    public PersonVehicleService() {
		dao = new PersonVehicleDAO();
	}

	public PersonVehicleDAO getPersonVehicleDao() {
		return dao;
	}

    @Override
    public List<PersonVehicle> listAllPV() {
		List<PersonVehicle> list = dao.findAllPV();
		return list;
	}
    */
}
