package com.example.anneh.mad_libs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;

public class FirstActivity extends AppCompatActivity {

    // add variable story
    Story story;
    InputStream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void storyClicked(View view) {

        // get InputStream
        switch(view.getId()) {
            case R.id.simple:       stream = getResources().openRawResource(R.raw.madlib0_simple);
                                    break;
            case R.id.tarzan:       stream = getResources().openRawResource(R.raw.madlib1_tarzan);
                                    break;
            case R.id.university:   stream = getResources().openRawResource(R.raw.madlib2_university);
                                    break;
            case R.id.clothes:      stream = getResources().openRawResource(R.raw.madlib3_clothes);
                                    break;
            case R.id.dance:        stream = getResources().openRawResource(R.raw.madlib4_dance);
                                    break;
        }

        // initialize story
        story = new Story(stream);

        // Direct user to new activity + pass Story object
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }


}
