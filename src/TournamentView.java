// Eugene Fedoriv
// 
// 01/08/2019
// 01/22/2019
//
// Tournament Viewer Menu
//
// Brought up by Option Menu. Tournament Viewer shows the tournament bracket and allows the user to control the tournament.


/*			*Variable Dictionary*
 * 
 * block[] (Block) - the array of objects of Block class that are used to collect each pair of subBlocks
 * 
 * subBlock[] (SubBlock) - the array of objects of SubBlock class that are used to store one object of Player class per each SubBlock
 *
 * Players[] (Player) - the array of objects of Player class. Each 'Player' object contains a JLabel that is placed on the field and displays the Player's name
 *
 * gameMode (Integer) - the variable which conveys what type of the Tournament Bracket is used (it has 3 options: for maximum of 32, 16, or 8 players in the bracket).(p.s. 'gameMode' is determined while the amount of players participating in the tournament received from the "Option Menu")
 *
 * Winner (JLabel) - the label which is placed on the JFrame and displays the winner of the Tournament in the end of the game.(Also has red borders around it)
 *
 * jFieldSB[] (JPanel) - the array of panel objects used to draw borders around each SubBlock object
 *
 * players (Integer) - the integer which receives and stores the amount of players (received from "Option Menu")
 *
 * ordered (boolean) - the boolean variable which is received from "Option Menu" and gives the information whether to place players randomly or matched by twos into the Tournament Bracket
 * 
 * loopVar (Integer) - the cycle defining variable, used for setting X and Y values for the block[], and subBlock[] objects. loopVar's value varies depending on the value of 'player' variable
 * 
 * counterforBlocks (Integer) - the counter variable used while setting X and Y values for the block[] array
 * 
 * counterSB (Integer) - the counter variable used while setting X and Y values for the subBlock[] array as well as determining whether the subBlock is located in the upper or lower part of the block
 * 
 * height (Integer) - the variable that represents the height of each block[] object
 * 
 * length (Integer) - the variable that represents the length of each block[] object
 * 
 * gap (Integer) - the variable that represents the length of the gap between block[] objects
 * 
 * xPos[] (Integer) - the array that represents the X-values of each column of blocks on the JFrame
 * 
 * yPos[] (Integer) - the array that represents the Y-values of the first block of each column on the certain level of the Tournament net
 * 
 * coefficient (Integer) - the variable which is used while setting Y-values for each subBlock[] and block[] objects.('mult' variable is depended on variable 'gameMod'. Therefore Y-values of subBlockp[] and Block[] classes will vary depending on what type of the tournament net is used
 * 
 * seq1[] (Integer) - the array of integers representing the sequence in which Players must be placed in the tournament net (used if 'ordered'=false and 'gameMode' = 1(meaning the tournament is out of 4-8 players, and the players must be placed randomly into the Tournament Bracket))
 * 
 * seq2[] (Integer) - the array of integers representing the sequence in which Players must be placed in the tournament net (used if 'ordered'=false and 'gameMode' = 2(meaning the tournament is out of 9-16 players, and the players must be placed randomly into the Tournament Bracket))
 * 
 * seq3[] (Integer) - the array of integers representing the sequence in which Players must be placed in the tournament net (used if 'ordered'=false and 'gameMode' = 3(meaning the tournament is out of 17-32 players, and the players must be placed randomly into the Tournament Bracket))
 * 
 * seqPair1[] (Integer) - the array of integers representing the sequence in which Players must be placed in the tournament net (used if 'ordered'=true and 'gameMode' = 1(meaning the tournament is out of 4-8 players, and the players must be matched by pairs before being placed into the Tournament Bracket))
 * 
 * seqPair2[] (Integer) - the array of integers representing the sequence in which Players must be placed in the tournament net (used if 'ordered'=true and 'gameMode' = 2(meaning the tournament is out of 9-16 players, and the players must be matched by pairs before being placed into the Tournament Bracket))
 * 
 * seqPair3[] (Integer) - the array of integers representing the sequence in which Players must be placed in the tournament net (used if 'ordered'=true and 'gameMode' = 3(meaning the tournament is out of 17-32 players, and the players must be matched by pairs before being placed into the Tournament Bracket))
 *
 * audioInputStream (AudioInputStream) - input stream to the audio file
 *
 * clip (Clip) - the object reproducing the audio file
 *
 */ 

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.border.Border;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class TournamentView extends JFrame implements ActionListener,MouseListener {

Block block[];
int gameMode=0;
SubBlock subBlock[];
Player Players[];
JLabel Winner,TitleJL;
JPanel jFieldSB[];
boolean mode;
String title;
AudioInputStream audioInputStream; 
Clip clip;

int players=0;// integer that will represent amount of players(sent from the option menu)
boolean ordered=false;// random/ordered
 
int loopVar=0;
int counterforBlocks=0,counterSB=0;
int height=0,width=0, gap=0;
int xPos[] =new int[10],yPos[]= new int[3];
double coefficient=0;
	


	public TournamentView(Player PlayersOM[],boolean orderedOM,int playersOM, String titleOM,boolean modeOM) {
	super("Tournament View");
	

	 this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	 
	ordered =orderedOM;
	players = playersOM;
	Players = new Player[players];
	Players =PlayersOM;
	mode = modeOM;
	title= titleOM;
	
	if (ordered==false) shuffleArray(Players);
		
		int seq1[] = {0,4,2,6,1,5,3,7};
		int seq2[]= {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
		int seq3[] = {0,16,8,24,4,20,12,28,2,18,10,26,6,22,14,30,1,17,9,25,5,21,13,29,3,19,11,27,7,23,15,31};

		int seqPair1[] = {0,1,4,5,2,3,6,7};
		int seqPair2[]= {0,1,8,9,4,5,12,13,2,3,10,11,6,7,14,15};
		int seqPair3[]= {0,1,16,17,8,9,24,25,4,5,20,21,12,13,28,29,2,3,18,19,10,11,26,27,6,7,22,23,14,15,30,31};
	
	
		TitleJL = new JLabel(title);
		
		TitleJL.setHorizontalAlignment(0);
		
		
		getContentPane().setBackground(new Color(255,229,153));
		setSize(1465, 850);
		setResizable(false);
		setLayout(null);
		
		Winner = new JLabel();
		
		
		
		
	
		if (players>3 && players<=8) 
		{
			gameMode=1;
			loopVar=24;
			block = new Block[7];
			subBlock = new SubBlock[14];
			height=346;
			width=242;
			gap=40;
			yPos[1]=gap;
			yPos[2]=((height+2*gap)+gap)/2;
			xPos[4]=gap;
			xPos[5]=1450-(gap+width);
			xPos[6]=2*gap+width;
			xPos[7]=1450-2*(gap+width);
			xPos[8]=3*gap+2*width;
			xPos[9]=1450-3*(gap+width);
			coefficient=0.25;
			
		
			
			for (int i=0;i<7;i++) {
				block[i]=new Block(i);
				block[i].setHeight(height);
				block[i].setWidth(width);

					 if (i>=0 && i<=3)block[i].setLevel(2);
					else if (i>=4 && i<=5)block[i].setLevel(1);
					else if (i==6)block[i].setLevel(0);
			}
			
			for (int i=0;i<14;i++) {
				subBlock[i]=new SubBlock();
				subBlock[i].setHeight(height/2);
				subBlock[i].setWidth(width);
			}
		}
		else if (players>8 && players<=16) {
			gameMode=2;
			loopVar=16;
			block = new Block[15];
			subBlock = new SubBlock[30];
			height=172;//height
			width=184;//width
			gap=20;
			yPos[0]=gap;
			yPos[1]=((height+2*gap)+gap)/2;
			yPos[2]=gap+height+yPos[1];
			xPos[2]=gap;
			xPos[3]=1450-(gap+width);
			xPos[4]=2*gap+width;
			xPos[5]=1450-2*(gap+width);
			xPos[6]=3*gap+2*width;
			xPos[7]=1450-3*(gap+width);
			xPos[8]=4*gap+3*width;
			xPos[9]=1450-4*(gap+width);
			coefficient=0.5;
	
			for (int i=0;i<15;i++) {
				block[i]=new Block(i);
				block[i].setHeight(height);
				block[i].setWidth(width);
				
				 if (i>=0 && i<=7)block[i].setLevel(3);
				else if (i>=8 && i<=11)block[i].setLevel(2);
				else if (i>=12 && i<=13)block[i].setLevel(1);
				else if (i==14)block[i].setLevel(0);
				
			}
			
			for (int i=0;i<30;i++) {
				subBlock[i]=new SubBlock();
				subBlock[i].setHeight(height/2);
				subBlock[i].setWidth(width);
			}
		}
		else {
			gameMode=3;
			loopVar=0;
			block = new Block[31];
			subBlock = new SubBlock[62];
			width=150;
			height=90;
			gap=10;
			yPos[0]=((height+2*gap)+gap)/2;
			yPos[1]=gap+height+yPos[0];
			yPos[2]=2*(gap+height)+gap+height+yPos[0];
			
			xPos[0]=gap;
			xPos[1]=1450-(gap+width);
			xPos[2]=2*gap+width;
			xPos[3]=1450-2*(gap+width);
			xPos[4]=3*gap+2*width;
			xPos[5]=1450-3*(gap+width);
			xPos[6]=4*gap+3*width;
			xPos[7]=1450-4*(gap+width);
			xPos[8]=5*gap+4*width;
			xPos[9]=5*gap+4*width;
			
			
			coefficient=1;
	
			for (int i=0;i<31;i++) {
				block[i]=new Block(i);
				block[i].setHeight(height);
				block[i].setWidth(width);
				
				if (i<=15) block[i].setLevel(4);
				else if (i>=16 && i<=23)block[i].setLevel(3);
				else if (i>=24 && i<=27)block[i].setLevel(2);
				else if (i>=28 && i<=29)block[i].setLevel(1);
				else if (i==30)block[i].setLevel(0);
				
			}
			
			for (int i=0;i<62;i++) {
				subBlock[i]=new SubBlock();
				subBlock[i].setHeight(height/2);
				subBlock[i].setWidth(width);
			}
		}
		//******
		

		for (int i =loopVar;i<=31;i++){
		
			if (i<=7){
		
				block[counterforBlocks].setX(xPos[0]);
				block[counterforBlocks].setY((int) (coefficient*(gap+height)*i+gap));
				counterforBlocks++;
				
				subBlock[counterSB].setX(xPos[0]);
				subBlock[counterSB].setY((int) (coefficient*(gap+height)*i+gap));
				subBlock[counterSB].setUpper(true);
				counterSB++;
				subBlock[counterSB].setX(xPos[0]);
				subBlock[counterSB].setY((int) (coefficient*(gap+height)*i+gap)+height/2);
				subBlock[counterSB].setUpper(false);
				counterSB++;
	
		}
		
		else if (i>=8 && i<=15){
	
			block[counterforBlocks].setX(xPos[1]);
			block[counterforBlocks].setY((int)( coefficient*(gap+height)*(i-8)+gap));
			counterforBlocks++;
			
			
			subBlock[counterSB].setX(xPos[1]);
			subBlock[counterSB].setY((int)(coefficient*(gap+height)*(i-8)+gap));
			subBlock[counterSB].setUpper(true);
			counterSB++;
			subBlock[counterSB].setX(xPos[1]);
			subBlock[counterSB].setY((int)(coefficient*(gap+height)*(i-8)+gap)+height/2);
			subBlock[counterSB].setUpper(false);
			counterSB++;
		
			
			
		}
		
		else if (i>=16 && i<=19){
	
			
			block[counterforBlocks].setX(xPos[2]);
			block[counterforBlocks].setY((int)(coefficient*(2*(gap+height))*(i-16)+yPos[0]));
			counterforBlocks++;
			
			
			subBlock[counterSB].setX(xPos[2]);
			subBlock[counterSB].setY((int)(coefficient*(2*(gap+height))*(i-16)+yPos[0]));
			subBlock[counterSB].setUpper(true);
			counterSB++;
			subBlock[counterSB].setX(xPos[2]);
			subBlock[counterSB].setY((int)(coefficient*(2*(gap+height))*(i-16)+yPos[0])+height/2);
			subBlock[counterSB].setUpper(false);
			counterSB++;
			
		}
	
////		
		

	else if (i>=20 && i<=23){
	
		block[counterforBlocks].setX(xPos[3]);
		block[counterforBlocks].setY((int)(coefficient*(2*(gap+height))*(i-20)+yPos[0]));
		counterforBlocks++;
		
		
		subBlock[counterSB].setX(xPos[3]);
		subBlock[counterSB].setY((int)(coefficient*(2*(gap+height))*(i-20)+yPos[0]));
		subBlock[counterSB].setUpper(true);
		counterSB++;
		subBlock[counterSB].setX(xPos[3]);
		subBlock[counterSB].setY((int)(coefficient*(2*(gap+height))*(i-20)+yPos[0])+height/2);
		subBlock[counterSB].setUpper(false);
		counterSB++;
		
		
		
		}
		
	else if (i>=24 && i<=25){
		
		block[counterforBlocks].setX(xPos[4]);
		block[counterforBlocks].setY((int)(coefficient*(4*(gap+height))*(i-24)+yPos[1]));
		counterforBlocks++;
		

		subBlock[counterSB].setX(xPos[4]);
		subBlock[counterSB].setY((int)(coefficient*(4*(gap+height))*(i-24)+yPos[1]));
		subBlock[counterSB].setUpper(true);
		counterSB++;
		subBlock[counterSB].setX(xPos[4]);
		subBlock[counterSB].setY((int)(coefficient*(4*(gap+height))*(i-24)+yPos[1])+height/2);
		subBlock[counterSB].setUpper(false);
		counterSB++;
		
	}
else if (i>=26 && i<=27){

	block[counterforBlocks].setX(xPos[5]);
	block[counterforBlocks].setY((int)(coefficient*(4*(gap+height))*(i-26)+yPos[1]));
	counterforBlocks++;
	
	
	subBlock[counterSB].setX(xPos[5]);
	subBlock[counterSB].setY((int)(coefficient*(4*(gap+height))*(i-26)+yPos[1]));
	subBlock[counterSB].setUpper(true);
	counterSB++;
	subBlock[counterSB].setX(xPos[5]);
	subBlock[counterSB].setY((int)(coefficient*(4*(gap+height))*(i-26)+yPos[1])+height/2);
	subBlock[counterSB].setUpper(false);
	counterSB++;
	
	
	}
else if (i==28){
	
	
	block[counterforBlocks].setX(xPos[6]);
	block[counterforBlocks].setY((int) (coefficient*(8*(gap+height))*(i-28)+yPos[2]));
	counterforBlocks++;
	
	
	subBlock[counterSB].setX(xPos[6]);
	subBlock[counterSB].setY((int) (coefficient*(8*(gap+height))*(i-28)+yPos[2]));
	subBlock[counterSB].setUpper(true);
	counterSB++;
	subBlock[counterSB].setX(xPos[6]);
	subBlock[counterSB].setY((int) (coefficient*(8*(gap+height))*(i-28)+yPos[2])+height/2);
	subBlock[counterSB].setUpper(false);
	counterSB++;
	
}
else if (i==29){

	
	block[counterforBlocks].setX(xPos[7]);
	block[counterforBlocks].setY((int) (coefficient*(8*(gap+height))*(i-29)+yPos[2]));
	counterforBlocks++;
	
	
	
	subBlock[counterSB].setX(xPos[7]);
	subBlock[counterSB].setY((int) (coefficient*(8*(gap+height))*(i-29)+yPos[2]));
	subBlock[counterSB].setUpper(true);
	counterSB++;
	subBlock[counterSB].setX(xPos[7]);
	subBlock[counterSB].setY((int) (coefficient*(8*(gap+height))*(i-29)+yPos[2])+height/2);
	subBlock[counterSB].setUpper(false);
	counterSB++;
	
}
else if (i==30){
	
	block[counterforBlocks].setX(xPos[8]);
	block[counterforBlocks].setY((int) (coefficient*(8*(gap+height))*(i-30)+yPos[2]));
	counterforBlocks++;
	
	
	subBlock[counterSB].setX(xPos[8]);
	subBlock[counterSB].setY((int) (coefficient*(8*(gap+height))*(i-30)+yPos[2]));
	subBlock[counterSB].setUpper(true);
	counterSB++;
	subBlock[counterSB].setX(xPos[8]);
	subBlock[counterSB].setY((int) (coefficient*(8*(gap+height))*(i-30)+yPos[2])+height/2);
	subBlock[counterSB].setUpper(false);
	counterSB++;

}
else if(i==31) {
	Winner.setHorizontalAlignment(0);
	Winner.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
	Winner.setFont(new Font("Arial",Font.BOLD,19));
	switch(gameMode) {
	case 3:
		Winner.setBounds(block[block.length-1].getX(),block[block.length-1].getY()-154, block[block.length-1].getWidth(), 90);
		TitleJL.setFont(new Font("Times New Roman",Font.BOLD,35));
		TitleJL.setBounds(block[28].getX(),10,block[29].getX()-block[28].getX()+block[28].getWidth(),60);
		break;
	case 2:
		Winner.setBounds(block[block.length-1].getX(),block[block.length-1].getY()-154, block[block.length-1].getWidth(), 90);
		TitleJL.setFont(new Font("Times New Roman",Font.BOLD,30));
		TitleJL.setBounds(block[12].getX(),10,block[13].getX()-block[12].getX()+block[12].getWidth(),60);
		break;
	case 1:
		Winner.setBounds(block[block.length-1].getX(),block[block.length-1].getY()-140, block[block.length-1].getWidth(), 90);
		TitleJL.setFont(new Font("Times New Roman",Font.BOLD,30));
		TitleJL.setBounds(block[4].getX(),10,block[5].getX()-block[4].getX()+block[4].getWidth(),60);
		break;
	}
	add(Winner);
	add(TitleJL);
	
}
			
	
		
		}
		for (int i =0;i<=block.length;i++) {
			if (ordered==false) {
			if (gameMode==3) {
				
				if (seq3[i]<players) {
					subBlock[i].setPlayer(Players[seq3[i]]);
					add(subBlock[i].getLabel());
				}
				else {
					subBlock[i].setPlayer(null);
					add(subBlock[i].getLabel());
				}
				
				if(seq3[i]%2==1) {
					block[(seq3[i]-1)/2].addSubBlock(subBlock[seq3[i]]);
					subBlock[seq3[i]].setBlock(block[(seq3[i]-1)/2]);
				}
				else {
					block[seq3[i]/2].addSubBlock(subBlock[seq3[i]]);
					subBlock[seq3[i]].setBlock(block[seq3[i]/2]);
				}
			
			}
			else if (gameMode==2) {
			
				if (seq2[i]<players) {
					subBlock[i].setPlayer(Players[seq2[i]]);
					add(subBlock[i].getLabel());
				}
				else {
					subBlock[i].setPlayer(null);
					add(subBlock[i].getLabel());
				}
				
				if(seq2[i]%2==1) {
					block[(seq2[i]-1)/2].addSubBlock(subBlock[seq2[i]]);
					subBlock[seq2[i]].setBlock(block[(seq2[i]-1)/2]);
					
				}
				else {
					block[seq2[i]/2].addSubBlock(subBlock[seq2[i]]);
					subBlock[seq2[i]].setBlock(block[seq2[i]/2]);
				}
			
			}
			else {
				
				if (seq1[i]<players) {
					subBlock[i].setPlayer(Players[seq1[i]]);
					add(subBlock[i].getLabel());
				
				}
				else {
					subBlock[i].setPlayer(null);
					add(subBlock[i].getLabel());
				}
				
				if(seq1[i]%2==1) {
					block[(seq1[i]-1)/2].addSubBlock(subBlock[seq1[i]]);
					subBlock[seq1[i]].setBlock(block[(seq1[i]-1)/2]);
					
				}
				else {
					block[seq1[i]/2].addSubBlock(subBlock[seq1[i]]);
					subBlock[seq1[i]].setBlock(block[seq1[i]/2]);
				}
			
			}
			}
			else {
				if (gameMode==3) {
				
					if (seqPair3[i]<players)	{
						subBlock[i].setPlayer(Players[seqPair3[i]]);
						add(subBlock[i].getLabel());
					}
					else {
						subBlock[i].setPlayer(null);
						add(subBlock[i].getLabel());
					}
					
					if(seqPair3[i]%2==1) {
						block[(seqPair3[i]-1)/2].addSubBlock(subBlock[seqPair3[i]]);
						subBlock[seqPair3[i]].setBlock(block[(seqPair3[i]-1)/2]);
						
					}
					else {
						block[seqPair3[i]/2].addSubBlock(subBlock[seqPair3[i]]);
						subBlock[seqPair3[i]].setBlock(block[seqPair3[i]/2]);
					}
				
				}
				else if (gameMode==2) {
				
					if (seqPair2[i]<players) {
					subBlock[i].setPlayer(Players[seqPair2[i]]);
					add(subBlock[i].getLabel());
				}
					else {
						subBlock[i].setPlayer(null);
						add(subBlock[i].getLabel());
					}
					
					if(seqPair2[i]%2==1) {
						block[(seqPair2[i]-1)/2].addSubBlock(subBlock[seqPair2[i]]);
						subBlock[seqPair2[i]].setBlock(block[(seqPair2[i]-1)/2]);
						
					}
					else {
						block[seqPair2[i]/2].addSubBlock(subBlock[seqPair2[i]]);
						subBlock[seqPair2[i]].setBlock(block[seqPair2[i]/2]);
					}
				
				}
				else {
					
					if (seqPair1[i]<players) {
						subBlock[i].setPlayer(Players[seqPair1[i]]);
						add(subBlock[i].getLabel());
					}
					else {
						subBlock[i].setPlayer(null);
						add(subBlock[i].getLabel());
					}
				
					if(seqPair1[i]%2==1) {
						block[(seqPair1[i]-1)/2].addSubBlock(subBlock[seqPair1[i]]);
						subBlock[seqPair1[i]].setBlock(block[(seqPair1[i]-1)/2]);
						
					}
					else {
						block[seqPair1[i]/2].addSubBlock(subBlock[seqPair1[i]]);
						subBlock[seqPair1[i]].setBlock(block[seqPair1[i]/2]);
					}
				}
			}
		}
		
		for (int i=block.length+1;i<subBlock.length;i++) {
			subBlock[i].setBlock(block[(int)i/2]);
			subBlock[i].setPlayer(null);
		}
		

		for (int i=0;i<subBlock.length;i++) {
			
			if(subBlock[i].getLabel()!=null) subBlock[i].getLabel().addMouseListener(this);
			}
		
		
		for (int i=0;i<=block.length;i++) {//setting counter of Players of the highest level Blocks with empty sub Blocks.
			if(subBlock[i].getPlayer()==null) {
				if(block[subBlock[i].getBlock().getNum()].getUpperSB().getPlayer()==null && block[subBlock[i].getBlock().getNum()].getLowerSB().getPlayer()==null)
				block[subBlock[i].getBlock().getNum()].setCounter(0);
				else block[subBlock[i].getBlock().getNum()].setCounter(1);
				
			}
		
				}
		
jFieldSB = new JPanel[subBlock.length];
		
		for (int i=0;i<subBlock.length;i++) {
			jFieldSB[i]=new JPanel();
			 
			jFieldSB[i].setBounds(subBlock[i].getX(),subBlock[i].getY(),subBlock[i].getWidth()+1,subBlock[i].getHeight());
			jFieldSB[i].setBackground(new Color(255,229,153));
			jFieldSB[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		add(jFieldSB[i]);		
		}
		
		
		switch (gameMode) {
		
		case 3:
			moveOnLoop(15,16,0);
			
			break;
		case 2:
			moveOnLoop(7,8,0);
			break;
			
		case 1:
			moveOnLoop(3,4,0);
			break;
			
		}
		
		 
		
		
		setVisible(true);
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	
	}
	@Override
	public void mousePressed(MouseEvent e) {

		for (int i=0;i<subBlock.length;i++) {
			if(e.getSource()==subBlock[i].getLabel()) {
				if(subBlock[i].getPlayer()!=null && block[subBlock[i].getBlock().getNum()].getCounter()==2 && subBlock[i].getBlock().getWhetherMMisOpened()==false) {
				new MatchView(block[subBlock[i].getBlock().getNum()].getUpperSB().getPlayer(), block[subBlock[i].getBlock().getNum()].getLowerSB().getPlayer(),mode,block[subBlock[i].getBlock().getNum()].getUpperSB(),block[subBlock[i].getBlock().getNum()].getLowerSB(),this);
					
				}
				
			}
				}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		for (int i=0;i<subBlock.length;i++) {
			if(e.getSource()==subBlock[i].getLabel()) {
		
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//0-1-16

public boolean checkIfNext(Block B) {
		int BlockNum = B.getNum();
		boolean theOnly=true;
		int level = B.getLevel();
		int a=0;
	int allowedLevel=0;
	
	if (level==0)return(false);
	switch (gameMode) {
	case 3:
		allowedLevel=3;
		break;
	case 2:
		allowedLevel=2;
		break;
	case 1:
		allowedLevel=1;
	}
	
	if (level<allowedLevel) {
		
		return(false);
	}
		
			if (level==3){
				
				for (int j=0;j<=8;j++){
				if (BlockNum%8==j){
					a=2*j;
				}
				}
				
		
		}
			else if(level==2) {
				for (int j=0;j<=4;j++){
					if (BlockNum%4==j){
						a=2*j;
					}
					}
			}
			
			else if(level==1) {
				for (int j=0;j<=2;j++){
					if (BlockNum%2==j){
						a=2*j;
					}
					}
			}
		
	if (block[BlockNum].getCounter()==2) return(false);
	
			 
				for (int i=0;i<=1;i++) {
					if (block[a+i].getCounter()!=0)
					return(false);
					
				}
			
			
			
			return theOnly;
			
		
	}
	
public void Next(SubBlock thisSubBlock) {
	int blockNum= thisSubBlock.getBlock().getNum();
	
		if (block[blockNum].getCounter()!=1) {
			if (block[blockNum].getLevel()==0) {
				if (thisSubBlock.getUpper()==true) {
					Winner.setText(block[blockNum].getLowerSB().getPlayer().getName());
					thisSubBlock.setPlayer(null);
					block[blockNum].setCounter(0);
				
				}
				else {
					Winner.setText(block[blockNum].getUpperSB().getPlayer().getName());
					thisSubBlock.setPlayer(null);
					block[blockNum].setCounter(0);
				}
				block[blockNum].getLowerSB().getLabel().removeMouseListener(this);
				block[blockNum].getLowerSB().setLabel(null);
				block[blockNum].getUpperSB().getLabel().removeMouseListener(this);
				block[blockNum].getUpperSB().setLabel(null);
			
				 //playing the victory sound
				
			    try {
					audioInputStream = AudioSystem.getAudioInputStream(new File("victory.wav"));
					clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
			    }
			    catch (LineUnavailableException q) {
					q.printStackTrace();
				} 
				catch (IOException q) {
					q.printStackTrace();
				} 
				catch (UnsupportedAudioFileException q) {
					q.printStackTrace();
				}


			}
			
			else {
				
			thisSubBlock.setPlayer(null);
			block[blockNum].setCounter(1);
			switch (gameMode) {
			
			case 3:
				moveOnLoop(blockNum,16,blockNum);
				
				break;
			case 2:
				moveOnLoop(blockNum,8,blockNum);
				break;
				
			case 1:
				moveOnLoop(blockNum,4,blockNum);
				break;
				
			}
				
		}
			
		}
	
	
}
	
public void moveOnLoopOrdered(int a,int b, int c) {
	for (int i=c;i<=a;i++) {
		
		if(i%2==0) {
			if (block[i].getUpperSB().getPlayer()!=null) {//upper
				
				block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
				subBlock[i+(2*b)].setPlayer(block[i].getUpperSB().getPlayer());
				remove(jFieldSB[i+(2*b)]);
				add(block[(int)i/2+b].getUpperSB().getLabel());
				if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
				subBlock[i+(2*b)].getLabel().addMouseListener(this);
				
			
				
			}
			else if (block[i].getLowerSB().getPlayer()!=null) {
				
				block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
				subBlock[i+(2*b)].setPlayer(block[i].getLowerSB().getPlayer());
				add(block[(int)i/2+b].getUpperSB().getLabel());
				remove(jFieldSB[i+(2*b)]);
				subBlock[i+(2*b)].getLabel().addMouseListener(this);
				if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
			}
			
		}
		else {//i%==1
	if (block[i].getUpperSB().getPlayer()!=null) {//lower
				
				block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
				subBlock[i+(2*b)].setPlayer(block[i].getUpperSB().getPlayer());
				remove(jFieldSB[i+(2*b)]);
				add(block[(int)i/2+b].getLowerSB().getLabel());
				subBlock[i+(2*b)].getLabel().addMouseListener(this);
			
				if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
			}
			else if (block[i].getLowerSB().getPlayer()!=null) {
				
				block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
				subBlock[i+(2*b)].setPlayer(block[i].getLowerSB().getPlayer());
				remove(jFieldSB[i+(2*b)]);
				add(block[(int)i/2+b].getLowerSB().getLabel());
				subBlock[i+(2*b)].getLabel().addMouseListener(this);
				if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
			}
		}
	}
}
	
public void moveOnLoop(int a, int b,int c) {
	for (int i=c;i<=a;i++) {
		
			if(i%2==0) {
			if (block[i].getCounter()==1) {
				if (block[i].getUpperSB().getPlayer()==null) {
					
					block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
					
					subBlock[i+(2*b)].setPlayer(block[i].getLowerSB().getPlayer());
					
					remove(jFieldSB[i+(2*b)]);
					add(block[(int)i/2+b].getUpperSB().getLabel());
					
					
					
					if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
					
				
						subBlock[i+(2*b)].getLabel().addMouseListener(this);
					
					if(block[i].getLowerSB().getLabel()!=null) {
						block[i].getLowerSB().getLabel().removeMouseListener(this);
						block[i].getLowerSB().setLabel(null);
						block[i].setCounter(0);
					}
					
					if(block[i].getUpperSB().getLabel()!=null) {
						block[i].getUpperSB().getLabel().removeMouseListener(this);
						block[i].getUpperSB().setLabel(null);
						block[i].setCounter(0);
					}
					
					 if(block[(int)i/2+b].getCounter()==1 && block[(int)i/2+b].getLevel()!=0) {
						 
						 if(checkIfNext(block[(int)i/2+b])==true) {
						
							if (subBlock[i+(2*b)].getUpper()==true) {
								block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)+1]);
								block[(int)i/2+b].getLowerSB().setPlayer(null);//filling the colour for the empty spot
								add(block[(int)i/2+b].getLowerSB().getLabel());
								remove(jFieldSB[i+(2*b)]);
								remove(jFieldSB[i+(2*b)+1]);
						
							}
							else {
								block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)+1]);
								block[(int)i/2+b].getUpperSB().setPlayer(null);//filling the colour for the empty spot
								add(block[(int)i/2+b].getUpperSB().getLabel());
								remove(jFieldSB[i+(2*b)]);
								remove(jFieldSB[i+(2*b)+1]);
								
							}
							
							 
							 
							 subBlock[i+(2*b)].getLabel().removeMouseListener(this);
							 subBlock[i+(2*b)].setLabel(null);
							 block[(int)i/2+b].setCounter(0);
							 
								switch (gameMode) {
								
								case 3:
									moveOnLoopOrdered((int)i/2+b,16,(int)i/2+b);
									
									break;
								case 2:
									moveOnLoopOrdered((int)i/2+b,8,(int)i/2+b);
									break;
									
								case 1:
									moveOnLoopOrdered((int)i/2+b,4,(int)i/2+b);
									break;
									
								}
						 }
					 }
		
					
				}
				else if (block[i].getLowerSB().getPlayer()==null) {
					
					block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
					
					subBlock[i+(2*b)].setPlayer(block[i].getUpperSB().getPlayer());
					
				
					add(block[(int)i/2+b].getUpperSB().getLabel());
					remove(jFieldSB[i+(2*b)]);
					
					subBlock[i+(2*b)].getLabel().addMouseListener(this);
				
					if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
					
					
					if(block[i].getLowerSB().getLabel()!=null) {
						block[i].getLowerSB().getLabel().removeMouseListener(this);
						block[i].getLowerSB().setLabel(null);
						block[i].setCounter(0);
					}
					
					if(block[i].getUpperSB().getLabel()!=null) {
						block[i].getUpperSB().getLabel().removeMouseListener(this);
						block[i].getUpperSB().setLabel(null);
						block[i].setCounter(0);
					}
					
					if(block[(int)i/2+b].getCounter()==1 && block[(int)i/2+b].getLevel()!=0) {
						 if(checkIfNext(block[(int)i/2+b])==true) {
								
							 if (subBlock[i+(2*b)].getUpper()==true) {
									block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)+1]);
									block[(int)i/2+b].getLowerSB().setPlayer(null);//filling the colour for the empty spot
									add(block[(int)i/2+b].getLowerSB().getLabel());
									remove(jFieldSB[i+(2*b)]);
									remove(jFieldSB[i+(2*b)+1]);
								}
								else {
									block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)+1]);
									block[(int)i/2+b].getUpperSB().setPlayer(null);//filling the colour for the empty spot
									add(block[(int)i/2+b].getUpperSB().getLabel());
									remove(jFieldSB[i+(2*b)]);
									remove(jFieldSB[i+(2*b)+1]);
								}
		
							 
							 
							 subBlock[i+(2*b)].getLabel().removeMouseListener(this);
							 subBlock[i+(2*b)].setLabel(null);
							 block[(int)i/2+b].setCounter(0);
							 
								switch (gameMode) {
								
								case 3:
									moveOnLoopOrdered((int)i/2+b,16,(int)i/2+b);
									
									break;
								case 2:
									moveOnLoopOrdered((int)i/2+b,8,(int)i/2+b);
									break;
									
								case 1:
									moveOnLoopOrdered((int)i/2+b,4,(int)i/2+b);
									break;
									
								}
						 }
					 }
					
				}
		
			}
			}
			
			
			else {  //i%2==1
				if (block[i].getCounter()==1) {
					if (block[i].getUpperSB().getPlayer()==null) {
						
						block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
						subBlock[i+(2*b)].setPlayer(block[i].getLowerSB().getPlayer());
						
						
						add(block[(int)i/2+b].getLowerSB().getLabel());
						remove(jFieldSB[i+(2*b)]);
					
						
						subBlock[i+(2*b)].getLabel().addMouseListener(this);
						
						
						if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
						
						
						if(block[i].getLowerSB().getLabel()!=null) {
							block[i].getLowerSB().getLabel().removeMouseListener(this);
							block[i].getLowerSB().setLabel(null);
							block[i].setCounter(0);
						}
						
						if(block[i].getUpperSB().getLabel()!=null) {
							block[i].getUpperSB().getLabel().removeMouseListener(this);
							block[i].getUpperSB().setLabel(null);
							block[i].setCounter(0);
						}///
						
						
						if(block[(int)i/2+b].getCounter()==1 && block[(int)i/2+b].getLevel()!=0) {
							 if(checkIfNext(block[(int)i/2+b])==true) {
							
					 
								 
								 if (subBlock[i+(2*b)].getUpper()==true) {
										block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)-1]);
										block[(int)i/2+b].getLowerSB().setPlayer(null);//filling the colour for the empty spot
										add(block[(int)i/2+b].getLowerSB().getLabel());
										remove(jFieldSB[i+(2*b)]);
										remove(jFieldSB[i+(2*b)-1]);
									}
									else {
										block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)-1]);
										block[(int)i/2+b].getUpperSB().setPlayer(null);//filling the colour for the empty spot
										add(block[(int)i/2+b].getUpperSB().getLabel());
										remove(jFieldSB[i+(2*b)]);
										remove(jFieldSB[i+(2*b)-1]);
									}
								 
								
								 subBlock[i+(2*b)].getLabel().removeMouseListener(this);
								 subBlock[i+(2*b)].setLabel(null);
								 block[(int)i/2+b].setCounter(0);
								 
									switch (gameMode) {
									
									case 3:
										moveOnLoopOrdered((int)i/2+b,16,(int)i/2+b);
										
										break;
									case 2:
										moveOnLoopOrdered((int)i/2+b,8,(int)i/2+b);
										break;
										
									case 1:
										moveOnLoopOrdered((int)i/2+b,4,(int)i/2+b);
										break;
										
									}
							 }
						 }
				
					}
					else if (block[i].getLowerSB().getPlayer()==null) {
						
						
						
						block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)]);
						
						subBlock[i+(2*b)].setPlayer(block[i].getUpperSB().getPlayer());
						
						
						
						add(block[(int)i/2+b].getLowerSB().getLabel());

						remove(jFieldSB[i+(2*b)]);
						
						
						subBlock[i+(2*b)].getLabel().addMouseListener(this);
						
						if (block[(int)i/2+b].getLevel()==0)  subBlock[i+(2*b)].getLabel().setFont(new Font("Arial",Font.BOLD,15));
						
						
						if(block[i].getLowerSB().getLabel()!=null) {
							block[i].getLowerSB().getLabel().removeMouseListener(this);
							block[i].getLowerSB().setLabel(null);
							block[i].setCounter(0);
						}
						
						if(block[i].getUpperSB().getLabel()!=null) {
							block[i].getUpperSB().getLabel().removeMouseListener(this);
							block[i].getUpperSB().setLabel(null);
							block[i].setCounter(0);
						}///
						
						
						 if(block[(int)i/2+b].getCounter()==1 && block[(int)i/2+b].getLevel()!=0) {
							 if(checkIfNext(block[(int)i/2+b])==true) {
							
											 
															 
															 if (subBlock[i+(2*b)].getUpper()==true) {
																	block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)-1]);
																	block[(int)i/2+b].getLowerSB().setPlayer(null);//filling the colour for the empty spot
																	add(block[(int)i/2+b].getLowerSB().getLabel());
																	remove(jFieldSB[i+(2*b)]);
																	remove(jFieldSB[i+(2*b)-1]);
																}
																else {
																	block[(int)i/2+b].addSubBlock(subBlock[i+(2*b)-1]);
																	block[(int)i/2+b].getUpperSB().setPlayer(null);//filling the colour for the empty spot
																	add(block[(int)i/2+b].getUpperSB().getLabel());
																	remove(jFieldSB[i+(2*b)]);
																	remove(jFieldSB[i+(2*b)-1]);
																}
															 
														
								 
								 
								 subBlock[i+(2*b)].getLabel().removeMouseListener(this);
								 subBlock[i+(2*b)].setLabel(null);
								 block[(int)i/2+b].setCounter(0);
								 
									switch (gameMode) {
									
									case 3:
										moveOnLoopOrdered((int)i/2+b,16,(int)i/2+b);
										
										break;
									case 2:
										moveOnLoopOrdered((int)i/2+b,8,(int)i/2+b);
										break;
										
									case 1:
										moveOnLoopOrdered((int)i/2+b,4,(int)i/2+b);
										break;
										
									}
							 }
						 }
					
					}
				}
				
			}
		
	}
}


public void shuffleArray(Player[] player) {
	 Random rnd = ThreadLocalRandom.current();
	    for (int i = player.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      Player a = player[index];
	      player[index] = player[i];
	      player[i] = a;
	    }
}


}
