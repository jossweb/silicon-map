package type;

import java.util.Random;

/**
 * Generates a random string of a given length
 * 
 * 
 * @author FIGUEIRAS Jossua
 */
public class RandomString {
    private String randomString;

    /**
     * Constructs a random string of specified length
     *
     * @param length the length of the random string
     */
    public RandomString(int length){
        char[] charTable = {
        'a','b','c','d','e','f','g','h','i','j','k','l','m',
        'n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
        '0','1','2','3','4','5','6','7','8','9','?',',','.',
        ';', '/', ':', '\'', '=', '+', '≠', '-', '$', 'ê', 'é',
        '&', '"', ' ', '(', '§', '!', ')', '<', '>', '*', '@'
        };
        this.randomString = "";
        Random random = new Random();
        for(int i = 0; i<length; i++){
            this.randomString += charTable[random.nextInt(charTable.length)];
        }
    }
    /**
     * Returns the generated random string
     *
     * @return the generated random string
     */
    public String getString(){
        return this.randomString;
    }
}
