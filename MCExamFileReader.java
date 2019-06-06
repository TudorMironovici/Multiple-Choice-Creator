//===========================================================================================================
// File: MCExamFileReader              Creator: Tudor Mironovici tcm26@njit.edu
//
// Purpose: Lets a user take a multiple choice exam, with scantron-like answer choices from a formated file.
//===========================================================================================================
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class MCExamFileReader 
{
   // -----------------------------Variables that Multiple Methods Use------------------------------
   private static int correct = 0; 
   private static int[] userAnswers;
   private static ArrayList<Integer> answerKey = new ArrayList<Integer>();
   private static ArrayList<Integer> skipped = new ArrayList<Integer>();
   private static ArrayList<Integer> incorrect = new ArrayList<Integer>(); 
   private static ArrayList<String> questionTextBank = new ArrayList<String>(); 
   private static ArrayList<ArrayList<String>> answerTextBank = new ArrayList<ArrayList<String>>();
   private static ArrayList<ArrayList<String>> reasonTextBank = new ArrayList<ArrayList<String>>();
   //-----------------------------------------------------------------------------------------------
   
   
   public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException
   {
      //----------------------------------------Variables----------------------------------------
      double score;
      int threshold = 70;                                                                             //---------------------
      String good = "Nice job!";                                                                      // Changable Variables
      String bad = "\"By failing to prepare, you are preparing to fail.\" - Benjamin Franklin.";      //---------------------
      DecimalFormat to2Decimals = new DecimalFormat("#,###,##0.00");
      //-----------------------------------------------------------------------------------------
      
      readFile();
      for (int q = 0; q < questionTextBank.size(); q++)
      {
         ask(q);
      }
      
      goBack();
      score = (double)correct/(double)questionTextBank.size()*100;
      System.out.println("You got "+correct+" correct, which is "+to2Decimals.format(score)+"%.\n"+((score >= threshold) ? good : bad));
      feedback();
   }
   
   //----------------------------------
   // Reads and handles the test file.
   //----------------------------------
   public static void readFile() throws FileNotFoundException, IllegalArgumentException
   {
      //----------------------------------------Variables----------------------------------------
      boolean fileOK = false;
      boolean introDone = false;
      boolean questionTextDone = false;
      boolean emptyString = false;
      boolean reasonGiven = false;
      int extentionStart = 0;
      int questionCounter = 1;
      int answerIndex = 0;
      int tempCounter;
      ArrayList<String> tempAnswerTextArray = new ArrayList<String>();
      ArrayList<String> tempReasonTextArray = new ArrayList<String>();
      Exception badFileType = new IllegalArgumentException("This file is the wrong file type.");
      Scanner testFileNameScanner = new Scanner(System.in);
      Scanner testReader;
      String testFileDirectory = System.getProperty("user.dir");
      String testFileLocation;
      String testFileName = "";           //-----------
      String fileExtention = ".txt";      // Changable
      String questionEnd = "***";         // Variables
      String reasonKey = "###";           //-----------
      String intro = "";
      String currentLine;
      String tempQuestionLine;
      String tempQuestionText = "";
      String tempAnswerLine = "";
      String tempAnswerText = "";
      String tempReasonText = "";
      String[] tempQuestionParsed;
      String[] tempAnswerParsed;
      File testFile;
      //-----------------------------------------------------------------------------------------
      
      
      if (testFileName.length() == 0)
      {
         System.out.println("Place the formatted (type in 'F' to review proper formatting) "+fileExtention+" file in the same directory as this file ("+testFileDirectory+").");
      }
      
      while (!fileOK)
      {
         try
         {
            if (testFileName.length() == 0)
            {
               System.out.print("Type in the name of the test (of 'F' to review proper formatting): ");
               testFileName = testFileNameScanner.nextLine();
               
               if (testFileName.equals("F") || testFileName.equals("f"))
               {
                  formatText(fileExtention, questionEnd, reasonKey);
                  System.out.print("Type in the name of the test file: ");
                  testFileName = testFileNameScanner.nextLine();
               }
            }
            //---------------------------------
            // Checks to see if the user input
            // an extention in the file name.
            //---------------------------------
            for (int nameIndex = 0; nameIndex < testFileName.length(); nameIndex++)
            {
               if (testFileName.charAt(nameIndex) == '.')
               {
                  extentionStart = nameIndex;
                  break;
               }
            }
            
            //-----------------------------------------------
            // If there is an extention, handles whether
            // it's the wrong extention type or not. If not,
            // removes the extention from testFileName. 
            //-----------------------------------------------
            if (extentionStart != 0)
            {
               extentionStart = 0;
               
               if (testFileName.length() > 4 && !(testFileName.substring(testFileName.length()-4).equals(fileExtention)))
               {
                  throw badFileType;
               }
               else if (testFileName.length() < 5)
               {
                  throw badFileType;
               }
               else
               {
                  testFileName = testFileName.substring(0, testFileName.length()-4);
                  fileOK = true;
               }
            }
            
            testFileLocation = testFileDirectory+"\\"+testFileName+fileExtention;
            testFile = new File(testFileLocation);
            testReader = new Scanner(testFile);
            
            //====================================
            // Actually gets to reading the file.
            //====================================
            while (testReader.hasNextLine())
            {
               fileOK = true;
               currentLine = testReader.nextLine();
               //---------------------------
               // Creates the intro String.
               //---------------------------
               if (!introDone)
               {
                  if (currentLine.length() == 0)
                  {
                     intro += "\n";
                  }
                  else if (!Character.isDigit(currentLine.charAt(0)))
                  {
                     intro += currentLine + "\n";
                  }
                  else
                  {
                     System.out.println(intro);
                     introDone = true;
                  }
               }
               
               //-----------------------------------------------
               // If the Scanner sees that the current line 
               // begins with a number, it knows that it should
               // mark the line as a "question"...
               //-----------------------------------------------
               if (currentLine.length() != 0 && Character.isDigit(currentLine.charAt(0)))
               {
                  tempQuestionLine = currentLine;
                  tempQuestionParsed = tempQuestionLine.split(" ");
                  answerIndex = 0;
                  
                  if (questionCounter > 1)
                  {
                     answerTextBank.add(tempAnswerTextArray);
                     tempAnswerTextArray = new ArrayList<String>();
                     reasonTextBank.add(tempReasonTextArray);
                     tempReasonTextArray = new ArrayList<String>();
                  }
                  while (!questionTextDone)
                  {
                     //------------------------------------------------
                     // ...until it sees the "questionEnd" key String.
                     //------------------------------------------------
                     if (!(tempQuestionLine.length() == 0) && tempQuestionParsed[tempQuestionParsed.length-1].equals(questionEnd))
                     {
                        tempQuestionParsed[tempQuestionParsed.length-1] = "";
                        questionTextDone = true;
                     }
                     
                     for (String word : tempQuestionParsed)
                     {
                        tempQuestionText += word+" ";
                     }
                     
                     tempQuestionText += "\n";
                     if (!questionTextDone)
                     {
                        tempQuestionLine = testReader.nextLine();
                        tempQuestionParsed = tempQuestionLine.split(" ");
                     }
                  }
                  questionTextBank.add(tempQuestionText);
                  tempQuestionText = "";
                  questionCounter++;
               }
               
               //--------------------------------------------------------
               // Once the program recognizes the question text is done, 
               // it goes into reading the multiple choice answers 
               //--------------------------------------------------------
               else if (introDone)
               {
                  tempAnswerLine = currentLine;
                  reasonGiven = false;
                  emptyString = false;
                  questionTextDone = false;
                  tempAnswerText = new String();
                  tempReasonText = new String();

                  if (tempAnswerLine.length() == 0)
                  {
                     emptyString = true;
                  }
                  if (!emptyString)
                  {
                     tempAnswerParsed = tempAnswerLine.split(" ");
                     for (String word : tempAnswerParsed)
                     {
                        if (word.equals(reasonKey))
                        {
                           reasonGiven = true;
                           continue;
                        }
                        
                        if (!reasonGiven) 
                        {
                           tempAnswerText += word+" ";
                        }
                        
                        else
                        {
                           tempReasonText += word+" ";
                        }
                     }
                     
                     //-----------------------------------
                     // This handles the correct answers.
                     //-----------------------------------
                     if (!reasonGiven)
                     {
                        tempReasonText = "This text shouldn't pop up. If it does, contact me at tcm26@njit.edu - sorry about this!";      // Placeholder text. It should never get called upon by the program.
                        answerKey.add(answerIndex);
                     }
                     tempAnswerTextArray.add(tempAnswerText);
                     tempReasonTextArray.add(tempReasonText);
                     answerIndex++;
                  }
               }
            }
            
            if (tempAnswerLine.length() != 0)
            {
               answerTextBank.add(tempAnswerTextArray);
               tempAnswerTextArray = new ArrayList<String>();
               reasonTextBank.add(tempReasonTextArray);
               tempReasonTextArray = new ArrayList<String>();
            }
            testReader.close();
         }
         
         //-------------------------
         // Catches the exceptions.
         //-------------------------
         catch (IllegalArgumentException BadFileType)
         {
            System.out.println(badFileType.getMessage());
         }
         
         catch (Exception BadFile)
         {
            System.out.println("This file doesn't exist. Double-check your spelling please.");
            fileOK = false;
            testFileName = "";
         }
         userAnswers = new int[questionTextBank.size()];
      }
   }
   
   //---------------------------------------
   // Prints out the text which explains
   // how to properly format the test file.
   // Can also be seen in the readme.txt
   //---------------------------------------
   public static void formatText(String fileExtention, String questionEnd, String reasonKey)
   {
      System.out.println(
      "\nThere are three major sections to the "+fileExtention+" file.\n"+
      "\t\t\t- the introduction\n"+
      "\t\t\t- the question\n"+
      "\t\t\t- the answer / reason\n"+
      "\n"+
      "- The first section of the "+fileExtention+" should be the introduction of your exam. Feel free to skip this and get to the\n"+
      "questions right away by just starting with the question. No formatting is needed for the introduction.\n"+
      "- The question section can be repeated as many times as needed. However, make sure you always follow the proper\n"+
      "formatting, or else the program will get mad at you and won't work. The only thing you have to make sure to do for\n"+
      "the question section is to have the first character of the first line of your question's text be an integer,\n"+
      "prerably to represent the question's number. Feel free to stylistically format the question's number however, such\n"+
      "\"1.)\" or \"1:\". Then, put \""+questionEnd+"\" at the end of the last line of your question text, seperated by a blank space.\n"+
      "- The answer / reason section can also be repeated as many times as needed, but always has to follow the question\n"+
      "section. So after a question, put the multiple choice options with their reasons, and then another question (if\n"+
      "applicable). Note that you CANNOT start the multiple choice answer text line with an integer. This will make the\n"+
      "program think you're starting another question. You need to start the line with the letter of the multiple choice\n"+
      "option. Feel free to stylistically format the multiple choice option's letter however, such as \"A.\" or \"b)\". \n"+
      "Moreover, you can only use one line for the multiple choice answers. To seperate the incorrect multiple choice\n"+
      "option text from the reason as to why it's wrong text, just add \""+reasonKey+"\" right after the last word of the option's\n"+
      "text, with a space before and after. If you want to see an example run, type in \"example\" in the next prompt."
      );
   }
   
   
   //-------------------------------------------------
   // Prints out the question text, takes the answer,
   // and tells the user if they're correct or not.
   //-------------------------------------------------
   public static void ask(int q)
   {
      //---------------------------Variables---------------------------
      boolean inputOK = false;
      int inputIndex = 0;
      char input;
      char correctAnswer;
      char[] availableOptions = new char[answerTextBank.get(q).size()+1];
      Scanner scan = new Scanner(System.in);
      //----------------------------------------------------------------
      
      System.out.println(questionTextBank.get(q));
      for (int mcOption = 0; mcOption < answerTextBank.get(q).size(); mcOption++)
      {
         System.out.println("\t"+answerTextBank.get(q).get(mcOption));
         availableOptions[mcOption] = answerTextBank.get(q).get(mcOption).charAt(0);
      }
      availableOptions[availableOptions.length-1] = 'S';
      System.out.print("\nAnswer: ");
      input = Character.toUpperCase(scan.next().charAt(0));
      correctAnswer = answerTextBank.get(q).get(answerKey.get(q)).charAt(0);
      
      //----------------------------
      // Checks if the input is one
      // of the available options.
      //----------------------------
      while (!inputOK)
      {
         for (char option : availableOptions)
         {
            if (input == option)
            {
               inputOK = true;
               
            }
         }
         if (!inputOK)
         {
            System.out.print("That's not one of the available choices. Please try again: ");
            input = Character.toUpperCase(scan.next().charAt(0));
            inputIndex++;
         }
      }
      userAnswers[q] = inputIndex;
      
      //---------------------------------
      // Checks if the answer was 
      // correct, skipped, or incorrect.
      //---------------------------------
      if (input == correctAnswer)
      {
         System.out.println("Correct.\n");
         correct++;
      }
      else if (input == 'S')
      {
         System.out.println("Skipped.\n");
         skipped.add(q);
      } 
      else
      {
         System.out.println("Incorrect.\n");
         incorrect.add(q);
      }
   } 
   
   //-------------------------------------------
   // Goes through all the "skipped" questions.
   //-------------------------------------------
   public static void goBack()
   {
      while (skipped.size() != 0)
      {
         //-------------Variables-------------
         boolean integer = false;
         Integer question = 0;
         Scanner scan= new Scanner(System.in);
         //------------------------------------
         
         System.out.print("You skipped questions: ");
         formatPrint(skipped);
         
         //------------------------------------------
         // Checks to make sure that the user inputs
         // an integer as the test question.
         //------------------------------------------
         while (!integer)
         {
            try
            {
               System.out.print("Please enter the number of the question you want to go back to: ");
               question = scan.nextInt() -1;
               integer = true;
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
            if (question > questionTextBank.size() - 1 || question < 0)
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
         System.out.print((((int)list.get(index))+1)+((list.size() > 2) ? ", ": " "));
      }
      System.out.println((((list.size() > 1)) ? "and " : "")+((int)list.get(list.size()-1)+1));
   }

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
         System.out.println("Question "+(incorrect.get(questionWrongIndex)+1)+":\nYour answer: "+answerTextBank.get(incorrect.get(questionWrongIndex)).get(userAnswers[incorrect.get(questionWrongIndex)])+"\nReason: "+reasonTextBank.get(incorrect.get(questionWrongIndex)).get(userAnswers[incorrect.get(questionWrongIndex)])+"\n");
      }
   }
}
