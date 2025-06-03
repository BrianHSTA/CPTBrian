import arc.*;

public class connect4methods{
	
	public static int[] PieceCoordinate(int[][] intBoard, int intMove, int intPlayer){
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
	
	public static int[] StrRGBtoIntRGB(String strRGB){
		int intRGB[];
		intRGB = new int[3];
		String strNum;
		boolean blnRun;
		
		int intCount;
		
		for (intCount = 0; intCount<3; intCount++){
			blnRun = true;
			while (blnRun == true){
				if (strRGB.substring(0,1)==","){
					strRGB = strRGB.substring(1,strRGB.length());
					blnRun = false;
				}else{
					strRGB = strRGB.substring(1,strRGB.length());
				}
			}
		}
	}
	
	
}

