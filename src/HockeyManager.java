import java.util.ArrayList;
import java.util.List;

class PlayerAlreadyRegisteredException extends RuntimeException {
    public PlayerAlreadyRegisteredException(String message) {
        super(message);
    }
}

public class HockeyManager {
    private List<HockeyPlayer> players;

    public HockeyManager() {
        players = new ArrayList<>();
    }

    private boolean isPlayerRegistered(String name) {
        for (HockeyPlayer player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    private void addPlayer(HockeyPlayer player) {
        if (isPlayerRegistered(player.getName())) {
            throw new PlayerAlreadyRegisteredException("Player with name " + player.getName() + " is already registered.");
        }
        players.add(player);
    }

    public void AddNewForward(String name, int age) {
        addPlayer(new Forward(name, age));
    }

    public void AddNewForward(String name, int age, int goals) {
        addPlayer(new Forward(name, age, goals));
    }

    public void AddNewDefender(String name, int age) {
        addPlayer(new Defender(name, age));
    }

    public void AddNewDefender(String name, int age, int hits) {
        addPlayer(new Defender(name, age, hits));
    }

    public void AddNewGoalie(String name, int age) {
        addPlayer(new Goalie(name, age));
    }

    public void AddNewGoalie(String name, int age, int wins) {
        addPlayer(new Goalie(name, age, wins));
    }

    public void PrintNameAndAgeOfTheYoungestPlayer() {
        if (players.isEmpty()) {
            System.out.println("No players registered.");
            return;
        }

        HockeyPlayer youngest = players.get(0);
        for (HockeyPlayer player : players) {
            if (player.getAge() < youngest.getAge()) {
                youngest = player;
            }
        }

        System.out.printf("Youngest player: Name: %s, Age: %d\n", youngest.getName(), youngest.getAge());
    }

    public void updateGoals(String name, int newGoals) {
        HockeyPlayer player = findPlayerByName(name);
        Forward forward = validatePlayerType(player, Forward.class);
        forward.setGoals(newGoals);
        //System.out.printf("Goals for forward %s updated to %d\n", name, newGoals);
    }

    public void updateHits(String name, int newHits) {
        HockeyPlayer player = findPlayerByName(name);
        Defender defender = validatePlayerType(player, Defender.class);
        defender.setHits(newHits);
        //System.out.printf("Hits for defender %s updated to %d\n", name, newHits);
    }

    public void updateWins(String name, int newWins) {
        HockeyPlayer player = findPlayerByName(name);
        Goalie goalie = validatePlayerType(player, Goalie.class);
        goalie.setWins(newWins);
        //System.out.printf("Wins for goalie %s updated to %d\n", name, newWins);
    }

    public void incrementGoals(String name, int count) {
        HockeyPlayer player = findPlayerByName(name);
        Forward forward = validatePlayerType(player, Forward.class);
        forward.setGoals(forward.getGoals() + count);
        //System.out.printf("Goals for forward %s incremented by %d, now set to %d\n", name, count, forward.getGoals());
    }

    public void incrementHits(String name, int count) {
        HockeyPlayer player = findPlayerByName(name);
        Defender defender = validatePlayerType(player, Defender.class);
        defender.setHits(defender.getHits() + count);
        //System.out.printf("Hits for defender %s incremented by %d, now set to %d\n", name, count, defender.getHits());
    }

    public void incrementWins(String name, int count) {
        HockeyPlayer player = findPlayerByName(name);
        Goalie goalie = validatePlayerType(player, Goalie.class);
        goalie.setWins(goalie.getWins() + count);
        //System.out.printf("Wins for goalie %s incremented by %d, now set to %d\n", name, count, goalie.getWins());
    }

    private <T extends HockeyPlayer> T validatePlayerType(HockeyPlayer player, Class<T> type) {
        if (type.isInstance(player)) {
            return type.cast(player);
        }
        throw new IllegalArgumentException("Player " + player.getName() + " is not a " + type.getSimpleName() + ".");
    }


    private HockeyPlayer findPlayerByName(String name) {
        for (HockeyPlayer player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        throw new IllegalArgumentException("Player with name " + name + " does not exist.");
    }

    public void removePlayer(String name) {
        HockeyPlayer playerToRemove = findPlayerByName(name);
        players.remove(playerToRemove);
        //System.out.println("Player " + name + " has been removed.");
    }

    public void printAllPlayers() {
        System.out.println("Players:");
        if (players.isEmpty()) {
            System.out.println("No players registered.");
            return;
        }

        for (HockeyPlayer player : players) {
            if (player instanceof Forward) {
                Forward forward = (Forward) player;
                System.out.printf("Forward: Name: %s, Age: %d, Goals: %d\n", forward.getName(), forward.getAge(), forward.getGoals());
            } else if (player instanceof Defender) {
                Defender defender = (Defender) player;
                System.out.printf("Defender: Name: %s, Age: %d, Hits: %d\n", defender.getName(), defender.getAge(), defender.getHits());
            } else if (player instanceof Goalie) {
                Goalie goalie = (Goalie) player;
                System.out.printf("Goalie: Name: %s, Age: %d, Wins: %d\n", goalie.getName(), goalie.getAge(), goalie.getWins());
            }
        }
    }



    // Nested classes remain unchanged
    private abstract class HockeyPlayer {
        private String name;
        private int age;

        public HockeyPlayer(String name, int age) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Player name cannot be null or empty.");
            }
            if (age <= 0) {
                throw new IllegalArgumentException("Player age must be greater than zero.");
            }
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    private class Forward extends HockeyPlayer {
        private int goals;

        public Forward(String name, int age) {
            super(name, age);
            this.goals = 0;
        }

        public Forward(String name, int age, int goals) {
            super(name, age);
            this.goals = goals;
        }

        public int getGoals() {
            return goals;
        }

        public void setGoals(int goals) {
            this.goals = goals;
        }
    }

    private class Defender extends HockeyPlayer {
        private int hits;

        public Defender(String name, int age) {
            super(name, age);
            this.hits = 0;
        }

        public Defender(String name, int age, int hits) {
            super(name, age);
            this.hits = hits;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }
    }

    private class Goalie extends HockeyPlayer {
        private int wins;

        public Goalie(String name, int age) {
            super(name, age);
            this.wins = 0;
        }

        public Goalie(String name, int age, int wins) {
            super(name, age);
            this.wins = wins;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }
    }
}
