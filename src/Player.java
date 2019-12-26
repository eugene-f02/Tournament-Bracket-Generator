// Eugene Fedoriv
//
// Player class
//
// Provides a template for creating a player for the tournament, as well as accessing information about the player


/*			*Variable Dictionary*
 * 
 * name (String) - the variable representing the name of a player
 * 
 * details (String) - the variable containing some additional information about a player
 * 
 * score (Integer) - the variable representing how many games a player has won
 * 
 */

public class Player {

	String name, details;
	int score;
	
	Player(String playerName, String playerDetails){
		name = playerName;
		details = playerDetails;
		score = 0;
	}
	
	Player(){
		
	}
	
	public String getName() {
		String theName = name;
		return theName;
	}
	
	public String getDetails() {
		String theDetails = details;
		return theDetails;
	}
	
	public int getScore() {
		int theScore = score;
		return theScore;
	}
	
	void setName(String theName) {
		name = theName;
	}
	void setDetails(String theDetails) {
		details = theDetails;
	}
	void setScore(int theScore) {
		score = theScore;
	}
}
