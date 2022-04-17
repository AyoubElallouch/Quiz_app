package com.elallouch.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {
    Button btntry, btnmic, btnmap;
    Counter c = new Counter();
    TextView score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_score);

        score = (TextView) findViewById(R.id.score);
        score.setText(String.valueOf(c.cpt));

        btntry = (Button) findViewById(R.id.btntryagain);
        btnmic = (Button) findViewById(R.id.btnmic);
        btnmap = (Button) findViewById(R.id.btnmap);

        btntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Score.this,MainMenu.class));
            }
        });
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Score.this, MapsActivity.class));
            }
        });
        btnmic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



    }
}
