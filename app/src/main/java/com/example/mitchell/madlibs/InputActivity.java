package com.example.mitchell.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InputActivity extends AppCompatActivity {

    // declaring variables
    public static final String intentArg = "storyText";
    int wordsLeft;
    TextView amountWords;
    Story story;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Intent intent = getIntent();
        story = (Story)intent.getSerializableExtra("story");
        // calling the functions
        wordsCounter();
        hints();
    }

    public void onSubmit(View view) {
        // look at the edittext and get the word the user typed in
        EditText userInput = (EditText) findViewById(R.id.editText);
        String input = userInput.getText().toString();
        // checks if the user typed a word
        if (!input.equals("")) {
            // fill in the word
            story.fillInPlaceholder(input);
            // clear edittext
            userInput.setText("");
            Toast.makeText(this, "Keep going!", Toast.LENGTH_SHORT).show();
            // if the amount of words is reached go to next activity
            if (story.getPlaceholderRemainingCount() == 0) {
                Intent intent = new Intent(this, OutputActivity.class);
                intent.putExtra(intentArg, story.toString());
                this.startActivity(intent);
            }
            wordsCounter();
            hints();
        }
        else{
            Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
        }
    }

    // keeps track of the amount of words the user need to fill in
    public void wordsCounter(){
        wordsLeft = story.getPlaceholderRemainingCount();
        String words = wordsLeft + " word(s) left";
        amountWords = (TextView) findViewById(R.id.amountWords);
        amountWords.setText(words);
    }

    // tells the user what kind of word they need to type
    private void hints(){
        EditText userInput = (EditText) findViewById(R.id.editText);
        String hint = story.getNextPlaceholder();
        userInput.setHint("Please type a/an " + hint);
    }

}
