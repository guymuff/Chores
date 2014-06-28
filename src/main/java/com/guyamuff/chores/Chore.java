package com.guyamuff.chores;

/**
 * Created by Arch Stanton on 9/28/13.
 */
public class Chore implements Comparable<Chore> {
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
        if (previousTime == 0) {
            previousTime = lastCompleted;
            lastCompleted = System.currentTimeMillis();
        } else {
            lastCompleted = previousTime;
            previousTime = 0;
        }
    }

    boolean isCompletedToday() {
        return previousTime != 0;
    }

    @Override
    public int compareTo(Chore another) {
        return (int) (this.id - another.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chore)) return false;

        Chore chore = (Chore) o;

        if (id != chore.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
