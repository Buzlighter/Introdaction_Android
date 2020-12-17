package com.example.coursera_task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class FragmentSettings extends Fragment {
    public RadioButton google;
    public RadioButton yandex;
    public RadioButton bing;
    public RadioGroup radioGroup;
    public static final String DOMAIN_GOOGLE = "https://www.google.com/search?q=";
    public static final String DOMAIN_YANDEX = "https://yandex.ru/search/?text=";
    public static final String DOMAIN_BING = "https://www.bing.com/search?q=";
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = Objects.requireNonNull(this.getActivity())
                .getSharedPreferences(FragmentSearching.APP_PREFERENCES_SEARCHER, Context.MODE_PRIVATE);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        google = view.findViewById(R.id.google);
        yandex = view.findViewById(R.id.yandex);
        bing = view.findViewById(R.id.bing);
        radioGroup = view.findViewById(R.id.radio_group);

        // При создании View элемента выставляем RadioButton в пункт из SharedPreferences
        String currentSearcher = sharedPreferences.getString(FragmentSearching.DOMAIN, "");
        int currentId = getCheckedIdBySearcherName(currentSearcher, view);
        if (!currentSearcher.isEmpty() && currentId != -1) {
            radioGroup.check(currentId);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId) {
                case R.id.google:
                    chooseSearcher(sharedPreferences, DOMAIN_GOOGLE);
                    break;
                case R.id.yandex:
                    chooseSearcher(sharedPreferences, DOMAIN_YANDEX);
                    break;
                case R.id.bing:
                    chooseSearcher(sharedPreferences, DOMAIN_BING);
                    break;
                default:
                    break;
            }
        });
        return view;
    }

    public int getCheckedIdBySearcherName(String searcherName, View view) {
        switch (searcherName) {
            case DOMAIN_GOOGLE:
                return R.id.google;
            case DOMAIN_YANDEX:
                return R.id.yandex;
            case DOMAIN_BING:
                return R.id.bing;
            default:
                return -1;
        }
    }


    public void chooseSearcher(SharedPreferences sharedPreferences, String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FragmentSearching.DOMAIN, data);
        editor.apply();
    }
}