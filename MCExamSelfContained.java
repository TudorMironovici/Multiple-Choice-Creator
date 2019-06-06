//======================================================================================
// File: MCExamSelfContained              Creator: Tudor Mironovici (tcm26@njit.edu)
//
// Purpose: Lets a user take a multiple choice exam, with scantron-like answer choices.
//======================================================================================
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.ArrayList;

public class MCExamSelfContained
{
   private static String[] questionText =                      // Stores the multiple choice question text.
   {
      "In the following array, what is the value of fees.length?\n\n\tdouble[][] fees={{3.00, 3.50},{6.35, 7.35},{9.00, 5.00}};",
      "Having multiple class methods of the same name where each method has a different number of or type of parameters is known as:",
      "What is y after the following switch statement?\n\n\tint x=65, y=66, z=67;\n\tswitch (z-x)  {\n\t\tcase 1: y = 67; break;\n\t\tcase 2: y = 68; break;",
      "The following nested loop structure will execute the inner most statement (x++) how many times?\n\n\tfor (int j = 0; j < 100; j++)\n\t\tfor (int k = 10; k > 0; k--)\n\t\t\t\tx++;",
      "In Java, arrays are ___________",
      "Assume an int array, candy, stores the number of candy bars sold by a group of children where\ncandy[j] is the number of candy bars sold by child j. Assume there are 12 children in all.\nWhat does the following method do?\n\n\t\tpublic int question6( ) {\n\t\t\tint value1 = 0;\n\t\t\tint value2 = 0;\n\t\t\tfor (int j = 0; j <12; j++)\n\t\t\t\tif (candy[j] > value1){\n\t\t\t\t\tvalue1 = candy[j];\n\t\t\t\t\tvalue 2 = j;\n\t\t\t\t}\n\t\t\treturn value2;\n\t\t}",
      "Static methods can",
      "The Container class has one integer attribute named area that is set to a default value of 3. There is\none accessor method named getArea() that returns the area.\nChoose the code to fill in that will display the following on the screen:\t\t333\n\n\n\tContainer [] c = {new Container(), new Container(), new Container() };\n\tfor (int i = 0; i < c.length; i++)\n\t\tSystem.out.print( _____code to fill in_____ );",
      "In the following code, what is the printout?\n\n\tpublic class Test {\n\t\tpublic static void main(String[] args {\n\t\t\tint[] list1 = {1, 0, 1};\n\t\t\tint[] list2 = {0, 1, 0};\n\t\t\tlist2 = list1;\n\t\t\tlist1[0] = 0; list1[1] = 1; list2[2] = 2;\n\t\t\tfor (int i = 0; i < list1.length; i++)\n\t\t\t\tSystem.out.print(list1[i] + \" \");\n\t\t}\n\t}",
      "To initialize a String array names to store the three Strings \"Huey\", \"Duey\" and \"Louie\", you\nwould do"
   };
   private static String[][] answerText =                      // Stores the multiple choice answer choices for each individual question.
   {
      {  // Question 1
         "1",
         "2",
         "4",
         "6",
         "None of the above."
      },
      {  // Question 2
         "method overriding",
         "method overloading",
         "information hiding",
         "encapsulation",
         "None of the above."
      },
      { // Question 3
         "65",
         "67",
         "68",
         "69",
         "None of the above."
      },
      {  // Question 4
         "100",
         "200",
         "1,000",
         "2,000",
         "None of the above."
      },
      {  // Question 5
         "primitive data types",
         "primitive data types if the type stored in the array is a primitive data type and objects otherwise",
         "objects",
         "interfaces",
         "None of the above."
      },
      {  // Question 6
         "It returns the total number of children who sold 0 candy bars",
         "It returns the index of the child who sold the most candy bars",
         "It returns the number of candy bars sold by the child who sold the most candy bars",
         "It returns the total number of children who sold more than 0 candy bars",
         "None of the above."
      },
      {  // Question 7
         "invoke other static methods",
         "reference any instance data",
         "reference non-static instance data",
         "invoke non-static methods",
         "None of the above."
      },
      {  // Question 8
         "c.area",
         "c[i].getArea()",
         "c[area]",
         "c[i].area",
         "None of the above."
      },
      { // Question 9
         "1 2 1",
         "0 1 2",
         "0 1 1",
         "1 0 1",
         "None of the above."
      },
      {  // Question 10
         "String names = {\"Huey\", \"Duey\", \"Louie\"};",
         "String[ ] names = new String{\"Huey\", \"Duey\", \"Louie\"};",
         "String names[3] = {\"Huey\", \"Duey\", \"Louie\"};",
         "String names; names[0] = \"Huey\"; names[1] = \"Duey\"; names[2] = \"Louie\";",
         "None of the above."
      }
   };
   private static String error = "This text shouldn't pop up. If it does, contact me at tcm26@njit.edu - sorry about this!";        // Used as placeholder for the reason Array bank.
   private static String[][] reasonText =
   {
      {  // Question 1
         "Even though the double[][] \"fees\" is one long Array, .length goes into the \"fees\" Array one layer in and counts how many Arrays are inside. The correct answer is 3.", 
         "The .length function in this case counts how many Arrays are in the Array \"fees\", not how many doubles are in one of the inner Arrays. The correct answer is 3.", 
         "The .length function looks inside the \"fees\" Array and sees how many Arrays are inside. The correct answer is 3.", 
         "Even though the double[][] \"fees\" is one long Array, .length goes into the \"fees\" Array one layer in and counts how many Arrays are inside. The correct answer is 3.", 
         error
      },
      {  // Question 2
         "Method overriding has to do with changing changing what the output value is rather than the type of parameters.", 
         error, 
         "Information Hiding is hiding the data which is being affected by that implementation. Use of private and public comes under this. For example, hiding the variables of the classes.", 
         "Encapsulation is just putting all similar data and functions into a group (having things be inside the Class header).", 
         "The correct answer is B - one of the answer choices."
      },
      {  // Question 3
         "z-x is 67-65, which equals 2. This goes to case 2, which sets y to 68 and breaks out of the switch statement. When it prints out y, it gets the value of 68.", 
         "z-x is 67-65, which equals 2. This goes to case 2, which sets y to 68 and breaks out of the switch statement. When it prints out y, it gets the value of 68.", 
         error, 
         "z-x is 67-65, which equals 2. This goes to case 2, which sets y to 68 and breaks out of the switch statement. When it prints out y, it gets the value of 68.", 
         "z-x is 67-65, which equals 2. This goes to case 2, which sets y to 68 and breaks out of the switch statement. When it prints out y, it gets the value of 68."
      },
      {  // Question 4
         "The outter-most loop starts at j = 0 and ends after j = 99, so it happens 100 times. The inner-most loop starts at k = 10 and ends after k = 1, so it happens 10 times.\nk gets reset every time j incements, so this it effectively doing 10*100.", 
         "The outter-most loop starts at j = 0 and ends after j = 99, so it happens 100 times. The inner-most loop starts at k = 10 and ends after k = 1, so it happens 10 times.\nk gets reset every time j incements, so this it effectively doing 10*100.", 
         error, 
         "The outter-most loop starts at j = 0 and ends after j = 99, so it happens 100 times. The inner-most loop starts at k = 10 and ends after k = 1, so it happens 10 times.\nk gets reset every time j incements, so this it effectively doing 10*100.", 
         "The outter-most loop starts at j = 0 and ends after j = 99, so it happens 100 times. The inner-most loop starts at k = 10 and ends after k = 1, so it happens 10 times.\nk gets reset every time j incements, so this it effectively doing 10*100."
      },
      {  // Question 5
         "Primitive data types are booleans, bytes, chars, shorts, ints, longs, floats, and doubles.", 
         "Anything that uses a primitive data type is cannot be a primitive data type - it's an object.", 
         error, 
         "An interface is an abstract type that is used to specify a behavior that classes must implement.", 
         "The correct answer is C - one of the answer choices."
      },
      {  // Question 6
         "The for loop goes through each child in the list, and checks if the amount they sold is greater than \"value1\", which starts out at 0. If the child sold more than the value of \"value1\", \"value1\" gets overwritten,\n and \"value2\" gets saved as the child's number (or - index). At the end, the child's number gets printed, which would be the child with the most candy sold.", 
         error, 
         "The for loop goes through each child in the list, and checks if the amount they sold is greater than \"value1\", which starts out at 0. If the child sold more than the value of \"value1\", \"value1\" gets overwritten,\n and \"value2\" gets saved as the child's number (or - index). At the end, the child's number gets printed, which would be the child with the most candy sold.", 
         "The for loop goes through each child in the list, and checks if the amount they sold is greater than \"value1\", which starts out at 0. If the child sold more than the value of \"value1\", \"value1\" gets overwritten,\n and \"value2\" gets saved as the child's number (or - index). At the end, the child's number gets printed, which would be the child with the most candy sold.", 
         "The for loop goes through each child in the list, and checks if the amount they sold is greater than \"value1\", which starts out at 0. If the child sold more than the value of \"value1\", \"value1\" gets overwritten,\n and \"value2\" gets saved as the child's number (or - index). At the end, the child's number gets printed, which would be the child with the most candy sold."         
      },
      {  // Question 7
         error, 
         "References can only be made based on if the intstance is also static.", 
         "References can only be made to static instances.", 
         "Static methods can only invoke static methods.", 
         "The correct answer is A - one of the answer choices."
      },
      {  // Question 8
         "c.area doesn't work because the method is named \"getArea()\", and c is the Container Array, not the individual Container objects.", 
         error, 
         "c[area] would return the Container object at index area. However, \"area\" isn't an initialized int (that's why the for loop initialized \"i\", c[i] would work). You also need to call the method by doing .getArea().", 
         "c[i].area is almost correct - the method is named \"getArea\" and has to have no parameters. So rather than .area, .getArea() should be used.", 
         "The correct answer is A - one of the answer choices."
      },
      {  // Question 9
         "This main method initializes 2 Arrays (list1 and list2) with values, then sets list2 equal to list1, which means that all of the values of list2 are references to list1's values.\nThen, list1 is set to 0 at index 0, list 1 is set to 1 and index 1, and list2 is set to 2. Then, the for loop goes through each index of list1 and prints it out with a space afterwards.", 
         "This main method initializes 2 Arrays (list1 and list2) with values, then sets list2 equal to list1, which means that all of the values of list2 are references to list1's values.\nThen, list1 is set to 0 at index 0, list 1 is set to 1 and index 1, and list2 is set to 2. Then, the for loop goes through each index of list1 and prints it out with a space afterwards.", 
         error, 
         "This main method initializes 2 Arrays (list1 and list2) with values, then sets list2 equal to list1, which means that all of the values of list2 are references to list1's values.\nThen, list1 is set to 0 at index 0, list 1 is set to 1 and index 1, and list2 is set to 2. Then, the for loop goes through each index of list1 and prints it out with a space afterwards.", 
         "This main method initializes 2 Arrays (list1 and list2) with values, then sets list2 equal to list1, which means that all of the values of list2 are references to list1's values.\nThen, list1 is set to 0 at index 0, list 1 is set to 1 and index 1, and list2 is set to 2. Then, the for loop goes through each index of list1 and prints it out with a space afterwards."
      },
      {  // Question 10
         "You initialized \"names\" as a String object rather than a String Array. This would work: String[] names = {\"Huey\", \"Duey\", \"Louie\"};", 
         "You initialized \"names\" as a String Array, but then had it set to a new String object. This would work: String[] names = new String[]{\"Huey\", \"Duey\", \"Louie\"};", 
         "When initializing a String Array, you don't specify the length on the left - you specify the length on the right (String names[] = new String[3];). When initializing a String literal, you don't specify at all.\nThis would work: String names[] = {\"Huey\", \"Duey\", \"Louie\"};", 
         "You instantiate \"names\" as a String object rather than a String Array. This would work: String[] names = new String[3]; names[0] = \"Huey\"; names[1] = \"Duey\"; names[2] = \"Louie\";", 
         error
      }
   };                 // Question:     1    2    3    4    5    6    7    8    9   10
   private static char[] answerKey = {'E', 'B', 'C', 'C', 'C', 'B', 'A', 'B', 'C', 'E'};
   private static ArrayList<Integer> skipped = new ArrayList<Integer>();
   private static ArrayList<Integer> incorrect = new ArrayList<Integer>();
   private static char[] userAnswers = new char[questionText.length];
   private static Scanner scan = new Scanner(System.in);
   private static int correct = 0;
   private static int threshold = 70;                          // The grade of the student that is deemed "good" 
   
   
   //------------------------------------------
   // Prints out the intro to the exam, the
   // questions, and accepts the user's input.
   //------------------------------------------  
   public static void main(String[] args)
   {
      intro();
      for (int q = 0; q < questionText.length; q++)
      {
         ask(q);
      }
      
      goBack();
      double score = (double)correct/(double)questionText.length*100;
      DecimalFormat to2Decimals = new DecimalFormat("#,###,##0.00");
      System.out.println("You got "+correct+" correct, which is "+to2Decimals.format(score)+"%.\n"+((score >= threshold) ? "Great job!" : "\"By failing to prepare, you are preparing to fail.\" - Benjamin Franklin."));
      feedback();
   }
   
   //--------------------------------------
   // Prints the introduction to the exam.
   //-------------------------------------- 
   public static void intro()
   {
      System.out.print(
      "Welcome to the exam!\n"+
      "You've worked hard all semester, now here's your chance to show your brain power!\n\n"+
      
      "You have unlimited time to finish the exam, since I don't know how to implement a time feature (yet).\n"+
      "There are "+questionText.length+" multiple choice questions, and three open endeds. This program, only does the multiple choice ones.\n"+
      "If you want to skip a question and save it for later, type 'S' as your answer.\n\n\n\n"
      );
   }
   
   //-------------------------------------------------
   // Prints out the question text, takes the answer,
   // and tells the user if they're correct or not.
   //-------------------------------------------------
   public static void ask(int q)
   {
      System.out.println((q+1)+") "+questionText[q]+"\n\n");
      for (int ae = 65; ae < 70; ae++)
      {
         System.out.println("\t"+(char)ae+". "+answerText[q][ae-65]);
      }
      System.out.print("\nAnswer: ");
      
      //----------------------------
      // Checks if the input is one
      // of the available options.
      //----------------------------
      char input;
      do          
      {
         input = Character.toUpperCase(scan.next().charAt(0));
         if (input != 'A' && input != 'B' && input != 'C' && input != 'D' && input != 'E' && input != 'S')
         {
            System.out.print("That's not one of the available choices. Please try again: ");
         }
      }
      while (input != 'A' && input != 'B' && input != 'C' && input != 'D' && input != 'E' && input != 'S');
      userAnswers[q] = input;
      
      //---------------------------------
      // Checks if the answer was 
      // correct, skipped, or incorrect.
      //---------------------------------
      if (input == answerKey[q])
      {
         System.out.println("Correct.");
         correct++;
      }
      else if (input == 'S')
      {
         System.out.println("Skipped.");
         skipped.add(q);
      } 
      else
      {
         System.out.println("Incorrect.");
         incorrect.add(q);
      }
      System.out.println("\n");
   }
   
   //-------------------------------------------
   // Goes through all the "skipped" questions.
   //-------------------------------------------
   public static void goBack()
   {
      while (skipped.size() != 0)
      {
         System.out.print("You skipped questions: ");
         formatPrint(skipped);
         boolean pass = false;
         Integer question = 0;
         
         //------------------------------------------
         // Checks to make sure that the user inputs
         // an integer as the test question.
         //------------------------------------------
         while (!pass)
         {
            try
            {
               System.out.print("Please enter the number of the question you want to go back to: ");
               question = scan.nextInt() -1;
               pass = true;
            }
            catch (Exception notAnInt)
            {
               System.out.println("That's not an integer.");
               scan.next();
            }
         }
         
         //--------------------------------------
         // Handles integer inputs that don't
         // line up with saved question numbers.
         //--------------------------------------
         while (skipped.contains(question) == false)
         {
            if (question > questionText.length-1 || question < 0)
            {
               System.out.println("This was not one of the available multiple choice question.");
            }
            else
            {
               System.out.println("Sorry, you cannot go back to questions you have already submitted.");
            }
            System.out.print("Please enter the number of the question you want to go back to: ");
            question = scan.nextInt() - 1;
         }
         
         //------------------------------------
         // Removes the question from the list 
         // of skipped questions, and asks it.
         //------------------------------------
         skipped.remove(question);
         ask(question);
      }
   }
   
   //--------------------------
   // Sorts the given list and 
   // prints it out nicely.
   //--------------------------
   public static void formatPrint(ArrayList<Integer> list)
   {
      Collections.sort(list);
      for (int index = 0; index < list.size()-1; index++)
      {
         System.out.print((((int)list.get(index))+1)+((list.size() > 1) ? ", ": ""));
      }
      System.out.println((((list.size() > 1)) ? "and " : "")+((int)list.get(list.size()-1)+1));
   }
   
   //--------------------------------
   // Gives the user feedback on the 
   // questions they got wrong.
   //--------------------------------
   public static void feedback()
   {
      if (incorrect.size() == 0)
      {
         System.out.print("Congrats on getting all them right!");
         return;
      }
      
      Collections.sort(incorrect);
      System.out.println("\n\nHere are the questions you got wrong:\n");
      for (int questionWrongIndex = 0; questionWrongIndex < incorrect.size(); questionWrongIndex++)
      {
         System.out.println("Question "+(incorrect.get(questionWrongIndex)+1)+":\nYour answer: "+userAnswers[incorrect.get(questionWrongIndex)]+". "+answerText[incorrect.get(questionWrongIndex)][(int)answerKey[incorrect.get(questionWrongIndex)] - 65]+"\nReason: "+reasonText[incorrect.get(questionWrongIndex)][(int)userAnswers[incorrect.get(questionWrongIndex)] - 65]+"\n");
      }
   }
}