package timetable;

import java.util.ArrayList;
import java.util.List;


public class Student 
{
   
   
	private short id;
    private  List<Exam> S_timeTable = new ArrayList<>();
    
    
    public Student(){
        
    }
    
    
    public Student(short id){
        this.id=id;
    }
   
    public void setID(short id)
    {
    this.id=id;	
    }
    
    
    public  short getID(){
        return id;
    }
    
   public void add_exam(Exam e){
	   S_timeTable.add(e);
	   
   }
   
   
   
   public List<Exam> get_EaxmList(){
	   //for(int i=0;i<S_timeTable.size();i++)
	   
	   //System.out.println(S_timeTable.get(i).getID());
	   
	   return S_timeTable;
   }
   
   
  
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student)
            if (( (Student) obj).id == this.id)
                return true;
        return false;
    }

    public void print_allExamsOfSpecificStudent()
    {
    	System.out.println("all Exams information of Student ID("+this.getID()+"):");
    	
    for( short i=0;i<this.get_EaxmList().size();i++)
    	print_InformationOfSpeceficExam(this.get_EaxmList().get(i));

    }

    public void print_InformationOfSpeceficExam(Exam e)
    {
    	
    	
    	System.out.println("all information of Exam No("+e.getID()+"):");
    	

        System.out.println(" Duration="+e.getduration()+
    	           ", Size="+e.getSize()+", ConflictWithSize="+e.ConflictWithSize+", Room="+e.getRoom().getID()+
    	           ", Period="+e.getPeriod().getID()+", Exclusive="+e.getRoomExclusive()+
    	           ", FrontLoad="+e.getFrontLoad());          
        
        
     System.out.print("coincedence with=");
     for(short c=0;c<e.get_CoinList().size();c++)
     	System.out.print(" "+e.get_CoinList().get(c).getID());
          
     System.out.println();
     System.out.print("Exclusion with=");
     for(short q=0;q<e.get_exclusionList().size();q++)
     System.out.print(" "+e.get_exclusionList().get(q).getID());
     System.out.println();
     System.out.print("After=");
     for(short a=0;a<e.get_afterList().size();a++)
     	System.out.print(" "+e.get_afterList().get(a).getID());
     System.out.println();	
     System.out.print("Before=");
     for(short b=0;b<e.get_beforeList().size();b++)
     	System.out.print(" "+e.get_beforeList().get(b).getID());
     System.out.println();
     System.out.print("Conflict With=");
     for(short c=0;c<e.ConflictWith.size();c++)
    	 System.out.print(" "+e.ConflictWith.get(c));
     System.out.println( "************************************************************************************************");

    }	

    
}