import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

//панель в панели на которую будут  выводится результаты рассчета
//одна GridLayout
//вторая GridBagLayout
public class ResPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagLayout gb; 
	JPanel boss;
	ResPanel(){
		setLayout(new GridLayout(1,1));
		boss=new JPanel();
		//невидимая рамка чтобы получились отступы 
		Border border = BorderFactory.createEmptyBorder(5,12,5,12);
		boss.setBorder(border);
		add(boss);
		gb=new GridBagLayout(); 
		boss.setLayout(gb);
		
		
	}

}
