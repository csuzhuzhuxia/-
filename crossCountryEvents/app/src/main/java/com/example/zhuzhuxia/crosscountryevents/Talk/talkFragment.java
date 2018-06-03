package com.example.zhuzhuxia.crosscountryevents.Talk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuzhuxia.crosscountryevents.R;
import com.example.zhuzhuxia.crosscountryevents.Talk.Comment.Comment;
import com.example.zhuzhuxia.crosscountryevents.Talk.Talk.Talk;
import com.example.zhuzhuxia.crosscountryevents.Talk.Talk.Talk_Adapter;
import com.example.zhuzhuxia.crosscountryevents.Talk.Talk.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhuxia on 2018/5/23.
 */

public class talkFragment extends Fragment{

    public View.OnClickListener listener;
    private List<Talk> TalkList = new ArrayList<>();
    public Talk_Adapter adapter;
    public int position;
    public static int RESULT_OK=0;
    public static int RESULT_CANCLE=1;


    //    private ImageView comment;
    private TextView hide_down;
    private EditText comment_content;
    private Button comment_send;


    private RelativeLayout rl_comment;
    private LinearLayout rl_enroll;
    private ImageButton imageView_publish;
    private View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_talk,container,false);

        initTalk();
        adapter = new Talk_Adapter(view.getContext(), R.layout.talk_item,TalkList);
        final ListView listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "点击了全部", Toast.LENGTH_SHORT).show();
            }
        });
        //ListView item 中的删除按钮的点击事件
        adapter.setOnItemDeleteClickListener(new Talk_Adapter.onItemDeleteListener() {

            @Override
            public void onThumbClick(int i) {
                Toast.makeText(view.getContext(), "点赞", Toast.LENGTH_SHORT).show();
                if(!TalkList.get(i).isThumb){
                    TalkList.get(i).thumb_number++;
                }else{
                    TalkList.get(i).thumb_number--;
                }
                TalkList.get(i).isThumb = !TalkList.get(i).isThumb;
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onComment(int i) {
                position = i;
                Toast.makeText(view.getContext(), "评论", Toast.LENGTH_SHORT).show();
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                // 显示评论框
                rl_enroll.setVisibility(View.GONE);
                rl_comment.setVisibility(View.VISIBLE);
                Toast.makeText(view.getContext(),(rl_enroll==null)+"",Toast.LENGTH_SHORT);
            }
        });

        initView();

        return view;
    }

    private void initTalk(){
        for(int i =0;i<2;i++){
            Talk apple = new Talk("Apple",R.drawable.apple_pic);
            TalkList.add(apple);
        }
    }





    private void initView() {

        hide_down = (TextView) view.findViewById(R.id.hide_down);
        comment_content = (EditText) view.findViewById(R.id.comment_content);
        comment_send = (Button) view.findViewById(R.id.comment_send);

        rl_enroll = (LinearLayout) view.findViewById(R.id.rl_enroll);
        rl_comment = (RelativeLayout) view.findViewById(R.id.rl_comment);
        imageView_publish = (ImageButton)view.findViewById(R.id.publish);
        setListener();
    }

    /**
     * 设置监听
     */
    public void setListener() {
        hide_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_comment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
            }
        });
        comment_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendComment();
            }
        });

        imageView_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PublishActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    /**
     * 发送评论
     */
    public void sendComment(){
        if(comment_content.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            // 生成评论数据
            Comment comment = new Comment();

            User user = new User(1,"name");
            comment.setUser(user);
            comment.setContent(comment_content.getText().toString());
            TalkList.get(position).comments.add(comment);
            // 发送完，清空输入框
            comment_content.setText("");

            adapter.adapter_comment.notifyDataSetChanged();

            Toast.makeText(view.getContext().getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }

    public void publish(Talk talk){
        TalkList.add(talk);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("dataReturn");
                    Talk talk = new Talk("zhuzhuxia",R.drawable.watermelon_pic);
                    talk.comments = new ArrayList<Comment>();
                    talk.text = returnData;
                    publish(talk);
                }else if(requestCode == RESULT_CANCLE){

                }
                break;
            default:
                break;
        }
    }
}
