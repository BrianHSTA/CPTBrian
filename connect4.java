import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


public class connect4{
	public static void main(String[] args){
		
		TextInputFile lasttheme = new TextInputFile("lasttheme.txt")
		String strThemeName = lasttheme.readline();
		String strPlayer1RGB = lasttheme.readline();
		String strPlayer2RGB = lasttheme.readline();
		String strBoardColor = lasttheme.readline();
		String strTitle = lasttheme.readline();
		
		
		Console con = new Console("Connect 4", 700,700)
		// set the background
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,700,700);
			
		
		
		
		
	}
}
