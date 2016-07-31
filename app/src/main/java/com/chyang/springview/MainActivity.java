package com.chyang.springview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chyang.library.ShrinkScrollerView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private ShrinkScrollerView mSpringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open).setOnClickListener(this);
        findViewById(R.id.down).setOnClickListener(this);
        findViewById(R.id.up_down).setOnClickListener(this);
        mSpringView = (ShrinkScrollerView) findViewById(R.id.spv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open:
                mSpringView.snapToScreen(ShrinkScrollerView.SCREEN_OPEN);
                break;
            case R.id.down:
                mSpringView.snapToScreen(ShrinkScrollerView.SCREEN_DOWN_EXIT);
                break;
            case R.id.up_down:
                //mSpringView.snapToScreen(SpringView.SCREENT_UP_EXIT);
                break;
        }
    }
}
