package com.pillar.ind.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pillar.ind.ui.MainActivity;
import com.pillar.ind.R;

public class PickUp extends AppCompatActivity {
    Button next_bt;
    TextView policy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);
        next_bt = findViewById(R.id.button);
        final Intent intent = new Intent(this, MainActivity.class);
        next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun",false).apply();
                startActivity(intent);
            }
        });
        policy = findViewById(R.id.textView7);
        policy.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        String htmlTaggedString  = "<u>Политикой Конфиденциальности</u>";
        Spanned textSpan  =  android.text.Html.fromHtml(htmlTaggedString);
        policy.setText(textSpan);
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Privacy_policy.class));
            }
        });
    }




}