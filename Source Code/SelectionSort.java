// Course Code: CIS211-651 Data Structures
// Submission Type: 7 Sorting Project
// Due Date: December 10th, 2020
// Authors: Brandon Polite
// Purpose: Selection sort related methods

package com.mycompany.sort;

public class SelectionSort {
    /**Method to do selection sort on array
     @param <T> - Generic placeholder object
     @param a - an array*/
    public static <T extends Comparable<? super T>>
        void selectionSort(T[] a){
            //For loop to iterate through the array
            for(int index = 0; index < a.length - 1; index++){
                //get index of smallest item
                int indexOfSmallest = getIndexOfSmallest(a, index, a.length - 1);
                //swap smallest item with current item
                swap(a, index, indexOfSmallest);
            }
    }
    
    /**Method to do selection sort on array
    @param <T> - Generic placeholder object
    @param a - an array
    @param first - starting index
    @param last - last index
    @return index of smallest value as int*/
    public static <T extends Comparable<? super T>>
        int getIndexOfSmallest(T[] a, int first, int last){
            T min = a[first];   //object to store smallest item
            int indexOfMin = first; //int to store index of smallest item
            
            //for loop to iterate throgh array, start at index after first
            for(int index = first + 1; index <= last; index++){
                //if the item at index is smaller than current min
                //set min to item at index and change index of min to current index
                if(a[index].compareTo(min) < 0){
                    min = a[index];
                    indexOfMin = index;
                }
            }
            return indexOfMin;
    }
    
    /**Method to swap two elements in array
     @param a - an array
     @param i - an index location within the array
     @param j - another index location within the array*/
    private static void swap(Object[] a, int i, int j){
        //Swap the objects
        Object temp = a[i];
        a[i] = a [j];
        a[j] = temp;
    }    
}
