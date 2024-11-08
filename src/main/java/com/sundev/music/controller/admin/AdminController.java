package com.sundev.music.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sundev.music.model.Category;
import com.sundev.music.model.Users;
import com.sundev.music.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;
 
    @GetMapping("/login")
    public String adminLogin(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }

    @GetMapping("/table")
    public String table(Model model) {
        model.addAttribute("activePage", "tables");
        return "table";
    }

    @GetMapping("/charts")
    public String chart(Model model) {
        model.addAttribute("activePage", "charts");
        return "charts";
    }

    @GetMapping("/users")
    public String user(Model model) {
        List<Users> users = service.getUsers();
        model.addAttribute("activePage", "users");
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("activePage", "category");
        return "category";
    }

    @PreAuthorize("hasAuthority('EDIT') or hasAuthority('WRITE')")
    @GetMapping("/add-category")
    public String addCategory(Model model) {
        model.addAttribute("activePage", "category");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(auth -> {
            System.out.println("Authenticated authority: " + auth.getAuthority());
        });
        return "add_category";
    }


    @PostMapping("/add-category")
    public String postCategory(@RequestBody Category entity) {
        List<Users> users = service.getUsers();
        return "redirect:/add-category";
    }
    
    
}
