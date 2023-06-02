package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Driver.*;
import DTOs.*;

public class UserDAO<UserDTO> {
    private Connection connection;

    public UserDAO(Connection connection) {
        // try {
        //     // データベースに接続するための処理
        //     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampleDB", "username", "password");
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        this.connection = connection;
    }

    public void insertUser(DTOs.UserDTO user) {

        PreparedStatement statement = null;

        try {

            String sql = "INSERT INTO users (user_id, password, name, role) VALUES (?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUserId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getRole());

            int result = statement.executeUpdate();
            System.out.println("登録結果:" + result);
            // return result;

        } catch (SQLException e) {
            throw new RuntimeException("テーブルのINSERTに失敗しました", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("ステートメントの解放に成功しました");
                }
            } catch (SQLException e) {
                throw new RuntimeException("ステートメントの解放に失敗しました", e);
            }
        }
    }
    

    public List<DTOs.UserDTO> selectAll() {
        
        List<DTOs.UserDTO> userList = new ArrayList<DTOs.UserDTO>();

        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM users";

            statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("user_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                DTOs.UserDTO user = new DTOs.UserDTO(userId, password, name, role);
                user.setUserId(userId);
                user.setPassword(password);
                user.setName(name);
                user.setRole(role);
                userList.add(user);
            }
            System.out.println("USERDTOテーブルのSELECTに成功しました");
            return userList;


        } catch (SQLException e) {
            throw new RuntimeException("USERDTOテーブルのSELECTに失敗しました", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("ステートメントの解放に成功しました");
                }
            } catch (SQLException e) {
                throw new RuntimeException("ステートメントの解放に失敗しました", e);
            }
        }

        // return user;
    }

    public void updateUser(DTOs.UserDTO user) {
        PreparedStatement statement = null;
    
        try {
            String sql = "UPDATE users SET password = ?, name = ?, role = ? WHERE user_id = ?";

            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setString(3, user.getRole());
            statement.setString(4, user.getUserId());

            statement.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException("USERDTOテーブルのSELECTに失敗しました", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("ステートメントの解放に成功しました");
                }
            } catch (SQLException e) {
                throw new RuntimeException("ステートメントの解放に失敗しました", e);
            }
        }
    }

    // その他のメソッド（削除など）もここに追加することができます
    public void deleteUser(int userId) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM users WHERE user_id = ?";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);

            statement.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException("USERDTOテーブルのSELECTに失敗しました", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("ステートメントの解放に成功しました");
                }
            } catch (SQLException e) {
                throw new RuntimeException("ステートメントの解放に失敗しました", e);
            }
        }
    }
    


    public void close() {
        PreparedStatement statement = null;
        try {
            if (statement != null) {
                statement.close();
                System.out.println("ステートメントの解放に成功しました");
            }
        } catch (SQLException e) {
            throw new RuntimeException("ステートメントの解放に失敗しました", e);
        }
    }
}
