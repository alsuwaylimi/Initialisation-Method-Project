package timetable;

import java.util.ArrayList;
import java.util.List;


public class Period  {

	private byte id=-127;
	private byte day;
	private short duration;
	
	private short penalty=0;
	private boolean late=false;
	private int totalexamsPenalty=0;
	private List<ItcRoom> periodRoomS=new ArrayList<>();
	private List<Short> periodExamS= new ArrayList<>();
	//private List<Period> PeriodList = new ArrayList<>();
	
	
	
//Constructors
	public Period()
	{
		
		
	}

	
	
	public Period (byte id, byte day, short duration, short penalty, boolean late)
	{
		this.id=id;
		this.day=day;
		this.duration=duration;
		this.penalty=penalty;
		this.late=late;
			
	}	


	public Period (byte id, byte day, short duration, short penalty, boolean late, List<ItcRoom> periodRooms )
	{
		this.id=id;
		this.day=day;
		this.duration=duration;
		this.penalty=penalty;
		this.late=late;
		this.periodRoomS=periodRooms;
	}	

	public Period (byte id, byte day, short duration, short penalty, boolean late,List<Short> periodExamS, List<ItcRoom> periodRooms )
	{
		this.id=id;
		this.day=day;
		this.duration=duration;
		this.penalty=penalty;
		this.late=late;
		this.periodRoomS=periodRooms;
		this.periodExamS=periodExamS;
	}	

	
	
	
	
	public Period (byte id, byte day, short duration, short penalty)
	{
		this.id=id;
		this.day=day;
		this.duration=duration;
		this.penalty=penalty;
		late=false;
	}
	
	
	public Period(Period period) 
	{
		id=period.id;
		day=period.day;
		duration=period.duration;
		penalty=period.penalty;
		late=period.late;
		
		//1PeriodList=period.PeriodList;
		// TODO Auto-generated constructor stub
	}

	

	
//set variables Methods
	
	public void setPeriod (byte id, byte day, short duration, short penalty)
	{
		this.id=id;
		this.day=day;
		this.duration=duration;
		this.penalty=penalty;
	}

	public void setPeriod(Period period)
	{	id=period.id;
	    day=period.day;
	    duration=period.duration;
	    penalty=period.penalty;
	    late=period.late;
	    //2PeriodList=period.PeriodList;
	    // TODO Auto-generated constructor stub
     }

	
	public void setTotalExamssPenlty(int penalty) {
		this.totalexamsPenalty=penalty;
	}
	
	
	public void incrementTotalExamsPenaltyByVlaue(int penalty) {
		this.totalexamsPenalty+=penalty;
	}
	
	

	
	public void setPeriodID (byte id)
	{
		this.id=id;
	}
	
	public void setPeriodDay (byte day)
	{
		this.day=day;
	}
	
	public void setPenalty(short penalty)
	{
		this.penalty=penalty;
	}

	
	public void setLate()
	{
		late=true;
	}
	
	public void setLate(boolean late)
	{
		this.late=late;
	}
	public void setPeriodDuration (short duration )
	{
		this.duration=duration;
	}
	
	
// add variable to ArrayList methods

	public void add_periodExamS(short examID)
	{
		periodExamS.add(examID);
		
	}
	
	public void add_periodRoomS(ItcRoom room)
	{
		periodRoomS.add(room);
		
	}
	
	
	public void set_PeriodRooms(List<ItcRoom> periodRoomS)
	{
		this.periodRoomS=periodRoomS;
	}
	
	/*3
	 public void add_PeriodList(Period period)
		
	{
			PeriodList.add(period);
			
	}
		
	*/
	
// get variable methods	
	
	public byte getID()
	{
		return id;
	}
	
	public byte getDay()
	{
		return day;
	}
	
	public short getDuration()
	{
		return duration;
	}
	
	public short getPenalty()
	{
		return penalty;
	}

	public boolean getLate()
	{
		return late;
	}

	
	public int getTotalExamsPenalty() {
		return this.totalexamsPenalty;
			
	}

//get Whole ArrayLists Methods

	public List<ItcRoom> get_allperiodRoomS()
    {
         return periodRoomS;
    }

   public List<Short> get_allPeriodExamS()
   {
       return periodExamS;
   }

/*4
   public ArrayList<Period> get_PeriodList()
   {
       return PeriodList;
   }
*/
//get a variable in ArrayLists Methods
   
   public ItcRoom get_SpecificRoomInPeriodRooms(byte index)
    {
       return periodRoomS.get(index);
    }
/*
   public Exam get_SpecificExamInThePeriod(int indxe)
    {
      return periodExamS.get(indxe);
    }
*/
   public void Print_allInformationOFSpecificPeriod()
   {List<ItcRoom> rooms=new ArrayList<>();
   rooms=this.get_allperiodRoomS();
   System.out.println(rooms.size());
   for(short i=0;i<rooms.size();i++)	
   {	System.out.println("room ID("+rooms.get(i).getID()+"):");
   	for(short j=0;j<rooms.get(i).getRoomExams().size();j++)
   		System.out.println("ExamID="+rooms.get(i).getRoomExams().get(j).getID());
   }}  

}