import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        GenericQueue<Character> letters = new GenericQueue<>();
        int[] smallLetters = new int[26];
        System.out.print("Sample Input: ");
        Scanner scanner = new Scanner(System.in);
        String oldString = scanner.nextLine();
        char[] newStringArray = new char[oldString.length()];
        for (int i = 0; i < oldString.length(); i++) {
            char c = oldString.charAt(i);
            if (letters.isEmpty() && smallLetters[c-97] == 0) {
                //Enqueue the character
                letters.enqueue(c);
                smallLetters[c-97]++;
                newStringArray[i] = letters.peek();
            } else if (letters.isEmpty() && smallLetters[c-97] != 0) {
                //Queue is empty,append #
                smallLetters[c-97]++;
                newStringArray[i] = '#';
            }
             else if (letters.peek() == c) {
                 //Remove the top, get the next element or #
                letters.remove(c);
                if (letters.isEmpty()) {
                    newStringArray[i] = '#';
                } else {
                    newStringArray[i] = letters.peek();
                }
                smallLetters[c-97]++;
            }
            else if (letters.peek() != c) {
                //Enqueue the character if not exists in Queue, if exists: remove
                if (smallLetters[c-97] == 0) {
                    letters.enqueue(c);
                } else {
                    letters.remove(c);
                }
                smallLetters[c-97]++;
                newStringArray[i] = letters.peek();
            }
        }
        String newString = String.copyValueOf(newStringArray);
        System.out.println("Sample Output: " + newString);
    }
}
