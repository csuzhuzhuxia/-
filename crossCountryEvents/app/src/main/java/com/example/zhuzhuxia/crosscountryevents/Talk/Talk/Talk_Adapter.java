package com.example.zhuzhuxia.crosscountryevents.Talk.Talk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhuzhuxia.crosscountryevents.R;
import com.example.zhuzhuxia.crosscountryevents.Talk.Comment.AdapterComment;
import com.example.zhuzhuxia.crosscountryevents.Talk.Comment.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuzhuxia on 2018/2/26.
 */

public class Talk_Adapter extends ArrayAdapter<Talk>  {
    private int resourceId;

    public AdapterComment adapter_comment;




    public Talk_Adapter(@NonNull Context context, int resource, @NonNull List<Talk> talkList) {
        super(context, resource, talkList);
        resourceId = resource;
        for (int i =0;i<talkList.size();i++){
            talkList.get(i).comments = new ArrayList<Comment>();
        }
        talkList.get(1).comments.add(new Comment(new User(1,"zhuzhuxia"),"hello"));
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Talk talk = getItem(position);
        View view;
        ViewHodler viewHodler;
        if(convertView ==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHodler = new ViewHodler();
            viewHodler.user_Image = (ImageView)view.findViewById(R.id.talk_image);
            viewHodler.user_Name = (TextView)view.findViewById(R.id.talk_name);
            viewHodler.user_Text = (TextView)view.findViewById(R.id.talk_text);
            viewHodler.comment_list = (ListView)view.findViewById(R.id.talk_comment_list);
            viewHodler.user_time = (TextView)view.findViewById(R.id.talk_time);
            viewHodler.comment_button = (ImageButton)view.findViewById(R.id.talk_comment_flag);
            viewHodler.thumb_button = (ImageButton)view.findViewById(R.id.thumb);
            viewHodler.number_view = (TextView)view.findViewById(R.id.thumb_number);
            view.setTag(viewHodler);
        }else{
            view = convertView;
            viewHodler = (ViewHodler)view.getTag();
        }

        viewHodler.user_Image.setImageResource(talk.user.user_id);
        viewHodler.user_Name.setText(talk.user.user_name);
        viewHodler.user_Text.setText(talk.text);
        viewHodler.user_time.setText(getTime());
        viewHodler.number_view.setText(talk.thumb_number+"");

        //  这里就类似之前在activity中设置我们的list

        adapter_comment = new AdapterComment(view.getContext(), R.layout.comment_item,talk.comments);
        viewHodler.comment_list.setAdapter(adapter_comment);



        viewHodler.thumb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemDeleteListener.onThumbClick(position);
            }
        });

        viewHodler.comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemDeleteListener.onComment(position);
            }
        });


        return view;
//        return super.getView(position, convertView, parent);
    }
    public String getTime() {
        long time = System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        return t1;
    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onThumbClick(int i);
        void onComment(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }








}

