
#			Grigoriadou Georgia 3160029 			
#Date: 		14-01-2018

	.text
	.globl main

main:	addi $s0, $zero, 0           #$s0 number of nodes
		addi $s1, $zero, 0           #$s1 pointer

#Main menu pritning	
Main_Menu:

	la $a0,diaxwrismos			
	li $v0, 4 
	syscall						#print tis grammes diaxwrismos

	la $a0,msg_app 					
	li $v0, 4 
	syscall						#print to msg_app

	la $a0, msg_insert			   
	li $v0, 4 
	syscall						#print to msg_insert	

	la $a0, msg_delete			 
	li $v0, 4 
	syscall						#print to msg_delete	
	
	la $a0, msg_asc_order		   
	li $v0, 4 
	syscall						#print to msg_asc_order	

	la $a0, msg_desc_order		 
	li $v0, 4 
	syscall						#print to msg_desc_order	

	la $a0, msg_exit			  
	li $v0, 4 
	syscall						#print to msg_exit	

	la $a0,diaxwrismos			
	li $v0, 4 
	syscall						#print to diaxwrismos


	li $v0, 5						#prompt user to enter a menu choice 1-4
	syscall
	move $t2, $v0                   #stores the user's input in $t2    
	
	beq $t2, 1, insert_node			#an $t2=1 goto insert_node
	beq $t2, 2, delete_node			#an $t2=2 goto delete_node
	beq $t2, 3, order_ascending		#an $t2=3 goto order_ascending
	beq $t2, 4, order_decending		#an $t2=4 goto order_decending
	beq $t2, 0, exit 	 			#an $t2=0 goto  exit

	j error_input                #an o xrhsths den dosei sosto arithmo

#Node insertion
insert_node:

	la $a0,msg_enter_int			#print msg_enter_int
	li $v0, 4 
	syscall
	
	bne $s0,$zero, nextNode			#if nodes exist goto nextNode
	
	
#firstnode creation
firstNode:
	li $a0,8					# 2 x sizeof(int)
	li $v0,9					#sbrk - system call 9, dynamic memory allocation
	syscall						#allocation 8 bytes      
		
	sw $v0, pointer    			#pointer  is address of first allocated byte
	move $t4, $v0				
		
	li $v0,5					#read int
	syscall
	sw $v0,pointer($s1)			#stores  int1 ston 1st place of p which is the data

	addi $s1, $s1, 4			#stores int2 that represents address to the second byte
	sw $zero, pointer($s1)
		
	j print

# next node insertion	
nextNode:
	li $v0, 5              		#read int
	syscall					
		
	addi $s1, $s1, 4			#allocates the two words of memory for data and address as in the case of firstnode
	sw $v0, pointer($s1) 				
	addi $s1, $s1, 4			
	sw $zero, pointer($s1)		
		
	j print						#prints the list of integers inserted
	
print:
	la $a0,msg_node					
	li $v0, 4 
	syscall

	addi $s1, $s1, 4			
	addi $s0, $s0, 1

	li $v0, 1
	move $a0, $s0
	syscall
		
	li $v0, 1					
	addi $s1, $s1, -4			#deletes the part that has the address to print only the data
	lw $a0, pointer($s1)
		
	li $v0, 4
	la $a0, tab
	syscall
					
	bgt $s0, 1, sort			#goto to sort function to sort the integers after the last input
	j Main_Menu
	
#delete choice is not implemented,many crashes to the program dont know why.returns user to main menu			
delete_node:

	la $a0, msg_crash
	li $v0, 4 
	syscall
	j Main_Menu
	
#ascending order list of nodes		
order_ascending:
	
	la $a0,msg_List_ascend					  #print msg_List_ascend
	li $v0, 4 
	syscall
		
	move $s6, $s1                         	#stores the current position of pointer
	addi $s5, $zero, 0                    	#counter for total nodes
	addi $s1, $zero, 0
	
printLoop:
	beq $s5, $s0, exit_print_AscList
	li $v0, 1
	lw $a0, pointer($s1)
	syscall
			
	la $a0, Space
	li $v0, 4 
	syscall
			
	addi $s1, $s1, 8
	addi $s5, $s5, 1
	j printLoop
			
exit_print_AscList:
			
	la $a0, tab
	li $v0, 4 
	syscall
			
	move $s1, $s6                      #restore value of the pointer

	j Main_Menu
		
#descending order list of nodes			
order_decending:		
		
	la $a0,msg_List_descend					#print msg_List_descend
	li $v0, 4 
	syscall
		
	move $s6, $s1                        #stores the current position of pointer
	addi $s5, $zero, 0                   #counter for total nodes
	addi $s1, $zero, 16
printLoop1:
	beq $s5, $s0, exit_print_descList	#compares temporary counter to constant counter of nodes
	li $v0, 1
	lw $a0, pointer($s1)
	syscall
			
	la $a0, Space
	li $v0, 4 
	syscall
			
	addi $s1, $s1, -8					#moves 2 bytes,8 positions on memory back
	addi $s5, $s5, 1					#stores the one byte representing the data/int
	j printLoop1
			
exit_print_descList:
			
	la $a0, tab
	li $v0, 4 
	syscall
			
	move $s1, $s6                      	#restore value of the pointer

	j Main_Menu
		

#sort function	
sort:
	move $s6, $s1			#sores current position of our pointer 
	
next_execution:	
	addi $s1, $zero, 0		#set pointer value to the beginning of the index
	addi $s5, $zero, 1		#counter number of total nodes
	addi $s4, $zero, 0		#counter for swap_ints performed for each run
		
#loop to check every int with the following and make changes accordingly		
loop:
	beq $s5, $s0, exitLoop	
	lw $t0, pointer($s1)
	addi $s1, $s1, 8
	lw $t1, pointer($s1)
	addi $s5, $s5, 1	
	beq $t1,$zero, no_swap_ints		#goto no_swap_ints
	bge $t0, $t1, swap_ints			#goto swap ints function
			
no_swap_ints:
	j loop
#swap two ints positions	
swap_ints:
	addi $s4, $s4, 1				#increment swap_ints counter by one
	addi $s1, $s1, -8				
	sw $t1, pointer($s1)			#stores temporary at $t1 current pointer position
	addi $s1, $s1, 8				#brings the other to the new position
	sw $t0, pointer($s1)		
	j loop	
				
exitLoop:
	beqz $s4, sort_List
	addi $s4, $zero, 0
	j next_execution
		
sort_List:				
	move $s1, $s6						#resets the value of the pointer		
	j Main_Menu
#Error input from the user
	error_input:
		la $a0, msg_err					#print the error message
		li $v0, 4 
		syscall
			
		j Main_Menu			

#termatise to programma
exit:
	
	la $a0,msg_end_prog				#print msg_end_prog
	li $v0, 4 
	syscall

	li $v0,10						#end of program
	syscall

#data section
.data
msg_app: 					.asciiz "Main Menu:\n"
diaxwrismos: 				.asciiz "------------------------------------------------------\n"
msg_insert: 				.asciiz "1. Insert node \n"
msg_delete: 				.asciiz "2. Delete node \n"
msg_asc_order: 				.asciiz "3. Print list ascending order\n"
msg_desc_order: 			.asciiz "4. Print list descending order\n"
msg_exit: 					.asciiz "0. Exit program\n"	
msg_enter_int: 				.asciiz "\nEnter an Integer: "
msg_node: 					.asciiz "\nNode: "
msg_crash: 					.asciiz "\nDelete mode not completed,sorry return to main menu: "
msg_del_node: 				.asciiz "\nNode that you want to delete:\n"
msg_List_ascend: 			.asciiz "\nList in Ascendng order:\n"
msg_List_descend: 			.asciiz "\nList in Descending order:\n"
msg_err: 					.asciiz "\nWrong Input. Please try again!\n\n"
Space: 						.asciiz " "
tab: 						.asciiz "\n"
msg_end_prog: 				.asciiz "\nProgram ended."
pointer:.word 4
	