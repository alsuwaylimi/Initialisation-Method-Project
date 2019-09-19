package timetable;

import java.util.ArrayList;
import java.util.List;


public class ItcRoom  {
	    private byte id=-127;
	    private short capacity;
	    private short penalty=0;
	    private short freeSize=capacity;
	    private boolean Exclusive =false;
	    private List<Exam> roomExams=  new ArrayList<>();
	   // private  ArrayList<ItcRoom> roomList = new ArrayList<>();
	    
	
	    
	   
//Constructors 
	    
	    ItcRoom()
	    {	    }
	    
	    
	    ItcRoom(byte id)
	    {
		     this.id = id; 
	    }
	    
	    
	    ItcRoom(byte id, short capacity, short penalty) 
	    {
	     this.id = id; 
	     this.capacity = capacity;
	     this.penalty = penalty;
	     this.Exclusive=false;
		 this.freeSize=capacity;
	    }
	    
	    public ItcRoom(byte id, short capacity, short penalty, boolean exclusive,short freeSize ) 
	    {
	     this.id = id; 
	     this.capacity = capacity;
	     this.penalty = penalty;
	     this.Exclusive=exclusive;
		 this.freeSize=freeSize;

	    }
	    
	    
	    
	    ItcRoom(byte id, short capacity, short penalty, short freeSize, boolean Exclusive,List<Exam> roomExams) 
	    {
	     this.id = id; 
	     this.capacity = capacity;
	     this.penalty = penalty;
	     this.Exclusive=Exclusive;
		 this.freeSize=freeSize;
		 this.roomExams=roomExams;
	    }
	
	    
	    
	    
	    ItcRoom(ItcRoom itcRoom) 
	    {
	    	id=itcRoom.id;
	    	capacity=itcRoom.capacity;
			penalty=itcRoom.penalty;
			Exclusive=itcRoom.Exclusive;
			freeSize=itcRoom.freeSize;

			//roomList=itcRoom.roomList;
	    	// TODO Auto-generated constructor stub
		}
	    
	    
//set a variable Methods 
	    
	    public void setRoomID(byte id)
	    {
	        this.id = id;
	    }
	    
	    public void setRoomCapacity(short capacity)
	    {
	        this.capacity = capacity;
	    }
	    
	    public void setRoomPenalty(short penalty)
	    {
	        this.penalty=penalty;
	    }
	    
	    public void setRoomExclusive(boolean Exclusive)
	    {
	        this.Exclusive=Exclusive;
	    }
	    
	    public void setRoomFreeSize(short freeSize)
	    {
	        this.freeSize=freeSize;
	    }
	    
//set whole ArrayLists methods 
	    
	    public void setRoomList (ArrayList<ItcRoom> roomList)
	    {
	        //this.roomList = roomList;
	    }

	    public void setRoomExams (ArrayList<Exam> roomExamS)
	    {
	        this.roomExams = roomExamS;
	    } 
// add a variable to ArrayLists methods
	    
	    public void add_roomExamS (Exam exam)
	    {
	    	this.roomExams.add(exam);
	    	freeSize=(short) (freeSize-(short)exam.getSize());
	    }
	    
	    public void add_roomList (ItcRoom room)
	    {
	    	//roomList.add(room);
	    }
	    
	    
//get a variable Methods 
	   

	    
	    public byte getID()
	    {
	        return id;
	    }
	    
	    public short getCapacity()
	    {
	        return capacity;
	    }
	    
	    public short getPenalty()
	    {
	        return penalty;   
	    }

	    public boolean getRoomExclusive()
	    {
	        return Exclusive;
	    }
	    
	    public short getFreeSize()
	    {
	        return freeSize;
	    }
	    
	    
	    
//get Whole ArrayLists Methods 
	    
	    public List<Exam> getRoomExams()
	    {
	    	return roomExams;
	    }

	  /*
	    public ArrayList<ItcRoom> getRoomList()
	    {
	    	return roomList;
	    }
	    */
	  
	    
//get a variable in ArrayLists Methods 

	    public Exam getSpecificExamInRoomExamS(short index)
	    {
	    	return roomExams.get(index);
	    }
	    
	    
	   
}//end Class
