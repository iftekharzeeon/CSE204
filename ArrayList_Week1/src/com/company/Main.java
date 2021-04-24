package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Constructors
        Array a = new Array();
        Array b = new Array(5);
        Array c = new Array(new String[] {"A", "B", "C"});

//        getArray Method
//        String[] s1 = c.getArray();
//        for (String s: s1) {
//            System.out.print(s + " ");
//        }

//        //getElement Method
//        String e1 = c.getAnElement(2);
//        System.out.println(e1);
//
//        //add(1) Method
//        a.add("Testing1");
//        for (String s: a.getArray()) {
//            System.out.print(s + " ");
//        }
//
//        //add(2) Method
//        c.add(1, "F");
//        for (String s: c.getArray()) {
//            System.out.print(s + " ");
//        }
//
//        //remove Method
//        c.remove("A");
//        for (String s: c.getArray()) {
//            System.out.print(s + " ");
//        }
//
//        //findIndex Method
//        int[] i1 = c.findIndex("B");
//        for (int i : i1) {
//            System.out.print(i + " ");
//        }
//
//        //subArray Method
//        String[] s2 = c.subArray(0,2);
//
//        //merge Method
        b.merge(new String[]{"A", "C", "F"}, new String[] {"B", "D", "E"});
        for (String s: b.getArray()) {
            System.out.print(s + " ");
        }
//
//        //length
//        System.out.println( "Length of the array" + a.length());
//
//        //isEmpty
//        System.out.println("Is empty?: " + a.isEmpty());

    }
}
