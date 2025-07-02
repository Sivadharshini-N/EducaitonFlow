package model;

import java.sql.*;
import java.util.*;

public class ProductivityModel {
    public final String url = "jdbc:sqlite:productivity.db";

    public ProductivityModel() {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();

            // Table to store daily summary
            stmt.execute("CREATE TABLE IF NOT EXISTS productivity_log (" +
                    "date TEXT PRIMARY KEY, " +
                    "productive_hours INTEGER, " +
                    "non_productive_hours INTEGER, " +
                    "percentage REAL)");

            // Table to store detailed hourly activity logs
            stmt.execute("CREATE TABLE IF NOT EXISTS hourly_log (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "date TEXT, " +
                    "hour_range TEXT, " +
                    "activity TEXT, " +
                    "is_productive BOOLEAN)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save hour-wise activity logs
    public void saveHourlyLogs(String date, List<String> hourRanges, List<String> activities, List<Boolean> productiveFlags) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO hourly_log (date, hour_range, activity, is_productive) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < hourRanges.size(); i++) {
                pstmt.setString(1, date);
                pstmt.setString(2, hourRanges.get(i));
                pstmt.setString(3, activities.get(i));
                pstmt.setBoolean(4, productiveFlags.get(i));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save summary per day
    public void saveSummary(String date, int productive, int nonProductive, double percent) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT OR REPLACE INTO productivity_log " +
                    "(date, productive_hours, non_productive_hours, percentage) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setInt(2, productive);
            pstmt.setInt(3, nonProductive);
            pstmt.setDouble(4, percent);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get peak productive day based on highest percentage
    public String getPeakProductiveDay() {
        String sql = "SELECT date, productive_hours, non_productive_hours FROM productivity_log";
        String peakDate = "";
        double maxPercent = -1;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String date = rs.getString("date");
                int prod = rs.getInt("productive_hours");
                int nonProd = rs.getInt("non_productive_hours");

                if (prod + nonProd == 0) continue; // Avoid division by zero

                double percent = (prod * 100.0) / (prod + nonProd);

                if (percent > maxPercent) {
                    maxPercent = percent;
                    peakDate = date;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return peakDate.isEmpty()
                ? "No productivity data found."
                : "Your most productive day was: " + peakDate + " with " + String.format("%.2f", maxPercent) + "% productivity.";
    }

    // View activity and productivity status hour-wise for a specific date
    public String getHourlyReportByDate(String date) {
        StringBuilder report = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT hour_range, activity, is_productive FROM hourly_log WHERE date = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();

            boolean hasData = false;
            report.append("--- Report for ").append(date).append(" ---\n");
            while (rs.next()) {
                hasData = true;
                String range = rs.getString("hour_range");
                String activity = rs.getString("activity");
                boolean productive = rs.getBoolean("is_productive");
                report.append(range).append(" : ").append(activity)
                        .append(" ").append(productive ? "✅ Productive" : "❌ Not Productive").append("\n");
            }

            if (!hasData) {
                report.append("No activity logs found for this date.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report.toString();
    }
}
