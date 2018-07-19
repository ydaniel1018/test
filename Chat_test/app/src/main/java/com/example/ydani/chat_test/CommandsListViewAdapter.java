package com.example.ydani.chat_test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ydani on 2018-04-05.
 */

public class CommandsListViewAdapter extends BaseAdapter {
    ArrayList<CommandsListItem> listItems;
    Context context;
    ActivityAdd activityAdd;
    static final int DIALOG_CUSTOM_ID = 0;

    public CommandsListViewAdapter(Context context, ActivityAdd activityAdd, ArrayList<String> commands, ArrayList<String> answer) {
        this.context = context;
        this.activityAdd = activityAdd;
        listItems = new ArrayList<>();
        for (int index = 0; index < commands.size(); index++) {
            CommandsListItem item = new CommandsListItem(commands.get(index), answer.get(index));
            listItems.add(item);
        }
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public CommandsListItem getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.commands_list_item_layout, parent, false);
        }

        TextView command = (TextView) convertView.findViewById(R.id.command);
        TextView answer = (TextView) convertView.findViewById(R.id.answer);

        CommandsListItem item = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        command.setText(item.command);
        answer.setText(item.answer);

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                createDeleteDialog(position);

                return false;
            }
        });

        return convertView;
    }

    protected void createDeleteDialog(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activityAdd.commandManager.removeCommand(id);
                activityAdd.reloadListAdater();
            }
        });
        builder.setMessage("가르친 말을 잊게 하시겠습니까?");
        builder.setTitle("삭제");
        builder.create();
        builder.show();
    }
}