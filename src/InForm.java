
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;





//������� �����
public class InForm extends JDialog
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//��������� �������
	   private JTabbedPane p = new JTabbedPane();
	   //������ ��� ������ �� ������
	   private ArrayList<String> nameList,valueList;
	//������ ��� �������
		 public static MainPanel act;
		 ResPanel res;
		 KfPanel kf;
		 
		 //����� ������
		static JRadioButton rb0=new JRadioButton("-����/�����(�����)");
		static JRadioButton rb1=new JRadioButton("-�������(������)");
		 //JCheckBox useKf = new JCheckBox("-��������� ������� ������������");
		 static JButton prn=new JButton("������");
		 
		
//�����������
  public InForm() 
  {
	  
	  	act=new MainPanel();
		res=new ResPanel();
		kf=new KfPanel();
	  //�������� ������������� ������� �������������
	//  useKf.setSelected(true);
	  
	  //������ ������
	  prn.setEnabled(false);
	  //��������� ������ ������
	  prn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Printing(nameList,valueList);
				
			}});
	  
	  Toolkit toolkit = Toolkit.getDefaultToolkit();
      GraphicsConfiguration gc = this.getGraphicsConfiguration();
      Rectangle bounds = gc.getBounds();
      Insets insets = toolkit.getScreenInsets(gc);
      bounds.x += insets.left;
      bounds.y += insets.top;
      bounds.width -= insets.left + insets.right;
      bounds.height -= insets.top + insets.bottom;
      this.setBounds(bounds.x, bounds.y, (int)Math.rint(bounds.width/1.9), bounds.height);
	 
      
      Dimension size=new Dimension((int)Math.rint(bounds.width/2.5)-100, bounds.height-65);
	  p.setPreferredSize(size);
	 this.setResizable(false);
	   
	
	  //��������
	  this.setLayout(new BorderLayout());
	  //�����
	  Font font=new Font("Serif", Font.BOLD,18);
	 p.setFont(font);
//��������� �������� � ���������
	
	 
	 
	 p.addTab("���� ������" , act);
	 p.addTab("��������� ��������", res);
	 p.addTab( "������������", kf);
	
	 //��������� ������������ �������
 p.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
			int sv= ((JTabbedPane)e.getSource()).getSelectedIndex();
			
			
			switch (sv) 
			{ 
			case 0://InForm.this.useKf.setEnabled(true);
			res.boss.removeAll();
			rb0.setEnabled(true);
			rb1.setEnabled(true);
			prn.setEnabled(false);
			kf.upd();
			if(rb0.isSelected())InForm.this.rb0status();
			if(rb1.isSelected())InForm.this.rb1status();
			break;
			case 2:res.boss.removeAll();
					rb0.setEnabled(true);
					rb1.setEnabled(true);
					prn.setEnabled(false);
					//InForm.this.useKf.setEnabled(false);
					kf.upd();
					if(rb0.isSelected())InForm.this.rb0status();
					if(rb1.isSelected())InForm.this.rb1status();
			break; 
			case 1:rb0.setEnabled(false);
				rb1.setEnabled(false);
				prn.setEnabled(true);
				//InForm.this.useKf.setEnabled(false);
				nameList = new ArrayList<String>();
				valueList = new ArrayList<String>();
				nameList.clear();
				valueList.clear();
				kf.upd();
				
				kf.kfi=XmlIsSimple.getkf2(XmlIsSimple.getkf1(),act.editor20.getTextField().getText());
				kf.kfspeci=XmlIsSimple.getkf3(XmlIsSimple.getkf1(),act.editor20.getTextField().getText());
				//����������� ��������� ����� ���� ������������ �� ������� � ������ ������������
				double kp=1.0;
				if(rb1.isSelected())kp=Double.parseDouble(kf.kfspeci[2]);
				
				try{
					if(act.tf2.getText().length()==0){//��������� ����� ����������
						Double.parseDouble("error");}
				//���������� �������
				int kv=Integer.parseInt(act.tf5.getText());
				//���� ������ ����� ������ �������
				res.boss.add(new JLabel1("������� �������������� ������ �� ����"), new GridBagConstraints(0, 0, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("������� �������������� ������ �� ����");
				
				res.boss.add(new JLabel1(""+Integer.parseInt((String)act.tf1.getText())+" �"), new GridBagConstraints(8,0, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(""+Integer.parseInt((String)act.tf1.getText())+" �");
				
				res.boss.add(new JLabel1("����� ������"), new GridBagConstraints(0, 1, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("����� ������");
				
				res.boss.add(new JLabel1(act.tf2.getText()), new GridBagConstraints(8,1, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(act.tf2.getText());
				if(act.cb0.getSelectedIndex()==0){
					res.boss.add(new JLabel1("������"), new GridBagConstraints(0,2, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					if(rb0.isSelected()){
					nameList.add("������");	
					valueList.add(" ");
					}
				}else{
					res.boss.add(new JLabel1("�������"), new GridBagConstraints(0, 2, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("�������");
					valueList.add(" ");
				}
				res.boss.add(new JLabel1("������� �������� �����"), new GridBagConstraints(0,3, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("������� �������� �����");
				
				if(act.cb2.getSelectedIndex()==0){
					res.boss.add(new JLabel1("��"), new GridBagConstraints(8,3, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add("��");	
				/*------------------------*/}
				else{
					res.boss.add(new JLabel1("���"), new GridBagConstraints(8,3, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add("���");
				/*----------------------------------*/}
				
				String cb1;
				int tv=act.cb1.getSelectedIndex();
				if(tv==1){cb1="������������������";}
				else {cb1="�������������";}
				res.boss.add(new JLabel1("����� "+cb1), new GridBagConstraints(0, 4, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("����� "+cb1);
				
				res.boss.add(new JLabel1(" "), new GridBagConstraints(8,4, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(" ");
				
				double ks;
				if(act.cb3.getSelectedIndex()==1){cb1="�� ������������� ��� �������������";
				ks=1.0;
				}
				else {cb1="����������� ��� ������������";
				ks=0.5;
				}
				res.boss.add(new JLabel1("����� "+cb1), new GridBagConstraints(0, 5, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("����� "+cb1);
				
				res.boss.add(new JLabel1(" "), new GridBagConstraints(8,5, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(" ");
				
				res.boss.add(new JLabel1("���������� �������"), new GridBagConstraints(0, 6, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("���������� �������");
				
				res.boss.add(new JLabel1(act.tf5.getText()), new GridBagConstraints(8,6, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(act.tf5.getText());
				
				res.boss.add(new JLabel1("��� ����� ,����"), new GridBagConstraints(0, 7, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("��� ����� ,����");
				
				res.boss.add(new JLabel1(act.tf3.getText()), new GridBagConstraints(8,7, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(act.tf3.getText());
				/*
				res.boss.add(new JLabel1("���������� ������ ,���"), new GridBagConstraints(0, 8, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("���������� ������ ,���");
				
				res.boss.add(new JLabel1(act.tf4.getText()), new GridBagConstraints(8,8, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(act.tf4.getText());
				*/
				res.boss.add(new JLabel1("����� ��������"), new GridBagConstraints(0, 9, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("����� ��������");
				
				res.boss.add(new JLabel1(act.editor1.getTextField().getText()), new GridBagConstraints(8,9, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(act.editor1.getTextField().getText());
				
				res.boss.add(new JLabel1("����� �������"), new GridBagConstraints(0, 10, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("����� �������");
				
				res.boss.add(new JLabel1(act.editor2.getTextField().getText()), new GridBagConstraints(8,10, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(act.editor2.getTextField().getText());
				
				long ms=InForm.getdelayms(act.editor2.getTextField().getText(), act.editor1.getTextField().getText());
				float min=ms/60000;
				float hourd=min/60;
				long hour=Math.round(hourd);
				int ihour=(int)hour;
				float dayd=hourd/24;
				long dayl=ms/86400000;
				long day;
				if(dayd>dayl)day=dayl+1;
				else day=dayl;
				
				
				res.boss.add(new JLabel1("������� (�����������), �����"), new GridBagConstraints(0, 12, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("������� (�����������), �����");
				
				res.boss.add(new JLabel1(""+(float)Math.round(hourd*100)/100+"("+hour+")"), new GridBagConstraints(8,12, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(""+hour);
				
				res.boss.add(new JLabel1("�������, �����"), new GridBagConstraints(0, 13, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("�������, �����");
				
				res.boss.add(new JLabel1(""+day), new GridBagConstraints(8,13, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(""+day);
				
				//������� ����� �����������
				double sb;
				if(hour<=48){
						
					//sb=kv*ks*Double.parseDouble((String) kf.table.getValueAt(ihour,tv+1 ));
					sb=kv*ks*Double.parseDouble((String) kf.kfi[ihour][tv+1])*kp;
					//System.out.println("������");
						
					}else{
						
						sb=kv*ks*((ihour-48)*Double.parseDouble((String) kf.table.getValueAt(0,tv+1 ))+Double.parseDouble((String) kf.table.getValueAt(48,tv+1 )));
						
						
					}
				if(hour==0)sb=0;
				//������� ����� ��������
				double sh;//���� ��������
				double kg;
				kg=Double.parseDouble(kf.tf4.getText());//����������� ��������� �����
				
				
				if(act.cb2.getSelectedIndex()==1)kg=1.0;
				//System.out.println(act.l2.getSelectedIndex());
				if(act.l2.getSelectedIndex()==1)
				sh=Double.parseDouble(kf.tf5.getText())*kg*Double.parseDouble(act.tf3.getText())*(int)day;
				else
					sh=Double.parseDouble(kf.tf1.getText())*kg*Double.parseDouble(act.tf3.getText())*(int)day;	
				
				
				double mr;//���������� ������
				mr=Double.parseDouble(kf.tf2.getText())*Double.parseDouble(act.tf4.getText());
				

				//����� ������ �������� ����/�����
				if(rb0.isSelected()){
					
					
					
					
					
					
					
					
					
					res.boss.add(new JLabel1("���� ����������� �������, ���.������"), new GridBagConstraints(0, 14, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("���� ����������� �������, ���.������");
					
					res.boss.add(new JLabel1(del2(""+Math.rint(sb),0)), new GridBagConstraints(8,14, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add(del2(""+Math.rint(sb),0));
					if(act.cb0.getSelectedIndex()==0){
					double sbnds=sb*0.2;
					res.boss.add(new JLabel1("��� 20%, ���.������"), new GridBagConstraints(0, 15, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("��� 20%, ���.������");
					
					res.boss.add(new JLabel1(del2(""+Math.rint(sbnds),0)), new GridBagConstraints(8,15, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add(del2(""+Math.rint(sbnds),0));
					}
					if(sh!=0){
						
						
						
						
						
						
						
						
					res.boss.add(new JLabel1("���� ��������, ���.������"), new GridBagConstraints(0, 16, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("���� ��������, ���.������");
					
					res.boss.add(new JLabel1(del2(""+Math.rint(sh),0)), new GridBagConstraints(8,16, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add(del2(""+Math.rint(sh),0));
					if(act.cb0.getSelectedIndex()==0){
					double shnds=sh*0.2;
					res.boss.add(new JLabel1("��� 20%, ���.������"), new GridBagConstraints(0, 17, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("��� 20%, ���.������");
					
					res.boss.add(new JLabel1(del2(""+Math.rint(shnds ),0)), new GridBagConstraints(8,17, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add(del2(""+Math.rint(shnds ),0));
					}
					}
					
					
					
					
					
					
					
					
					res.boss.add(new JLabel1("���� ���������� ������ �� "+act.tf4.getText()+" ���., ���.������"), new GridBagConstraints(0, 18, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("���� ���������� ������ �� "+act.tf4.getText()+" ���., ���.������");
					
					res.boss.add(new JLabel1(del2(""+Math.rint(mr ),0) ), new GridBagConstraints(8,18, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add(del2(""+Math.rint(mr ),0));
					if(act.cb0.getSelectedIndex()==0){
					double mrnds=mr*0.2;
					
					
					
					res.boss.add(new JLabel1("��� 20%, ���.������"), new GridBagConstraints(0, 19, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("��� 20%, ���.������");
					
					res.boss.add(new JLabel1(del2(""+Math.rint(mrnds),0)), new GridBagConstraints(8,19, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add(del2(""+Math.rint(mrnds),0));
					}
					
					
					
					
					
					
					
					double nu;//���������
					double total;//���� �� �����������
					if(ihour>24&&act.cb3.getSelectedIndex()==1){
						nu=sb;
						res.boss.add(new JLabel1("��������� ,���.������ "), new GridBagConstraints(0, 20, 5, 1, 0, 0,
								GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
						nameList.add("��������� ,���.������ ");
						
						res.boss.add(new JLabel1(del2(""+Math.rint(nu),0)), new GridBagConstraints(8,20, 2, 1, 1, 1,
					            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
						valueList.add(del2(""+Math.rint(nu),0));
						
					}else{
						
						nu=0;
					}
					if(act.cb0.getSelectedIndex()==0)total=(sb+sh+mr)*1.2+nu;
					else total=(sb+sh+mr)+nu;
					res.boss.add(new JLabel1("�������� �� ���� , ���.������"), new GridBagConstraints(0, 21, 5, 1, 0, 0,
							GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					nameList.add("�������� �� ���� , ���.������");
					
					res.boss.add(new JLabel1(del2(""+Math.rint(total ),0)), new GridBagConstraints(8,21, 2, 1, 1, 1,
				            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
					valueList.add(del2(""+Math.rint(total ),0));
					
			}
				//����� ������ ������� �������
			else{
				double total=(sb+sh+mr)*1.2;
				res.boss.add(new JLabel1("���� ����������� �������, ������� ���"), new GridBagConstraints(0, 14, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("���� ����������� �������, ������� ���");
				
				res.boss.add(new JLabel1(okrug(sb)), new GridBagConstraints(8,14, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(okrug(sb));
				
				
				
				
				res.boss.add(new JLabel1("��� 20%, ������� ���"), new GridBagConstraints(0, 15, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("��� 20%, ������� ���");
				
				res.boss.add(new JLabel1(""+okrug(sb*0.2)), new GridBagConstraints(8,15, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(""+okrug(sb*0.2));
				
				
				
				
				
				
				
				
				
				
				res.boss.add(new JLabel1("���� ��������, ������� ���"), new GridBagConstraints(0, 16, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("���� ��������, ������� ���");
				
				res.boss.add(new JLabel1(okrug(sh)), new GridBagConstraints(8,16, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(okrug(sh));
				
				
				
				
				res.boss.add(new JLabel1("��� 20%, ������� ���"), new GridBagConstraints(0, 17, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("��� 20%, ������� ���");
				
				res.boss.add(new JLabel1(""+okrug(sh*0.2)), new GridBagConstraints(8,17, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(""+okrug(sh*0.2));
				
				
				
				
				
				
				res.boss.add(new JLabel1("���� ���������� ������ �� "+act.tf4.getText()+" ���., ������� ���"), new GridBagConstraints(0, 18, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("���� ���������� ������ �� "+act.tf4.getText()+" ���., ������� ���");
				
				res.boss.add(new JLabel1(okrug(mr)), new GridBagConstraints(8,18, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(okrug(mr));
				
				
				
				
				
				res.boss.add(new JLabel1("��� 20%, ������� ���"), new GridBagConstraints(0, 19, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("��� 20%, ������� ���");
				
				res.boss.add(new JLabel1(""+okrug(mr*0.2)), new GridBagConstraints(8,19, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(""+okrug(mr*0.2));
				
				
				
				
				
				
				
				
				
				res.boss.add(new JLabel1("�������� �� ���� , ������� ���"), new GridBagConstraints(0, 20, 5, 1, 0, 0,
						GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				nameList.add("�������� �� ���� , ������� ���");
				
				res.boss.add(new JLabel1(okrug(total )), new GridBagConstraints(8,20, 2, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				valueList.add(okrug(total ));
				
			}
				//��������� ���
				
				//ArrayList indata = new ArrayList();
				//indata.clear();
				
		String[] indata={
				""+(XmlIsSimple.p),
				""+rb0.isSelected(),
				act.tf1.getText(),
				act.tf2.getText(),
				act.editor20.getTextField().getText(),
				""+act.cb0.getSelectedIndex(),
				""+act.cb1.getSelectedIndex(),
				""+act.cb2.getSelectedIndex(),
				act.tf3.getText(),
				act.tf4.getText(),
				""+act.cb3.getSelectedIndex(),
				act.editor1.getTextField().getText(),
				act.editor2.getTextField().getText(),
				act.tf5.getText()};
		        
				XmlIsSimple.saveAct(indata,getPoint(),getDimension());
				//actWrite(act.tf1.getText(),indata);
				
				} catch(IllegalArgumentException ex) { 
					//System.out.println("�����������1");
					
						p.setSelectedIndex(0);
						
						InForm.this.except();
						//sh=0;
					} 
				break; 
			 
			}
			
		}});
	
	 //��������� � ���� ��������� �������
	 add(p, BorderLayout.NORTH);
	 //������ � ����� �������� ��� ������ ���� ��������
	 JPanel p0=new JPanel();
		rb0.setSelected(true);
		rb0.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 rb0status();
       
      }
  });
		rb1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 rb1status();
         	
      }
  });
		//p0.add(useKf);
		p0.add(rb0);
		p0.add(rb1);
		p0.add(prn);
		 add(p0, BorderLayout.SOUTH);
	  
	//�������� ��� �������� ����  
	  
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
   
    
    //��� ����
    setTitle("������� �� ����");
   
  }
  private void rb0status(){
	  
	  if(rb0.isSelected())rb1.setSelected(false);
      kf.table.setEnabled(true);
      kf.but2.setEnabled(true);
      kf.tf1.setEnabled(true);
      kf.tf2.setEnabled(true);
      kf.tf3.setEnabled(true);
      kf.tf4.setEnabled(true);
      kf.tf5.setEnabled(true);
       kf.upd(); 
       
       act.l0.setEnabled(true);
       act.cb0.setEnabled(true);
	  
	  
	  
  }
  
  
private void rb1status(){
	  
	  
	  
	  if(rb1.isSelected() )rb0.setSelected(false);
  	 
  	 kf.upd();
  	 kf.tf1.setEnabled(false);
      kf.tf2.setEnabled(false);
      kf.tf3.setEnabled(false);
      kf.tf4.setEnabled(false);
      kf.tf5.setEnabled(false);
  	 kf.but2.setEnabled(false);
  	kf.table.setEnabled(false);
  	act.l0.setEnabled(false);
     act.cb0.setEnabled(false);
	  
  }
  
  
  private static long getdelayms(String strdata1,String strdata2) {
      
      SimpleDateFormat format = new SimpleDateFormat("dd MMMyyyy-HH:mm");
      Calendar cal1 = null;
      Calendar cal2 = null;
            try {
				Date date1 = format.parse(strdata1);
				Date date2= format.parse(strdata2);
				cal1 = Calendar.getInstance();
	            cal1.setTime(date1);
	            cal2 = Calendar.getInstance();
	            cal2.setTime(date2);
	            
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            long time1 = cal1.getTimeInMillis() + cal1.getTimeZone().getOffset(cal1.getTimeInMillis());
            long time2 = cal2.getTimeInMillis() + cal2.getTimeZone().getOffset(cal2.getTimeInMillis());
            return Math.abs(time1 - time2);
    }
  
  //����� ���������� ����� �� 2 ������
  public static String okrug(double sb){
  	
	  BigDecimal bigDecimal = new BigDecimal(sb);
	  bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
	  return ""+bigDecimal.doubleValue();
  	
  }
  
  //�������� ��� ��������� �������
public static String del2(String str,int offset){
	
	
	if(str.indexOf(".")>0)return str.substring(0,str.indexOf(".")+offset);
	else return str;
	
}

//����� ��� ������ ���� ��������
public static String dataotpr(){
	//System.out.println("*");
	return act.editor20.getTextField().getText();
	
}



//����� ��������� ���� ���� �� ��������� ��� ���� 
  //���� �� ������������
   private  void except(){
	 Point p= this.getLocation();
	 Dimension dm=this.getSize();
	  final JDialog dialog=new JDialog();
	  
	  dialog .setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	  JLabel dl=new JLabel1("�� ��������� ��� ����!");
	  dl.setFont(new Font("Serif", Font.BOLD, 22));
	  dl.setForeground(Color.red);
	  dialog.add(dl);
	  dialog.setLocation(p.x+(int)Math.rint(dm.width/2)-120,p.y+ (int)Math.rint(dm.height/2));
	  dialog.setUndecorated(true);
	  
	  
	  dialog.addFocusListener(new FocusListener(){

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			//dialog.dispose();
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			dialog.dispose();
		}});
	  dialog.setVisible(true);
	  dialog.pack();
	 
	  
  }
   private  Point getPoint(){
	   Point p =this.getLocation();
	   return p;
   }
   private  Dimension getDimension(){
	   Dimension dm=this.getSize();
	   return dm;
   }
   
   
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			InForm inf=new InForm();
			
			
			//���������
			inf.setVisible(true);
			
	}
  
  
}


