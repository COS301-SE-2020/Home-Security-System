package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.model.SessionModel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sessions")
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://sigma-argus.herokuapp.com")
public class SessionController {
    private final List<SessionModel> tableData = new ArrayList<>();
    SessionModel sessionStore;

    // http://localhost:8080/sessions/addSession/
    @PostMapping(value = "/addSession")
    public String addSession(@Valid @RequestBody SessionModel sm) {
        sessionStore = new SessionModel(sm.getId(), sm.getRole(), sm.getEmail());
        tableData.add(sessionStore);
        return "Added user to session";
    }
    /*@PostMapping(value = "/addSession/{id}/{email}/{password}/{role}")
    public String addSession(@PathVariable("id") String id, @PathVariable("email") String email,
                          @PathVariable("password") String password, @PathVariable("role") String role) {
        sessionStore = new SessionModel(id, email, password, role);
        tableData.add(sessionStore);
        return "Added user to session";
    }*/
    // http://localhost:8080/sessions/getEmails/
    @GetMapping(value = "/getAllEmails")
    public String[] getAllEmails() {
        String print[];
        print = new String[tableData.size()];
        System.out.println("List of emails: ");
        for (int i = 0; i < tableData.size(); i++) {
            print[i] = tableData.get(i).getEmail();
        }

        return print;
    }

    // http://localhost:8080/sessions/getSessionDetails/
    @GetMapping(value = "/getSessionDetails")
    public JSONArray getSessionDetails() {
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();

        for (SessionModel tableDatum : tableData) {
            obj.put("id", tableDatum.getId());
            obj.put("role", tableDatum.getRole());
            obj.put("email", tableDatum.getEmail());
            array.add(obj);
        }

        return array;
    }


    @DeleteMapping(value = "/deleteSessionId/{id}")
    public String deleteSessionId(@PathVariable("id") String id) {
        for (int i = 0;i < tableData.size(); i++) {
            if (tableData.get(i).getId().equals(id)) {
                tableData.remove(i);
            }
        }

        return "User session deleted";
    }

    public void deleteAllSessions(){
        tableData.clear();
    }

}
