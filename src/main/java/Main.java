/*
Create a simple tic-tac-toe game. Depending on your skills and knowledge.

Game grid should be 3x3. It should be possible for the user to put values in the grid by typing row number and column number.



Easy: Ask user for row and column and write in the two dimensional array a value "1" in the correct place.

Check whether or not the row chosen by user contains all 1.

If all elements in row contain 1, then let player know they won.

Medium: Ask for row and column and write in the two dimensional array a value "1" or "2" in the correct place, depending on which players turn it is. Switch the turn after every move.

Check whether or not the row chosen by user contains all 1, or all 2.

Check whether or not the column chosen by user contains all 1, or all 2.

(1 represents X, 2 represents O, 0 represents empty)

Hard: A regular tic-tac-toe, check diagonals as well. Instead of 1 and 0, use X and O (2D array should be of char type.)

After every move make sure the playing field is not full and make it draw, if it is full.


*/
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final char PLAYER_A_MARKER = 'X';
    final char PLAYER_B_MARKER = 'O';
    int gameGridSize = 3;
    char[][] gameGrid = new char[gameGridSize][gameGridSize];
    gameGrid = createGameGrid(gameGridSize);
    int counter = 0;
    String player;
    char playerMark;
    
    System.out.println("Welcome to the game of Tic-Tac-Toe!");
    System.out.println("Player A, you play " + PLAYER_A_MARKER + ", please enter your name: ");
    String playerA = scanner.nextLine();
    System.out.println("Player B, you play  " + PLAYER_B_MARKER + ", please enter your name: ");
    String playerB = scanner.nextLine();
    System.out.println("Welcome, "+playerA+" and "+ playerB+"! Let the game begin!\n");
    printGameGrid(gameGrid);
    
    while(checkGridFull(gameGrid) == false){
      if(counter % 2 == 0){
        player = playerA;
        playerMark = PLAYER_A_MARKER;
      } else{
        player = playerB;
        playerMark = PLAYER_B_MARKER;
      }
      
      System.out.println(player + ", your turn. You play " + playerMark);
      
              
      int[] coordinates = getCoordinates(scanner);
      if (checkFreePlace(gameGrid, coordinates[0], coordinates[1])){
        gameGrid[coordinates[0]][coordinates[1]] = playerMark;
        printGameGrid(gameGrid);
          
        if(checkGameWin(gameGrid, coordinates, playerMark)){
          System.out.println("\n"+player + " is a winner!");
          break;
        } else if(checkGridFull(gameGrid)){
          System.out.println("\nThe game is a draw!");
          break;
        }
                  
        counter++;
      } else{
        System.out.println("This place is already taken. Please try again.");
      }
      
    }

    System.out.println("Game is ower! Than you for playing!");
       
    scanner.close();
  }
  public static char[][] createGameGrid(int gameGridSize){
    char[][] grid = new char[gameGridSize][gameGridSize];
    for (int i = 0; i < gameGridSize; i++) {
      for (int j = 0; j < gameGridSize; j++) {
        grid[i][j] = ' ';
        
      }
    }
    return grid;
  }

  public static void printGameGrid(char[][] gameGrid){
    System.out.println();
    for (int i = 0; i < gameGrid.length; i++) {
      for (int j = 0; j < gameGrid[i].length; j++) {
        System.out.print(" " + gameGrid[i][j] + " ");
        if(j<gameGrid[i].length-1){
          System.out.print("|");
        }
      }
      System.out.println();
      if(i<gameGrid.length-1){
        System.out.println("-".repeat(gameGrid[i].length*4-1));
      }

    }
    System.out.println();
  }

  public static int[] getCoordinates(Scanner scanner){
    int[] coordinates = new int[2];
    System.out.print("Enter  row number: ");
    coordinates[0] = scanner.nextInt();
    System.out.print("Enter column number: ");
    coordinates[1] = scanner.nextInt();
    return coordinates;
  }

  public static boolean checkFreePlace(char[][] gameGrid, int row, int column){
    if(gameGrid[row][column] == ' '){
      return true;
    } else{
      return false;
    }

  }

  public static boolean checkGridFull(char[][] grid){
    for (int i = 0; i < grid.length; i++){
      for (int j = 0; j < grid[i].length; j++){
        if(grid[i][j] == ' '){
          return false;
        }
      }
    }    
    return true;
  }

  public static boolean checkGameWin(char[][] gameGrid, int[] coordinates, char marker){
    if (checkRow(gameGrid, coordinates[0], marker) || checkColumn(gameGrid, coordinates[1], marker) || checkDiagonal(gameGrid, marker)){
      return true;
    } else {
      return false;
    }
    
  }

  
  public static boolean checkRow(char[][] gameGrid, int row, char mark){
    for(int i = 0; i < gameGrid.length; i++){
      if(gameGrid[row][i] != mark){
        return false;
      }
    }
    return true;
  }
  public static boolean checkColumn(char[][] gameGrid, int column, char mark){
    for(int i = 0; i < gameGrid[0].length; i++){
      if(gameGrid[i][column] != mark){
        return false;
      }
    }
    return true;
  }
  
  public static boolean checkDiagonal (char[][] gameGrid, char mark){
    boolean diagonal1 = true;
    boolean diagonal2 = true;
    for(int i = 0; i < gameGrid.length; i++){
      if(gameGrid[i][i] != mark){
        diagonal1 = false;
      }
      if(gameGrid[i][gameGrid.length-1-i] != mark){
        diagonal2 = false;
      }
    }
    return diagonal1 || diagonal2;
  }
  
}
