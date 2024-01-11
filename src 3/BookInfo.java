
public class BookInfo {
    int copies;
    int isbn;

    public BookInfo(int copy,int i){
        copies=copy;
        isbn=i;
    }
    public int getCopies(){return copies;}
    public void addCopies(int copy){copies=copy+copies;}
    public void subCopies(){copies--;}
    public int getISBN() {return isbn;}

}
