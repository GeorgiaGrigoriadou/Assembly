#Author : Georgia Grigoriadou
#Date : thursday 02/11/2017

#$s0 τα ευρώ που έδωσε ο πελάτης
#$s1 τα λεπτά που έδωσε ο πελάτης
#$t0 <--20 euros 
#$t1 <--99 cents
#$t2 = 5
#$t3 = 24
#$t4 = 100
#$t8 = ΣΥΝΟΛΙΚΑ ρέστα σε λεπτά 
#$s2 $s3 $s4 $s5 $s7 $t2 $t3 $t4 $t5 $t6 $t7 ΤΟΥΣ ΧΡΗΣΙΜΟΠΟΙΩ ΓΙΑ ΝΑ ΥΠΟΛΟΓΙΣΩ 
                                              #ΤΟ ΠΛΗΘΟΣ ΤΩΝ (ΧΑΡΤΟ)ΝΟΜΙΣΜΑΤΩΝ

		.text
		.globl main 
		
main: 	
		la $a0, pgm  #Εμφάνισε το τιτλο 
		li $v0, 4    #του προγράμματος
		syscall
		
		la $a0, cost  #Εμφάνισε το 
		li $v0, 4     #συνολικο κόστος
		syscall
		
		la $a0, euros_print #Εμφάνισε το μέγιστο
		li $v0,4            #επιτρεπόμενο ποσό 
		syscall             #σε ευρώ
		
		li $v0, 5     #διάβασε τα ευρώ
		syscall       #σε ακέραιο 
		
		move $s0, $v0  #$s0 <-- ευρω που εδωσε ο πελατης
		
		la $a0, cents_print  #Εμφάνισε το μέγιστο
		li $v0,4             #επιτρεπόμενο ποσό
		syscall              #σε λεπτά
		
		li $v0,5       #διάβασε τα λεπτά
		syscall        #σε ακέραιο
		
		move $s1, $v0  #$s1 <-- λεπτά που έδωσε ο πελάτης
		
		li $t0, 20  # $t0 <--20 euros 
		li $t1, 99  #$t1 <--99 cents 
		
		bgt $s0, $t0, error1    # if(ευρω του πελατη) > 20) πήγαινε στη θέση error1
		bgt $s1, $t1, error1   #if(λεπτα του πελατη) >99) πήγαινε στη θέση error1
		
		li $t2, 5
		li $t3, 24
		li $t4, 100 
		
		# ΡΕΣΤΑ ΣΕ ΛΕΠΤΑ {
		sub $t0, $s0, $t2      #t8 <--ευρω του πελατη-5
		mul $t8, $t0, $t4 		#μετατροπή τα ρέστα απο ευρώ σε λεπτά
		sub $t9, $s1, $t3     #t9 <-- 99-λεπτά του πελατη
		add $t8, $t8, $t9      # t8 περιέχει τα ΣΥΝΟΛΙΚΑ ρέστα σε λεπτά
		#}
		
		
		bgt $zero, $t8, error2  #if (0 > ρέστα) πήγαινε στη θέση error2
		beq $zero, $t8, change  #if (0 = ρέστα) πήγαινε στη θέση change
		
		#υπολογισμός ρέστα {
		li $t4, 1000
		div $s2, $t8, $t4        # Υπολογίζω πόσα χαρτονομίσματα 
		rem $s6, $t8, $t4        #και πόσα κέρματα πρέπει να δωθούν 
		                         #στον πελάτη με div και mod 
		li $t4, 500              
		div $s3, $s6, $t4
		rem $s6, $s6, $t4
		                         #Αποθηκεύω κάθε φορά το 
		li $t4, 200              #το ακέραιο πηλικο σε 
		div $s4, $s6, $t4        #διαφορετικό καταχωρητή
		rem $s6, $s6, $t4        #ώστε να γνωρίζω το πλήθος
		                         #του κάθε (χαρτο)νομίσματος
		li $t4, 100              #που πρέπει να δωθεί
		div $s5, $s6, $t4        #στον πελάτη
		rem $s6, $s6, $t4
		
		li $t4, 50
		div $s7, $s6, $t4
		rem $s6, $s6, $t4
		
		li $t4, 20
		div $t2, $s6, $t4
		rem $s6, $s6, $t4
		
		li $t4, 10
		div $t3, $s6, $t4
		rem $s6, $s6, $t4
		
		li $t4, 5
		div $t5, $s6, $t4
		rem $s6, $s6, $t4
		
		li $t4, 2
		div $t6, $s6, $t4
		rem $s6, $s6, $t4
		
		li $t4, 1
		div $t7, $s6, $t4
		rem $s6, $s6, $t4
		#}
		
		################
		
		# ΕΜΦΑΝΙΖΩ ΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ
		
		# 10 euros {
		beq $zero, $s2, L1
		move $a0, $s2          
		li $v0, 1    
		syscall
		
		la $a0, teneu   
		li $v0, 4    
		syscall
		#}
		
		# 5 euros{
L1:		beq $zero, $s3, L2
		move $a0, $s3
		li $v0, 1    
		syscall
		
		la $a0, fiveeu   
		li $v0, 4    
		syscall
		#}
		
		# 2 euros {
L2:		beq $zero, $s4, L3
		move $a0, $s4
		li $v0, 1    
		syscall
		
		la $a0, twoeu   
		li $v0, 4    
		syscall
		#}
		
		
		# 1 euros{ 
L3:		beq $zero, $s5, L4
		move $a0, $s5
		li $v0, 1    
		syscall
		
		la $a0, oneeu   
		li $v0, 4    
		syscall	
		#}
		
		# 50 cents {
L4:		beq $zero, $s7, L5
		move $a0, $s7
		li $v0, 1    
		syscall
		
		la $a0, fifcen   
		li $v0, 4    
		syscall
		#}
		
		# 20 cents {
L5:		beq $zero, $t2, L6
		move $a0, $t2
		li $v0, 1    
		syscall
		
		la $a0, tcen   
		li $v0, 4    
		syscall
		#} 
		
		# 10 cents {
L6:		beq $zero, $t3, L7
		move $a0, $t3
		li $v0, 1    
		syscall
		
		la $a0, tencen  
		li $v0, 4    
		syscall
		#} 
		
		# 5 cents{
L7:		beq $zero, $t5, L8
		move $a0, $t5
		li $v0, 1    
		syscall
		
		la $a0, fivcen  
		li $v0, 4    
		syscall
		#} 
		
		# 2 cents {
L8:		beq $zero, $t6, L9
		move $a0, $t6
		li $v0, 1    
		syscall
		
		la $a0, twocen  
		li $v0, 4    
		syscall  
		#}
		
		# 1 cents {
L9:		beq $zero, $t7, exit
		move $a0, $t7
		li $v0, 1    
		syscall
		
		la $a0, onecen  
		li $v0, 4    
		syscall
		#}
		
		j exit
		
		
error1: la $a0, error1_print    
		li $v0,4
		syscall
		j exit
		
		
error2: la $a0, error2_print
		li $v0,4
		syscall
		j exit
		
change: la $a0, change_print
		li $v0,4
		syscall
	

exit:	li $v0, 10    #Βγες απο το προγραμμα
		syscall
		
		
		.data
pgm: 	.asciiz "Parking Ticket Payment \n\n"
CRLF:	.asciiz "\n\n"
cost:   .asciiz "Fee : 5 euros and 24 cents \n\n"
euros_print:  .asciiz "Euros (<=20) : "
cents_print:  .asciiz "Cents (<100) : "
error1_print: .asciiz "Error! Please try again. \n"
error2_print: .asciiz "Error! Not enough money!  \n"
change_print: .asciiz "change = 0"
teneu:  .asciiz " x 10 euros\n"
fiveeu: .asciiz " x 5 euros\n"
twoeu:  .asciiz " x 2 euros\n"
oneeu:  .asciiz " x 1 euros\n"
fifcen: .asciiz " x 50 cents\n"
tcen:   .asciiz " x 20 cents\n"
tencen: .asciiz " x 10 cents\n"
fivcen: .asciiz " x 5 cents\n"
twocen: .asciiz" x 2 cents\n"
onecen: .asciiz " x 1 cents\n"

