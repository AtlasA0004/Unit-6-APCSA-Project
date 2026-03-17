//Import ArrayList and Scanner utils
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Asks for the user to input an English sentence or phrase
 * Using NLP, converts the input into latin
 * Returns the translation
 * @author Atlas A
 * @since March 2026
*/
public class Translator {

  //Creates a scanner to take in User input
  Scanner scan = new Scanner(System.in);

  //Creates ArrayLists to store the txt files data
  private ArrayList<String> english;
  private ArrayList<String> latin;

  //Creates a String and ArrayList to store the users input
  private String userInput;
  private ArrayList<String> inputPerWord;

  //Creates two booleans to determine whether to continue running the code or if it is the first translation respectively
  private boolean isRunning = true;
  private boolean isFirst = true;
  

  /**
  * No-Argument Constructor
  * Creates a Translator object
  * Precondition: Files English.txt and Latin.txt exist as ArrayLists of the same length
  * Postcondition: ArrayLists english and Latin are defined
  */
  public Translator(){
    english = FileReader.toStringList("English.txt");
    latin = FileReader.toStringList("Latin.txt");
    userInput = null;
  }

  /**
  * While isRunning is true, ask for user input, print out the translation, and then ask whether to keep running
  * Precondition: Methods prompt, seperateWords(String phrase), translate(ArrayList<String> toTranslate), and checkContinue are properly defined
  * Postcondition: inputPerWord and userInput are defined
  */
  public void runTranslator(){
    //While the translator is still running
    while(isRunning){

      //Ask for user input
      prompt();

      //Seperate the input into words and store in an ArrayList
      inputPerWord = seperateWords(userInput);

      //Print out the translation
      System.out.println("Translation: " + translate(inputPerWord));

      //Check whether to continue the next loop
      checkContinue();
    }
  }

  /**
  * Asks for a user input of a sentence or a phrase, then sets userInput to that value
  * If it is the first run of the program, print a hello message, else only print the prompt
  * Precondition: scan is a defined scanner object for the console
  * Postcondition: defines the userInput
  */
  public void prompt(){

    //If this is the first time the method is called
    if(isFirst){

      //Print out a hello and the prompt
      System.out.println("Hello There! \nPlease enter a word or sentece you would like to translate:");

      //Set the userInput to the line inputed to the user
      userInput = scan.nextLine();

      //Set isFirst to false
      isFirst = false;
    }

    //Else (ie. the user has already translated a word or sentence)
    else{
      
      //Print out only the prompt message
      System.out.println("Please enter another word or sentece you would like to translate:");
      
      //Set the userInput to the line inputed to the user
      userInput = scan.nextLine();
    }
    
  }

  /**
  * Takes in a String and returns a ArrayList where each word is seperated into a different element
  * @param phrase, the String to seperate by word
  * @return ArrayList<String>, the seperated words
  * Precondition: None
  * Postcondition: None
  */
  public ArrayList<String> seperateWords(String phrase){
    ArrayList<String> result = new ArrayList<String>();
    while(phrase.indexOf(" ") != -1){
      result.add(phrase.substring(0, phrase.indexOf(" ")));
      phrase = phrase.substring(phrase.indexOf(" ") + 1);
    }
    result.add(phrase);
    return result;
  }


  /**
  * Translates each word of the parameter into latin, or adds a placeholder
  * @param toTranslate, the ArrayList to convert into latin
  * @return a String composed of the translated phrase
  * Precondition: english and latin are defined ArrayLists of the same length
  * Postcondition: None
  */
  public String translate(ArrayList<String> toTranslate){

    //Defines the return variable
    String result = "";

    //For every element in the parameter
    for(int wordNum = 0; wordNum<toTranslate.size(); wordNum++){

      //Set the sucessful status to false
      boolean wasSucessful = false;

      //Set the current index word to a variable
      String currentWord = toTranslate.get(wordNum).toLowerCase();

      //For every element in the english ArrayList
      for(int index = 0; index<english.size(); index++){

        //If the current word of the parameter is equal to the current word of the english array
        if(currentWord.equals(english.get(index))){

          //Set sucessful status to true
          wasSucessful = true;

          //Add the latin version of the word to the return
          result += latin.get(index) + " ";
        }
      }

      //If the parameter did not have a sucessful translation, instead add [Word not found] to the return
      if(!wasSucessful){
        result += "[Word not found] ";
      }
    }
    //Return the return variable
    return result;
  }

  /**
  * Checks whether the user wishes to continue the program
  * Precondition: scan is a defined scanner object for the console
  * Postcondition: isRunning is updated
  */
  public void checkContinue(){

    //Asks the user whether they would like to translate another word or sentence
    System.out.println("Would you like to translate another word or sentence?");

    //Sets the input to a String
    String continueInput = scan.nextLine();

    //If that string does not equal a variation of yes
    if(!(continueInput.toLowerCase().equals("yes") || continueInput.toLowerCase().equals("y") || continueInput.toLowerCase().equals("sic") || continueInput.toLowerCase().equals("yes please!") || continueInput.toLowerCase().equals("yes please"))){
      //Set isRunning to false to end the while loop
      isRunning = false;

      //Print out a thank you statement
      System.out.println("Thank you for using this translator!");
    }
  }

  /**
  * Prints out a statement summarizing how the class works
  * @return String summariing how the class works
  * Precondition: None
  * Postcondition: None
  */
  public String HowProgramWorks(){
    return "\nThis program works by using NLP, or Natural Language Processing! \nIt begins by breaking down the users input into individual words \nThen it compares the word made lowercase to all of the words in the English.txt file \nIf it finds a match, then it adds the Latin word meaning the English one to the result\nIf the program does not find a response, then it simply instead adds [Word not found]";
  }


  
}