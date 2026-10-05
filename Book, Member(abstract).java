public class Book {

    private String title;
    private String name;
    private String ID;
    private String publicationDate;

    public Book(String title, String name, String ID, String publicationDate) {
        this.title = title;
        this.name = name;
        this.ID = ID;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}