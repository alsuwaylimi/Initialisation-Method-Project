/* recently just remove this mark
package timetable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crossover {
//private	static List<Exam> missExams=new ArrayList<>();
//private	static List<Exam> missExamsY=new ArrayList<>();
private static List<Exam> missExams=new ArrayList<>();
private static Indvi Sol;
private static List<Indvi> offsprings=new ArrayList<>();
private static int crossPoint;
public static List<Indvi> crossover(Indvi indvi1,Indvi indvi2, GA ga) throws FileNotFoundException
{offsprings.clear();

Random rn = new Random();
crossPoint=rn.nextInt(indvi1.getPro().Periods.size());
System.out.println("crossPoint="+crossPoint);


Indvi indvibeforeC_A=new Indvi(indvi1.getPro());
removeCBeforePoint(indvibeforeC_A);
Indvi indviBefore2Copy=new Indvi(indvi2.getPro());
removeDuplicateCBeforePoint(indvibeforeC_A, indvi2);
crossBeforePoint(indvibeforeC_A,indviBefore2Copy);
missExamsCBeforePoint(indvibeforeC_A, indvi2);
if(rescheduleMissExams_CABeforePoint(indvibeforeC_A)==true)
offsprings.add(indvibeforeC_A);




Indvi indvibeforeC_B=new Indvi(indvi2.getPro());
removeCBeforePoint(indvibeforeC_B);
Indvi indviBefore1Copy=new Indvi(indvi1.getPro());
removeDuplicateCBeforePoint(indvibeforeC_B, indvi1);
crossBeforePoint(indvibeforeC_B,indviBefore1Copy);
missExamsCBeforePoint(indvibeforeC_B, indvi1);
if(rescheduleMissExams_CABeforePoint(indvibeforeC_B)==true)
	offsprings.add(indvibeforeC_B);




if(crossPoint+1==indvi1.getPro().Periods.size())
	crossPoint-=1;
Indvi indviAfterC_A=new Indvi(indvi1.getPro());
removeCAfterPoint(indviAfterC_A);
Indvi indviAfter2Copy=new Indvi(indvi2.getPro());
removeDuplicateCAfterPoint(indviAfterC_A, indvi2);
crossAfterPoint(indviAfterC_A,indviAfter2Copy);
missExamsCAfterPoint(indviAfterC_A, indvi2);
if(rescheduleMissExams_CAfterPoint(indviAfterC_A)==true)
	offsprings.add(indviAfterC_A);



Indvi indviAfterC_B=new Indvi(indvi2.getPro());
removeCAfterPoint(indviAfterC_B);
Indvi indviAfter1Copy=new Indvi(indvi1.getPro());
removeDuplicateCAfterPoint(indviAfterC_B, indvi1);
crossAfterPoint(indviAfterC_B,indviAfter1Copy);
missExamsCAfterPoint(indviAfterC_B, indvi1);
if(rescheduleMissExams_CAfterPoint(indviAfterC_B)==true)
	offsprings.add(indviAfterC_B);


System.out.println(" Solution A===============================================================================");
PrintInfoPeriods(indvi1);
System.out.println(" Solution B ==============================================================================");
PrintInfoPeriods(indvi2);

System.out.println("BeforeA **********************************************************************************");
PrintInfoPeriods(indvibeforeC_A);
	
System.out.println("BeforeB **********************************************************************************");
PrintInfoPeriods(indvibeforeC_B);
	

System.out.println("AfterA ************************************************************************************");
PrintInfoPeriods(indviAfterC_A);
	

System.out.println("AfterB ************************************************************************************");
PrintInfoPeriods(indviAfterC_B);
	






/*
Indvi indvibeforeC_B=new Indvi(indvi2.getPro());
removeCBeforePoint(indvibeforeC_B);
crossBeforePoint(indvibeforeC_B,indvi1);
removeDuplicateCBeforePoint(indvibeforeC_B, indvi1);
missExamsCBeforePoint(indvibeforeC_B, indvi1);


if(rescheduleMissExams_CABeforePoint(indvibeforeC_B)==true)
offsprings.add(indvibeforeC_B);
*/
/*recently just remove this mark
return offsprings;

//Indvi indviafterC_A=new Indvi(indvi1.getPro());
//Indvi indviafterC_B=new Indvi(indvi2.getPro());
//removeAfter(indviA,crossPoint);


/* recently just remove this mark

}

public static void removeCBeforePoint(Indvi indvi)

{
	
	for(int i=0; i<=crossPoint;i++)
	{indvi.getPro().getPeriods().get(i).get_allPeriodExamS().clear();
		for(int r=0;r<indvi.getPro().getPeriods().get(i).get_allperiodRoomS().size();r++)
		{	
		indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).getRoomExams().clear();
		indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).setRoomFreeSize(indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).getCapacity());
		indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).setRoomExclusive(false);
		}
	}
}


public static void crossBeforePoint(Indvi indviCrossed, Indvi indvi)
{
	
	
	for(int i=0;i<=crossPoint;i++)
	{
	for(int pe=0;pe<indvi.getPro().Periods.get(i).get_allPeriodExamS().size();pe++)
		indviCrossed.getPro().getPeriods().get(i).add_periodExamS(indvi.getPro().Periods.get(i).get_allPeriodExamS().get(pe));


	for(int j=0;j<indvi.getPro().Periods.get(i).get_allperiodRoomS().size();j++)
	{
	for(int re=0;re<indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size();re++)
		indviCrossed.getPro().getPeriods().get(i).get_allperiodRoomS().get(j).add_roomExamS(indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(re));
		
	indviCrossed.getPro().getPeriods().get(i).get_allperiodRoomS().get(j).setRoomFreeSize(indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getFreeSize());
				
	indviCrossed.getPro().getPeriods().get(i).get_allperiodRoomS().get(j).setRoomExclusive(indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getRoomExclusive());
				                             
	}			                             
	     
	}
	
	for(int i=0;i<indviCrossed.getPro().getExams().size();i++)
	{
		if(indvi.getPro().getExams().get(i).getPeriod().getID()<=crossPoint)
			{
			indviCrossed.getPro().getExams().get(i).setPeroid(indvi.getPro().getExams().get(i).getPeriod());
			indviCrossed.getPro().getExams().get(i).setRoom(indvi.getPro().getExams().get(i).getRoom());
			}
	}
	
	
}



public static void missExamsCBeforePoint(Indvi indviCrossed, Indvi indvi)
{missExams.clear();
	for(int i=0;i<indviCrossed.getPro().getExams().size();i++)
		if(indviCrossed.getPro().getExams().get(i).getPeriod().getID()<=crossPoint & indvi.getPro().getExams().get(i).getPeriod().getID()>crossPoint)
			missExams.add(indviCrossed.getPro().getExams().get(i));
}



public static void removeDuplicateCBeforePoint(Indvi indviCrossed, Indvi indvi)
{ Exam e;

  for(int i=0;i<indviCrossed.getPro().getExams().size();i++)
	if(indviCrossed.getPro().getExams().get(i).getPeriod().getID()>crossPoint & indvi.getPro().getExams().get(i).getPeriod().getID()<=crossPoint)

	{	
//		missExams.add(indviCrossed.getPro().getExams().get(i));
		e=indviCrossed.getPro().getExams().get(i);
		
		int periodID=e.getPeriod().getID();
		int roomID=-1;
		for(int indexroom=0;indexroom<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().size();indexroom++)
		 	if(e.getRoom().getID()==indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(indexroom).getID())
		 roomID=indexroom;
		Integer ID;
	/*	if(e.get_CoinList().size()!=0)
		  { for(int j=0;j<e.get_CoinList().size();j++)
			{ ID =e.get_CoinList().get(j).getID();
	     	  for(int r=0;r<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().size();r++)
			      {	for(int Cr=0;Cr<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getRoomExams().size();Cr++)
			     	{if(ID==indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getRoomExams().get(Cr).getID())
					   { 
			     		indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getRoomExams().remove(Cr);
			     		indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).setRoomExclusive(false);
					     int newFreeSize=0;
						  newFreeSize=indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getFreeSize()+e.get_CoinList().get(j).getSize();
						  indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).setRoomFreeSize(newFreeSize);
						  
				       }
				
				     }
			       }
	     	 indviCrossed.getPro().getPeriods().get(periodID).get_allPeriodExamS().remove(ID);
			 missExams.add(indviCrossed.getPro().getExams().get(ID));
			}
		  }//end if
		*/
/* recently just remove this mark
	    ID=e.getID();
	    indviCrossed.getPro().getPeriods().get(periodID).get_allPeriodExamS().remove(ID);
		for(int id=0;id<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getRoomExams().size();id++)
		   if(indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getRoomExams().get(id).getID()==ID)
			  {indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getRoomExams().remove(id);
			  indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).setRoomExclusive(false);
			  int newFreeSize=0;
			  newFreeSize=indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getFreeSize()+e.getSize();
			  indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).setRoomFreeSize(newFreeSize);
			  
			  }

	}}	
		
		
	




public static void crossAfterPoint(Indvi indviCrossed, Indvi indvi)
{
	
	
	for(int i=crossPoint+1;i<indviCrossed.getPro().getPeriods().size();i++)
		
		
	{
	for(int pe=0;pe<indvi.getPro().Periods.get(i).get_allPeriodExamS().size();pe++)
		indviCrossed.getPro().getPeriods().get(i).add_periodExamS(indvi.getPro().Periods.get(i).get_allPeriodExamS().get(pe));


	for(int j=0;j<indvi.getPro().Periods.get(i).get_allperiodRoomS().size();j++)
	{
	for(int re=0;re<indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size();re++)
		indviCrossed.getPro().getPeriods().get(i).get_allperiodRoomS().get(j).add_roomExamS(indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(re));
		
	indviCrossed.getPro().getPeriods().get(i).get_allperiodRoomS().get(j).setRoomFreeSize(indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getFreeSize());
				
	indviCrossed.getPro().getPeriods().get(i).get_allperiodRoomS().get(j).setRoomExclusive(indvi.getPro().Periods.get(i).get_allperiodRoomS().get(j).getRoomExclusive());
				                             
	}			                             
	     
	}
	

	
	for(int i=0;i<indviCrossed.getPro().getExams().size();i++)
	{
		if(indvi.getPro().getExams().get(i).getPeriod().getID()>crossPoint)
			{
			indviCrossed.getPro().getExams().get(i).setPeroid(indvi.getPro().getExams().get(i).getPeriod());
			indviCrossed.getPro().getExams().get(i).setRoom(indvi.getPro().getExams().get(i).getRoom());
			}
	}

	
	
	
	
	
	
	
	
	
}
public static void removeDuplicateCAfterPoint(Indvi indviCrossed,Indvi indvi )
{
	
	
		 Exam e;

		  for(int i=0;i<indviCrossed.getPro().getExams().size();i++)
				if(indviCrossed.getPro().getExams().get(i).getPeriod().getID()<=crossPoint & indvi.getPro().getExams().get(i).getPeriod().getID()>crossPoint)

			{	
//				missExams.add(indviCrossed.getPro().getExams().get(i));
				e=indviCrossed.getPro().getExams().get(i);
				
				int periodID=e.getPeriod().getID();
				int roomID=-1;
				for(int indexroom=0;indexroom<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().size();indexroom++)
				 	if(e.getRoom().getID()==indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(indexroom).getID())
				 roomID=indexroom;
				Integer ID;
			/*	if(e.get_CoinList().size()!=0)
				  { for(int j=0;j<e.get_CoinList().size();j++)
					{ ID =e.get_CoinList().get(j).getID();
			     	  for(int r=0;r<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().size();r++)
					      {	for(int Cr=0;Cr<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getRoomExams().size();Cr++)
					     	{if(ID==indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getRoomExams().get(Cr).getID())
							   { 
					     		indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getRoomExams().remove(Cr);
					     		indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).setRoomExclusive(false);
							     int newFreeSize=0;
								  newFreeSize=indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).getFreeSize()+e.get_CoinList().get(j).getSize();
								  indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(r).setRoomFreeSize(newFreeSize);
								  
						       }
						
						     }
					       }
			     	 indviCrossed.getPro().getPeriods().get(periodID).get_allPeriodExamS().remove(ID);
					 missExams.add(indviCrossed.getPro().getExams().get(ID));
					}
				  }//end if
				*/


/*recently just remove this mark
			    ID=e.getID();
			    indviCrossed.getPro().getPeriods().get(periodID).get_allPeriodExamS().remove(ID);
				for(int id=0;id<indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getRoomExams().size();id++)
				   if(indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getRoomExams().get(id).getID()==ID)
					  {indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getRoomExams().remove(id);
					  indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).setRoomExclusive(false);
					  int newFreeSize=0;
					  newFreeSize=indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getFreeSize()+e.getSize();
					  indviCrossed.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).setRoomFreeSize(newFreeSize);
					  
					  }

			}
	
	
	
	
	
	
	
	}
  
	

















public static void removeCAfterPoint(Indvi indvi)

{
	
	for(int i=crossPoint+1; i<indvi.getPro().getPeriods().size();i++)
		{indvi.getPro().getPeriods().get(i).get_allPeriodExamS().clear();
		  for(int r=0;r<indvi.getPro().getPeriods().get(i).get_allperiodRoomS().size();r++)
		
			{indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).getRoomExams().clear();
			indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).setRoomFreeSize(indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).getCapacity());
			indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(r).setRoomExclusive(false);
			}
		}
}


public static void missExamsCAfterPoint(Indvi indviCrossed, Indvi indvi)
{missExams.clear();
	for(int i=0;i<indviCrossed.getPro().getExams().size();i++)
		if(indviCrossed.getPro().getExams().get(i).getPeriod().getID()>crossPoint & indvi.getPro().getExams().get(i).getPeriod().getID()<=crossPoint)
			missExams.add(indviCrossed.getPro().getExams().get(i));
}






public static boolean rescheduleMissExams_CABeforePoint( Indvi indviCrossed)
{
Sol=indviCrossed;
boolean iSscheduled=false;
	if(missExams.size()==0)
		return true;
	while(!missExams.isEmpty())
	{  iSscheduled=false;
		for(int i=crossPoint+1;i<indviCrossed.getPro().getPeriods().size();i++)
			{if(BuildinThisPeriod(missExams.get(0), i)==true)
				iSscheduled=true;			  
				 if(missExams.isEmpty())
		        	 return true;	
			}       		
		
		if(iSscheduled==false)
			return false;
	
	}


	return false;


}

public static boolean rescheduleMissExams_CAfterPoint( Indvi indviCrossed)
{

Sol=indviCrossed;
boolean iSscheduled=false;
	if(missExams.size()==0)
		return true;
	while(!missExams.isEmpty())
	{  iSscheduled=false;
		for(int i=0;i<=crossPoint;i++)
			{if(BuildinThisPeriod(missExams.get(0), i)==true)
				iSscheduled=true;			  
				 if(missExams.isEmpty())
		        	 return true;	
			}       		
		
		if(iSscheduled==false)
			return false;
	
	}


	return false;



}



public static boolean BuildinThisPeriod(Exam e, int Pi){
if(e.coin.size()!=0)
	{if(Ps_checkCoincidence(e,Pi)==true)
	return true;
	else
		return false;
	}
else if( Ps_checkDuration(e,Pi)==true & Ps_checkConflict(e,Pi)==false 
		& Ps_checkExclusion(e,Pi)==false & Ps_checkAfter(e,Pi)==true & Ps_checkBefore(e,Pi)==true  )
	{if(Ps_scheduleInRoom(e,Pi)==true)
	{return true;}
	}

	return false;
}


//*********  check duration *******************************************************************************************************************************************

public static boolean Ps_checkDuration(Exam e, int Pi)
{
if(e.getduration()<=Sol.getPro().getPeriods().get(Pi).getDuration())
	{	

	return true;// means this period can be used to schedule this exam
	}
	else
	{///System.out.println("durationnnnnnnn ");
	return false;
	}
}

//**********  check Conflict ************************************************************************

public static boolean Ps_checkConflict(Exam e, int Pi)
{
for(int i=0;i<e.ConflictWith.size();i++)
	{
	   if(Sol.getPro().getPeriods().get(Pi).get_allPeriodExamS().contains(e.ConflictWith.get(i)))
		{//System.out.println("conflictttttttttttt ");
		   return true;}// means there is conflict
	   
	}
	
return false;// means no conflict
}

//********* check Exclusion ************************************************************************

public static boolean Ps_checkExclusion(Exam e, int Pi)
{
for(int i=0; i<e.exclusion.size();i++)
	if(Sol.getPro().getPeriods().get(Pi).get_allPeriodExamS().contains(e.exclusion.get(i).getID()))
		{	//System.out.println("exclussssssssion ");

		return true;
		}
return false;
}

//*******if exam afterList has exams-->check if all previous exams are scheduled***********************************************************
public static boolean Ps_checkAfter(Exam e, int Pi){

boolean scheduled=false;
//if(e.getID()==28)
	//System.out.println("Exam 28"+e.get_afterList().get(0).getID());
	for(int i=0;i<e.after.size();i++)
		{for(int j=0;j<Pi;j++)
			{if(Sol.getPro().getPeriods().get(j).get_allPeriodExamS().contains(e.after.get(i).getID()))
			{
				scheduled=true;
				break;
			}
			
			}
		if(scheduled==false)
			{//System.out.println("aftttttttttttttter");
			//if(e.getID()==28)
				//System.out.println("Exam 28"+e.get_afterList().get(0).getID());
			return false;
			}
		else
			scheduled=false;
		}
	
return true;

}
//******************** check coincidence *********************************************************************
public static boolean Ps_checkBefore(Exam e, int Pi){
	for(int i=0;i<e.before.size();i++)
		{for(int j=0;j<=Pi;j++)
			{if(Sol.getPro().getPeriods().get(j).get_allPeriodExamS().contains(e.before.get(i).getID()))
				{//System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
				return false;}
			}
			
			}
	
return true;
}

public static boolean Ps_checkCoincidence(Exam e, int Pi)
{
	if(Ps_checkDuration(e,Pi)==false | Ps_checkConflict(e,Pi)==true
			| Ps_checkExclusion(e,Pi)==true | Ps_checkAfter(e,Pi)==false | Ps_checkBefore(e,Pi)==false)
					return false;
			for(int i=0;i<e.coin.size();i++)
			if(Ps_checkDuration(e.coin.get(i),Pi)==false | Ps_checkConflict(e.coin.get(i),Pi)==true
			   | Ps_checkExclusion(e.coin.get(i),Pi)==true | Ps_checkAfter(e.coin.get(i),Pi)==false | Ps_checkBefore(e.coin.get(i),Pi)==false)
			return false;			

			List<ItcRoom>AlltempRooms=new ArrayList<>();
			AlltempRooms.clear();
			for(int i=0;i<Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().size();i++)
			{	ItcRoom tempRoom=new ItcRoom();
				tempRoom.setRoomID(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getID());
				
				tempRoom.setRoomCapacity(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getCapacity());
				tempRoom.setRoomPenalty(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getPenalty());
				tempRoom.setRoomFreeSize(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getFreeSize());
				tempRoom.setRoomExclusive(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getRoomExclusive());

			for(int j=0;j<Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getRoomExams().size();j++)
					tempRoom.add_roomExamS(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getRoomExams().get(j));

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
			Ps_scheduleInRoom(e,Pi);

			for(int i=0;i<e.coin.size();i++)
				{ for(int j=0;j<Sol.getPro().getExams().size();j++)
					if(e.coin.get(i).getID()==Sol.getPro().getExams().get(j).getID())
				Ps_scheduleInRoom(Sol.getPro().getExams().get(j),Pi);
					
				}
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

//******************* schedule in room *************************************************************************
public static boolean Ps_scheduleInRoom(Exam e, int Pi)
{ 
	for(int i=0;i<Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().size();i++)
	{ 
	      if(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getRoomExams().size()==0)
	         { if(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()) 
	               { Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
	                 Sol.getPro().getPeriods().get(Pi).add_periodExamS(e.getID());
	                 if(e.getRoomExclusive()==true)
	                	 Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).setRoomExclusive(true);
	                 e.setPeroid(Sol.getPro().getPeriods().get(Pi));
	    	         e.setRoom(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i));
	    	         //finalSol.add(e);
	    	         deleteExam(e.getID());
	    	         return true;}}
	             
	        else if(e.getRoomExclusive()==false 
	        	    & Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getFreeSize()>=e.getSize()
	        	    & Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).getRoomExclusive()==false)
	        		{ Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i).add_roomExamS(e);
	        		Sol.getPro().getPeriods().get(Pi).add_periodExamS(e.getID());
	                  e.setPeroid(Sol.getPro().getPeriods().get(Pi));
	     	          e.setRoom(Sol.getPro().getPeriods().get(Pi).get_allperiodRoomS().get(i));
	     	          //finalSol.add(e);
		    	         deleteExam(e.getID());
	     	          return true;
	        		}
		  
		 
	}
		
	return false;
		
}

public static void deleteExam(int IdExam){

	for(int i=0;i<missExams.size();i++)
	{
		if(missExams.get(i).getID()==IdExam)
			missExams.remove(i);
	}

		
}

public static void PrintInfoPeriods(Indvi indvi){
	for(int i=0;i<indvi.getPro().getPeriods().size();i++)
		{
		System.out.print("**Period#("+indvi.getPro().getPeriods().get(i).getID()+"):=>>");
		for(int j=0;j<indvi.getPro().getPeriods().get(i).get_allPeriodExamS().size();j++)
		System.out.print(indvi.getPro().getPeriods().get(i).get_allPeriodExamS().get(j)+", ");
		System.out.println();
		for(int k=0;k<indvi.getPro().getPeriods().get(i).get_allperiodRoomS().size();k++)
			{System.out.print("roomNO("+k+"):");
			  for(int n=0;n<indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(k).getRoomExams().size();n++)
			  System.out.print(indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(k).getRoomExams().get(n).getID()+", ");
			  System.out.println();
			}	
		}
}

}
*/ //recently just remove this marks