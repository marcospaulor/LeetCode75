package challenges;

import interfaces.ChallengesInterface;

import java.util.Scanner;


public class _1071 implements ChallengesInterface {

    String gdc0fStrings(String str1, String str2) {
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }

        int sizeA = str1.length(), sizeB = str2.length();
        while (sizeB != 0){
            int temp = sizeB;
            sizeB = sizeA % sizeB;
            sizeA = temp;
        }

        return str1.substring(0, sizeA);
    }

    @Override
    public void solve(Scanner scanner) {
        String result = this.gdc0fStrings(scanner.nextLine(), scanner.nextLine());
        System.out.println(result);
    }

    @Override
    public int getChallengeNumber() {
        return 1071;
    }

    @Override
    public String getChallengeName() {
        return "Greatest Common Divisor of Strings";
    }
}
