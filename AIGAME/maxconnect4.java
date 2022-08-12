import java.util.Scanner;

/**
 * 
 * @author James Spargo
 * This class controls the game play for the Max Connect-Four game. 
 * To compile the program, use the following command from the maxConnectFour directory:
 * javac *.java
 *
 * the usage to run the program is as follows:
 * ( again, from the maxConnectFour directory )
 *
 *  -- for interactive mode:
 * java MaxConnectFour interactive [ input_file ] [ computer-next / human-next ] [ search depth]
 *
 * -- for one move mode
 * java maxConnectFour.MaxConnectFour one-move [ input_file ] [ output_file ] [ search depth]
 * 
 * description of arguments: 
 *  [ input_file ]
 *  -- the path and filename of the input file for the game
 *  
 *  [ computer-next / human-next ]
 *  -- the entity to make the next move. either computer or human. can be abbreviated to either C or H. This is only used in interactive mode
 *  
 *  [ output_file ]
 *  -- the path and filename of the output file for the game.  this is only used in one-move mode
 *  
 *  [ search depth ]
 *  -- the depth of the minimax search algorithm
 * 
 *   
 */

 /***********************************************************************************************
	 * Name     	 	:  Sallagonda Avinash Reddy
	 * Student ID 		:  1002034491
	 ************************************************************************************************/


public class maxconnect4
{
	public static void fun(GameBoard currentGame){
		Scanner sc = new Scanner(System.in);		
		System.out.println("please enter any number from 1 to 7");
		int k = sc.nextInt();
		while(!currentGame.playPiece(k-1))
		{
			System.out.println("Invalid input, please enter any value between 1 to 7");
			Scanner sc1 = new Scanner(System.in);
			k = sc1.nextInt();
		}
    return;
	}
	public static String swap(String p1,String p2){
		String temp="";
		temp=p1;
		p1=p2;
		p2=temp;
		return p1;
	}

	public static void def1(GameBoard currentGame){
		currentGame.printGameBoard();			
		// print the current scores
	}

	public static String score(GameBoard currentGame,String p1,String p2){
		System.out.println( "Score:" + p1 + "(1)  = " + currentGame.getScore( 1 ) + ",  " +  p2 + "(2)   = " + currentGame.getScore( 2 ) + "\n " );
	    return p1;
	}
	public static String human(GameBoard currentGame,String p1,String p2){
		currentGame.printGameBoardToFile( "human.txt" );
		System.out.println("The state of the gave after human move is");
		def1(currentGame);	
		String op=score(currentGame,p1,p2);
		return p1;
	}
	public static void print(GameBoard currentGame,int py){
		System.out.println("Move so far " + currentGame.getPieceCount()  + ":  by computer " + "column " + (py+1));
		System.out.print("The game state after move by computer is:\n");
	}
	public static void main(String[] args) 
	{
		// check for the correct number of arguments
		if( args.length != 4 ) 
		{
			System.out.println("Four command-line arguments are needed:\n"
					+ "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
					+ " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

			exit_function( 0 );
		}
		String game_mode = args[0].toString();				
		String input = args[1].toString();				
		int depthLevel = Integer.parseInt( args[3] );  		
		GameBoard currentGame = new GameBoard( input );
		AiPlayer c = new AiPlayer(depthLevel);
		int py = 99;		
		if( game_mode.equalsIgnoreCase( "interactive" ) ) 
		{
			System.out.print("\nMaxConnect-4 game\n");
			System.out.print("The game state before move:\n");		
			String nextPlay = args[2].toString();
			if(nextPlay.equalsIgnoreCase("human-next"))
			{
				String t="";
				String p1 = "human";
				String p2 = "computer";
				if(currentGame.getCurrentTurn() == 2) 
				{
					t=p1;p1=p2;p2=t;
				}
				currentGame.printGameBoard();
				String op=score(currentGame,p1,p2);
				// 6*7 grid
				while(currentGame.getPieceCount() < 42) 
				{
			        fun(currentGame);
					String op1=human(currentGame,p1,p2);
					py = c.findBestPlay(currentGame);
					currentGame.playPiece(py);
					print(currentGame,py);
					currentGame.printGameBoard();
					String op2=score(currentGame,p1,p2);
					currentGame.printGameBoardToFile( "computer.txt" );
				} 
				System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
				exit_function(0);
			}
			
			if(nextPlay.equalsIgnoreCase("computer-next"))
			{
				currentGame.printGameBoard();
				
				String p1 = "human";
				String p2 = "computer";
				String temp = "";
				if(currentGame.getCurrentTurn()==2)
				{
					temp = p1;
					p1 = p2;
					p2 = temp;}
				String op=score(currentGame,p1,p2);
				while( currentGame.getPieceCount() < 42 ) 
				{
					py = c.findBestPlay(currentGame);
					currentGame.playPiece(py);
					print(currentGame,py);
					def1(currentGame);
					String op1=score(currentGame,p1,p2);
					currentGame.printGameBoardToFile("computer.txt");
					fun(currentGame);
					currentGame.printGameBoardToFile( "human.txt" );
					String op3=score(currentGame,p1,p2);
					String op2=score(currentGame,p1,p2);
				} 

				System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
				exit_function(0);
			}
		} 
		else if( game_mode.equalsIgnoreCase( "one-move" ) ) 
		{
			String output = args[2].toString();				// the output game file
			System.out.print("\nMaxConnect-4 game\n"+"The game state before move:\n");
			def1(currentGame);
			System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) + ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
			if( currentGame.getPieceCount() < 42 ) 
			{
				int cp = currentGame.getCurrentTurn();
				py = c.findBestPlay( currentGame );
				currentGame.playPiece( py );
				System.out.println("Move so far " + currentGame.getPieceCount() + ": Player " + cp + ", column " + py);
				System.out.print("The game state after move:\n");
				def1(currentGame);
				System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) + ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
				currentGame.printGameBoardToFile( output );
			} 
			else 
			{
				System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
			}

			//************************** end computer play

			return;
		}
	} // end of main()

	/**
	 * This method is used when to exit the program prematurly.
	 * @param value an integer that is returned to the system when the program exits.
	 */
	private static void exit_function( int value )
	{
		System.out.println("exiting from MaxConnectFour.java!\n\n");
		System.exit( value );
	}
} // end of class connectFour