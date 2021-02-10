package com.coding;

import java.util.List;

public abstract class Serialize {
    abstract String toXML();
    abstract String fromXML();
    abstract String toJson();
    abstract String fromJson();

    public String createTag(String name, List<String> tags){
        String tagString = "";
        for(String tag : tags){
            tagString += tag;
        }
        return createTag(name, tagString);
    }

    public String createTag(String name, String content){
        return "<"+name+">"+content+"</"+name+">\n";
    }

}
