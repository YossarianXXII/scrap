/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siteObjects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoss
 */
public class LetterCount {
    int totalNumber;
    private List<Letter> letters;
    public int minSize = 8;
    public int maxSize = 30;
    
    public LetterCount(){
        letters = new ArrayList<>();
        totalNumber = 0;
    }
    
    public void addLetter(char c, int count){
        letters.add(new Letter(c, count));
        totalNumber+=count;
               
    }
    /** Counts the final size of character based on its frequency.
        Size is counted as linear function of occurrences
    */
    public void countSize(){
        int maxCount = 0;
        int minCount = totalNumber;
        for(Letter l: letters){
            if(l.count > maxCount) maxCount = l.count;
            if(l.count < minCount) minCount = l.count;
        }
        float m = (minSize - maxSize)/(float)(maxCount - minCount);

        for(Letter l: letters){
            l.size = (int) (m*(l.count - minCount) + maxSize);
        }
    }  
    
    public int getLSize(char c){
        for(Letter l: letters){
            if(l.c == c) return l.size;
        }
        return 0;
    }    
  

    public boolean contains(char c) {
        Letter l = new Letter(c);
        return letters.contains(l);
    }
    
    private class Letter{
        char c;
        int count;
        int size;
        public Letter(char c){
            this.c = c; 
        }
        public Letter(char c, int count){
            this.c = c; this.count = count;
        }
        
        @Override
        public boolean equals(Object obj){
            if(obj == null) return false;
            else if( !(obj instanceof Letter)) return false;
            else return ((Letter)obj).c == this.c;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 59 * hash + this.c;
            return hash;
        }
    }
}
