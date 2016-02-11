import java.awt.*;
import java.util.ArrayList;
//печать на принтер
public class Printing extends Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Printing(ArrayList<String> nameList,ArrayList<String> valueList){
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		//связываем приложение с принтером
		PrintJob printJob=toolkit.getPrintJob(this,"Printing test",null);
		
		if(printJob!=null){
			//получаем графический контекст принтера
			Graphics graphics=printJob.getGraphics();
			//шрифт
			graphics.setFont(new Font("Courier",Font.ITALIC,10));
			//выводим заголовки
			for (int i = 0; i < nameList.size(); i++) {
				String str=(String)nameList.get(i);
				if((String)valueList.get(i)!=" ")str=filling(str);
				graphics.drawString(str,30,12*i+40);	
			}
			//шрифт 
			
			graphics.setFont(new Font("Courier",Font.BOLD,10));
			//выводим данные
			for (int i = 0; i < nameList.size(); i++) {
				
				graphics.drawString((String)valueList.get(i),280,12*i+40);	
			}
			//заканчиваем печать
			printJob.end();
		}
	}
	//добавляет подчеркивание между заголовком и данными
	private static String filling(String str){
		int og=40-str.length();
		for(int i=0;i<=og;i++)str=str+"_";
		return str;
		}
}
