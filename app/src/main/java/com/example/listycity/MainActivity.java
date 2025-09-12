package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addButton;
    Button deleteButton;
    EditText inputCity;
    Button confirmButton;
    LinearLayout bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);
        inputCity = findViewById(R.id.input_city);
        confirmButton = this.findViewById(R.id.confirm_button);
        bottomBar = findViewById(R.id.bottom_bar);

        dataList = new ArrayList<>();

        cityAdapter = new ArrayAdapter<>( this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        addButton.setOnClickListener(v -> {
            bottomBar.setVisibility(View.VISIBLE); // make bottom visible (text box and confirm button)
            inputCity.requestFocus(); // move cursor into the text box
        });

        confirmButton.setOnClickListener(v -> {
            String cityAdded = inputCity.getText().toString();
            dataList.add(cityAdded);
            cityAdapter.notifyDataSetChanged(); // refresh
            inputCity.getText().clear();
            bottomBar.setVisibility(View.GONE);
        });

        deleteButton.setOnClickListener(v -> {
            int position = cityList.getCheckedItemPosition();

            if (position == -1) { // -1 means no selection
                return;
            }

            dataList.remove(position);                // remove from list
            cityAdapter.notifyDataSetChanged();       // refresh UI
            cityList.clearChoices();                  // clear highlight
        });

    }
}