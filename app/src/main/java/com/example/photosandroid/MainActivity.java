package com.example.photosandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.AdapterView.OnItemClickListener;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button searchButton, openButton, addAlbumButton, deleteAlbumButton, editAlbumButton;
    private TextView editAlbumTextView, addAlbumTextView;
    private ListView albumListView;

    private ArrayAdapter<Album> arrayAdapter;
    public static User user = new User("Main");
    public Object selected;
    public Album actualSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openButton = findViewById(R.id.searchButton);
        searchButton = findViewById(R.id.searchButton2);
        deleteAlbumButton = findViewById(R.id.deleteAlbumButton);
        addAlbumButton = findViewById(R.id.photoListView);
        arrayAdapter = new ArrayAdapter<Album>(this, android.R.layout.simple_list_item_1);
        albumListView = findViewById(R.id.albumListView);
        albumListView.setAdapter(arrayAdapter);
        editAlbumButton = findViewById(R.id.editAlbumButton);
        addAlbumTextView = findViewById(R.id.addAlbumTextView);
        editAlbumTextView = findViewById(R.id.editAlbumTextView);


        albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position);
                albumListView.setSelection(position);
                actualSelected = (Album) selected;
            }

        });


        addAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String albumName = addAlbumTextView.getText().toString();
                if (!albumName.isEmpty()) {
                    Album addition = new Album(albumName);
                    arrayAdapter.add(addition);
                    MainActivity.user.addAlbum(addition);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        deleteAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actualSelected != null) {
                    arrayAdapter.remove(actualSelected);
                    MainActivity.user.deleteAlbum(actualSelected);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        editAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actualSelected != null) {
                    String albumName = editAlbumTextView.getText().toString();
                    actualSelected.setAlbumName(albumName);
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent change = new Intent(MainActivity.this, searchPageController.class);
                startActivity(change);
            }
        });

        openButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent change = new Intent(MainActivity.this, photoController.class);
                startActivity(change);
            }
        });
    }
}

