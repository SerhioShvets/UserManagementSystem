package com.management.system.controllers;

import com.management.system.models.Group;
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

import java.util.HashSet;
import java.util.Set;

@Controller
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupService userGroupService;

    /*list of groups*/
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public String getAllGroups(Model model) {
        model.addAttribute("group_list", groupRepository.findAll());
        return "group_all_groups";
    }

    /*list of users who belong to the group*/
    @RequestMapping(value = "/group/{groupname}/users", method = RequestMethod.GET)
    public String getGroupUsers(@PathVariable String groupname, Model model) {
        Group group = groupRepository.findByGroupname(groupname);
        model.addAttribute("groupname", groupname);
        model.addAttribute("group_users", group.getUser_list());
        return "group_users";
    }

    /*adding group GET*/
    @RequestMapping(value = "/group/create", method = RequestMethod.GET)
    public String createGroup(Model model) {
        model.addAttribute("new_group", new Group());
        return "group_create";
    }

    /*adding group POST*/
    @RequestMapping(value = "/group/create", method = RequestMethod.POST)
    public String createGroup(@ModelAttribute Group group) {
        groupRepository.save(group);
        return "redirect:/group/list";
    }


    /*editing group GET*/
    @RequestMapping(value = "/group/{groupname}/edit", method = RequestMethod.GET)
    public String editGroup(@PathVariable String groupname, Model model) {
        model.addAttribute("group", groupRepository.findByGroupname(groupname));
        return "group_edit";
    }

    /*editing group POST*/
    @RequestMapping(value = "/group/{groupname}/edit", method = RequestMethod.POST)
    public String editGroup(@ModelAttribute Group group) {
        groupRepository.save(group);
        return "redirect:/group/list";
    }

    /*deleting group*/
    @RequestMapping(value = "/group/{groupname}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable String groupname) {
        groupRepository.deleteByGroupname(groupname);
        return "redirect:/group/list";
    }

    /*deleting user from list of users*/
    @RequestMapping(value = "/group/{groupname}/users/{username}/delete", method = RequestMethod.GET)
    public String deleteUserFromUserList(@PathVariable String groupname, @PathVariable String username) {
        userGroupService.removeUserFromGroup(username, groupname);
        return "redirect:/group/" + groupname + "/users";
    }
}