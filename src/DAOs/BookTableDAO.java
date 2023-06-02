package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTOs.*;
import Driver.*;


public class BookTableDAO{
    private Connection connection;

    public BookTableDAO(Connection connection) {
        // try {
        //     // データベースに接続するための処理
        // //     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampleDB", "username", "password");
        // // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        this.connection = connection;
    }
    

    public int insertBook(DTOs.BookDTO book) {

        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO books (isbn, title, subtitle, description) VALUES (?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);

            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getSubtitle());
            statement.setString(4, book.getDescription());

            int result = statement.executeUpdate();
            System.out.println("登録結果:" + result);
            return result;

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

    public List<DTOs.BookDTO> selectAllBooks() {
        
        List<BookDTO> bookList = new ArrayList<BookDTO>();

        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM books";

            statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String subtitle = resultSet.getString("subtitle");
                String description = resultSet.getString("description");

                DTOs.BookDTO book = new DTOs.BookDTO(isbn, title, subtitle, description);
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setSubtitle(subtitle);
                book.setDescription(description);
                bookList.add(book);
            }
            System.out.println("DTOテーブルのSELECTに成功しました");
            // return bookList;

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

        // return bookList;
    }

    public void updateBook(DTOs.BookDTO book) {

        PreparedStatement statement = null;

        try  {

            String sql = "UPDATE books SET title = ?, subtitle = ?, description = ? WHERE isbn = ?";

            statement = connection.prepareStatement(sql);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getSubtitle());
            statement.setString(3, book.getDescription());
            statement.setString(4, book.getIsbn());

            statement.executeUpdate();
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
    }

    public void deleteBook(String isbn) {
        PreparedStatement statement = null;

        try  {
            String sql = "DELETE FROM books WHERE isbn = ?";

            statement = connection.prepareStatement(sql);

            statement.setString(1, isbn);

            statement.executeUpdate();
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
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(DTOs.BookDTO book) {
    }
}
