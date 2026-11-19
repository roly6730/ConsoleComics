import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String PASSWORD = "5555";
        ArrayList<Comic> comics = new ArrayList<>();
        ArrayList<String> bookLog = new ArrayList<>();

        comics.add(new Comic(1, "Amazing Spider-Man", "Stan Lee", 5, 5));
        comics.add(new Comic(2, "Batman: The Killing Joke", "Alan Moore", 3, 3));
        comics.add(new Comic(3, "Watchmen", "Alan Moore", 4, 4));
        comics.add(new Comic(4, "Maus", "Art Spiegelman", 2, 2));
        comics.add(new Comic(5, "Saga", "Brian K. Vaughan", 3, 3));
        comics.add(new Comic(6, "The Sandman", "Neil Gaiman", 4, 4));
        comics.add(new Comic(7, "Naruto", "Masashi Kishimoto", 6, 6));
        comics.add(new Comic(8, "One Piece", "Eiichiro Oda", 6, 6));
        comics.add(new Comic(9, "Dragon Ball", "Akira Toriyama", 5, 5));
        comics.add(new Comic(10, "Persepolis", "Marjane Satrapi", 2, 2));

        String[] student = {
            "Christian Banal", "Roland Ybanez",
            "James Lawrence Lagsit", "Resty Garin",
            "Lyndon Francisco", "Eathan Chua",
            "Jancel Abayon", "Tomkylle Guingguing"
        };

        String[] id = {
            "261080081", "261080142", "261080029",
            "261080135", "261080141", "261080023",
            "261080143", "261080136"
        };
        ArrayList<String> studentLog = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        
        boolean programStatus = true;
        // STUDENT LOGIN
        while(programStatus){
            boolean loggedIn = false;
            String studentName = "";
            boolean running = true;
            while (!loggedIn) {

            System.out.print("\nEnter your student number: ");
            String studentId = input.nextLine();
            
            if(studentId.equalsIgnoreCase("admin")){
                    boolean tryAgain = true;
                    while(tryAgain){
                    System.out.print("Enter admin password: ");
                    String password = input.nextLine();
                    if(password.equals(PASSWORD)){
                        boolean adminRunning = true;
                        while(adminRunning){
                        adminPanel();
                        System.out.print("Enter your choice: ");
                        int panel = Integer.parseInt(input.nextLine());
                        switch(panel){
                            case 1 :
                                System.out.println("=== Student Panel ===");
                                if(studentLog.isEmpty()){
                                    System.out.println("No Logins yet!");
                                }else{
                                    for (String log : studentLog){
                                        System.out.println(log);
                                        System.out.println("\n--------------------");
                                    }
                                }
                                break;
                            case 2 :
                                System.out.println("=== Borrowed Books ===");
                                if (bookLog.isEmpty()) {
                                    System.out.println("No borrowed books yet!");
                                } else {
                                    for (String log : bookLog) {
                                        System.out.println(log);
                                        System.out.println("\n--------------------");
                                    }
                                }
                                break;
                            case 3 : 
                                adminRunning = false;
                                tryAgain = false;
                                break;
                            default :
                                System.out.println("Invalid number");
                                break;
                        }
                        }
                    }else{
                        System.out.println("Wrong password! Press Y to try again & Any key to Exit");
                        if(!input.nextLine().equalsIgnoreCase("Y")){
                            tryAgain = false;
                        }
                    }
                    }
                    continue;
            }

            for (int i = 0; i < id.length; i++) {

                if (studentId.equals(id[i])) {

                    studentName = student[i];
                    loggedIn = true;

                    LocalDateTime now = LocalDateTime.now();

                    DateTimeFormatter format =
                        DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");

                    System.out.println("\nWelcome, " + studentName);
                    System.out.println("Login time: " + now.format(format));
                    studentLog.add("\nName: " + student[i] + "\nLogin Time: " + now.format(format));
                    break;
                }
            }
            if (!loggedIn) {
                System.out.println("[ERROR] Invalid student number!");
            }
        }

        // MAIN MENU

        while (running) {

            showMenu();

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {

                case 1:
                    showComics(comics);
                    break;

                case 2:
                    borrowComic(comics, input, bookLog, studentName);
                    break;

                case 3:
                    returnComic(comics, input);
                    break;

                case 4:
                    showComics(comics);
                    break;

                case 5:
                    System.out.println("\nThank you, " + studentName);
                    System.out.println("You have logged out.");
                    running = false;
                    break;

                default:
                    System.out.println("[ERROR] Invalid choice!");
            }
        }
        }
    }
    static void showMenu() {

        System.out.println("\n==== Comic Book Library Management System ====");
        System.out.println("[1] View Comic Books");
        System.out.println("[2] Borrow Comic Book");
        System.out.println("[3] Return Comic Book");
        System.out.println("[4] View Available Comics");
        System.out.println("[5] Logout");
    }
    static void adminPanel(){
        System.out.println("\n==== Admin Panel ====");
        System.out.println("[1] Student Log");
        System.out.println("[2] Borrowed Books");
        System.out.println("[3] Exit");
    }
    // DISPLAY COMIC BOOKS
    static void showComics(ArrayList<Comic> comics) {

            System.out.println("\n===== CURRENT AVAILABLE COMICS =====");
            System.out.println("\n===== COMIC BOOKS =====");

            for (Comic comic : comics) {
                if (comic.availableCopies > 0) {

                    System.out.println("\nID: " + comic.id);
                    System.out.println("Title: " + comic.title);
                    System.out.println("Author: " + comic.author);
                    System.out.println("Available Copies: " + comic.availableCopies);
                    System.out.println("--------------------------------");
            }
        }
    }

    // BORROW COMIC BOOK
    static void borrowComic(ArrayList<Comic> comics, Scanner input, ArrayList<String> bookLog, String studentName) {

        System.out.print("\nEnter the comic ID you want to borrow: ");
        int comicId = Integer.parseInt(input.nextLine());

        for (Comic comic : comics) {

            if (comic.id == comicId) {

                if (comic.availableCopies > 0) {

                    comic.borrowCopy();
                    LocalDate returnDate = borrowingDeadline(comic.title);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd, yyyy");
                    bookLog.add("\nName: " + studentName + "\nComics Title: " + comic.title + "\nReturn Deadline: " + returnDate.format(format) );

                }else{
                    System.out.println("No copies left");
                }
                return;
            }
             
        }
        System.out.println("Comic book not found!");
    }

    // BORROWING DEADLINE
    static LocalDate borrowingDeadline(String comicTitle) {

        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(3);

        DateTimeFormatter format =
            DateTimeFormatter.ofPattern("MMM dd, yyyy");

        System.out.println("\n[SUCCESS] You have successfully borrowed: "
                           + comicTitle);

        System.out.println("\n===== BORROWING INFORMATION =====");
        System.out.println("Borrow Date: " + borrowDate.format(format));
        System.out.println("Return Deadline: " + returnDate.format(format));
        System.out.println("Borrowing Period: 3 Days");

        System.out.println("\n[WARNING] Please return your comic book on time!");
        return returnDate;
    }

    // RETURN COMIC BOOK
    static void returnComic(ArrayList<Comic> comics, Scanner input) {

        System.out.println("\n===== RETURN COMIC BOOK =====");

        System.out.print("\nEnter the comic ID you want to return: ");
        int comicId = Integer.parseInt(input.nextLine());

        for (Comic comic : comics) {

            if (comic.id == comicId) {

                if (comic.availableCopies < comic.totalCopies) {

                    comic.returnCopy();

                    System.out.println("\n[SUCCESS] Comic book returned: "
                                       + comic.title);
                } else{
                    System.out.println("You didn't borrow this comic");
                }
                return;
            }
        }
        System.out.println("Invalid comic");
    }
}