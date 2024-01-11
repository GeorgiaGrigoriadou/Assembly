class ListNode {
    BookInfo data;
    ListNode nextNode;
    /**
     * Constructor. It initializes data and sets next node to null
     * @param book a reference to node's data
     */
    ListNode(BookInfo book) {
        this( book, null );
    }
    /**
     * constructor creates ListNode with passed data and next node
     * @param book the reference to node's data
     * @param node the next node in the list
     */
    ListNode( BookInfo book, ListNode node ) {
        data = book;
        nextNode = node;
    }
    /**
     * Returns this node's data
     * @return the reference to node's data
     */
    int getBookInfoIsbn() {
        return data.getISBN();
    }

    /**
     * Get reference to next node
     * @return the next node
     */
    ListNode getNext() {
        return nextNode;
    }
}
