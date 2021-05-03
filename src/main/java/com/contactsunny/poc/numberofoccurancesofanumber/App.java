package com.contactsunny.poc.numberofoccurancesofanumber;

public class App {

    public static void main(String[] args) {
        int[] array = {2, 10, 13, 13, 13, 14, 29, 37, 100};
        printArray(array);
        System.out.println("-------------------------");

        int numberToFind = 13;
        // Getting the left index of the number to find.
        // The left index will be first occurrence of the element.
        int leftIndex = searchInArray(array, numberToFind, true);
        // If the left index is -1, it means that element was not found.
        // It makes no sense to continue with the process. So exiting here.
        if (leftIndex == -1) {
            System.out.println(numberToFind + " not found in the array!");
            System.exit(0);
        }
        // Getting the right index of the element.
        // The right index will be last occurrence of the element.
        int rightIndex = searchInArray(array, numberToFind, false);

        // Calculating the number of occurrences of the element with this formula.
        int occurrences = (rightIndex - leftIndex) + 1;
        System.out.println("Element found " + occurrences + " times!");
    }

    private static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * Method to start the recursive algorithm to perform binary search on the array to find the element
     *
     * @param array the given sorted array
     * @param numberToFind the number to find
     * @param isLeft this boolean tells if we have to move to the left or the right to continue the search
     * @return the index of the number to find.
     */
    private static int searchInArray(int[] array, int numberToFind, boolean isLeft) {
        // Initializing the first iteration of the recursive search
        return performBinarySearch(array, numberToFind, 0, array.length - 1, isLeft);
    }

    /**
     * Binary search method to find the element in the given sorted array recursively.
     *
     * @param array The given sorted array
     * @param numberToFind Tthe number to find
     * @param left The left index
     * @param right The right index
     * @param isLeft this boolean tells if we have to move to the left or the right to continue the search
     * @return The index at which the element was found.
     */
    private static int performBinarySearch(int[] array, int numberToFind, int left, int right, boolean isLeft) {
        // Checking if we have crossed the boundaries
        if (right >= left) {
            // The middle element.
            int mid = left + (right - left) / 2;
            // If we find the element at array[mid], we're done with the search
            if (array[mid] == numberToFind) {
                // Checking if we have to find the first occurrence of the element.
                if (isLeft) {

                    if (mid - 1 >= left && array[mid - 1] == numberToFind) {
                        return performBinarySearch(array, numberToFind, left, mid - 1, isLeft);
                    }
                }
                // Checking if we have to find the last occurrence of the element.
                if (!isLeft) {
                    // Checking if the mid + 1 index is still within bounds.
                    // If the element to the right of the element is the same
                    // as the one we're trying to find, we continue the search to the right.
                    if (mid + 1 <= right && array[mid + 1] == numberToFind) {
                        return performBinarySearch(array, numberToFind, mid + 1, right, isLeft);
                    }
                }
                return mid;
            }
            // If the number to find is less than the value at the mid index,
            // we have to continue the search in the left sub-array.
            // Calling the search method again with the left and right bounds for the left sub-array
            if (numberToFind < array[mid]) {
                return performBinarySearch(array, numberToFind, left, mid - 1, isLeft);
            }
            // If the number to find is greater than the value at the mid index,
            // we have to continue the search in the right sub-array.
            // Calling the search method again with the left and right bounds for the right sub-array
            return performBinarySearch(array, numberToFind, mid + 1, right, isLeft);
        }
        // We reach this statement when we have iterated throughout the array and haven't yet found the element.
        // A value of -1 will indicate that the element wasn't found.
        return -1;
    }
}
