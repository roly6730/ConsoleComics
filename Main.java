import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Comic> comics = new ArrayList<>();
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
        
        String[] student = {"Christian Banal", "Roland Ybanez", "James Lawrence Lagsit", "Resty Garin",  "Lyndon Francisco", "Eathan Chua", "Jancel Abayon" };
        String[] id ={"261080081", "261080142", "261080029", "261080135", "261080141", "261080023", "261080143"};

        Scanner input = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.print("Enter your student number: ");
            String studentId = input.nextLine();
            for(int i = 0; i < id.length; i++){
                if(studentId.equals(id[i])){
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");
                    String loginTime = now.format(format);
                    System.out.println("\nWelcome, " + student[i]);
                    System.out.println("Login time: " + loginTime);
                    showMenu();
                }
            }
        }
        input.close();
    }
    static void showMenu(){
        System.out.println("\n==== Comic Book Library Management System ====\n[1] ");
    }
}
