import controller.TypingSpeedController;
import controller.ProductivityController;
import controller.ScribbleController;
import controller.TimeTableController;
import controller.GradeController;

import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        TypingSpeedController wpm=new TypingSpeedController();
        ProductivityController pc = new ProductivityController();
        ScribbleController scribbleController = new ScribbleController();
        TimeTableController timeTableController = new TimeTableController();
        GradeController gradeController = new GradeController();


        System.out.println("\nHey there!!, Great to see you again🤩🥳");

        while(true) {
            System.out.println("\n===== Main Hub =====");
            System.out.println("1. Productivity Analyser");
            System.out.println("2. View Peak Time");
            System.out.println("3. TimeTable Manager");
            System.out.println("4. Daily Scribbles");
            System.out.println("5. WPM Calculator");
            System.out.println("6. GradeSystem");;
            System.out.println("7. Exit");


            System.out.print("Please enter a valid option ^_^ ");
            int option = scanner.nextInt();
            scanner.nextLine();


            switch (option)
            {
                    case 1:
                        pc.run();
                        break;
                    case 2:
                        pc.showPeakProductiveDay();  // fetch and display most productive day
                        break;
                    case 3:
                        timeTableController.timeTableMenu();
                        break;
                    case 4:
                        System.out.println("1. Write Today's Scribble\n2. View Past Scribble");
                        int subOption = scanner.nextInt();
                        scanner.nextLine(); // Clear newline
                        if (subOption == 1) scribbleController.writeScribble();
                        else if (subOption == 2) scribbleController.viewScribble();                        break;
                    case 5:
                        System.out.println("Welcome to WPM calculator");
                        wpm.run();
                        break;
                    case 6:
                        gradeController.start();
                        break;
                    case 7:
                        System.out.println("You Exited the system");
                        return;
                    default:
                        System.out.println("Enter a valid option");
                        scanner.close();
                        System.exit(0);
            }
        }
    }
}