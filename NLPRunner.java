import java.util.ArrayList;

public class NLPRunner {
  public static void main(String[] args) {

    // Instantiate a Translator object
    Translator translate = new Translator();

    // Calls runTranslator to ask for and then translate a sentence
    translate.runTranslator();

    // Outputs how the translator program works
    System.out.println(translate.HowProgramWorks());

  }
}