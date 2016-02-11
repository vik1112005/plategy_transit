import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.jdom2.Element;
 //на данный момент этот класс не используетс€ сохранил дл€ примера создани€ таблицы из листа 
//и дл€ примера сортировки (дл€ сортировки в качестве компоратора нужно применить класс SortElement предварительно переписав сортировку под себ€)
public class SelectAct extends JDialog {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int i = 0;
	List<Integer> beans;
    
    public SelectAct(List<Element> beans,final MainPanel pan) {
    	this.beans=new ArrayList<Integer>();
 this.beans.clear();
       for(int i=0;i<=beans.size()-1;i++){
    	   
    	   this.beans.add(Integer.parseInt(beans.get(i).getName().substring(3)));
    	   
       }
 
       
 
     Collections.sort(this.beans,new SortElement());
 
        TableModel model = new MyTableModel(this.beans);
        final JTable table = new JTable(model);
        
        
       // table.getRowSorter().setSortKeys(null);	
        
        table.setRowSelectionInterval(0, 0);
        
        JButton bt1=new JButton("ќткрыть");
        JButton bt2=new JButton("ќтмена");
        
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//System.out.println("act"+table.getValueAt(table.getSelectedRow(),0));
           Element el=	XmlIsSimple.openAct("act"+table.getValueAt(table.getSelectedRow(), 0)); 
         //  System.out.println(el);
             	  String line;
           	//считываем из файла данные дл€ ввода
             	 line = el.getAttributeValue("rb");
           		//System.out.println(el.getName().toString());
           		InForm.rb0.setSelected(Boolean.parseBoolean(line));
           		InForm.rb1.setSelected(!Boolean.parseBoolean(line));
           		
           		line = el.getName().substring(3);
           		pan.tf1.setText(line);
           	 	
           		line = el.getAttributeValue("numvag");
           		pan.tf2.setText(line);
           		
           		line = el.getAttributeValue("napr");
           		pan.cb0.setSelectedIndex(Integer.parseInt(line));
           		
           		line = el.getAttributeValue("type");
           		pan.cb1.setSelectedIndex(Integer.parseInt(line));
           		
           		line = el.getAttributeValue("og");
           		pan.cb2.setSelectedIndex(Integer.parseInt(line));
           		
           		line = el.getAttributeValue("ves");
           		pan.tf3.setText(line);
           	 	
           		line = el.getAttributeValue("hmr");
           		pan.tf4.setText(line);
           		
           		line = el.getAttributeValue("sob");
           		pan.cb3.setSelectedIndex(Integer.parseInt(line));
           		
           		line = el.getAttributeValue("time1");
           		pan.editor1.getTextField().setText(line);
           		
           		line = el.getAttributeValue("time2");
           		pan.editor2.getTextField().setText(line);
           		
           		line = el.getAttributeValue("kol");
           		pan.tf5.setText(line);
           		line = el.getAttributeValue("numkf");
           		pan.lastkf=Integer.parseInt(line);	
          
            	dispose();
            	
           } });	
        
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	dispose();
           } });	
        JScrollPane scroll=new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(170, 530));
        JPanel panel=new JPanel();
       
        
		 Container p = getContentPane();
		 p.setLayout(new BorderLayout());
		 panel.setLayout(new BorderLayout());
		 p.add(bt1,BorderLayout.WEST);
		 p.add(bt2,BorderLayout.EAST);
        p.add(scroll,BorderLayout.NORTH);
        p.add(panel,BorderLayout.CENTER);
        setPreferredSize(new Dimension(170, 600));
         this.add(panel);
         pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
  /*  public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new TestFrame();
            }
        });
    }*/
 
    public class MyTableModel implements TableModel {
 
        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
 
        private List<Integer> beans;
 
        public MyTableModel(List<Integer> beans) {
            this.beans = beans;
        }
 
        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }
 
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }
 
        public int getColumnCount() {
            return 1;
        }
 
        public String getColumnName(int columnIndex) {
            
                return "Ќомер акта";
           
        }
 
        public int getRowCount() {
            return beans.size();
        }
 
        public Object getValueAt(int rowIndex, int columnIndex) {
            Integer bean = beans.get(rowIndex);
           
                return bean;
            
        }
 
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
 
        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }
 
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
 
        }
 
    }
 
    
}
