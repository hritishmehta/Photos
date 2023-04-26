package com.example.photosandroid;

//import org.example.Photos;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Hritish Mehta
 * @author Likhit Krishnam
 * This class controls functions relating to the master user list and takes care of user persistence.
 */
public class Admin implements Serializable{

    /**
     * Arraylist of all the users
     */
    public ArrayList<User> allUsers;
    /**
     * User instance of the current user
     */
    private User currentUser;

    /**
     * String of the filename where data gets stored for persistence.
     */
    private static final String fileName = "users.dat";
    /**
     * servialVersianUID for data persistence application
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public Admin(){
        allUsers = new ArrayList<User>();
    }

    /**
     * Save's the program's state to the users.dat file
     * @param admin
     * @throws IOException
     */
    public static void save(Admin admin) throws IOException {
        ObjectOutputStream stream = null;
        try{
            stream = new ObjectOutputStream(new FileOutputStream(fileName));
            stream.writeObject(Photos.admin);
        } catch(Exception e){
            e.printStackTrace();
        }
        stream.close();
    }

    /**
     * Loads the previous program state from users.dat file
     * @param admin
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ParseException
     */
    public static void load(Admin admin) throws IOException, ClassNotFoundException, ParseException {
        File file = new File("users.dat");
        String[] fileNames = {"dog1","dog2","dog3","dog4","dog5"};
        String[] fileLocs = {"data/dog1.jpg","data/dog2.jpg","data/dog3.jpg","data/dog4.jpg","data/dog5.jpg"};

        Album stockAlbum = new Album("stock");
        if(file.length()==0){
            System.out.println("0");
            User stock = new User("stock");
            stock.addAlbum(stockAlbum);
            Photos.admin.allUsers.add(stock);
            for(int i = 0; i<fileLocs.length; i++){
                Photo img = new Photo(fileLocs[i],fileNames[i]);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
                String s = sdf.format(file.lastModified());
                Date date = sdf.parse(s);
                img.setDate(date);

                stockAlbum.addPhoto(img);
            }
            return;
        }
        try{
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
            Photos.admin = (Admin) inp.readObject();
            inp.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Getter method to retrieve a user by their username
     * @param username
     * @return user
     */
    public User getUser(String username){
        for(User user: allUsers){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    /**
     * Getter method to retrieve current user
     * @return currentUser
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Setter method to set the current user
     * @param u
     */
    public void setCurrentUser(User u){
        currentUser = u;
    }

    /**
     * Method to check if a user exists based on a username.
     * @param username
     * @return boolean
     */
    public boolean checkUser(String username){
        for(User user: allUsers){
            if(user.getUsername().equals(username))
                return true;
        }
        return false;
    }


}

