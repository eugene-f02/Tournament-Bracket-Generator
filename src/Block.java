// Eugene Fedoriv
//
// Block class
//
// Provides a template for creating a cell on a tournament bracket containing two players that compete against each other. It also provides an access
//  to the information about the block and the two corresponding sub-blocks.


/*			*Variable Dictionary*
 * 
 * x (Integer) - the integer that represent the X-value of the Block object on the JFrame
 * 
 * y (Integer) - the integer that represent the Y-value of the Block object on the JFrame
 * 
 * width (Integer) - the integer that represent the width of the Block object
 * 
 * height (Integer) - the integer that represent the height of the Block object
 * 
 * upper (SubBlock) - the object of the SubBlock class associated with this Block which lies in the upper part of this Block
 * 
 * lower (SubBlock) - the object of the SubBlock class associated with this Block which lies in the lower part of this Block
 * 
 * num (Integer) - the Integer representing the number of this Block regarding the Tournament net in the JFrame
 * 
 * level (Integer) - the Integer representing the level of this Block regarding the Tournament net in the JFrame
 * 
 * counterOfSubBlocksInTheBlock (Integer) - the Integer that counts how many SubBlocks are currently used by this Block.
 * 
 */

public class Block {
	boolean MMOpened=false;//+V D 
	int x;
	int y;
	int width;
	int height;
	SubBlock upper,lower;
	int num;
	int level;
	int counterOfSubBlocksInTheBlock=0;
	
	Block(int number){
		num=number;
	}
	
	
	public void addSubBlock(SubBlock subBlock) {
		if (subBlock.getUpper()==true) upper=subBlock;
		else lower = subBlock;
		counterOfSubBlocksInTheBlock++;
		
	}
	

	
	public int getLevel() {
		return(level);
	}
	
	public int getNum() {
		return(num);
	}
	
	public int getCounter() {
		return(counterOfSubBlocksInTheBlock);
	}
	
	public SubBlock getLowerSB(){
		return(lower);
	}
	
	public SubBlock getUpperSB(){
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
	
	public boolean getWhetherMMisOpened() {
		return (MMOpened);
	}
	
	
	
	public void setCounter( int theCounter) {
		counterOfSubBlocksInTheBlock=theCounter;
	}
	
	public void setLevel(int theLevel) {
		level= theLevel;
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
	
	public void setMMOpened(Boolean opened) {
		MMOpened =opened;
	}
}
