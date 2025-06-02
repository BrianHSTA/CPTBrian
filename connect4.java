import arc.*;

public class connect4{
	public static void main(String[] args){
	Console con = new Console();
	int intBoard[][];
	intBoard = new int[7][6];
	
	int intMove;
	int intPlayer = 1;
	int intNewBoard[][];
	int intMoveCount = 0;
	while (intMoveCount < 42){
	
		intMove = con.readInt();
		intMove = intMove-1;
		intNewBoard = connect4methods.updateBoard(intBoard, intMove, intPlayer);
		con.println(intNewBoard[0][0]);
		con.println(intNewBoard[0][1]);
		con.println(intNewBoard[0][2]);
		con.println(intNewBoard[0][3]);
		con.println(intNewBoard[0][4]);
		con.println(intNewBoard[0][5]);
		if ((intBoard[intMove][0] == intNewBoard[intMove][0]) && (intBoard[intMove][1] == intNewBoard[intMove][1]) && (intBoard[intMove][2] == intNewBoard[intMove][2]) && (intBoard[intMove][3] == intNewBoard[intMove][3]) && (intBoard[intMove][4] == intNewBoard[intMove][4]) && (intBoard[intMove][5] == intNewBoard[intMove][5])){
			con.println("Invalid move, go again");
		}else{
			intBoard = intNewBoard;
			intPlayer = intPlayer%2+1;
		}
	}
		
	}
}
