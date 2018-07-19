package com.example.ydani.chat_test;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by ydani on 2018-03-15.
 */

public class CommandManager {
    final String COMMANDS = "commands.txt";
    final String ANSWERS = "answers.txt";
    ArrayList<String> commands;
    ArrayList<String> answers;
    Activity activity;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public CommandManager(Activity act){
        this.activity = act;
        commands = new ArrayList<>();
        answers = new ArrayList<>();
        initFireBase();
    }
    public CommandManager(Activity act, ArrayList<String> commands, ArrayList<String> answers){
        this.activity = act;
        this.commands = commands;
        this.answers = answers;
        initFireBase();
    }
    void initFireBase(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("command");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                boolean isEqual = false;
                for(int index = 0; index < commands.size(); index++){
                    if(commands.get(index).equals(dataSnapshot.getKey())){
                        isEqual = true;
                    }
                }
                if(!isEqual){
                    commands.add(dataSnapshot.getKey());
                    answers.add(dataSnapshot.getValue().toString());
                    //Toast.makeText(activity.getApplicationContext(), "value is = "+dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                    isEqual = false;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//    public void saveCommand(){
//        File file1 = new File(activity.getFilesDir(), COMMANDS);
//        File file2 = new File(activity.getFilesDir(), ANSWERS);
//
//        FileWriter fileWriter;
//        try {
//            fileWriter =  new FileWriter(file1);
//
//            for(int index = 0; index < commands.size(); index++)
//                fileWriter.write(commands.get(index)+"\n");
//
//            fileWriter.close();
//            /////////////////////////////////////////////////////////////////////////////////////
//            fileWriter =  new FileWriter(file2);
//
//            for(int index = 0; index < answers.size(); index++)
//                fileWriter.write(answers.get(index)+"\n");
//
//            fileWriter.close();
//
//        } catch (FileNotFoundException e) {
//
//            e.printStackTrace();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    public void loadCommand(){
//        File file1 = new File(activity.getFilesDir(), COMMANDS);
//        File file2 = new File(activity.getFilesDir(), ANSWERS);
//        BufferedReader bufferedReader;
//
//        try {
//            bufferedReader = new BufferedReader(new FileReader(file1));
//            commands = new ArrayList<>();
//            while(bufferedReader.ready())
//                commands.add(bufferedReader.readLine());
//            bufferedReader.close();
//            /////////////////////////////////////////////////////////////////////////////////////
//            bufferedReader = new BufferedReader(new FileReader(file2));
//            answers = new ArrayList<>();
//            while(bufferedReader.ready())
//                answers.add(bufferedReader.readLine());
//            bufferedReader.close();
//
//        } catch (FileNotFoundException e) {
//            commands = new ArrayList<>();
//            commands.add(".");
//            answers = new ArrayList<>();
//            answers.add(".");
//            saveCommand();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }


    public String getAnswer(String command){
        if(commands.size() != 0) {
            for (int index = 0; index < commands.size(); index++) {
                if(commands.get(index).equals(command)){
                    if(answers.size() != 0)
                        return answers.get(index);
                    else
                        return "무언가 잘못 되었어요.";
                }
            }
            Random rnd = new Random();
            int ran = rnd.nextInt(5);
            switch (ran){
                case 0:
                    return "그 말도 제게 알려주세요";
                case 1:
                    return "이해할수없어요..";
                case 2:
                    return "뭐라는건지 모르겠네요..";
                case 3:
                    return "제가 알아들을수없는말이네요.";
                case 4:
                    return "그건 무슨 말이죠?";
                default:
                    return "??";
            }
        }
        else{
            return "아직 아무것도 몰라요";
        }



    }
    public void addCommand(String command, String answer){
        myRef.child(command).setValue(answer);
    }

    public void reset(){
        myRef.setValue(null);
    }

    public ArrayList<String> getCommands(){
        return (ArrayList<String>)commands.clone();
    }
    public ArrayList<String> getAnswers(){
        return (ArrayList<String>)answers.clone();
    }
    public void removeCommand(int position){
        myRef.child(commands.remove(position)).setValue(null);
        answers.remove(position);
    }
}
