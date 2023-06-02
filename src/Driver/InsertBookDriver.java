package Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import DAOs.BookTableDAO;
import Common.ConnectionManager;
import DTOs.BookDTO;

public class InsertBookDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ISBNを入力してください");
        String isbn = scanner.nextLine();
        System.out.println("タイトルを入力してください");
        String title = scanner.nextLine();
        System.out.println("サブタイトルを入力してください");
        String subtitle = scanner.nextLine();
        System.out.println("説明を入力してください");
        String description = scanner.nextLine();

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            // Value
            BookDTO book = new BookDTO(isbn, title, subtitle, description);
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setSubtitle(subtitle);
            book.setDescription(description);

            // DAO
            BookTableDAO booktableDAO = new BookTableDAO(connection);
            booktableDAO.insert(book);
            connectionManager.commit();
            System.out.println("本が正常に登録されました。");
        } catch (SQLException e) {
            connectionManager.rollback();
            System.err.println("データベースエラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            connectionManager.closeConnection();
        }
    }
}

