package com.elallouch.quizgame;

import androidx.appcompat.app.AppCompatActivity;

public class Counter extends AppCompatActivity {
    public static int cpt = 0;

    public Counter() {
    }
    public Counter(int x) {
        this.cpt = x;
    }
}
