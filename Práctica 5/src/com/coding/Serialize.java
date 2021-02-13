package com.coding;

import java.util.List;

public abstract class Serialize {
    abstract String toXML();
    abstract void fromXML(String xml);
    abstract String toJson();
    abstract void fromJson(String json);

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
