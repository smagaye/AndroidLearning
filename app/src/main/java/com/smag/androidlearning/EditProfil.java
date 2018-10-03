package com.smag.androidlearning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.smag.androidlearning.helper.RecyclerAdapterEDT;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfil extends Activity {

    private CalendarView calendarView;
    private Button edit_photo_profil;
    private EditText info_user;
    private EditText profession_user;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private static final String PICTURE_PATH = "picture_path";
    private CircleImageView circleImageView ;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        calendarView =(CalendarView) findViewById(R.id.date_picker);

        edit_photo_profil = (Button) findViewById(R.id.edit_photo_profil);

        info_user = (EditText) findViewById(R.id.info_perso_user_input);
        profession_user = (EditText) findViewById(R.id.profession_user_input);

        info_user.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Toast.makeText(getApplicationContext(), "key pressed", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        circleImageView = (CircleImageView) findViewById(R.id.photo_profile_image_view);
        sharedPreferences = getApplicationContext().getSharedPreferences( getString(R.string.preference_file_key) , Context.MODE_PRIVATE);
        String picture_path = sharedPreferences.getString( PICTURE_PATH,"");
        if( !picture_path.equals("")) circleImageView.setImageURI(Uri.fromFile(new File(picture_path)));
        calendarView.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        // display the selected date by using a toast
                        Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

// 1. Instantiate an AlertDialog.Builder with its constructor
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

// 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage("Test message")
                                .setTitle("Test Titre");

// 3. Get the AlertDialog from create()
                        AlertDialog dialog = builder.create();
                    }
                });

        edit_photo_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent  = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult( intent , PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE ){
            imageUri = data.getData();
           // Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG).show();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(imageUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Toast.makeText(getApplicationContext(),picturePath , Toast.LENGTH_LONG).show();
            circleImageView.setImageURI(Uri.fromFile(new File(picturePath)));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PICTURE_PATH,picturePath);
            editor.commit();
        }
    }
}
