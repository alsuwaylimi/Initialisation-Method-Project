package timetable;

import java.util.List;

public class CheckHardConstraints {

private static Problem sol;
	public static boolean  checkAllHC(Problem solution) {
		//System.out.println("befffffffffffffffore"+Exams.get(123).get_beforeList().get(0).getID());
		sol=solution;
			if(sol.getExams().size()!= Problem.isComplete)
				System.out.println("The solution is uncompleted!");
			else{
		if(checkAfter()==true & checkExclusion()==true & checkCoincedence()==true & checkRoomExclusive()==true & checkRoomCapacity()==true & checkPeriodDuration()==true &
		checkConflict()==true)
		return true;}
		return false;
	}
	
	
		public static boolean checkAfter() {
		short e1,e2=0;
		boolean violation=false;
		
		
		for(int i=0;i<sol.constraint.get_ListAfter().size();i++)
			{e1=sol.constraint.get_ListAfter().get(i).e1.getID();
		e2=sol.constraint.get_ListAfter().get(i).e2.getID();
		
		if(sol.getExams().get(e1).getPeriod().getID()<sol.getExams().get(e2).getPeriod().getID())
				{violation=true;
			System.out.println("violation After Hard Constraint between Exam("+e1+") and Exam("+e2+")");
			
				}
			}
		if(violation==false)
		{
		System.out.println("No violation for After Hard Constraint");
		return true;}
		
		return false;
		
		
		
		
	}
		
		public static boolean checkExclusion() {
			short e1,e2=0;
			boolean violation=false;
			for( int i=0;i<sol.constraint.get_ListExclusion().size();i++)
			{
				e1=sol.constraint.get_ListExclusion().get(i).e1.getID();
				e2=sol.constraint.get_ListExclusion().get(i).e2.getID();
				
				if(sol.getExams().get(e1).getPeriod().getID()==sol.getExams().get(e2).getPeriod().getID())
				{violation=true;
			System.out.println("violation Exclusion Hard Constraint between Exam("+e1+") and Exam("+e2+")");
				
				
			}
		}
			
			
			if(violation==false)
			{
			System.out.println("No violation for Exclusion Hard Constraint");
			return true;}
			
			return false;
		}
		
		public static boolean checkCoincedence() {
		
			short e1,e2=0;
			boolean violation=false;
			for( int i=0;i<sol.constraint.get_ListCoin().size();i++)
			{
				e1=sol.constraint.get_ListCoin().get(i).e1.getID();
				e2=sol.constraint.get_ListCoin().get(i).e2.getID();
				
				if(sol.getExams().get(e1).getPeriod().getID()!=sol.getExams().get(e2).getPeriod().getID())
				{violation=true;
			System.out.println("violation Coincedence Hard Constraint between Exam("+e1+") and Exam("+e2+")");
				
				
			}
		}
			
			
			if(violation==false)
			{
			System.out.println("No violation for Coincedence Hard Constraint");
			return true;}
			
			return false;
			
			
			
		}

		public static boolean checkRoomExclusive() {
			int e=0,periodID=0,roomID=0,indexRoom=0;
			boolean violation=false;
			for( int i=0;i<sol.constraint.get_ListRoomExclisive().size();i++)
			{e=	sol.constraint.get_ListRoomExclisive().get(i).getID();
			periodID=sol.getExams().get(e).getPeriod().getID();
			roomID=sol.getExams().get(e).getRoom().getID();
			for(int j=0;j<sol.getPeriods().get(0).get_allperiodRoomS().size();j++)
				if(roomID==sol.getPeriods().get(0).get_allperiodRoomS().get(j).getID())
					indexRoom=j;
			if(sol.getPeriods().get(periodID).get_allperiodRoomS().get(indexRoom).getRoomExams().size()>1)
			{   System.out.println("violation RoomExclusive Hard Constraint with Exam No("+e+") in room No("+indexRoom+") in period No("+periodID+")");
				violation=true;
			}
			}
			
			if(violation==false)
			{
			System.out.println("No violation for RoomExclusive Hard Constraint");
			return true;}
			
			
			return false;
		}
		
		
		
		public static boolean checkRoomCapacity() {
			/*int Id=0;
			int capacity=0;
			for(int i=0;i<sol.getPeriods().size();i++)
				for(int j=0;j<sol.getPeriods().get(i).get_allperiodRoomS().size();j++)
				{ Id=sol.getPeriods().get(i).get_allperiodRoomS().get(j).getID();
			         capacity=sol.getPeriods().get(i).get_allperiodRoomS().get(j).getCapacity();
			    System.out.println(Id+"=capacity="+capacity);     
			    for(int k=0;k<sol.getExams().size();k++)
			    	if()
			    
			    
			    
				}*/
			int sum=0,capacity=0;
			boolean violation=false;
			for(int i=0;i<sol.getPeriods().size();i++)
			for(int j=0;j<sol.getPeriods().get(i).get_allperiodRoomS().size();j++)
				{capacity=sol.getPeriods().get(i).get_allperiodRoomS().get(j).getCapacity();
				 sum=0;
				for(int k=0;k<sol.getPeriods().get(i).get_allperiodRoomS().get(j).getRoomExams().size();k++)	
					sum+=sol.getPeriods().get(i).get_allperiodRoomS().get(j).getRoomExams().get(k).getSize();
				if(sum>capacity)
					{System.out.println("Violation Room capacity in room("+sol.getPeriods().get(i).get_allperiodRoomS().get(j).getID()+") in period No("+i+")");
					 violation=true;
					}
				}
			
			if(violation==false)
			{
			System.out.println("No violation for Room Capacity Hard Constraint");
			return true;}
			return false;
		}
		
		
		public static boolean checkPeriodDuration() {
			boolean violation=false;
			for(int i=0;i<sol.getPeriods().size();i++)
				for(int j=0;j<sol.getPeriods().get(i).get_allperiodRoomS().size();j++)
					for(int k=0;k<sol.getPeriods().get(i).get_allperiodRoomS().get(j).getRoomExams().size();k++)
						if(sol.getPeriods().get(i).get_allperiodRoomS().get(j).getRoomExams().get(k).getduration()>sol.getPeriods().get(i).getDuration())
						{  System.out.println("Violation Period Duration for exam No("+sol.getPeriods().get(i).get_allperiodRoomS().get(j).getRoomExams().get(k).getID()+") in period No("+i+")");
							violation=true;
						}
							
						
			if(violation==false)
			{
			System.out.println("No violation for duration Hard Constraint");
			return true;}
			return false;
		}
		

public static boolean checkConflict()
{ boolean violation=false;
	for(int c=0;c<sol.getExams().size();c++)
       { Exam e=sol.getExams().get(c);
         int Pi=e.getPeriod().getID();
         for(short i=0;i<e.ConflictWith.size();i++)
	        { if(sol.getPeriods().get(Pi).get_allPeriodExamS().contains(e.ConflictWith.get(i)))
		        {System.out.println("Violation Conflict between exam No("+e.getID()+") with exam No("+e.ConflictWith.get(i)+")+ in period No("+Pi+")");
		         violation=true;
		        }
	        }
       }
	
 if(violation==false)
   {
	System.out.println("No violation for Conflict duration Hard Constraint");
	return true;// means no conflict}
   }

 return false;// means there is conflict
}
		
}
