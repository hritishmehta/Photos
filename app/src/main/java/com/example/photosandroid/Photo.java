package com.example.photosandroid;

import android.net.Uri;

import java.io.Serializable;
import java.net.URI;
import java.util.*;
/**
 * @author Hritish Mehta
 * @author Likhit Krishnam
 * This class controls the editing of photos
 */
public class Photo implements Serializable{

    /**
     * String that holds the name of the photo
     */
    private String photoName;

    private Uri image;

    /**
     * serialVersionUID for data persistence application
     */
    private static final long serialVersionUID = 1L;



    private ArrayList<Tag> tags;

    /**
     * Constructor
     */
    public Photo() {
        this.image = null;
        this.photoName = null;
    }


    public Photo(Uri uri, String name){
        this.image = uri;
        this.photoName = name;

        tags = new ArrayList<Tag>();

    }

    /**
     * Getter method for photo name
     * @return photoName
     */
    public String getPhotoName(){
        return photoName;
    }

    /**
     * Setter method to set the photo name
     * @param photoName
     */
    public void setPhotoName(String photoName){
        this.photoName = photoName;
    }

    public Uri getImage(){
        return image;
    }

    public void setImage(Uri image){
        this.image = image;
    }

    /**
     * Getter method for the tag list
     * @return tags
     */
    public ArrayList<Tag> getTags(){
        return tags;
    }


    /**
     * Adds a tag to the tag list
     * @param tagAddition
     */
    public void addTag(Tag tagAddition){
        if(!areTagsSame(tagAddition)){
            tags.add(tagAddition);
        }
    }

    /**
     * Removes the tag from a tag list
     * @param tagRemove
     */
    public void removeTag(Tag tagRemove){
        for(Tag t: this.getTags()){
            if(t.equals(tagRemove)){
                tags.remove(tagRemove);
            }
        }
    }

    /**
     * Checks if two tags are the same
     * @param tagAddition
     * @return boolean
     */
    public boolean areTagsSame(Tag tagAddition){
        for(Tag tag: tags){
            if(tag.equals(tagAddition)){
                return true;
            }
        }
        return false;
    }


    /**
     * Deletes a tag from tag list
     * @param t
     */
    public void deleteTag(Tag t){
        tags.remove(t);
    }

    /**
     * Checks to see if a tag already exists or not
     * @param t
     * @param temp
     * @return
     */
    public boolean tagExists(Tag t, Photo temp){
        for(Tag tempTag: temp.getTags()){
            if(t.equals(tempTag)){
                return true;
            }
        }
        return false;
    }

    /**
     * Equals implementation overriding object equals.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(!(obj instanceof Photo))
            return false;

        Photo other = (Photo) obj;

        if(!this.getPhotoName().equals(other.getPhotoName())){
            return false;
        }
        //for each tag in other, check if it exists in this
        //for each tag in this check if it exists in other
        //basically: if a is a subset of b and b is a subset of a, a==b
        for(Tag t: tags){
            if(!tagExists(t, other)){
                return false;
            }
        }
        for(Tag t: other.getTags()){
            if(!tagExists(t, this)){
                return false;
            }
        }


        //actual picture file has to be the same
        if(!this.getImage().equals(other.getImage())){
            return false;
        }


        return true;
    }

    /**
     * toString implementation for tag
     * @return
     */
    public String tagToString(){
        if(!this.getTags().isEmpty()){
            String result = "";
            for(Tag t: this.getTags()){
                result += "Key: " + t.getKey() + " Values: ";
                for(String value: t.getValues()){
                    result +=  value + ", ";
                }
                result += "\n";
            }
            return result;
        }
        return "";
    }





}
