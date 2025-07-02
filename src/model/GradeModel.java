package model;

import java.sql.*;
import java.util.*;

public class GradeModel {
    private final Map<String, Double> gradePoints = new HashMap<>();
    private final String url = "jdbc:sqlite:grade.db";

    public GradeModel() {
        gradePoints.put("O", 10.0);
        gradePoints.put("A+", 9.0);
        gradePoints.put("A", 8.0);
        gradePoints.put("B+", 7.0);
        gradePoints.put("B", 6.0);
        gradePoints.put("C", 5.0);
        gradePoints.put("F", 0.0);

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS cgpa_records (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "semester TEXT, " +
                    "gpa REAL, " +
                    "credits INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getGradePoint(String grade) {
        return gradePoints.getOrDefault(grade.toUpperCase(), -1.0);
    }

    public double calculateGPA(String[] grades, int[] credits) {
        double totalPoints = 0, totalCredits = 0;

        for (int i = 0; i < grades.length; i++) {
            double gp = getGradePoint(grades[i]);
            if (gp == -1) continue;
            totalPoints += gp * credits[i];
            totalCredits += credits[i];
        }
        return (totalCredits == 0) ? 0.0 : totalPoints / totalCredits;
    }

    public double calculateCGPA(List<Double> gpas, List<Integer> credits) {
        double totalPoints = 0, totalCredits = 0;
        for (int i = 0; i < gpas.size(); i++) {
            totalPoints += gpas.get(i) * credits.get(i);
            totalCredits += credits.get(i);
        }
        return (totalCredits == 0) ? 0.0 : totalPoints / totalCredits;
    }

    public void insertSemesterRecord(String semester, double gpa, int credits) {
        String sql = "INSERT INTO cgpa_records (semester, gpa, credits) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, semester);
            pstmt.setDouble(2, gpa);
            pstmt.setInt(3, credits);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllRecords() {
        List<String> records = new ArrayList<>();
        String sql = "SELECT semester, gpa, credits FROM cgpa_records";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add("Semester: " + rs.getString("semester") +
                        ", GPA: " + rs.getDouble("gpa") +
                        ", Credits: " + rs.getInt("credits"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public void deleteRecordBySemester(String semester) {
        String sql = "DELETE FROM cgpa_records WHERE semester = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, semester);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
