import arc.*;

public class connect4methods{
	
	public static int[][] updateBoard(int[][] intBoard, int intMove, int intPlayer){
		//Update the board by placing a piece in user inputted column
		//Check row by row in a column from bottom to top
	
		//If the row closest to the bottom is empty, place piece, return board
		int intCount = 5;
		while (intCount >= 0){
			if (intBoard[intMove][intCount] != 1 && intBoard[intMove][intCount] != 2){
				intBoard[intMove][intCount] = intPlayer;
				return intBoard;
			}else{
				intCount = intCount-1;
			}
		}
		//If no pieces are placed due to a full row, return an unchanged board
		//Note to self: Check if board is unchanged. If it is, do not move onto next player and output invalid move 
		return intBoard;
		
	}
	
	
	
}
