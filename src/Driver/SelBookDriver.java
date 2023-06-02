package Driver;

import java.sql.Connection;
import java.util.List;

import Common.ConnectionManager;
import DAOs.BookTableDAO;
import DTOs.BookDTO;

public class SelBookDriver {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            // DAOの作成
            BookTableDAO bookTableDAO = new BookTableDAO(connection);
            List<BookDTO> bookList = bookTableDAO.selectAll();
            System.out.println("取得結果");
            for (BookDTO book : bookList) {
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("タイトル: " + book.getTitle());
                System.out.println("サブタイトル: " + book.getSubtitle());
                System.out.println("説明: " + book.getDescription());
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
