import java.io.PrintStream;

public class ST {
    private static class TreeNode {
        int id; // unique id of the node
        String city; //city where the node is located
        TreeNode left; // pointer to left subtree
        TreeNode right; // pointer to right subtree
        TreeNode parent; // pointer to parent
        int N ;//number of nodes in the subtree starting at this TreeNode
        List booklist = new List(); // sorted linked list of the books, stored in this TreeNode
        protected static final TreeNode EXTERNAL = new TreeNode();

        private TreeNode() {
            super();
        }

        protected TreeNode( int id, String c,TreeNode parent, TreeNode left, TreeNode right) {
            super();
            if ( left == null || right == null) {
                throw new IllegalArgumentException();
            }
            this.id = id;
            this.city = c;
            this.parent=parent;
            this.left = left;
            this.right = right;
        }

        protected TreeNode( int id, String c) {
            super();
            this.id = id;
            this.city = c;
        }

        public String getCity() {
            illegalOnExternalNode();
            return city;
        }

        public int getID() {
            illegalOnExternalNode();
            return id;
        }

        public TreeNode getParent() {
            illegalOnExternalNode();
            return parent;
        }

        public TreeNode getLeftChild() {
            illegalOnExternalNode();
            return left;
        }

        public TreeNode getRightChild() {
            illegalOnExternalNode();
            return right;
        }

        public void setLeftChild(TreeNode left) {
            illegalOnExternalNode();
            this.left = left;
        }

        public void setRightChild(TreeNode right) {
            illegalOnExternalNode();
            this.right = right;
        }

        private void illegalOnExternalNode() {
            if (this == EXTERNAL) {throw new IllegalStateException();}
        }

        public void visit() {System.out.print("WareHouse " +id+" , is located in  " +city +"\n");}
    }

    private TreeNode head; //ρίζα στο δέντρο των αποθηκών

    private TreeNode rotRight(TreeNode t){
        TreeNode temp=t.getLeftChild();
        t.setLeftChild(temp.getRightChild());
        temp.setRightChild(t);
        return temp;
    }

    private TreeNode rotLeft(TreeNode t){
        TreeNode temp=t.getRightChild();
        t.setRightChild(temp.getLeftChild());
        temp.setLeftChild(t);
        return temp;
    }

    void insertWarehouse(int nodeid, String name){
       head=insertR(head,nodeid,name);
       if(head==null){return;}
    }

    private TreeNode insertR(TreeNode t,int id,String name){
        if(t==null){
            TreeNode node=new TreeNode(id,name);
            node.parent=node;
            return node;
        }
        if(t.id==id) {
            System.out.println("The id for  Warehouse at " + t.city + " is used");
            return null;
        }
        if (Math.random()*(t.N+1) < 1.0)
            return insertT(t,id,name);

        if (t.id<id){
            t.left=insertR(t.left,id,name);
        }
        else {
            t.right=insertR(t.right,id,name);
        }
        t.N++;
        System.out.println(t.N);
        return t;
    }

    private TreeNode insertT(TreeNode t,int id,String name) {
        if (t == null) return new TreeNode(id,name);
        if (t.id<id) {
            t.left = insertT(t.left,id,name);
            t = rotRight(t); }
        else {
            t.right = insertT(t.right,id,name);
            t = rotLeft(t); }
        return t;
    }

    void insertBookAtWarehouse(int nodeid, int isbn, int copies){
        TreeNode node=searchNode(head,nodeid);
        if(node==null){ System.out.println("There is no Warehouse with this ID\n");}
        boolean b =node.booklist.searchBook(isbn);
        if(!b){
            node.booklist.addBook(new BookInfo(copies,isbn));
        }
        else{
            node.booklist.addCopy(copies,isbn);
        }
    }

    private  TreeNode searchNode(TreeNode h,int id){
        if(h==null){return null;}
        if(h.id==id){return h;}
        if(h.id<id){
            return searchNode(h.left,id);
        }
        else{
            return searchNode(h.right,id);
        }
    }

    void removeWarehouse(int nodeid){removeR(head,nodeid);}

    private TreeNode removeR(TreeNode h, int id) {
        if (h == null){
            System.out.println("There is no Warehouse with this ID\n");
            return null;
        }
        int w = h.getID();
        if(w==id) {
            System.out.println(h.getCity());
            h = joinLR(h.left, h.right);
            h.N--;
        }
        else if(w>id) {
            h.right=removeR(h.right,id);
        }
        else{
            h.left=removeR(h.left,id);
        }
        return h;
    }

    private TreeNode joinLR(TreeNode a, TreeNode b) {
        if (a == null) return b;
        if (b == null) return a;
        int N = a.N + b.N;
        if (Math.random()*N < 1.0*a.N) {
            a.right = joinLR(a.right, b);
            return a;
        }
        else {
            b.left = joinLR(a, b.left);
            return b;
        }
    }

    void removeBook(int nodeid, int isbn){
        TreeNode node=searchNode(head,nodeid);
        if(node==null){ System.out.println("There is no Warehouse with ID : "+nodeid+"\n"); return;}
        boolean b =node.booklist.searchBook(isbn);
        if(b){
            if(node.booklist.subCopy(isbn)==0){
                node.booklist.remBook(isbn);
            }
        }
    }

    void searchByWarehouse(int nodeid){
        TreeNode node=searchNode(head,nodeid);
        if(node==null){
            System.out.println("There is no Warehouse with ID : "+nodeid+"\n");
            return;
        }
        else{
            node.visit();
            node.booklist.print();
        }
    }

    void searchBookInWarehouse(int nodeid, int isbn){
        TreeNode node=searchNode(head,nodeid);
        boolean b =node.booklist.searchBook(isbn);
        if(node==null){
            System.out.println("There is no Warehouse with ID : "+nodeid+"\n");
            return;
        }
        if(!b){
            System.out.println("The book with isbn: "+isbn+" there is no at Warehouse\n");
        }
        else{
            node.visit();
            node.booklist.printBook(isbn);
        }
    }

    void searchBook(int isbn){
        System.out.print("The book is avaliable at \n");
        cros(head, isbn);
    }

    private void cros(TreeNode h,int isbn){
        if(h==null){return;}
        if(h.booklist.searchBook(isbn)){
            h.visit();
            h.booklist.printCopies(isbn);
        }
        cros(h.left,isbn);
        cros(h.right,isbn);
    }

    void printΤree(PrintStream stream){traverseR(head);}
    private void traverseR(TreeNode h) {
        if (h == null) return;
        h.visit();
        traverseR(h.left);
        traverseR(h.right);
       }
}
