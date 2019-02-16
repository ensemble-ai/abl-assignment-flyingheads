
ABL-Assignment-Solution
====================

Description
--------------------
This project is divided into three section - Starter tasks, Intermediate tasks and Advanced tasks. Please follow the instruction below to navigate throught the project. 

Starter Tasks
--------------------
Follow the steps below to get the starter agent to work : 
* In GameEngine.java, uncomment lines 151 and 152  (Starter ABL files). Save the file.
```
      StarterAgent starterAgent = new StarterAgent();
      starterAgent.startBehaving();
```
* Run AgentCompiler.java.
* Refresh the abl.generated folder.
* Run GameEngine.java.

Initially, you would observe a player colored in blue. You can use arrow keys to change it's position.

Press "B" to create a new bot. This bot has a MoveTo behavior (which uses the move action) to follow the player around. 

Press "C" to change the color of the bots.

* Add a move action that takes a (int xdir, int ydir, int id) as input.
* Make a MoveTo behavior that moves a bot to a target location over time.
* Add a SetColor action that takes (int r, int b, int g, int id).
* Add color sensing to the BotWME (this includes changes to BotSensor.java and BotWME.java).

Intermediate Tasks
--------------------
Follow the steps below to get the intermediate agent to work : 
* In GameEngine.java, uncomment lines 153 and 154  (Intermediate ABL files). Save the file.
```
      IntermediateAgent intermediateAgent = new IntermediateAgent();
      intermediateAgent.startBehaving();
```
* Run AgentCompiler.java.
* Refresh the abl.generated folder.
* Run GameEngine.java.

Initially, you would observe a player colored in blue. You can use arrow keys to change it's position. 

Press "F" to spawn four new bots around the player. These bots maintain the formation around the bot by moving around with it.

* Add on action that creates a new Bot
* Create 4 bots and have them keep a formation around the player.

Advanced Tasks
--------------------
Follow the steps below to get the advanced agent to work : 
* In GameEngine.java, uncomment lines 155 and 156  (Advanced ABL files). Save the file.
```
      AdvancedAgent advancedAgent = new AdvancedAgent();
      advancedAgent.startBehaving();
```
* Run AgentCompiler.java.
* Refresh the abl.generated folder.
* Run GameEngine.java.

Our advanced agent has following characteristics : 
* All the bots generated in the game are angry at the player and maintain a certain distance at all times. 
* If the player approaches a bot and manages to come closer to the bot, it turns into red color (signigying anger).
* If the player is very close to the bot, it transforms from a small red bot to a large red bot. 

Initially, you would observe a player colored in blue. You can use arrow keys to change it's position. 

Press "F" to spawn four new bots around the player. These bots will follow the characteristics as mentioned above. User can always press "F" at any point in the game to create four new bots. 


Design and create a multi-bot social interaction that communicates to the player via movement and bot color. The player should be able to interact with your multi-bot behavior set through movement, shooting, or some other user interaction that you design.

