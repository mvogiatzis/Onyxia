======================================== ONYXIA APPLICATION ========================================

The game is simple to play and the player does not need any instructions in order to understand the gameplay.

To run the game from command line, go to dist directory and type:
"java -jar Onyxia_game.jar"
or double click on the Onyxia_game.jar , it is executable.

I did not use any automated GUI to create the user interface.

User's prefer to interact pressing buttons rather writing commands, and this is the most common way to play the game.
However, the game can be played completely using the provided JTextField.
Note: Every time that a user tries to perform an action, his current actions are typed automatically into the JTextField.
From Human Computer Interaction point of view, this helps the user remember which command he previously entered, this is
why it is used.

There is a background music in the game that loops and some other small sounds that they play according to
user actions. Each sound is a seperated thread.
The audio is part of the interface with the user, this is why it is implemented in User Interface class.

Note: You cannot open two java jar or keep busy with some way the audio because the audio interface cannot access a sound
if another application is using it at the same time. Also an audio device must be available.

The player has 'hitpoints' field that is not used in the current implementation but it is an important feature, this is why
it is present in the current implementation.

The game is easy to finish if you pay attention to everything you find.

