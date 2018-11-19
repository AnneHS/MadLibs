package com.example.anneh.mad_libs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        IS LISTENER NODIG????
//        Go to next activity when start is clicked
//        Button btn = (Button)findViewById(R.id.start);
//        btn.setOnClickListener(new View.OnClickListener());
    }


    public void startClicked(View view) {
        /** continue to next activity */

        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(intent);
    }

}
