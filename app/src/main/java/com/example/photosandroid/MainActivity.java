package com.example.photosandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.text.ParseException;


public class MainActivity extends AppCompatActivity {
    private Button searchButton, openButton, addButton, deleteButton, editButton;
    private ListView albumListView;

    private ArrayAdapter<Album> arrayAdapter;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try {
            MainActivity.user.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if(user==null){
            user = new User("Main");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_page);
        albumListView = findViewById(R.id.albumListView);
        openButton = findViewById(R.id.openButton);
        searchButton=findViewById(R.id.searchButton);
        addButton = findViewById(R.id.addAlbumButton);
        editButton = findViewById(R.id.editAlbumButton);
        deleteButton = findViewById(R.id.deleteAlbumButton);

        albumListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        albumListView.setSelection(0);
        arrayAdapter = new ArrayAdapter<Album>(this,R.layout.album_page,user.getAllAlbums());
        albumListView.setAdapter(arrayAdapter);

        albumListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                Album selectedAlbum = (Album)albumListView.getSelectedItem();
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final EditText albumNameInput = new EditText(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Set the title and message of the dialog
                builder.setTitle("Create New Album");
                builder.setMessage("Enter the name of the new album:");

                // Set the view of the dialog to be the EditText input view
                builder.setView(albumNameInput);

                builder.setPositiveButton("Create",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        String albumName = albumNameInput.getText().toString();
                        Album addition = new Album(albumName);
                        MainActivity.user.addAlbum(addition);
                        arrayAdapter.add(addition);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }

}

