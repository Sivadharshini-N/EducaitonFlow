package view;

import java.util.*;

public class GradeView {
    private final Scanner sc = new Scanner(System.in);

    public int getSubjectCount() {
        System.out.print("Enter number of subjects: ");
        return sc.nextInt();
    }

    public String getSubjectGrade(int i) {
        System.out.print("Enter grade for Subject " + (i + 1) + " (O, A+, A, B+, etc.): ");
        return sc.next();
    }

    public int getSubjectCredit(int i) {
        System.out.print("Enter credit for Subject " + (i + 1) + ": ");
        return sc.nextInt();
    }

    public int getSemesterCount() {
        System.out.print("Enter number of semesters: ");
        return sc.nextInt();
    }

    public double getSemesterGPA(int i) {
        System.out.print("Enter GPA for Semester " + (i + 1) + ": ");
        return sc.nextDouble();
    }

    public int getSemesterCredit(int i) {
        System.out.print("Enter total credits in Semester " + (i + 1) + ": ");
        return sc.nextInt();
    }

    public void showResult(String msg) {
        System.out.println(msg);
    }

    public int showMenu() {
        System.out.println("\n=== GPA/CGPA Menu ===");
        System.out.println("1. Calculate GPA");
        System.out.println("2. Calculate CGPA");
        System.out.println("3. Exit");
        System.out.print("Enter your option: ");
        return sc.nextInt();
    }

    public String getSemesterName() {
        System.out.print("Enter semester name (e.g., Sem 1): ");
        return sc.nextLine();
    }

    public void showAllRecords(List<String> records) {
        System.out.println("\nSaved GPA Records:");
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (String r : records) System.out.println(r);
        }
    }

    public void showCGPAMenu() {
        System.out.println("\n=== GPA / CGPA Menu ===");
        System.out.println("1. Calculate GPA");
        System.out.println("2. Store GPA Record");
        System.out.println("3. View All GPA Records");
        System.out.println("4. Delete GPA Record by Semester");
        System.out.println("5. Calculate CGPA");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

}
