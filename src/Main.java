import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nHey there!!, Great to see you again🤩🥳");

        while(true) {
            System.out.println("\n===== Main Hub =====");
            System.out.println("1. Productivity Calculator");
            System.out.println("2. View Peak Time");
            System.out.println("3. TimeTable Manager");
            System.out.println("4. Daily Scribbles");
            System.out.println("5. WPM Calculator");
            System.out.println("6. Exit ❌");

            System.out.print("Please enter a valid option ^_^ ");
            int option = scanner.nextInt();
            scanner.nextLine();


            switch (option)
            {
                    case 1:
                        System.out.println("This is productivity calculator");
                        break;
                    case 2:
                        System.out.println("You are in view peak time");
                        break;
                    case 3:
                        System.out.println("This is TimeTable");
                        break;
                    case 4:
                        System.out.println("This is Daily scribbles");
                        break;
                    case 5:
                        System.out.println("This is WPM calculator");
                        break;
                    case 6:
                        System.out.println("You Exited the system");
                        break;
                    default:
                        System.out.println("Enter a valid option");
                        scanner.close();
                        System.exit(0);
            }
        }
    }
}