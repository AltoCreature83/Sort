// Course Code: CIS211-651 Data Structures
// Submission Type: 7 Sorting Project
// Due Date: December 10th, 2020
// Authors: Brandon Polite
// Purpose: Time measurer / sorting method tester

 package com.mycompany.sort;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class SortX {
    public static void main(String[] args) throws Exception {

        // define an output file
        String path = new File("out.csv").getAbsolutePath();
        FileWriter writer = new FileWriter(path);
        // top header - merge these cells in excel
        writer.write("QuickSort, , ,ShellSort, , ,InsertionSort, , ,SelectionSort\n");

        writer.write("n (ints), average time (ns), stddev (ns),");       
        writer.write("n (ints), average time (ns), stddev (ns),");         
        writer.write("n (ints), average time (ns), stddev (ns),");         
        writer.write("n (ints), average time (ns), stddev (ns)\n");             

        //Declare int to define sorting methods to run 10000 times.
        final int TIMES_TO_RUN = 10000;

        testSorts(10, 10);
        

        getSortTimes(5000, TIMES_TO_RUN, writer);
        //sort arrays of 10000 integers, 10000 times
        getSortTimes(10000, TIMES_TO_RUN, writer);
        //sort arrays of 25000 integers, 10000 times
        getSortTimes(25000, TIMES_TO_RUN, writer);
        //sort arrays of 50000 integers, 10000 times
        getSortTimes(50000, TIMES_TO_RUN, writer);


        writer.close();
    }

    /**Method to calculate mean times taken to sort arrays using various methods
     Method will output the mean times taken and the standard deviation of the times
     in the data set
     @param ARRAY_SIZE - the size of the array that will hold random numbers
     @param TIMES_TO_RUN - number of times to run the sorting algorithms*/
    public static void getSortTimes(final int ARRAY_SIZE, final int TIMES_TO_RUN, final FileWriter writer) throws Exception {

        final int MAXINT = Integer.MAX_VALUE;
        Integer[] quickNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        Integer[] shellNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        Integer[] insertionNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        Integer[] selectionNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        int tempInt;
        
        //Declare longs to store mean times each sorting algorithm takes to complete
        ArrayList<Long> quickTimes = new ArrayList<>();
        ArrayList<Long> shellTimes = new ArrayList<>();
        ArrayList<Long> insertionTimes = new ArrayList<>();
        ArrayList<Long> selectionTimes = new ArrayList<>();
        
        //Declare long variables to store sum of times taken
        long quickSum = 0;
        long shellSum = 0;
        long insertionSum = 0;
        long selectionSum = 0;
        
        //Declare long variables to store mean times
        long quickMean;
        long shellMean;
        long insertionMean;
        long selectionMean;
        
        //Declare double variables to store standard deviation times
        long quickSD;
        long shellSD;
        long insertionSD;
        long selectionSD;
        
        for(int runNumber = 0; runNumber < TIMES_TO_RUN; runNumber++){
            //Generate array of random numbers between 0 and MAXINT
            for(int i = 0; i < ARRAY_SIZE; i++){
                tempInt = (int) (Math.random() * MAXINT);
                quickNumbers[i] = tempInt;
                shellNumbers[i] = tempInt;
                insertionNumbers[i] = tempInt;
                selectionNumbers[i] = tempInt;     
            }
 
            //Sort array, and add time taken to times arrays
            quickTimes.add(quickSortTime(quickNumbers));
            shellTimes.add(shellSortTime(shellNumbers));
            insertionTimes.add(insertionSortTime(insertionNumbers));
            selectionTimes.add(selectionSortTime(selectionNumbers));   
        }
        
        //Calculate Sum of times
        for (int i = 0; i < TIMES_TO_RUN; i++){
            quickSum += quickTimes.get(i);
            shellSum += shellTimes.get(i);
            insertionSum += insertionTimes.get(i);
            selectionSum += selectionTimes.get(i);
        }
          
        //Calculate Mean Times
        quickMean = quickSum / TIMES_TO_RUN;
        shellMean = shellSum / TIMES_TO_RUN;
        insertionMean = insertionSum / TIMES_TO_RUN;
        selectionMean = selectionSum / TIMES_TO_RUN;
        
        //Calculate Standard Deviation
        quickSD = getStandardDeviation(quickMean, quickTimes);
        shellSD = getStandardDeviation(shellMean, shellTimes);
        insertionSD = getStandardDeviation(insertionMean, insertionTimes);
        selectionSD = getStandardDeviation(selectionMean, selectionTimes);
        
        //Output results to file
        System.out.println("-----------------------------------------------------------");
        System.out.println("QuickSort (" + ARRAY_SIZE + "): " +
                            "\nMean Time: " + quickMean +
                            "\nStandard Deviation: " + quickSD);
        writer.write( ARRAY_SIZE + "," + quickMean + "," + quickSD + "," );
        
        System.out.println("\nShell Sort (" + ARRAY_SIZE + "): " +
                            "\nMean Time: " + shellMean +
                            "\nStandard Deviation: " + shellSD);
        writer.write( ARRAY_SIZE + "," + shellMean + "," + shellSD + "," );
        
        System.out.println("\nInsertion Sort (" + ARRAY_SIZE + "): " +
                            "\nMean Time: " + insertionMean +
                            "\nStandard Deviation: " + insertionSD);
        writer.write( ARRAY_SIZE + "," + insertionMean + "," + insertionSD + "," );
        
        System.out.println("\nSelection Sort (" + ARRAY_SIZE + "): " +
                            "\nMean Time: " + selectionMean +
                            "\nStandard Deviation: " + selectionSD);
        writer.write( ARRAY_SIZE + "," + selectionMean + "," + selectionSD + "\n" );

    }     
    
    /**Method to calculate standard deviation
     @param mean - a mean value from a data set
     @param times - a data set of times*/
    public static long getStandardDeviation(long mean, ArrayList<Long> times){
        long variance = 0;    //long to store variance of data set

        //for loop to calculate variance of data set
        for(long num: times){
            variance += Math.pow(num - mean, 2);
        }
        //Calculate variance
        variance = variance / times.size();
        //Standard Deviation = sqrt of variance
        return (long) Math.sqrt(variance);
    }
    
    /**Method to do a quick sort on an array
     @param numbers - an array to be sorted
     @return the time taken to do the sort*/
    public static long quickSortTime(Integer[] numbers){
        long startTime = System.nanoTime(); //get time function is called
        QuickSort.quickSort(numbers, 0, numbers.length - 1); 
        long stopTime = System.nanoTime();  //get time function completes
        long timeTaken = stopTime - startTime;  //get total time taken 
        return timeTaken; 
    }
    
    /**Method to do a shell sort on an array
     @param numbers - an array to be sorted
     @return the time taken to do the sort*/
    public static long shellSortTime(Integer[] numbers){
        long startTime = System.nanoTime(); //get time function is called
        ShellSort.shellSort(numbers, 0, numbers.length - 1); 
        long stopTime = System.nanoTime();  //get time function completes
        long timeTaken = stopTime - startTime;  //get total time taken 
        return timeTaken;
    }
    
    /**Method to do an insertion sort on an array
     @param numbers - an array to be sorted
     @return the time taken to do the sort*/
    public static long insertionSortTime(Integer[] numbers){
      long startTime = System.nanoTime(); //get time function is called
      InsertionSort.insertionSort(numbers, 0, numbers.length - 1); 
      long stopTime = System.nanoTime();  //get time function completes
      long timeTaken = stopTime - startTime;  //get total time taken 
      return timeTaken;
    }
    
    /**Method to do a selection sort on an array
     @param numbers - an array to be sorted
     @return the time taken to do the sort*/
    public static long selectionSortTime(Integer[] numbers){
      long startTime = System.nanoTime(); //get time function is called
      SelectionSort.selectionSort(numbers); 
      long stopTime = System.nanoTime();  //get time function completes
      long timeTaken = stopTime - startTime;  //get total time taken 
      return timeTaken;
    }

    /**Prints results of all sorts to verify they all work as expected
     @param ARRAY_SIZE - how big to make the test arrays
     @param MAX_INT - max number size in test run*/
    public static void testSorts(final int ARRAY_SIZE, final int MAX_INT ){
        Integer[] quickNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        Integer[] shellNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        Integer[] insertionNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        Integer[] selectionNumbers = new Integer[ARRAY_SIZE]; //Array to store random integers
        int tempInt;

        for(int i = 0; i < ARRAY_SIZE; i ++){
            tempInt = (int) (Math.random() * MAX_INT);
            quickNumbers[i] = tempInt;
            shellNumbers[i] = tempInt;
            insertionNumbers[i] = tempInt;
            selectionNumbers[i] = tempInt;
        }
        
        System.out.println("Before quicksorting: "); printArray(quickNumbers);
        QuickSort.quickSort(quickNumbers, 0, quickNumbers.length - 1);
        System.out.println("After quicksorting: "); printArray(quickNumbers);

        System.out.println("\nBefore shellsorting: "); printArray(shellNumbers);
        ShellSort.shellSort(shellNumbers, 0, shellNumbers.length - 1);
        System.out.println("After shellsorting: "); printArray(shellNumbers);

        System.out.println("\nBefore insertion sorting: "); printArray(insertionNumbers);
        InsertionSort.insertionSort(insertionNumbers, 0, insertionNumbers.length - 1);
        System.out.println("After insertion sorting: "); printArray(insertionNumbers);


        System.out.println("\nBefore selection sorting: "); printArray(selectionNumbers);
        SelectionSort.selectionSort(selectionNumbers);
        System.out.println("After selection sorting: "); printArray(selectionNumbers);
        
    }


    /**
     * Mostly stolen and adapted from Caleb's recursion project.
     * Prints out the integer array.
     * @param <T> - generic anything that implements a GOOD toString()
     * @param array - array to print
    */
    public static void printArray( Object[] array ){
        int i = 0;
        for (; i < array.length-1; i++) {
            System.out.print( array[i] + ", " );
        }
        System.out.println( array[i] ); // avoid trailing comma
    }
}
