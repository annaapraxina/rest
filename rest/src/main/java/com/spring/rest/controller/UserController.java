package com.spring.rest.controller;

import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import com.spring.rest.service.RoleService;
import com.spring.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping(value = "/admin")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping(value = "/admin/edit")
    public String updateUser(@ModelAttribute User user, @RequestParam("updId") Long id, @RequestParam("rolesId") List<Long> rolesId) {
        Set<Role> roles = roleService.getRoleById(rolesId);
        userService.updateUser(user, id, roles);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/add")
    public String addNewUser(@ModelAttribute User user, @RequestParam("rolesId") List<Long> rolesId) {
        Set<Role> roles = roleService.getRoleById(rolesId);
        userService.addUser(user, roles);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/remove")
    public String deleteUser(@RequestParam("delId") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}