package Driver;

import java.sql.Connection;
import java.util.Scanner;

import Common.ConnectionManager;
import DAOs.BookTableDAO;

public class DelBookDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("削除する本のISBNを入力してください");
        String isbn = scanner.nextLine();

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
      
            connection = connectionManager.getConnection();
            // DAOの作成
            BookTableDAO bookTableDAO = new BookTableDAO(connection);
            bookTableDAO.deleteBook(isbn);
            connectionManager.commit();
            System.out.println("本が正常に削除されました。");
        
            connectionManager.closeConnection();
        
    }
}
