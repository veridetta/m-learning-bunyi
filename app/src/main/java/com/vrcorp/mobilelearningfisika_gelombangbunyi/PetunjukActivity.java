package com.vrcorp.mobilelearningfisika_gelombangbunyi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baoyachi.stepview.VerticalStepView;

import java.util.ArrayList;
import java.util.List;

public class PetunjukActivity extends AppCompatActivity {
    VerticalStepView verticalStepView;
    CardView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk);
        verticalStepView = findViewById(R.id.step_petunjuk);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetunjukActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        List<String> list0 = new ArrayList<>();
        list0.add("Buka menu “Materi” untuk mempelajari materi dengan seksama");
        list0.add("Kerjakan latihan soal untuk mengasah kemampuan dan mengetahui perkembangan belajar");
        list0.add("Catatlah semua kesulitan yang anda alami dalam mempelajari M-learning dan tanyakan pada instruktur/guru");
        verticalStepView.setStepsViewIndicatorComplectingPosition(list0.size() - 1)//设置完成的步数
                .setTextSize(16)
                .setStepViewUnComplectedTextColor(R.color.grey_800)
                .reverseDraw(false)//default is true
                .setStepViewTexts(list0)//总步骤
                .setLinePaddingProportion(0.85f)//设置indicator线与线间距的比例系数
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getApplicationContext(), R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getApplicationContext(), R.color.grey_400))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.grey_400))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.attention));//设置StepsViewIndicator AttentionIcon
    }
}
