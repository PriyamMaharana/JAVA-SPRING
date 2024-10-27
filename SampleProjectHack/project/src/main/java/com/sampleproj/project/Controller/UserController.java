package com.sampleproj.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sampleproj.project.Exception.UserNotFoundException;
import com.sampleproj.project.Model.User;
import com.sampleproj.project.Repository.UserRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepo repo;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("/user/{id}")
    User getUser(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
    
    @PostMapping("/user/add")
    public String addUser(@RequestBody User newuser) {
        repo.save(newuser);
        return "Data Added Successfully!";
    }

    @PutMapping("/user/update/{id}")
    public String editUser(@PathVariable Long id, @RequestBody User updateUser) {
        
        return repo.findById(id).map(user-> {
            user.setName(updateUser.getName());
            user.setUsername(updateUser.getUsername());
            user.setEmail(updateUser.getEmail());
            repo.save(user);
            return "Data Updated Successfully!";
        }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/delete/{id}")
    public String delUser(@PathVariable Long id) {
        if(!repo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repo.deleteById(id);
        return "User Deleted with ID: "+id;
    }
    
    
}
