import java.util.InputMismatchException;
import java.util.Scanner;

public class SystemMenu {
    public static void main(String[] args) {
        boolean done = false;
        String answer,name;
        int id,isbn,copies;
        ST st=new ST();
        Scanner scan = new Scanner(System.in);
        while (!done) {
            scan.equals(null);
            System.out.println("1.Insert Warehouse");
            System.out.println("2.Remove Warehouse");
            System.out.println("3.Insert a book at Warehouse");
            System.out.println("4.Remove book");
            System.out.println("5.Search By warehouse");
            System.out.println("6.Search book in Warehouse");
            System.out.println("7.Search book");
            System.out.println("8.Print all Warehouses");
            System.out.println("0.EXIT");
            answer=scan.nextLine();
            if (answer.equals("1")) {
                System.out.println("Please enter the name of Warehouse: ");
                while(true){
                    name = scan.nextLine();
                    if(name.matches("[A-Za-z]+")){
                        break;
                    }
                    else{
                        System.out.println("A name cannot contain numbers or symbols");
                    }
                }
                System.out.println("Please enter the ID of Warehouse: ");
                id = scan.nextInt();
                st.insertWarehouse(id,name);
            }
            else if(answer.equals("2")){
                System.out.println("Please enter the ID of Warehouse to delete it: ");
                try {
                    id = scan.nextInt();
                    st.removeWarehouse(id);
                }
                catch (InputMismatchException e){
                    System.out.print("Number entered was not an integer. \n\n");

                }
            }
            else if(answer.equals("3")){
                try {
                    System.out.println("Please enter the ID of Warehouse: ");
                    id = scan.nextInt();
                    System.out.println("Please enter the ISBN of book: ");
                    isbn = scan.nextInt();
                    System.out.println("Please enter the copies of book: ");
                    copies = scan.nextInt();
                    st.insertBookAtWarehouse(id,isbn,copies);
                }
                catch (InputMismatchException e){
                    System.out.print("Number entered was not an integer. \n\n");
                }
            }
            else if(answer.equals("4")){
                try {
                    System.out.println("Please enter the ID of Warehouse: ");
                    id = scan.nextInt();
                    System.out.println("Please enter the ISBN of book: ");
                    isbn = scan.nextInt();
                    st.removeBook(id, isbn);
                }
                catch (InputMismatchException e){
                    System.out.print("Number entered was not an integer. \n\n");
                }
            }
            else if(answer.equals("5")){
                try {
                    System.out.println("Please enter the ID of Warehouse: ");
                    id = scan.nextInt();
                    st.searchByWarehouse(id);
                }
                catch (InputMismatchException e){
                    System.out.print("Number entered was not an integer. \n\n");
                }
            }
            else if(answer.equals("6")){
                try {
                    System.out.println("Please enter the ID of Warehouse: ");
                    id = scan.nextInt();
                    System.out.println("Please enter the ISBN of book: ");
                    isbn = scan.nextInt();
                    st.searchBookInWarehouse(id, isbn);
                }
                catch (InputMismatchException e){
                    System.out.print("Number entered was not an integer. \n\n");
                }
            }
            else if(answer.equals("7")){
                try {
                    System.out.println("Please enter the ISBN of book: ");
                    isbn = scan.nextInt();
                    st.searchBook(isbn);
                }
                catch (InputMismatchException e){
                    System.out.print("Number entered was not an integer. \n\n");
                }
            }
            else if(answer.equals("8")){
                st.printΤree(System.out);
            }
            else if(answer.equals("0")){
                done=true;
            }
        }
    }
}
