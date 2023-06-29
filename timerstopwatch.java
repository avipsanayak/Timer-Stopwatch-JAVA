//Timer and Stopwatch
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class timerstopwatch 
{
    private static boolean isRunning = false;
    private static boolean isPaused = false;
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Timer and Stopwatch!");
        System.out.println("1. Timer\n2. Stopwatch\nEnter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        switch (choice) 
        {
            case 1:
                runTimer();
                break;
            case 2:
                runStopwatch();
                break;
            default:
                System.out.println("Invalid choice. Exiting the program.");
        }
    }

    private static void runTimer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the duration (in seconds): ");
        int duration = scanner.nextInt();
        System.out.println("Timer started. Waiting for " + duration + " seconds...");
        isRunning = true;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (duration * 1000);
        while (System.currentTimeMillis() < endTime) 
        {
            if (!isRunning) 
            {
                System.out.println("Timer paused. Press 'r' to resume, 'c' to cancel or 's' to check remaining time...");
                String input = scanner.next();
                if (input.equalsIgnoreCase("r")) 
                {
                    System.out.println("Timer resumed. Waiting for remaining time...");
                    isRunning = true;
                    endTime = System.currentTimeMillis() + (endTime - startTime);
                }
                else if (input.equalsIgnoreCase("c")) 
                {
                    System.out.println("Timer cancelled.");
                    return;
                } 
                else if (input.equalsIgnoreCase("s")) 
                {
                    long remainingTime = TimeUnit.MILLISECONDS.toSeconds(endTime - System.currentTimeMillis());
                    System.out.println("Remaining time: " + remainingTime + " seconds");
                }
            }
        }
        System.out.println("Timer completed!");
    }

    private static void runStopwatch() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Stopwatch started. Press 's' to stop, 'p' to pause, 'r' to resume, 'l' to lap, or 'q' to quit...");
        isRunning = true;
        long startTime = System.currentTimeMillis();
        long lapStartTime = startTime;
        while (isRunning) {
            if (scanner.hasNextLine()) 
            {
                String input = scanner.nextLine();
                switch (input.toLowerCase()) {
                    case "s":
                        isRunning = false;
                        long endTime = System.currentTimeMillis();
                        long elapsedTime = endTime - startTime;
                        long hours = TimeUnit.MILLISECONDS.toHours(elapsedTime);
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % 60;
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60;
                        long milliseconds = elapsedTime % 1000;
                        System.out.println("Stopwatch stopped!");
                        System.out.println("Elapsed time: " + hours + " hours, " + minutes + " minutes, " +
                                seconds + " seconds, " + milliseconds + " milliseconds");
                        break;
                    case "p":
                        if (!isPaused) 
                        {
                            System.out.println("Stopwatch paused.");
                            isPaused = true;
                        } 
                        else 
                        {
                            System.out.println("Stopwatch is already paused.");
                        }
                        break;
                    case "r":
                        if (isPaused) 
                        {
                            System.out.println("Stopwatch resumed.");
                            isPaused = false;
                            startTime = System.currentTimeMillis() - (startTime - System.currentTimeMillis());
                        } 
                        else 
                        {
                            System.out.println("Stopwatch is not paused.");
                        }
                        break;
                    case "l":
                        if (!isPaused) 
                        {
                            long lapEndTime = System.currentTimeMillis();
                            long lapElapsedTime = lapEndTime - lapStartTime;
                            long lapMinutes = TimeUnit.MILLISECONDS.toMinutes(lapElapsedTime);
                            long lapSeconds = TimeUnit.MILLISECONDS.toSeconds(lapElapsedTime) % 60;
                            long lapMilliseconds = lapElapsedTime % 1000;
                            System.out.println("Lap Time: " + lapMinutes + " minutes, " +
                                    lapSeconds + " seconds, " + lapMilliseconds + " milliseconds");

                            lapStartTime = lapEndTime;
                        } 
                        else 
                        {
                            System.out.println("Stopwatch is paused. Resume to record lap time.");
                        }
                        break;
                    case "q":
                        isRunning = false;
                        System.out.println("Stopwatch terminated.");
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
        }
    }
}
