package com.example.zhuzhuxia.crosscountryevents.Talk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhuzhuxia.crosscountryevents.R;

import java.util.List;

/**
 * Created by zhuzhuxia on 2018/2/26.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {
    private int resourceId;

    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
        resourceId =  resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article fruit = getItem(position);
        View view;
        ViewHodler viewHodler;
        if(convertView ==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHodler = new ViewHodler();
            viewHodler.ArticleImage = (ImageView)view.findViewById(R.id.artitle_image);
            viewHodler.ArticleName = (TextView)view.findViewById(R.id.artitle_name);
            view.setTag(viewHodler);
        }else{
            view = convertView;
            viewHodler = (ViewHodler)view.getTag();
        }

        viewHodler.ArticleImage.setImageResource(fruit.getImageId());
        viewHodler.ArticleName.setText(fruit.getName());
        return view;
    }

    class ViewHodler{
        ImageView ArticleImage;
        TextView  ArticleName;
    }
}
