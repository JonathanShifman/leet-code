package algorithms;

/*
4. Median of Two Sorted Arrays
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/

import java.util.Optional;

public class Problem4 {

    private int halfOfTotalLength;

    private int[] shortArray;
    private int[] longArray;

    private Optional<Integer> shortArrayUnderBoundElement;
    private Optional<Integer> shortArrayOverBoundElement;
    private Optional<Integer> longArrayUnderBoundElement;
    private Optional<Integer> longArrayOverBoundElement;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        shortArray = nums1.length < nums2.length ? nums1 : nums2;
        longArray = shortArray == nums1 ? nums2 : nums1;
        if(shortArray.length == 0) {
            return median(longArray);
        }
        this.halfOfTotalLength = (shortArray.length + longArray.length) / 2;

        int partition = calculatePartition(0, shortArray.length);
        return calculateMediamBasedOnPartition(partition);
    }

    private double median(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        return nums.length % 2 == 0 ?
                average(nums[nums.length / 2], nums[nums.length / 2 - 1]) :
                nums[nums.length / 2];
    }

    private double calculateMediamBasedOnPartition(int partition) {
        updateElementsBasedOnBound(partition);
        if((shortArray.length + longArray.length) % 2 == 0) {
            int maxUnderBoundElement;
            int minOverBoundElement;
            if(shortArrayUnderBoundElement.isPresent()) {
                maxUnderBoundElement = longArrayUnderBoundElement.isPresent() ?
                        Math.max(shortArrayUnderBoundElement.get(), longArrayUnderBoundElement.get()) :
                        shortArrayUnderBoundElement.get();
            }
            else {
                maxUnderBoundElement = longArrayUnderBoundElement.get();
            }
            if(shortArrayOverBoundElement.isPresent()) {
                minOverBoundElement = longArrayOverBoundElement.isPresent() ?
                        Math.min(shortArrayOverBoundElement.get(), longArrayOverBoundElement.get()) :
                        shortArrayOverBoundElement.get();
            }
            else {
                minOverBoundElement = longArrayOverBoundElement.get();
            }
            return average(maxUnderBoundElement, minOverBoundElement);
        }
        else {
            return shortArrayOverBoundElement.isPresent() ?
                Math.min(shortArrayOverBoundElement.get(), longArrayOverBoundElement.get()) :
                    longArrayOverBoundElement.get();
        }
    }

    private double average(int a, int b) {
        return (double)(a + b) / 2;
    }

    private int calculatePartition(int partitionLowerBound, int partitionUpperBound) {
        if(partitionUpperBound - partitionLowerBound <= 1) {
            return partitionIsValid(partitionLowerBound) ? partitionLowerBound : partitionUpperBound;
        }

        int partitionNewBound = partitionLowerBound + ((partitionUpperBound - partitionLowerBound) / 2);
        if(newBoundShouldBeLowerBound(partitionNewBound)) {
            return calculatePartition(partitionNewBound, partitionUpperBound);
        }
        return calculatePartition(partitionLowerBound, partitionNewBound);
    }

    private boolean newBoundShouldBeLowerBound(int newBound) {
        updateElementsBasedOnBound(newBound);
        return shortArrayUnderBoundElement.get() < longArrayUnderBoundElement.get() ? true : false;
    }

    private boolean partitionIsValid(int bound) {
        updateElementsBasedOnBound(bound);
        if(!shortArrayUnderBoundElement.isPresent()) {
            return longArrayUnderBoundElement.get() <= shortArrayOverBoundElement.get();
        }
        if(!shortArrayOverBoundElement.isPresent()) {
            return shortArrayUnderBoundElement.get() <= longArrayOverBoundElement.get();
        }
        return shortArrayUnderBoundElement.get() < longArrayUnderBoundElement.get() ?
                shortArrayOverBoundElement.get() >= longArrayUnderBoundElement.get() :
                longArrayOverBoundElement.get() >= shortArrayUnderBoundElement.get();
    }

    private void updateElementsBasedOnBound(int bound) {
        this.shortArrayUnderBoundElement = createOptional(shortArray,bound - 1);
        this.shortArrayOverBoundElement = createOptional(shortArray, bound);
        this.longArrayUnderBoundElement = createOptional(longArray, halfOfTotalLength - bound - 1);
        this.longArrayOverBoundElement = createOptional(longArray, halfOfTotalLength - bound);
    }

    private Optional<Integer> createOptional(int[] array, int index) {
        return Optional.ofNullable(index >= 0 && index < array.length ? array[index] : null);
    }

}
