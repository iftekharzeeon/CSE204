import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Enter the number of inputs: ");
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[4];
        String[] inputs = scanner.nextLine().split(" ");
        for (int i = 0; i < 4; i++) {
            input[i] = Integer.parseInt(inputs[i]);
        }
        Graph graph = new Graph(input[0]);
        int[] friendsCollection = new int[input[3]];
        int[] friendsInfo = new int[input[3]];
        int collectedItems = 0;
        int totalItems = 0;
        String outputMessages = "";
        //System.out.println("Enter the number of edges: ");
        for (int j = 0; j < input[1]; j++) {
            String[] connections = scanner.nextLine().split(" ");
            graph.addEdge(Integer.parseInt(connections[0]), Integer.parseInt(connections[1]));
        }
        //System.out.println("Enter the number of items in locations: ");
        for (int j = 0; j < input[2]; j++) {
            String[] locations = scanner.nextLine().split(" ");
            int place = Integer.parseInt(locations[0]);
            int items = Integer.parseInt(locations[1]);
            graph.addLocations(place, items);
            totalItems += items;
        }
        //System.out.println("Enter the starting points of friends: ");
        for (int j = 0; j < input[3]; j++) {
            String[] startingPoints = scanner.nextLine().split(" ");
            int startingPoint = Integer.parseInt(startingPoints[0]);
            int friend = Integer.parseInt(startingPoints[1]);
            friendsInfo[friend] = startingPoint;
        }

        for (int k = 0; k < input[3]; k++) {
            friendsCollection[k] = graph.dfs(friendsInfo[k]);
            collectedItems += friendsCollection[k];
        }

        if (collectedItems == totalItems) {
            outputMessages = "Mission Accomplished\n";
            System.out.println("Mission Accomplished");
        } else {
            outputMessages = "Mission Impossible\n";
            System.out.println("Mission Impossible");
        }
        outputMessages += collectedItems + " out of " + totalItems + " pieces are collected\n";
        System.out.println(collectedItems + " out of " + totalItems + " pieces are collected");
        for (int j = 0; j < friendsCollection.length; j++) {
            outputMessages += j + " collected " + friendsCollection[j] + " pieces\n";
            System.out.println(j + " collected " + friendsCollection[j] + " pieces");
        }

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(outputMessages);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error Writing to the file");
            e.printStackTrace();
        }
    }
}
