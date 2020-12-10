package com.example.courseratask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private View main_layout;
    public static String fromEdit = "fromEdit";

    private View.OnClickListener pressButton = v -> {
        if(!TextUtils.isEmpty(editText.getText())) {
            showMessage();
        }
        transferToSecondActivity();
    };

    private void transferToSecondActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(fromEdit, editText.getText().toString());
        startActivity(intent);
    }

    private void showMessage(){
        Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_layout = findViewById(R.id.main_layout);
        main_layout.setBackgroundResource(R.drawable.image_background);

        button = findViewById(R.id.button_main);
        editText = findViewById(R.id.et_text);

        button.setOnClickListener(pressButton);
    }
}