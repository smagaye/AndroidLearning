package com.smag.androidlearning;

import android.app.Activity;

import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
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

import com.rey.material.app.DialogFragment;
import com.rey.material.app.TimePickerDialog;
import com.smag.androidlearning.helper.RecyclerAdapterEDT;

import java.io.File;
import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfil extends AppCompatActivity {

    private Button edit_photo_profil;
    private EditText info_user;
    private EditText profession_user;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private static final String PICTURE_PATH = "picture_path";
    private static final String INFO_USER = "info_user";
    private static final String PROFESSION_USER = "profession_user";
    private CircleImageView circleImageView ;
    private Button date_picker;
    private Button hDebutPicker;
    private Button hFinPicker;
    Dialog.Builder builder;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        edit_photo_profil = (Button) findViewById(R.id.edit_photo_profil);

        info_user = (EditText) findViewById(R.id.info_perso_user_input);
        profession_user = (EditText) findViewById(R.id.profession_user_input);

        date_picker = (Button) findViewById(R.id.date_picker_button);
        hDebutPicker = (Button) findViewById(R.id.h_debut_picker);
        hFinPicker = (Button) findViewById(R.id.h_fin_picker);

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new DatePickerDialog.Builder(R.style.Material_App_Dialog_DatePicker_Light){
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                        String date = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                        Toast.makeText(getApplicationContext(), "Date is " + date, Toast.LENGTH_SHORT).show();
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };
                builder.positiveAction("OK")
                        .negativeAction("ANNULER");
                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getSupportFragmentManager(), null);
            }
        });
        hDebutPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new TimePickerDialog.Builder(R.style.Material_App_Dialog_DatePicker_Light , 24, 00){
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        TimePickerDialog dialog = (TimePickerDialog)fragment.getDialog();
                        Toast.makeText(getApplicationContext(), "Time is " + dialog.getFormattedTime(SimpleDateFormat.getTimeInstance()), Toast.LENGTH_SHORT).show();
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(getApplicationContext(), "Cancelled" , Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("OK")
                        .negativeAction("ANNULER");
                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getSupportFragmentManager(), null);
            }
        });
        circleImageView = (CircleImageView) findViewById(R.id.photo_profile_image_view);
        sharedPreferences = getApplicationContext().getSharedPreferences( getString(R.string.preference_file_key) , Context.MODE_PRIVATE);
        String picture_path = sharedPreferences.getString( PICTURE_PATH,"");
        if( !picture_path.equals("")) circleImageView.setImageURI(Uri.fromFile(new File(picture_path)));
        info_user.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(INFO_USER , info_user.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(), "Nom et Prénom modifié", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        profession_user.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PROFESSION_USER , profession_user.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(), "Profession modifié", Toast.LENGTH_SHORT).show();
                return false;
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
