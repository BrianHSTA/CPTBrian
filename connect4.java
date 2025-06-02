import arc.*;

public class connect4{
	public static void main(String[] args){
	Console con = new Console();
	//[x][y]
	int intBoard[][];
	intBoard = new int[7][6];
	
	int intPieceCoordinate[];
	intPieceCoordinate = new int[2];
	
	int intMove = 0;
	int intPlayer = 1;
	int intNewBoard[][];
	int intMoveCount = 0;

	while (intMoveCount <= 42){
	
		intMove = con.readInt();
		intMove--;
		intPieceCoordinate = connect4methods.intPieceCoordinate(intBoard, intMove, intPlayer);
		
		int intTestCount;
		for (intTestCount = 0; intTestCount <= 6; intTestCount++){
			con.println(intBoard[intTestCount][0]+" - "+intBoard[intTestCount][1]+" - "+intBoard[intTestCount][2]+" - "+intBoard[intTestCount][3]+" - "+intBoard[intTestCount][4]+" - "+intBoard[intTestCount][5]);
		}

		//con.println(intNewBoard[intMove][0]);
		//con.println(intNewBoard[intMove][1]);
		//con.println(intNewBoard[intMove][2]);
		//con.println(intNewBoard[intMove][3]);
		//con.println(intNewBoard[intMove][4]);
		//con.println(intNewBoard[intMove][5]);
		
		if (intPieceCoordinate[0] == -1 && intPieceCoordinate[1] == -1){
			con.println("Invalid move, go again");
		}else{
			intBoard[intPieceCoordinate[0]][intPieceCoordinate[1]] = intPlayer;
			intPlayer = intPlayer%2+1;
			intMoveCount++;
		}
	
	}
		
	}
}
