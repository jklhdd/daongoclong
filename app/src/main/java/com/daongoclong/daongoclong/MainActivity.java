package com.daongoclong.daongoclong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edName, edMail, edFeedback;
    Spinner spinner;
    Button btSend;
    CheckBox checkBox;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        appDatabase = AppDatabase.getAppDatabase(this);
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edMail = findViewById(R.id.edMail);
        edFeedback = findViewById(R.id.edFeedback);
        checkBox = findViewById(R.id.ck);
        btSend = findViewById(R.id.btSend);

        btSend.setOnClickListener(this);

        String[] devName = {"Gripe", "Zack", "Dave", "Sam"};
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, devName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (edName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }
        if (edMail.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter mail", Toast.LENGTH_LONG).show();
            return;
        }
        if (edFeedback.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter feedback", Toast.LENGTH_LONG).show();
            return;
        }

        Feedback feedback = new Feedback();
        feedback.setName(edName.getText().toString());
        feedback.setMail(edMail.getText().toString());
        feedback.setContent(edFeedback.getText().toString());
        feedback.setDevName(spinner.getSelectedItem().toString());
        if (!checkBox.isChecked()){
            feedback.setResponse(false);
        }else feedback.setResponse(true);

        if(appDatabase.feedbackDao().insert(feedback) != 0){
            edName.setText(null);
            edMail.setText(null);
            edFeedback.setText(null);
            checkBox.setChecked(false);

            int count = appDatabase.feedbackDao().count();
            Toast.makeText(this, "Total rows: "+count, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Send failed!", Toast.LENGTH_LONG).show();
        }
    }
}