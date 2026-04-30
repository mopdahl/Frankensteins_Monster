# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
    - We discusseed different stories and worlds that we were interested in building upon and we settled on Frankenstein. We talked about a lot of various classes that we needed, but most of our work was planned out as we were building the game step by step.
    -  We initially decided on our major building blocks, then focused on world structure and navigation. Since we weren't entirely sure what we wanted from the project, we set up a semi-versatile world, and added specific objects/commands later.

 - What **new thing(s)** did you learn / figure out in completing this project?
    - I feel like I learned a lot more about Hashmaps due to this assignment, and just in general solidified my understanding of Java.
    - I definitely struggled with the dynamic typecasting that we ended up needing in the game loop. I ended up using quite a few helper methods (many removed) to try to work around Java's strict typecasting; it helped me appreciate some of Java's limits and benefits.

 - Is there anything that you wish you had **implemented differently**?
    - No, I rather enjoyed the process of making this game. I wish at the start we created an observable item class rather than diving straight into a grabbable object class though. I wish I could've implemented that feature before the deadline.
    - Within the game loop, I wish we had implemented user commands differently. I tried to make the while loop more concise by storing commands in a HashMap. This seems to have worked fine, but required several helper methods to remove bugs. I wish we used more conditionals when interpreting input, and perhaps used multiple HashMaps to store different types of commands, to try to make running commands more efficient.

 - If you had **unlimited time**, what additional features would you implement?
    - I would add the observable object class. I would make the characters a little bit more interesting. I would implement clothing to shield player from damage.
    - I would love to make mobile characters, who could possibly follow/hunt the player. Also, it would have been helpful to add a 'help' command, and to override GrabbableObject's 'use' method in more of its subclasses.

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
    -  ?

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
    - 
    -  Think about how to organize classes before anything else! Adding seemingly superfluous superclasses may make it easier to generalize method calls. Also, write docstrings as you write functions, even if you think you'll delete them later.

 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
