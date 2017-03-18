package com.example.ornol.dinoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    public EditText[] Edits= new EditText[10];
    public int temp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Edits[0]=(EditText) findViewById(R.id.restaurantName);
        Edits[1]=(EditText) findViewById(R.id.email);
        Edits[2]=(EditText) findViewById(R.id.Password);
        Edits[3]=(EditText) findViewById(R.id.PasswordConfirmation);
        Edits[4]=(EditText) findViewById(R.id.PhoneNumber);
        Edits[5]=(EditText) findViewById(R.id.Website);
        Edits[6]=(EditText) findViewById(R.id.Address);
        Edits[7]=(EditText) findViewById(R.id.City);
        Edits[8]=(EditText) findViewById(R.id.PostalCode);
        Edits[9]=(EditText) findViewById(R.id.Description);
        for(int i = 0;i <Edits.length;i++) {
            Edits[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Edits[temp].setText("");
                }
            });
        }
    }

    public void SignUp(View view){
        for(int i =0;i<Edits.length;i++){

        }
    }
}
