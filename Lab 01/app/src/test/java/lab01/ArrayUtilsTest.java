package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {
    @Test
    void sayHi() {
        System.out.println("Hello from the test.");
    }

    // A sorted array
    @Test
    void sortedAAA() {
        //Triple A
        int[] someArray = {1,2,3,4};       // arrange - setup / populate universe of tests
        boolean someArraySorted = ArrayUtils.isSorted(someArray);  // act
        assertTrue(someArraySorted);       // assert
    }

    // A sorted array - all at once
    @Test
    void sorted() {
        assertTrue(ArrayUtils.isSorted(new int[] {1,2,3,4}));
    }

    // Empty arrays are sorted by definition
    @Test
    void emptySorted(){
        int [] anArray = {};                                //Arrange
        boolean isSorted = ArrayUtils.isSorted(anArray);    //Act
        assertTrue(isSorted);                               //Assert
    }
    // Arrays of one element are sorted by definition
    @Test
    void singleSorted(){
        int [] anArray = {(int)(Math.random() * 1000)};     //Arrange
        boolean isSorted = ArrayUtils.isSorted(anArray);    //Act
        assertTrue(isSorted);                               //Assert

    }

    // A partially sorted array (some elements are in sorted order, but some aren't)
    @Test
    void partialSorted(){
        int [] anArray = {1, 2, -5, 10, 9, 12};     //Arrange
        boolean isSorted = ArrayUtils.isSorted(anArray);    //Act
        assertFalse(isSorted);                               //Assert

    }
    // A completely unsorted array (no elements are in sorted order)
    @Test
    void notSorted(){
        int [] anArray = {9, 4};     //Arrange
        boolean isSorted = ArrayUtils.isSorted(anArray);    //Act
        assertFalse(isSorted);                               //Assert

    }
    // An array with duplicate values (may be sorted or not depending on the values chosen)
    @Test
    void dupSorted(){
        int [] anArray = {1, 9, 9 ,4};     //Arrange
        boolean isSorted = ArrayUtils.isSorted(anArray);    //Act
        assertTrue(isSorted);                               //Assert

    }
}



