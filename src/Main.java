public class Main {
    public static void main(String[] args) {
        HockeyManager manager = new HockeyManager();

        manager.printAllPlayers();

        System.out.println("\nREGISTER");
        try {
            manager.AddNewForward("Peter", 20);
            manager.AddNewDefender("Patrik", 28);
            manager.AddNewGoalie("Pavol", 22);
        } catch (PlayerAlreadyRegisteredException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }

        manager.printAllPlayers();

        System.out.println("\nREGISTER");
        try {
            manager.AddNewForward("Jaro", 18, 30);
            manager.AddNewDefender("Jakub", 20, 5);
            manager.AddNewGoalie("Jano", 30, 15);

            // Attempt to register a duplicate player
            manager.AddNewForward("Peter", 25);
        } catch (PlayerAlreadyRegisteredException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }

        manager.printAllPlayers();

        System.out.println("\nUPDATE");
        try {
            manager.updateGoals("Peter", 10);
            manager.updateHits("Patrik", 7);
            manager.updateWins("Pavol", 3);
            manager.updateGoals("Jaro", 40);
            manager.updateHits("Jakub", 10);
            manager.updateWins("Jano", 20);
        } catch (Exception e) {
            System.out.println("Error during update: " + e.getMessage());
        }
        manager.printAllPlayers();


        System.out.println("\nINCREMENT");
        try {
            manager.incrementGoals("Peter", 5);
            manager.incrementHits("Patrik", 3);
            manager.incrementWins("Pavol", 2);

            manager.incrementGoals("Jaro", 10);
            manager.incrementHits("Jakub", 2);
            manager.incrementWins("Jano", 1);
        } catch (Exception e) {
            System.out.println("Error during increment: " + e.getMessage());
        }
        manager.printAllPlayers();


        System.out.println("\nYOUNGEST PLAYER");
        manager.PrintNameAndAgeOfTheYoungestPlayer();


        System.out.println("\nREMOVE PLAYER");
        try {
            manager.removePlayer("Peter");

        } catch (Exception e) {
            System.out.println("Error during remove: " + e.getMessage());
        }
        manager.printAllPlayers();
    }
}
