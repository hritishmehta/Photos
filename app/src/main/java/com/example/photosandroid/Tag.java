package com.example.photosandroid;

import java.io.Serializable;
import java.util.*;
/**
 * @author Hritish Mehta
 * @author Likhit Krishnam
 * This class controls the actions of the album page
 */
public class Tag implements Serializable{
    /**
     * serialVersionUID for data persistence
     */
    private static final long serialVersionUID = 1L;
    /**
     * String instance of key of tag
     */
    public String key;
    /**
     * String array of values associated with key
     */
    public ArrayList<String> values = new ArrayList<String>();

    /**
     * Constructor
     * @param key
     * @param values
     */
    public Tag(String key, ArrayList<String> values){
        this.key = key;
        this.values = values;
    }

    /**
     * Getter method for the key attribute
     * @return key
     */
    public String getKey(){
        return key;
    }

    /**
     * Getter method for the string array of values
     * @return
     */
    public ArrayList<String> getValues(){
        return values;
    }

    /**
     * Setter method for key
     * @param key
     */
    public void setKey(String key){
        this.key = key;
    }

    /**
     * Setter method for String array of values
     * @param values
     */
    public void setValues(ArrayList<String> values){
        this.values = values;
    }

    /**
     * Equals method implementation overriding object equals
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(!(obj instanceof Tag))
            return false;

        Tag other = (Tag) obj;

        if(!(other.getKey()).equals(this.key)){
            return false;
        }
        for(String s: values){
            if(!other.getValues().contains(s)){
                return false;
            }
        }
        return true;
    }

    /**
     * toString implementation
     * @return
     */
    public String toString(){
        String result = "";
        result += "Key: " + this.key + " Values: ";
        for(String s: values){
            result += s + ", ";
        }
        return result;
    }
}
