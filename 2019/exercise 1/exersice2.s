#Author : Georgia Grigoriadou
# ΑΜ : p3160029
 
	.text
	.globl main

main:	
	li $s1, 0           #$s1 keys=0
	li $s2, 0           #$s2 key=0
	li $s3, 0           #$s3 pos=0
	li $s4, 0           #$s4 choice=0
	li $s5, 0           #$s5 telos=0
	li $s6, 0           #$s6 i =0
	la $s7, pinakas 
	lw $s0, N           #N = 10
	
	move $t1, $s7       #t1 = pinakas[i]
	li $t2, 0           #index to pinaka
	li $t3, 0			#t3 = 0
	
for_loop:
         
	bge $s6, $s0, Do                   # $s6 is i, $s0 is N,  if i>N go to exit 
	lw $t7 , pinakas($t2)
	sw $t3 , pinakas($t2)  
	addi $t2, $t2, 4
	addi $s6, $s6, 1                   #i=i+1
	j for_loop
	
Main_run:
#Main menu printing		
Do:	

	la $a0, msg_menu			
	li $v0, 4 
	syscall						        #print "Menu"

	la $a0, msg_insert_key 					
	li $v0, 4 
	syscall						        #print "1.insert_key"

	la $a0, msg_find_key			   
	li $v0, 4 
	syscall						        #print "2.find_key"	

	la $a0, msg_displ_has_table			 
	li $v0, 4 
	syscall						        #print "3.display hash table"	
	
	la $a0, msg_exit 		   
	li $v0, 4 
	syscall						        #print "4.exit"
	
	la $a0, msg_choice		   
	li $v0, 4 
	syscall						        #print "choice?"

	li $v0, 5					      	#prompt user to enter a menu choice 1-4
	syscall
	
	move $t0, $v0                       #stores the user's input in $t0   
	
	beq $t0, 1, first_choice			#an $t0=1 goto insert_key
	beq $t0, 2, second_choice			#an $t0=2 goto find_key
	beq $t0, 3, Display_hash_table		#an $t0=3 goto Display_hash_table		
	beq $t0, 4, fourth_choice 	 		#an $t0=4 goto  exit
	jal error_input
	
while_check:                            #ελεγχος της Do => while(telos ==0)
	beq $s5, 1 , exit                   # $t5 is telos   
	j Do                                #if telos = 1 goto Do







########## choice = 1 insert_key ############	
first_choice:
	la $a0, msg_give_key 		   
	li $v0, 4 
	syscall						    #print "Give new key (greater than zero) :"
	
	li $v0, 5						#prompt user to enter the key
	syscall
	
	move $s2, $v0                   #stores the user's input (key)  in $s2 
	ble $s2, $zero, err_key_input
	move $a0, $s2                   #a0 = key
	jal insert_key
	
#########  end choice = 1   ################	
	
	

	
############  insert_key   ##################
insert_key:
	move $t3, $a0                  #t3 = key 
	move $a0, $t3                 
	jal find_key                   #position = find_key(hash, k ) 
	
	move $t2, $a0                  #$t2 is potition 
	beq $t2, -1, if_has_f          #if potition = -1 go to if_has_f
	
	la $a0, msg_already_has_table  #print the message
	li $v0, 4                      # "key is already in hash table"
	syscall
	
	jal Do 
	
if_has_f: 
	bge $s1 , $s0, full_table_msg  #$s1 is keys and $s2 is N , if keys>=N goto full_table_msg
	move $a0, $t3                  #a0 = key
	
	jal hash_function
	
	move $t2, $a0                  #t2 is position = hash_function(hash, k)
	sll $t6, $t2, 2                #potition*2
	move $t7, $s7                  #$s7 is the address of pinakas ara o $t7 exei tin address tou pinaka
	add $t7, $t7, $t6              #t7 = t7+pinakas
	lw $t7, 0($t7)                 #$t7 = hash[potition]
	sw $t3, 0($t7)                 #hash[position] = k
	addi $s1, $s1, 1               #keys++
	
	jal Do
	
	
full_table_msg:        
	la $a0, msg_full_table	       #print the message
	li $v0, 4                      # " hash table is full"
	syscall
	
	jal Do
		
############  end insert_key    ##############
	
	
	
#############  choice = 2 find_key   #############
second_choice : 
	la $a0, msg_give_key_for_search 	#print the message	   
	li $v0, 4                           #"give key to search for"
	syscall
	
	li $v0, 5				            #prompt user to enter the key		
	syscall 
	
	move $s2, $v0                       #$s2 is the key          
	move $a0, $s2                       #a0 = key
	
	jal find_key
	
	move $s3, $a0                       #$s3 is pos , pos=find_key(hash, k)
	bne $s3, -1, el                     # if pos = -1 goto el
	
	la $a0, msg_not_key_in_hash 		#print the message   
	li $v0, 4                           # "key not in hash table"
	syscall

	jal Do

el: 
	la $a0, msg_key_value 	         	#print the message
	li $v0, 4                           # "key value = "
	syscall
	sll $t6, $s3, 2                     # $t6 = potition*2
	move $t7, $s7                       #$s7 is the address of pinakas ara o $t7 exei tin address tou pinaka
	add $t7, $t7, $t6                   #t7 = t7+pinakas
	lw $t7, 0($t7)
	
	li $v0,1                            #print hash[i]
	move $a0, $t7
	syscall
	
	la $a0, msg_table_pos 		        #print the message
	li $v0, 4                           # "table position = "
	syscall
	
	li $v0,1                          	#print potition
	move $a0, $s3
	syscall
	
	la $a0, tab                         # tab 
	li $v0, 4 
	syscall
	
	jal Do
	
############# end choice = 2 find_key #############	


##########  find_key	############ 
find_key:
	li $t2, 0                      #t2 is potition = 0
	div $a0, $s0                   #k mod N
	mfhi $t2                       #potition= k%N
	li $t4, 0                      #t4 is i = 0
	li $t5, 0                      #t5 is found = 0
	
   
while_:
	
	bge $t4, $s0, continue         #if i>=N bges ap tin while
	bne $t5,$zero, continue        #if found=! 0  bges ap tin while
	addi $t4, $t4,1                #i++
	sll $t6, $t2, 2                # $t6 = potition*2
	move $t7, $s7                  #$s7 is the address of pinakas ara o $t7 exei tin address tou pinaka
	add $t7, $t7, $t6              #t7 = t7+pinakas
	lw $t7, 0($t7)                 #t7 = hash[potition]
	bne $t7, $t2, else_            #if hash[position]=!k goto else
	li $t5, 1                      #found = 1 
	j while_
	
else_:
	addi $t2,$t2,1                 # position++
	div $t2,$s0                    # position= position%N
	mfhi $t2
	j while_
	 
continue:
	bne $t5 , 1, else_2            #if found != 1 
	move $v0, $t2                  #return position
	jr $ra
else_2 :
	li $t0,-1
	move $v0, $t0                  #return -1
	jr $ra
 
###########   end find_key   ###########




######### choice = 3 Display hash #############
Display_hash_table:

	li $t1, 0                   #$t1 is i 

for_displ:	
	bge $t1, $s0, back          #if i >= N goto back
	sll $t2, $t1, 2             #$t2 is potition = i*2 
	move $t7, $s7               #$s7 is the address of pinakas ara o $t7 exei tin address tou pinaka
	add $t7, $t7, $t2           #t7 = t7+pinakas
	lw $t7, 0($t7)              #t7 = hash[potition]
	li $v0,1                    #print hash[i]
	move $a0, $t7
	syscall
	
	addi $t1,$t1, 1             #i++
	j for_displ

back:
	jal Do
##########  end choice = 3 Display hash   ########
	
	
	
######### choice = 4 exit #############
	
fourth_choice:
	addi $s5, $s5, 1
	jal while_check

#########   end choice = 4 exit  #######


#######   hash_function  ########
hash_function:
	div $a0, $s0               # k mod N
	mfhi $t2                   #potition= k%N
	move $t7, $s7              #$s7 is the address of pinakas ara o $t7 exei tin address tou pinaka
	
	
while_has_f:
	sll $t6, $t2, 2            #$t6 is potition = i*2
	add $t7, $t7, $t6          #t7 = t7+pinakas
	lw $t7, 0($t7)             #$t7 = hash[potition]
	beq $t7, $zero, return_    # if hash[potition] == 0 goto return_
	addi $t2, $t2, 1           #position++
	div $t2, $s0               # position= position%N  
	mfhi $t2
	j while_has_f
	
return_: 
	move $a0, $t2
	jr $ra
	
###########  end hash_function   #############################
	
	
#Error key from the user	
err_key_input:
	la $a0, err_msg				#print the error message
	li $v0, 4 
	syscall
	
	jal insert_key
	
#Error input from the user
error_input:
	la $a0, msg_err				#print the error message
	li $v0, 4 
	syscall
			
	jal Main_run			

#termatise to programma
exit:
	la $a0,msg_end_prog			#print msg_end_prog
	li $v0, 4 
	syscall

	li $v0,10					#end of program
	syscall
	
	
	
	
#data section
.data
msg_menu: 					.asciiz "\n Menu:\n"
msg_insert_key: 			.asciiz "1. Insert Key \n"
msg_find_key: 				.asciiz "2. Find Key \n"
msg_displ_has_table: 		.asciiz "3. Display Hash Table\n"
msg_exit: 					.asciiz "4. Exit\n"	
msg_end_prog: 				.asciiz "\nProgram ended."
msg_err: 					.asciiz "\nWrong Input. Please try again!\n\n"
err_msg:                    .asciiz "\n key must be greater than zero\n"
msg_give_key:               .asciiz "\nGive new key (greater than zero) :\n"
msg_choice:                 .asciiz "\n Choice?\n"
msg_already_has_table:      .asciiz "\n Key is already in hash table\n"
msg_full_table:             .asciiz "\n hash table is full\n"
msg_not_key_in_hash:        .asciiz "\n key not in hash table \n"
msg_give_key_for_search:    .asciiz "\n give key to search for\n"
msg_key_value:              .asciiz "\n key value = "
msg_table_pos :             .asciiz "\n table position = "
N:                          .word 10
.align 2
pinakas:					.space 40
tab: 						.asciiz "\n"
