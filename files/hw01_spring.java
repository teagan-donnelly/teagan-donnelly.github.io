//written by Teagan Donnelly
import java.util.Random;
import java.util.Scanner;
import java.io.*;


public class hw01_spring {
    public static void main(String[] args) {
        String fileName = "prizeList.txt";
        try {
            //Read and parse the prize file into parallel arrays
            PrizeData prizes = readPrizeFile(fileName);
            if (prizes.count == 0) {
                System.out.println("No valid prizes found in " + fileName + ". Exiting.");
                return;
            }

            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            outerLoop:
            while (true) {
                //Choose 5 unique prizes
                int needed = 5;
                if (prizes.count < needed) {
                    System.out.println("Not enough prizes available (" + prizes.count + "). Need at least 5.");
                    break;
                }

                int[] chosenIndices = pickNUniqueIndices(prizes.count, needed, random);

                //find sum and print prize names
                double sum = 0.0;
                System.out.println("Welcome to the showcase show down!");
                System.out.println("Your prizes are:");
                for (int i = 0; i < chosenIndices.length; i++) {
                    int idx = chosenIndices[i];
                    System.out.println(prizes.names[idx]);
                    sum += prizes.prices[idx];
                }

                //Prompts users for guess
                System.out.println("You must guess the total cost of the prizes without going over and within $1,300 of its actual price");
                System.out.print("Enter your guess\n");
                double guess;
                try {
                    String guessLine = scanner.nextLine().trim();
                    guess = Double.parseDouble(guessLine);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number entered. Treating as 0.");
                    guess = 0.0;
                }

                System.out.println("The actual cost was " + sum);
                //Determine outcome
                if (guess > sum) {
                    System.out.println("Your guess was over. You lose");
                } else {
                    double diff = sum - guess; //non-negative since guess <= sum
                    if (diff <= 1300.0) {
                        System.out.println("You win!!!");
                    } else {
                        System.out.println("Your guess was close, but not close enough. You lose.");
                    }
                }

                //Ask to quit
                System.out.println("Would you like to quit? Enter \"yes\" to quit");
                String quit = scanner.nextLine().trim();
                if (quit.equalsIgnoreCase("yes")) {
                    System.out.println("Goodbye!");
                    break outerLoop;
                }
                //otherwise loop continues for another round
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("Error reading prize file: " + e.getMessage());
        }
    }

    private static PrizeData readPrizeFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IOException("File not found: " + filename);
        }

       
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int validCount = 0;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\t");
            if (parts.length != 2) continue;
            try {
                Double.parseDouble(parts[1].trim());
                validCount++;
            } catch (NumberFormatException nfe) {
                // skip malformed price
            }
        }
        br.close();

        //allocate arrays of exactly validCount size
        String[] names = new String[validCount];
        double[] prices = new double[validCount];

        
        br = new BufferedReader(new FileReader(file));
        int idx = 0;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\t");
            if (parts.length != 2) continue;
            String name = parts[0].trim();
            String priceStr = parts[1].trim();
            try {
                double price = Double.parseDouble(priceStr);
                names[idx] = name;
                prices[idx] = price;
                idx++;
            } catch (NumberFormatException nfe) {
                //ignore malformed
            }
        }
        br.close();

        return new PrizeData(names, prices, idx);
    }

   
    private static int[] pickNUniqueIndices(int max, int n, Random random) {
        int[] chosen = new int[n];
        boolean[] used = new boolean[max]; // default false
        int count = 0;
        while (count < n) {
            int r = random.nextInt(max);
            if (!used[r]) {
                used[r] = true;
                chosen[count] = r;
                count++;
            }
        }
        return chosen;
    }

    private static class PrizeData {
        String[] names;
        double[] prices;
        int count;

        PrizeData(String[] names, double[] prices, int count) {
            this.names = names;
            this.prices = prices;
            this.count = count;
        }
    }

}
