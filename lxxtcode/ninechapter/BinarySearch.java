package ninechapter;

import java.util.List;

public class BinarySearch {
	
	// ******************************* TEMPLATE *******************************

	/**
	 * Template: Binary Search
	 */
    public int binarySearch(int[] nums, int target) {
    	if (nums == null || nums.length == 0) { // length == 0 is not necessary
    		return -1;
    	}
    	
    	int start = 0;
    	int end = nums.length - 1;
    	
    	while (start <= end) { // use "<" if search for a range
    		int mid = (start + end) >>> 1; // be aware of length of 2
    		
	    	if (nums[mid] == target) {
	            return mid;
	        } else if (nums[mid] < target) {
	        	start = mid + 1;
	        } else {
	        	end = mid - 1;
	        }
    	}
    	
    	return -1;
	}

    // ******************************* PROBLEMS *******************************

    /**
     * Search Insert Position
     *
     * Given a sorted array and a target value, return the index if the target
	 * is found. If not, return the index where it would be if it were inserted
	 * in order. You may assume no duplicates in the array.
     */
    public int searchInsert(int[] A, int target) {
    	if (A == null) {
            return -1;
        } else if (A.length == 0) {
            return 0;
        }

        int start = 0;
        int end = A.length - 1;

        while (start < end) {
            int mid = (start + end) >>> 1;
            
            if (A[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return A[start] < target ? start + 1 : start;
    }
    
    /**
     * Search for a Range.
     *
     * Given a sorted array of integers, find the starting and ending position
	 * of a given target value.
	 *
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 *
	 * If the target is not found in the array, return [-1, -1].
	 *
	 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
     */
    public int[] searchRange(int[] A, int target) {
    	int[] range = new int[2];
    	range[0] = -1;
        range[1] = -1;
        if (A == null || A.length == 0) {
            return range;
        }

        // search start
        int start = 0, end = A.length - 1;
        while (start < end) {
            int mid = (start + end) >>> 1; // left middle

            if (A[mid] == target) {
                end = mid;
            } else if (A[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // add valid start or return
        if (A[start] == target) {
            range[0] = start;
        } else {
            return range;
        }

        // search end
        end = A.length - 1;
        while (start < end) {
            int mid = (start + end + 1) >>> 1; // right middle

            if (A[mid] == target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        // add end
        range[1] = end;

        return range;
    }

    /**
	 * Search a 2D Matrix.
	 *
	 * Write an efficient algorithm that searches for a value in an m x n
	 * matrix. This matrix has the following properties:
	 *
	 * Integers in each row are sorted from left to right. The first integer of
	 * each row is greater than the last integer of the previous row.
	 */
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m * n - 1;

        while (start <= end) {
            int mid = (start + end) >>> 1;
            int midRow = mid / n;
            int midCol = mid % n;

            if (matrix[midRow][midCol] == target) {
                return true;
            } else if (matrix[midRow][midCol] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    /**
	 * First Position of Target.
	 *
	 * For a given sorted array (ascending order) and a
	 * target number, find the first index of this number in O(log n) time
	 * complexity.
	 *
	 * If the target number does not exist in the array, return -1.
	 *
	 * @param nums:
	 *            The integer array.
	 * @param target:
	 *            Target to find.
	 * @return: The first position of target. Position starts from 0.
	 */
    public int firstPosition(int[] nums, int target) {
        //write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return (nums[start] == target) ? start : -1;
    }

    /**
     * Search in a Big Sorted Array.
     *
	 * Given a big sorted array with positive integers sorted by ascending
	 * order. The array is so big so that you can not get the length of the
	 * whole array directly, and you can only access the kth number by
	 * ArrayReader.get(k) (or ArrayReader->get(k) for C++). Find the first index
	 * of a target number.
	 *
	 * Your algorithm should be in O(log k), where k is the first index of the
	 * target number.
	 *
	 * Return -1, if the number doesn't exist in the array.
	 *
	 * Notice: If you accessed an inaccessible index (outside of the array),
	 * ArrayReader.get will return 2,147,483,647.
	 *
	 * @param reader:
	 *            An instance of ArrayReader. (Replaced by List here)
	 * @param target:
	 *            An integer
	 * @return : An integer which is the index of the target number
	 */
    public int searchBigSortedArray(List<Integer> reader, int target) {
        // write your code here
        int start;
        int end;

        // find the search end
        for (end = 1; end > 0; end = end << 1) {
            if (reader.get(end) >= target) {
                break;
            }
        }

        // get search range
        start = end >>> 1;
        if (end < 0) {
            end = Integer.MAX_VALUE;
        }

        while (start < end) {
            int mid = (start + end) >>> 1;
            int num = reader.get(mid);

            if (num == target) {
                end = mid;
            } else if (num < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return (reader.get(start) == target) ? start : -1;
    }

    /**
	 * Find Minimum in Rotated Sorted Array.
	 *
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * You may assume no duplicate exists in the array.
	 */
	public int findMin(int[] num) {
		if (num == null || num.length == 0) {
            return -1;
        }

        int start = 0, end = num.length - 1;

        while (start < end) {
            int mid = (start + end) >>> 1;

            if (num[mid] < num[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return num[start];
    }

    /**
	 * Search in Rotated Sorted Array
	 *
	 * It should be shortened by using same search without normal binary search
	 *
	 * Suppose a sorted array is rotated at some pivot unknown to you
	 * beforehand.
	 *
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 *
	 * You are given a target value to search. If found in the array return its
	 * index, otherwise return -1.
	 *
	 * You may assume no duplicate exists in the array.
	 */
    public int search(int[] A, int target) {
    	if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0, end = A.length - 1;

        while (start < end) {
            int mid = (start + end) >>> 1;

            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                if (A[mid] < A[end] && A[end] < target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (A[start] < A[mid] && A[start] > target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return (A[start] == target) ? start : -1;
    }

    /**
	 * Find Peek Element.
	 *
	 * There is an integer array which has the following features:
	 *
	 * The numbers in adjacent positions are different. A[0] < A[1] &&
	 * A[A.length - 2] > A[A.length - 1]. We define a position P is a peek if:
	 *
	 * A[P] > A[P-1] && A[P] > A[P+1] Find a peak element in this array. Return
	 * the index of the peak.
	 *
	 * Notice: The array may contains multiple peeks, find any of them.
	 */
    public int findPeak(int[] A) {
    	int start = 0, end = A.length - 1;

        while (start < end) {
            int mid = (start + end) >>> 1;

            if (A[mid] > A[mid + 1]) {
                if (A[mid] > A[mid - 1]) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    /**
     * First Bad Version.
     *
	 * The code base version is an integer start from 1 to n. One day, someone
	 * committed a bad version in the code case, so it caused this version and
	 * the following versions are all failed in the unit tests. Find the first
	 * bad version.
	 *
	 * You can call isBadVersion to help you determine which version is the
	 * first bad one. The details interface can be found in the code's
	 * annotation part.
	 *
	 * @param n An integers.
	 * @return An integer which is the first bad version.
	 */
    public int findFirstBadVersion(int n) {
        // write your code here
        if (n < 1) {
            return -1;
        }

        int start = 1, end = n;

        while (start < end) {
            int mid = (start + end) >>> 1;

            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        if (isBadVersion(start)) {
            return start;
        }
        return -1;
    }

    private boolean isBadVersion(int n) {
    	// Helper function just for compilation, not implemented.
    	throw new IllegalStateException();
    }

    /**
     * 3. 2774:ľ�ļӹ�
     * http://bailian.openjudge.cn/practice/2774
     */
//    #include <iostream>
//    using namespace std;
//
//    int cut_by_length(int wood[], int n, int length) {
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            count += wood[i] / length;
//        }
//        return count;
//    }
//
//    int main() {
//        int n, target, sum = 0;
//        
//        cin >> n >> target;
//        int wood[n];
//        for (int i = 0; i < n; i++) {
//            cin >> wood[i];
//            sum += wood[i];
//        }
//        
//        int start = 1, end = sum, mid, count;
//        while (start + 1 < end) {
//            mid = start + (end - start) / 2;
//            count = cut_by_length(wood, n, mid);
//            if (count == target) {
//                start = mid;
//            } else if (count < target) {
//                end = mid;
//            } else {
//                start = mid;
//            }
//        }
//        if (cut_by_length(wood, n, end) >= target) {
//            cout << end;
//        } else {
//            cout << start;
//        }
//        return 0;
//    }

    /**
     * 
     */
  //Solution to copy books, the following code is in c++ since wikioi 
  //only accetps c++
//  #include <stdio.h>
//  #include <string.h>
//  #include <stdlib.h>
//  #include <algorithm>
//  #include <iostream>
//  #include <cstdio>
//  using namespace std;
//
//  // Check whether a given number of pages in a slice is
//  // valid, i.e. all the books could get copied.
//  bool isValid(int M, int K, int* pages, int sliceNum) {
//      int curSliceNum = 0;
//      int curBook = M - 1;
//      for(int i = K - 1; i >= 0; i--) {
//          curSliceNum = 0;
//
//          while(curSliceNum + pages[curBook] <= sliceNum && 
//                  curBook >= 0) {
//              curSliceNum += pages[curBook];
//              curBook--;
//          }
//
//          if (curBook < 0) {
//              return true;
//          }
//      }
//
//      return false;
//  }
//
//
//  // Use binary search to find the optimal number of pages in a slice.
//  int search(int M, int K, int* pages, int minSliceNum, int maxSliceNum) {
//      int beg = minSliceNum;
//      int end = maxSliceNum;
//      int mid;
//
//      while (beg + 1 < end) {
//          mid = (beg + end) / 2;
//          if (isValid(M, K, pages, mid)) {
//              end = mid;
//          } else {
//              beg = mid;
//          }
//      }
//
//      if (isValid(M, K, pages, end)) {
//          return end;
//      }
//
//      return beg;
//  }
//
//  int main() {
//      int M, K;
//      scanf("%d %d", &M, &K);
//
//      int* pages = new int[M];
//      int* startBook = new int[K];
//      int* endBook = new int[K];
//      int maxSliceNum = 0;
//      int minSliceNum = 0;
//      int optimalSliceNum;
//      for(int i = 0; i < M; i++) {
//          scanf("%d ", &pages[i]);
//          minSliceNum = min(pages[i], minSliceNum);
//          maxSliceNum += pages[i];
//      }
//
//      optimalSliceNum = search(M, K, pages, minSliceNum, maxSliceNum);
//
//      int curSliceNum = 0;
//      int curBook = M - 1;
//      for(int i = K - 1; i >= 0; i--) {
//          curSliceNum = 0;
//          endBook[i] = curBook;
//          while (curSliceNum + pages[curBook] <= optimalSliceNum && 
//                  curBook >= i) {
//              curSliceNum += pages[curBook];
//              curBook--;
//          }
//          startBook[i] = curBook + 1;
//      }
//
//      for(int i = 0; i < K; i++) {
//          printf("%d %d\n", startBook[i] + 1, endBook[i] + 1);
//      }
//
//      delete [] endBook; 
//      delete [] startBook; 
//      delete [] pages; 
//
//      return 0;
//  }

	/**
	 * Search in Rotated Sorted Array II
	 * 
	 * Follow up for "Search in Rotated Sorted Array": What if duplicates are
	 * allowed?
	 * 
	 * Would this affect the run-time complexity? How and why?
	 * 
	 * Write a function to determine if a given target is in the array.
	 * 
	 * O(logN) ~ O(n), depends on number of duplicates.
	 * 
	 * This solutions is so concise and beautiful.
	 */
	public boolean searchRotatedWithDup(int[] A, int target) {
		int left = 0;
        int right = A.length - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (A[mid] == target) {
                return true; // or return index according to requirement
            }
            
            if (A[left] < A[mid]) { // left part is sorted
                if (A[left] <= target && A[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
                
            } else if (A[left] > A[mid]) { // right part is sorted
                if (A[mid] <= target && A[right] >= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
                
            } else {
                left++;
            }
        }
        
        return false;
	}

	public void test() {
		int[] nums = new int[3];
		nums[0] = 1;
		nums[1] = 2;
		nums[2] = 3;
		System.out.println(binarySearch(nums, 1));
	}
	
	public static void main(String[] args) {
		BinarySearch test = new BinarySearch();
		test.test();
	}

}
