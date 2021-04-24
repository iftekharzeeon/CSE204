package com.company;

import java.util.Scanner;

public class Main {

    public static int currentTime = 0;
    public static int latestEventTime = 0;
    public static int remainingTime = 0;
    public static int wastageTime = 0;
    public static int currentPlayers = 0;
    public static boolean firstIteration = true;
    public static boolean ltr = true;
    public static boolean gameOn = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers;
        int playerNumbers;
        int[] reflexes;

        Players p;
        PillowGame game = new PillowGame();
        String message;
        int time;

        System.out.println("Enter Player Numbers: ");
        while (true) {
            numbers = scanner.nextLine();
            try {
                playerNumbers = Integer.parseInt(numbers);
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input. Please enter valid player numbers");
            }
        }
        currentPlayers = playerNumbers;
        reflexes = new int[playerNumbers];
        System.out.println("Enter the reflexes of the " + playerNumbers + " players:");
        for (int i = 0; i < playerNumbers; i++) {
            try {
                reflexes[i] = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input. Please enter valid reflexes times");
                return;
            }
        }
        for (int i = 0; i < playerNumbers; i++) {
            p = new Players(i+1, reflexes[i]);
            game.append(p);
        }
        scanner.nextLine();
        optionMenu();
        while (gameOn) {
            System.out.println("Enter 0 0 to show menu: ");
            message = scanner.nextLine();
            String[] splitMessage = message.split(" ");
            time = Integer.parseInt(splitMessage[0]);
            currentTime = time;
            switch (splitMessage[1]) {
                case "M":
                    //Music Ends
                    firstIteration = true;
                    if (time >= latestEventTime) {
                        time = time - latestEventTime;
                    }
                    int key = game.removePlayer(time);
                    latestEventTime = time;
                    currentPlayers--;
                    if (currentPlayers == 1) {
                        System.out.println("Game Over : Player " + key + " wins!!");
                        gameOn = false;
                    }
                    break;
                case "R":
                    //Reverse
                    firstIteration = true;
                    if (time >= latestEventTime) {
                        time = time - latestEventTime;
                    }
                    game.reverse(time);
                    latestEventTime = currentTime;
                    break;
                case "I":
                    //New Player
                    firstIteration = true;
                    if (time >= latestEventTime) {
                        time = time - latestEventTime;
                    }
                    int newReflex = Integer.parseInt(splitMessage[2]);
                    Players newPlayer = new Players(++playerNumbers, newReflex);
                    game.insertNewPlayer(time, newPlayer);
                    currentPlayers++;
                    latestEventTime = currentTime;
                    break;
                case "P":
                    //Show Pillow Holder
                    firstIteration = true;
                    if (time >= latestEventTime) {
                        time = time - latestEventTime;
                    }
                    game.showPlayer(time);
                    break;
                case "F":
                    //End Game
                    firstIteration = true;
                    if (time >= latestEventTime) {
                        time = time - latestEventTime;
                    }
                    game.finishGame(time);
                case "0":
                    //Show Menu
                    optionMenu();
                    break;
                default:
                    System.out.println("Wrong Option. Please choose correct option");
                    break;
            }
        }
    }
    public static void optionMenu() {
        System.out.println("---------------------");
        System.out.println("Choose correct option from below:");
        System.out.println("Time-M for music end");
        System.out.println("Time-R for reverse order");
        System.out.println("Time-I-reflex for new player");
        System.out.println("Time-P to show player");
        System.out.println("Time-F to end game");
        System.out.println("---------------------");
    }
}
