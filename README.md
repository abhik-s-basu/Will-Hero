#  Will Hero Game

Adaptation of the popular game [Will Hero](https://apps.apple.com/us/app/will-hero/id1317231325) by [ZPlay](https://apps.apple.com/us/developer/zplay-beijing-info-tech-co-ltd/id531022725) for our [Advanced Programming Course (CSE201)](http://techtree.iiitd.edu.in/viewDescription/filename?=CSE201)

## 💻 Tech Stack

- Java
- JavaFX

## 💡 Design & Implementation

- A start menu opens up, which on tapping leads to the main menu which on tapping begins the game.
- Clicking on the mouse/trackpad will move the player forward and we can observe it is colliding with the orc and jumping accordingly.
- If an orc dies, or a chest opens we get a few coins which are shown on the top right corner.
- A game can be started by tapping on a play or choosing from the saved game which has been serialised.
- The hero can swap weapons and weapons have levels as well.
- The EndGame, PauseGame and GameWonMenu have been created using FXML and controller classes from Java as well.
- Sound has been added as well, to make the gameplay more immersive.

## 🖼️ Design & Implementation

- Fully functional game with with lots of additional features
- Audio assets have been used to replicate the original game to the maximum
- Revive Functionality for the Hero on his First Death based on Coins
- 2 Kinds of Weapons for the Hero
- Choice between 4 different Hero Helmets
- Auto generated player statistics for recap
- Custom implemented video recording which captures this screen only for a specified duration
- Ghost feature which can be used to play against your previous game
- Implemented Custom Exceptions and JavaFX Components by building on top of the standard tools

## 👀 Gameplay Design

- The game makes use of an animation timer, which is 60 frames per second and using that we control the timing of the events that transpire in our game.
- The obstacles are created using the GameObject constructor. This constructor even makes life easy while serialising since all parameters are passed in the Game Object and then the images are displayed.
- The collisions are handled using an event handler and make use of animation timers as well. We create 4 rectangles around our image views because of which the collision handling becomes comparatively easy. 

## 📚 Concepts Used

- Inheritance, Polymorphism and Abstract classes were used a lot.
- Extensive use of  java collection framework
- The serialisation was also used for designing the whole saved games paradigm.
- Implemented exception handling wherever possible

## 🎬 Visuals

Loading Screen -

![HomeScreen](mainScreen.jpg)
Game Play - 

![GamePlay](gamePlay.jpg)
Helmet Choices - 
![Helmet Choosing Menu](helmets.jpg)
Player Statistics Menu - 
![Player Statistics](PlayerStatistics.jpg)


## Designs 

UML Class Diagram -

![UML](https://github.com/abhik-s-basu/Will-Hero/blob/master/UML%20Diagrams/UMLClassDiagram.jpg)

Use Case Diagram - 

![UseCase](https://github.com/abhik-s-basu/Will-Hero/blob/master/UML%20Diagrams/UseCase.jpg)

Creators:

[Abhik](https://github.com/abhik-s-basu) & [Niranjan](https://github.com/nin-ran-jan)
