import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


public class connect4{
	public static void main(String[] args){
		//Console con = new Console();
		
		//READING DATA FROM LAST THEMES FOR THE GRAPHIC SETTINGS
		TextInputFile themesIn = new TextInputFile("themes.txt");
		TextOutputFile themesOut = new TextOutputFile("themes.txt");
		TextInputFile lasttheme = new TextInputFile("lasttheme.txt");
		String strThemeName = lasttheme.readLine();
		
		String strPlayer1RGB = lasttheme.readLine();
		int intP1RGB[];
		intP1RGB = new int[3]; 
		intP1RGB = connect4methods.StrRGBtoIntRGB(strPlayer1RGB);
		
		String strPlayer2RGB = lasttheme.readLine();
		int intP2RGB[];
		intP2RGB = new int[3]; 
		intP2RGB = connect4methods.StrRGBtoIntRGB(strPlayer2RGB);
		
		String strBoardRGB = lasttheme.readLine();
		int intBoardRGB[];
		intBoardRGB = new int[3]; 
		intBoardRGB = connect4methods.StrRGBtoIntRGB(strBoardRGB);
		
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
		boolean blnLoop;
		String strP1Name;
		String strP2Name;
		int intP1Wins;
		int intP2Wins;
		
		//Other Variables
		TextOutputFile leaderboardOut = new TextOutputFile("leaderboards.txt", true);
		TextInputFile leaderboardIn = new TextInputFile("leaderboards.txt");
		int intDisplayedNames = 6;
		String strName;
		String strWins;
		String strLeaderboardData[][];
		int intLeaderboardLength;
		int intLeaderboardDisplay;
		//BEGINNING OF CONSOLE
		Console con = new Console(strTitle, 700,700);
		BufferedImage imgLogo = con.loadImage("C4Logo.png");
		while (intScreen != 5){
			if (intScreen == 0){
				//set the background
				//con.setDrawColor(Color.BLACK);
				//con.fillRect(0,0,700,700); first 2 numbers are point one, 2nd nums are increase from point 1
				con.setDrawColor(Color.WHITE);
				con.drawString("Connect 4", 275,140);
				con.drawString("Play Game (1)", 100,245);
				con.drawString("View Leaderboard (2)", 100,295);
				con.drawString("Load Theme (3)", 100, 345);
				con.drawString("Create Theme (4)", 100, 395);
				con.drawString("Quit (5)", 545, 600);
				
				con.drawImage(imgLogo, 130, -50);
				intScreen = connect4methods.getNum(con, 0, 5);
				//System.out.println(intScreen);
				connect4methods.clear(con);	
			}
			
			
			
			else if (intScreen == 1){
				
				con.setDrawColor(Color.WHITE);
				con.drawString("Player 1 Name:", 250,200);
				con.print("\n\n\n\n\n\n\n\n\n\n\n                        ");
				strP1Name = con.readLine();
				con.clear();
				connect4methods.clear(con);
				con.setDrawColor(Color.WHITE);
				con.drawString("Player 2 Name:", 250,200);
				con.print("\n\n\n\n\n\n\n\n\n\n\n                        ");
				strP2Name = con.readLine();
				con.clear();
				connect4methods.clear(con);
				
				intP1Wins = 0;
				intP2Wins = 0;
				blnLoop = true;
				while (blnLoop == true){
					con.setDrawColor(Color.WHITE);
					con.drawString("Player 1 - "+strP1Name, 80,20);
					con.drawString("Player 2 - "+strP2Name, 420,20);
				
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
					con.setDrawColor(Color.WHITE);
					for (intCount = 1;intCount < 8;intCount++){
						con.drawString("("+intCount+")",15+intCount*80, 610);
					}
					
					//BEGINNING OF CONNECT 4 CODE
					intPlayer = 1;
					intMoveCount = 0;
					blnRun = true;
					

					while (intMoveCount < 42 && blnRun == true){
						intMove = connect4methods.getNum(con, 1, 7);
						intMove--;
						intPieceCoordinate = connect4methods.PieceCoordinate(intBoard, intMove, intPlayer);
						
						if (intPieceCoordinate[0] == -1 && intPieceCoordinate[1] == -1){
							con.println("Invalid move, go again");
						}else{
							intBoard[intPieceCoordinate[0]][intPieceCoordinate[1]] = intPlayer;
							//con.println(intPieceCoordinate[0]+","+intPieceCoordinate[1]);
							if (intPlayer == 1){
								con.setDrawColor(new Color(intP1RGB[0], intP1RGB[1], intP1RGB[2]));
							}else if (intPlayer == 2){
								con.setDrawColor(new Color(intP2RGB[0], intP2RGB[1], intP2RGB[2]));
							}
							con.fillOval(80+intPieceCoordinate[0]*80,120+intPieceCoordinate[1]*80,60,60);
							intPlayer = intPlayer%2+1;
							intMoveCount++;
						}
						if (connect4methods.CheckForWin(intBoard)==true){
							blnRun = false;
							}
					}
					connect4methods.clear(con);
					
					
					//Ask if players want to play again
					con.setDrawColor(Color.WHITE);
					if (blnRun==false){
						con.drawString("Player "+(intPlayer%2+1)+" wins!", 200,100);
						if ((intPlayer%2+1)==1){intP1Wins++;}
						else{intP2Wins++;}
						}
					else{con.drawString("Tie", 200,100);}
					con.drawString("Return to Menu (0)", 200,180);
					con.drawString("Play Again (1)", 200,230);
					intScreen = connect4methods.getNum(con, 0, 1);
					connect4methods.clear(con);
					intBoard = new int[7][6];
					if (intScreen == 0){blnLoop = false;}//If players choose menu, end game loop
				}
				leaderboardOut.println(strP1Name);
				leaderboardOut.println(intP1Wins);
				leaderboardOut.println(strP2Name);
				leaderboardOut.println(intP2Wins);
			}
			
			
			
			else if (intScreen == 2){
				leaderboardIn = new TextInputFile("leaderboards.txt");
				intLeaderboardLength = connect4methods.FileLength(leaderboardIn);
				System.out.println(intLeaderboardLength);
				intLeaderboardLength = intLeaderboardLength/2;
				System.out.println(intLeaderboardLength);
				leaderboardIn = new TextInputFile("leaderboards.txt");
				
				con.setDrawColor(Color.WHITE);
				con.drawString("LeaderBoard", 290, 100);
				con.drawString("Return to Menu (0)", 430, 600);
				
				strLeaderboardData = new String[intLeaderboardLength][2];
				intCount = 0;
				while (leaderboardIn.eof() == false){
						strName = leaderboardIn.readLine();
						strWins = leaderboardIn.readLine();
						strLeaderboardData[intCount][0] = strName;
						strLeaderboardData[intCount][1] = strWins;
						intCount++;
					}
				strLeaderboardData = connect4methods.BubbleSort(strLeaderboardData,intLeaderboardLength);
				
				//for (intCount=0;intCount<intLeaderboardLength;intCount++){con.println(strLeaderboardData[intCount][0]+"-"+strLeaderboardData[intCount][1]);}
				intLeaderboardDisplay = intLeaderboardLength;
				if (intLeaderboardDisplay > 8){intLeaderboardDisplay = 8;}
				
				for (intCount=1;intCount<=intLeaderboardDisplay;intCount++){
					con.drawString(strLeaderboardData[intLeaderboardLength-intCount][0], 100, 120+intCount*50);
					con.drawString(strLeaderboardData[intLeaderboardLength-intCount][1], 580, 120+intCount*50);
				}
				System.out.println(intLeaderboardLength+"-"+strLeaderboardData[0][0]+"-"+strLeaderboardData[0][1]);


				intScreen = connect4methods.getNum(con, 0, 0);
				connect4methods.clear(con);

				}
			
			else if (intScreen == 3){
				
				
			}
		}
		con.repaint();
		leaderboardOut.close();
		leaderboardIn.close();
		lasttheme.close();
	}
}

