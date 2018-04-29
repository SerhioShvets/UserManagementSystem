package com.management.system.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    private Long id_group;
    private String groupname;
    private Set<User> user_list;

    public Group() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId_group() {
        return id_group;
    }


    public void setId_group(Long id_group) {
        this.id_group = id_group;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @ManyToMany(mappedBy = "user_group")
    public Set<User> getUser_list() {
        return user_list;
    }

    public void setUser_list(Set<User> user_list) {
        this.user_list = user_list;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id_group=" + id_group +
                ", groupname='" + groupname;

    }
}
