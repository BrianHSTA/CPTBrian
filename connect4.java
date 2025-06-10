import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


public class connect4{
	public static void main(String[] args){
		//Console con = new Console();
		
		//READING DATA FROM LAST THEMES FOR THE GRAPHIC SETTINGS
		TextInputFile lasttheme = new TextInputFile("lasttheme.txt");
		String strThemeName = lasttheme.readLine();
		
		String strPlayer1RGB = lasttheme.readLine();
		int intPlayer1RGB[];
		intPlayer1RGB = new int[3]; 
		intPlayer1RGB = connect4methods.StrRGBtoIntRGB(strPlayer1RGB);
		
		String strPlayer2RGB = lasttheme.readLine();
		int intPlayer2RGB[];
		intPlayer2RGB = new int[3]; 
		intPlayer2RGB = connect4methods.StrRGBtoIntRGB(strPlayer2RGB);
		
		String strBoardRGB = lasttheme.readLine();
		int intBoardRGB[];
		intBoardRGB = new int[3]; 
		intBoardRGB = connect4methods.StrRGBtoIntRGB(strPlayer1RGB);
		
		String strTitle = lasttheme.readLine();
		
		
		
		//0 = menu, 1 = play game, 2 = leaderboard, 3 = load theme, 4 = quit
		int intScreen = 0;
		int intCount;
		int intCount2;
		
		
		//INITIALIZE CONNECT4 GAME VARIABLES
		int intBoard[][];
		intBoard = new int[7][6];
		int intPieceCoordinate[];
		intPieceCoordinate = new int[2];
		int intMove;
		int intPlayer;
		int intMoveCount;
		boolean blnRun;
		
		//BEGINNING OF CONSOLE
		Console con = new Console(strTitle, 700,700);
		BufferedImage imgLogo = con.loadImage("C4Logo.png");
		while (intScreen != 4){
			if (intScreen == 0){
				//set the background
				//con.setDrawColor(Color.BLACK);
				//con.fillRect(0,0,700,700); first 2 numbers are point one, 2nd nums are increase from point 1
				con.setDrawColor(Color.WHITE);
				con.drawString("Connect 4", 275,140);
				con.drawString("Play Game (1)", 100,245);
				con.drawString("View Leaderboard (2)", 100,295);
				con.drawString("Load Theme (3)", 100, 345);
				con.drawString("Quit (4)", 545, 600);
				
				con.drawImage(imgLogo, 130, -50);
				con.repaint();
				intScreen = con.getKey()-'0'; //Since '1' = 49 and '0' = 48 on ASCII table
				//System.out.println(intScreen);
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,700,700);
			}else if (intScreen == 1){
				intCount = 0;//y
				intCount2 = 0;//x
				con.setDrawColor(new Color(intBoardRGB[0], intBoardRGB[1], intBoardRGB[2]));
				con.fillRect(70,110,560,480);
				
				con.setDrawColor(Color.BLACK);
				for (intCount = 0; intCount < 6; intCount++){
					for (intCount2 = 0; intCount2 < 7; intCount2++){
						con.fillOval(80+intCount2*80,120+intCount*80,60,60);
					}
				}
				
				//BEGINNING OF CONNECT 4 CODE
				intPlayer = 1;
				intMoveCount = 0;
				blnRun = true;
				
				
				
				while (intMoveCount <= 42 && blnRun == true){
					con.repaint();
					intMove = con.getKey()-'0';
					intMove--;
					intPieceCoordinate = connect4methods.PieceCoordinate(intBoard, intMove, intPlayer);
					
					if (intPieceCoordinate[0] == -1 && intPieceCoordinate[1] == -1){
						con.println("Invalid move, go again");
					}else{
						intBoard[intPieceCoordinate[0]][intPieceCoordinate[1]] = intPlayer;
						con.println(intPieceCoordinate[0]+","+intPieceCoordinate[1]);
						if (intPlayer == 1){
							con.setDrawColor(new Color(intPlayer1RGB[0], intPlayer1RGB[1], intPlayer1RGB[2]));
						}else if (intPlayer == 2){
							con.setDrawColor(new Color(intPlayer2RGB[0], intPlayer2RGB[1], intPlayer2RGB[2]));
						}
						con.fillOval(80+intPieceCoordinate[0]*80,120+intPieceCoordinate[1]*80,60,60);
						intPlayer = intPlayer%2+1;
						intMoveCount++;
					}
					if (connect4methods.blnCheckForWin(intBoard)==true){
						blnRun = false;
						con.println("Player "+(intPlayer%2+1)+" wins!");
						}
				}
				intScreen = con.getKey()-'0';
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,700,700);
				intBoard = new int[7][6];
			}
			
			con.repaint();
		}
		
		
	}
}
