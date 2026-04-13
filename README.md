# Group31_EECS-1720---Assignment

# Dungeon Gacha Adventure Game

## Overview
Dungeon Gacha Adventure Game is a simple turn-based adventure game built in Java with Swing. In this game, the player explores a dungeon made up of different room types, random events, and monster encounters. The core mechanic of the game is a gacha system, which allows the player to pull random items that may either help or harm them during their journey.

The project follows a MVC style structure, where the model manages the game logic, the view displays the interface, and the controller handles user input and updates the game.

## How to Run the Program
1.	Clone or download the project.
2.	Open the project in a Java IDE such as Eclipse.
3.	Compile the project. 
4.	Run the **Main.java in app package** to launch the start screen. 
5.	Select a difficulty level and character type, then start the game.

## Gameplay
At the start of the game, the player chooses a dungeon difficulty level and a character type. Different character types provide different advantages, such as stronger attacks, better gacha luck, extra starting HP, or extra starting shield.

The player begins in the Gacha Room with 40 HP and 5 gacha tickets. In this room, the player can spend tickets to pull random items from the gacha system and store them in their backpack.
After preparing in the Gacha Room, the player chooses the next room to enter from a list of available room options. The game shows the difficulty level of each room before the player makes a choice. The player may enter either an Event Room or a Monster Room.

## Gacha System and Items
The gacha system is one of the main features of the game. Each time the player pulls, they receive a random item with different probabilities.

Possible items include:

•	**Healing Potion**: restores 10 HP 

•	**Poison Potion**: damages the player by 5 HP 

•	**Shield**: gives the player 5 shield points 

•	**Bomb**: deals 15 damage to the monster 

•	**Lucky Charm**: guarantees the next dodge 

•	**Instant Kill Sword**: defeats the monster instantly 

•	**Cursed Skull**: kills the player instantly 

All items are stored in the player’s backpack. If the backpack is full, the player must discard an existing item before adding the new one.

## Event Rooms
In an Event Room, a random event happens immediately. Examples include:

•	a trap that damages the player 

•	a healing fountain that restores HP 

•	a curse that reduces the player’s next dodge chance 

•	a treasure reward that gives extra gacha tickets 

## Monster Rooms
In a Monster Room, the player must survive and defeat the monster. During battle, the player can:

•	use an item from the backpack 

•	perform a normal attack 

•	dodge the monster’s attack 

A normal attack has a 60% success rate, while dodge has a base success rate of 80%. Some character types and item effects can modify these actions.

After the player successfully clears a room, they receive additional gacha tickets based on the difficulty of that room and return to the Gacha Room to prepare for the next challenge.

## GUI Features
The game includes a graphical user interface that displays important game information such as:

•	player HP 

•	monster HP 

•	current room type 

•	number of monsters left 

•	number of gacha tickets 

•	room choices 

•	action log 

•	backpack contents 

The GUI also includes a display panel that provides additional information to the player. By moving the mouse over certain interactive components, such as room choices and backpack items, the player can view related descriptions and information in the display panel. This helps the player better understand the effects of items during gameplay.

## Controls
### Start Screen

•	Select the dungeon difficulty 

•	Select the player character type 

•	Click Start Game 

### Keyboard Controls

•	G: pull gacha in the Gacha Room

•	1-3: choose the next room

•	Q/W/E/R/T: use an item from the corresponding backpack slot in a Monster Room 

•	A: perform a normal attack 

•	D: dodge 

### Mouse Interaction

•	Hover over certain components to view descriptions in the display panel 

## Win and Lose Conditions
The player wins the game by clearing all Monster Rooms in the dungeon.

The player loses if their HP reaches 0.

## Division of Work
Yik: Main gameplay logic and mouse event handling

Sophia: Gacha system logic and items probabilities

Angel: GUI layout and UML document

Tian: Keyboard input and event listener handling

## References of sources
https://ca.pinterest.com/pin/925700898419422747/ :character

https://ca.pinterest.com/pin/51298883246255504/ :monster

https://www.reddit.com/r/PixelArt/comments/8e7bu6/occc_buried_shrine_gif/ : monsterroom

https://www.reddit.com/media?url=https%3A%2F%2Fi.redd.it%2Fikuoza7zbxf51.png : gatcharoom 

https://ca.pinterest.com/pin/18929260937822481/ :eventroom

https://ca.pinterest.com/pin/4081455907773574/ :backpack 

https://ca.pinterest.com/pin/5770305769402744/ :player

https://ca.pinterest.com/pin/915778905485280683/ :bomb

https://ca.pinterest.com/pin/308707749478504107/ :cursedSkull

https://ca.pinterest.com/pin/37225134415932317/ :luckyCharm

https://ca.pinterest.com/pin/39617671718989003/ :shieldItem

https://emojiterra.com/question-mark/ :questionmark

https://ca.pinterest.com/pin/124200902218441231/ :gameicon 

https://ca.pinterest.com/pin/2392606048229233/ :warrior

https://ca.pinterest.com/pin/225672631323171656/ :rogue

https://ca.pinterest.com/pin/562105597264969547/ :tank


