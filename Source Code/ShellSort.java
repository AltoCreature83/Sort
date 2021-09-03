// Course Code: CIS211-651 Data Structures
// Submission Type: 7 Sorting Project
// Due Date: December 10th, 2020
// Authors: Brandon Polite
// Purpose: Contains methods pertaining to Shell Sort

package com.mycompany.sort;

public class ShellSort {

    /**Method to complete shell sort on array
     @param <T> - Generic placeholder object
     @param a - an array to be sorted
     @param first - index of array to begin sorting at
     @param last - index of array to end sorting at*/
    public static <T extends Comparable<? super T>>
         void shellSort(T[] a, int first, int last){
            int n = a.length;  //int to store number of items in array to be sorted
            int space = n/2;   //the midpoint of the entries to sort
            
            //While loop to sort array
            while (space > 0){
                //for loop to iterate through items in the array
                //start at beginning of array, continue until index of space 0 is reached
                //System.out.println(""+space);
                for(int begin = first; begin < (first + space); begin++){
                    //call sort algorithm to sort selection
                    incrementalInsertionSort(a, begin, last, space); 
                }
                space = space/2; //narrow sort selection to continue while loop
            }
    }
    
    /**Method to sort equally spaced entries of an array into ascending order
     @param <T> - Generic placeholder object
     @param a - an array to be sorted
     @param first - index of array to begin sorting at
     @param last - index of array to end sorting at
     @param space - size of gap between spaced array entries*/
    private static <T extends Comparable<? super T>>
         void incrementalInsertionSort(T[] a, int first, int last, int space){
            T nextToInsert; //object to store item to insert into array
            int index;
            //For loop to iterate through unsorted portions of array
            for(int unsorted = first+space; unsorted <= last; unsorted = unsorted + space){
                //System.out.println("unsorted = "+unsorted);
                nextToInsert = a[unsorted]; //copy unsorted object
                index = unsorted - space;
                while( ( index>=first ) && (nextToInsert.compareTo(a[index]) < 0) ){
                    a[index + space] = a[index];
                    index = index - space;
                }
                a[index + space] = nextToInsert;
            }
    }        
}
