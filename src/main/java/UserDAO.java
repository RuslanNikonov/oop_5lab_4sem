import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("group"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                users.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return users;
    }

    public void addUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "INSERT INTO user (name, lastname, group, phone, email) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getGroup());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (conn != null) conn.close();
        }
    }
    public void deleteUser(int lastname) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "DELETE FROM user WHERE lastname = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, lastname);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (conn != null) conn.close();
        }
    }

    public void updateUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "UPDATE user SET name = ?, lastname = ?, group = ?, phone = ?, email = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getGroup());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (conn != null) conn.close();
        }
    }


}