package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.converter.PersonToPersonDTO;
import com.springboot.SpringBackend.dto.PersonDTO;
import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService service;
    private final ModelMapper modelMapper;
    //private final PersonToPersonDTO personToDTO;

    @Autowired
    public PersonController(PersonService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
        //this.personToDTO = personToDTO;
    }

    @GetMapping("/persons")
    public List<PersonDTO> getAllPersons() {
        List<Person> list = service.listAllPeople();
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDTO> getProfileById(@PathVariable(value = "id") Long id) {
        Person x = service.getPersonByID(id).get();
        return ResponseEntity.ok().body(convertToDto(x));
    }

    @PostMapping("/persons")
    public Person makePerson(@Valid @RequestBody PersonDTO dto) {
        return service.createPerson(convertToEntity(dto));
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> editPerson(@PathVariable(value = "id") Long pid,
                                                   @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person x = service.getPersonByID(pid)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + pid));

        x.setPersonId(personDetails.getPersonId());
        x.setName(personDetails.getName());
        x.setSurname(personDetails.getSurname());
        x.setListed(personDetails.getListed());
        x.setDate(personDetails.getDate());
        x.setImage(personDetails.getImage());
        final Person updatedPerson = service.updatePerson(personDetails);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/persons/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long pid) throws ResourceNotFoundException {
        Person x = service.getPersonByID(pid)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + pid));

        service.deletePerson(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public PersonDTO convertToDto(Person x) {
        PersonDTO dto = modelMapper.map(x, PersonDTO.class);
        return dto;
    }

    public Person convertToEntity(PersonDTO dto) {
        Person x = modelMapper.map(dto, Person.class);
        return x;
    }
}

//alternative way (reference)
/*
    private ProductService productService;
    private ProductToProductForm productToProductForm;

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/product/list";
    }

    @RequestMapping({"/product/list", "/product"})
    public String listProducts(Model model){
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model){
        model.addAttribute("product", productService.getById(Long.valueOf(id)));
        return "product/show";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Product product = productService.getById(Long.valueOf(id));
        ProductForm productForm = productToProductForm.convert(product);

        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "product/productform";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id){
        productService.delete(Long.valueOf(id));
        return "redirect:/product/list";
    }
*/
