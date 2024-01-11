import java.util.Scanner;
public class DNAPalindrome {
    public static void main(String args[]){
        int size;
        String dna;
        boolean bool=true;
        CharDoubleEndedQueueImpl palindrome=new CharDoubleEndedQueueImpl();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Give character sequence : ");
            dna = scan.nextLine();
            size= dna.length();
            if(dna.matches("[A-Z ]+")){
                break;
            }
            else{
                System.out.println("A DNA cannot contain numbers or symbols ");
            }
        }
        for(int i=0;i<size ;i++){
            palindrome.addLast(dna.charAt(i));
        }

        palindrome.printQueue(System.out);
        for(int i=0; i<size/2; i++){
            if((palindrome.getFirst()=='T'&& palindrome.getLast()=='A')||
                    (palindrome.getFirst()=='A'&& palindrome.getLast()=='T')||
                    (palindrome.getFirst()=='G'&& palindrome.getLast()=='C')||
                    (palindrome.getFirst()=='C'&& palindrome.getLast()=='G')||
                    (palindrome.getFirst()=='T'&& palindrome.getLast()=='A')){
                palindrome.removeLast();
                palindrome.removeFirst();
            }
            else{
                bool=false;
            }
        }
        if(bool){
            System.out.println("The DNA " + dna + " is Watson-Crick complemented palindrome");
        }
        else {
            System.out.println("The DNA " + dna + " is NOT Watson-Crick complemented palindrome");
        }
    }
}
