//package ui;
//
//import model.Goalie;
//import model.Player;
//import model.Team;
//import model.League;
//import model.ScoreSheet;
//import persistence.JsonWriter;
//import persistence.JsonReader;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import java.util.List;
//import java.util.Scanner;
//
//// user interaction for my project
//public class ScoreSheetApp {
//
//    private static final String JSON_PATH = "./data/league.json";
//    private League myLeague;
//    private Scanner input;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//
//    // EFFECTS: initializes program
//    public ScoreSheetApp() throws FileNotFoundException {
//        init();
//    }
//
//    // EFFECTS: creates some sample teams, players, and goalies for testing, initializes Scanner object,
//    //          a league, and starts the app loop
//    public void init() {
//        input = new Scanner(System.in);
//        myLeague = new League("My League");
//        jsonWriter = new JsonWriter(JSON_PATH);
//        jsonReader = new JsonReader(JSON_PATH);
//        scoreSheetLoop();
//    }
//
//    // EFFECTS: prompts user with options on how to run the app
//    public void scoreSheetLoop() {
//        System.out.println("\nAdd/Edit Team = 1 \nMake new ScoreSheet = 2 \nList all stats = 3");
//        System.out.println("Load League from file = 4 \nSave League to file = 5 \nExit Program = 9");
//        int i = input.nextInt();
//        if (i == 1) {
//            modifyTeam();
//        } else if (i == 2) {
//            makeScoreSheet();
//        } else if (i == 3) {
//            listAllStats(myLeague);
//            scoreSheetLoop();
//        } else if (i == 4) {
//            loadLeague();
//        } else if (i == 5) {
//            saveLeague();
//        } else if (i == 9) {
//            System.out.println("goodbye");
//        } else {
//            System.out.println("Select again");
//            scoreSheetLoop();
//        }
//    }
//
//    // EFFECTS: prints stats of every player and goalie of every team in the league
//    public void listAllStats(League myLeague) {
//        String myLeagueName = myLeague.getName();
//        System.out.println(myLeagueName);
//        for (Team t : myLeague.getTeams()) {
//            System.out.println(t.getName() + " Stats:");
//            System.out.println("Wins: " + (t.getWins()));
//            System.out.println("Losses: " + (t.getLosses()));
//            List<Player> currentTeamPlayers = t.getPlayers();
//            for (Player p : currentTeamPlayers) {
//                System.out.println(p.getName());
//                System.out.println("Goals: " + (p.getGoals()));
//                System.out.println("Assists: " + (p.getAssists()));
//                System.out.println("PIM: " + (p.getPenaltyMinutes()));
//            }
//            List<Goalie> currentTeamGoalies = t.getGoalies();
//            for (Goalie g : currentTeamGoalies) {
//                System.out.println(g.getName());
//                System.out.println("Shots against: " + (g.getShotsAgainst()));
//                System.out.println("Goals against: " + (g.getGoalsAgainst()));
//                System.out.println("Wins: " + (g.getWins()));
//            }
//        }
//    }
//
//    // EFFECTS: prompts user to either add a team or edit a team, and calls respective methods
//    public void modifyTeam() {
//        System.out.println("\nAdd team = add");
//        System.out.println("Edit team = edit");
//        String modifyTeamAnswer = input.next();
//        if ("add".equals(modifyTeamAnswer)) {
//            System.out.println("Enter a team name");
//            String teamName = input.next();
//            Team newTeam = new Team(teamName);
//            myLeague.addTeam(newTeam);
//            scoreSheetLoop();
//        } else if ("edit".equals(modifyTeamAnswer)) {
//            editTeam();
//        } else {
//            modifyTeam();
//        }
//
//    }
//
//    //EFFECTS: prompts user to select how they'd like to edit a selected team, and calls editing methods
//    public void editTeam() {
//        System.out.println("List of Teams:");
//        for (Team t : myLeague.getTeams()) {
//            System.out.println(t.getName());
//        }
//        System.out.println("Enter the team you'd like to edit");
//        String userInput = input.next();
//        Team team = myLeague.findTeam(userInput);
//        System.out.println("Add player = addP");
//        System.out.println("Remove player = remP");
//        System.out.println("Add goalie = addG");
//        System.out.println("Remove goalie = remG");
//        String editTeamAnswer = input.next();
//        if ("addP".equals(editTeamAnswer)) {
//            scoreSheetAddPlayer(team);
//        } else if ("remP".equals(editTeamAnswer)) {
//            scoreSheetRemovePlayer(team);
//        } else if ("addG".equals(editTeamAnswer)) {
//            scoreSheetAddGoalie(team);
//        } else if ("remG".equals(editTeamAnswer)) {
//            scoreSheetRemoveGoalie(team);
//        }
//
//
//    }
//
//    // EFFECTS: creates a new player based on user input and adds it to a team
//    public void scoreSheetAddPlayer(Team team) {
//        System.out.println("Enter their Name");
//        String playerName = input.next();
//        System.out.println("Enter their Age");
//        int playerAge = Integer.parseInt(input.next());
//        System.out.println("Enter their Position");
//        String playerPosition = input.next();
//        Player player = new Player(playerName, playerAge, playerPosition);
//        team.addPlayer(player);
//        scoreSheetLoop();
//    }
//
//    // EFFECTS: prompts uer to select a player from a team, and removes that player from the team
//    public void scoreSheetRemovePlayer(Team team) {
//        System.out.println("List of players:");
//        for (Player p : team.getPlayers()) {
//            System.out.println(p.getName());
//        }
//        System.out.println("Enter their Name");
//        String playerName = input.next();
//        Player player = team.findPlayer(playerName);
//        team.removePlayer(player);
//        scoreSheetLoop();
//    }
//
//    //EFFECTS: creates a new goalie based on user input and adds it to a team
//    public void scoreSheetAddGoalie(Team team) {
//        System.out.println("Enter their Name");
//        String goalieName = input.next();
//        System.out.println("Enter their Age");
//        int goalieAge = Integer.parseInt(input.next());
//        Goalie goalie = new Goalie(goalieName, goalieAge);
//        team.addGoalie(goalie);
//        scoreSheetLoop();
//    }
//
//    //EFFECTS: prompts uer to select a goalie from a team, and removes that goalie from the team
//    public void scoreSheetRemoveGoalie(Team team) {
//        System.out.println("List of goalies:");
//        for (Goalie g : team.getGoalies()) {
//            System.out.println(g.getName());
//        }
//        System.out.println("Enter their Name");
//        String goalieName = input.next();
//        Goalie goalie = team.findGoalie(goalieName);
//        team.removeGoalie(goalie);
//        scoreSheetLoop();
//    }
//
//    //EFFECTS: creates a ScoreSheet for a game, user selects from teams in league
//    public void makeScoreSheet() {
//        Scanner input = new Scanner(System.in);
//        System.out.println("List of Teams:");
//        for (Team t : myLeague.getTeams()) {
//            System.out.println(t.getName());
//        }
//        System.out.println("Enter a team");
//        String inputTeam1String = input.next();
//        Team team1 = myLeague.findTeam(inputTeam1String);
//        System.out.println("Enter another team");
//        String inputTeam2String = input.next();
//        Team team2 = myLeague.findTeam(inputTeam2String);
//        ScoreSheet myScoreSheet = new ScoreSheet(team1, team2);
//        editScoreSheet(myScoreSheet, team1, team2);
//    }
//
//    // EFFECTS: loop for editing the ScoreSheet, user can call methods to edit player/goalie stats or end the
//    //          ScoreSheet at the end of a game
//    public void editScoreSheet(ScoreSheet scoresheet, Team team1, Team team2) {
//        int end = 0;
//        while (end != 9) {
//            String msg = "Select 1 for " + team1.getName() + " or 2 for " + team2.getName() + " or 9 to end ScoreS
//            heet";
//            System.out.println(msg);
//            int teamSelect = input.nextInt();
//            end = teamSelect;
//            if (end == 9) {
//                endScoreSheet(scoresheet, team1, team2);
//            } else {
//                editScoreSheetPlayerOrGoalie(scoresheet, team1, team2, teamSelect);
//
//            }
//        }
//    }
//
//    // EFFECTS: prompts user to either edit a player or goalie in the team they previously selected
//    public void editScoreSheetPlayerOrGoalie(ScoreSheet scoresheet, Team team1, Team team2, int teamSelect) {
//        System.out.println("Select to edit a player or goalie: Player = p, Goalie = g");
//        String playerOrGoalie = input.next();
//        if (playerOrGoalie.equals("p")) {
//            if (teamSelect == 1) {
//                editPlayer(team1, scoresheet, 1);
//            } else {
//                editPlayer(team2, scoresheet, 2);
//            }
//        } else if (playerOrGoalie.equals("g")) {
//            if (teamSelect == 1) {
//                editGoalie(team1);
//            } else {
//                editGoalie(team2);
//            }
//        }
//    }
//
//    // EFFECTS: ends the ScoreSheet, adds wins and losses appropriately to teams based on score of game
//    public void endScoreSheet(ScoreSheet scoresheet, Team team1, Team team2) {
//        if (scoresheet.getTeam1Score() > scoresheet.getTeam2Score()) {
//            team1.addWin();
//            addWinGoalie(team1);
//            team2.addLoss();
//            System.out.println(team1.getName() + " wins!");
//        } else if (scoresheet.getTeam1Score() < scoresheet.getTeam2Score()) {
//            team1.addLoss();
//            addWinGoalie(team2);
//            team2.addWin();
//            System.out.println(team2.getName() + " wins!");
//        } else {
//            System.out.print("Tie \n");
//        }
//        scoreSheetLoop();
//    }
//
//    // EFFECTS: allows user to select which goalie on winning team is credited with a win
//    public void addWinGoalie(Team team) {
//        System.out.println("Which goalie gets the win?");
//        for (Goalie g : team.getGoalies()) {
//            System.out.println(g.getName());
//        }
//        String winningGoalieString = input.next();
//        Goalie winningGoalie = team.findGoalie(winningGoalieString);
//        winningGoalie.addWin();
//    }
//
//    //EFFECTS: prompts user to select a player, and which stat they'd like to modify
//    public void editPlayer(Team team, ScoreSheet scoreSheet, int teamID) {
//        System.out.println("Choose a player:");
//        for (Player p : team.getPlayers()) {
//            System.out.println(p.getName());
//        }
//        String player = input.next();
//        Player currentPlayer = team.findPlayer(player);
//        System.out.println("Choose a stat for " + player);
//        System.out.println("Add 1 Goal = g \nAdd 1 Assist = a \nAdd Penalty Minutes = p");
//        String playerStat = input.next();
//        if (playerStat.equals("g")) {
//            currentPlayer.addGoal();
//            if (teamID == 1) {
//                scoreSheet.addGoalTeam1();
//
//            } else if (teamID == 2) {
//                scoreSheet.addGoalTeam2();
//            }
//        } else if (playerStat.equals("a")) {
//            currentPlayer.addAssist();
//        } else if (playerStat.equals("p")) {
//            System.out.println("Enter number of minutes: ");
//            int i = input.nextInt();
//            currentPlayer.addPenaltyMinutes(i);
//        }
//    }
//
//    // EFFECTS: prompts user to select a goalie and which stat they'd like to modify
//    public void editGoalie(Team team) {
//        System.out.println("Choose a goalie:");
//        for (Goalie g : team.getGoalies()) {
//            System.out.println(g.getName());
//        }
//        String goalie = input.next();
//        Goalie currentGoalie = team.findGoalie(goalie);
//        System.out.println("Choose a stat for " + goalie);
//        System.out.println("Add 1 Shot Against = s");
//        System.out.println("Add 1 Goal Against = g");
//        System.out.println("Add Win = w");
//        String goalieStat = input.next();
//        if (goalieStat.equals("s")) {
//            currentGoalie.addShotAgainst();
//        } else if (goalieStat.equals("g")) {
//            currentGoalie.addGoalAgainst();
//        }
//    }
//
//    // EFFECTS: saves League to file
//    private void saveLeague() {
//        try {
//            jsonWriter.openWriter();
//            jsonWriter.write(myLeague);
//            jsonWriter.closeWriter();
//            System.out.println("Saved " + myLeague.getName() + " to " + JSON_PATH);
//            scoreSheetLoop();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save file to " + JSON_PATH);
//        }
//    }
//
//    // EFFECTS: loads League from file
//    private void loadLeague() {
//        try {
//            myLeague = jsonReader.readLeague();
//            System.out.println("Loaded " + myLeague.getName() + " from " + JSON_PATH);
//            scoreSheetLoop();
//        } catch (IOException e) {
//            System.out.println("Unable to load from file: " + JSON_PATH);
//        }
//    }
//
//}
//
//
//
//
