package Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import DAOs.UserDAO;
import Common.ConnectionManager;

public class DelUserDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("削除するユーザーIDを入力してください");
        String userId = scanner.nextLine();

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            // DAOの作成
            UserDAO userDAO = new UserDAO(connection);
            userDAO.delete(userId);
            connectionManager.commit();
            System.out.println("ユーザーが正常に削除されました。");
        } catch (SQLException e) {
            connectionManager.rollback();
            System.err.println("データベースエラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            connectionManager.closeConnection();
        }
    }
}
