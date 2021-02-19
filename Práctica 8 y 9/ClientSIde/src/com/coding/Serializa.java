package com.coding;

import java.util.List;

public abstract class Serializa {
    abstract String toXML();
    abstract String toJson();

    public String createTag(String name, List<String> tags){
        String tagString = "";
        for(String tag : tags){
            tagString += tag;
        }
        return createTag("persona",tagString);
    }

    public String createTag(String name, String content){
        return "<"+name+">"+content+"</"+name+">";
    }

}
