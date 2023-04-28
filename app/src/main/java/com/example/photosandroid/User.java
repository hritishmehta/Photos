package com.example.photosandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Hritish Mehta
 * @author Likhit Krishnam
 * This class controls the user functionalities such as adding and deleting albums
 */
public class User implements Serializable {
    /**
     * serialVersionUID for data persistence application
     */
    private static final long serialVersionUID = 1L;
    /**
     * String instance of the username
     */
    private String username;
    /**
     * Arraylist of albums of the user
     */
    private ArrayList<Album> albumList;

    /**
     * Album instance of the current album of the user
     */
    private Album currAlbum;

    /**
     * Constructor
     * @param username
     */
    public User(String username){
        this.username = username;
        albumList = new ArrayList<Album>();
        currAlbum = null;
    }

    /**
     * Getter method for the currAlbum
     * @return currAlbum
     */
    public Album getCurrAlbum(){
        return currAlbum;
    }

    /**
     * Setter method for the currAlbum
     * @param a
     */
    public void setCurrAlbum(Album a){
        currAlbum = a;
    }

    /**
     * Getter method for the user's username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Getter method for the user's album list
     * @return albumList
     */
    public ArrayList<Album> getAllAlbums(){
        return albumList;
    }

    /**
     * Getter method for the album in an array list based on index
     * @param index
     * @return
     */
    public Album getAlbum(int index){
        return albumList.get(index);
    }

    /**
     * Getter method for an album based on the name of the album
     * @param name
     * @return
     */
    public Album getAlbum(String name){
        for(int i = 0; i<albumList.size(); i++){
            if(albumList.get(i).getAlbumName().equals(name)) {
                return albumList.get(i);
            }
        }
        return null;
    }

    /**
     * Adds album to album list
     * @param a
     */
    public void addAlbum(Album a){
        if(a==null){
            return;
        }
        else{
            albumList.add(a);
        }
    }

    /**
     * Setter method for the username
     * @param name
     */
    public void setUsername(String name) {
        this.username = name;
    }

    /**
     * Equals implementation overriding Object class's implementation
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        User anotherUser = (User) object;
        if(this.username.equals(anotherUser.getUsername())){
            return true;
        }
        return false;
    }

    /**
     * toString implementation
     * @return
     */
    @Override
    public String toString(){
        return username;
    }

    /**
     * Deletes album from album list
     * @param a
     */
    public void deleteAlbum(Album a){
        if(a!=null){
            albumList.remove(a);
        }
    }
    public static void save(User u) throws IOException {
        ObjectOutputStream stream = null;
        try{
            stream = new ObjectOutputStream(new FileOutputStream("albums.dat"));
            stream.writeObject(MainActivity.user);
        } catch(Exception e){
            e.printStackTrace();
        }
        stream.close();
    }
    public static void load(User u) throws IOException, ClassNotFoundException, ParseException {
        File file = new File("albums.dat");

        try{
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream("albums.dat"));
            MainActivity.user = (User) inp.readObject();
            inp.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
