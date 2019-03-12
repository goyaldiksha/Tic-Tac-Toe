package ticTacToe;

import java.util.Scanner;

public class GameManager {
	private Player player1, player2;
	private CreateBoard board;
	
	//driver class
	public static void main(String args[]){
		GameManager t = new GameManager();
		System.out.println("TIC TAC TOE GAME!!!");
		t.startGame();
	}
	
	//start game
	public void startGame(){
		Scanner s = new Scanner(System.in);
		
		
		// Players' input
		player1 = takePlayerInput(1);
		player2 = takePlayerInput(2);
		while(player1.getSymbol() == player2.getSymbol()){
			System.out.println("Symbol Already taken !! Pick another symbol !!");
			char symbol = s.next().charAt(0);
			player2.setSymbol(symbol);
		}
		
		
		// Create Board
		board = new CreateBoard(player1.getSymbol(), player2.getSymbol());
		
		
		// Conduct the Game
		boolean player1Turn = true;
		int status = CreateBoard.INCOMPLETE;
		while(status == CreateBoard.INCOMPLETE || status == CreateBoard.INVALID){  
			if(player1Turn){
				System.out.println("Player 1 - " + player1.getName() + "'s turn");
				System.out.println("Enter x coordinate: ");
				int x = s.nextInt()-1;
				System.out.println("Enter y coordinate: ");
				int y = s.nextInt()-1;
				 status =  board.move(player1.getSymbol(), x, y);
				if(status != CreateBoard.INVALID){
					player1Turn = false;
					board.print();
				}
				else{
					System.out.println("Invalid Move !! Try Again !!");
				}
			}
			
			else{
				System.out.println("Player 2 - " + player2.getName() + "'s turn");
				System.out.println("Enter x coordinate: ");
				int x = s.nextInt()-1;
				System.out.println("Enter y coordinate: ");
				int y = s.nextInt()-1;
				status =  board.move(player2.getSymbol(), x, y);
				if(status != CreateBoard.INVALID){
					player1Turn = true;
					board.print();
				}
				else{
					System.out.println("Invalid Move !! Try Again !!");
				}				
			}
		}
	//check status
		if(status == CreateBoard.PLAYER_1_WINS){
			System.out.println("Player 1 - " + player1.getName() +" wins !!");
		}else if(status == CreateBoard.PLAYER_2_WINS){
			System.out.println("Player 2 - " + player2.getName() +" wins !!");
		}else{
			System.out.println("Draw !!");
		}
	}
	
	
	//take input of player's information
	private Player takePlayerInput(int num){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Player " + num + " name: ");
		String name = s.nextLine();
		System.out.println("Enter Player " + num + " symbol: ");
		char symbol = s.next().charAt(0);
		Player p = new Player(name, symbol);
		return p;	
	}
}
