package model;

import java.sql.*;

public class TimeTableModel {
    private final String url = "jdbc:sqlite:timetable.db";

    public TimeTableModel() {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS timetable (" +
                    "day TEXT NOT NULL, " +
                    "slot TEXT NOT NULL, " +
                    "subject TEXT, " +
                    "PRIMARY KEY (day, slot))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveOrUpdate(String day, String slot, String subject) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT OR REPLACE INTO timetable (day, slot, subject) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, day);
            pstmt.setString(2, slot);
            pstmt.setString(3, subject);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[][] getTimetable(String[] days, String[] slots) {
        String[][] table = new String[days.length][slots.length];
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT subject FROM timetable WHERE day = ? AND slot = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < days.length; i++) {
                for (int j = 0; j < slots.length; j++) {
                    pstmt.setString(1, days[i]);
                    pstmt.setString(2, slots[j]);
                    ResultSet rs = pstmt.executeQuery();
                    table[i][j] = rs.next() ? rs.getString("subject") : "-";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
}
