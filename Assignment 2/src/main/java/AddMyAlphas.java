import java.util.ArrayList;
public class AddMyAlphas {
    public int add(String numbers){
        int runningTotal = 0;
        if (numbers.isEmpty()){
            return runningTotal;
        }
        //Split string into different numbers putting each valid number into an array list
        ArrayList<Integer> addingList = new ArrayList();
        for (String input : numbers.split("[^0-9||^-]+") ){
            addingList.add(Integer.parseInt(input));
        }
        for (Integer inte : addingList){
            if (inte < 0){
                throw new IllegalArgumentException();
            }
            if (inte <= 1000) {
                runningTotal = runningTotal + inte;
            }
        }
        System.out.println(runningTotal);
        return runningTotal;
    }
}