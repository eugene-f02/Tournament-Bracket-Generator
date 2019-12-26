// Eugene Fedoriv
//
// Errors class
//
// Provides a template for creating pop-up errors and a method to catch non-number user input when a numeric input is expected


import javax.swing.JOptionPane;

public class Errors {
	
	 public static void error(String info, String title) // error messages
	    {
	        JOptionPane.showMessageDialog(null, info,title, JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	   public static boolean isNumeric(String str)  // used to tell if a string is numeric (all numbers)
	   {  
		 int x;
	     try  
	     {  
	       x = Integer.parseInt(str);  
	     }  
	     catch(NumberFormatException nfe)  
	     {  
	       return false;  
	     }  
	     return true;  
	   }
}
