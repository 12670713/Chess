package com.example.chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameRuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rule);
    }

    public void onClickMoveRule(View view) {
        Intent intent = new Intent(GameRuleActivity.this, MoveRuleActivity.class);
        startActivity(intent);
    }
}
