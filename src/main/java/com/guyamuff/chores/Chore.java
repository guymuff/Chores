package com.guyamuff.chores;

/**
 * Created by Arch Stanton on 9/28/13.
 */
public class Chore {
    long id;
    String name;
    long frequency;
    long lastCompleted;

    long previousTime;

    @Override
    public String toString() {
        return name;
    }

    void toggleLastCompleted() {
        if(previousTime == 0) {
            previousTime = lastCompleted;
            lastCompleted = System.currentTimeMillis();
        } else {
            lastCompleted = previousTime;
            previousTime = 0;
        }


    }
}
