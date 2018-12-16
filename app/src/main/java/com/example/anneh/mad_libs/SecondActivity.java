package com.example.anneh.mad_libs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView counter;
    TextView help;
    TextView input;
    Story retrievedStory;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get picked story
        Intent intent = getIntent();
        retrievedStory = (Story) intent.getSerializableExtra("story");

        // Set first hint
        input = findViewById(R.id.input);
        input.setHint(retrievedStory.getNextPlaceholder());

        // Show help
        help = findViewById(R.id.help);
        help.setText("Please type a/an " + retrievedStory.getNextPlaceholder());

        // Show remaining words
        counter = findViewById(R.id.counter);
        int remaining = retrievedStory.getPlaceholderRemainingCount();
        counter.setText(remaining + " word(s) left");
    }

    public void okClicked(View view) {

        // Get user input as string and fill in
        String word = input.getText().toString();
        retrievedStory.fillInPlaceholder(word);


        // Go to next activity if story is completely filled in
        if (retrievedStory.getNextPlaceholder() == "") {
            Intent intent = new Intent(SecondActivity.this, Complete.class);
            intent.putExtra("story", retrievedStory);
            startActivity(intent);
        }
        // Else empty input & set next hint (to "adjective", "noun" e.g.)
        else {
            input.setHint(retrievedStory.getNextPlaceholder());
            input.setText("");
        }

        // Update remaining words
        int remaining = retrievedStory.getPlaceholderRemainingCount();
        counter.setText(remaining + " word(s) left");

        // Update help
        help.setText("Please type a/an " + retrievedStory.getNextPlaceholder());
    }

    @Override
    public void onBackPressed() {

        // Go back to first activity (Pick a story)
        retrievedStory.clear();
        Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
        startActivity(intent);
    }

}
