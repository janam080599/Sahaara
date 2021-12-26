package com.example.speech;

import java.io.Serializable;

public class Person implements Serializable {
      private String name,number;

    Person(){

    }

    public Person(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
