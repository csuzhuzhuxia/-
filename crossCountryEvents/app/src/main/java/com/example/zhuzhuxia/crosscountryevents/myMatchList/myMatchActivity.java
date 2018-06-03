package com.example.zhuzhuxia.crosscountryevents.myMatchList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zhuzhuxia.crosscountryevents.R;
import com.example.zhuzhuxia.crosscountryevents.matchList.MatchListFragment;

public class myMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_match);

        MatchListFragment matchListFragment = (MatchListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        matchListFragment.setTitle("我的比赛");
        matchListFragment.setFlag(MatchListFragment.flag_my);

    }
}
