package com.example.ydani.chat_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ydani on 2017-12-20.
 */

public class ActivityAdd extends AppCompatActivity {

    EditText ed;
    EditText ed2;
    Button btn;
    ListView listView;
    CommandsListViewAdapter viewAdapter;


    CommandManager commandManager;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("인공지능 학습");

        commandManager = new CommandManager(this,  (ArrayList<String>)getIntent().getSerializableExtra("commands"),  (ArrayList<String>)getIntent().getSerializableExtra("answers"));
        ed = (EditText)findViewById(R.id.edit1);
        ed2 = (EditText)findViewById(R.id.edit2);
        btn = (Button)findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandManager.addCommand(ed.getText().toString(), ed2.getText().toString());

                finish();
            }
        });

        listView = (ListView) findViewById(R.id.add_list);
        viewAdapter = new CommandsListViewAdapter(this, this, commandManager.getCommands(), commandManager.getAnswers());
        listView.setAdapter(viewAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });
    }

    public void reloadListAdater(){
        CommandsListViewAdapter viewAdapter = new CommandsListViewAdapter(this, this, commandManager.getCommands(), commandManager.getAnswers());
        listView.setAdapter(viewAdapter);
    }

}
