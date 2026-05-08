Design Justification:

Throughout the process of making this game, we had many ideas of how to approach the process of creating rooms and buildings.
We went over the process of creating rooms through a loop, so that we didn't have to manually construct each Room object.
However, we realized that we would have to change the name, description, etc of all the rooms individually anyways.
This led to us settling for constructing each individual Room object. The Room objects became apart of an ArrayList in the Building class. 

We also considered using the navigation format similar to that of Zork. Such as having the user move by typing "Go North, Go South...",
However, we decided against that and instead implemented an enter method instead. We felt that this method was more accessible to users,
especially since we provide a map of the world.

***

Regarding the implementation of our navigation, we had a few different ideas. We considered saving "Directions" and "Rooms" in a HashMap attribute of the Room class, to create a type of multi-linked list structure. However, this design would require manually adding every adjacent Room to the HashMap of each Room - or organizing Rooms in an ArrayList in the Building class, and using these indices to initialize these HashMaps.
So, we considered storing Rooms in a nested ArrayList in Building. Unlike the previous option, this would not require us to consider the directed relationship of a Room to each of its adjacent Rooms. The main problem we found with this method was that this would create a strict grid. It would not allow for a non-rectangular world, without storing null values in the array.
So, we chose to store Rooms and their coordinates in an array in the Building class. The 'rooms' attribute of Building is an ArrayList of ArrayLists. This inner array contains a Room, its x-coordinate, and its y-coordinate: {Room, x, y}. This allows us to search for adjacent rooms using 'indices', without storing empty space. This let us make the "ship" Building not rectangular, without needing to handle null values in an ArrayList. 