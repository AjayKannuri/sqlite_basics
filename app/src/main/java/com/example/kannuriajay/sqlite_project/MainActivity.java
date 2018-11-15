package com.example.kannuriajay.sqlite_project;

import android.database.Cursor;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private EditText nameField;
    private EditText surnameField;
    private EditText marksField;
    private Button addData;
    private Button viewBtn;
    private EditText idField;
    private Button updateBtn;
    private Button deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(MainActivity.this);
        nameField=(EditText)findViewById(R.id.NameField);
        surnameField=(EditText)findViewById(R.id.surnameField2);
        marksField=(EditText)findViewById(R.id.marksField);
        addData=(Button)findViewById(R.id.loginBtn2);
        viewBtn=(Button)findViewById(R.id.viewBtn);
        idField=(EditText)findViewById(R.id.idField);
        updateBtn=(Button)findViewById(R.id.updateBtn);
        deleteBtn=(Button)findViewById(R.id.deleteBtn);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue=nameField.getText().toString();
                String surnameValue=surnameField.getText().toString();
                String marksValue=marksField.getText().toString();
                boolean isInserted=myDb.insertData(nameValue,surnameValue,marksValue);
                if(isInserted)
                {
                    Toast.makeText(getApplicationContext(),"successfully added to the database", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"not added to the database", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Cursor res=myDb.getAllData();
               // Toast.makeText(getApplicationContext(),"came inside of viewBtn", Toast.LENGTH_SHORT).show();
                 if(res.getCount()==0)
                 {
                     Toast.makeText(getApplicationContext(),"no data is available", Toast.LENGTH_SHORT).show();
                 }
                 StringBuffer sb=new StringBuffer();
                 while(res.moveToNext())
                 {
                     sb.append("ID: "+ res.getString(0)+"\n"+"NAME "+res.getString(1)+"\n"+"SURNAME "+res.getString(2)+"\n"+"MARKS "+res.getString(3));
                     sb.append("\n");
                 }
                 showMessage("Data",sb.toString());
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=idField.getText().toString();
                 Integer deleteRows=myDb.deleteData(id);
                if(deleteRows>0)
                {
                    Toast.makeText(getApplicationContext(),"successfully deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"not successfully deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue=nameField.getText().toString();
                String surnameValue=surnameField.getText().toString();
                String marksValue=marksField.getText().toString();
                String id=idField.getText().toString();
                boolean isUpdated=myDb.updateData(id,nameValue,surnameValue,marksValue);
                if(isUpdated)
                {
                    Toast.makeText(getApplicationContext(),"successfully Updated to the database", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"not updated to the database", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
          builder.setCancelable(true);
          builder.setTitle(title);
          builder.setMessage(Message);
          builder.show();
       // Toast.makeText(getApplicationContext(),"dialogue is opened", Toast.LENGTH_SHORT).show();

    }
}
