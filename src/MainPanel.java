import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;

import org.jdom2.Element;

import java.util.Calendar;
import java.util.Date;
//Класс для ввода данных
public class MainPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gb; 
		public JLabel l0;
	public JTextField tf1,tf2,tf3,tf4,tf5;	
	 
	public JComboBox cb0,cb1,cb2,cb3 ,l2;
	public JSpinner.DateEditor editor1,editor2,editor20;
	public int lastkf=0;
	public MainPanel(){
		
		Font font=new Font("Serif", Font.BOLD,18);
		//выбираем ввоз/вывоз
		InForm.rb0.setSelected(true);
		
		
		this.setLayout(new GridLayout(1,1));
		//панель для элементов интерфейса
		JPanel boss=new JPanel();
		//рамка невидимая
		Border border = BorderFactory.createEmptyBorder(5,12,5,12);
		boss.setBorder(border);
		
		add(boss);
		gb=new GridBagLayout(); 
		
		
		//менеджер добавляем к панели
		
		boss.setLayout(gb);
		//элементы интерфейса
		JLabel l1= new JLabel("Акт №");
		l1.setFont(font);
		
		
		
			tf1 = new JTextField();
			tf1.setFont(font);
			
			
			//слушатель ввод только цифр
		tf1.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyTyped(java.awt.event.KeyEvent e) {
	            char a = e.getKeyChar();
	            if (!Character.isDigit(a)
	                && (a != '\b')) {
	              e.consume();
	            }
	          }
	        });
		
		

		
		tf1.setColumns(4);
		//выравнивание справа
		tf1.setHorizontalAlignment(JTextField.RIGHT);
		//буква К
		JLabel l01= new JLabel("К");
		l01.setFont(font);
		//размещаем элементы в окне
		boss.add(l1, new GridBagConstraints(0, 0, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		
		//Выбор предыдущих рассчетов
		JButton button = new JButton("Найти");
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setFont(font);
        boss.add(button, new GridBagConstraints(4,0,1, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	
            	//SelectAct sa=new SelectAct(XmlIsSimple.getlistacts(),MainPanel.this);
            	
            	
            	
            	
            	 Element el=	XmlIsSimple.openAct("act"+MainPanel.this.tf1.getText()); 
                 //  System.out.println(el);
            	 if(el!=null){
                     	  String line;
                   	//считываем из файла данные для ввода
                     	 line = el.getAttributeValue("rb");
                   		//System.out.println(el.getName().toString());
                   		InForm.rb0.setSelected(Boolean.parseBoolean(line));
                   		InForm.rb1.setSelected(!Boolean.parseBoolean(line));
                   		
                   		line = el.getName().substring(3);
                   		MainPanel.this.tf1.setText(line);
                   	 	
                   		line = el.getAttributeValue("numvag");
                   		MainPanel.this.tf2.setText(line);
                   		
                   		line = el.getAttributeValue("otpravka");
                   		MainPanel.this.editor20.getTextField().setText(line);
                   		
                   		line = el.getAttributeValue("napr");
                   		MainPanel.this.cb0.setSelectedIndex(Integer.parseInt(line));
                   		
                   		line = el.getAttributeValue("vk");
                   		MainPanel.this.l2.setSelectedIndex(Integer.parseInt(line));
                   		
                   		line = el.getAttributeValue("type");
                   		MainPanel.this.cb1.setSelectedIndex(Integer.parseInt(line));
                   		
                   		line = el.getAttributeValue("og");
                   		MainPanel.this.cb2.setSelectedIndex(Integer.parseInt(line));
                   		
                   		line = el.getAttributeValue("ves");
                   		MainPanel.this.tf3.setText(line);
                   	 	
                   		line = el.getAttributeValue("hmr");
                   		MainPanel.this.tf4.setText(line);
                   		
                   		line = el.getAttributeValue("sob");
                   		MainPanel.this.cb3.setSelectedIndex(Integer.parseInt(line));
                   		
                   		line = el.getAttributeValue("time1");
                   		MainPanel.this.editor1.getTextField().setText(line);
                   		
                   		line = el.getAttributeValue("time2");
                   		MainPanel.this.editor2.getTextField().setText(line);
                   		
                   		line = el.getAttributeValue("kol");
                   		MainPanel.this.tf5.setText(line);
                   		line = el.getAttributeValue("numkf");
                   		MainPanel.this.lastkf=Integer.parseInt(line);	
            	
            	 }
            	
            	
            	//sa.setLocation(x, y);
            	//sa.setTitle("Список актов");
           } });	
		
		
		
				boss.add(tf1, new GridBagConstraints(8,0,1, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(l01, new GridBagConstraints(9, 0, 1, 1, 0, 0,
			            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				
				
				
				
				
				
		
				String[] items5 = {
					    "Вагон №",
					    "Контейнер №"};
				l2=new JComboBox(items5);
		l2.setFont(font);
			tf2=new JTextField(33);
			tf2.setFont(font);
			tf2.setText("");
			//слушатель ограничение количества вводимых символов
			tf2.addKeyListener(new java.awt.event.KeyAdapter() {
			      public void keyPressed(KeyEvent e) {
			          //обработка нажатия любой кнопки
			    	 if(tf2.getText().length()>=53)tf2.setText(tf2.getText().substring(0,52));
			       }
			       public void keyTyped(KeyEvent e) {
			         //обработка нажатия именно символьной кнопки
			       }

			     });
		boss.add(l2, new GridBagConstraints(0, 1, 2, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(tf2, new GridBagConstraints(2,1,GridBagConstraints.REMAINDER, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		
	
				//Дата отправки
				JLabel l120= new JLabel("Дата отправки");
				l120.setFont(font);
				Date now20 = new Date();  
		        final SpinnerDateModel model20 = new SpinnerDateModel(now20, null, now20,  
		                                                            Calendar.DAY_OF_WEEK); 
		        JSpinner spinner20 = new JSpinner(model20);  
		        spinner20.setFont(font);
		     
		        editor20 = new JSpinner.DateEditor(spinner20,  
		                                                             "dd.MM.yyyy");  
		        editor20.getTextField().setColumns(6);
		        JFormattedTextField ftf20 = editor20.getTextField();  
		        ftf20.setEditable(true);  
		        ftf20.setHorizontalAlignment(JTextField.CENTER);  
		        ftf20.setBackground(Color.white); 
		        spinner20.setEditor(editor20);
				
		        boss.add(l120, new GridBagConstraints(0, 2, 5, 1, 0, 0,
			            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
						boss.add(spinner20, new GridBagConstraints(4,2,6, 1, 1, 1,
			            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
						
				
				
				
				
				
		l0= new JLabel("Направление(ИМ-импорт,ЭКС-экспорт)");
		l0.setFont(font);
		l0.setEnabled(InForm.rb0.isSelected());
		String[] items0 = {
			    "ИМ",
			    "ЭКС"};
		cb0=new JComboBox(items0);
		cb0.setFont(font);
				boss.add(l0, new GridBagConstraints(0, 3, 5, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		boss.add(cb0, new GridBagConstraints(8, 3, 2, 1, 0, 0,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		cb0.setEnabled(InForm.rb0.isSelected());
		
		
		JLabel l3= new JLabel("Тип вагона(УВ-Универсальный,СВ-Специализированный)");
		l3.setFont(font);
		String[] items1 = {
			    "УВ",
			    "СВ"};
		cb1=new JComboBox(items1);
		cb1.setFont(font);
		//cb1.setSelectedIndex(0);
		boss.add(l3, new GridBagConstraints(0, 4, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(cb1, new GridBagConstraints(8,4,2, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		
		
		
		JLabel l4= new JLabel("Опасные грузы");
		l4.setFont(font);
		String[] items2 = {
			    "ОГ",
			    "--"};
		cb2=new JComboBox(items2);
		cb2.setFont(font);
		cb2.setSelectedIndex(0);
		boss.add(l4, new GridBagConstraints(0, 5, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(cb2, new GridBagConstraints(8,5,2, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		
		JLabel l6= new JLabel("Вес груза,тонн");
		l6.setFont(font);
		tf3=new JTextField(8);
		tf3.setFont(font);
		tf3.setHorizontalAlignment(JTextField.RIGHT);
		//ограничение ввода только цифры и точка
		tf3.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyTyped(java.awt.event.KeyEvent e) {
	            char a = e.getKeyChar();
	            if (!Character.isDigit(a)
	            		&& (a != '.')
	               && (a != '\b')) {
	              e.consume();
	            }
	          }
	        });
		boss.add(l6, new GridBagConstraints(0, 6, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(tf3, new GridBagConstraints(8,6,2, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		
		
		
		
		
		JLabel l8= new JLabel("Маневр.работа,час");
		l8.setFont(font);
		tf4=new JTextField(8);
		tf4.setFont(font);
		tf4.setText("1");
		tf4.setHorizontalAlignment(JTextField.RIGHT);
		//слушатель ограничение ввода
		tf4.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyTyped(java.awt.event.KeyEvent e) {
	            char a = e.getKeyChar();
	            if (!Character.isDigit(a)
	            		&& (a != '.')
	               && (a != '\b')) {
	              e.consume();
	            }
	          }
	        });
		boss.add(l8, new GridBagConstraints(0, 7, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(tf4, new GridBagConstraints(8,7,2, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		JLabel l9= new JLabel("Собственник(СОБ-Собственный,Ж-ж.д. администрация)");
		l9.setFont(font);
		String[] items3 = {
			    "СОБ",
			    "Ж"};
		 cb3=new JComboBox(items3);
		 cb3.setFont(font);
		boss.add(l9, new GridBagConstraints(0, 8, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(cb3, new GridBagConstraints(8,8,2, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		JLabel l10= new JLabel("Время задержки");
			 l10.setFont(font);
		
		Date now1 = new Date();  
        final SpinnerDateModel model1 = new SpinnerDateModel(now1, null, now1,  
                                                            Calendar.DAY_OF_WEEK);  
        JSpinner spinner1 = new JSpinner(model1);  
       spinner1.setFont(font);
     
        editor1 = new JSpinner.DateEditor(spinner1,  
                                                             "dd MMM yyyy-HH:mm");  
        editor1.getTextField().setColumns(10);
        JFormattedTextField ftf1 = editor1.getTextField();
        ftf1.setEditable(true); 
        ftf1.setHorizontalAlignment(JTextField.CENTER);  
        ftf1.setBackground(Color.white);  
         spinner1.setEditor(editor1);
		
        boss.add(l10, new GridBagConstraints(0, 9, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(spinner1, new GridBagConstraints(4,9,6, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		
		
		
		
		JLabel l12= new JLabel("Время выпуска");
		l12.setFont(font);
		Date now2 = new Date();  
        final SpinnerDateModel model2 = new SpinnerDateModel(now2, null, now2,  
                                                            Calendar.DAY_OF_WEEK); 
        JSpinner spinner2 = new JSpinner(model2);  
        spinner2.setFont(font);
     
        editor2 = new JSpinner.DateEditor(spinner2,  
                                                             "dd MMM yyyy-HH:mm");  
        editor2.getTextField().setColumns(10);
        JFormattedTextField ftf2 = editor2.getTextField();  
        ftf2.setEditable(true);  
        ftf2.setHorizontalAlignment(JTextField.CENTER);  
        ftf2.setBackground(Color.white); 
        spinner2.setEditor(editor2);
		
        boss.add(l12, new GridBagConstraints(0, 10, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(spinner2, new GridBagConstraints(4,10,6, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
		
		
		
		
			//	NumberFormatter formatter2 = new NumberFormatter(new DecimalFormat("#"));
		
		JLabel l14= new JLabel("Количество вагонов");
		l14.setFont(font);
		tf5 = new JTextField();
		tf5.setFont(font);
		//слушатель
		tf5.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyTyped(java.awt.event.KeyEvent e) {
	            char a = e.getKeyChar();
	            if (!Character.isDigit(a)
	               && (a != '\b')) {
	              e.consume();
	            }
	          }
	        });
		tf5.setColumns(4);
		tf5.setHorizontalAlignment(JTextField.RIGHT);

		boss.add(l14, new GridBagConstraints(0, 11, 5, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				boss.add(tf5, new GridBagConstraints(8,11,2, 1, 1, 1,
	            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 6));
				
		
	}
	

	
}
