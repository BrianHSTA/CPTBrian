import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


public class connect4{
	public static void main(String[] args){
		//Console con = new Console();
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
		
		
		
		//0 = menu, 1 = play game, 2 = leaderboard, 3 = load theme, 4 == quit
		int intScreen = 0;
		
		Console con = new Console(strTitle, 700,700);
		BufferedImage imgLogo = con.loadImage("C4Logo.png");
		while (intScreen != 4){
			if (intScreen == 0){
				//set the background
				//con.setDrawColor(Color.BLACK);
				//con.fillRect(0,0,700,700);
				con.setDrawColor(Color.WHITE);
				con.drawString("Connect 4", 275,140);
				con.drawString("Play Game (1)", 100,245);
				con.drawString("View Leaderboard (2)", 100,295);
				con.drawString("Load Theme (3)", 100, 345);
				con.drawString("Quit (4)", 545, 600);
				
				con.drawImage(imgLogo, 130, -50);
				intScreen = con.readInt();
			}else if (intScreen == 1){
				con.setDrawColor(new Color(intBoardRGB[0], intBoardRGB[1], intBoardRGB[2]));
				con.fillRect(50,75,600,550);
				
			}
			
			con.repaint();
		}
		
		
	}
}
