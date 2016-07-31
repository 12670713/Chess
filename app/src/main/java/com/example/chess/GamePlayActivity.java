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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.layout);
    }

    public void onClickPawn(final View view) {
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

        switch (view.getId()) {
            case R.id.imgWhitePawn1:
            case R.id.imgWhitePawn2:
            case R.id.imgWhitePawn3:
            case R.id.imgWhitePawn4:
            case R.id.imgWhitePawn5:
            case R.id.imgWhitePawn6:
            case R.id.imgWhitePawn7:
            case R.id.imgWhitePawn8:
                TextView textView = new TextView(this);
                textView.setText("X");

                final RelativeLayout.LayoutParams layoutParamsX =
                        new RelativeLayout.LayoutParams(
                                (int) (25 * getResources().getDisplayMetrics().density),
                                (int) (25 * getResources().getDisplayMetrics().density)
                        );

                layoutParamsX.topMargin = layoutParams.topMargin - (int) (25 * getResources().getDisplayMetrics().density);
                layoutParamsX.leftMargin = layoutParams.leftMargin;

                textView.setLayoutParams(layoutParamsX);
                textView.setGravity(Gravity.CENTER);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutParams.topMargin = layoutParamsX.topMargin;
                        layoutParams.leftMargin = layoutParamsX.leftMargin;
                        view.setLayoutParams(layoutParams);
                        mRelativeLayout.removeView(v);
                    }
                });
                mRelativeLayout.addView(textView);

                break;
        }
    }
}
