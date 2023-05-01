import java.util.Scanner;
/**
 * The Input class is responsible for handling user inputs
 */
public class Input
{
    private Scanner reader;
    // Initalise the private fields for the Input class
    /**
     * Constructor for objects of class Input
     */
    public Input()
    {
        reader = new Scanner(System.in);
        // Give the private fields values
    }

    /**
     * Gets a single "word" from user keyboard input
     */
    public String getInput(String prompt)
    {
        String inputLine = null;
        String input = null;
        System.out.print("\n" + prompt + ": ");
        // Prints a prompt which was passed to the function to the user
        inputLine = reader.nextLine();
        Scanner tokenizer = new Scanner(inputLine);
        // Create a new keyboard input reader 
        if(tokenizer.hasNext()) {
            input = tokenizer.next();
        }
        // Gets a single "word" input, reads the input until a space is found in the input
        if(input == null){
            return "";
        }
        return input;
        // Returns the inputted "word"
    }
    
    /**
     * Gets a collection of words from user keyboard input
     */
    public String getInputLine(String prompt)
    {
        String inputLine = "";
        String input = "";
        System.out.print("\n" + prompt + ": ");
        // Prints a prompt which was passed to the function to the user
        inputLine = reader.nextLine();
        Scanner tokenizer = new Scanner(inputLine);
        // Create a new keyboard input reader
        while(tokenizer.hasNext()) {
            input += tokenizer.next();
            input += " ";
        }
        // Gets all inputted words until no extra input is found, and concatinates them together
        if(input == null){
            return "";
        }
        return input;
        // Returns the inputted collection of words
    }
    
    /**
     * Gets a single Integer user keyboard input
     */
    public Integer getIntInput(String prompt)
    {
        Integer returnInt;
        while (true){
            // This loop allows the input function to run until the function can return a valid input
            try {
                returnInt = Integer.parseInt(getInput(prompt));
                // Use the classes own input function, but try to parse it as an integer
                return returnInt;
                // If the parse was successful, return the inputted integer
            }
            catch (NumberFormatException ex){
                System.out.print("\nEnter valid int");
                // If the parse failed and threw an exception, print a prompt and do not return
            }
        } 
    }
}
