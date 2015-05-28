package com.lite.jqgrid4j.test;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Role{

    private Integer id;

    private String name;

    private Integer type;

    private String head;

    private Integer level;

    private Integer listOrder;

    private Boolean enabled;

    private Date createDate;

    private String description;

    private Role parent;

    private Set<Role> children = new LinkedHashSet<Role>();

    public Role(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }

    public Set<Role> getChildren() {
        return children;
    }

    public void setChildren(Set<Role> children) {
        this.children = children;
    }

}
