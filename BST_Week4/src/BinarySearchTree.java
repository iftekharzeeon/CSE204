
public class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    private boolean isEmpty() {
        return this.root == null;
    }

    public void insertItem(TreeNode node) {
        if (isEmpty()) {
            this.root = node;
            System.out.println("New Item Inserted");
            return;
        }
        TreeNode temp = this.root;
        while (true) {
            if (temp.getValue() == node.getValue()) {
                System.out.println("Duplicate Item");
                return;
            }
            if (temp.getValue() < node.getValue()) {
                if (temp.right == null) {
                    temp.right = node;
                    break;
                } else {
                    temp = temp.right;
                }
            } else {
                if (temp.left == null) {
                    temp.left = node;
                    break;
                } else {
                    temp = temp.left;
                }
            }
        }
        System.out.println("New Item Inserted");
    }

    public TreeNode searchItem(int value) {
        if (isEmpty()) {
            return null;
        }
        TreeNode temp = this.root;
        while (temp != null) {
            if (temp.getValue() == value) {
                return temp;
            } else if (temp.getValue() < value) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return null;
    }

    public TreeNode deleteItem(TreeNode rootNode, int value) {
        if (rootNode == null) {
            return null;
        }
        if (rootNode.getValue() < value) {
            rootNode.right = deleteItem(rootNode.right, value);
        } else if (rootNode.getValue() > value) {
            rootNode.left = deleteItem(rootNode.left, value);
        } else {
            if (rootNode.left == null) {
                return rootNode.right;
            } else if (rootNode.right == null) {
                return rootNode.left;
            } else {
                TreeNode temp = getMaxItem(rootNode.left);
                rootNode.setValue(temp.getValue());
                rootNode.left = deleteItem(rootNode.left, temp.getValue());
            }
        }
        return rootNode;
    }

    public int getItemDepth(int value) {
        if (value == this.root.getValue()) {
            return 0;
        }
        int depth = 0;
        TreeNode temp = this.root;
        while (temp.getValue() != value) {
            if (value > temp.getValue()) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
            depth++;
        }
        if (depth == 0) return -1;
        return depth;
    }

    public TreeNode getMaxItem(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode temp = node;
        TreeNode max = temp;
        while (temp != null) {
            if (temp.right != null) {
                max = temp.right;
            }
            temp = temp.right;
        }
        return max;
    }

    public TreeNode getMinItem(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode temp = node;
        TreeNode min = temp;
        while (temp != null) {
            if (temp.left != null) {
                min = temp.left;
            }
            temp = temp.left;
        }
        return min;
    }

    public TreeNode inOrderPredecessor(int value) {
        TreeNode node = searchItem(value);
        if (node != null && node.left != null) {
            return getMaxItem(node.left);
        }
        TreeNode temp = this.root;
        TreeNode predecessor = null;
        while (temp.getValue() != value) {
            if (temp.getValue() > value) {
                temp = temp.left;
            } else {
                predecessor = temp;
                temp = temp.right;
            }
            if (temp == null) {
                return null;
            }
        }
        return predecessor;
    }

    public TreeNode inOrderSuccessor(int value) {
        TreeNode node = searchItem(value);
        if (node != null && node.right != null) {
            return getMinItem(node.right);
        }

        TreeNode temp = this.root;
        TreeNode successor = null;
        while (temp.getValue() != value) {
            if (temp.getValue() > value) {
                successor = temp;
                temp = temp.left;
            } else {
                temp = temp.right;
            }
            if (temp == null) {
                return null;
            }
        }
        return successor;
    }

    public int getHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight > rightHeight) {
            return (leftHeight+1);
        }
        return (rightHeight+1);
    }

    public void printPreOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.getValue() + " ");
        printInOrder(node.right);
    }

    public void printPostOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.getValue() + " ");
    }

    public int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftNode = getSize(node.left);
        int rightNode = leftNode + getSize(node.right);
        return (rightNode+1);
    }
}
