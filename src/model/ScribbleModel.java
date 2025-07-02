package model;

import java.sql.*;

public class ScribbleModel {
    private final String url = "jdbc:sqlite:scribble.db";

    public ScribbleModel() {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS scribbles (" +
                    "date TEXT PRIMARY KEY, " +
                    "entry TEXT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveEntry(String date, String entry) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT OR REPLACE INTO scribbles (date, entry) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setString(2, entry);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getEntryByDate(String date) {
        StringBuilder result = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT entry FROM scribbles WHERE date = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result.append("--- Scribble on ").append(date).append(" ---\n");
                result.append(rs.getString("entry"));
            } else {
                result.append("No entry found for ").append(date);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
