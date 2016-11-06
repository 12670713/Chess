package com.example.chess;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GamePlayActivity extends AppCompatActivity {

    RelativeLayout mRelativeLayout;
    GridLayout mLayoutBlack;
    GridLayout mLayoutWhite;

    int[][] mResPosition;
    boolean mOnClick = false;
    boolean mWhite = true;

    String[] promotion;

    ArrayList<TextView> mListTextX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.layout);
        mLayoutBlack = (GridLayout) findViewById(R.id.layoutBlack);
        mLayoutWhite = (GridLayout) findViewById(R.id.layoutWhite);
        initPosition();

        promotion = new String[4];
        promotion[0] = "Queen";
        promotion[1] = "Knight";
        promotion[2] = "Bishop";
        promotion[3] = "Rook";

        mListTextX = new ArrayList<>();
    }

    public void onClickMove(View view) {
        String tag = (String) view.getTag();

        if (mWhite != isWhite(view.getId())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GamePlayActivity.this);
            builder.setTitle(R.string.dialog_title);

            if (mWhite) {
                // white 차례
                builder.setMessage(R.string.dialog_message_white_turn);
            } else {
                // black 차례
                builder.setMessage(R.string.dialog_message_black_turn);
            }

            builder.setCancelable(false);
            builder.setPositiveButton(android.R.string.ok, null);

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        if (mOnClick) {
            for (int i = 0; i < mListTextX.size(); i++) {
                TextView temp = mListTextX.get(i);
                mRelativeLayout.removeView(temp);
            }
            mListTextX.clear();
        }

        switch (tag) {
            case "pawn":
                movePawn(view);
                break;
            case "rook":
                moveRook(view);
                break;
            case "knight":
                moveKnight(view);
                break;
            case "bishop":
                moveBishop(view);
                break;
            case "queen":
                moveQueen(view);
                break;
            case "king":
                moveKing(view);
                break;
        }
    }

    private void initPosition() {
        mResPosition = new int[8][8];
        for (int i = 0; i < mResPosition.length; i++) {
            for (int j = 0; j < mResPosition[i].length; j++) {
                mResPosition[i][j] = 0;
            }
        }
        mResPosition[1][0] = R.id.imgBlackPawn1;
        mResPosition[1][1] = R.id.imgBlackPawn2;
        mResPosition[1][2] = R.id.imgBlackPawn3;
        mResPosition[1][3] = R.id.imgBlackPawn4;
        mResPosition[1][4] = R.id.imgBlackPawn5;
        mResPosition[1][5] = R.id.imgBlackPawn6;
        mResPosition[1][6] = R.id.imgBlackPawn7;
        mResPosition[1][7] = R.id.imgBlackPawn8;

        mResPosition[0][0] = R.id.imgBlackRook1;
        mResPosition[0][1] = R.id.imgBlackKnight1;
        mResPosition[0][2] = R.id.imgBlackBishop1;
        mResPosition[0][3] = R.id.imgBlackKing;
        mResPosition[0][4] = R.id.imgBlackQueen;
        mResPosition[0][5] = R.id.imgBlackBishop2;
        mResPosition[0][6] = R.id.imgBlackKnight2;
        mResPosition[0][7] = R.id.imgBlackRook2;


        mResPosition[6][0] = R.id.imgWhitePawn1;
        mResPosition[6][1] = R.id.imgWhitePawn2;
        mResPosition[6][2] = R.id.imgWhitePawn3;
        mResPosition[6][3] = R.id.imgWhitePawn4;
        mResPosition[6][4] = R.id.imgWhitePawn5;
        mResPosition[6][5] = R.id.imgWhitePawn6;
        mResPosition[6][6] = R.id.imgWhitePawn7;
        mResPosition[6][7] = R.id.imgWhitePawn8;

        mResPosition[7][0] = R.id.imgWhiteRook1;
        mResPosition[7][1] = R.id.imgWhiteKnight1;
        mResPosition[7][2] = R.id.imgWhiteBishop1;
        mResPosition[7][3] = R.id.imgWhiteQueen;
        mResPosition[7][4] = R.id.imgWhiteKing;
        mResPosition[7][5] = R.id.imgWhiteBishop2;
        mResPosition[7][6] = R.id.imgWhiteKnight2;
        mResPosition[7][7] = R.id.imgWhiteRook2;
    }

    private void movePawn(View view) {
        boolean white = isWhite(view.getId());

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    if (white) {
                        if (top == 6 && mResPosition[top - 2][left] == 0) {
                            showX(view, top, left, top - 2, left, mResPosition[top - 2][left]);
                            mOnClick = true;
                        }
                        if (top > 0 && mResPosition[top - 1][left] == 0) {
                            showX(view, top, left, top - 1, left, mResPosition[top - 1][left]);
                            mOnClick = true;
                        }
                        if (top > 0 && left > 0 && mResPosition[top - 1][left - 1] != 0 && !isWhite(mResPosition[top - 1][left - 1])) {
                            showX(view, top, left, top - 1, left - 1, mResPosition[top - 1][left - 1]);
                            mOnClick = true;
                        }
                        if (top > 0 && left < mResPosition[top].length - 1 && mResPosition[top - 1][left + 1] != 0 && !isWhite(mResPosition[top - 1][left + 1])) {
                            showX(view, top, left, top - 1, left + 1, mResPosition[top - 1][left + 1]);
                            mOnClick = true;
                        }
                    } else {
                        if (top == 1 && mResPosition[top + 2][left] == 0) {
                            showX(view, top, left, top + 2, left, mResPosition[top + 2][left]);
                            mOnClick = true;
                        }
                        if (top < mResPosition.length - 1 && mResPosition[top + 1][left] == 0) {
                            showX(view, top, left, top + 1, left, mResPosition[top + 1][left]);
                            mOnClick = true;
                        }
                        if (top < mResPosition.length - 1 && left > 0 && mResPosition[top + 1][left - 1] != 0 && isWhite(mResPosition[top + 1][left - 1])) {
                            showX(view, top, left, top + 1, left - 1, mResPosition[top + 1][left - 1]);
                            mOnClick = true;
                        }
                        if (top < mResPosition.length - 1 && left < mResPosition[top].length - 1 && mResPosition[top + 1][left + 1] != 0 && isWhite(mResPosition[top + 1][left + 1])) {
                            showX(view, top, left, top + 1, left + 1, mResPosition[top + 1][left + 1]);
                            mOnClick = true;
                        }
                    }
                    return;
                }
            }
        }
    }

    private void moveRook(View view) {
        boolean white = isWhite(view.getId());

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    // 위로 이동
                    for (int i = top - 1; i >= 0; i--) {
                        if (mResPosition[i][left] != 0) {
                            if (isWhite(mResPosition[i][left]) != white) {
                                showX(view, top, left, i, left, mResPosition[i][left]);
                            }
                            break;
                        }
                        showX(view, top, left, i, left, mResPosition[i][left]);
                        mOnClick = true;
                    }
                    // 아래로 이동
                    for (int i = top + 1; i < mResPosition.length; i++) {
                        if (mResPosition[i][left] != 0) {
                            if (isWhite(mResPosition[i][left]) != white) {
                                showX(view, top, left, i, left, mResPosition[i][left]);
                            }
                            break;
                        }
                        showX(view, top, left, i, left, mResPosition[i][left]);
                        mOnClick = true;
                    }
                    // 왼쪽으로 이동
                    for (int i = left - 1; i >= 0; i--) {
                        if (mResPosition[top][i] != 0) {
                            if (isWhite(mResPosition[top][i]) != white) {
                                showX(view, top, left, top, i, mResPosition[top][i]);
                            }
                            break;
                        }
                        showX(view, top, left, top, i, mResPosition[top][i]);
                        mOnClick = true;
                    }
                    // 오른쪽으로 이동
                    for (int i = left + 1; i < mResPosition[top].length; i++) {
                        if (mResPosition[top][i] != 0) {
                            if (isWhite(mResPosition[top][i]) != white) {
                                showX(view, top, left, top, i, mResPosition[top][i]);
                            }
                            break;
                        }
                        showX(view, top, left, top, i, mResPosition[top][i]);
                        mOnClick = true;
                    }

                    return;
                }
            }
        }
    }

    private void moveKnight(View view) {
        boolean white = isWhite(view.getId());

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    // 위2 왼1
                    if (top > 1 && left > 0) {
                        if (mResPosition[top - 2][left - 1] == 0 || isWhite(mResPosition[top - 2][left - 1]) != white) {
                            showX(view, top, left, top - 2, left - 1, mResPosition[top - 2][left - 1]);
                            mOnClick = true;
                        }
                    }
                    // 위2 오1
                    if (top > 1 && left < mResPosition[top].length - 1) {
                        if (mResPosition[top - 2][left + 1] == 0 || isWhite(mResPosition[top - 2][left + 1]) != white) {
                            showX(view, top, left, top - 2, left + 1, mResPosition[top - 2][left + 1]);
                            mOnClick = true;
                        }
                    }
                    // 위1 왼2
                    if (top > 0 && left > 1) {
                        if (mResPosition[top - 1][left - 2] == 0 || isWhite(mResPosition[top - 1][left - 2]) != white) {
                            showX(view, top, left, top - 1, left - 2, mResPosition[top - 1][left - 2]);
                            mOnClick = true;
                        }
                    }
                    // 위1 오2
                    if (top > 0 && left < mResPosition[top].length - 2) {
                        if (mResPosition[top - 1][left + 2] == 0 || isWhite(mResPosition[top - 1][left + 2]) != white) {
                            showX(view, top, left, top - 1, left + 2, mResPosition[top - 1][left + 2]);
                            mOnClick = true;
                        }
                    }
                    // 아2 왼1
                    if (top < mResPosition.length - 2 && left > 0) {
                        if (mResPosition[top + 2][left - 1] == 0 || isWhite(mResPosition[top + 2][left - 1]) != white) {
                            showX(view, top, left, top + 2, left - 1, mResPosition[top + 2][left - 1]);
                            mOnClick = true;
                        }
                    }
                    // 아2 오1
                    if (top < mResPosition.length - 2 && left < mResPosition[top].length - 1) {
                        if (mResPosition[top + 2][left + 1] == 0 || isWhite(mResPosition[top + 2][left + 1]) != white) {
                            showX(view, top, left, top + 2, left + 1, mResPosition[top + 2][left + 1]);
                            mOnClick = true;
                        }
                    }
                    // 아1 왼2
                    if (top < mResPosition.length - 1 && left > 1) {
                        if (mResPosition[top + 1][left - 2] == 0 || isWhite(mResPosition[top + 1][left - 2]) != white) {
                            showX(view, top, left, top + 1, left - 2, mResPosition[top + 1][left - 2]);
                            mOnClick = true;
                        }
                    }
                    // 아1 오2
                    if (top < mResPosition.length - 1 && left < mResPosition[top].length - 2) {
                        if (mResPosition[top + 1][left + 2] == 0 || isWhite(mResPosition[top + 1][left + 2]) != white) {
                            showX(view, top, left, top + 1, left + 2, mResPosition[top + 1][left + 2]);
                            mOnClick = true;
                        }
                    }

                    return;
                }
            }
        }
    }

    private void moveBishop(View view) {
        boolean white = isWhite(view.getId());

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    // 위 왼쪽
                    for (int i = top - 1, j = left - 1; i >= 0 && j >= 0; i--, j--) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }
                    // 위 오른쪽
                    for (int i = top - 1, j = left + 1; i >= 0 && j < mResPosition[top].length; i--, j++) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }
                    // 아래 왼쪽
                    for (int i = top + 1, j = left - 1; i < mResPosition.length && j >= 0; i++, j--) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }
                    // 아래 왼쪽
                    for (int i = top + 1, j = left + 1; i < mResPosition.length && j < mResPosition[top].length; i++, j++) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }

                    return;
                }
            }
        }
    }

    private void moveQueen(View view) {
        boolean white = isWhite(view.getId());

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    // 위로 이동
                    for (int i = top - 1; i >= 0; i--) {
                        if (mResPosition[i][left] != 0) {
                            if (isWhite(mResPosition[i][left]) != white) {
                                showX(view, top, left, i, left, mResPosition[i][left]);
                            }
                            break;
                        }
                        showX(view, top, left, i, left, mResPosition[i][left]);
                        mOnClick = true;
                    }
                    // 아래로 이동
                    for (int i = top + 1; i < mResPosition.length; i++) {
                        if (mResPosition[i][left] != 0) {
                            if (isWhite(mResPosition[i][left]) != white) {
                                showX(view, top, left, i, left, mResPosition[i][left]);
                            }
                            break;
                        }
                        showX(view, top, left, i, left, mResPosition[i][left]);
                        mOnClick = true;
                    }
                    // 왼쪽으로 이동
                    for (int i = left - 1; i >= 0; i--) {
                        if (mResPosition[top][i] != 0) {
                            if (isWhite(mResPosition[top][i]) != white) {
                                showX(view, top, left, top, i, mResPosition[top][i]);
                            }
                            break;
                        }
                        showX(view, top, left, top, i, mResPosition[top][i]);
                        mOnClick = true;
                    }
                    // 오른쪽으로 이동
                    for (int i = left + 1; i < mResPosition[top].length; i++) {
                        if (mResPosition[top][i] != 0) {
                            if (isWhite(mResPosition[top][i]) != white) {
                                showX(view, top, left, top, i, mResPosition[top][i]);
                            }
                            break;
                        }
                        showX(view, top, left, top, i, mResPosition[top][i]);
                        mOnClick = true;
                    }

                    // 위 왼쪽
                    for (int i = top - 1, j = left - 1; i >= 0 && j >= 0; i--, j--) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }
                    // 위 오른쪽
                    for (int i = top - 1, j = left + 1; i >= 0 && j < mResPosition[top].length; i--, j++) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }
                    // 아래 왼쪽
                    for (int i = top + 1, j = left - 1; i < mResPosition.length && j >= 0; i++, j--) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }
                    // 아래 왼쪽
                    for (int i = top + 1, j = left + 1; i < mResPosition.length && j < mResPosition[top].length; i++, j++) {
                        if (mResPosition[i][j] != 0) {
                            if (isWhite(mResPosition[i][j]) != white) {
                                showX(view, top, left, i, j, mResPosition[i][j]);
                            }
                            break;
                        }
                        showX(view, top, left, i, j, mResPosition[i][j]);
                        mOnClick = true;
                    }

                    return;
                }
            }
        }
    }

    private void moveKing(View view) {
        boolean white = isWhite(view.getId());

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    // 위쪽
                    if (top > 0) {
                        if (mResPosition[top - 1][left] == 0 || isWhite(mResPosition[top - 1][left]) != white) {
                            showX(view, top, left, top - 1, left, mResPosition[top - 1][left]);
                            mOnClick = true;
                        }
                    }
                    // 왼쪽
                    if (left > 0) {
                        if (mResPosition[top][left - 1] == 0 || isWhite(mResPosition[top][left - 1]) != white) {
                            showX(view, top, left, top, left - 1, mResPosition[top][left - 1]);
                            mOnClick = true;
                        }
                    }
                    // 아래쪽
                    if (top < mResPosition.length - 1) {
                        if (mResPosition[top + 1][left] == 0 || isWhite(mResPosition[top + 1][left]) != white) {
                            showX(view, top, left, top + 1, left, mResPosition[top + 1][left]);
                            mOnClick = true;
                        }
                    }
                    // 오른쪽
                    if (left < mResPosition[top].length - 1) {
                        if (mResPosition[top][left + 1] == 0 || isWhite(mResPosition[top][left + 1]) != white) {
                            showX(view, top, left, top, left + 1, mResPosition[top][left + 1]);
                            mOnClick = true;
                        }
                    }

                    // 위 왼쪽
                    if (top > 0 && left > 0) {
                        if (mResPosition[top - 1][left - 1] == 0 || isWhite(mResPosition[top - 1][left - 1]) != white) {
                            showX(view, top, left, top - 1, left - 1, mResPosition[top - 1][left - 1]);
                            mOnClick = true;
                        }
                    }
                    // 위 오른쪽
                    if (top > 0 && left < mResPosition[top].length - 1) {
                        if (mResPosition[top - 1][left + 1] == 0 || isWhite(mResPosition[top - 1][left + 1]) != white) {
                            showX(view, top, left, top - 1, left + 1, mResPosition[top - 1][left + 1]);
                            mOnClick = true;
                        }
                    }
                    // 아래 왼쪽
                    if (top < mResPosition.length - 1 && left > 0) {
                        if (mResPosition[top + 1][left - 1] == 0 || isWhite(mResPosition[top + 1][left - 1]) != white) {
                            showX(view, top, left, top + 1, left - 1, mResPosition[top + 1][left - 1]);
                            mOnClick = true;
                        }
                    }
                    // 아래 오른쪽
                    if (top < mResPosition.length - 1 && left < mResPosition[top].length - 1) {
                        if (mResPosition[top + 1][left + 1] == 0 || isWhite(mResPosition[top + 1][left + 1]) != white) {
                            showX(view, top, left, top + 1, left + 1, mResPosition[top + 1][left + 1]);
                            mOnClick = true;
                        }
                    }

                    return;
                }
            }
        }
    }


    private void showX(final View view, final int originalTop, final int originalLeft, final int top, final int left, final int resId) {
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

        TextView textView = new TextView(this);
        textView.setText("X");

        final RelativeLayout.LayoutParams layoutParamsX =
                new RelativeLayout.LayoutParams(
                        (int) (25 * getResources().getDisplayMetrics().density),
                        (int) (25 * getResources().getDisplayMetrics().density)
                );

        layoutParamsX.topMargin = top * 25 * (int) getResources().getDisplayMetrics().density;
        layoutParamsX.leftMargin = left * 25 * (int) getResources().getDisplayMetrics().density;

        textView.setLayoutParams(layoutParamsX);
        textView.setGravity(Gravity.CENTER);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutParams.topMargin = layoutParamsX.topMargin;
                layoutParams.leftMargin = layoutParamsX.leftMargin;
                view.setLayoutParams(layoutParams);

                for (int i = 0; i < mListTextX.size(); i++) {
                    TextView temp = mListTextX.get(i);
                    mRelativeLayout.removeView(temp);
                }
                mListTextX.clear();

                mResPosition[originalTop][originalLeft] = 0;
                mResPosition[top][left] = view.getId();

                if (resId != 0) {
                    View originalView = findViewById(resId);
                    mRelativeLayout.removeView(originalView);

                    if (isWhite(resId)) {
                        mLayoutBlack.addView(originalView);
                    } else {
                        mLayoutWhite.addView(originalView);
                    }

                    if (resId == R.id.imgWhiteKing) {
                        // black win
                        AlertDialog.Builder builder = new AlertDialog.Builder(GamePlayActivity.this);
                        builder.setTitle(R.string.dialog_title);
                        builder.setMessage(R.string.dialog_message_black_win);
                        builder.setCancelable(false);
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 확인 눌렀을 때
                                finish();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (resId == R.id.imgBlackKing) {
                        // white win
                        AlertDialog.Builder builder = new AlertDialog.Builder(GamePlayActivity.this);
                        builder.setTitle(R.string.dialog_title);
                        builder.setMessage(R.string.dialog_message_white_win);
                        builder.setCancelable(false);
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 확인 눌렀을 때
                                finish();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }

                String tag = (String) view.getTag();
                Log.d("GamePlayActivity", "tag: " + tag + " top:" + top + " left:" + left + " white:" + mWhite);
                if (tag.equals("pawn") && ((mWhite && top == 0) || (!mWhite && top == mResPosition.length - 1))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GamePlayActivity.this);
                    builder.setTitle("promotion");
                    builder.setCancelable(false);
                    builder.setSingleChoiceItems(promotion, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ImageView imageView = (ImageView) view;
                            switch (i) {
                                case 0:
                                    if (mWhite) {
                                        imageView.setImageResource(R.drawable.blackqueen);
                                        imageView.setTag("queen");
                                    } else {
                                        imageView.setImageResource(R.drawable.queen);
                                        imageView.setTag("queen");
                                    }
                                    break;
                                case 1:
                                    if (mWhite) {
                                        imageView.setImageResource(R.drawable.blackknight);
                                        imageView.setTag("knight");
                                    } else {
                                        imageView.setImageResource(R.drawable.knight);
                                        imageView.setTag("knight");
                                    }
                                    break;
                                case 2:
                                    if (mWhite) {
                                        imageView.setImageResource(R.drawable.blackbishop);
                                        imageView.setTag("bishop");
                                    } else {
                                        imageView.setImageResource(R.drawable.bishop);
                                        imageView.setTag("bishop");
                                    }
                                    break;
                                case 3:
                                    if (mWhite) {
                                        imageView.setImageResource(R.drawable.blackrook);
                                        imageView.setTag("rook");
                                    } else {
                                        imageView.setImageResource(R.drawable.rook);
                                        imageView.setTag("rook");
                                    }
                                    break;
                            }
                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                mOnClick = false;
                mWhite = !mWhite;
            }
        });
        mRelativeLayout.addView(textView);
        mListTextX.add(textView);
    }

    private boolean isWhite(int resId) {

        switch (resId) {
            case R.id.imgWhitePawn1:
            case R.id.imgWhitePawn2:
            case R.id.imgWhitePawn3:
            case R.id.imgWhitePawn4:
            case R.id.imgWhitePawn5:
            case R.id.imgWhitePawn6:
            case R.id.imgWhitePawn7:
            case R.id.imgWhitePawn8:

            case R.id.imgWhiteRook1:
            case R.id.imgWhiteKnight1:
            case R.id.imgWhiteBishop1:
            case R.id.imgWhiteQueen:
            case R.id.imgWhiteKing:
            case R.id.imgWhiteBishop2:
            case R.id.imgWhiteKnight2:
            case R.id.imgWhiteRook2:
                return true;

            default:
                return false;
        }
    }
}
