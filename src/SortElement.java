import java.util.Comparator;



	 // ����� ���������� ��������� ������ � ������ ������ � ���� ������ �� ������������

    class SortElement implements Comparator<Object>
    {       
        public int compare(Object o1, Object o2) 
        {
        	
        	int i=0;
        	if(((Integer) o1)<( ((Integer) o2) ) )return -1;
        	if(((Integer) o1)>( ((Integer) o2) ) )return 1;
        	return i;
           
            
        }
    }	