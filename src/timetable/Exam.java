package timetable;

import java.util.ArrayList;
import java.util.List;

public class Exam {
	private short id;
	private boolean roomExlusive=false;
	private ItcRoom room;
    private Period period;
    private short duration;
    private short Size;
    public short freePeriods=0;
    private boolean frontLoad=false;
    public  short conflictAfter=0;
    public  short ConflictWithSize=0;
    public int ConflictWithStudents=0;
    private short noViolation2R=0;
    private short noViolation2D=0;
    private short noViolationPS=0;
    private short noViolationFL=0;
    private short noViolationPP=0;
    private short noViolationRP=0;
    private short noViolationNM=0;
    private int totalPenalty=0;
    public List<Short> ConflictWith=new ArrayList<>();
    public List<Short> FreePathWith=new ArrayList<>();
	public List<Student> enrolled = new ArrayList<>();
    public List<Exam> coin = new ArrayList<>();
    public List<Exam> after = new ArrayList<>();
    public List<Exam> before = new ArrayList<>();
    public List<Exam> exclusion = new ArrayList<>();
    
 
//Constructors
   Exam() {}

   Exam(ItcRoom room,Period period,List<Student> enrolled)
   {
	   this.room=room;
	   this.period=period;
	   this.enrolled=enrolled;
   } 
    
   Exam(short id, short duration, short Size)
   {
        this.id=id;
    	this.room =new ItcRoom();
        this.duration=duration;
        this.Size=Size;
        this.period=new Period();   
   }
    
    Exam(short id, short duration) 
    {
        this.id=id;
    	this.period = null;
        this.room =null;
        this.duration=duration;
    }
    
// Set variable methods
    
    public void setExamId(short id) 
    {
        this.id=id;
    }
        
    public void setPeroidId(byte id) 
    {
        period.setPeriodID(id);
    }
    
    public void setPeroidLate() 
    {
        period.setLate();
    }
    
    public void setRoomId(byte id)
    {
        room.setRoomID(id);
    }
        
    public void setPeroid(Period period)
    {
        this.period = period;
    }
    
    public void setRoom(ItcRoom room) 
    {
        this.room = room;
    }
    
    public void set2RViolation(short noViolation) 
    {
    	this.noViolation2R=noViolation;
    }
  
    
    
    public void set2DViolation(short noViolation) 
    {
    	this.noViolation2D=noViolation;
    }
    
    
    
    
    public void setPSViolation(short noViolation) 
    {
    	this.noViolationPS=noViolation;
    }
    
    public void setFLViolation(short noViolation) 
    {
    	this.noViolationFL=noViolation;
    }
    
    public void setPPViolation(short noViolation) 
    {
    	this.noViolationPP=noViolation;
    }
    
    
    public void setRPViolation(short noViolation) 
    {
    	this.noViolationRP=noViolation;
    }
    
    public void setNMViolation(short noViolation) 
    {
    	this.noViolationNM=noViolation;
    }
   
    
    public void setTotalPenalty() 
    {
    	totalPenalty=(this.noViolation2D*Problem.getW2D())+(this.noViolation2R*Problem.getW2R())+(this.noViolationPS)+(this.noViolationNM)+(this.noViolationPP)+(this.noViolationRP)+(this.noViolationFL);
    }
   
    public void setTotalPenalty(int TotalPenalty) 
    {
    	totalPenalty=TotalPenalty;
    }
   
    
     public void increment2RViolationBy(short x) 
    {
    	noViolation2R+=x;
    }
    
 
    public void increment2DViolationBy(short x) 
    {
    	noViolation2D+=x;
    }
    
    
    public void incrementPSViolationBy(short x) 
    {
    	noViolationPS+=x;
    }
    

    public void incrementFLViolationBy(short x) 
    {
    	noViolationFL+=x;
    }
    
    public void incrementPPViolationBy(short x) 
    {
    	noViolationPP+=x;
    }
    
    public void incrementRPViolationBy(short x) 
    {
    	noViolationRP+=x;
    }
    
    public void incrementNMViolationBy(short x) 
    {
    	noViolationNM+=x;
    }
    
    public void setDuration(short duration)
    {
    	this.duration=duration;
    }

    public void setPeroid_And_Room(Period period, ItcRoom room) 
    {
    	this.period = period;
    	this.room=room;
    	
    }
    
    public void setFrontLoad(boolean frontLoad)
    {
	        this.frontLoad=frontLoad;    	
	}
    
    
    public void setSize(short Size) 
    {
        this.Size = Size;
    }
    
    public void setRoomExclusive(boolean roomExclusive) 
    {
        this.roomExlusive = roomExclusive;
    }
    
    public void setAfterList(List<Exam> after){
    	this.after=after;
    }
    
    public void setBeforeList(List<Exam> before){
    	this.before=before;
    }
    
    public void setCoinList(List<Exam> coin){
    	this.coin=coin;
    }
    
    public void setExclusionList(List<Exam> exclusion){
    	this.exclusion=exclusion;
    }
    
    public void setEnrolledList(List<Student> enrolled){
    	this.enrolled=enrolled;
    }
    
    
    
    
// add variable to ArrayLists
    
    public void add_enrolledStudent(short id) 
    {
        enrolled.add(new Student(id));
    }

    public void add_enrolledStudent(Student student) 
    {
        enrolled.add(student);
    }

        
    
    public void add_Coin(Exam c)
    {
    	coin.add(c);
    }
    
    public void add_After(Exam c)
    {
    	after.add(c);
    }
    
    
    public void add_Before(Exam c)
    {
    	before.add(c);
    }
    
    public void add_Exclusion(Exam c)
    {
    	exclusion.add(c);
    }
    
//get variable methods
    
    
    public short getID()
    {
        return id;
    }
    
    public ItcRoom getRoom()
    {
    return room;    	
    }
   
    public Period getPeriod()
    {
        return period;    	
    }
    
    public short getduration()
    {
        return duration;    	
    }
   
    public short get2RViolation() 
    {
    	return noViolation2R;
    }
    
    public short get2DViolation() 
    {
    	return noViolation2D;
    }
    
    
    public short getPSViolation() 
    {
    	return noViolationPS;
    }
    
    

    public short getFLViolation() 
    {
    	return noViolationFL;
    }
    
    public short getPPViolation() 
    {
    	return noViolationPP;
    }
    
    public short getRPViolation() 
    {
    	return noViolationRP;
    }
    
    public short getNMViolation() 
    {
    	return noViolationNM;
    }
    
    
    public boolean getRoomExclusive()
    {
        return roomExlusive;    	
    }
        
    public boolean getFrontLoad()
    {
        return frontLoad;    	
    }
    
    public short getSize()
    {
        return Size;    	
    }
    
    
    public int  getTotalPenalty()
    {
        return totalPenalty;    	
    }
    
    
    public short get_enrolledSize()
    {
  	   return (short)enrolled.size();
    }
    
    public short get_specificStudentEnrolled(short index)
    {
   	   return enrolled.get(index).getID();
    }
    
// get whole ArrayLists methods
    
   public List<Student> get_AllStudentEnrolled()
   {
      return enrolled;
   }
     
    
    public List<Exam> get_CoinList()
    {
   	  return coin;
    }
     
    public List<Exam> get_afterList()
    {
  	   return after;
    }
    
    public List<Exam> get_beforeList()
    {
   	   return before;
    }
     
    public List<Exam> get_exclusionList()
    {
   	   return exclusion;
    }
     
    public  void Print_allStudentsOfSpecificExam(){
    			System.out.println("Students enrolled in Exam No("+this.getID()+"):");
    			
    			for(short i=0;i<this.getSize();i++)
    				System.out.println("Student ID="+this.get_AllStudentEnrolled().get(i).getID());
    		}

}//end class
    

