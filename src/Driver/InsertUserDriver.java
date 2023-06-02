package Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import DTOs.UserDTO;
import DAOs.*;

import Common.ConnectionManager;

public class InsertUserDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ユーザーIDを入力してください");
        String userId = scanner.nextLine();
        System.out.println("パスワードを入力してください");
        String password = scanner.nextLine();
        System.out.println("名前を入力してください");
        String name = scanner.nextLine();
        System.out.println("役割を入力してください");
        String role = scanner.nextLine();

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            //Value
            DTOs.UserDTO user = new DTOs.UserDTO(userId, password, name, role);
            user.setUserId(userId);
            user.setPassword(password);
            user.setName(name);
            user.setRole(role);


            //DAO
            DAOs.UserDAO userDAO = new DAOs.UserDAO(connection);
            userDAO.insert(user);
            connectionManager.commit();
            System.out.println("ユーザーが正常に登録されました。");
        } catch (SQLException e) {
            connectionManager.rollback();
            System.err.println("データベースエラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            connectionManager.closeConnection();
        }
    }
}
