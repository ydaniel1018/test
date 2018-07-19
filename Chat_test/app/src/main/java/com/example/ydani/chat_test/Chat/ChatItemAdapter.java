package com.example.ydani.chat_test.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ydani.chat_test.R;

import java.util.ArrayList;

/***************************************************************************************************
 **************************************************************************************************/


public class ChatItemAdapter extends BaseAdapter {
    ArrayList<MessageItem> MSGList;
    ArrayList<View> viewList;
    Context context;
    public ChatItemAdapter(Context context, ArrayList arrayList){
        super();
        MSGList = arrayList;
        this.context = context;
        viewList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return MSGList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return MSGList.get(i).makeView(view, viewGroup);
        
    }

    @Override
    public MessageItem getItem(int i) {
        return MSGList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addMessageItem(MessageItem messageItem){
        MSGList.add(messageItem);
    }
}
