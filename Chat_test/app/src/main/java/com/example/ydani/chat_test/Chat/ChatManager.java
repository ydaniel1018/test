package com.example.ydani.chat_test.Chat;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ChatManager {
    ArrayList<MessageItem> chat_list;
    Activity activity;
    final String CHATLIST = "Chat_List.txt";

    public ChatManager(Activity activity){
        chat_list = new ArrayList<>();
        this.activity = activity;
        //saveChat();
        loadChat();
    }
    public void saveChat(){
        File file = new File(activity.getFilesDir(), CHATLIST);

        BufferedWriter bufferedWriter;
        try {
            bufferedWriter =  new BufferedWriter(new FileWriter(file));

            for(int index = 0; index < chat_list.size(); index++)
                bufferedWriter.write((chat_list.get(index).msg)+"\n");

            bufferedWriter.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public void loadChat() {
        File file = new File(activity.getFilesDir(), CHATLIST);

        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader(file));

            while(bufferedReader.ready()){
                chat_list.add(new UserMessageItem(activity.getApplicationContext(), "User", bufferedReader.readLine()));
                chat_list.add(new BotMessageItem(activity.getApplicationContext(), "Bot", bufferedReader.readLine()));
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            chat_list = new ArrayList<>();
            saveChat();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addMessageItem(MessageItem messageItem){
        chat_list.add(messageItem);
    }
    public ArrayList<MessageItem> getChat_list(){return (ArrayList<MessageItem>)this.chat_list.clone();}
}
