class Comic {

    int id;
    String title;
    String author;
    int totalCopies;
    int availableCopies;

    Comic(int id, String title, String author,
          int totalCopies, int availableCopies) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    void borrowCopy() {
            availableCopies--;
    }

    void returnCopy() {
            availableCopies++;
    }
}