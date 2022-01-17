package com.library.entity;

import com.library.enums.Role;

public class User {
    private Integer id;
    private String login;
    private String password;
    private String surnameRu;
    private String surnameEn;
    private String nameRu;
    private String nameEn;
    private String patronymicRu;
    private String patronymicEn;
    private Role role;
    private boolean active;

    public User() {
    }

    public User(String login, String password, Role role, boolean active) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSurnameRu() {
        return surnameRu;
    }

    public void setSurnameRu(String surnameRu) {
        this.surnameRu = surnameRu;
    }

    public String getSurnameEn() {
        return surnameEn;
    }

    public void setSurnameEn(String surnameEn) {
        this.surnameEn = surnameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPatronymicRu() {
        return patronymicRu;
    }

    public void setPatronymicRu(String patronymicRu) {
        this.patronymicRu = patronymicRu;
    }

    public String getPatronymicEn() {
        return patronymicEn;
    }

    public void setPatronymicEn(String patronymicEn) {
        this.patronymicEn = patronymicEn;
    }

    public String getRoleString() {
        switch (role) {
            case LIBRARIAN:
                return "LIBRARIAN";
            case ADMINISTRATOR:
                return "ADMINISTRATOR";
            default:
                return "VIEWER";
        }
    }
////////////////////////////*********************
    public String getActiveString() {
        if (active) return "true";
        else return "false";
    }

    public String getFullName(String lang) {
        if (lang.equals("ru")) {
            return surnameRu + " " + nameRu + " " + patronymicRu;
        } else {
            return surnameEn + " " + nameEn + " " + patronymicEn;
        }
    }
}
