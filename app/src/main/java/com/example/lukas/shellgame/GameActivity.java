package com.example.lukas.shellgame;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;

public class GameActivity extends Activity
{

    ShellGameView shellGameView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        shellGameView = findViewById(R.id.myGameView);

    }
}
