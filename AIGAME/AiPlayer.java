import java.util.*;

/**
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing. 
 * 
 * @author james spargo
 *
 */
public class AiPlayer 
{
	/***********************************************************************************************
	 * Name     	 	:  Avinash Reddy Sallagonda
	 * Student ID 		:  1002034491
	 ************************************************************************************************/

	/**
	 * The constructor essentially does nothing except instantiate an
	 * AiPlayer object.
	 *
	 */

	int depthLevel;

	public AiPlayer(int depthLevel) 
	{
		this.depthLevel = depthLevel;
	}

	/**
	 * This method plays a piece randomly on the board
	 * @param currentGame The GameBoard object that is currently being used to
	 * play the game.
	 * @return an integer indicating which column the AiPlayer would like
	 * to play in.
	 */

	//findBestPlay method decides whether the computer should make the move the minimum value or the maximum value using the alphabeta pruning logic
	public int findBestPlay(GameBoard currentGame) 
	{
		int playChoice = 99;
		
		if (currentGame.getCurrentTurn() == 1) 
		{
			int m, n = Integer.MAX_VALUE;
			
			for (int i = 0; i < 7; i++) 
			{
				if (currentGame.isValidPlay(i)) 
				{
					GameBoard gameBoard = new GameBoard(currentGame.getGameBoard());
					gameBoard.playPiece(i);
					m = maxu(gameBoard, depthLevel, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if ( m < n) 
					{
						playChoice = i;
						n = m;
					}
				}
			}
		} 
		else 
		{
			int  m, n = Integer.MIN_VALUE;
			for (int i = 0; i < 7; i++) 
			{
				if (currentGame.isValidPlay(i)) 
				{
					GameBoard nex = new GameBoard(currentGame.getGameBoard());
					nex.playPiece(i);
					m = minu(nex, depthLevel, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if (m > n) 
					{
						playChoice = i;
						n = m;
					}
				}
			}
		}
		return playChoice;
	}  

	public List<Integer> fun(int mini,int dl,int alpha,int beta,int value){
		List<Integer> co = new ArrayList<>();
		if(mini<value)
		mini=value;
		if(mini >= beta) 
		{
			co.add(mini);
			co.add(alpha);
			co.add(beta);
			co.add(value);
			int k=1;
			co.add(k);
			return co;
		}
		if(mini>alpha)
		alpha=mini;
		co.add(mini);
		co.add(alpha);
		co.add(beta);
		co.add(value);
		int k=0;
		co.add(k);
		return co;
	}
	public int maxu(GameBoard gameBoard, int depthLevel, int alpha, int beta) 
	{
		if((gameBoard.getPieceCount() < 42) && depthLevel > 0) 
		{
			int m=7;
			int mini = Integer.MIN_VALUE, value;
			for(int i = 0; i <m ; i++) 
			{
				if(gameBoard.isValidPlay(i)) 
				{
					List<Integer> co=new ArrayList<>();
					GameBoard gb = new GameBoard(gameBoard.getGameBoard());
					Boolean k=gb.playPiece(i);

					int d=0;
					value = minu(gb, depthLevel-1, alpha, beta);
					co = fun(mini,depthLevel,alpha,beta,value);
					mini=co.get(0);
					alpha=co.get(1);
					beta=co.get(2);
					value=co.get(3);
					d=co.get(4);
					if(d==1) return mini;
				}
			}
			return mini;
		}
		else
		{
		
			// GameBoard gb = new GameBoard(gameBoard.getGameBoard());
			return (gameBoard.eval(2)*100) - (gameBoard.eval(1)*100);
		}
	}
	public List<Integer> fun2(int maxi,int depthLevel,int alpha,int beta,int value){
		List<Integer> co = new ArrayList<>();

		if(maxi>value)
		maxi=value;
		if(maxi <= alpha) 
		{
			co.add(maxi);
			co.add(alpha);
			co.add(beta);
			co.add(value);
			int k=1;
			co.add(k);
			return co;
		}
		if(maxi<beta)
		beta=maxi;
		co.add(maxi);
		co.add(alpha);
		co.add(beta);
		co.add(value);
		int k=0;
		co.add(k);
		return co;
	}
	public int minu(GameBoard gameBoard, int depthLevel, int alpha, int beta) 
	{
		if((gameBoard.getPieceCount() < 42) && depthLevel > 0) 
		{
			int maxi = Integer.MAX_VALUE, value;
			int m=7;
			int d=0;
			for(int i = 0; i < m; i++) 
			{
				if(gameBoard.isValidPlay(i)) 
				{
					GameBoard gb = new GameBoard(gameBoard.getGameBoard());
					gb.playPiece(i);
					List<Integer> co=new ArrayList<>();
					value=maxu(gb, depthLevel-1, alpha, beta);
					co=fun2(maxi, depthLevel, alpha, beta,value);
					maxi=co.get(0);
					alpha=co.get(1);
					beta=co.get(2);
					value=co.get(3);
					d=co.get(4);
					if(d==1) return maxi;
				}
			}
			return maxi;
		}
		else
		{
			int k=0;
			return k;
			// GameBoard gb = new GameBoard(gameBoard.getGameBoard());
			// return (gameBoard.eval(2)*100) - (gameBoard.eval(1)*100);
		}
	}
}