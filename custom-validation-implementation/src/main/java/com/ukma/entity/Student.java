package com.ukma.entity;

public class Student {

    private String name;
    private String phone;
    private String email;
    private String faculty;

    public Student(
            String name,
            String phone,
            String email,
            String faculty
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getFaculty() {
        return faculty;
    }
}
