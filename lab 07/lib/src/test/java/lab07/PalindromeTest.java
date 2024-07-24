package lab07;

import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

class PalindromeTest {

    /*
     * Try testing the following cases:
     *  - any string, followed by the reverse of that string is a palindrome. CHECK
     *  - any string, followed by a single character, then the reverse of the string is a palindrome. CHECK
     *  - Any string made up of unique characters of length 2 or greater is not a palindrome.
     *  - Any palindrome set to uppercase is still a palindrome.
     */


    @Property
    void stringPlusReverseValid(@ForAll @AlphaChars String word){
        System.out.println(word);
        assertTrue(Palindrome.isPalindrome(word+reverse(word)));
    }

    @Property
    void stringPlusCharPlusReversePass(@ForAll @AlphaChars String word, @ForAll @AlphaChars char extraChar){
        assertTrue(Palindrome.isPalindrome(word +extraChar+reverse(word)));

    }

    @Property
    void failUniqueCharStringLength2(
            @ForAll @AlphaChars @StringLength(min = 2) @UniqueElements String word){
        System.out.println(word);
        assertFalse(Palindrome.isPalindrome(word));
    }
    @Property
    void upperCasePalindrome(@ForAll @AlphaChars String word){
        System.out.println(word);
        assertTrue(Palindrome.isPalindrome(
                word+reverse(word).toUpperCase()
        ));
    }




    static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    @Provide
    Arbitrary<String> stringGreaterThanTwo(){

        return Arbitraries.strings().filter(n -> n.length() > 2);
    }
}