// Eugene Fedoriv
//
// SubBlock class
//
// Provides a template for creating a cell on the tournament bracket containing one Player, and access to the information about the sub-block
//  and the corresponding player


/*			*Variable Dictionary*
 * 
 * x (Integer) - the integer that represent the X-value of the SubBlock object on the JFrame
 * 
 * y (Integer) - the integer that represent the Y-value of the SubBlock object on the JFrame
 * 
 * width (Integer) - the integer that represent the width of the SubBlock object
 * 
 * height (Integer) - the integer that represent the height of the SubBlock object
 * 
 * currentBlock (Block) - the object of 'Block' class associated with the current SubBlock
 * 
 * upper (Boolean) - the boolean variable specifying whether or not the current SubBlock is located in the upper part of the Block associating with the current SubBlock
 * 
 * player (Player) - the object of 'Player' class related to the current SubBlock
 * 
 * PlayerIcon (JLabel) - the label of the player's name of this SubBlock
 * 
 * border (Border) - the line border that is used for the 'PlayerIcon' label
 * 
 */

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;

public class SubBlock {
	
	 Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

	 	int x;
		int y;
		int width;
		int height;
		boolean upper;
		Player player;
		JLabel PlayerIcon;
		Block currentBlock;
		
		public void setPlayer(Player thePlayer){
			player=thePlayer;
			if(thePlayer !=null) {
			PlayerIcon = new JLabel(player.getName());
			
			PlayerIcon.setHorizontalAlignment(0);
		if(upper==true)	PlayerIcon.setBounds(x, y, width+1, height);
		else PlayerIcon.setBounds(x, y, width+1, height);
			if (upper==true){
				PlayerIcon.setBackground(new Color(220,90,100));
				PlayerIcon.setBorder(border);
				PlayerIcon.setOpaque(true);
			}
			else {
				PlayerIcon.setBackground(new Color(120,170,245));
				PlayerIcon.setBorder(border);;
				PlayerIcon.setOpaque(true);
			}
			}
			else {
				PlayerIcon = new JLabel("");
				if(upper==true)	PlayerIcon.setBounds(x, y, width+1, height);
				else PlayerIcon.setBounds(x, y, width+1, height);
					if (upper==true){
						
						PlayerIcon.setBackground(new Color(220,90,100));
						PlayerIcon.setBorder(border);
						PlayerIcon.setOpaque(true);
					}
					else {
						PlayerIcon.setBackground(new Color(120,170,245));
						PlayerIcon.setBorder(border);
						PlayerIcon.setOpaque(true);
					}
			}
	
		}
		
		
		
		public void setLabel(JLabel newLabel) {
			PlayerIcon=newLabel;
		}
		
		public void setBlock(Block newBlock) {
			currentBlock=newBlock;
		}
	
		public void setX(int xVal){
			x=xVal;
		}
		
		public void setY(int yVal){
			y=yVal;
		}
		
		public void setHeight(int heightVal){
			height=heightVal;
		}
		
		public void setWidth(int widthVal){
			width=widthVal;
		}
		
		public void setUpper(boolean ValUpper){
			upper=ValUpper;
		}
		
		public JLabel getLabel() {
			return(PlayerIcon);
		}
		
		public Block getBlock() {
			return(currentBlock);
		}
		
		public Player getPlayer(){
			return(player);
		}
		
		public boolean getUpper()
		{
			return(upper);
		}

		public int getX()
		{
			return(x);
		}
		
		public int getY()
		{
			return(y);
		}
		
		public int getWidth()
		{
			return(width);
		}
		
		public int getHeight()
		{
			return(height);
		}
}
