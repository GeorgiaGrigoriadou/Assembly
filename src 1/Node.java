public class Node {
    char item;
    Node next;

    Node(char data) {this( data,null );}
    Node() {this('/');}

    Node(char data, Node node) {
        item = data;
        next = node;
    }
    public char getchar() {return item;}
    public Node getNext() {return next;}
    public void setItem(char data){item=data;}
    public void setNext(Node nex){next =nex;}
}

