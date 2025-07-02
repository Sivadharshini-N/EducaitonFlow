package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TypingTextDAO
{
    public final String url = "jdbc:sqlite:typing.db";  // Database file

    // Constructor – create table if cd C:\Users\HP\IdeaProjects\EducationFlow\srcnot exists
    public TypingTextDAO() {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "CREATE TABLE IF NOT EXISTS typing_texts (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "text TEXT NOT NULL)";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all sample texts from DB
    public List<String> getAllSamples() {
        List<String> texts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT text FROM typing_texts";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                texts.add(rs.getString("text"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return texts;
    }

    // Add user sample text to DB
    public void addUserSample(String userText) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO typing_texts (text) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userText);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
