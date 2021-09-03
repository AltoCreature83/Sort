// Course Code: CIS211-651 Data Structures
// Submission Type: 7 Sorting Project
// Due Date: December 10th, 2020
// Authors: Brandon Polite
// Purpose: Methods pertaining to Insertion Sort (and technically shell sort too)

package com.mycompany.sort;

public class InsertionSort {
    /**Method to do an insertion sort on an array
     @param <T> - Generic placeholder object
     @param a - an array to be sorted
     @param first - the index of where to begin sorting
     @param last - index of where to end sorting*/
    public static <T extends Comparable<? super T>>
         void insertionSort(T[] a, int first, int last){
             T nextToInsert;    //object to store item to insert into array
             
             //For loop to iterate through unsorted portions of array
             for(int unsorted = first + 1; unsorted <= last; unsorted++){
                 nextToInsert = a[unsorted]; //copy unsorted object
                 //insert unsorted object into sorted position
                 insertInOrder(nextToInsert, a, first, unsorted - 1);
                }
            }    
    
         
    /**Method to insert an object in a correct position within an array
     @param <T> - Generic placeholder object
     @param anEntry - the object to insert
     @param a - an array being sorted
     @param begin - where to start sorting
     @param end - where to end sorting*/
    public static <T extends Comparable<? super T>>
        void insertInOrder(final T anEntry, final T[] a, final int begin, final int end){
            int index = end; //set int index to end to act as reverse counter
            
            //while index is greater than or equal to the start of the area being sorted
            //and an entry is greater than item at index...
            while((index >= begin) && (anEntry.compareTo(a[index]) < 0)){
                a[index + 1] = a[index];    //make room to insert
                index--;    //decrement index to continue while loop
            }
            a[index + 1] = anEntry; //insert item into sorted position
        }       
}
