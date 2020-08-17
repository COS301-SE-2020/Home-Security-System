package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.SessionModel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.sun.activation.registries.LogSupport.log;

@RestController
public class SessionService {
    private final List<SessionModel> tableData = new ArrayList<SessionModel>();
    SessionModel sessionStore;

    // Usage: get request, http://localhost:8080/springboot/addSession/{id}/{email}/{password}/{role}
     @GetMapping(value = "/addSession/{id}/{email}/{password}/{role}")
     public String addSession(@PathVariable("id") String id, @PathVariable("email") String email,
                              @PathVariable("password") String password, @PathVariable("role") String role){

         sessionStore = new SessionModel(id, email, password, role);
         // System.out.println("teset1");
        tableData.add(sessionStore);
        // System.out.println("Done");
        return "Added user to session";
    }
    // http://localhost:8080/springboot/getEmails
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
    // http://localhost:8080/springboot/getAllDetails
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

    public void deleteSpecifiedSession(String id){
        for (int i = 0;i < tableData.size(); i++){
            if (tableData.get(i).getID().equals(id)){
                tableData.remove(i);
            }
        }
    }
    public void deleteAllSession(){
        tableData.clear();
    }

}
