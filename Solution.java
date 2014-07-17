/*
 Numeric Keypad SMS Decoder

Assume that you’re on the T9 SMS mode [Manual Mode]. In this case:
a would be 2
b would be 22
c would be 222
d would be 3 etc

IMPORTANT: To send 2 letters in sequence from the same cell phone button, the user must “pause” before pressing the same button a second time. Use a space character to indicate a pause. 

For example: “ab” would be “2 22”

Example:
“multunus” would be  6885558 8866887777

Finally, as you might expect, pressing zero will send a space. 

Example:
“ruby multunus” would be 777882299906885558 8866887777


The above describes how the encoder works. But your task is to write a decoder instead. 

Example:
Input:  2 2202223033 333
Output: ab cd ef

To keep things simple you do not need to consider Capitals and Numbers.
*/


import java.util.*;

public class Solution {
	public static void main(String[] args){
		
		// Scanner object to take in the input string
		Scanner sc = new Scanner(System.in);
		
		// Store the input string
		String input = sc.nextLine();
		
		//Split the input string into its constituent words
		String[] splitstring = input.split("0");
		
		//Count the number of words
		int noofstrings = splitstring.length;
		
		//Count variable for counting the number of times a number is pressed
		int count = 0;
		
		//Used to store the character form the string array
		char firstchar = 0;
		
		//Character that is used to keep track of the output alphabet
		char outchar = 0;
		
		//To keep track of the previous character
		char oldchar = ' ';
		
		//To keep count of the number of times a character is pressed
		int ccount = 0;
		
		//Final output character 
		char finalchar = 0;
		
		//Loop for each word
		for(int i = 0; i < noofstrings; i++){
				
				//Keep track of the word string of numbers
				String  workingstring  = splitstring[i];
				
				//Setting default value of the oldchar after each word
				oldchar = ' ';
				
				//Setting default value of outchar after each word
				outchar = ' ';
				
				//Setting default value of ccount after each word
				ccount = 0;
				
				//Replacing the blank space character with a parsable integer 
				workingstring.replace(' ', '0');
				
				//Loop to iterate through each number in the word
				for(int j = 0;j<  workingstring.length(); j++){
					
					//Taking the number we will be working with
					firstchar = workingstring.charAt(j);
					
					//Get the character that is pressed first from the number
					char corrosponding = checkstring(firstchar);
					
					//If the number pressed is a space (0)
					if(corrosponding == 1){
						
						//Set the count to 0 
						count = 0;
					}
					
					//If the previous number is not the same as the current number
					if(oldchar != corrosponding ){
						
						//The final character too be set is given as the base character + offset 
						finalchar = (char) (outchar+ccount);
						
						//Check that the character to be printed is an alphabet
						if(finalchar > 96){
							System.out.print(finalchar);	
						}
						
						//Set the character count to 0
						ccount = 0;
						}
					
					//Set the outside character to the corrosponding so that it can be used outside the loop
					outchar = corrosponding;
					
					//If increase the character count if the previous number is the same as the new number
					if( count > 0 && oldchar == corrosponding){
						ccount++;
					}
					
					//Increase the count variable to indicate an increase in char count
					count++;
					
					//If there is a space between the numbers set the count to 0
					if(corrosponding == 1){
						count = 0;
					}
					
					
					// Set old character to the new value
					oldchar = corrosponding;
				}
				
				//check that the output character is not space, if not then print the character
				if((char)(outchar+ccount) != ' '){
					System.out.print((char)(outchar+ccount));
				}else{
					//else do not print the space
				}
				
			//Print the space to differentiate between the words	
			System.out.print(" ");
		}
	}

	
	
	//Takes in the character value of the input number
	//And gives out the character value of the alphabet
	private static char checkstring(int firstchar) {
		
		//Create an array which is large enough for the character value of the number
		int[] check = new int[58];
		
		//Output for the character of blankspace(0)
		check[48] = 1;
		
		//Character value 2 = 50; a = 97; b = 98; c = 99 
		check[50] = 97;
		
		//Character value 3 = 51; d = 100; e = 101; f = 102
		check[51] = 100;
		
		//Character value 4 = 52; g = 103; h = 104; i = 105
		check[52] = 103;
		
		//Character value 5 = 53; j = 106; k = 107; l = 108
		check[53] = 106;
		
		//Character value 6 = 54; m = 109; n = 110; o = 111
		check[54] = 109;
		
		//Character value 7 = 55; p = 112; q = 113; r = 114; s = 115
		check[55] = 112;
		
		//Character value 8 = 56; t = 116; u = 117; v = 118
		check[56] = 116;
		
		//Character value 9 = 57; w = 119; x = 120; y = 121; z = 122
		check[57] = 119;
		
		//Return the alphabet of the corresponding number value 
		return (char) check[firstchar];
	}
}
