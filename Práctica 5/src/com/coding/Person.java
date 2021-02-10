package com.coding;

import java.util.ArrayList;
import java.util.List;

public class Person extends Serialize{
    public String name;
    public int age;
    public String gender;
    public Boolean status;

    public Person(){}

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        status = true;
    }

    public Person(String name, int age, String gender, Boolean status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
    }

    public void test(String xml){
        String pattern = "<edad[^>]*>(.*?)</edad>";
        String updated = xml.replaceAll(pattern, "$2");

        System.out.println(updated);
    }

    @Override
    public String toXML() {

        List<String> tags = new ArrayList<String>();

        tags.add( createTag("nombre", name) );
        tags.add( createTag("edad", Integer.toString(age)) );
        tags.add( createTag("genero", gender) );
        tags.add( createTag("status", status.toString()) );

        return super.createTag("Persona", tags);
    }

    @Override
    public String fromXML() {
        return null;
    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public String fromJson() {
        return null;
    }
}
