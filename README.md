# WhiteElephantGame
I made a java program that manages a white elephant game. It still has bugs I need to work out but it is pretty functional as is.

Game Rules:

Each participant must bring a gift to donate to the pot in order to play. Before the game begins, each player is randomly assigned a number to determine the order of players.

TURN OPTIONS:
1. Select Gift:
   Choose a gift from the pot and unwrap it. If you have a gift already, it must be returned to the pot.
2. Hold Gift:
   Do nothing. You keep the gift you currently have.
3. Steal Gift:
   Take someone else's gift and return your old gift to the pot. The victim of the steal then gets to select a gift from the pot or steal someone else's gift. See STEALS section for more information on how steals work.
   
STEALS:
Whenever a gift is stolen, it receives a strike. Upon the third steal, the gift will have three strikes and the person that has the gift after the final steal will be removed from the game with that gift. Also, a gift cannot be stolen within the same turn. For example, if player A steals a gift from player B, player B cannot steal back the gift that turn. 
