package com.example.eva2_7_activity_result;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int INFO_ACTIVITY =100;
    final int INFO_CONTACTOS=200;
    final int INFO_IMAGE=300;
    //final int INFO_IMAGENES;
    Intent intent, intentimage ,intentContact;
    ImageView imgVwMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwMostrar =findViewById(R.id.imgVwMostrar);
        intent = new Intent(this,InfoActivity.class);
        intentContact=new Intent(Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI);

        intentimage=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    public void onClick(View v){
        startActivityForResult(intent,INFO_ACTIVITY);
    }

    public void onClickContact(View v){
        startActivityForResult(intentContact, INFO_CONTACTOS);
    }

    public void onCLickImage(View v){
        startActivityForResult(intentimage, INFO_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){//IDENTIFICA LA ACTIVIDAD
            case INFO_ACTIVITY:
                if (resultCode == Activity.RESULT_OK){//SI REGRESARON RESULTADOS O NO
                    //AQUI HACEMOS ALGO CON LOS DATOS QUE NOS REGRESARON
                    Toast.makeText(this,data.getStringExtra("DATOS"),Toast.LENGTH_LONG).show();
                }
                break;
            case INFO_CONTACTOS:
                if (resultCode== Activity.RESULT_OK){
                   Toast.makeText(this,data.getDataString(),Toast.LENGTH_LONG).show();
               }
                break;
            case INFO_IMAGE:
                if (resultCode== Activity.RESULT_OK){
                    Toast.makeText(this,data.getDataString(),Toast.LENGTH_LONG).show();
                    imgVwMostrar.setImageURI(Uri.parse(data.getDataString()));
                }
                break;
            default:
        }
    }
}