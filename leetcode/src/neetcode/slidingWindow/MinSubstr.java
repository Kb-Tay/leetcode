package neetcode.slidingWindow;

class MinSubStr {
    public String minWindow(String s, String t) {
        // Q: when sliding window, how to know window can be minimised?

        // if s[r] contains char in t -> 

        // brute force:
        // each time we move window -> check all chars of t match freq in s
            // when valid window in s, 
            // check left pointer, see if count of letter in s at least greater? than that in t
            // if yes -> start reducing window
    

        // Optimise: not check every letter of t each time
        // when we want to reduce window -> check against letter removed to the letter in t

        Map<Character, Integer> freq_t = new HashMap<>();
        Map<Character, Integer> freq_s = new HashMap<>();
        
    
        for (int i = 0; i < t)
    }
}