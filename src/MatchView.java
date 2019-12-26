// Eugene Fedoriv
// 
// 01/10/2019
// 01/21/2019
// 
// Match View Menu
//
// Brought up by the tournament view. Shows details about the selected match


/*			*Variable Dictionary*
 * 
 * lightBlue, shapeBlue, lightRed, shapeRed (Color) - references to the corresponding colours 
 * 
 * bestOf3 (boolean) - used to check whether a best-of-one or best-of-three game mode has been chosen
 * 
 * topPlus, topMinus, botPlus, botMinus (JButton) - buttons used for manipulating the circles acting as a counter of wins for each player 
 * 
 * SaveExit (JButton) - saves the scores of the match and exits the current MatchView menu
 * 
 * topLabel, botLabel, detTop, detLow (JLabel) - labels displaying the two players' names and details
 * 
 * playerTop, playerBot (Player) - two player objects considered for the current MatchView menu
 * 
 * topP, botP (JPanel) - two panels containing name and details labels for each of the two players
 * 
 * border (Border) - border reference, used around labels displaying players' names
 * 
 * UpperSB, LowerSB (SubBlock) - reference to the two corresponding subBlocks
 * 
 * tournament (TournamentView) - reference to the tournamentView menu
 * 
 * originalScoreUpper, originalScoreLower (int) - used to track score for each of the players
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MatchView extends JFrame implements ActionListener {



	Color lightBlue = new Color(120, 170, 245);
	Color shapeBlue = new Color(130, 180, 255);
	Color lightRed = new Color(220, 90, 100);
	Color shapeRed = new Color(230, 100, 110);

	boolean bestOf3;
	JButton topPlus, topMinus, botPlus, botMinus, SaveExit;
	JLabel topLabel, botLabel, detTop, detLow;
	Player playerTop, playerBot;
	JPanel topP, botP;
	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	SubBlock UpperSB, LowerSB;
	TournamentView tournament;
	int originalScoreUpper,originalScoreLower;
	
	
	
	public MatchView(Player playerUpper, Player playerLower, boolean winType, SubBlock Upper, SubBlock Lower, TournamentView TV) {
		super(playerUpper.getName()+" vs " +playerLower.getName());
	
		
		 
		
		  
		LowerSB=Lower;
		UpperSB=Upper;
		tournament=TV;
		
		UpperSB.getBlock().setMMOpened(true);
		
		originalScoreUpper=playerUpper.getScore();
		originalScoreLower = playerLower.getScore();
		
		 addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	            	UpperSB.getBlock().setMMOpened(false);
	               playerTop.setScore(originalScoreUpper);
	               playerBot.setScore(originalScoreLower);
	            }
	        });
		 
		 
		int x1 = 0, x2 = 0, y1 = 150, y2 = 410;
		bestOf3 = winType;
		if (bestOf3 == true) {
			x1 = 435;
			x2 = 75;
		} else if (bestOf3 == false) {
			x1 = 340;
			x2 = 140;
		}

		getContentPane().setLayout(null);
		getContentPane().setBackground(Menu.beige);

		setSize(595, 660);
		

	
		
		topP = new JPanel();//red field
		topP.setBounds(20, 20, 550, 250);
		topP.setBackground(lightRed);
		topP.setLayout(null);
		add(topP);
		
		

		botP = new JPanel();//blue field
		botP.setBounds(20, 290, 550, 250);
		botP.setBackground(lightBlue);
		botP.setLayout(null);
		add(botP);
	
		
		playerTop = playerUpper;
		playerBot = playerLower;
		playerTop.setScore(playerTop.getScore());
		playerBot.setScore(playerBot.getScore());

	

		topMinus = new JButton("-");
		topMinus.setBounds(x2, y1, 70, 70);
		topMinus.addActionListener(this);
		topMinus.setBackground(shapeRed);
		add(topMinus);
		
		topP.add(topMinus);
		
		
		
		topPlus = new JButton("+");
		topPlus.setBounds(topP.getWidth()-topMinus.getWidth()-x2, y1, 70, 70);
		topPlus.addActionListener(this);
		topPlus.setBackground(shapeRed);
		add(topPlus);
		
		topP.add(topPlus);
		
		
		botPlus = new JButton("+");
		botPlus.setBounds(topP.getWidth()-topMinus.getWidth()-x2, y1, 70, 70);
		botPlus.addActionListener(this);
		botPlus.setBackground(shapeBlue);
		add(botPlus);
		
		botP.add(botPlus);
	
		botMinus = new JButton("-");
		botMinus.setBounds(x2, y1, 70, 70);
		botMinus.addActionListener(this);
		botMinus.setBackground(shapeBlue);
		add(botMinus);

		botP.add(botMinus);
		
		
		topLabel = new JLabel(playerTop.getName());
		topLabel.setBounds((topP.getWidth())/2-150, topP.getY(), 300, 60);
		topLabel.setBorder(border);
		topLabel.setHorizontalAlignment(0);
		topLabel.setBackground(shapeRed);
		add(topLabel);		
		
		topP.add(topLabel);		
		
		botLabel = new JLabel(playerBot.getName());
		botLabel.setBounds((topP.getWidth())/2-150, topP.getY(), 300, 60);
		botLabel.setBackground(shapeBlue);
		botLabel.setBorder(border);
		botLabel.setHorizontalAlignment(0);
		add(botLabel);
		
		botP.add(botLabel);
		
		if (!playerTop.getDetails().equals(" ")) {
		detTop = new JLabel("Details: "+"  "+playerTop.getDetails());
		detTop.setBounds(topLabel.getX(), topLabel.getY()+topLabel.getHeight()+5, topLabel.getWidth(), topLabel.getHeight());
		detTop.setBackground(shapeBlue);
		
		add(detTop);
		
		topP.add(detTop);
		}
		
		if (!playerBot.getDetails().equals(" ")) {
		detLow = new JLabel("Details: "+"  "+playerBot.getDetails());
		detLow.setBounds(botLabel.getX(), botLabel.getY()+botLabel.getHeight()+5, botLabel.getWidth(), botLabel.getHeight());
		detLow.setBackground(shapeRed);
		
		add(detLow);
		
		botP.add(detLow);
		}
		
		///**********
		SaveExit = new JButton("Save & Exit");
		SaveExit.setBounds(this.getWidth()/2-topLabel.getWidth()/2, 640-90, topLabel.getWidth(), 80);
		add(SaveExit);
		SaveExit.addActionListener(this);
		
		setBackground(Menu.beige);
		setResizable(false);
		repaint();
		
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);


		int X1 = 0, X2 = 0, Y1 = 0, Y2 = 0;
		if (bestOf3 == true) {
			X1 = 215; // left
			X2 = 310; // right
			Y1 = 180; // top
			Y2 = 440; // bottom
			g.setColor(shapeRed);
			g.fillOval(X2, Y1+30, 70, 70); // top-right
			g.setColor(shapeBlue);
			g.fillOval(X2, Y2+30, 70, 70); // bottom-right
			g.setColor(Color.black);
			g.drawOval(X2, Y1+30, 70, 70); // top-right outline
			g.drawOval(X2, Y2+30, 70, 70); // bottom-right outline
		} else if (bestOf3 == false) {
			X1 = 262;
			Y1 = 180; // top
			Y2 = 440; // bottom
		}
		g.setColor(shapeRed);
		g.fillOval(X1, Y1+30, 70, 70); // top[-left]

		g.setColor(shapeBlue);
		g.fillOval(X1, Y2+30, 70, 70); // bottom[-left]

		g.setColor(Color.black);
		g.drawOval(X1, Y1+30, 70, 70); // top[-left] outline
		g.drawOval(X1, Y2+30, 70, 70); // bottom[-left] outline
	
		
		
		
		
		
		if (playerTop.getScore()==0) {
			topMinus.setEnabled(false);
		}
	 if (playerTop.getScore() == 1) {
			g.setColor(Color.WHITE);
			g.fillOval(X1, Y1+30, 70, 70);
			
			if (bestOf3==false) {
				topPlus.setEnabled(false);
				botMinus.setEnabled(false);
				botPlus.setEnabled(false);
			}

		} else if (playerTop.getScore() == 2) {
			
				g.setColor(Color.WHITE);
				g.fillOval(X1, Y1+30, 70, 70); // top-left point oval
				g.fillOval(X2, Y1+30, 70, 70); // top-right point oval
				g.setColor(Color.BLACK);
			
				topPlus.setEnabled(false);
				botMinus.setEnabled(false);
				botPlus.setEnabled(false);
			
		}
		
		
		if (playerBot.getScore()==0) {
			botMinus.setEnabled(false);
		}
		 if (playerBot.getScore() == 1) {
			g.setColor(Color.WHITE);
			g.fillOval(X1, Y2+30, 70, 70); // bottom[-left] point oval
			g.setColor(Color.BLACK);
			if (bestOf3==false) {
				botPlus.setEnabled(false);
				topMinus.setEnabled(false);
				topPlus.setEnabled(false);
			}
		} else if (playerBot.getScore() == 2) {
			
				g.setColor(Color.WHITE);
				g.fillOval(X1, Y2+30, 70, 70); // top-left point oval
				g.fillOval(X2, Y2+30, 70, 70); // top-right point oval
				g.setColor(Color.BLACK);
				
				botPlus.setEnabled(false);
				topMinus.setEnabled(false);
				topPlus.setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == topPlus) {
			playerTop.setScore(playerTop.getScore() + 1);
			
			if (bestOf3==false && playerTop.getScore()==1) {
				topMinus.setEnabled(true);
			}
			
			if (bestOf3==true && playerTop.getScore()==1) {
				topMinus.setEnabled(true);
			}
			
			
			repaint();
		}
		if (e.getSource() == topMinus) {
			if (bestOf3==false && playerTop.getScore()==1) {
				topPlus.setEnabled(true);
				botMinus.setEnabled(true);
				botPlus.setEnabled(true);
			}
			playerTop.setScore(playerTop.getScore() - 1);
			
			if (bestOf3==true && playerTop.getScore()==1) {
				topPlus.setEnabled(true);
				botMinus.setEnabled(true);
				botPlus.setEnabled(true);
			}
			
			repaint();
		}
		if (e.getSource() == botPlus) {
			playerBot.setScore(playerBot.getScore() + 1);
			
			if (bestOf3==false && playerBot.getScore()==1) {
				botMinus.setEnabled(true);
			}
			
			if (bestOf3==true && playerBot.getScore()==1) {
				botMinus.setEnabled(true);
			}
			
			repaint();
		}
		if (e.getSource() == botMinus) {
			if (bestOf3==false && playerBot.getScore()==1) {
				botPlus.setEnabled(true);
				topMinus.setEnabled(true);
				topPlus.setEnabled(true);
			}
			
			playerBot.setScore(playerBot.getScore() - 1);
			
			if (bestOf3==true && playerBot.getScore()==1) {
				botPlus.setEnabled(true);
				topMinus.setEnabled(true);
				topPlus.setEnabled(true);
			}
			
			repaint();
		}
		
		if (e.getSource() == SaveExit) {
			UpperSB.getBlock().setMMOpened(false);
			if(bestOf3==false) {
				if (playerTop.getScore()==1) {
					
					tournament.Next(LowerSB);
					tournament.repaint();
					playerTop.setScore(0);
					this.dispose();
				}
				else if (playerBot.getScore()==1) {
				
					tournament.Next(UpperSB);
					tournament.repaint();
					playerBot.setScore(0);
					this.dispose();
				}
			}
			else if (bestOf3==true){
				if (playerTop.getScore()==2) {
					
					tournament.Next(LowerSB);
					tournament.repaint();
					playerTop.setScore(0);
					this.dispose();
				}
				else if (playerBot.getScore()==2) {
				
					tournament.Next(UpperSB);
					tournament.repaint();
					playerBot.setScore(0);
					this.dispose();
				}
			}
			
				this.dispose();
	
	
			
		}
	}

	

}


