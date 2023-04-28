package com.example.photosandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.text.ParseException;


public class MainActivity extends AppCompatActivity {
    private Button searchButton, openButton, addAlbumButton, deleteAlbumButton, editAlbumButton;
    private TextView editAlbumTextView, addAlbumTextView;
    private ListView albumListView;

    private ArrayAdapter<Album> arrayAdapter;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try {
            MainActivity.user.load(MainActivity.user);
        } catch (IOException | ClassNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }

        if(user==null){
            user = new User("Main");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        albumListView = findViewById(R.id.albumListView);
        openButton = findViewById(R.id.openButton);
        searchButton=findViewById(R.id.openButton);
        addAlbumButton = findViewById(R.id.addAlbumButton);
        editAlbumButton = findViewById(R.id.editAlbumButton);
        deleteAlbumButton = findViewById(R.id.deleteAlbumButton);

        albumListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        albumListView.setSelection(0);
        arrayAdapter = new ArrayAdapter<Album>(this,R.layout.activity_main,user.getAllAlbums());
        albumListView.setAdapter(arrayAdapter);


        albumListView.setOnItemLongClickListener((parent, view, position, id) -> {
            Album selectedAlbum = (Album)albumListView.getSelectedItem();
            return true;
       });

        addAlbumButton.setOnClickListener(v-> {
            String albumName = addAlbumTextView.getText().toString();
            if(albumName.equals(null)){
                Toast.makeText(getApplicationContext(), "Please type in a album name first", Toast.LENGTH_SHORT).show();
            }
            else{
                Album addition = new Album(albumName);
                arrayAdapter.add(addition);
                arrayAdapter.notifyDataSetChanged();
            }
            // Set the title and message of the dialog
//            builder.setTitle("Create New Album");
//            builder.setMessage("Enter the name of the new album:");
//
//            // Set the view of the dialog to be the EditText input view
//            builder.setView(albumNameInput);
//            builder.setPositiveButton("Create",new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface dialog, int which){
//                    String albumName = albumNameInput.getText().toString();
//                    Album addition = new Album(albumName);
//                    MainActivity.user.addAlbum(addition);
//                    arrayAdapter.add(addition);
//                    arrayAdapter.notifyDataSetChanged();
//                }
//            });
//            builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface dialog, int which){
//                    dialog.cancel();
//                }
//            });
//
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();

        });
    }

}

