import arc.*;

public class connect4methods{
	
	public static int[] intPieceCoordinate(int[][] intBoard, int intMove, int intPlayer){
		int intPieceCoordinate[];
		intPieceCoordinate = new int[2];
		int intCount = 5;
			//Check row by row in a column from bottom to top
		//If the row closest to the bottom is empty, return coordinate of empty slot
		while (intCount >= 0){
			if (intBoard[intMove][intCount] != 1 && intBoard[intMove][intCount] != 2){
				intPieceCoordinate[0] = intMove;
				intPieceCoordinate[1] = intCount;
				return intPieceCoordinate;
			}else{
				intCount = intCount-1;
			}
		}
		//If no available placement in the column return negative coordinates
		//Note to self: Check if board is unchanged. If it is, do not move onto next player and output invalid move 
		intPieceCoordinate[0] = -1;
		intPieceCoordinate[1] = -1;
		return intPieceCoordinate;
		
	}
	
	
	
}
