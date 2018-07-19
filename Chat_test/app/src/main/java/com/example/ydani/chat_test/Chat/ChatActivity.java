package com.example.ydani.chat_test.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ydani.chat_test.ActivityAdd;
import com.example.ydani.chat_test.CommandManager;
import com.example.ydani.chat_test.R;

public class ChatActivity extends AppCompatActivity {
    EditText ed;
    ListView chat_listview;
    Button button;
    Button Delete;
    ChatItemAdapter adapter;
    ChatManager chatManager;
    InputMethodManager inputMethodManager;

    CommandManager commandManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("인공지능과의 채팅");


        inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        button = (Button)findViewById(R.id.button);
        ed = (EditText)findViewById(R.id.ed) ;
        chat_listview = (ListView) findViewById(R.id.command_list);
        commandManager = new CommandManager(this);
        chatManager = new ChatManager(this);
        Delete = (Button)findViewById(R.id.button_del);

        adapter = new ChatItemAdapter(this, chatManager.getChat_list());
        chat_listview.setAdapter(adapter);

        Delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ed.setText("");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserMessageItem userMessegeItem = new UserMessageItem(getApplicationContext(), "USER", ed.getText().toString());
                BotMessageItem botMessegeItem = new BotMessageItem(getApplicationContext(), "BOT", commandManager.getAnswer(ed.getText().toString()));

                adapter.addMessageItem(userMessegeItem);
                chatManager.addMessageItem(userMessegeItem);

                adapter.addMessageItem(botMessegeItem);
                chatManager.addMessageItem(botMessegeItem);
                ed.setText("");


                inputMethodManager.hideSoftInputFromWindow(ed.getWindowToken(),0);
                chat_listview.smoothScrollToPosition(chat_listview.getMaxScrollAmount());
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_commands){
            Intent intent = new Intent(this.getApplicationContext(), ActivityAdd.class);

            intent.putExtra("commands", commandManager.getCommands());
            intent.putExtra("answers", commandManager.getAnswers());
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        commandManager = new CommandManager(this);
    }

    @Override
    protected void onStop(){
        chatManager.saveChat();
        super.onStop();

    }
}
