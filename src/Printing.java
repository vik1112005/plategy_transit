import java.awt.*;
import java.util.ArrayList;
//������ �� �������
public class Printing extends Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Printing(ArrayList<String> nameList,ArrayList<String> valueList){
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		//��������� ���������� � ���������
		PrintJob printJob=toolkit.getPrintJob(this,"Printing test",null);
		
		if(printJob!=null){
			//�������� ����������� �������� ��������
			Graphics graphics=printJob.getGraphics();
			//�����
			graphics.setFont(new Font("Courier",Font.ITALIC,10));
			//������� ���������
			for (int i = 0; i < nameList.size(); i++) {
				String str=(String)nameList.get(i);
				if((String)valueList.get(i)!=" ")str=filling(str);
				graphics.drawString(str,30,12*i+40);	
			}
			//����� 
			
			graphics.setFont(new Font("Courier",Font.BOLD,10));
			//������� ������
			for (int i = 0; i < nameList.size(); i++) {
				
				graphics.drawString((String)valueList.get(i),280,12*i+40);	
			}
			//����������� ������
			printJob.end();
		}
	}
	//��������� ������������� ����� ���������� � �������
	private static String filling(String str){
		int og=40-str.length();
		for(int i=0;i<=og;i++)str=str+"_";
		return str;
		}
}
