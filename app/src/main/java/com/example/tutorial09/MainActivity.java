package com.example.tutorial09;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText edtWrite;
    TextView txtData;
    final String FILE_ASSETS = "data.json";
    final String FILE_INTERNAL = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWrite = findViewById(R.id.edtWrite);
        txtData = findViewById(R.id.txtData);

    }

    public void Read_Assets(View view) {
        try {
            InputStream inputStream = getAssets().open(FILE_ASSETS);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder= new StringBuilder();
            String mLine;
            while((mLine = bufferedReader.readLine())!=null){
                stringBuilder.append(mLine+"\n");
            }
            txtData.setText(stringBuilder.toString());
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Read_File(View view) {
        try {

                FileInputStream fin = openFileInput(FILE_INTERNAL);
                InputStreamReader inputStreamReader = new InputStreamReader(fin);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder= new StringBuilder();
                String mLine;
                while((mLine = bufferedReader.readLine())!=null){
                    stringBuilder.append(mLine+"\n");
                }
                txtData.setText(stringBuilder);
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"There is no file",Toast.LENGTH_SHORT).show();
        }
    }

    public void Write_File(View view) {
        try {
            if(!edtWrite.getText().toString().isEmpty()){
                FileOutputStream fileOutputStream = openFileOutput(FILE_INTERNAL, Context.MODE_PRIVATE);
                String data = edtWrite.getText().toString();
                fileOutputStream.write(data.getBytes());
                fileOutputStream.close();
                edtWrite.setText(null);
                Toast.makeText(this,"File Write successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Please enter some text to read file",Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}