package com.example.mitchell.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomStory();
        story = generateStory();
    }

    //  go to next activity
    public void onSubmit(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra("story",story);
        this.startActivity(intent);
    }

    // makes a arraylist with the stories and gives a random story from that list
    public String randomStory() {
        ArrayList<String> fileNames = new ArrayList<>();
        fileNames.add("stories/madlib0_simple.txt");
        fileNames.add("stories/madlib1_tarzan.txt");
        fileNames.add("stories/madlib2_university.txt");
        fileNames.add("stories/madlib3_clothes.txt");
        fileNames.add("stories/madlib4_dance.txt");

        Random rand = new Random();
        int n = rand.nextInt(fileNames.size() - 1);
        return fileNames.get(n);
    }

    public Story generateStory() {
        try {
            // get a random filename and make an instance of the class Story
            story = new Story(getAssets().open(randomStory()));
        }
        catch (IOException e) {
            //log error
        }
        return story;
    }
}
