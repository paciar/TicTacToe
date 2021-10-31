import java.util.*;
public class TicTacToe {
	
	static int userChoice;
	static String[][] grid;
	static String gameOverMessage = "Stalemate! Nobody wins!", winner = "none";
	static boolean startGame, running, playerOneTurn = true, playerTwoTurn = false;
	static Scanner sk = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe!");
		startGame = true;
		running = true;
		while (startGame) {
		    userPreferences();
		    startGame = false;
		}
		
		System.out.println("Player 1 uses 'X' and Player 2 uses 'O'. Player 1 goes first.");
		setUpGrid();
		printGrid();
		
		while (running) {
			if (userChoice == 1) { // vs human
				while (playerOneTurn) {
					playerOneInput();
					printGrid();
					playerOneTurn = false;
					playerTwoTurn = true;
				}
				checkIfGameOver("X");
				while (playerTwoTurn) {
					playerTwoInput();
					printGrid();
					playerTwoTurn = false;
					playerOneTurn = true;
				}
				checkIfGameOver("O");
			}
			
			if (userChoice == 2) { // vs computer
				while (playerOneTurn) {
					playerOneInput();
					printGrid();
					playerOneTurn = false;
					playerTwoTurn = true;
				}
				checkIfGameOver("X");
				while (playerTwoTurn) {
					botInput();
					printGrid();
					playerTwoTurn = false;
					playerOneTurn = true;
				}
				checkIfGameOver("O");
			}
		}
		System.out.println(gameOverMessage);
	}
	
	public static void userPreferences() { // finished method
		System.out.println("Enter 1 if you would like to play against another player or enter 2 if you would like to play against the computer: ");
		userChoice = sk.nextInt();
		if (userChoice != 1 && userChoice != 2) {
			System.out.println("Make sure you enter either 1 or 2. Try again: ");
			userChoice = sk.nextInt();
		}
	}
	
	public static void setUpGrid() { // finished method
		grid = new String[3][3];
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				grid[row][col] = " ";
			}
		}
	}
	
	public static void printGrid() { // finished method
		System.out.println("    1   2   3");
		for (int row = 0; row < grid.length; row++) {
			System.out.print(row+1 + " |");
			for (int col = 0; col < grid[0].length; col++) {
				System.out.print(" " + grid[row][col] + " |");
			}
			System.out.println();
			System.out.println("  -------------");
		}
	}
	
	public static void playerOneInput() { // finished method
		int row1, col1;
		boolean invalidInput = true;
		while (invalidInput) {
			System.out.println("Player 1, enter the row of the cell you want to mark: ");
			row1 = sk.nextInt() - 1;
			if (outOfBounds(row1)) {
				System.out.println("Make sure the row you entered is between 1 and 3. Try again: ");
				row1 = sk.nextInt() - 1;
			}
			System.out.println("Enter the column of the cell you want to mark: ");
			col1 = sk.nextInt() - 1;
			if (outOfBounds(col1)) {
				System.out.println("Make sure the column you entered is between 1 and 3. Try again: ");
				col1 = sk.nextInt() - 1;
			}
			if (grid[row1][col1].equals(" ")) {
				grid[row1][col1] = "X";
				invalidInput = false;
			}
			else {
				System.out.println("The cell you tried to mark is already marked. Select another cell and try again.");
				printGrid();
			}
		}
	}
	
	public static void playerTwoInput() { // finished method
		int row2, col2;
		boolean invalidInput = true;
		while (invalidInput) {
			System.out.println("Player 2, enter the row of the cell you want to mark: ");
			row2 = sk.nextInt() - 1;
			if (outOfBounds(row2)) {
				System.out.println("Make sure the row you entered is between 1 and 3. Try again: ");
				row2 = sk.nextInt() - 1;
			}
			System.out.println("Enter the column of the cell you want to mark: ");
			col2 = sk.nextInt() - 1;
			if (outOfBounds(col2)) {
				System.out.println("Make sure the column you entered is between 1 and 3. Try again: ");
				col2 = sk.nextInt() - 1;
			}
			if (grid[row2][col2].equals(" ")) {
				grid[row2][col2] = "O";
				invalidInput = false;
			}
			else {
				System.out.println("The cell you tried to mark is already marked. Select another cell and try again.");
				printGrid();
			}
		}
	}
	
	public static void botInput() { // finished method
		int numEmpty = getNumEmpty();
		ArrayList<Integer> emptyRowNums = new ArrayList<Integer>(numEmpty);
		ArrayList<Integer> emptyColNums = new ArrayList<Integer>(numEmpty);
		int randomIndex = (int)(Math.random()*numEmpty);
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col].equals(" ")) {
					emptyRowNums.add(row);
					emptyColNums.add(col);
				}
			}
		}
		int randomRow = emptyRowNums.get(randomIndex);
		int randomCol = emptyColNums.get(randomIndex);
		grid[randomRow][randomCol] = "O";
		emptyRowNums.remove(randomIndex);
		emptyColNums.remove(randomIndex);
	}
	
	public static int getNumEmpty() { // finished method
		int count = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col].equals(" "))
					count++;
			}
		}
		return count;
	}
	
	public static void checkIfGameOver(String mark) { // finished method
		for (int row = 0; row < grid.length; row++) { // checks if there are 3 in a row in rows
			if (grid[row][0].equals(mark) && grid[row][1].equals(mark) && grid[row][2].equals(mark)) {
				winner = mark;
				gameOver();
				return;
			}
		}
		for (int col = 0; col < grid[0].length; col++) { // checks if there are 3 in a row in cols
			if (grid[0][col].equals(mark) && grid[1][col].equals(mark) && grid[2][col].equals(mark)) {
				winner = mark;
				gameOver();
				return;
			}
		}
		if (grid[0][0].equals(mark) && grid[1][1].equals(mark) && grid[2][2].equals(mark)) { // checks 3 in a row in diagonals
			winner = mark;
			gameOver();
			return;
		}
		if (grid[0][2].equals(mark) && grid[1][1].equals(mark) && grid[2][0].equals(mark)) {
			winner = mark;
			gameOver();
			return;
		}
		if (getNumEmpty() == 0 && winner.equals("none")) {
			gameOver();
			return;
		}
	}
	
	public static void gameOver() { // finished method
		playerOneTurn = false;
		playerTwoTurn = false;
		running = false;
		if (winner.equals("X"))
			gameOverMessage = "Congratulations Player 1, You Win!";
		if (winner.equals("O"))
			gameOverMessage = "Congratulations Player 2, You Win!";
	}
	
	public static boolean outOfBounds(int x) { // finished method
		return (x < 0 || x > 2);
	}
}