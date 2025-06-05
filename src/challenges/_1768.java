package challenges;

import interfaces.ChallengesInterface;

import java.lang.reflect.Array;
import java.util.Scanner;

public class _1768 implements ChallengesInterface {

    // Problem 7ms
    String mergeAlternately(String word1, String word2){
        int size = Math.max(word1.length(), word2.length());
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < size; i++){
            Character c1 = (i < word1.length()) ? word1.charAt(i) : null;
            Character c2 = (i < word2.length()) ? word2.charAt(i) : null;
            result.append(c1 != null ? c1.toString() : "").append(c2 != null ? c2.toString() : "");
        }
        return result.toString();
    }

    // Optimized Version <1ms but memory exceeded
    String mergeAlternatelyOptimized(String word1, String word2){
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < word1.length() && i < word2.length()){
            result.append(word1.charAt(i));
            result.append(word2.charAt(i));
            i++;
        }

        while (i < word1.length()){
            result.append(word1.charAt(i));
            i++;
        }

        while (i < word2.length()){
            result.append(word2.charAt(i));
            i++;
        }

        return result.toString();
    }

    // Problem optimized <1ms
    String mergeAlternatelyOptimized2(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int size = Math.max(word1.length(), word2.length());
        for (int i = 0; i < size; i++) {
            if (i < word1.length()) {
                result.append(word1.charAt(i));
            }
            if (i < word2.length()) {
                result.append(word2.charAt(i));
            }
        }
        return result.toString();
    }


    @Override
    public void solve(Scanner scanner) {
        String result = this.mergeAlternatelyOptimized2(scanner.nextLine(), scanner.nextLine());
        System.out.println(result);
    }

    @Override
    public int getChallengeNumber() {
        return 1768;
    }

    @Override
    public String getChallengeName() {
        return "Merge Strings Alternately";
    }
}