package Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Common.ConnectionManager;
import DAOs.BookTableDAO;
import DTOs.BookDTO;

public class UpdBookDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("更新する本のISBNを入力してください");
        String isbn = scanner.nextLine();
        System.out.println("新しいタイトルを入力してください");
        String newTitle = scanner.nextLine();
        System.out.println("新しいサブタイトルを入力してください");
        String newSubtitle = scanner.nextLine();
        System.out.println("新しい説明を入力してください");
        String newDescription = scanner.nextLine();

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            // DTOの作成
            BookDTO book = new BookDTO(isbn, null, null, null);
            book.setTitle(newTitle);
            book.setSubtitle(newSubtitle);
            book.setDescription(newDescription);

            // DAOの作成
            BookTableDAO bookTableDAO = new BookTableDAO(connection);
            bookTableDAO.update(book);
            connectionManager.commit();
            System.out.println("本が正常に更新されました。");
        } catch (SQLException e) {
            connectionManager.rollback();
            System.err.println("データベースエラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            connectionManager.closeConnection();
        }
    }
}
