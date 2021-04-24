package com.company;


public class PillowGame {
    private Players head;

    public PillowGame() {
        this.head = null;
    }

    public void append(Players p) {
        Players ptr = this.head;
        if (ptr == null) {
            this.head = p;
            head.previous = head;
            head.next = head;
            return;
        }
        if (ptr.next == head) {
            head.next = p;
            p.next = head;
            p.previous = head;
            head.previous = null;
        } else {
            while (ptr.next != head) {
                ptr = ptr.next;
            }
            p.next = ptr.next;
            ptr.next = p;
            p.previous = ptr;
            head.previous = p;
        }
    }

    public int removePlayer(int time) {
        int key = pillowHolder(time);
        Players newHead = this.head;
        Players ptr = this.head;
        if (Main.ltr) {
            while (newHead.previous.getKey() != key) {
                newHead = newHead.next;
            }
            if (ptr.getKey() == key) {
                Players last = ptr;
                while (last.next != head) {
                    last = last.next;
                }
                last.next = ptr.next;
                ptr.next.previous = last;
                head = ptr.next;
                System.out.println("Player " + key + " has been eliminated at t=" + Main.currentTime);
                return head.getKey();
            }
            while (ptr.next.getKey() != key) {
                ptr = ptr.next;
            }
            Players temp = ptr.next;
            ptr.next = temp.next;
            temp.next.previous = ptr;

        } else {
            while (newHead.next.getKey() != key) {
                newHead = newHead.previous;
            }
            if (ptr.getKey() == key) {
                Players last = ptr;
                while (last.previous != head) {
                    last = last.previous;
                }
                last.previous = ptr.previous;
                ptr.previous.next = last;
                head = ptr.previous;
                System.out.println("Player " + key + " has been eliminated at t=" + Main.currentTime);
                return head.getKey();
            }
            while (ptr.previous.getKey() != key) {
                ptr = ptr.previous;
            }
            Players temp = ptr.previous;
            ptr.previous = temp.previous;
            temp.previous.next = ptr;
        }
        head = newHead;
        System.out.println("Player " + key + " has been eliminated at t=" + Main.currentTime);
        return head.getKey();
    }

    public void showPlayer(int time) {
        int key = pillowHolder(time);
        System.out.println("Player " + key + " is holding the pillow at t=" + Main.currentTime);
    }

    public void reverse(int time) {
        int key = pillowHolder(time);
        Players newHead = this.head;
        if (Main.ltr) {
            while (newHead.getKey() != key) {
                newHead = newHead.next;
            }
            Main.ltr = false;
        } else {
            while (newHead.getKey() != key) {
                newHead = newHead.previous;
            }
            Main.ltr = true;
        }
        this.head = newHead;
        Main.wastageTime = head.getTime() - Main.remainingTime;
    }

    public void insertNewPlayer(int time, Players newPlayer) {
        int key = pillowHolder(time);
        Players ptr = this.head;
        if (Main.ltr) {
            while (ptr.getKey() != key) {
                ptr = ptr.next;
            }
            newPlayer.next = ptr;
            newPlayer.previous = ptr.previous;
            ptr.previous.next = newPlayer;
            ptr.previous = newPlayer;
        } else {
            while (ptr.getKey() != key) {
                ptr = ptr.previous;
            }
            newPlayer.previous = ptr;
            newPlayer.next = ptr.next;
            ptr.next.previous = newPlayer;
            ptr.next = newPlayer;
        }
        head = ptr;

        Main.wastageTime = ptr.getTime() - Main.remainingTime;
    }

    public void finishGame(int time) {
        int key = pillowHolder(time);
        System.out.print("Game Over : Player " + key + " is holding the pillow at t=" + Main.currentTime + ", pillow passing sequence = Player ");
        Players ptr = this.head;
        while (ptr.getKey() != key) {
            if (Main.ltr) {
                ptr = ptr.next;
            } else {
                ptr = ptr.previous;
            }
        }
        Players ptr2 = ptr;
        while (true) {
            System.out.print(ptr2.getKey());
            if (Main.ltr) {
                ptr2 = ptr2.next;
            } else {
                ptr2 = ptr2.previous;
            }
            if (ptr2 == ptr) {
                break;
            }
            System.out.print(", ");
        }
        System.out.println();
        Main.gameOn = false;
    }

    private int pillowHolder(int time) {
        if (time == 0) {
            return this.head.getKey();
        }
        int key;
        int sumTime = 0;
        Players ptr = this.head;

        while (true) {
            if (Main.firstIteration) {
                sumTime += ptr.getTime() - Main.wastageTime;
                Main.firstIteration = false;
            } else {
                sumTime += ptr.getTime();
            }

            if (sumTime >= time) {
                Main.remainingTime = sumTime - time;
                key = ptr.getKey();
                break;
            }
            if (Main.ltr) {
                ptr = ptr.next;
            } else {
                ptr = ptr.previous;
            }
        }
        return key;
    }
}
