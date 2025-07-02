package controller;

import model.GradeModel;
import view.GradeView;

import java.util.*;

public class GradeController {
    private final GradeModel model = new GradeModel();
    private final GradeView view = new GradeView();

    public void start() {
        while (true) {
            view.showCGPAMenu();
            int option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> calculateGPA();
                case 2 -> storeRecord();
                case 3 -> view.showAllRecords(model.getAllRecords());
                case 4 -> deleteRecord();
                case 5 -> calculateCGPA();
                case 6 -> {
                    view.showResult("Returning to Main Hub...");
                    return;
                }
                default -> view.showResult("Invalid option.");
            }
        }
    }

    private void calculateGPA() {
        int count = view.getSubjectCount();
        String[] grades = new String[count];
        int[] credits = new int[count];

        for (int i = 0; i < count; i++) {
            grades[i] = view.getSubjectGrade(i);
            credits[i] = view.getSubjectCredit(i);
        }

        double gpa = model.calculateGPA(grades, credits);
        view.showResult("Calculated GPA: " + String.format("%.2f", gpa));
    }

    private void storeRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter semester name: ");
        String sem = sc.nextLine();

        System.out.print("Enter GPA: ");
        double gpa = sc.nextDouble();

        System.out.print("Enter total credits: ");
        int credits = sc.nextInt();

        model.insertSemesterRecord(sem, gpa, credits);
        view.showResult("Record added successfully!");
    }

    private void deleteRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter semester to delete: ");
        String semester = sc.nextLine();
        model.deleteRecordBySemester(semester);
        view.showResult("Record deleted (if existed).");
    }

    private void calculateCGPA() {
        List<String> records = model.getAllRecords();
        if (records.isEmpty()) {
            view.showResult("No records found. Please store some GPA records first.");
            return;
        }

        List<Double> gpas = new ArrayList<>();
        List<Integer> credits = new ArrayList<>();

        for (String r : records) {
            String[] parts = r.split(", ");
            double gpa = Double.parseDouble(parts[1].split(": ")[1]);
            int credit = Integer.parseInt(parts[2].split(": ")[1]);
            gpas.add(gpa);
            credits.add(credit);
        }

        double cgpa = model.calculateCGPA(gpas, credits);
        view.showResult("Your CGPA is: " + String.format("%.2f", cgpa));
    }
}
