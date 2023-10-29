# The Thief Escape
<img width="948" alt="Demo" src="https://github.com/tisarji-team/The-Thief-Escaped/assets/48820052/5633f951-8109-4fdb-a5f2-2d41f035beca">

# Function

In the provided Java code, we have a game represented by several functions. Each function has a specific role in managing and executing the game. Here's a breakdown of the functions in the code

1. `initFont()`: This function initializes a custom font used in the game by loading a TrueType font file named "PixeloidMono.ttf" and registering it with the graphics environment.

2. `Board()` Constructor: The constructor initializes an instance of the `Board` class, calling the `loadImages()`, `initVariables()`, and `initFont()` functions to set up the game.

3. `initBoard()`: This function initializes the game board by adding a key adapter, making it focusable, and setting the background color to black.

4. `initVariables()`: This function initializes various game variables and data structures, including screen data, ghost positions, game dimensions, and more. It also sets up a timer for game animation.

5. `addNotify()`: This method overrides the `addNotify` method in Swing components. It initializes the game by calling `initGame()` when the component is added to a container.

6. `doAnim()`: This function controls the animation of the game, specifically the movement of the player character (thief). It updates the thief's animation frame based on a countdown and direction.

7. `playGame(Graphics2D g2d)`: This function handles the game logic during active gameplay. It checks if the game is in a "dying" state and triggers the "death" sequence or moves the thief and ghosts.

8. `showIntroScreen(Graphics2D g2d)`: This function displays the game's intro screen with a message to start the game by pressing 'S'.

9. `drawScore(Graphics2D g)`: This function draws the player's score and remaining lives on the screen.

10. `checkMaze()`: This function checks if the player has completed the current maze (eaten all dots) and updates the game accordingly.

11. `death()`: This function is called when the player character (thief) loses a life. It decrements the remaining lives and triggers the "death" sequence. If no lives are left, the game ends.

12. `moveGhosts(Graphics2D g2d)`: This function controls the movement and behavior of the ghosts in the game. It calculates their new positions, handles collisions, and checks for interactions with the thief.

13. `drawGhost(Graphics2D g2d, int x, y)`: This function draws a ghost on the game board at the specified position.

14. `moveThief()`: This function controls the movement of the player character (thief), handling user input and updating the thief's position.

15. `drawThief(Graphics2D g2d)`: This function is responsible for drawing the thief on the game board. The thief's appearance depends on its direction and animation frame.

16. Several `drawThief(Direction)` functions: These functions draw the thief facing different directions and with various animation frames. They are used by `drawThief()` to visually represent the thief's movement.

17. `drawMaze(Graphics2D g2d)`: This function draws the game maze on the screen, including walls and dots.

18. `initGame()`: This function initializes the game by setting up initial values for lives, score, and the level. It then calls `initLevel()` to set up the current level.

19. `initLevel()`: This function sets up the current level by copying level data, initializing ghost positions, and continuing the game.

20. `continueLevel()`: This function continues the current level by repositioning the ghosts and the thief, including their speeds.

21. `loadImages()`: This function loads the images used in the game, such as the thief character and the ghosts.

22. `paintComponent(Graphics g)`: This method is responsible for rendering the game's graphics and calls `doDrawing(g)` to paint the game components.

23. `doDrawing(Graphics g)`: This function handles the actual drawing of the game components, including the maze, thief, ghosts, and the intro screen.

24. `TAdapter` (Inner Class): This inner class extends `KeyAdapter` and handles keyboard input. It responds to key presses (arrow keys, 'S', 'P', and 'Esc') and adjusts the game state accordingly.

25. `actionPerformed(ActionEvent e)`: This method is part of the `ActionListener` interface and is called by the game's timer. It triggers the repainting of the game board for animation.

These functions collectively make up the logic for a game that involves a thief character navigating a maze, collecting dots, avoiding ghosts, and keeping score. The game is controlled using arrow keys and can be started and paused with specific keys.

# Demo Game

https://github.com/tisarji-team/The-Thief-Escaped/assets/48820052/b254c883-1ac2-4fee-9ca1-7901fabc56e0

# Develop Team By Tisarji-Team

![Credit1](https://github.com/tisarji-team/The-Thief-Escaped/assets/48820052/5af18e93-0a28-4291-b1fe-89e35b4d329c)

# Credit

- https://github.com/armin-reichert/pacman
- https://github.com/hecoding/Pac-Man
- https://zetcode.com/javagames/tetris/
