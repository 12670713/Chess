package com.example.chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoveRuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_rule);
    }

    public void onClickMoveKing(View view) {
        Intent intent = new Intent(MoveRuleActivity.this, MoveKingActivity.class);
        startActivity(intent);
    }

    public void onClickMoveQueen(View view) {
        Intent intent = new Intent(MoveRuleActivity.this, MoveQueenActivity.class);
        startActivity(intent);
    }

    public void onClickMoveBishop(View view) {
        Intent intent = new Intent(MoveRuleActivity.this, MoveBishopActivity.class);
        startActivity(intent);
    }

    public void onClickMoveRook(View view) {
        Intent intent = new Intent(MoveRuleActivity.this, MoveRookActivity.class);
        startActivity(intent);
    }

    public void onClickMoveKnight(View view) {
        Intent intent = new Intent(MoveRuleActivity.this, MoveKnightActivity.class);
        startActivity(intent);
    }

    public void onClickMovePawn (View view) {
        Intent intent = new Intent(MoveRuleActivity.this, MovePawnActivity.class);
        startActivity(intent);
    }
}
