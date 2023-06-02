package Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import DTOs.UserDTO;
import DAOs.UserDAO;
import Common.ConnectionManager;

public class UpdUserDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("更新するユーザーIDを入力してください");
        String userId = scanner.nextLine();
        System.out.println("新しいパスワードを入力してください");
        String newPassword = scanner.nextLine();
        System.out.println("新しい名前を入力してください");
        String newName = scanner.nextLine();
        System.out.println("新しい役割を入力してください");
        String newRole = scanner.nextLine();

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            // DTOの作成
            UserDTO user = new UserDTO(userId, null, null, null);
            user.setPassword(newPassword);
            user.setName(newName);
            user.setRole(newRole);

            // DAOの作成
            UserDAO userDAO = new UserDAO(connection);
            userDAO.update(user);
            connectionManager.commit();
            System.out.println("ユーザーが正常に更新されました。");
        } catch (SQLException e) {
            connectionManager.rollback();
            System.err.println("データベースエラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            connectionManager.closeConnection();
        }
    }
}
