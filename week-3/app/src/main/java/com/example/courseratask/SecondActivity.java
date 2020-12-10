package com.example.courseratask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private Button button2;
    private TextView textView;
    private View second_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button2 = findViewById(R.id.button_second);
        textView = findViewById(R.id.tv_text);
        second_layout = findViewById(R.id.second_layout);
        second_layout.setBackgroundResource(R.drawable.image_background);

        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString(MainActivity.fromEdit));

        button2.setOnClickListener(event -> openBrowserUrl(bundle.getString(MainActivity.fromEdit)));
    }


    public void openBrowserUrl(String searchWord) {
        Intent browserIntent =
                new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.ru/search?q=" + searchWord));
        startActivity(browserIntent);
    }
}