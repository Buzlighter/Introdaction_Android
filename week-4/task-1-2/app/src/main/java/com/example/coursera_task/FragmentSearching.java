package com.example.coursera_task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class FragmentSearching extends Fragment {
    public static final String APP_PREFERENCES_SEARCHER = "Searcher";
    public static final String DOMAIN = "DOMAIN";

    public EditText text_search;
    public Button button_search;
    public SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = Objects.requireNonNull(this.getActivity())
                .getSharedPreferences(APP_PREFERENCES_SEARCHER, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searching, container, false);
        text_search = view.findViewById(R.id.text_search);
        button_search = view.findViewById(R.id.button_search);
        button_search.setOnClickListener(v -> {
            String searchUrl = sharedPreferences.getString(DOMAIN, "");
            String searchWord = text_search.getText().toString();
            if (!searchWord.isEmpty() &&
                    !searchUrl.isEmpty()) {
                openBrowserUrl(searchUrl, searchWord);
            }
        });
        return view;
    }

    public void openBrowserUrl(String searchUrl, String searchWord) {
        Intent browserIntent =
                new Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl + searchWord));
        startActivity(browserIntent);
    }
}