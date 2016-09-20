package com.app.newsdemo.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.newsdemo.R;
import com.app.newsdemo.bean.News;
import com.app.newsdemo.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * @author: liyabin
 * @description:
 * @projectName: NewsDemo
 * @date: 2016-09-18
 * @time: 19:57
 */
public class Myadapter extends BaseAdapter {

    private ArrayList<News> list;
    private Context context;

    public Myadapter(ArrayList<News> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
           // holder.thumbnail_pic_s = (SmartImageView) convertView.findViewById(R.id.imgsrc);
            holder.thumbnail_pic_s = (ImageView) convertView.findViewById(R.id.imgsrc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate());
        holder.author_name.setText(list.get(position).getAuthor_name());

        //加载图片方式一：SmartImageView
        //holder.thumbnail_pic_s.setImageUrl(list.get(position).getThumbnail_pic_s());


        //加载图片方式二：开启ImageTask异步加载图片
        // 设置让iv在图片下载等待过程中显示提示图片
        holder.thumbnail_pic_s.setImageResource(R.mipmap.ic_launcher);
        //通过setTag方法让iv上存储当前图片的下载网址
        holder.thumbnail_pic_s.setTag(list.get(position).getThumbnail_pic_s());
        new ImageTask(holder.thumbnail_pic_s).execute(list.get(position).getThumbnail_pic_s());
        return convertView;
    }

    class ViewHolder{
        TextView title;
        TextView date;
        TextView author_name;
        ImageView thumbnail_pic_s;
        //SmartImageView thumbnail_pic_s;
    }
}
