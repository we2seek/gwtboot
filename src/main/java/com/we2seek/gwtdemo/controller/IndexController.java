package com.we2seek.gwtdemo.controller;

import com.we2seek.gwtdemo.model.Role;
import com.we2seek.gwtdemo.model.RoleRepository;
import com.we2seek.gwtdemo.model.User;
import com.we2seek.gwtdemo.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @ModelAttribute("allroles")
    public List<Role> constructUser() {
        return roleRepository.findAll();
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String showIndexPage(Model model) {
        logger.info("> showIndexPage");

        List<User> userList = userRepository.findAll();
        StringBuilder sb = new StringBuilder();
        for (User u : userList) {
            u.getRoles().size();
            sb.append(u);
        }
        logger.info("Users: " + sb.toString());
        model.addAttribute("usersList", userList);

        logger.info("< showIndexPage");
        return "user/list";
    }

    @RequestMapping(value = "/admin/user/new", method = RequestMethod.GET)
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/edit";
    }

    @RequestMapping(value = "/admin/user/{name}/edit", method = RequestMethod.GET)
    public String showEditUserForm(@PathVariable String name, Model model) {
        logger.info("> showEditUserForm");
        User user = userRepository.findByNameIgnoreCase(name);
        logger.info("user to be updated: " + user.toString());
        model.addAttribute("user", user);
        logger.info("< showEditUserForm");
        return "user/edit";
    }

    @RequestMapping(value = "/admin/user/save", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        logger.info("> saveUser");
        if (bindingResult.hasErrors()) {
            logger.info("got errors:" + bindingResult.toString());
            logger.info("< saveUser");
            return "user/edit";
        } else {
            logger.info("Before saving: " + user.toString());
            userRepository.save(user);
            logger.info("After saving: " + user.toString());
            logger.info("< saveUser");
            return "redirect:/";
        }
    }
}
