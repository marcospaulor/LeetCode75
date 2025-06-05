import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File challengesDir = new File("src/challenges");

        if (!challengesDir.exists() || !challengesDir.isDirectory()) {
            System.out.println("Error: challenges directory does not found.");
            scanner.close();
            return;
        }

        File[] challengeFiles = challengesDir.listFiles((dir, name) -> name.endsWith(".java"));
        if (challengeFiles == null || challengeFiles.length == 0) {
            System.out.println("No challenges found.");
            scanner.close();
            return;
        }

        int[] challengeNumbers = Arrays.stream(challengeFiles)
                .map(file -> file.getName().replace(".java", "").replace("_", ""))
                .filter(name -> !name.isEmpty())
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        while (true) {
            System.out.println("\nChallenges available: " + Arrays.toString(challengeNumbers));
            System.out.print("Enter challenge number: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("\nExiting...");
                break;
            }

            try {
                int challengeNumber = Integer.parseInt(input);
                String className = "challenges._" + challengeNumber;

                Class<?> challengeClass = Class.forName(className);
                Object challengeInstance = challengeClass.getDeclaredConstructor().newInstance();

                // Get methods getChallengeNumber and getChallengeName
                Method getChallengeNumberMethod = challengeClass.getMethod("getChallengeNumber");
                Method getChallengeNameMethod = challengeClass.getMethod("getChallengeName");
                Method solveMethod = challengeClass.getMethod("solve", Scanner.class);

                // Call methods to get challenge number and name
                int number = (int) getChallengeNumberMethod.invoke(challengeInstance);
                String name = (String) getChallengeNameMethod.invoke(challengeInstance);

                // Show challenge information
                System.out.println("\nSolving challenge " + number + ": " + name + "...");
                solveMethod.invoke(challengeInstance, scanner);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input");
            } catch (ClassNotFoundException e) {
                System.out.println("Error: Challenge " + input + " not found");
            } catch (NoSuchMethodException e) {
                System.out.println("Error: Method not found in challenge " + input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}