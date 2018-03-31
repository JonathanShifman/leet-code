package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem4Test {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @Test
    public void testBasic1() {
        int[] nums1 = new int[] {1, 2, 4, 5, 7, 10, 11, 13, 14, 15, 16, 17, 18, 20, 21, 23};
        int[] nums2 = new int[] {0, 3, 6, 8, 9, 12, 19, 22};
        Problem4 prob = new Problem4();
        assertEquals(11.5, prob.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testBasic2() {
        int[] nums1 = new int[] {1, 2, 4, 5, 7, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        int[] nums2 = new int[] {0, 3, 6, 8, 9, 10, 11, 12};
        Problem4 prob = new Problem4();
        assertEquals(11.5, prob.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testGivenExample1() {
        int[] nums1 = new int[] {1, 3};
        int[] nums2 = new int[] {2};
        Problem4 prob = new Problem4();
        assertEquals(2.0, prob.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testGivenExample2() {
        int[] nums1 = new int[] {1, 2};
        int[] nums2 = new int[] {3, 4};
        Problem4 prob = new Problem4();
        assertEquals(2.5, prob.findMedianSortedArrays(nums1, nums2));
    }
}