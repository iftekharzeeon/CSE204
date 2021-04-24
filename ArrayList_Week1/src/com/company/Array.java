package com.company;

public class Array {
    private String[] sr;

    Array() {
        this.sr = new String[80];
    }

    Array(int i) {
        this.sr = new String[i];
    }

    Array(String[] s) {
        this.sr = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            this.sr[i] = s[i];
        }
    }

    public String[] getArray() {
        return this.sr;
    }

    public String getAnElement(int i) {
        if (i >= this.sr.length) {
            System.out.println("Invalid Index");
            return null;
        } else {
            return this.sr[i];
        }
    }

    public void add(String element) {
        for (int i = 0; i < this.sr.length; i++)  {
            if (this.sr[i] == null) {
                this.sr[i] = element;
                return;
            }
        }
        String[] temp = new String[this.sr.length + 1];
        for (int i = 0; i < this.sr.length; i++){
            temp[i] = this.sr[i];
        }
        temp[temp.length-1] = element;
        this.sr = temp;
    }

    public void add(int i, String element) {
        String[] temp = new String[this.sr.length+1];
        for (int j = 0; j < i; j++){
            temp[j] = this.sr[j];
        }
        if (i < this.sr.length) {
            temp[i] = element;
            for (int j = i+1; j < temp.length; j++){
                temp[j] = this.sr[i];
                i++;
            }
            this.sr = temp;
        }
        else {
            System.out.println("Invalid position");
        }
    }

    public void remove(String element) {
        int count = 0;
        for (String s: this.sr) {
            if (s.equals(element)) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Element not found");
            return;
        }
        String[] temp = new String[this.sr.length - count];
        int i = 0;
        for (int j = 0; j < this.sr.length; j++) {
            if (!this.sr[j].equals(element)) {
                temp[i] = this.sr[j];
                i++;
            }
        }
        this.sr = temp;
    }

    public int[] findIndex(String element) {
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < this.sr.length; i++) {
            if (this.sr[i].equals(element)) {
                count1++;
            }
        }
        int[] indexes = new int[count1];

        for (int i = 0; i < this.sr.length; i++) {
            if (this.sr[i].equals(element)) {
                indexes[count2] = i;
                count2++;
            }
        }
        return indexes;
    }

    public String[] subArray(int start, int end) {
        String[] temp = new String[end - start + 1];
        int i = 0;
        for (int j = start; j <= end; j++) {
            temp[i] = this.sr[j];
            i++;
        }
        return temp;
    }

    public void merge(String[] s1, String[] s2) {
        this.sr = new String[s1.length + s2.length];
        int i;
        for (i = 0; i < s1.length; i++) {
            this.sr[i] = s1[i];
        }
        for (int j = 0; j < s2.length; j++) {
            this.sr[i] = s2[j];
            i++;
        }
    }

    public int length() {
        return this.sr.length;
    }

    public boolean isEmpty() {
        for (String s: this.sr) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }
}