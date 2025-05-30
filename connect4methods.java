import arc.*;

public class connect4methods{
	
	public static int[][] updateBoard(int[][] intBoard, int intDrop, int intPlayerNum){
		//Update the board by placing a piece in user inputted column
		//Check row by row in a column from bottom to top
		
		//If the row closest to the bottom is empty, place piece, return board
		int intCount = 6;
		while (intCount > 0){
			if (intBoard[intDrop][6-intCount] == 0){
				intBoard[intDrop][6-intCount] = intPlayerNum;
				intPlayerNum = (intPlayerNum%2)+1;
				return intBoard;
			}else{
				intCount--;
			}
		}
		//If no pieces are placed due to a full row, return an unchanged board
		//Note to self: Check if board is unchanged. If it is, do not move onto next player and output invalid move 
		if (intCount == 0){
			return intBoard;
		}
		
	}
	
}
