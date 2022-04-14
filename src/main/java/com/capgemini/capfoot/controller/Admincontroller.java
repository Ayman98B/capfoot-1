package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.repository.AdminRepository;
import com.capgemini.capfoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class Admincontroller {

        @Autowired
        AdminService adminService;
        @Autowired
        AdminRepository adminRepository;

        @PostMapping("/add/admins")
        public void ajouterAdmin(@RequestBody Admin admin){
            adminService.add(admin);
        }

        @GetMapping("/admins/{id}")
        public Optional<Admin> trouver(@PathVariable("id") Long id){
            return adminRepository.findById(id);
        }

        @GetMapping("/admins")
         public List<Admin> finaAll(@PathVariable("id") Long id){
        return adminRepository.findAll();
    }


        @RequestMapping(value = "/admin_auth", method = RequestMethod.GET)
        public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
        }

        @GetMapping("/test")
        public ResponseEntity<Object> getUsers(@RequestHeader HttpHeaders httpHeaders) {return ResponseEntity.ok().body("Hi user!");}

        @GetMapping("/logout")
        public String logout(HttpServletRequest request) throws ServletException {
            request.logout();
            return "Au revoir";
        }

            /*//@GetMapping("/admin_auth/test")
            public String function() {
                return "admin_auth";*/
}
