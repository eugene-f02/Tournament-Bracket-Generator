// Eugene Fedoriv
//
// Main Menu
//
// Main menu allows the user to choose between creating their own tournament or using a sample.


/*			*Variable Dictionary*
 * 
 * sample (JButton) - used to generate a tournament bracket using a sample
 * 
 * newTournament (JButton) - used to generate a new tournament bracket
 * 
 * title (JLabel) - a label used in the menu (inscription) 
 * 
 * metrics1 (FontMetrics) - used to measure pixels of a certain text written in 'font1'
 * 
 * metrics2 (FontMetrics) - used to measure pixels of a certain text written in 'font2'
 * 
 * font1 (Font) - a font reference (bold font "Candara" (size 19))
 * 
 * font2 (Font) - a font reference (plain font "Candara" (size 16))
 * 
 * beige (Color) - beige colour
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Menu extends JFrame implements ActionListener
{
	
	JButton sample,newTournament;
	JLabel title;
	FontMetrics metrics1 = getFontMetrics(font1);
	FontMetrics metrics2 = getFontMetrics(font2);
	
	public static Font font1 = new Font("Candara", Font.BOLD, 19);
	public static Font font2 = new Font("Candara", Font.PLAIN, 16);
	public static Color beige = new Color(255,229,153);
	
	public static void main(String[] args) {
		new Menu();
	}
	
	 
			
	
	
	public Menu() {
		
		   super("Tournament Bracket Generator");
		   setSize(500,180);
		   this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		   getContentPane().setLayout(null);
		   getContentPane().setBackground(beige);
		   

		
		   
		   title = new JLabel("Tournament Bracket Generator");
		   title.setFont(font1);
		   title.setBounds(100, 10, 300, 23);
		   add(title);
		   
		   
		   sample = new JButton("Generate a Sample");
		   sample.setFont(font2);
		   sample.setBounds(20,70,220,40);
		   sample.addActionListener(this);
		   add(sample);
		   
		   
		   newTournament = new JButton("Create a new Tournament");
		   newTournament.setFont(font2);
		   newTournament.setBounds(260,70,220,40);
		   newTournament.addActionListener(this);
		   add(newTournament);
		   
		//  int adv = metrics2.stringWidth("text");  //used to measure pixels 
	    //  System.out.println(adv);
		//	System.out.println(metrics2.getHeight());

			
			
			
			 setVisible(true);
			 setResizable(false);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		
		if (e.getSource()==sample) { //generating sample tournament bracket
		
			
			
			
			boolean passed = false;
			while (passed==false) {
				
			String inp = (String)JOptionPane.showInputDialog(this,
					"How many players would you like to include (4-32)?", 
					"Sample Tournament Bracket",JOptionPane.PLAIN_MESSAGE);
			
			if (inp == null)passed = true;
			
			else if (Errors.isNumeric(inp)==true) {
					
				int inpInt  = Integer.valueOf(inp);
				
				
				if (inpInt >3 && inpInt <33) {
					passed = true;
					Player[] players = new Player[inpInt];
					
					for(int i = 0; i <inpInt; i++) 
					{
						players[i]=new Player(String.valueOf(i),"Player #"+String.valueOf(i));
						
					}
					
					new TournamentView(players,false,inpInt,"Tournament (" + String.valueOf(inpInt) + ")",true);
					this.dispose();
					
					
				}
				else Errors.error("The number of players cannot be less than 4 or grater than 32","Error");
				
				
			}
			else Errors.error("Please, make sure that the number of players is numeric(e.g. 8,9,13)","Error");
		
		}
		
	}
		
		else { // opening "create your own tournament" menu
			new OptionMenu();
			this.dispose();
		}

	
	
}
	
	
	
}
