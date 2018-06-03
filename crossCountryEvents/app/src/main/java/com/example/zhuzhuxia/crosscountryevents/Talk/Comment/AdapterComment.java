package com.example.zhuzhuxia.crosscountryevents.Talk.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zhuzhuxia.crosscountryevents.R;

import java.util.List;

/**
 * Created by zhuzhuxia on 2018/2/27.
 */

public class AdapterComment extends ArrayAdapter<Comment> {

    private int resourceId;

    public AdapterComment(@NonNull Context context, int resource, @NonNull List<Comment> objects) {
        super(context, resource, objects);
        resourceId =  resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment comment = getItem(position);
        View view;
        Comment_ViewHodler viewHodler;
        if(convertView ==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHodler= new Comment_ViewHodler();
            viewHodler.comment_name = (TextView) view.findViewById(R.id.comment_name);
            viewHodler.comment_content = (TextView)view.findViewById(R.id.comment_content);
            view.setTag(viewHodler);
        }else{
            view = convertView;
            viewHodler = (Comment_ViewHodler)view.getTag();
        }

        viewHodler.comment_name.setText(comment.user.user_name);
        viewHodler.comment_content.setText(comment.text);
        return view;
    }

}
