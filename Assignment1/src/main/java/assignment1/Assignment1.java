package assignment1;

import static assignment1.Assignment1.Triangle.*;

public class Assignment1 {


    public  static int getLength(String input){


        return input.length();
    }



    public static String fizzBuzz(int input){
        String output = "";
        if (input % 3 == 0){
            output = output + "Fizz";
        }
        if (input%5==0){
            output = output + "Buzz";
        }

        if ( input % 3 != 0 && input%5!=0 ){
            output = ""+input;
        }

        System.out.println(output + "!");


        return output+"!";
    }

    enum Triangle  {equilateral, isosceles, scalene, invalid}
    Triangle categorise(int x, int y, int z)
    {
        if (x > 0 && y > 0 && z > 0 &&
                x + y >= z && x + z >= y && y + z >= x) {
            if (x == y)
                if (y == z) return equilateral;
                else return isosceles;
            else if (y == z) return isosceles;
            else if (x == z) return isosceles;
            else return scalene;
        }
        else return invalid;}
}
