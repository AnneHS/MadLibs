package com.example.anneh.mad_libs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Complete extends AppCompatActivity {

    Story completeStory;
    TextView completeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        Intent intent = getIntent();
        completeStory = (Story) intent.getSerializableExtra("story");


        completeText = findViewById(R.id.completeText);
        completeText.setText(completeStory.toString());

    }

    public void newClicked(View view) {
        // resets the story back to an empty initial state
        completeStory.clear();

        Intent intent = new Intent(Complete.this, FirstActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        completeStory.clear();
        Intent intent = new Intent(Complete.this, FirstActivity.class);
        startActivity(intent);
    }
}
