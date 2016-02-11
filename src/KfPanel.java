import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.*;

//����� ��� ��������� � �������������� �������������
public class KfPanel extends JPanel{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//������� ��� �������� �������������	
	String[][] kft=new String[3][49];
	String[][] kfi=new String[3][49];
	String[] kfspect=new String[3];
	String[] kfspeci=new String[3];
	//������ ��� ���������� � ���������� 
	JButton but1=new JButton("��������");
	JButton but2=new JButton("���������");
	//����� ������ �������
	JLabel1 lb=new JLabel1("������������(����/�����)");
	//����� ��������� �������������
	JLabel1 lb1=new JLabel1("����������� ��������(� ������)");
	JLabel1 lb2=new JLabel1("����������� ���������� ������(� ����������)");
	JLabel1 lb3=new JLabel1("����������� ���������");
	JLabel1 lb4=new JLabel1("����������� �������� �����");
	JLabel1 lb5=new JLabel1("����������� ��������");
	//��������� ���� ��� ����� �������������
	JTextField tf1=new JTextField(10);
	JTextField tf2=new JTextField(10);
	JTextField tf3=new JTextField(10);
	JTextField tf4=new JTextField(10);
	JTextField tf5=new JTextField(10);
	//������� � ��������������
	final JTable table;
	 //���������� ����� ��������������
	
	//�����������
	KfPanel(){
		//������� ���������  ���������� (������  �� ���� ����� ���� ����� � �������� ���������� ����������)
		KeyListener kl=new KeyAdapter(){
			
			  public void keyTyped(java.awt.event.KeyEvent e) {
		            char a = e.getKeyChar();
		            if (!Character.isDigit(a)
		            		&& (a != '.')	
		                && (a != '\b')) {
		              e.consume();
		            }
		          }
		};
		tf1.addKeyListener(kl);
		tf2.addKeyListener(kl);
		tf3.addKeyListener(kl);
		tf4.addKeyListener(kl);
		tf5.addKeyListener(kl);
		// Font font=new Font("Serif", Font.BOLD,18);
		//��������
		this.setLayout(new BorderLayout());
		//����/����� �� ���������
		InForm.rb0.setSelected(true);
		
		 //������ ���������� �������� �������
	    String [] headers = { "���������� �����", "�����������-��","�����������-��"};
	    //����� ������� ����
	   // createfile(kfile);
	    //����� ������ ������������� �� �����
		kft=XmlIsSimple.getkf2(XmlIsSimple.getkf1(),InForm.dataotpr());
		kfspect=XmlIsSimple.getkf3(XmlIsSimple.getkf1(),InForm.dataotpr());
		tf1.setText(kfspect[0]);
		tf2.setText(kfspect[1]);
		tf3.setText(kfspect[2]);
		tf4.setText(kfspect[3]);
		tf5.setText(kfspect[4]);
		//�������
		 table = new JTable(kft, headers);
		// table.setFont(font);
		//��������� ������ ������
        but1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upd();
		     }
		});
        
        but2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = Calendar.getInstance();
				
				String date=calendar.get(Calendar.DATE)+"."+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.YEAR);
				
				String[] kfs={tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),date};
				
				for(int i=0;i<=table.getRowCount()-1;i++){
					
					kft[i][1]=(String) table.getValueAt( i, 1);
					kft[i][2]=(String) table.getValueAt( i, 2);
					kft[i][0]=""+i;
					if(i==0)kft[i][0]="����� 48";
				}
				//������ ������������� � ����
				//setkf(kfile,kft);
				XmlIsSimple.setkf(kft,kfs);
		     }
		}); 
        //��������� ��� �� ������
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel pan=new JPanel();
        pan.setLayout(new GridBagLayout());
       
        
        pan.add(lb1, new GridBagConstraints(0, 0,1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0,5, 0), 0, 6));
        pan.add(tf1, new GridBagConstraints(5,0,1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(lb5, new GridBagConstraints(0, 1, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(tf5, new GridBagConstraints(5,1, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(lb2, new GridBagConstraints(0, 2, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(tf2, new GridBagConstraints(5,2, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(lb3, new GridBagConstraints(0, 3, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(tf3, new GridBagConstraints(5,3, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(lb4, new GridBagConstraints(0, 4, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(tf4, new GridBagConstraints(5,4, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        
        pan.add(but1, new GridBagConstraints(2, 5, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        pan.add(but2, new GridBagConstraints(5,5, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        
        this.add(lb, BorderLayout.NORTH);
       
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pan, BorderLayout.SOUTH);
        
        this.setVisible(true);
   }
	//����� ��� ���������� �������
	public  void upd(){
		// String kfile=choosname(InForm.rb0.isSelected());
			//createfile(kfile);
			kft=XmlIsSimple.getkf2(XmlIsSimple.getkf1(),InForm.dataotpr());
			kfspect=XmlIsSimple.getkf3(XmlIsSimple.getkf1(),InForm.dataotpr());
			tf3.setText(kfspect[2]);
			tf4.setText(kfspect[3]);
			tf5.setText(kfspect[4]);
			for(int i=0;i<=table.getRowCount()-1;i++){
				table.setValueAt(kft[i][0], i, 0);
				if (InForm.rb0.isSelected()){
				table.setValueAt(kft[i][1], i, 1);
				table.setValueAt(kft[i][2], i, 2);
				tf1.setText(kfspect[0]);
				tf2.setText(kfspect[1]);
				
				}
				else{
					 
					table.setValueAt(InForm.okrug(Double.parseDouble(kft[i][1])*Double.parseDouble(tf3.getText())), i, 1);
					table.setValueAt(InForm.okrug(Double.parseDouble(kft[i][2])*Double.parseDouble(tf3.getText())), i, 2);
					tf1.setText(InForm.okrug(Double.parseDouble(kfspect[0])*Double.parseDouble(tf3.getText())));
					tf2.setText(InForm.okrug(Double.parseDouble(kfspect[1])*Double.parseDouble(tf3.getText())));
					}
				
				
			}
			
		
	}}
	
	

