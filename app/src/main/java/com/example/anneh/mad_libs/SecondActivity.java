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

        Intent intent = getIntent();
        retrievedStory = (Story) intent.getSerializableExtra("story");

        // set first hint
        input = findViewById(R.id.input);
        input.setHint(retrievedStory.getNextPlaceholder());

        // show help
        help = findViewById(R.id.help);
        help.setText("Please type a/an " + retrievedStory.getNextPlaceholder());

        // show remaining words
        counter = findViewById(R.id.counter);
        int remaining = retrievedStory.getPlaceholderRemainingCount();
        counter.setText(remaining + " word(s) left");
    }

    public void okClicked(View view) {

        // get user input as string and fill in
        String word = input.getText().toString();
        retrievedStory.fillInPlaceholder(word);


        // go to next activity if story is completely filled in
        if (retrievedStory.getNextPlaceholder() == "") {
            Intent intent = new Intent(SecondActivity.this, Complete.class);
            intent.putExtra("story", retrievedStory);
            startActivity(intent);
        }
        // else empty input & set next hint (to "adjective", "noun" e.g.)
        else {
            input.setHint(retrievedStory.getNextPlaceholder());
            input.setText("");
        }

        // update remaining words
        int remaining = retrievedStory.getPlaceholderRemainingCount();
        counter.setText(remaining + " word(s) left");

        // update help
        help.setText("Please type a/an " + retrievedStory.getNextPlaceholder());
    }

    @Override
    public void onBackPressed() {
        retrievedStory.clear();
        Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
        startActivity(intent);
    }

}
