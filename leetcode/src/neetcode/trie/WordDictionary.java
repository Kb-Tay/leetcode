package neetcode.trie;

import java.util.ArrayList;
import java.util.List;

class WordDictionary {
    public Trie head;

    public WordDictionary() {
      this.head = new Trie();
    }
    
    public void addWord(String word) {
      head.add(word, 0);
    }
    
    public boolean search(String word) {
      return head.search(word, 0);  
    }


    class Trie {
      public Trie[] l;
      boolean hasWord; 

      public Trie() {
        this.l = new Trie[26];
      }

      public void add(String word, int i) {
        if (i == word.length()) {
          this.hasWord = true;
          return;
        }
        int pos = word.charAt(i) - 'a';
        if (l[pos] == null) {
          l[pos] = new Trie();
        }

        l[pos].add(word, i + 1);
      }

      public boolean search(String word, int i) {
        if (i == word.length()) {
          return this.hasWord;
        }

        if (this.l[i] == null) {
          return false;
        }

        
        if (word.charAt(i) == '.') {
          boolean ans = false;

          for (int x = 0; x < 26; x++) {
            if (l[x] == null) return false;
            ans = ans || l[x].search(word, x);
          }

          return ans;
        }

        int pos = word.charAt(i) - 'a';

        return l[pos] != null ? l[pos].search(word, i + 1) : false;
      }
    }
}