Design Justification:

Throughout the process of making this game, we had many ideas of how to approach the process of creating rooms and buildings.
We went over the process of creating rooms through a loop, so that we didn't have to manually construct each Room object.
However, we realized that we would have to change the name, description, etc of all the rooms individually anyways.
This led to us settling for constructing each individual Room object. The Room objects became apart of an ArrayList in the Building class. 

We also considered using the navigation format similar to that of Zork. Such as having the user move by typing "Go North, Go South...",
However, we decided against that and instead implemented an enter method instead. We felt that this method was more accessible to users,
especially since we provide a map of the world.

***