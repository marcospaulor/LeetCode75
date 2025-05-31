import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File challengesDir = new File("src/challenges");

        if(!challengesDir.exists() || !challengesDir.isDirectory()) {
            System.out.println("Error: challenges directory does not found.");
            scanner.close();
            return;
        }

        File[] challengeFiles = challengesDir.listFiles((dir, name) -> name.endsWith(".java"));
        if(challengeFiles == null || challengeFiles.length == 0) {
            System.out.println("No challenges found.");
            scanner.close();
            return;
        }

        int[] challengeNumbers = Arrays.stream(challengeFiles)
                .map(file -> file.getName().replace(".java", "").replace("_",""))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        while(true){
            System.out.println("\nChallenges availables: " + Arrays.toString(challengeNumbers));
            System.out.print("Enter challenge number: ");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("exit")){
                System.out.println("\nExiting...");
                break;
            }

            int challengeNumber = 0;
            try{
                challengeNumber = Integer.parseInt(input);
                String className = "challenges._" + challengeNumber;

                Class<?> challengeClass = Class.forName(className);
                Object challengeInstance = challengeClass.getDeclaredConstructor().newInstance();

                Method solveMethod = challengeClass.getMethod("solve", Scanner.class);

                System.out.println("\nSolving challenge " + challengeNumber + "...");
                solveMethod.invoke(challengeInstance, scanner);
            } catch (NumberFormatException e){
                System.out.println("Error: invalid input");
            } catch (ClassNotFoundException e){
                System.out.println("Error: Challenge " + challengeNumber + " not found");
            } catch (NoSuchMethodException e){
                System.out.println("Error: Method 'solve' not found in the challenge " + challengeNumber);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}