package com.sh405260.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_game_level);

    }


    //Level 1 ( From 1 - 10)
    public void level1(View view){
        Game myGame = new Game();
        myGame.setHigh(10);
        Intent gamePage = new Intent(MainActivity.this, Game.class);
        startActivity(gamePage);

    }

    //Level 2 ( From 1 - 50)
    public void level2(View view){
        Game myGame = new Game();
        myGame.setHigh(50);
        Intent gamePage = new Intent(MainActivity.this, Game.class);
        startActivity(gamePage);

    }

    //Level 3 ( From 1 - 100)
    public void level3(View view){
        Game myGame = new Game();
        myGame.setHigh(100);
        Intent gamePage = new Intent(MainActivity.this, Game.class);
        startActivity(gamePage);

    }

    //Level 4 ( From 1 - 1000)
    public void level4(View view){
        Game myGame = new Game();
        myGame.setHigh(1000);
        Intent gamePage = new Intent(MainActivity.this, Game.class);
        startActivity(gamePage);

    }





}
