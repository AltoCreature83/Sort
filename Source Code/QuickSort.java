// Course Code: CIS211-651 Data Structures
// Submission Type: 7 Sorting Project
// Due Date: December 10th, 2020
// Author: Brandon Polite
// Purpose: Contains methods pertaining to QuickSort

package com.mycompany.sort;

public class QuickSort {
    /**Method to quicksort an array
     @param <T> - Generic placeholder object
     @param arr - an array to be sorted
     @param first - index of array to begin sorting at
     @param last - index of array to end sorting at*/
    public static <T extends Comparable<? super T>>
     void quickSort(T arr[], int first, int last) { 
        if (first < last) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, first, last); 
  
            // Recursively sort elements before 
            // partition and after partition 
            quickSort(arr, first, pi-1); 
            quickSort(arr, pi+1, last); 
        } 
    }
    
    /**
       This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivo
     @param <T> - Generic placeholder object
     @param arr - an array to be sorted
     @param first - index of array to begin sorting at
     @param last - index of array to end sorting at*/
    public static <T extends Comparable<? super T>> 
     int partition(T arr[], int first, int last) { 
        T pivot = arr[last];  
        int i = (first-1); // index of smaller element 
        for (int j=first; j<last; j++) 
        { 
            // If current element is smaller than the pivot 
            if( arr[j].compareTo(pivot) < 0 ) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                T temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[last] (or pivot) 
        T temp = arr[i+1]; 
        arr[i+1] = arr[last]; 
        arr[last] = temp; 
  
        return i+1; 
    }  
}
