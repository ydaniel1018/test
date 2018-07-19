package com.example.ydani.chat_test.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ydani.chat_test.R;

/**
 * Created by ydani on 2018-05-03.
 */

public class BotMessageItem extends MessageItem {
    public BotMessageItem(Context context, String name, String msg) {
        super(context, name, msg);

    }

    @Override
    public View makeView(View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.bot_message, parent, false);
        TextView textView = (TextView)view.findViewById(R.id.msg);
        textView.setText(msg);
        return view;
    }
}
