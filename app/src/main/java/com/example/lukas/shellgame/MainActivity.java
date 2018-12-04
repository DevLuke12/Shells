package com.example.lukas.shellgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{
    private Button btnNewGame;
    private Button myText;
    private Button myText2;
    private Button myImage;
    public static final String PASSED_MESSAGE = "zodiacName";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewGame = findViewById(R.id.btnNewGame);

        btnNewGame.setOnClickListener(openNewGame);
    }

    private View.OnClickListener openNewGame = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent(MainActivity.this,
                    GameActivity.class);
            //intent.putExtra(PASSED_MESSAGE, myText2.getText().toString());
            startActivity(intent);
        }
    };
}
