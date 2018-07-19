package com.example.ydani.chat_test.Chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ydani on 2018-05-03.
 */

abstract public class MessageItem extends View {
    String name;
    String msg;
    Context context;
    public MessageItem(Context context, String  name, String msg) {
        super(context);
        this.name = name;
        this.msg = msg;
        this.context = context;
    }

    abstract public View makeView(View view, ViewGroup parent);
}
