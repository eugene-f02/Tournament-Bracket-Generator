// Eugene Fedoriv
// 
// 01/10/2019
// 01/21/2019
// 
// Options Menu
//
// Allows the user to choose between multiple options that will effect the tournament and match view menus


/*			*Variable Dictionary*
 * 
 * add (JButton) - adds the names of players to the combo box
 * 
 * start (JButton) - creates a tournament bracket based on the entered information
 * 
 * remove (JButton) - removes the selected player from the combo box
 * 
 * back (JButton) - returns to the main menu
 * 
 * players[] (Player) - stores the players of the tournament 
 * 
 * newTournamentT (JTextField) - a text field where a tournament name is typed
 * 
 * numberOfPlayersT (JTextField) - a text field where a number of players for the tournament is typed
 * 
 * addPlayerT (JTextField) - a text field where the name of a new player is typed
 * 
 * playerDetailsT (JTextField) - a text field where optional details for a player can be typed
 * 
 * titleL (JLabel) - a label used in the menu (inscription) 
 * 
 * newTournamentL (JLabel) - a label used in the menu (inscription) 
 * 
 * numberOfPlayersL (JLabel) - a label used in the menu (inscription) 
 * 
 * addPlayerL (JLabel) - a label used in the menu  (inscription)
 * 
 * playerDetailsL (JLabel) - a label used in the menu (inscription)
 * 
 * question (JLabel) - a label used in the menu (image)
 * 
 * playerCounter (JLabel) - a label used in the menu (inscription)
 * 
 * randomPlacement (JRadioButton) - enables random placement of players for the tournament bracket
 * 
 * orderedPlacement (JRadioButton) - enables paired sequential placement of players for the tournament bracket
 * 
 * bestOfOne (JRadioButton) - sets the game mode of the tournament as 'best-of-one' 
 * 
 * bestOfThree (JRadioButton) - sets the game mode of the tournament as 'best-of-three'
 * 
 * placementP (JPanel) - a panel which stores 'randomPlacement' and 'orderedPlacement' radio buttons as well as 'question' label
 * 
 * gameModeP (JPanel) - a panel which stores 'bestOfOne' and 'bestOfThree' radio buttons 
 * 
 * placement (ButtonGroup) - groups 'randomPlacement' and 'orderedPlacement' radio buttons
 * 
 * gameMode (ButtonGroup) - groups 'bestOfOne' and 'bestOfThree' radio buttons
 * 
 * playersBox (JComboBox) - a combo box displaying the players of the tournament
 * 
 * playerList (ArrayList<String>) - stores entered player names
 * 
 * playerDetails (ArrayList<String>) - stores entered optional player details information
 * 
 * tournamentName (String) - stores a tournament name entered by the user
 * 
 * numOfPlayers (int) - stores a number of players entered by the user
 * 
 * Counter (int) - used to keep track of how many players have been added/removed by the user
 * 
 * playerPlacement (boolean) - used to tell whether a random or paired placement has been chosen
 * 
 * gameM (boolean) - used to tell whether a best-of-one or best-of-three game mode has been chosen
 * 
 * checkedOne (boolean) - used to tell whether the user selected either of the two game modes
 * 
 * checkedTwo (boolean) - used to tell whether the user selected either of the two 'playerPlacement' modes
 * 
 * image (BufferedImage) - an image used for providing clarification regarding 'playerPlacement' selection
 * 
 */
import javax.swing.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class OptionMenu extends JFrame implements ActionListener
{	
	
	   JButton add,start,remove,back;
	   Player players[];
   	   JTextField newTournamentT,numberOfPlayersT,addPlayerT,playerDetailsT,numberOfPlayersRandom;
	   JLabel titleL,newTournamentL,numberOfPlayersL,addPlayerL,playerDetailsL,question,playerCounter;
	   JRadioButton randomPlacement, orderedPlacement,bestOfOne,bestOfThree;
	   JPanel placementP,gameModeP;
	   ButtonGroup placement,gameMode;
	   JComboBox playersBox;
	   ArrayList<String> playerList = new ArrayList<String>();
	   ArrayList<String> playerDetails = new ArrayList<String>();
	   String tournamentName;
	   int numOfPlayers, Counter=0;
	   boolean playerPlacement,gameM,checkedOne = false, checkedTwo=false;
	   BufferedImage image;
	   
	   
	public OptionMenu()
	   {
		   super("New Tournament");
		   setSize(500,528);
		   this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		   getContentPane().setLayout(null);
		   getContentPane().setBackground(Menu.beige);
		  
		   

	       try {                
	          image = ImageIO.read(new File("question-mark.png"));
	       } catch (IOException ex) {
	            Errors.error("Cannot find image 'question-mark.pnj'", "error");
	       }
	
	       
		   question = new JLabel(new ImageIcon (image));
	       question.addMouseListener(new MouseAdapter() 
	       {
	           @Override
	           public void mouseClicked(MouseEvent e) 
	           {
	        	   
	             if(question.isEnabled()==true) Errors.error("If \"Random\" button is selected, the players will be randomly distributed into the tournament bracket"
	             		+ "\nIf \"Paired\" button is selected, the players will be paired in sequence based on their location in the combo box"
	             		+ "\n(i.e. player 1 vs player 2, player 3 vs player 4, etc.)","Placement");
	           }
	       });
		   
		   titleL = new JLabel("Tournament Bracket Generator");
		   titleL.setFont(Menu.font1);
		   titleL.setBounds(100, 10, 300, 23);
		   add(titleL);
	
		   
		   
		   newTournamentL = new JLabel("Tournament Name:");
		   newTournamentL.setFont(Menu.font2);
		   newTournamentL.setBounds(30, 53, 151, 20);
		   add(newTournamentL);
		    
		   newTournamentT = new JTextField(20);//tournament name
		   newTournamentT.setFont(Menu.font2);
		   newTournamentT.setBounds(191, 48, 279, 30);
		   add(newTournamentT);
		   newTournamentT.addActionListener(new java.awt.event.ActionListener() {
			   public void actionPerformed(ActionEvent event) {
				   tournamentName=newTournamentT.getText();
				   numberOfPlayersT.setEnabled(true);
				   numberOfPlayersL.setEnabled(true);
				   numberOfPlayersT.requestFocus();
			   }
			 });

		   
		   
		   
		   
		   numberOfPlayersL = new JLabel("Number of Players:");
		   numberOfPlayersL.setFont(Menu.font2);
		   numberOfPlayersL.setBounds(30, 93, 149, 20);
		   add(numberOfPlayersL);
		   
		   numberOfPlayersT = new JTextField(20);
		   numberOfPlayersT.setFont(Menu.font2);
		   numberOfPlayersT.setBounds(191, 88, 45, 30);
		   add(numberOfPlayersT);
		   numberOfPlayersT.addActionListener(new java.awt.event.ActionListener() {
			   public void actionPerformed(ActionEvent event) {
				   
				   if (Errors.isNumeric(numberOfPlayersT.getText())==true) {
					  
				   if (Integer.valueOf(numberOfPlayersT.getText()) >3 && Integer.valueOf(numberOfPlayersT.getText()) <33) {
					   numOfPlayers = Integer.valueOf(numberOfPlayersT.getText());
					   enableMenus();
					   addPlayerT.requestFocus();
					   
					   
					   if (Counter == 0) remove.setEnabled(false);
					   
					   if (numOfPlayers <= playersBox.getItemCount()) {
						   
						  for (int i =playersBox.getItemCount()-1; i>=numOfPlayers; i--) {
							  playersBox.removeItemAt(i);
							  Counter--;
							  playerList.remove(i);
							  playerDetails.remove(i);
								
								
						  }
						  add.setEnabled(false);
							playerDetailsL.setEnabled(false);
							addPlayerL.setEnabled(false);
							playerDetailsT.setEnabled(false);
							addPlayerT.setEnabled(false);
						   
					   }
					   
					   playerCounter.setText(Counter+"/"+numOfPlayers);
					   allowToStart();
					   
				   }
				   else {
					   disableMenus();
					   Errors.error("The number of players cannot be less than 4 or grater than 32","Error");
					   
				   }
				}
				   else {
					   disableMenus();
					   Errors.error("Please, make sure that the number of players is numeric(e.g. 8,9,13)","Error");
				   }
				   
			   }
		   
			 });
		  
		   
		   
		   randomPlacement = new JRadioButton ("Random");
		   randomPlacement.setFont(Menu.font2);
		   randomPlacement.addActionListener(this);
		   
		   
		   orderedPlacement = new JRadioButton ("Paired   ");
		   orderedPlacement.setFont(Menu.font2);
		   orderedPlacement.addActionListener(this);
		   
		   
		   placement = new ButtonGroup();
		   placement.add(randomPlacement);
		   placement.add(orderedPlacement);
		   
		   
		   placementP = new JPanel();
		   placementP.setLayout(null);
		   placementP.setBackground(Menu.beige);
		   placementP.setFont(Menu.font2);
		   placementP.setBounds(30,138,160,100);
		   placementP.add(randomPlacement);
		   randomPlacement.setBounds(10, 25, 100, 20);
		   placementP.add(orderedPlacement);
		   orderedPlacement.setBounds(10, 55, 100, 20);
		   placementP.add(question);
		    question.setBounds(132, 72, 20, 20);
		   placementP.setBorder(BorderFactory.createTitledBorder(
                   BorderFactory.createEtchedBorder(), "Placement"));
		   add(placementP);

		   
		   
		   
		   
		   bestOfOne = new JRadioButton ("Best-of-One   ");
		   bestOfOne.setFont(Menu.font2);
		   bestOfOne.addActionListener(this);
		   
		   
		   bestOfThree = new JRadioButton ("Best-of-Three");
		   bestOfThree.setFont(Menu.font2);
		   bestOfThree.addActionListener(this);
		   
		   gameMode = new ButtonGroup();
		   gameMode.add(bestOfOne);
		   gameMode.add(bestOfThree);
		   
		   
		   gameModeP = new JPanel();
		   gameModeP.setLayout(null);
		   gameModeP.setBackground(Menu.beige);
		   gameModeP.setFont(Menu.font2);
		   gameModeP.setBounds(310,138,160,100);
		   gameModeP.add(bestOfOne);
		   bestOfOne.setBounds(10, 25, 150, 20);
		   gameModeP.add(bestOfThree);
		   bestOfThree.setBounds(10, 55, 150, 20);
		   gameModeP.setBorder(BorderFactory.createTitledBorder(
                   BorderFactory.createEtchedBorder(), "Game Mode"));
		   add(gameModeP);
		   
		  
		   
		   
		   
		   
		 
		   
		   
		   addPlayerL = new JLabel("Player Name:");
		   addPlayerL.setFont(Menu.font2);
		   addPlayerL.setBounds(30, 258, 102, 20);
		   add(addPlayerL);
		   
		   addPlayerT = new JTextField(20); // player name
		   addPlayerT.setFont(Menu.font2);
		   addPlayerT.setBounds(30,283,136,30);
		   add(addPlayerT);
		   addPlayerT.addActionListener(new java.awt.event.ActionListener() {
			   public void actionPerformed(ActionEvent event) {
				   playerDetailsT.requestFocus();
			   }
			 });
		   
		   playerDetailsL = new JLabel("Details(Optional):");
		   playerDetailsL.setFont(Menu.font2);
		   playerDetailsL.setBounds(30, 318, 136, 20);
		   add(playerDetailsL);
		   
		   
		
		   playerDetailsT = new JTextField(15); // player details
		   playerDetailsT.setFont(Menu.font2);
		   playerDetailsT.setBounds(30,343,136,30);
		   add(playerDetailsT);
		   playerDetailsT.addActionListener(new java.awt.event.ActionListener() {
			   public void actionPerformed(ActionEvent event) {
				   add.requestFocus();
			   }
			 });
  
		   
		   add= new JButton("Add");
		   add.setFont(new Font("Candara", Font.PLAIN, 14));
		   add(add);
		   add.setBounds(56,378,83,40);
		   add.addActionListener(this);
		   
		   
		   playersBox = new JComboBox();
		   playersBox.setFont(Menu.font2);
		   playersBox.addActionListener(this);
		   playersBox.setBounds(200,308, 100, 40);
		   add (playersBox);
		   
		   
		   remove=new JButton("<html>" + "Remove Selected" + "<br>" + "<center>Player</center>" + "</html>");
		   remove.setFont(new Font("Candara", Font.PLAIN, 14));
		   remove.setBounds(310,303,160,50);
		   remove.addActionListener(this);
		   add(remove);
		   
		     
		   
		   start = new JButton("Start");
		   start.setFont(Menu.font1);
		   start.setBounds(30,438,300,60);
		   start.addActionListener(this);
		   add(start);
		  
		   back = new JButton("Back");
		   back.setFont(Menu.font2);
		   back.setBounds(350,438,120,60);
		   back.addActionListener(this);
		   add(back);
		   
		   
		   
		   
		   playerCounter = new JLabel();
		   playerCounter.setFont(Menu.font2);
		   playerCounter.setBounds(225,288,50,20);
		   add(playerCounter);
		   
		   
		   numberOfPlayersT.setEnabled(false);
		   numberOfPlayersL.setEnabled(false);
		   disableMenus();
		   setVisible(true);
		   setResizable(false);
		   
		   
		 
	    
	      
		   
	   }
	   
	   
	
	
	
	
	
	   
	   public void actionPerformed(ActionEvent e)
	   {
		
			if (e.getSource()== randomPlacement) {
				playerPlacement = false;
				checkedOne=true;
				allowToStart();
			}
			else if (e.getSource()== orderedPlacement) {
				playerPlacement = true;
				checkedOne=true;
				allowToStart();
			}
			
			if (e.getSource()== bestOfOne) {
				gameM = false;
				checkedTwo = true;
				allowToStart();
			}
			else if (e.getSource() == bestOfThree) {
				gameM = true;
				checkedTwo = true;
				allowToStart();
			}
			
			if(e.getSource() == add)
			{
				if (addPlayerT.getText().trim().length()>0) {
					
	
				playerList.add(addPlayerT.getText().trim());
				playersBox.addItem(makeObj(addPlayerT.getText().trim()));
	
				playerDetails.add(playerDetailsT.getText().trim());
				playerDetailsT.setText("");
				Counter++;
				playerCounter.setText(Counter+"/"+numOfPlayers);
				addPlayerT.setText("");
				addPlayerT.requestFocus();
			
			

				if (remove.isEnabled()==false) {
					   remove.setEnabled(true);
				}
				
				if (Counter == numOfPlayers) {
					add.setEnabled(false);
					playerDetailsL.setEnabled(false);
					addPlayerL.setEnabled(false);
					playerDetailsT.setEnabled(false);
					addPlayerT.setEnabled(false);
					allowToStart();
				}
				
				
				
				
			
			}
				else {
					addPlayerT.setText("");
					addPlayerT.requestFocus();
				}
				
				
				
				
			}
			
			else if(e.getSource() == remove)
			{
				int selectedItem = playersBox.getSelectedIndex();
				playersBox.removeItemAt(selectedItem);
				playerList.remove(selectedItem);
				playerDetails.remove(selectedItem);
				Counter--;
				playerCounter.setText(Counter+"/"+numOfPlayers);
				
				if( Counter==0) remove.setEnabled(false);
				else if (Counter == numOfPlayers-1) {
					add.setEnabled(true);
					playerDetailsL.setEnabled(true);
					addPlayerL.setEnabled(true);
					playerDetailsT.setEnabled(true);
					addPlayerT.setEnabled(true);
					start.setEnabled(false);
				}
			}

			
			
			if(e.getSource()==start) 
			{	
								players = new Player[numOfPlayers];
						
								for(int i = 0; i <numOfPlayers; i++)
								{
									players[i]=new Player(playerList.get(i),playerDetails.get(i));
									
								}
								
								new TournamentView(players,playerPlacement,numOfPlayers,tournamentName,gameM);
						        this.dispose();
							}
							
			
			if (e.getSource()==back) {
				new Menu();
				this.dispose();
			}
			
			
			
			
	   }
	   
	   
	   private Object makeObj(final String item)  {
		     return new Object() {public String toString() { return item;} };
		   }
	   
	   
	   public void disableMenus() {
		   playerDetailsL.setEnabled(false);
		   addPlayerL.setEnabled(false);
		   randomPlacement.setEnabled(false);
		   orderedPlacement.setEnabled(false);
		   bestOfOne.setEnabled(false);
		   bestOfThree.setEnabled(false);
		   remove.setEnabled(false);
		   add.setEnabled(false);
		   playerDetailsT.setEnabled(false);
		   addPlayerT .setEnabled(false);
		   playersBox.setEnabled(false);
		   placementP.setEnabled(false);
		   gameModeP.setEnabled(false);
		   start.setEnabled(false);
		   question.setEnabled(false);
		   playerCounter.setEnabled(false);
	}
	   
	   
	   public void enableMenus() {
		   playerDetailsL.setEnabled(true);
		   addPlayerL.setEnabled(true);
		   randomPlacement.setEnabled(true);
		   orderedPlacement.setEnabled(true);
		   bestOfOne.setEnabled(true);
		   bestOfThree.setEnabled(true);
		   remove.setEnabled(true);
		   add.setEnabled(true);
		   playerDetailsT.setEnabled(true);
		   addPlayerT .setEnabled(true);
		   playersBox.setEnabled(true);
		   placementP.setEnabled(true);
		   gameModeP.setEnabled(true);
		   question.setEnabled(true);
		   playerCounter.setEnabled(true);
	}
	   
	  
	   public void allowToStart() {
		   
		if (checkedOne && checkedTwo && Counter == numOfPlayers)
			start.setEnabled(true);
		else start.setEnabled(false);
	   }
	   
	   
}



