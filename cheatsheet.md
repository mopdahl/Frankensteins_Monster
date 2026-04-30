This file will contain documentation for all commands available in your game.

Note:  It's a good idea to also make this list available inside the game, in response to a `HELP` command.

# GAME COMMANDS:

#### Basic Commands and Orientation
  * options
    - (no parameters): Shows a list of the commands you can try.

  * map
    - (no parameters): Shows a map of the building you are in. If you are in the woods, shows a map of the world.

  * inventory
    - (no parameters): Shows a list of the items you are carrying.

  * quit
    - (no parameters): Ends the game -- you won't be able to return.

#### Interactions

  * get
    - (an item): Adds the item to your inventory.

  * drop
    - (an item): Removes the item from your inventory.

  * use
    - (an item): Uses the given item. Effects depend on the item.

  * eat
    - (food): Makes you eat the food. Some food items may impact your health.

  * read
    - (a book): Shows the book's contents, if you can read.

  * look
    - (no parameters): Shows the description and contents of the room you are in. The command "look around" does the same.
    - (an item): Shows the item's description.

  * consider
    - (a person): Shows the person's statistics -- their name, hitpoints, and what they are holding.

  * attack
    - (a person): Attacks the person. Be forewarned -- aggression tends to be returned.

 * talk
   - (a person): Sees what the person has to say, if anything.

#### Navigation

  * exit
    - (no parameters): Makes you exit the building you are in. You will automatically move into the adjacent building.

  * enter
    - (a room): Makes you move into the given room.
    - (a building): Makes you move into the building, if you are not currently in a building.

  * lock
    - (a room): Locks the room, making it inaccessible.

  * unlock
    - (a room): Unlocks the room, making it accessible.



# SPOILER ALERT

If your game includes challenges that must be overcome to win, also list them below.

  * Kill Frankenstein
    - Frankenstein's room is only accessible by passing a room of aggressive sailors, who must be killed or bypassed (this can be done by simply 'entering' the next room). In order to survive the sailors AND kill Frankenstein, the player must make use of a weapon.
