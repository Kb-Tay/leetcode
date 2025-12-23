package key_algos;

/**
 * Inteview question regarding finding median element in the array
 */
public class Quickselect {
    public double findMedianInArr(int[] arr) {
        if (arr.length % 2 == 1) {
            return quickSelect(arr, 0, arr.length - 1, arr.length / 2);
        } else {
            return 0.5 * (quickSelect(arr, 0, arr.length - 1, arr.length / 2 - 1) 
                + quickSelect(arr, 0, arr.length - 1, arr.length / 2));  
        }
    }

    // finds the kth largest element in the array 
    public int quickSelect(int[] arr, int l, int r, int k) {
        int pivot = randomPivot(l, r);

        // note: we do not have to update the pos of k since the arr size does not change!
        int pos = partition(arr, l, r, pivot);
        if (pos > k) {
            return quickSelect(arr, l, pos-1, k); 
        } else if (pos < k) {
            return quickSelect(arr, pos+1, r, k);
        } else {
            return arr[pos];
        }
    }

    private static int randomPivot(int left, int right) {
		return left + (int) Math.floor(Math.random() * (right - left + 1));
	}	

    // partitions the array and return the ind of the pivot element
    public int partition(int[] arr, int l, int r, int pivot) {
        // swap pivot to end of arr
        swap(arr, pivot, r);

        // use an index to keep track of ending of ele < pivot
        int storeInd = l;
        
        for (int i = l; i < r; i++) {
            if (arr[i] < arr[r]) {
                swap(arr, storeInd, i);
                storeInd++;
            }
        }

        swap(arr, storeInd, r);
        return storeInd;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
