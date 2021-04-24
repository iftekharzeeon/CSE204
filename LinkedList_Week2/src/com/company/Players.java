package com.company;

public class Players {
    private int key;
    private int time;
    Players next;
    Players previous;

    public Players(int key, int time) {
        this.key = key;
        this.time = time;
        this.next = null;
        this.previous = null;
    }

    public int getKey() {
        return key;
    }

    public int getTime() {
        return time;
    }
}
