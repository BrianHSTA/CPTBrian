import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

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
		boolean blnRun;
		
		//Red
		blnRun = true;
		String strNum = "";
		while (blnRun == true){
			if (strRGB.substring(0,1).equals(",")){
				strRGB = strRGB.substring(1,strRGB.length());
				blnRun = false;
			}else{
				strNum = strNum+strRGB.substring(0,1);
				strRGB = strRGB.substring(1,strRGB.length());
			}			
		}
		intRGB[0] = Integer.parseInt(strNum);
		
		//Green
		blnRun = true;
		strNum = "";
		while (blnRun == true){
			if (strRGB.substring(0,1).equals(",")){
				strRGB = strRGB.substring(1,strRGB.length());
				blnRun = false;
			}else{
				strNum = strNum+strRGB.substring(0,1);
				strRGB = strRGB.substring(1,strRGB.length());
			}			
		}
		intRGB[1] = Integer.parseInt(strNum);
		
		//Blue
		intRGB[2] = Integer.parseInt(strRGB);
		return intRGB;
	}
	
	public static boolean CheckForWin(int[][] intBoard){
		//Check vertical column
		int intConnect = 0;
		
		int intColumn;
		int intRow;
		int intPlayer;
		int intAdjustment;
		boolean blnWin = false;
		
		//CHECKS VERTICAL CONNECTIONS
		//Per column
		for (intColumn = 0; intColumn <7; intColumn++){
			//Per row
			for (intRow = 0; intRow <3; intRow++){
				if (intBoard[intColumn][intRow] != 0 && intBoard[intColumn][intRow] == intBoard[intColumn][intRow+1] && intBoard[intColumn][intRow] == intBoard[intColumn][intRow+2] && intBoard[intColumn][intRow] == intBoard[intColumn][intRow+3]){
					return true;
					}
			}
		}
		//CHECKS HORIZONTAL CONNECTIONS
		for (intRow = 0;intRow<6;intRow++){
			for (intColumn = 0; intColumn<4; intColumn++){
				if (intBoard[intColumn][intRow] != 0 && intBoard[intColumn][intRow] == intBoard[intColumn+1][intRow] && intBoard[intColumn][intRow] == intBoard[intColumn+2][intRow] && intBoard[intColumn][intRow] == intBoard[intColumn+3][intRow]){
					return true;
				}
			}
		}
		
		//CHECKS DIAGONAL CONNECTIONS
		for (intColumn = 0;intColumn<4;intColumn++){
			for (intRow = 0;intRow<3;intRow++){
				if (intBoard[intColumn][intRow] != 0 && intBoard[intColumn][intRow] == intBoard[intColumn+1][intRow+1] && intBoard[intColumn][intRow] == intBoard[intColumn+2][intRow+2] && intBoard[intColumn][intRow] == intBoard[intColumn+3][intRow+3]){
					return true;
				}
			}
		}
		for (intColumn = 3;intColumn<7;intColumn++){
			for (intRow = 0;intRow<3;intRow++){
				if (intBoard[intColumn][intRow] != 0 && intBoard[intColumn][intRow] == intBoard[intColumn-1][intRow+1] && intBoard[intColumn][intRow] == intBoard[intColumn-2][intRow+2] && intBoard[intColumn][intRow] == intBoard[intColumn-3][intRow+3]){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void clear(Console con){
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,700,700);
	}
	
	public static String[][] BubbleSort(String[][] strArray, int intArrayLength){
		int intCount;
		int intCount2;
		String strNameTemp;
		String strWinsTemp;
		for (intCount = 0; intCount < intArrayLength-1; intCount++){
			for (intCount2 = 0; intCount2 < intArrayLength-1; intCount2++){
				if (Integer.parseInt(strArray[intCount2][1]) > Integer.parseInt(strArray[intCount2+1][1])){
					strNameTemp = strArray[intCount2][0];
					strArray[intCount2][0] = strArray[intCount2+1][0];
					strArray[intCount2+1][0] = strNameTemp;
					
					strNameTemp = strArray[intCount2][1];
					strArray[intCount2][1] = strArray[intCount2+1][1];
					strArray[intCount2+1][1] = strNameTemp;
				}
			}
		}
		return strArray;
	}
	public static int getNum(Console con, int min, int max){
		con.repaint();
		
		int intNum=max+1;
		while (intNum > max || intNum < min){
			intNum = con.getKey()-'0';
			if (intNum > max || intNum < min){
				con.repaint();
				con.setDrawColor(Color.WHITE);
				con.drawString("Invalid Input", 280, 650);
				//con.repaint();
				con.sleep(300);
				con.setDrawColor(Color.BLACK);
				con.fillRect(280,650,200,40);
				con.repaint();
				}
			}
		return intNum;
		
	}
	public static int FileLength(TextInputFile file){
		int intFileLength = 0;
		while (file.eof() == false){
				file.readLine();
				intFileLength++;
			}
		return intFileLength;
	}
	
}
