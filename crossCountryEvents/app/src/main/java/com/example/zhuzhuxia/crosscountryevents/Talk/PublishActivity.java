package com.example.zhuzhuxia.crosscountryevents.Talk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zhuzhuxia.crosscountryevents.R;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener{

    public EditText editText;
    public Button publish_button;
    public Button cancle_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }

        editText = (EditText)findViewById(R.id.edit_talk);
        publish_button = (Button)findViewById(R.id.publish_button);
        cancle_button = (Button)findViewById(R.id.cancle_button);


        setListener();
    }


    public void setListener(){
        publish_button.setOnClickListener(this);
        cancle_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.publish_button:
                Intent intent = new Intent();
                intent.putExtra("dataReturn",editText.getText().toString());
                setResult(talkFragment.RESULT_OK,intent);
                finish();
                break;
            case R.id.cancle_button:
                break;
            default:
                break;

        }
    }
}
