package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.SessionModel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.sun.activation.registries.LogSupport.log;

@RestController
@RequestMapping("/sessions")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionService {
    private final List<SessionModel> tableData = new ArrayList<SessionModel>();
    SessionModel sessionStore;

    // Usage: get request, http://localhost:8080/springboot/sessions/addSession/{id}/{email}/{password}/{role}
     @CrossOrigin(origins = "http://localhost:4200/")
     @GetMapping(value = "/addSession/{id}/{email}/{password}/{role}")
     public String addSession(@PathVariable("id") String id, @PathVariable("email") String email,
                              @PathVariable("password") String password, @PathVariable("role") String role){

         sessionStore = new SessionModel(id, email, password, role);
         // System.out.println("teset1");
        tableData.add(sessionStore);
        System.out.println("Done");
        return "Added user to session";
    }
    // http://localhost:8080/springboot/sessions/getEmails
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/getEmails")
    public String[] getAllEmail(){
        String print[];
        print = new String[tableData.size()];
        System.out.println("List of emails: ");
        for (int i = 0; i < tableData.size(); i++){
            print[i] = tableData.get(i).getEmail();
            // System.out.println(print[i]);
        }

        return print;
    }
    // http://localhost:8080/springboot/sessions/getAllDetails
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/getAllDetails")
    public JSONArray details(){
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        for (SessionModel tableDatum : tableData) {
            obj.put("id", tableDatum.getID());
            obj.put("email", tableDatum.getEmail());
            obj.put("password", tableDatum.getPassword());
            obj.put("role", tableDatum.getRole());
            array.add(obj);
        }
        return array;
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/removeById/{id}")
    public String deleteSpecifiedSession(@PathVariable("id") String id){
        for (int i = 0;i < tableData.size(); i++){
            if (tableData.get(i).getID().equals(id)){
                tableData.remove(i);
            }
        }
        System.out.println("Deleted");
        return "User deleted";
    }
    public void deleteAllSession(){
        tableData.clear();
    }

}
