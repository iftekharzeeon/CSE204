import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        String input;
        int item;
        optionMenu();
        do {
            System.out.println("Enter your command. 999 for menu");
            input = scanner.nextLine();
            TreeNode node;
            switch (input) {
                case "0":
                    break;
                case "1":
                    System.out.println("Enter your item, a valid integer: ");
                    item = scanner.nextInt();
                    scanner.nextLine();
                    binarySearchTree.insertItem(new TreeNode(item));
                    break;
                case "2":
                    System.out.println("Enter your item to search: ");
                    item = scanner.nextInt();
                    scanner.nextLine();
                    if (binarySearchTree.searchItem(item) != null) {
                        System.out.println( item + " has been found.");
                    } else {
                        System.out.println(item + " has not been found.");
                    }
                    break;
                case "3":
                    System.out.println("Enter your item to for in order Successor: ");
                    item = scanner.nextInt();
                    scanner.nextLine();
                    node = binarySearchTree.inOrderSuccessor(item);
                    if (node == null ) {
                        System.out.println("In Order Successor not found");
                    } else {
                        System.out.println("In Order Successor for " + item + " is: " + node.getValue());
                    }
                    break;
                case "4":
                    System.out.println("Enter your item to for in order predecessor: ");
                    item = scanner.nextInt();
                    scanner.nextLine();
                    node = binarySearchTree.inOrderPredecessor(item);
                    if (node == null ) {
                        System.out.println("In Order Predecessor not found");
                    } else {
                        System.out.println("In Order Predecessor for " + item + " is: " + node.getValue());
                    }
                    break;
                case "5":
                    System.out.println("Enter your item to Delete: ");
                    item = scanner.nextInt();
                    scanner.nextLine();
                    binarySearchTree.deleteItem(binarySearchTree.getRoot(), item);
                    break;
                case "6":
                    System.out.println("Enter your item to fine the depth: ");
                    item = scanner.nextInt();
                    scanner.nextLine();
                    int depth = binarySearchTree.getItemDepth(item);
                    if (depth >= 0) {
                        System.out.println("Item Depth of " + item + ": " + depth);
                    } else {
                        System.out.println("Item not found");
                    }
                    break;
                case "7":
                    node = binarySearchTree.getMaxItem(binarySearchTree.getRoot());
                    if (node != null) {
                        System.out.println("Maximum item of the tree: " + node.getValue());
                    } else {
                        System.out.println("Tree is empty");
                    }
                    break;
                case "8":
                    node = binarySearchTree.getMinItem(binarySearchTree.getRoot());
                    if (node != null) {
                        System.out.println("Minimum item of the tree: " + node.getValue());
                    } else {
                        System.out.println("Tree is empty");
                    }
                    break;
                case "9":
                    int height =  binarySearchTree.getHeight(binarySearchTree.getRoot());
                    if (height > -1) {
                        System.out.println("Height of the tree: " + height);
                    } else {
                        System.out.println("Tree is empty");
                    }
                    break;
                case "10":
                    System.out.println("In Order: ");
                    binarySearchTree.printInOrder(binarySearchTree.getRoot());
                    System.out.println();
                    break;
                case "11":
                    System.out.println("Pre Order: ");
                    binarySearchTree.printPreOrder(binarySearchTree.getRoot());
                    System.out.println();
                    break;
                case "12":
                    System.out.println("Post Order: ");
                    binarySearchTree.printPostOrder(binarySearchTree.getRoot());
                    System.out.println();
                    break;
                case "13":
                    int size = binarySearchTree.getSize(binarySearchTree.getRoot());
                    if (size > 0) {
                        System.out.println("Size of the tree: " + size);
                    } else {
                        System.out.println("Tree is empty");
                    }
                    break;
                case "999":
                    optionMenu();
                    break;
                default:
                    System.out.println("Invalid Input. Try again");
            }

        } while (!input.equals("0"));
    }
    private static void optionMenu() {
        System.out.println("-----------------------------------------");
        System.out.println("What operation do you want to perform? ");
        System.out.println("Select Option number. Enter 0 to exit.");
        System.out.println("1. Insert Item");
        System.out.println("2. Search Item");
        System.out.println("3. Get In Order Successor");
        System.out.println("4. Get In Order Predecessor");
        System.out.println("5. Delete Item");
        System.out.println("6. Get Item Depth");
        System.out.println("7. Get Max Item");
        System.out.println("8. Get Min Item");
        System.out.println("9. Get Height");
        System.out.println("10. Print In Order");
        System.out.println("11. Print Pre Order");
        System.out.println("12. Print Post Order");
        System.out.println("13. Get Size");
        System.out.println("-----------------------------------------");
    }
}
