package challenges;

import interfaces.ChallengesInterface;

import java.util.Arrays;
import java.util.Scanner;

public class _605 implements ChallengesInterface {

    // flowerbed = [1,0,0,0,1] n = 2
    // step = 2, verify f[0]
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) return  true;

        for(int i = 0; i < flowerbed.length; i++){
            if(flowerbed[i] == 0){
                boolean prevEmpty = (i == 0 || flowerbed[i-1] == 0);
                boolean nextEmpty = (i == flowerbed.length - 1 || flowerbed[i+1] == 0);

                if(prevEmpty && nextEmpty){
                    flowerbed[i] = 1;
                    n--;
                    if(n == 0) return true;
                }
            }
        }

        return n <= 0;
    }

    @Override
    public void solve(Scanner scanner) {
        int[] flowerbed = Arrays.stream(scanner.nextLine().replaceAll("[\\[\\]]", "").split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean result = this.canPlaceFlowers(flowerbed, scanner.nextInt());
        System.out.println(result);

    }

    @Override
    public int getChallengeNumber() {
        return 605;
    }

    @Override
    public String getChallengeName() {
        return "Can Place Flowers";
    }
}