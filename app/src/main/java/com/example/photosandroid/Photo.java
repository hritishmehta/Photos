package com.example.photosandroid;

import java.io.File;
import java.io.Serializable;
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

    /**
     * serialVersionUID for data persistence application
     */
    private static final long serialVersionUID = 1L;
    /**
     * String instance that holds caption of the photo
     */
    private String caption;

    /**
     * String instance holding file path of photo
     */
    private String filePath;

    /**
     * File instance of the image itself
     */
    private File picture;

    /**
     * Calendar instance
     */
    private Calendar calendar;
    /**
     * Date instance
     */
    private Date date;
    /**
     * Arraylist of tags for the photo
     */
    private ArrayList<Tag> tags;

    /**
     * Constructor
     */
    public Photo() {
        this.picture = null;
        this.filePath = null;
        this.photoName = null;
        this.caption = null;

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        tags = new ArrayList<Tag>();
    }

    /**
     * Constructor
     * @param file
     * @param name
     */
    public Photo(File file, String name){
        this.picture = file;
        this.photoName = name;
        this.calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND, 0);
        this.date = calendar.getTime();

        this.filePath = picture.getAbsolutePath();
        this.caption = null;
        tags = new ArrayList<Tag>();

    }

    /**
     * Constructor
     * @param filePath
     * @param name
     */
    public Photo(String filePath, String name){
        File imageFile = new File(filePath);
        this.picture = imageFile;
        this.photoName = name;
        tags = new ArrayList<Tag>();

        calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND, 0);
        this.date = calendar.getTime();

        this.filePath = picture.getAbsolutePath();
        this.caption = null;
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

    /**
     * Getter method for the caption of photo
     * @return caption
     */
    public String getCaption(){
        return caption;
    }

    /**
     * Setter method for the caption of the photo
     * @param caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Getter method for the file path of the photo
     * @return filePath
     */
    public String getFilePath(){
        return filePath;
    }

    /**
     * Setter method for the file path of the photo
     * @param fp
     */
    public void setFilePath(String fp){
        filePath = fp;
    }

    /**
     * Getter method for the File picture
     * @return picture
     */
    public File getPicture(){
        return picture;
    }

    /**
     * Setter method for the picture
     * @param pic
     */
    public void setPicture(File pic){
        picture = pic;
    }

    /**
     * Getter method for the Date instance
     * @return date
     */
    public Date getDate(){
        return date;
    }

    /**
     * Setter method for the Date instance
     * @param date
     */
    public void setDate(Date date){
        this.date = date;
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

        //caption of both pitures has to be the same
        if(!this.getCaption().equals(other.getCaption())){
            return false;
        }

        //actual picture file has to be the same
        if(!this.getPicture().equals(other.getPicture())){
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
