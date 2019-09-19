package timetable;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
public class BuildTableRandomly {

public static List<Exam>  FrontListExam=new ArrayList<>();
public static List<Exam>  BackListExam=new ArrayList<>();
public static List<Exam> UnscheduledExams=new ArrayList<>();
public static Problem sol;
public static List <Period> firstPS_Periods=new ArrayList<>();
public static List <Period> lastPS_Periods=new ArrayList<>();
public static List <Period> mid_Periods=new ArrayList<>();
public static List <Exam> finalSol=new ArrayList<>();
public static int interval;
static int PS=Problem.getPeriodSpread();


public static boolean Build(Problem individual) throws FileNotFoundException, UnsupportedEncodingException
{

UnscheduledExams.clear();
mid_Periods.clear();
finalSol.clear();
 sol=individual;
 for(int i=0;i<sol.Periods.size();i++)
	 for( int j=0;j<sol.Periods.get(i).get_allperiodRoomS().size();j++)
		 sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().clear();

 boolean isScheduled=true;
 List <Exam> Exams=sol.Exams;
 arrangeRooms();
UnscheduledExams=OrderingExams.OrderingUnscheduledExamsRandomly(Exams);
//print_allExamsInfoExceptPeriodAndRoom(UnscheduledExams);
//System.out.print(UnscheduledExams.size());
//determine midPeriods
for(int i=0;i<individual.getPeriods().size();i++)
	mid_Periods.add(sol.Periods.get(i));



//System.out.println("mid_Periods Size  Size  Size Size  Size  Size  Size  Size  Size  Size  Size="+mid_Periods.size());
//System.out.println("ID of First Period in mid Periods"+mid_Periods.get(0).getID());
//System.out.println("ID of Last Period in mid Periods"+mid_Periods.get(mid_Periods.size()-1).getID());	
//--------------------------------------------------------------------------------------------------------------
//Build_FirstPeriods********************************************************************************************

//--------------------------------------------------------------------------------------------------------------
//Build_midPeriods********************************************************************************************

boolean finishing=false;
while(!UnscheduledExams.isEmpty())
{	isScheduled=false;
	for(int i=0;i<mid_Periods.size();i++)	
		{if(Build_midPeriods(UnscheduledExams.get(0), i)==true)
		{if(UnscheduledExams.isEmpty())
		{
			System.out.print("UnscheduledExams is Empty");
			isScheduled=true;
		    finishing=true;
			
			
		}
			//System.out.println("cooooooooooner="+UnscheduledExams.get(0).getID());
			isScheduled=true;
			break;
			
		}
		
	
		}
if(isScheduled==false)
{
	System.out.println("Please try agian ..");
break;	
}
}

orderExamsByID(finalSol);
sol.Exams.clear();
for(int i=0; i<finalSol.size();i++)
	sol.Exams.add(finalSol.get(i));
//System.out.println("size "+sol.Exams.size()+" finish");
System.out.println("Number of exams Scheduled is: "+sol.Exams.size()+" out of "+Problem.isComplete);

//System.out.println(sol.Exams.get(5).getPeriod().getID());

CheckHardConstraints.checkAllHC(sol);


if(finishing==true)
{
	if(CheckHardConstraints.checkAllHC(sol)==true){
		System.out.println("*** feasible solution***");
	System.out.println("-------------------------------------------------------------------------------------------------------------");
	PrintSolution.printing(sol);
	}
	else 
		System.out.println("*** Infeasible solution***");

	
PrintSolution.printing(sol);
return true;
}
else
	return false;
}// End the primary method "Build"
//===============================================================================================================
	
//************************for firstPeriods --- checking Period duration**************************************************************

public static boolean firstPs_checkDuration(Exam e, int Pi)
{
if(e.getduration()<=firstPS_Periods.get(Pi).getDuration())
	return true;// means this period can be used to schedule this exam
else
	return false;
}
//************************for firstPeriods --- checkExamsConflict************************************************************************

public static boolean firstPs_checkConflict(Exam e, int Pi)
{
for(int i=0;i<e.ConflictWith.size();i++)
	{
	if(firstPS_Periods.get(Pi).get_allPeriodExamS().contains(e.ConflictWith.get(i)))
		{
		//System.out.println("conflict with"+e.getID()+" with "+e.ConflictWith.get(i));
return true;}// means there is conflict
	}	return false;// means no conflict
}
//************************for firstPeriods --- checkExclusion************************************************************************

public static boolean firstPs_checkExclusion(Exam e, int Pi)
{
for(int i=0; i<e.exclusion.size();i++)
	if(firstPS_Periods.get(Pi).get_allPeriodExamS().contains(e.exclusion.get(i).getID()))
		return true;
return false;
}
//***************************************************************************************************************

//*******if exam afterList has exams-->check if all before exams are scheduled***********************************************************
public static boolean firstPs_checkAfter(Exam e, int Pi){
boolean scheduled=false;
	for(int i=0;i<e.after.size();i++)
		{for(int j=0;j<Pi;j++)
			{if(firstPS_Periods.get(j).get_allPeriodExamS().contains(e.after.get(i).getID()))
			{
				scheduled=true;
				break;
			}
			
			}
		if(scheduled==false)
			return false;
		else
			scheduled=false;
		}
	
return true;
}
//******if exam coincidence has exams--> check if all its all coincidence exams can be scheduled in this period**************************************************************************
public static boolean firstPs_checkCoincidence(Exam e, int Pi){
if(firstPs_checkDuration(e,Pi)==false | firstPs_checkConflict(e,Pi)==true
| firstPs_checkExclusion(e,Pi)==true | firstPs_checkAfter(e,Pi)==false 
| firstPs_checkSpread(e,Pi)==false)
		return false;
for(int i=0;i<e.coin.size();i++)
if(firstPs_checkDuration(e.coin.get(i),Pi)==false | firstPs_checkConflict(e.coin.get(i),Pi)==true
   | firstPs_checkExclusion(e.coin.get(i),Pi)==true | firstPs_checkAfter(e.coin.get(i),Pi)==false 
   | firstPs_checkSpread(e.coin.get(i),Pi)==false)
return false;			

List<ItcRoom>AlltempRooms=new ArrayList<>();
AlltempRooms.clear();
for(int i=0;i<firstPS_Periods.get(Pi).get_allperiodRoomS().size();i++)
{	ItcRoom tempRoom=new ItcRoom();
	tempRoom.setRoomID(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getID());
	
	tempRoom.setRoomCapacity(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getCapacity());
	tempRoom.setRoomPenalty(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getPenalty());
	tempRoom.setRoomFreeSize(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize());
	tempRoom.setRoomExclusive(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExclusive());

for(int j=0;j<firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().size();j++)
		tempRoom.add_roomExamS(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().get(j));

AlltempRooms.add(tempRoom);
}

if(CheckProomsForCoinExams(AlltempRooms,e)==false)//first of all, check the the Exam before its Coin Exams
	return false;

//means the Exam can be scheduled in this period rooms and we will check its Coin Exams



for(int i=0;i<e.coin.size();i++)// check its Coin Exams
{
	if(CheckProomsForCoinExams(AlltempRooms,e.coin.get(i))==false)
		{
	     return false;}

}

//real scheduling 
firstPs_scheduleInRoom(e,Pi);

for(int i=0;i<e.coin.size();i++)
	{ for(int j=0;j<FrontListExam.size();j++)
		if(e.coin.get(i).getID()==FrontListExam.get(j).getID())
	firstPs_scheduleInRoom(FrontListExam.get(j),Pi);
		
	}
return true;
}

//check if there is any room can contain this exam, if any, then schedule this exam in this room for this Period*******************************************************
public static boolean firstPs_scheduleInRoom(Exam e, int Pi)
{ 
	for(int i=0;i<firstPS_Periods.get(Pi).get_allperiodRoomS().size();i++)
	{ if(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getPenalty()==0)
	     { if(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().size()==0)
	         { if(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()) 
	               { firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
	                 firstPS_Periods.get(Pi).add_periodExamS(e.getID());
	                 if(e.getRoomExclusive()==true)
	                    firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).setRoomExclusive(true);
	                 e.setPeroid(firstPS_Periods.get(Pi));
	    	         e.setRoom(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i));
	    	         finalSol.add(e);
	    	         deleteExam(e.getID());
	    	         return true;}}
	             
	        else if(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().get(0).getduration()==e.getduration()
	        	    & e.getRoomExclusive()==false 
	        	    & firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()
	        	    & firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExclusive()==false)
	        		{ firstPS_Periods.get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
	                  firstPS_Periods.get(Pi).add_periodExamS(e.getID());
	                  e.setPeroid(firstPS_Periods.get(Pi));
	     	          e.setRoom(firstPS_Periods.get(Pi).get_allperiodRoomS().get(i));
	     	          finalSol.add(e);
	     	          deleteExam(e.getID());
	     	          return true;
	        		}
		  }
		 
	}
		
	return false;
		}

//*********************checkPeriodSpreadForFirstPeriods************************************************************************
public static boolean firstPs_checkSpread(Exam e, int Pi){

for(int i=0;i<firstPS_Periods.size();i++)
	for(int j=0; j<e.ConflictWith.size();j++)
	if(firstPS_Periods.get(i).get_allPeriodExamS().contains(e.ConflictWith.get(j)))
	return false;

return true;
	
}

//***********check if all Coincidence Exams of this Exam can be scheduled in this Period Rooms******************
public static boolean CheckProomsForCoinExams(List<ItcRoom> rooms, Exam e)
{

	for(int i=0;i<rooms.size();i++)
	{ if(rooms.get(i).getPenalty()==0)
	     { if(rooms.get(i).getRoomExams().size()==0)
	         { if(rooms.get(i).getFreeSize()>=e.getSize()) 
	               { rooms.get(i).add_roomExamS(e);
	                 
	                 if(e.getRoomExclusive()==true)
	                	 rooms.get(i).setRoomExclusive(true);
	                 
	    	         return true;}}
	             
	        else if(rooms.get(i).getRoomExams().get(0).getduration()==e.getduration()
	        	    & e.getRoomExclusive()==false 
	        	    & rooms.get(i).getFreeSize()>=e.getSize()
	        	    & rooms.get(i).getRoomExclusive()==false)
	        		{ rooms.get(i).add_roomExamS(e);
	                  return true;
	        		}
		  }
		 
	}
		
	return false;
	
}
//*************************************************************************************************************
//-------------------------------------------------------------------------------------------------------------
//*************************************Build Last Periods*******************************************************


//************************for lastPeriods --- checking Period duration**************************************************************

public static boolean lastPs_checkDuration(Exam e, int Pi)
{
if(e.getduration()<=lastPS_Periods.get(Pi).getDuration())
	return true;// means this period can be used to schedule this exam
else
	return false;
}
//************************for last Periods --- checkExamsConflict************************************************************************

public static boolean lastPs_checkConflict(Exam e, int Pi)
{
for(int i=0;i<e.ConflictWith.size();i++)
	{
	if(lastPS_Periods.get(Pi).get_allPeriodExamS().contains(e.ConflictWith.get(i)))
		{
		//System.out.println("conflict with"+e.getID()+" with "+e.ConflictWith.get(i));
return true;}// means there is conflict
	}	return false;// means no conflict
}
//************************for lastPeriods --- checkExclusion************************************************************************

public static boolean lastPs_checkExclusion(Exam e, int Pi)
{
for(int i=0; i<e.exclusion.size();i++)
	if(lastPS_Periods.get(Pi).get_allPeriodExamS().contains(e.exclusion.get(i).getID()))
		return true;
return false;
}
//***************************************************************************************************************




//*******if exam BeforeList has exams-->check if all after exams are scheduled***********************************************************
public static boolean lastPs_checkBefore(Exam e, int Pi){
boolean scheduled=false;
	for(int i=0;i<e.before.size();i++)
		{for(int j=Pi+1;j<lastPS_Periods.size();j++)
			{if(lastPS_Periods.get(j).get_allPeriodExamS().contains(e.before.get(i).getID()))
			{
				scheduled=true;
				break;
			}
			
			}
		if(scheduled==false)
			return false;
		else
			scheduled=false;
		}
	
return true;
}

//***********************checkPeriodSpreadForLastPeriods*****************************************************
public static boolean lastPs_checkSpread(Exam e, int Pi){

for(int i=0;i<lastPS_Periods.size();i++)
	for(int j=0; j<e.ConflictWith.size();j++)
	if(lastPS_Periods.get(i).get_allPeriodExamS().contains(e.ConflictWith.get(j)))
	return false;

return true;
	
}

//check also the any exam will be scheduled in last periods must any of its after list exam not scheduled after this exam
/*public static boolean lastPs_checkAfter(Exam e, int Pi){

	for(int i=0;i<e.after.size();i++)
		for(int j=Pi;j<lastPS_Periods.size();j++)
			if(lastPS_Periods.get(j).get_allPeriodExamS().contains(e.after.get(i).getID()))
				return true;
			
		
			
return false;
}
*/
//******if exam coincidence has exams--> check if all its all coincidence exams can be scheduled in this period**************************************************************************
public static boolean lastPs_checkCoincidence(Exam e, int Pi){
	if(lastPs_checkDuration(e,Pi)==false | lastPs_checkConflict(e,Pi)==true |
			   lastPs_checkExclusion(e,Pi)==true | lastPs_checkBefore(e,Pi)==false 
			   // |lastPs_checkAfter(e,Pi)==true 
			   |lastPs_checkSpread(e,Pi)==false)
		return false;
	for(int i=0;i<e.coin.size();i++)
if(lastPs_checkDuration(e.coin.get(i),Pi)==false | lastPs_checkConflict(e.coin.get(i),Pi)==true |
   lastPs_checkExclusion(e.coin.get(i),Pi)==true | lastPs_checkBefore(e.coin.get(i),Pi)==false |lastPs_checkSpread(e.coin.get(i),Pi)==false)
return false;			

List<ItcRoom>AlltempRooms=new ArrayList<>();
AlltempRooms.clear();
for(int i=0;i<lastPS_Periods.get(Pi).get_allperiodRoomS().size();i++)
{	ItcRoom tempRoom=new ItcRoom();
	tempRoom.setRoomID(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getID());
	tempRoom.setRoomCapacity(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getCapacity());
	tempRoom.setRoomPenalty(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getPenalty());
	tempRoom.setRoomFreeSize(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize());
	tempRoom.setRoomExclusive(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExclusive());

for(int j=0;j<lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().size();j++)
		tempRoom.add_roomExamS(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().get(j));

AlltempRooms.add(tempRoom);
}

if(CheckProomsForCoinExams(AlltempRooms,e)==false)//first of all, check the the Exam before its Coin Exams
	return false;

//means the Exam can be scheduled in this period rooms and we will check its Coin Exams

for(int i=0;i<e.coin.size();i++)// check its Coin Exams
{
	if(CheckProomsForCoinExams(AlltempRooms,e.coin.get(i))==false)
		{
	     return false;}
}


//real scheduling 
lastPs_scheduleInRoom(e,Pi);

for(int i=0;i<e.coin.size();i++)
	{for(int j=0;j<BackListExam.size();j++)
		if(e.coin.get(i).getID()==BackListExam.get(j).getID())
			lastPs_scheduleInRoom(BackListExam.get(j),Pi);

		
	}
return true;
}


//check if there is any room can contain this exam, if any, then schedule this exam in this room for this Period*******************************************************
public static boolean lastPs_scheduleInRoom(Exam e, int Pi)
{ 
for(int i=0;i<lastPS_Periods.get(Pi).get_allperiodRoomS().size();i++)
{ if(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getPenalty()==0)
     { if(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().size()==0)
         { if(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()) 
               { lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
                 lastPS_Periods.get(Pi).add_periodExamS(e.getID());
                 if(e.getRoomExclusive()==true)
                    lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).setRoomExclusive(true);
                 e.setPeroid(lastPS_Periods.get(Pi));
    	         e.setRoom(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i));
    	         finalSol.add(e);
    	         deleteExam(e.getID());
    	         return true;}}
             
        else if(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().get(0).getduration()==e.getduration()
        	    & e.getRoomExclusive()==false 
        	    & lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()
        	    & lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExclusive()==false)
        		{ lastPS_Periods.get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
                  lastPS_Periods.get(Pi).add_periodExamS(e.getID());
                  e.setPeroid(lastPS_Periods.get(Pi));
     	          e.setRoom(lastPS_Periods.get(Pi).get_allperiodRoomS().get(i));
     	          finalSol.add(e);
     	          deleteExam(e.getID());
     	          return true;
        		}
	  }
	 
}
	
return false;
	
	//********************************************
}



//************************************End of building last periods *********************************************

//*************************************************************************************************************
//------------------------------Build mid periods -------------------------------------------------------------
//*************************************************************************************************************
//===============================================================================================================

	public static boolean Build_midPeriods(Exam e, int Pi){
	if(e.coin.size()!=0)
		{if(midPs_checkCoincidence(e,Pi)==true)
		return true;
		else
			return false;
		}
	else if( midPs_checkDuration(e,Pi)==true & midPs_checkConflict(e,Pi)==false 
			& midPs_checkExclusion(e,Pi)==false & midPs_checkAfter(e,Pi)==true  )
		{if(midPs_scheduleInRoom(e,Pi)==true)
		{return true;}
		}
	
		return false;
	}
//************************for mid Periods --- checking Period duration**************************************************************

public static boolean midPs_checkDuration(Exam e, int Pi)
{
if(e.getduration()<=mid_Periods.get(Pi).getDuration())
	return true;// means this period can be used to schedule this exam
else
	return false;
}
//************************for midPeriods --- checkExamsConflict************************************************************************

public static boolean midPs_checkConflict(Exam e, int Pi)
{
for(int i=0;i<e.ConflictWith.size();i++)
	{
	if(mid_Periods.get(Pi).get_allPeriodExamS().contains(e.ConflictWith.get(i)))
		{
		//System.out.println("conflict with"+e.getID()+" with "+e.ConflictWith.get(i));
return true;}// means there is conflict
	}	return false;// means no conflict
}
//************************for midPeriods --- checkExclusion************************************************************************

public static boolean midPs_checkExclusion(Exam e, int Pi)
{
for(int i=0; i<e.exclusion.size();i++)
	if(mid_Periods.get(Pi).get_allPeriodExamS().contains(e.exclusion.get(i).getID()))
		return true;
return false;
}
//***************************************************************************************************************

//*******if exam afterList has exams-->check if all before exams are scheduled***********************************************************
public static boolean midPs_checkAfter(Exam e, int Pi){
	
boolean scheduled=false;
for(int i=0;i<e.after.size();i++)
    {for(int j=0;j<firstPS_Periods.size();j++)
      {if(firstPS_Periods.get(j).get_allPeriodExamS().contains(e.after.get(i).getID()))
         { scheduled=true;
		  break;
	     }} 
	 if(scheduled==false)
       {{for(int j=0;j<Pi;j++)
          {if(mid_Periods.get(j).get_allPeriodExamS().contains(e.after.get(i).getID()))
		  {scheduled=true;
		  break;}
			
		}}}
		if(scheduled==false)
			return false;
		else
			scheduled=false;
		}
    
return true;
}
//******if exam coincidence has exams--> check if all its all coincidence exams can be scheduled in this period**************************************************************************
public static boolean midPs_checkCoincidence(Exam e, int Pi){
	if(midPs_checkDuration(e,Pi)==false | midPs_checkConflict(e,Pi)==true
			   | midPs_checkExclusion(e,Pi)==true | midPs_checkAfter(e,Pi)==false)
				return false;	
for(int i=0;i<e.coin.size();i++)
if(midPs_checkDuration(e.coin.get(i),Pi)==false | midPs_checkConflict(e.coin.get(i),Pi)==true
   | midPs_checkExclusion(e.coin.get(i),Pi)==true | midPs_checkAfter(e.coin.get(i),Pi)==false)
	return false;			

List<ItcRoom>AlltempRooms=new ArrayList<>();
AlltempRooms.clear();
for(int i=0;i<mid_Periods.get(Pi).get_allperiodRoomS().size();i++)
{	ItcRoom tempRoom=new ItcRoom();
	tempRoom.setRoomID(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getID());
	tempRoom.setRoomCapacity(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getCapacity());
	tempRoom.setRoomPenalty(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getPenalty());
	tempRoom.setRoomFreeSize(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize());
	tempRoom.setRoomExclusive(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExclusive());

for(int j=0;j<mid_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().size();j++)
		tempRoom.add_roomExamS(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().get(j));

AlltempRooms.add(tempRoom);
}



if(mid_CheckProomsForCoinExams(AlltempRooms,e)==false)//first of all, check the the Exam before its Coin Exams
	{
	return false;
	}
	
//means the Exam can be scheduled in this period rooms and we will check its Coin Exams

for(int i=0;i<e.coin.size();i++)// check its Coin Exams
{
	if(mid_CheckProomsForCoinExams(AlltempRooms,e.coin.get(i))==false)
		{return false;}
}


//real scheduling 
midPs_scheduleInRoom(e,Pi);

for(int i=0;i<e.coin.size();i++)
	{for(int j=0;j<UnscheduledExams.size();j++)
		if(e.coin.get(i).getID()==UnscheduledExams.get(j).getID())
			midPs_scheduleInRoom(UnscheduledExams.get(j),Pi);
	
		
	}
return true;
}

//check if there is any room can contain this exam, if any, then schedule this exam in this room for this Period*******************************************************
public static boolean midPs_scheduleInRoom(Exam e, int Pi)
{ 
	for(int i=0;i<mid_Periods.get(Pi).get_allperiodRoomS().size();i++)
	{ 
	      if(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExams().size()==0)
	         { if(mid_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()) 
	               { mid_Periods.get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
	                 mid_Periods.get(Pi).add_periodExamS(e.getID());
	                 if(e.getRoomExclusive()==true)
	                    mid_Periods.get(Pi).get_allperiodRoomS().get(i).setRoomExclusive(true);
	                 e.setPeroid(mid_Periods.get(Pi));
	    	         e.setRoom(mid_Periods.get(Pi).get_allperiodRoomS().get(i));
	    	         finalSol.add(e);
	    	         deleteExam(e.getID());
	    	         return true;}}
	             
	        else if(e.getRoomExclusive()==false 
	        	    & mid_Periods.get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()
	        	    & mid_Periods.get(Pi).get_allperiodRoomS().get(i).getRoomExclusive()==false)
	        		{ mid_Periods.get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
	                  mid_Periods.get(Pi).add_periodExamS(e.getID());
	                  e.setPeroid(mid_Periods.get(Pi));
	     	          e.setRoom(mid_Periods.get(Pi).get_allperiodRoomS().get(i));
	     	          finalSol.add(e);
	     	          deleteExam(e.getID());
	     	          return true;
	        		}
		  
		 
	}
		
	return false;
		
}


//***********check if all Coincidence Exams of this Exam can be scheduled in this Period Rooms******************
public static boolean mid_CheckProomsForCoinExams(List<ItcRoom> rooms, Exam e)
{
	for(int i=0;i<rooms.size();i++)
	{ 
	      if(rooms.get(i).getRoomExams().size()==0)
	         { if(rooms.get(i).getFreeSize()>=e.getSize()) 
	               { rooms.get(i).add_roomExamS(e);
	                
	                 if(e.getRoomExclusive()==true)
	                   rooms.get(i).setRoomExclusive(true);
	                   return true;}}
	             
	        else if(e.getRoomExclusive()==false 
	        	    & rooms.get(i).getFreeSize()>=e.getSize()
	        	    & rooms.get(i).getRoomExclusive()==false)
	        		{ rooms.get(i).add_roomExamS(e);
	                  
	     	          return true;
	        		}
		  
		 
	}
		
	return false;}
















//***************arrange all rooms for all Periods increasingly by their capacities*****************************
public static void arrangeRooms(){
boolean large=false;
int index=0;
for(int k=0;k<sol.Periods.size();k++)
{for(int i=1;i<sol.Periods.get(k).get_allperiodRoomS().size();i++)		
	{index=i;
		
			for(int j=i-1;j>=0;j--)
	{if(sol.Periods.get(k).get_allperiodRoomS().get(i).getCapacity()<sol.Periods.get(k).get_allperiodRoomS().get(j).getCapacity())
				{
		large=true;
				index=j;
				}

	else
	{	break;
	}

			
	}
	if(large==true)
			{sol.Periods.get(k).get_allperiodRoomS().add(index,sol.Periods.get(k).get_allperiodRoomS().get(i));
			sol.Periods.get(k).get_allperiodRoomS().remove(i+1);
			large=false;
	}}}

	
//for(int test=0;test<sol.Periods.get(4).get_allperiodRoomS().size();test++)
	//System.out.println("roomID"+sol.Periods.get(0).get_allperiodRoomS().get(test).getID());
	
}

//*******************delete exam from all Lists by Sent Exam Id *************************************************
public static void deleteExam(int IdExam){

	for(int i=0;i<FrontListExam.size();i++)
	{
		if(FrontListExam.get(i).getID()==IdExam)
			FrontListExam.remove(i);
	}

	
	for(int i=0;i<BackListExam.size();i++)
	{
		if(BackListExam.get(i).getID()==IdExam)
			BackListExam.remove(i);
	}
	
	for(int i=0;i<UnscheduledExams.size();i++)
	{
		if(UnscheduledExams.get(i).getID()==IdExam)
			UnscheduledExams.remove(i);
	}
	
}


//*************************print all info for all Exams except Period and Room***********************************
public static void print_allExamsInfoExceptPeriodAndRoom(List<Exam> Exams)
{
 for(int i=0; i<Exams.size(); i++)
    {System.out.println("index("+i+")=> ID="+Exams.get(i).getID()+", Duration="+Exams.get(i).getduration()+
	           ", Size="+Exams.get(i).getSize()+", ConflictWithSize="+Exams.get(i).ConflictWithSize+
	           
	          ", Exclusive="+Exams.get(i).getRoomExclusive()+
	           ", FrontLoad="+Exams.get(i).getFrontLoad());          
    System.out.print("coincedence with=");
    for(int c=0;c<Exams.get(i).get_CoinList().size();c++)
    	System.out.print(" "+Exams.get(i).get_CoinList().get(c).getID());
         
    System.out.println();
    System.out.print("Exclusion with=");
    for(int e=0;e<Exams.get(i).get_exclusionList().size();e++)
    System.out.print(" "+Exams.get(i).get_exclusionList().get(e).getID());
    System.out.println();
    System.out.print("After=");
    for(int a=0;a<Exams.get(i).get_afterList().size();a++)
    	System.out.print(" "+Exams.get(i).get_afterList().get(a).getID());
    System.out.println();	
    System.out.print("Before=");
    for(int b=0;b<Exams.get(i).get_beforeList().size();b++)
    	System.out.print(" "+Exams.get(i).get_beforeList().get(b).getID());
    System.out.println();
    System.out.println( "************************************************************************************************");
	}	
}

//*******************orderExamsByID******************************************************************************
public static void orderExamsByID(List<Exam> Exams)
{
	boolean large=false;
	int index=0;
		for(int i=1;i<Exams.size();i++)
	{index=i;
		
			for(int j=i-1;j>=0;j--)
	{if(Exams.get(i).getID()<Exams.get(j).getID())
				{
		large=true;
				index=j;
				}

	else
	{	break;
	}

			
	}
	if(large==true)
			{Exams.add(index,Exams.get(i));
			Exams.remove(i+1);
			large=false;
	}}

	
}
//*******************EndMethod***********************************************************************************
public static void Print_allInformationOFSpecificPeriod(Period P)
{
	List<ItcRoom> rooms=new ArrayList<>();
rooms=P.get_allperiodRoomS();
//System.out.println(rooms.size());
for(int i=0;i<rooms.size();i++)	
{	System.out.println("room ID("+rooms.get(i).getID()+"):");
	for(int j=0;j<rooms.get(i).getRoomExams().size();j++)
		System.out.println("ExamID="+rooms.get(i).getRoomExams().get(j).getID());
}}


}
