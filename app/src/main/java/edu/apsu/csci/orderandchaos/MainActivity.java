/*
    Daniel Davis
    CSCI 4010
    25 November 2019
 */

package edu.apsu.csci.orderandchaos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup layout = findViewById(R.id.main_menu);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aboutButton:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.moreButton:
                Uri uri = Uri.parse("http://en.wikipedia.org/wiki/Order_and_Chaos");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.playButton:
                intent = new Intent(getApplicationContext(), PlayGameActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
