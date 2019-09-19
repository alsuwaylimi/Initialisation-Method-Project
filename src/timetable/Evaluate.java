package timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Evaluate {
	//Population pop=new Population();
	//static Scanner  show=new Scanner(System.in);
	public static short W2R=Problem.getW2R();
	public static short W2D=Problem.getW2D();
	public static short WPS=1;
	public static short periodSpread=Problem.getPeriodSpread();
	public static short NonMix=Problem.getNonMixDuration();
	//public static short LargestExams=Problem.getLargeExam();
	public static short LastPeriods=Problem.getLastPeriod();
	public static short FLweight=Problem.getFrontLoadWeight();
	
	public static Problem Evaluate_Sol;
	private static short No2R=0;
	private static short No2D=0;
	private static short NoPS=0;
	private static short conflict=0;	
	private static long overAll_Penalty;
	private static long FrontLoad_Penalty;
	
	
//**************************************************************************************************************
	public static long overAll_Penalty(){
		for(short i=0;i<Evaluate_Sol.getExams().size();i++)
		{Evaluate_Sol.getExams().get(i).set2DViolation((short)0);
		Evaluate_Sol.getExams().get(i).set2RViolation((short)0);
		Evaluate_Sol.getExams().get(i).setPSViolation((short)0);
		Evaluate_Sol.getExams().get(i).setPPViolation((short)0);
		Evaluate_Sol.getExams().get(i).setRPViolation((short)0);
		Evaluate_Sol.getExams().get(i).setFLViolation((short)0);
		Evaluate_Sol.getExams().get(i).setNMViolation((short)0);
		Evaluate_Sol.getExams().get(i).setTotalPenalty((short)0);
			
		}
		
		for(short i=0;i<Evaluate_Sol.getPeriods().size();i++)
			Evaluate_Sol.getPeriods().get(i).setTotalExamssPenlty(0);
	
		
		
		overAll_Penalty=SumW2R2DPSofALLstudentS()+FrontLaod_Penalty()+MixDurationPenalties()+
				        RoomPenalties()+PeriodPenalties();
		/*
		System.out.println("r="+W2R+"d="+W2D);
		System.out.println("SumW2R2DPSofALLstudentS"+SumW2R2DPSofALLstudentS());
		System.out.println("FrontLoad"+FrontLaod_Penalty());
		System.out.println("MixDuration"+MixDurationPenalties());
		System.out.println("RoomPenalty"+ RoomPenalties());
		System.out.println("PeriodPenalty"+PeriodPenalties());
		*/
		
		
		for(short i=0;i<Evaluate_Sol.getExams().size();i++)
			Evaluate_Sol.getExams().get(i).setTotalPenalty();
		
	
	
		
		for(short i=0;i<Evaluate_Sol.getExams().size();i++)
		{Evaluate_Sol.getPeriods().get(Evaluate_Sol.getExams().get(i).getPeriod().getID()).incrementTotalExamsPenaltyByVlaue(Evaluate_Sol.getExams().get(i).getTotalPenalty());
			
		}
		return overAll_Penalty;
	}

//**************************************************************************************************************	

	public static long SumW2R2DPSofALLstudentS(){
	No2R=0;
	 No2D=0;
	 NoPS=0;
	 long allStudents_Penalty=0; 
	for(short i=0;i<Evaluate_Sol.Students.size();i++)
		 allStudents_Penalty+=	SumW2R2DPSofAstudent(i);
	//System.out.println("The number of occurrences of conflict Exams= "+conflict);
	
	//System.out.println("The number of occurrences of Two Exams in A Row= "+(No2R*W2R));
	//System.out.println("The number of occurrences of Two Exams in A Day= "+(No2D*W2D));
	//System.out.println("The number of occurrences of Periods Spread= "+NoPS);
	
	return allStudents_Penalty;
	}
//**************************************************************************************************************	
	
	    public static long SumW2R2DPSofAstudent(short s){
	    //System.out.println(	S_timeTable.get(0).getPeriod().getID());
	    //short W2R=Problem.getW2R();
	    
	    //short W2D=Problem.getW2D();
	    
	    //short WPS=Problem.getWPS();
	    short gap=Problem.getPeriodSpread();
	    
	    short penalty_2R=0;
	    short penalty_2D=0;
	    short penalty_PS=0;
	    long total_penalty=0;
	    // order the Exams of this student by periods of exam
	    boolean smaller=false;
	    short index=0;
	    	for(short i=1;i<Evaluate_Sol.Students.get(s).get_EaxmList().size();i++)
	    {
	    		
	    		index=i;
	    	
	    		for(short j=(short)(i-1);j>=0;j--)
	    {if(Evaluate_Sol.Students.get(s).get_EaxmList().get(i).getPeriod().getID()<Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getID())
	    			{
	    	smaller=true;
	    			index=j;
	    			}

	    else
	    {	break;
	    }

	    		
	    }
	    if(smaller==true)
	    		{Evaluate_Sol.Students.get(s).get_EaxmList().add(index,Evaluate_Sol.Students.get(s).get_EaxmList().get(i));
	    		Evaluate_Sol.Students.get(s).get_EaxmList().remove(i+1);
	    		smaller=false;
	    }}

	  //testing ordering
	    	//if(Evaluate_Sol.Students.get(s).getID()==50)
	    		//for(short l=0;l<Evaluate_Sol.Students.get(s).get_EaxmList().size();l++)
	    	//System.out.println("exam ID for student after ordering"+Evaluate_Sol.Students.get(s).get_EaxmList().get(l).getID())	;
	    
	    //end of ordering 
	    for(short i=0;i<Evaluate_Sol.Students.get(s).get_EaxmList().size();i++)
	    { 
	    short idndexExaminExamslist=0;
	    for(short b=0;b<Evaluate_Sol.getExams().size();b++)
	    	if(Evaluate_Sol.Students.get(s).get_EaxmList().get(i).getID()==Evaluate_Sol.getExams().get(b).getID())
	    		{idndexExaminExamslist=b;}
	    
	    	short p_id=Evaluate_Sol.Students.get(s).get_EaxmList().get(i).getPeriod().getID();
	    	short d_no=Evaluate_Sol.Students.get(s).get_EaxmList().get(i).getPeriod().getDay();
	    	for(short j=(short)(i+1);j<Evaluate_Sol.Students.get(s).get_EaxmList().size();j++){
	    	if(j<Evaluate_Sol.Students.get(s).get_EaxmList().size() & d_no==Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getDay() &
	    	   p_id==Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getID())
	    	{//System.out.println("Student id having conflict"+Evaluate_Sol.Students.get(s).getID());
	    		//System.out.println("conflict Exams("+Evaluate_Sol.Students.get(s).get_EaxmList().get(i).getID()+"&"+Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getID()+")");
	    		//System.out.println("iiiiii"+Evaluate_Sol.Students.get(s).get_EaxmList().get(i).getPeriod().getID());
	    		//System.out.println("jjj"+Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getID());
	    		conflict++;
	    	
	    		
	    	}
	    	else if(j<Evaluate_Sol.Students.get(s).get_EaxmList().size() & d_no==Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getDay() & p_id+1==Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getID())
	    	{penalty_2R++;
	    	Evaluate_Sol.getExams().get(idndexExaminExamslist).increment2RViolationBy((short)1);
	    	}
	    	else if (j<Evaluate_Sol.Students.get(s).get_EaxmList().size() & d_no==Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getDay())
	    		{penalty_2D++;
	    		Evaluate_Sol.getExams().get(idndexExaminExamslist).increment2DViolationBy((short)1);
	    		}
	    	if (j<Evaluate_Sol.Students.get(s).get_EaxmList().size() & Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getPeriod().getID()-p_id<=gap)
	    	{	penalty_PS++;
	    	Evaluate_Sol.getExams().get(idndexExaminExamslist).incrementPSViolationBy((short)1);
	   // System.out.println(Evaluate_Sol.Students.get(s).get_EaxmList().get(i).getID()+"with"+Evaluate_Sol.Students.get(s).get_EaxmList().get(j).getID());
	    	}
	    	}
	    }
	    
		//System.out.println(penalty_2R);
		//System.out.println(penalty_2D);
		//System.out.println(penalty_PS);
	    total_penalty=penalty_2R*W2R + penalty_2D*W2D + penalty_PS*WPS;
	    No2R+=penalty_2R ;
	    No2D+=penalty_2D;
	    NoPS+=penalty_PS;
	    return total_penalty;
	    }
	    
//**************************************************************************************************************	    
public static long FrontLaod_Penalty(){
			FrontLoad_Penalty=0;
			short NoFL=0;
			Exam exam=null;
			for( short i=0;i<Evaluate_Sol.Exams.size();i++)
			{exam=Evaluate_Sol.Exams.get(i);
				
				if(exam.getFrontLoad()==true & exam.getPeriod().getLate()==true)
				{NoFL++;
				Evaluate_Sol.Exams.get(i).incrementFLViolationBy(FLweight);
				
				
				
				//System.out.println("Exam ID("+exam.getID()+") is one of Largest Exams and timetabled in  a Late Period ID"+exam.getPeriod().getID());
				}
			
			}
			//System.out.println("The number of occurrences of FrontLoad Violation="+NoFL);
			FrontLoad_Penalty=NoFL*FLweight;
			return FrontLoad_Penalty;
		}
//**************************************************************************************************************
public static long PeriodPenalties(){
	long penalty=0;
	
	for(byte i=0;i<Evaluate_Sol.Periods.size();i++)
	{
		if(Evaluate_Sol.Periods.get(i).getPenalty()!=0  )
		{for(short j=0;j<Evaluate_Sol.Periods.get(i).get_allperiodRoomS().size();j++)
			 {if(!Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().isEmpty())
			   {penalty+=Evaluate_Sol.Periods.get(i).getPenalty()*Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size();}
			 
			 }
		}
	}	
	
	
	
	//the following to compute the penalty for each exam in order t determine every exam how much its penalty
	for(short i=0;i<Evaluate_Sol.getExams().size();i++)
		Evaluate_Sol.getExams().get(i).incrementPPViolationBy(Evaluate_Sol.getExams().get(i).getPeriod().getPenalty());
	
	//System.out.println("Periods Penalty="+penalty);
	return penalty;
	
}


//**************************************************************************************************************

public static long RoomPenalties(){
long penalty=0;
for(byte i=0;i<Evaluate_Sol.Periods.size();i++)
{for(byte j=0;j<Evaluate_Sol.Periods.get(i).get_allperiodRoomS().size();j++)
	if(Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getPenalty()>0 )
	
		if(!Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().isEmpty())
		{
			penalty+=Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getPenalty()*Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size();
			
		}
		
	}
//the following to compute the penalty for each exam in order t determine every exam how much its penalty
for(short i=0;i<Evaluate_Sol.getExams().size();i++)
	Evaluate_Sol.getExams().get(i).incrementRPViolationBy(Evaluate_Sol.getExams().get(i).getRoom().getPenalty());


//System.out.println("Room Penlties="+penalty);
return penalty;
}	

//**************************************************************************************************************
public static long MixDurationPenalties(){
	//for(short df=0;df<Evaluate_Sol.getExams().size();df++)
		//System.out.println("the exam number ["+df+"] has the follwoing ID="+Evaluate_Sol.getExams().get(df).getID());
		//show.next();
	
	
long penalty=0;
List<Short>count=new ArrayList<>();
for(byte i=0;i<Evaluate_Sol.Periods.size();i++)
{    for(byte j=0;j<Evaluate_Sol.Periods.get(i).get_allperiodRoomS().size();j++)
        {
	if(Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size()>0)
	{//count.add(Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(0).getduration());  
	for(short n=0;n<Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size();n++)
	{if(!count.contains(Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(n).getduration()))
        	{count.add(Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(n).getduration());
             
        	}
 
	}
	penalty+=count.size()-1;
	
	if(!count.isEmpty())
	{ count.remove(0);
	while(!count.isEmpty())
	{for(short n=0;n<Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size();n++)
		
		if(Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(n).getduration()==count.get(0))
		{Evaluate_Sol.getExams().get(Evaluate_Sol.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(n).getID()).incrementNMViolationBy(NonMix);
		count.remove(0);
		if(count.isEmpty())
			break;
			
		}
	
	}
	}
        count.clear();
   
	
	}
        }
}



//System.out.println("Mixduration="+penalty*NonMix);
return penalty*NonMix;
}
}//end class