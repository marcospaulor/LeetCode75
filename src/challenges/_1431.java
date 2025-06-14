package challenges;

import interfaces.ChallengesInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class _1431 implements ChallengesInterface {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies){
        List<Boolean> list = new ArrayList<>();
        int maxCandies = Arrays.stream(candies).max().getAsInt();
        for (int candy : candies) {
            list.add(candy + extraCandies >= maxCandies);
        }

        return list;
    }

    // This is the fastest <1ms
    public List<Boolean> kidsWithCandies2(int[] candies, int extraCandies){
        List<Boolean> list = new ArrayList<>();
        int maxCandies = candies[0];
        for (int i = 1; i < candies.length; i++){
            if(candies[i] > maxCandies){
                maxCandies = candies[i];
            }
        }

        for (int candy : candies) {
            list.add(candy + extraCandies >= maxCandies);
        }
        return list;
    }

    @Override
    public void solve(Scanner scanner) {
        int[] candies = Arrays.stream(scanner.nextLine().replaceAll("[\\[\\]]", "").split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Boolean> result = this.kidsWithCandies(candies,scanner.nextInt());
        System.out.println(result.toString());
    }

    @Override
    public int getChallengeNumber() {
        return 1431;
    }

    @Override
    public String getChallengeName() {
        return "Kids With the Greatest Number of Candies";
    }
}