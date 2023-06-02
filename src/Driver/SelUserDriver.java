package Driver;

import java.sql.Connection;
import java.util.List;
import DTOs.UserDTO;
import DAOs.UserDAO;
import DAOs.*;

import Common.ConnectionManager;

public class SelUserDriver {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            // DAOの作成
            DAOs.UserDAO userDAO = new DAOs.UserDAO(connection);
            List<UserDTO> userList = userDAO.selectAll();
            System.out.println("取得結果");
            for (UserDTO user : userList) {
                System.out.println("ユーザーID: " + user.getUserId());
                System.out.println("パスワード: " + user.getPassword());
                System.out.println("名前: " + user.getName());
                System.out.println("役割: " + user.getRole());
                System.out.println();
            }
        } catch (RuntimeException e) {
            System.err.println("実行時エラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            connectionManager.closeConnection();
        }
    }
}









































































// package Driver;

// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

// import ConnectionManager;

// public class SelUserDriver {
//     public static void main(String[] args) {
//         ConnectionManager connectionManager = new ConnectionManager();
//         Connection connection = null;
//         Statement statement = null;
//         ResultSet resultSet = null;

//         try {
//             connection = connectionManager.getConnection();
//             statement = connection.createStatement();
//             String sql = "SELECT * FROM テーブル名"; // テーブル名には実際のテーブル名を指定してください
//             resultSet = statement.executeQuery(sql);

//             System.out.println("取得結果");
//             while (resultSet.next()) {
//                 String userId = resultSet.getString("ユーザーIDのカラム名"); // ユーザーIDのカラム名には実際のカラム名を指定してください
//                 String password = resultSet.getString("パスワードのカラム名"); // パスワードのカラム名には実際のカラム名を指定してください
//                 String name = resultSet.getString("名前のカラム名"); // 名前のカラム名には実際のカラム名を指定してください
//                 String role = resultSet.getString("役割のカラム名"); // 役割のカラム名には実際のカラム名を指定してください

//                 System.out.println("ユーザーID: " + userId);
//                 System.out.println("パスワード: " + password);
//                 System.out.println("名前: " + name);
//                 System.out.println("役割: " + role);
//                 System.out.println();
//             }
//         } catch (SQLException e) {
//             System.err.println("データベースエラーが発生しました: " + e.getMessage());
//             throw e;
//         } finally {
//             try {
//                 if (resultSet != null) {
//                     resultSet.close();
//                 }
//                 if (statement != null) {
//                     statement.close();
//                 }
//                 if (connection != null) {
//                     connection.close();
//                 }
//             } catch (SQLException e) {
//                 System.err.println("クローズエラーが発生しました: " + e.getMessage());
//             }
//         }
//     }
// }
