
public class Node<Item> {
    Item data;
    Node next;

    public Node(Item data) {
        this.data = data;
    }
    public Node(Item data,Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
