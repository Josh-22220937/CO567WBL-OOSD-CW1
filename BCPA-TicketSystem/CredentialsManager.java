/**
 * The CredentialsManager class is responsible for holding the login details of the VenueManager
 * And for verifying whether a set of username and password values are valid
 */
public class CredentialsManager
{
    private String userName;
    private String passWord;
    // Initalise the private fields for the CredentialsManager class

    /**
     * Constructor for objects of class Credentials
     */
    public CredentialsManager()
    {
        userName = "manager35";
        passWord = "bestManager123";
        // Give the private fields values
    }

    /**
     * Verifies whether a username and password combination is valid
     */
    public boolean verify(String un, String pw)
    {
        if((un == null) || (pw == null)){
            return false;
        }
        // special if case for catching null inputs as the equals function throws an error when passed
        // Null values
        if((un.equals(userName)) && (pw.equals(passWord))) {
            return true;
            // Return boolean true if the username and password match the stored values
        } 
        else {
            return false;
            // Return boolean false if the username and password do not match the stored values
        }
    }
}
