package com.wordCounter;

//ParagraphCounter.java
public class ParagraphCounter {

 public static int countParagraphs(String input) {
     if (input == null || input.isEmpty()) {
         return 0;
     }

     String[] paragraphs = input.split("\\n\\s*\\n");
     return paragraphs.length;
 }

 public static int countWords(String input) {
     if (input == null || input.isEmpty()) {
         return 0;
     }

     String[] words = input.split("\\s+");
     return words.length;
 }

 public static int countCharacters(String input) {
     if (input == null) {
         return 0;
     }

     return input.length();
 }
}

