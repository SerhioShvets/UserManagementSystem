package com.management.system.controllers;


import com.management.system.models.User;
import com.management.system.repositories.GroupRepository;
import com.management.system.repositories.UserRepository;
import com.management.system.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupService userGroupService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        return "home";
    }

    /*list of users*/
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("user_list", userRepository.findAll());
        return "user_all_users";
    }

    /*list of user groups to which the user belongs*/
    @RequestMapping(value = "/user/{username}/groups", method = RequestMethod.GET)
    public String getUserGroups(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("user_groups", user.getUser_group());
        return "user_groups";
    }

    /*adding user account GET*/
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("new_user", new User());
        model.addAttribute("group_list", groupRepository.findAll());
        return "user_create";
    }

    /*adding new user account POST*/
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:list";
    }

    /*adding group to users list of group GET*/
    @RequestMapping(value = "/user/{username}/groups/add", method = RequestMethod.GET)
    public String addGroup(@PathVariable String username, Model model) {
        model.addAttribute("group_list", groupRepository.findAll());
        model.addAttribute("user", userRepository.findByUsername(username));
        return "user_add_group";
    }

    /*adding group to users list of group POST*/
    @RequestMapping(value = "/user/{username}/groups/add", method = RequestMethod.POST)
    public String addGroup(@PathVariable String username, @ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/user/"+username+"/groups";
    }


    /*editing user account GET*/
    @RequestMapping(value = "/user/{username}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userRepository.findByUsername(username));
        return "user_edit";
    }

    /*editing user account POST*/
    @RequestMapping(value = "/user/{username}/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/user/list";
    }

    /*deleting user account*/
    @RequestMapping(value = "/user/{username}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userRepository.removeUserByUsername(username);
        return "redirect:/user/list";
    }

    /*deleting user group from list of groups*/
    @RequestMapping(value = "/user/{username}/groups/{groupname}/delete", method = RequestMethod.GET)
    public String deleteGroupFromUserGroups(@PathVariable String username, @PathVariable String groupname) {
        userGroupService.removeUserFromGroup(username, groupname);
        return "redirect:/user/" + username + "/groups";
    }
}
