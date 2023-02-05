package com.example.vacantion.domain;

public class Client extends Entity<Long>{

    private String name;

    private Integer fidelityGrade;

    private Integer age;

    private Hobbies hobby;

    public Client(String name, Integer fidelityGrade, Integer age, Hobbies hobby) {
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.age = age;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public Integer getFidelityGrade() {
        return fidelityGrade;
    }

    public Integer getAge() {
        return age;
    }

    public Hobbies getHobby() {
        return hobby;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", fidelityGrade=" + fidelityGrade +
                ", age=" + age +
                ", hobby=" + hobby +
                '}';
    }
}
