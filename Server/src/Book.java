public class Book {
    private String title;
    private String author;

    public Book(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void addAuthorname(String author) {
        this.author = author;
    }

}
