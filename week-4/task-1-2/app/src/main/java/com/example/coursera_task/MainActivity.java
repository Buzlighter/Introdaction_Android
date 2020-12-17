package com.example.coursera_task;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public final  int menu_settings = R.id.settings;
    public final  int menu_searching = R.id.searching;
    public final  int menu_exit = R.id.exit;
    public final  int main_container = R.id.main_container;
    public FragmentSettings fragmentSettings;
    public FragmentSearching fragmentSearching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentSettings = new FragmentSettings();
        fragmentSearching = new FragmentSearching();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case menu_settings:
                makeToast(R.string.settings);
                openFragment(fragmentSettings);
                break;
            case menu_searching:
                makeToast(R.string.searching);
                openFragment(fragmentSearching);
                break;
            case menu_exit:
                makeToast(R.string.exit);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void makeToast(@StringRes int stringRes) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show();
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(main_container,fragment)
                .addToBackStack(Fragment.class.getName())
                .commit();
    }

}