package com.example.photosandroid;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.AdapterView.OnItemClickListener;


import androidx.appcompat.app.AppCompatActivity;


public class photoController extends AppCompatActivity {
    private ListView photoListView;
    private Button addButton, slideshowButton, moveButton, tagButton, deletePButton,backButton;
    private ImageView imageView;
    private TextView textView;
    private Photo selected;

    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_page);
        addButton = findViewById(R.id.addButton);
        deletePButton = findViewById(R.id.deletePButton);
        tagButton = findViewById(R.id.tagButton);
        moveButton = findViewById(R.id.moveButton);
        slideshowButton = findViewById(R.id.slideshowButton);
        backButton = findViewById(R.id.backButton2);
        textView = findViewById(R.id.textView3);
        photoListView = findViewById(R.id.photoListView);
        imageView = findViewById(R.id.imageView);

        myAdapter = new MyAdapter(this,R.layout.list_item,MainActivity.user.getCurrAlbum().getPhotoArrayList());
        photoListView.setAdapter(myAdapter);
        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = (Photo)parent.getItemAtPosition(position);
                photoListView.setSelection(position);
                imageView.setImageURI(selected.getImage());
                textView.setText(selected.getPhotoName());
            }
        });
        addButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 21);

           }
        });

        deletePButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity.user.getCurrAlbum().removePhoto(selected);
                photoListView.setAdapter(myAdapter);
                imageView.setImageURI(null);
                textView.setText("");
            }
        });

        tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new dialog
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.tag_dialog);

                dialog.setTitle("Enter Location and Person");

                EditText locationEditText = dialog.findViewById(R.id.location_edittext);
                EditText personEditText = dialog.findViewById(R.id.person_edittext);

                TextView locationTextView = dialog.findViewById(R.id.location_textview);
                TextView personTextView = dialog.findViewById(R.id.person_textview);

                locationTextView.setText("Location: ");
                personTextView.setText("Person: ");

                Button closeBtn = dialog.findViewById(R.id.close_button);
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


    }



    private static final int PICK_IMAGE_REQUEST = 21;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            String name = getFileName(imageUri);
            Photo p = new Photo(imageUri, name);
            MainActivity.user.getCurrAlbum().addPhoto(p);
            photoListView.setAdapter(myAdapter);

        }
    }

    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}

