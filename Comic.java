public class Comic {
    int id;
    String title;
    String author;
    int copies;
    int availableCopies;

    Comic(int id, String title,String author, int copies, int availableCopies){
        this.id = id;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
