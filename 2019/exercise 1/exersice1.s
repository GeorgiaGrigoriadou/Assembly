#Author : Georgia Grigoriadou
# ΑΜ : p3160029
#Date : thursday 02/11/2019		
		
	
		.text
		.globl main 

				
main: 	

		
		la $a0, pgm  #Εμφάνισε το τιτλο 
		li $v0, 4    #του προγράμματος
		syscall
		
		la $a0, object  #Εμφάνισε το 
		li $v0, 4     #μηνυμα για εισαγωγη object n
		syscall
		
		li $v0, 5     #διάβασε τον αριθμο
		syscall       #των object n
		
		move $s0, $v0  #$s0 <-- number of n
		
		la $a0, k     #Εμφάνισε το 
		li $v0, 4     #μηνυμα για εισαγωγη k
		syscall
		
		li $v0, 5     #διάβασε τον αριθμο k
		syscall     
		
		move $s1, $v0  #$s1 <-- number of k
		
		blt $s1, $0, exit #if k < 0 go to exit
		blt $s0, $v0, exit # if n < k
		move $a0, $s0
		move $a1, $s1
		jal combinations
		
		
		move $a0, $v0
		li $v0,1 
		syscall
		
		li $v0,10  #exit 
		syscall
		
		
		
combinations: 
		li $t1,1 #int factorial_n =1
		li $t2,1 #int factorial_k =1
		li $t3,1 #int factorial_n_k =1
		li $t4,1 #int i=1
		li $t5,1 #int j=1
		li $t6,1 #int p=1
		sub $t7, $a0, $a1 #t7 = n-k
		
factorial_n:
		bgt $t4,$a0, factorial_k #if i > n go to factorial_k
		mul $t1, $t1, $t4  #factorial_n *= i;
		addi $t4,1 #i++
		j factorial_n
		
factorial_k:
		bgt $t5,$a1, factorial_n_k #if j > k go to factorial_k*factorial_n_k
		mul $t2, $t2,$t5   #factorial_k *= i
		addi $t5, 1 #j++
		j factorial_k
		
factorial_n_k:
		bgt $t6, $t7, end_factorial # if p> n-k go to end_factorial
		mul $t3, $t3, $t6 #factorial_n_k *= i
		addi $t6,1 #p++
		j factorial_n_k
		
end_factorial:
		mul $t7,$t2, $t3 # $t7 = (factorial_k*factorial_n_k);
		div $t0, $t1, $t7 #$t0 = factorial_n / (factorial_k*factorial_n_k);
		move $v0, $t0
		jr $ra
		
		
		
		

exit:
		la $a0, msg  #Εμφάνισε μηνυμα  
		li $v0, 4    #λαθους
		syscall

     	li $v0, 10    #Βγες απο το προγραμμα
		syscall
		
		
		
		.data

pgm: 	.asciiz "This program computes the mathematical combinations function 
C(n, k), which is the number of ways of selecting k objects from a set of n distinct objects. \n\n "
object: .asciiz "Enter number of objects in the set (n): "
k:		.asciiz "Enter number to be chosen (k): "
msg:    .asciiz "Please enter n >= k >= 0"
result: .asciiz "The result is : "