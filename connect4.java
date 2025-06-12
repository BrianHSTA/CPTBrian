//Brian Huang
//Connect 4 Game
//12 June 2025
//7.88
//Description: Simulates 2-player game, Connect 4, with additional requirements from client Mr. Cadawas.
import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


public class connect4{
	public static void main(String[] args){
		//Console con = new Console();
		
		int intInput;
		//READING DATA FROM LAST THEMES FOR THE GRAPHIC SETTINGS
		//INITIALIZE THEME VARIABLES
		TextInputFile themesIn = new TextInputFile("themes.txt");
		TextOutputFile themesOut = new TextOutputFile("themes.txt", true);
		String strNewThemeName;
		String strNewP1RGB;
		String strNewP2RGB;
		String strNewBoardRGB;
		String strNewTitle;
		int intThemesLength;
		int intThemesDisplay;
		//ASSIGN THEME VARIABLES
		TextInputFile lastthemeIn = new TextInputFile("lasttheme.txt");
		TextOutputFile lastthemeOut;
		String strThemeName = lastthemeIn.readLine();
		
		String strP1RGB = lastthemeIn.readLine();
		int intP1RGB[];
		intP1RGB = new int[3]; 
		intP1RGB = connect4methods.StrRGBtoIntRGB(strP1RGB);
		
		String strP2RGB = lastthemeIn.readLine();
		int intP2RGB[];
		intP2RGB = new int[3]; 
		intP2RGB = connect4methods.StrRGBtoIntRGB(strP2RGB);
		
		String strBoardRGB = lastthemeIn.readLine();
		int intBoardRGB[];
		intBoardRGB = new int[3]; 
		intBoardRGB = connect4methods.StrRGBtoIntRGB(strBoardRGB);
		
		String strTitle = lastthemeIn.readLine();
		
		//Create variable to change screens, starting on menu screen
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
		Console con = new Console("Connect 4", 700,700);
		BufferedImage imgLogo = con.loadImage("C4Logo.png");
		while (intScreen != 6){//Loop until user selects 6 (quit)
			if (intScreen == 0){
				//Draw the background
				con.setDrawColor(Color.WHITE);
				con.drawString("Connect 4", 275,140);
				con.drawString("Play Game (1)", 100,245);
				con.drawString("View Leaderboard (2)", 100,295);
				con.drawString("Load Theme (3)", 100, 345);
				con.drawString("Create Theme (4)", 100, 395);
				con.drawString("Help (5)", 100, 445);
				con.drawString("Quit (6)", 545, 600);
				
				con.drawImage(imgLogo, 130, -50);
				intScreen = connect4methods.getNum(con, 1, 6);//Get new screen
				connect4methods.clear(con);	
				System.out.println("Screen: "+intScreen);//Check inputted screen in terminal
			}
			
			
			
			else if (intScreen == 1){//If game selected
				//Ask P1 name
				con.setDrawColor(Color.WHITE);
				con.drawString("Player 1 Name:", 250,200);
				con.print("\n\n\n\n\n\n\n\n\n\n\n                        ");
				strP1Name = con.readLine();
				con.clear();
				connect4methods.clear(con);
				//Ask P2 name
				con.setDrawColor(Color.WHITE);
				con.drawString("Player 2 Name:", 250,200);
				con.print("\n\n\n\n\n\n\n\n\n\n\n                        ");
				strP2Name = con.readLine();
				con.clear();
				connect4methods.clear(con);
				//Set wins to zero
				intP1Wins = 0;
				intP2Wins = 0;
				//Run until bln is false when the user chooses not to play again
				blnLoop = true;
				while (blnLoop == true){
					//CREATE GRAPHICS
					//name
					con.setDrawColor(Color.WHITE);
					con.drawString("P1 "+strP1Name+" - wins: "+intP1Wins, 80,20);
					con.drawString("P2 "+strP2Name+" - wins: "+intP2Wins, 420,20);
					//board
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
						con.drawString("("+intCount+")",15+intCount*80, 65);
					}
					//title
					con.drawString(strTitle,300, 610);
					
					//BEGINNING OF CONNECT 4 GAME CODE
					intPlayer = 1;
					intMoveCount = 0;
					blnRun = true;
					//While game is not won or tied continue asking user for new pieces
					while (intMoveCount < 42 && blnRun == true){
						intMove = connect4methods.getNum(con, 1, 7);
						intMove--;
						intPieceCoordinate = connect4methods.PieceCoordinate(intBoard, intMove, intPlayer);
						//For if piece placement is invalid
						if (intPieceCoordinate[0] == -1 && intPieceCoordinate[1] == -1){
							con.setDrawColor(Color.WHITE);
							con.drawString("Invalid Move", 290, 650);
							con.repaint();
							con.sleep(300);
							con.setDrawColor(Color.BLACK);
							con.fillRect(289,650,220,40);
						}else{//If piece placement valid, place piece
							intBoard[intPieceCoordinate[0]][intPieceCoordinate[1]] = intPlayer;//Change the coordinate on array to player num 
							System.out.println("("+intPieceCoordinate[0]+","+intPieceCoordinate[1]+")");
							//Choose color for piece depending on player
							if (intPlayer == 1){
								con.setDrawColor(new Color(intP1RGB[0], intP1RGB[1], intP1RGB[2]));
							}else if (intPlayer == 2){
								con.setDrawColor(new Color(intP2RGB[0], intP2RGB[1], intP2RGB[2]));
							}
							con.fillOval(80+intPieceCoordinate[0]*80,120+intPieceCoordinate[1]*80,60,60);//Change board graphic depending on piece coordinate
							intPlayer = intPlayer%2+1;//Change to other player, 1 become 2, 2 becomes 1
							intMoveCount++;//Add one to move counter
						}
						//Check for win, if won stop asking for pieces
						if (connect4methods.CheckForWin(intBoard)==true){
							blnRun = false;
							}
					}
					connect4methods.clear(con);
					
					
					//Ask if players want to play again
					con.setDrawColor(Color.WHITE);
					if (blnRun==false){
						con.drawString("Player "+(intPlayer%2+1)+" wins!", 270,250);
						if ((intPlayer%2+1)==1){intP1Wins++;}
						else{intP2Wins++;}
						}
					else{con.drawString("Tie", 335,250);}
					con.drawString("Return to Menu (0)", 250,330);
					con.drawString("Play Again (1)", 270,360);
					intScreen = connect4methods.getNum(con, 0, 1);
					connect4methods.clear(con);
					intBoard = new int[7][6];
					if (intScreen == 0){blnLoop = false;}//If players choose menu, end game loop, if player chooses 1, while loop will loop game
				}
				//When quit, output leaderboard data to leaderboard file
				leaderboardOut.println(strP1Name);
				leaderboardOut.println(intP1Wins);
				leaderboardOut.println(strP2Name);
				leaderboardOut.println(intP2Wins);
			}
			
			
			
			else if (intScreen == 2){//View leaderboard
				leaderboardIn = new TextInputFile("leaderboards.txt");
				//Get file length for array
				intLeaderboardLength = connect4methods.FileLength(leaderboardIn);
				intLeaderboardLength = intLeaderboardLength/2;
				System.out.println("Leaderboard Length: "+intLeaderboardLength);
				leaderboardIn = new TextInputFile("leaderboards.txt");
				
				con.setDrawColor(Color.WHITE);
				con.drawString("LeaderBoard", 290, 100);
				con.drawString("Return to Menu (0)", 430, 580);
				//Put leaderboard file in an array the length of the file
				strLeaderboardData = new String[intLeaderboardLength][2];
				intCount = 0;
				while (leaderboardIn.eof() == false){
						strName = leaderboardIn.readLine();
						strWins = leaderboardIn.readLine();
						strLeaderboardData[intCount][0] = strName;
						strLeaderboardData[intCount][1] = strWins;
						intCount++;
					}
				//Sort leaderboard array and print
				strLeaderboardData = connect4methods.BubbleSort(strLeaderboardData,intLeaderboardLength);
				
				for (intCount=0;intCount<intLeaderboardLength;intCount++){System.out.println(strLeaderboardData[intCount][0]+"-"+strLeaderboardData[intCount][1]);}
				intLeaderboardDisplay = intLeaderboardLength;
				if (intLeaderboardDisplay > 8){intLeaderboardDisplay = 8;}//Does not exceed the top 8 names printed
				
				for (intCount=1;intCount<=intLeaderboardDisplay;intCount++){
					con.drawString(strLeaderboardData[intLeaderboardLength-intCount][0], 100, 120+intCount*50);
					con.drawString(strLeaderboardData[intLeaderboardLength-intCount][1], 580, 120+intCount*50);
				}
				intScreen = connect4methods.getNum(con, 0, 0);
				connect4methods.clear(con);
				}
			
			else if (intScreen == 3){
				themesIn = new TextInputFile("themes.txt");
				con.setDrawColor(Color.WHITE);
				con.drawString("Load Theme",290,100);
				con.drawString("Return to Menu (0)", 430, 580);
				//Get theme length
				intThemesLength = connect4methods.FileLength(themesIn);
				intThemesLength = intThemesLength/5;
				themesIn = new TextInputFile("themes.txt");
				//Draw the names of themes, no more than 8 themes displayed
				if (intThemesLength>8){intThemesLength=8;}
				for (intCount = 1;intCount<=intThemesLength;intCount++){
					con.drawString("("+intCount+") "+themesIn.readLine(),230,120+intCount*50);
					themesIn.readLine();
					themesIn.readLine();
					themesIn.readLine();
					themesIn.readLine();
				}
				//Allows reader to choose a theme, overrides that theme into lasttheme.txt to update current theme
				intInput = connect4methods.getNum(con, 0, intThemesLength);
				while (intInput!=0){
						
					if (intInput!=0){
						themesIn = new TextInputFile("themes.txt");
						lastthemeOut = new TextOutputFile("lasttheme.txt");
						for (intCount=0;intCount<intInput-1;intCount++){
							for (intCount2=0;intCount2<5;intCount2++){themesIn.readLine();}
						}
						for (intCount=0;intCount<5;intCount++){lastthemeOut.println(themesIn.readLine());}
						con.drawString("Updated Theme",275,530);
					}
					intInput = connect4methods.getNum(con, 0, intThemesLength);
					
				}
				lastthemeIn = new TextInputFile("lasttheme.txt");
				//Apply theme changes to theme variables
				strThemeName = lastthemeIn.readLine();
				strP1RGB = lastthemeIn.readLine();
				intP1RGB = connect4methods.StrRGBtoIntRGB(strP1RGB);
				strP2RGB = lastthemeIn.readLine(); 
				intP2RGB = connect4methods.StrRGBtoIntRGB(strP2RGB);
				strBoardRGB = lastthemeIn.readLine();
				intBoardRGB = connect4methods.StrRGBtoIntRGB(strBoardRGB);
				strTitle = lastthemeIn.readLine();
				
				intScreen = 0;
				connect4methods.clear(con);
				
			}
			else if (intScreen == 4){
				themesOut = new TextOutputFile("themes.txt", true);
				//Draw console and prompt for information
				con.setDrawColor(Color.WHITE);
				con.drawString("Create Theme", 290, 100);
				con.drawString("Theme Name: ", 150, 187);
				con.drawString("Player 1 Color: ", 150, 259);
				con.drawString("Player 2 Color: ", 150, 331);
				con.drawString("Board Color: ", 150, 403);
				con.drawString("Board Title: ", 150, 475);
				con.drawString("Return to Menu (0)", 430, 580);
				con.drawString("Example RGB Color Input: #,#,#", 185, 130);
				con.print("\n\n\n\n\n\n\n\n                             ");
				strNewThemeName = con.readLine();
				con.print("\n\n                             ");
				strNewP1RGB = con.readLine();
				con.print("\n\n                             ");
				strNewP2RGB = con.readLine();
				con.print("\n\n                             ");
				strNewBoardRGB = con.readLine();
				con.print("\n\n                             ");
				strNewTitle = con.readLine();
				//Transfer new theme's information to the bottom of the theme file
				themesOut.println(strNewThemeName);
				themesOut.println(strNewP1RGB);
				themesOut.println(strNewP2RGB);
				themesOut.println(strNewBoardRGB);
				themesOut.println(strNewTitle);
				con.drawString("Theme Successfully Added", 55, 580);
				intScreen = connect4methods.getNum(con, 0, 0);
				connect4methods.clear(con);
				con.clear();
				
			}
			else if (intScreen == 5){
				con.setDrawColor(Color.WHITE);
				//Display information for the help console when user chooses help in menu
				con.drawString("Help", 330, 100);
				con.println("\n\n\n\n\n\nTwo players have colored disks that they drop to the\nbottom of the Connect 4 board.To win the game,players\nmust aim to create a connection of 4 discs vertically,\nhorizontally, or diagonally while blocking the\nopponent from accomplishing the same.\n\nTip: control the center, create traps, predict opponents\nmoves, keep an eye on connections of 3.");
				con.drawString("Return to Menu (0)", 430, 580);
				intScreen = connect4methods.getNum(con, 0, 0);
				connect4methods.clear(con);
				con.clear();
			}
		}
		System.exit(0);//Quit program when user selects quit and ends Connect 4 game loop
	}
}

