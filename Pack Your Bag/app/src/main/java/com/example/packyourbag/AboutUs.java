package com.example.packyourbag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Retrieve views
        TextView developerNameTextView = findViewById(R.id.developerNameTextView);
        TextView developerBioTextView = findViewById(R.id.developerBioTextView);
        LinearLayout skillsLayout = findViewById(R.id.skillsLayout);
        LinearLayout socialMediaLayout = findViewById(R.id.socialMediaLayout);

        // Populate data from XML structure
        developerNameTextView.setText("Ajay Soni");
        developerBioTextView.setText("Software Developer, Android App Developer");

        String[] skills = {"C++", "Data Structure & Algorithm", "Core Java", "Android Development", "XML"};
        for (String skill : skills) {
            TextView skillTextView = new TextView(this);
            skillTextView.setText(skill);
            skillsLayout.addView(skillTextView);
        }

        String[] socialMediaLinks = {"/https://www.linkedin.com/in/ajaysoni123// (LinkedIn)", "/https://github.com/ajaysoni12 (GitHub)"};
        for (String link : socialMediaLinks) {
            TextView linkTextView = new TextView(this);
            linkTextView.setText(link);
            socialMediaLayout.addView(linkTextView);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}