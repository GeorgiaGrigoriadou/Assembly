public class doubleNode {
     char item;
     doubleNode next,previous;

    public doubleNode(char data){
        item=data;
        next=null;
        previous=null;
    }

    public doubleNode(char data,doubleNode nex, doubleNode prev){
        item=data;
        next=nex;
        previous=prev;
    }

    public char getItem(){return item;}
    public doubleNode getNext(){return next;}
    public doubleNode getPrevious(){return previous;}
    public void setItem(char data){item=data;}
    public void setNext(doubleNode nex){next=nex;}
    public void setPrevious(doubleNode prev){previous=prev;}
}
