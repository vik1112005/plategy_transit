import java.awt.Font;

import javax.swing.JLabel;


public class JLabel1 extends  JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel1(String str){
		super(str);
		 Font font=new Font("Serif", Font.BOLD,18);
		 this.setFont(font);
		
	}
}
