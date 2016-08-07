package com.example.chess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GamePlayActivity extends AppCompatActivity {

    RelativeLayout mRelativeLayout;
    int[][] mResPosition;
    boolean mOnClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.layout);
        initPosition();
    }

    public void onClickMove(View view) {
        String tag = (String) view.getTag();

        switch (tag) {
            case "pawn":
                movePawn(view);
                break;
            case "rook" :
                moveRook(view);
                break;
            case "knight" :
                break;
            case "bishop" :
                break;
            case "queen" :
                break;
            case "king" :
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
        if (mOnClick) {
            return;
        }

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    if (top > 0 && mResPosition[top-1][left] == 0) {
                        showX(view, top, left, top-1, left);
                        mOnClick = true;
                        return;
                    }
                }
            }
        }
    }

    private void moveRook(View view) {
        if (mOnClick) {
            return;
        }

        for (int top = 0; top < mResPosition.length; top++) {
            for (int left = 0; left < mResPosition[top].length; left++) {
                if (mResPosition[top][left] == view.getId()) {
                    // 위로 이동
                    for (int i = top - 1; i > 0; i--) {
                        if (mResPosition[i][left] != 0) {
                            break;
                        }
                        showX(view, top, left, i, left);
                        mOnClick = true;
                    }

                    // 아래로 이동
                    for (int i = top + 1; i < mResPosition.length; i++) {
                        if (mResPosition[i][left] != 0) {
                            break;
                        }
                        showX(view, top, left, i, left);
                        mOnClick = true;
                    }

                    // 왼쪽으로 이동
                    for (int i = left - 1; i > 0; i--) {
                        if (mResPosition[top][i] != 0) {
                            break;
                        }
                        showX(view, top, left, top, i);
                        mOnClick = true;
                    }

                    // 오른쪽으로 이동
                    for (int i = left + 1; i < mResPosition[top].length; i++) {
                        if (mResPosition[top][i] != 0) {
                            break;
                        }
                        showX(view, top, left, top, i);
                        mOnClick = true;
                    }

                    return;
                }
            }
        }
    }

    private void showX(final View view, final int originalTop, final int originalLeft, final int top, final int left) {
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
                mRelativeLayout.removeView(v);
                mResPosition[originalTop][originalLeft] = 0;
                mResPosition[top][left] = view.getId();

                mOnClick = false;
            }
        });
        mRelativeLayout.addView(textView);
    }
}
