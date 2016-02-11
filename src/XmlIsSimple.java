import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class XmlIsSimple {
	//индекс коэффициентов
	static  int p=0;
	//метод для извлечения коэффициентов в лист из файла xml
    public static List<Element> getkf1() {
    	
        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("coefficients.xml");
            Document rDoc = parser.build(fr);
           fr.close();
           List<Element> temp = rDoc.getRootElement().getChildren();
           
            return temp;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
		
		
    }
    
  //метод для извлечения коэффициентов в массив из листа элеметов
  	public static String[][] getkf2(List <Element> temp,String data){
  		//System.out.println(data);
  		p=-1;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date1=null,date2=null;
        try {
			date1 = format.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i=temp.size()-1;i>=0;i--){
        	//System.out.println("1-"+i);
        	Element tmp = temp.get(i);
        	try {
        		date2 = format.parse(tmp.getAttributeValue("data"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        if(	!date1.before(date2)){
        	
        	p=i;
        	break;
        	}
        }
        
  		//берем последние коэффициенты если р -отрицательное
  		if(p<0)p=temp.size()-1;
  		
  		String[][] kf=new String[49][3];
  		Element tmp = temp.get(p);
  		for(int i=0;i<=48;i++){
  			
  			
  			 kf[i][0] =""+(double)i;
  			 if(i==0)		 kf[i][0] ="более 48";
  			 kf[i][1] = tmp.getAttributeValue("kbu"+i);
  			kf[i][2] = tmp.getAttributeValue("kbs"+i);
  			
  			
  		}
  		
  		return kf;
  		}
  	
  	 //метод для извлечения коэффициентов в массив
  	public static String [] getkf3(List <Element> temp,String data){
  		//System.out.println(data);
  		
  		p=-1;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date1=null,date2=null;
        try {
			date1 = format.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i=temp.size()-1;i>=0;i--){
        	//System.out.println("2-"+i);
        	Element tmp = temp.get(i);
        	try {
        		date2 = format.parse(tmp.getAttributeValue("data"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        if(	!date1.before(date2)){
        	p=i;
        	break;
        	}
        }
  		
  		
  		if(p<0)p=temp.size()-1;
  		String[] kf=new String[5];
  	 	Element tmp = temp.get(p);
  		kf[0]=tmp.getAttributeValue("khv");
  		kf[1]=tmp.getAttributeValue("mr");
  		kf[2]=tmp.getAttributeValue("kp");
  		kf[3]=tmp.getAttributeValue("kog");
  		kf[4]=tmp.getAttributeValue("khk");
  		return kf;
  		}
  	
  	
  	//запись в xml файл
  	public static void setkf(String[][] kfa,String[] kfs){
  		Element temp=null;
  		Document rDoc=null;
  		int n;
  		try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("coefficients.xml");
            rDoc = parser.build(fr);
            fr.close();
            temp = rDoc.getRootElement();
            
             n= temp.getChildren().size();
             Element kf=new Element("kf");
             kf.setAttribute("name", ""+n);
             kf.setAttribute("data", kfs[4]);
             kf.setAttribute("kog", kfs[3]);
             kf.setAttribute("kh", kfs[0]);
             kf.setAttribute("mr", kfs[1]);
             kf.setAttribute("kp", kfs[2]);
             
             for(int i=0;i<=48;i++){
            	 kf.setAttribute("kbs"+i, kfa[i][2]);	
				}	        
             	for(int i=0;i<=48;i++){
            	 kf.setAttribute("kbu"+i, kfa[i][1]);	
				}	       
             temp.addContent(kf);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
  		
  		save(rDoc,"coefficients.xml");
  	}
  	
  	
  	public static void saveAct(String[] indata,Point p1,Dimension dm){
  	//	bf=false;
  		Element temp=null;
  		Document rDoc=null;
  		try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("acts.xml");
            rDoc = parser.build(fr);
            fr.close();
            
            temp = rDoc.getRootElement();
           
            Element actl=temp.getChild("act"+indata[2]);
            
            if(actl!=null){
            		actl.setAttribute("numkf",""+p);
        			actl.setAttribute("rb",""+InForm.rb0.isSelected());
        			actl.setAttribute("vk",""+InForm.act.l2.getSelectedIndex());
        			actl.setAttribute("numvag",InForm.act.tf2.getText());
        			actl.setAttribute("otpravka",InForm.act.editor20.getTextField().getText());
        			actl.setAttribute("napr",""+InForm.act.cb0.getSelectedIndex());
        			actl.setAttribute("type",""+InForm.act.cb1.getSelectedIndex());
        			actl.setAttribute("og",""+InForm.act.cb2.getSelectedIndex());
        			actl.setAttribute("ves",InForm.act.tf3.getText());
        			actl.setAttribute("hmr",InForm.act.tf4.getText());
        			actl.setAttribute("sob",""+InForm.act.cb3.getSelectedIndex());
        			actl.setAttribute("time1",InForm.act.editor1.getTextField().getText());
        			actl.setAttribute("time2",InForm.act.editor2.getTextField().getText());
        			actl.setAttribute("kol",InForm.act.tf5.getText());
            	XmlIsSimple.existFile(rDoc,p1,dm);
            	}
            else{
            	
            			actl=new Element("act"+indata[2]);
            			actl.setAttribute("numkf",""+(getkf1().size()-1));
            			actl.setAttribute("rb",""+InForm.rb0.isSelected());
            			actl.setAttribute("vk",""+InForm.act.l2.getSelectedIndex());
            			actl.setAttribute("numvag",InForm.act.tf2.getText());
            			actl.setAttribute("otpravka",InForm.act.editor20.getTextField().getText());
            			actl.setAttribute("napr",""+InForm.act.cb0.getSelectedIndex());
            			actl.setAttribute("type",""+InForm.act.cb1.getSelectedIndex());
            			actl.setAttribute("og",""+InForm.act.cb2.getSelectedIndex());
            			actl.setAttribute("ves",InForm.act.tf3.getText());
            			actl.setAttribute("hmr",InForm.act.tf4.getText());
            			actl.setAttribute("sob",""+InForm.act.cb3.getSelectedIndex());
            			actl.setAttribute("time1",InForm.act.editor1.getTextField().getText());
            			actl.setAttribute("time2",InForm.act.editor2.getTextField().getText());
            			actl.setAttribute("kol",InForm.act.tf5.getText());
            			temp.addContent(actl);
            			
            			save(rDoc,"acts.xml");
            			
            			
            }
             
          
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
  		
  		
  		
  		
  		
  	}
  	private static void existFile(final Document doc,Point p,Dimension dm){
 	   
 		  final JDialog dialog=new JDialog();
 		  
 		  dialog .setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
 		  JLabel dl=new JLabel1("Такой акт существует.Заменить?");
 		  JPanel pn=new JPanel();
 		  JButton bt1=new JButton("Да");
 		  bt1.addActionListener(new ActionListener(){
 				public void actionPerformed(ActionEvent e){
 					//bf=true;
 					
 					save(doc,"acts.xml");
 					dialog.dispose();
 				}});
 		  JButton bt2=new JButton("Нет");
 		  bt2.addActionListener(new ActionListener(){
 				public void actionPerformed(ActionEvent e){
 					//bf=false;
 					dialog.dispose();
 				}});
 		  pn.add(bt1);
 		  pn.add(bt2); 
 		  dl.setFont(new Font("Serif", Font.BOLD, 22));
 		  dl.setForeground(Color.black);
 		  dialog.setLayout(new GridLayout(2,1));
 		  dialog.add(dl);
 		  dialog.add(pn);
 		  dialog.setLocation(p.x+(int)Math.rint(dm.width/2)-170,p.y+ (int)Math.rint(dm.height/2)-50);
 		  
 		  dialog.setVisible(true);
 		  dialog.pack();
 		 
 		  
 	  }
  	private static void save(Document doc,String file){
  		
  		
  		
  		
  		try{
	  	    XMLOutputter outputter = new XMLOutputter();
	  	    outputter.setFormat(Format.getPrettyFormat());
	  	    FileWriter fw = new FileWriter(file);
	  	    outputter.output(doc, fw);
	  	    fw.close();
	  	}
	  	catch (Exception ex) {
	  	    System.out.println(ex.getMessage());
	  	}
		
}
  	
  	
  	
  	
  	
  	
  	
  	
  	
  //метод для получения списка актов
    public static List<Element> getlistacts() {
    	
        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("acts.xml");
            Document rDoc = parser.build(fr);
           fr.close();
           List<Element> temp = rDoc.getRootElement().getChildren();
        
            return temp;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
		
		
    }
  	
  	
  	public static Element openAct(String act){
  		
  		Element temp=null;
  		Document rDoc=null;
  		
  		try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("acts.xml");
            rDoc = parser.build(fr);
            fr.close();
            temp = rDoc.getRootElement();
            Element actl=temp.getChild(act);
            if(actl==null){
            	
                final JDialog dialog=new JDialog();
         		  
         		  dialog .setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
         		  JLabel dl=new JLabel1("Нет акта с таким номером!");
         		  JPanel pn=new JPanel();
         		  JButton bt1=new JButton("Ок");
         		  bt1.addActionListener(new ActionListener(){
         				public void actionPerformed(ActionEvent e){
         					//bf=true;
         					
         					//save(doc,"acts.xml");
         					dialog.dispose();
         				}});
         		 
         		  pn.add(bt1);
         		  
         		  dl.setFont(new Font("Serif", Font.BOLD, 22));
         		  dl.setForeground(Color.black);
         		  dialog.setLayout(new GridLayout(2,1));
         		  dialog.add(dl);
         		  dialog.add(pn);
         		 //dialog.setLocation(p.x+(int)Math.rint(dm.width/2)-170,p.y+ (int)Math.rint(dm.height/2)-50);
         		 Point location = MouseInfo.getPointerInfo().getLocation();
             	int x = (int)location.getX();
             	int y = (int)location.getY();
             	dialog.setLocation(x, y);
         		  dialog.setVisible(true);
         		  dialog.pack(); 
            	
            	
            	
            }
            return actl;
  		}
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        
           return null;
        }
  	}
  	
  	
  
  	
  	
  	
  	
  	
  	
}
