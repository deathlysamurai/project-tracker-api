package com.deathlysamurai.projecttrackerapi.controller.open;

import com.deathlysamurai.projecttrackerapi.model.Credentials;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class OpenController {
    @PostMapping("/checkCredentials")
    public ArrayList<String> checkCredentials(@RequestBody Credentials credentials) {
        ArrayList<String> response = new ArrayList<String>();
        Boolean error = false;
        if(!credentials.getUsername().equals(System.getenv("username"))) {
            error = true;
            response.add("USERNAME_INCORRECT");
        }
        if(!credentials.getPassword().equals(System.getenv("password"))) {
            error = true;
            response.add("PASSWORD_INCORRECT");
        }
        if(!error) {
            response.add("OK");
        }
        return response;
    }
}