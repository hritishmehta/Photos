package com.example.photosandroid;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Hritish Mehta
 * @author Likhit Krishnam
 * This class is responsible for functionalities regarding adding and deleting pictures.
 */
public class Album implements Serializable {
    /**
     * String instance that holds the album name
     */
    private String albumName;
    /**
     * Arraylist of photos which holds the photos in the album.
     */
    public ArrayList<Photo> photoArrayList;
    /**
     * serialVersionUID responsible for data persistence application.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Photo instance of the current photo the user is on.
     */
    public Photo currentPhoto;

    /**
     * Constructor
     * @param name
     */
    public Album(String name){
        this.albumName = name;
        photoArrayList = new ArrayList<Photo>();
        currentPhoto = null;
    }


    /**
     * Getter method to get the Arraylist of photos in the album
     * @return photoArrayList
     */
    public ArrayList<Photo> getPhotoArrayList(){
        return photoArrayList;
    }

    /**
     * Getter method to get the album name
     * @return albumName
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Setter method to set the album name
     * @param name
     */
    public void setAlbumName(String name){
        this.albumName = name;
    }

    /**
     * Getter method to get the size of the album
     * @return
     */
    public int getAlbumSize(){
        return photoArrayList.size();
    }

    /**
     * Setter method to set the Arraylist of photos to a new arraylist of photos.
     * @param photos
     */
    public void setPhotosArrayList(ArrayList<Photo> photos){
        photoArrayList = photos;
    }

    /**
     * Adds photo the photo array list
     * @param p
     */
    public void addPhoto(Photo p){
        if(canAddPhoto(p)){
            photoArrayList.add(p);
        }
    }

    /**
     * Checks if user can add photo to an album and makes sure there aren't duplicates
     * @param p
     * @return
     */
    public boolean canAddPhoto(Photo p){
        if(p==null){
            return false;
        }
        else{
            for(Photo photo: photoArrayList){
                if(photo.equals(p)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the date of the first photo added.
     * @return dateStr
     */
    public String getStartDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, M-d-y 'at' h:m:s a");
        Date date = null;
        String dateStr = "No Date";
        if (!photoArrayList.isEmpty()) {
            date = this.getPhotoArrayList().get(0).getDate();
            for (Photo photo: photoArrayList) {
                if (photo.getDate().before(date)) {
                    date = photo.getDate();
                }
            }
            dateStr = dateFormatter.format(date);
        }

        return dateStr;
    }

    /**
     * Gets the date of the last photo added in the album
     * @return dateStr
     */
    public String getLastDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, M-d-y 'at' h:m:s a");
        Date date = null;
        String dateStr = "No Date";
        if (!photoArrayList.isEmpty()) {
            date = this.getPhotoArrayList().get(0).getDate();
            for (Photo photo: this.getPhotoArrayList()) {
                if (photo.getDate().after(date)) {
                    date = photo.getDate();
                }
            }
            dateStr = dateFormatter.format(date);
        }

        return dateStr;
    }

    /**
     * Removes a photo from the photo array list
     * @param p
     */
    public void removePhoto(Photo p){
        if(p==null){
            return;
        }
        else{
            photoArrayList.remove(p);
        }
    }


    /**
     * toString method
     * @return
     */
    public String toString(){
        String start="";
        String end= "";
        if(getStartDate()!=null && getLastDate()!=null){
            start = getStartDate();
            end = getLastDate();
            return "Name: " + albumName + ", " + "Photo Range: " + start + " - " + end + ", Album Size:" + getAlbumSize();
        }
        else{
            return "Name: " + albumName + ", " + "Photo Range: " + "" + "Album Size: " + getAlbumSize();
        }


    }
}