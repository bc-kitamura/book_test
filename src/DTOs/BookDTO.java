package DTOs;

public class BookDTO {
    private String isbn;
    private String title;
    private String subtitle;
    private String description;

    public BookDTO(String isbn, String title, String subtitle, String description) {
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
