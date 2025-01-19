# Problem Statement

Ref: [Tic Tac Toe](https://workat.tech/machine-coding/practice/design-tic-tac-toe-smyfi9x064ry)  

Let's look at the game of Tic Tac Toe. Tic Tac Toe is a two-player strategy game played on a 3x3 grid. There are 9 empty cells and 9 pieces - 5 pieces of 'X' and 4 pieces of 'O'. The game starts with an empty grid.


## Rules of the Game
1. The game is played between two players. One player owns the X pieces and can put it on any of the empty cells in the grid. The other player owns the O pieces and can put it on any of the empty cells.
2. The player with X makes the first turn. Each player plays alternately after that.
3. The first player to form a horizontal, vertical, or diagonal sequence wins.

## Requirements
Create a command-line application for Tic Tac Toe with the following functionality:
1. **Initialize Game**:
   - Ask the user for the names of the two players.
   - Print the grid after initializing.

2. **Gameplay**:
   - Allow the user to make moves on behalf of both players by entering the cell position.
   - Determine the piece (X/O) and make the move if it is valid.

3. **Valid Move**:
   - The piece is controlled by the player having the current turn.
   - The cell is empty.

4. **Invalid Move**:
   - Print `Invalid Move`.
   - The same player plays again.

5. **Valid Move**:
   - Place the piece on the cell.
   - Print the board after the move.

6. **Game End**:
   - Determine if a player has won or if there are no valid moves left.
   - Ignore all moves mentioned after the game ends.

## Position Representation
A position in the cell is represented by two values: 
- **Row Number** (1-3)
- **Column Number** (1-3)

Examples:
- `3 1` represents the cell at the extreme bottom-left (3rd row, 1st column).
- `1 3` represents the cell at the extreme top-right (1st row, 3rd column).


## Input/Output Format
### **Input Format**
- Multiple lines, each containing a cell position in the format: `row column`.
- Stop taking the input when you encounter the word `exit`.

### **Output Format**
1. Print the initial grid.
2. Print the grid after each valid move.
3. For an invalid move, print `Invalid Move` (do not print the grid).
4. When a player wins, print: `Player_Name won the game`.
5. When no valid moves are left, print `Game Over`.

### Board Representation
- `X` and `O` represent the pieces.
- A hyphen (`-`) represents an empty cell.  

The board needs to be printed in the following format based on the respective position:
```
X X O
- - X
O O -
```

### Initial Board
The initial position of the board would be
```
- - -
- - -
- - -
```

## Examples
### **Sample Input 1**
```
X Gaurav
O Sagar
2 2
1 3
1 1
1 2
2 2
3 3
exit
```

### **Expected Output 1**
```
- - -
- - -
- - -
- - -
- X -
- - -
- - O
- X -
- - -
X - O
- X -
- - -
X O O
- X -
- - -
Invalid Move
X O O
- X -
- - X
Gaurav won the game
```

### **Sample Input 2**
```
X Gaurav
O Sagar
2 3
1 2
2 2
2 1
1 1
3 3
3 2
3 1
1 3
exit
```

### **Expected Output 2**
```
- - -
- - -
- - -
- - -
- - X
- - -
- O -
- - X
- - -
- O -
- X X
- - -
- O -
O X X
- - -
X O -
O X X
- - -
X O -
O X X
- - O
X O -
O X X
- X O
X O X
O X X
O X O
Game Over
```

### **Sample Input 3**
```
X Gaurav
O Sagar
exit
```

### **Expected Output 3**
```
- - -
- - -
- - -
```

## Expectations
1. **Code Quality**:
   - Functional correctness is a priority.
   - Code should be modular and readable.
   - Address separation of concerns.
   - Avoid writing everything in a single file (if not coding in C/C++).
   - Ensure easy testability from the main method.

2. **Extensibility**:
   - Allow changes to the grid size with minimal effort.
   - Support additional types of pieces.
   - Accommodate more than two players or piece types.

3. **[Optional]**:
   - Write unit tests if possible.
   - No need to create a GUI.