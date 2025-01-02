# My Stat Tracker



## User stories
- as a user, I want to be able to add hockey team(s) to a league
- as a user, I want to be able to add player(s) and goalie(s) to a hockey team
- as a user, I want to be able to remove player(s) or goalie(s) to a hockey team
- as a user, I want to be able to create a game/ScoreSheet that involves 2 teams
- as a user, I want to be able to change the stats (such as goals, assists, PIM) of a player and goalie stats (such as 
  goals against, saves) in a game/ScoreSheet
- as a user, I want stats to automatically update while using a ScoreSheet
- as a user, I want to be able to view the stat totals of players and goalies

-as a user, I want to be able to be given the option to save a League
-as a user, I want to be able to be given the option to load a saved League

## Instructions
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking "Add/Edit Team" then "Add Team" and entering a Team name and pressing "enter"
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking "Add/Edit Team" then "Edit Team" then selecting a Team (a Team must be added for this to work), then "Remove Player/Goalie" then press the Player/Goalie to be removed (Must be a Player or Goalie to work) or Pressing "List All Stats" in the main menu
- You can locate my visual component by running the program
- You can save the state of my application by pressing "Save League to File"
- You can reload the state of my application by pressing "Load League from File"

## Sample log
Fri Dec 01 14:58:14 PST 2023
League My League Made
Fri Dec 01 14:58:26 PST 2023
Team My Team Made
Fri Dec 01 14:58:26 PST 2023
Team My Team Added
Fri Dec 01 14:58:26 PST 2023
League Stats Updated
Fri Dec 01 14:58:29 PST 2023
League Stats Updated
Fri Dec 01 14:58:30 PST 2023
League Stats Updated
Fri Dec 01 14:58:30 PST 2023
League Stats Updated
Fri Dec 01 14:58:36 PST 2023
New Player Player1 Made
Fri Dec 01 14:58:36 PST 2023
Player Player1 Added to My Team
Fri Dec 01 14:58:36 PST 2023
League Stats Updated
Fri Dec 01 14:58:39 PST 2023
League Stats Updated
Fri Dec 01 14:58:40 PST 2023
League Stats Updated
Fri Dec 01 14:58:41 PST 2023
League Stats Updated
Fri Dec 01 14:58:46 PST 2023
New Goalie Goalie1 Made
Fri Dec 01 14:58:46 PST 2023
Goalie Goalie1 Added to My Team
Fri Dec 01 14:58:46 PST 2023
League Stats Updated
Fri Dec 01 14:58:51 PST 2023
League Stats Updated
Fri Dec 01 14:58:52 PST 2023
League Stats Updated
Fri Dec 01 14:58:54 PST 2023
League Stats Updated
Fri Dec 01 14:58:55 PST 2023
Player Player1 Removed from My Team
Fri Dec 01 14:58:55 PST 2023
League Stats Updated

## Improvements

-Implement Observer pattern to update my Panels, right now many panels that don't need to be updated
are being updated when performing certain actions

-Re-do the way I make and display panels, do I have to create a new panel every time I update a League?

-More elegant formatting and display

-Implement exceptions for robustness such as when a user passes text information into fields

-Implement a global main menu button to remove the circumstance where a User accidentally clicks something and then has to follow
through steps they might not want to take to get back to the main menu

-Perhaps remove the bidirectional association between ScoreSheetAppGUI and ScoreSheetPanel

-Have player and goalie extend an abstract Person class
as there are a lot of similar methods throughout the program that each take either a player or goalie