package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.converter.PersonDTOToPerson;
import com.springboot.SpringBackend.dto.PersonDTO;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepo repo;
    //private PersonDTOToPerson dtoToPerson;

    @Autowired
    public PersonServiceImpl(PersonRepo psnRepo)
    {
        this.repo = psnRepo;
        //this.dtoToPerson = dto;
    }

    @Override
    public List<Person> listAllPeople() { return (List<Person>) this.repo.findAll(); }

    @Override
    public Optional<Person> getPersonByID(Long id) { return this.repo.findById(id); }

    @Override
    public Person createPerson(Person x) { return this.repo.save(x); }

    //@Override
    //public Person createPersonForm(PersonDTO dto) { return this.createPerson(dtoToPerson.convert(dto)); }

    @Override
    public Person updatePerson(Person x) { return this.repo.save(x); }

    //@Override
    //public Person updatePersonForm(PersonDTO dto) { return this.updatePerson(dtoToPerson.convert(dto)); }

    @Override
    public void deletePerson(Person x) { this.repo.delete(x); }

    @Override
    public void deletePersonById(Long id) { this.repo.deleteById(id); }

    /*
    private static PersonDAO dao;

    @Autowired
    public PersonService() {
		dao = new PersonDAO();
	}

	public PersonDAO getPersonDao() {
		return dao;
	}

    @Override
    public List<Person> listAllPeople() {
		List<Person> list = dao.findAllPeople();
		return list;
	}

    @Override
    public Person getPersonById(Long id) {
		Person x = dao.getPeopleById(id);
		return x;
	}

    @Override
    public Person createPerson(Person x) {
		dao.createPeople(x);
		return x;
	}

    @Override
    public Person updatePerson(Person x) {
		dao.updatePeople(x);
		return x;
	}

    @Override
    public void delete(Person x) {
		dao.deletePeople(x);
    }

    @Override
    public void deletePersonById(Long id) {
		dao.deletePeopleById(id);
	}
    */
}

