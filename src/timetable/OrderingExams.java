package timetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
//import java.util.Scanner;
//best version
public class OrderingExams {
public static List<Short> FrontListbyIDExam=new ArrayList<>();
public static List<Short> BackListbyIDExam=new ArrayList<>();
public static List<Exam> FrontListbyExam=new ArrayList<>();
public static List<Exam> BackListbyExam=new ArrayList<>();
public static List<Exam> UnscheduledbyExams=new ArrayList<>();
public static List<Short> UnscheduledbyIDExams=new ArrayList<>();
public static List<Short> finalUnscheduledbyIDExams=new ArrayList<>();
public static List<Exam> UnscheduledExams=new ArrayList<>();

//OrderingFrontList***************************************************************************************
// the following method to put the exams that must scheduled before some exams 
public static  List<Exam> OrderingFrontList(List<Exam> Exams){
	int max=0;
	for(int r=0;r<Exams.size();r++)
		if(Exams.get(r).get_afterList().size()>=max)
			max=Exams.get(r).get_afterList().size();
	//System.out.println("Max="+max);

	for(int kl=0;kl<=max;kl++)
	{
	short ExamID;
	FrontListbyIDExam.clear();
	FrontListbyExam.clear();
	for(short i=0;i<Exams.size();i++)
		{
		if(!FrontListbyIDExam.contains(Exams.get(i).getID()))
		{ if(!Exams.get(i).get_afterList().isEmpty())
			{
			   ExamID=Exams.get(i).getID();
			   for(short j=0;j<Exams.get(i).get_afterList().size();j++)
				  if(!FrontListbyIDExam.contains(Exams.get(i).get_afterList().get(j).getID()))	
		   FrontListbyIDExam.add(Exams.get(i).get_afterList().get(j).getID());
			FrontListbyIDExam.add(ExamID);
			}
		else
			FrontListbyIDExam.add(Exams.get(i).getID());
		}
		
		}

//	for(short i=0;i<FrontListbyIDExam.size();i++)
//	stem.out.println(FrontListbyIDExam.get(i));
	
//**********generate FrontListbyExam via FrontListbyIDExam************************************************************
	short index=0;
	for(short h=0;h<Exams.size();h++)
	{
		ExamID=FrontListbyIDExam.get(h);
		
		for(short g=0;g<Exams.size();g++)
			if(ExamID==Exams.get(g).getID())
			{index=g;
			break;
			}
		FrontListbyExam.add(Exams.get(index));
		
		
	}
	Exams.clear();
	Exams.addAll(FrontListbyExam);}
	return FrontListbyExam;
}


//********************OrderingBacktList************************************************************************
//the following method to put the exams "that must scheduled after the largest conflict exams" before the 
// largest conflict exams because we starting scheduling these exams at the end of the timetable
public static List<Exam>OrderingBackList(List<Exam> Exams){
	short ExamID;
	//stem.out.println("size before"+Exams.size());
	BackListbyIDExam.clear();
	BackListbyExam.clear();
	//stem.out.println("size before"+Exams.size());
	
	int max=0;
	for(int r=0;r<Exams.size();r++)
		if(Exams.get(r).get_beforeList().size()>=max)
			max=Exams.get(r).get_beforeList().size();
	
	for(int kl=0;kl<=max;kl++)
	{
	
	for(short i=0;i<Exams.size();i++)
		{// if(Exams.get(i).getFrontLoad()==false)
		if(!BackListbyIDExam.contains(Exams.get(i).getID()))
		{ if(!Exams.get(i).get_beforeList().isEmpty())
			{
			   ExamID=Exams.get(i).getID();
			   for(short j=0;j<Exams.get(i).get_beforeList().size();j++)
				  if(!BackListbyIDExam.contains(Exams.get(i).get_beforeList().get(j).getID()))	
					  
		                 BackListbyIDExam.add(Exams.get(i).get_beforeList().get(j).getID());
			BackListbyIDExam.add(ExamID);
			}
		else
		{//stem.out.println(Exams.get(i).getID());
			BackListbyIDExam.add(Exams.get(i).getID());
		
		
		}
		}
		
		}
	}
	//stem.out.println("size after"+BackListbyIDExam.size());
//for(short i=0;i<FrontListbyIDExam.size();i++)
//stem.out.println(FrontListbyIDExam.get(i));
	

	
//**********generate BackListbyExam via BackListbyIDExam************************************************************
	
	short index=0;
	for(short h=0;h<BackListbyIDExam.size();h++)
	{
		ExamID=BackListbyIDExam.get(h);
		
		for(short g=0;g<Exams.size();g++)
			if(ExamID==Exams.get(g).getID())
			{index=g;
			break;
			}
		BackListbyExam.add(Exams.get(index));
		
		
	}
	
//Exams=BackListbyExam;
//stem.out.println("size list"+Exams.size());
//stem.out.println("size list"+BackListbyExam.size());
//for(short i=0;i<BackListbyExam.size();i++)
//stem.out.println(BackListbyExam.get(i).getID());
	//stem.out.println("size after"+BackListbyExam.size());
	return BackListbyExam;
}

//***************Order UnscheduledExams randomly***************************************************************
public static List<Exam>OrderingUnscheduledExamsRandomly(List<Exam> Exams){
	//Scanner d=new Scanner (stem.in);
	/* boolean large=false;
	 short index=0;
	 for(short i=1;i<Exams.size();i++)
		{ index=i;
		  for(short j=(short)(i-1);j>=0;j--)
			 { if(Exams.get(i).FreePathWith.size()<Exams.get(j).FreePathWith.size())
				 {
				 
				 large=true;
				   index=j; }
	           else
		           {break;}
	    	 }
		  if(large==true)
			{Exams.add(index,Exams.get(i));
			 Exams.remove(i+1);
			 large=false;}
		}
	
for(int i=0;i<Exams.size();i++)
	 UnscheduledExams.add(Exams.get(i));
return UnscheduledExams;
*/
	
Random rn = new Random(); 
int index;
UnscheduledExams.clear();
while(!Exams.isEmpty())
 {index = rn.nextInt(Exams.size());
 UnscheduledExams.add(Exams.get(index));
 Exams.remove(index);
 }
	
return UnscheduledExams;
 

	
/*
	
	short ExamID;
	UnscheduledbyIDExams.clear();
	UnscheduledbyExams.clear();
	for(short i=0;i<Exams.size();i++)
		{
		if(!UnscheduledbyIDExams.contains(Exams.get(i).getID()))
		{ if(!Exams.get(i).get_afterList().isEmpty())
			{
			   ExamID=Exams.get(i).getID();
			   for(short j=0;j<Exams.get(i).get_afterList().size();j++)
				  if(!UnscheduledbyIDExams.contains(Exams.get(i).get_afterList().get(j).getID()))	
					  UnscheduledbyIDExams.add(Exams.get(i).get_afterList().get(j).getID());
			   UnscheduledbyIDExams.add(ExamID);
			}
		else
			UnscheduledbyIDExams.add(Exams.get(i).getID());
		}
		
		}

//	for(short i=0;i<FrontListbyIDExam.size();i++)
//	stem.out.println(FrontListbyIDExam.get(i));
	
//**********generate FrontListbyExam via FrontListbyIDExam************************************************************
	short index=0;
	for(short h=0;h<Exams.size();h++)
	{
		ExamID=UnscheduledbyIDExams.get(h);
		
		for(short g=0;g<Exams.size();g++)
			if(ExamID==Exams.get(g).getID())
			{index=g;
			break;
			}
		UnscheduledbyExams.add(Exams.get(index));
		
		
	}
	
	return UnscheduledbyExams;
*/}

	
	
public static List<Exam> OrderingUnscheduledExamsRandomly2(List<Exam> Exams){
	//Scanner d=new Scanner (stem.in);
	 boolean large=false;
	 short index=0;
	 for(short i=1;i<Exams.size();i++)
		{ index=i;
		  for(short j=(short)(i-1);j>=0;j--)
			 { if(Exams.get(i).FreePathWith.size()>=Exams.get(j).FreePathWith.size())
				 {
				 
				 large=true;
				   index=j; }
	           else
		           {break;}
	    	 }
		  if(large==true)
			{Exams.add(index,Exams.get(i));
			 Exams.remove(i+1);
			 large=false;}
		}
	

return Exams;
}	
	
public static List<Exam> OrderingbyConflict(List<Exam> Exams){
	//Scanner d=new Scanner (stem.in);
	 boolean large=false;
	 short index=0;
	 for(short i=1;i<Exams.size();i++)
		{ index=i;
		  for(short j=(short)(i-1);j>=0;j--)
			 { if(Exams.get(i).ConflictWithSize<=Exams.get(j).ConflictWithSize)
				 {
				 
				 large=true;
				   index=j; }
	           else
		           {break;}
	    	 }
		  if(large==true)
			{Exams.add(index,Exams.get(i));
			 Exams.remove(i+1);
			 large=false;}
		}
	

return Exams;
}		
	
	
public static  List<Exam> refreshOrderingFrontList(List<Exam> Exams){
	short ExamID;
	UnscheduledbyIDExams.clear();
	UnscheduledbyExams.clear();
	finalUnscheduledbyIDExams.clear();
	
	for(int i=0;i<Exams.size();i++)
	{
	
	if(i<(Exams.size()-1))
	{
		if((Exams.get(i).ConflictWithStudents-Exams.get(i+1).ConflictWithStudents)<5)
			{
			Exams.get(i).freePeriods-=1;
		Exams.get(i+1).freePeriods-=1;
		//System.out.println(Exams.get(i).getID());
		//System.out.println(Exams.get(i+1).getID());
			}
	}
	}
	
	
	
	boolean large=false;
	 short index1=0;
	 for(short i=1;i<Exams.size();i++)
		{ index1=i;
		  for(short j=(short)(i-1);j>=0;j--)
			 { if(Exams.get(i).freePeriods<Exams.get(j).freePeriods)
				 { large=true;
				   index1=j; }
	           else
		           {break;}
	    	 }
		  if(large==true)
			{Exams.add(index1,Exams.get(i));
			 Exams.remove(i+1);
			 large=false;}
		}

	
	
	
	
	for(short i=0;i<Exams.size();i++)
		{
		if(!UnscheduledbyIDExams.contains(Exams.get(i).getID()))
		{ if(!Exams.get(i).get_afterList().isEmpty())
			{
			   ExamID=Exams.get(i).getID();
			   for(short j=0;j<Exams.get(i).get_afterList().size();j++)
				  if(!UnscheduledbyIDExams.contains(Exams.get(i).get_afterList().get(j).getID()))	
					  UnscheduledbyIDExams.add(Exams.get(i).get_afterList().get(j).getID());
			   UnscheduledbyIDExams.add(ExamID);
			}
		else
			UnscheduledbyIDExams.add(Exams.get(i).getID());
		}
		
		}

//	for(short i=0;i<FrontListbyIDExam.size();i++)
//	stem.out.println(FrontListbyIDExam.get(i));
	
//**********generate FrontListbyExam via FrontListbyIDExam************************************************************
	short index=0;
	for(int s=0;s<UnscheduledbyIDExams.size();s++)
	{	
		for(int x=0;x<Exams.size();x++)
			if(UnscheduledbyIDExams.get(s)==Exams.get(x).getID())
				{finalUnscheduledbyIDExams.add(UnscheduledbyIDExams.get(s));
				break;
				
				}
		
	}
	

	//stem.out.println("IDDDDDDDDDDDDDDDD="+UnscheduledbyIDExams.size());
	//stem.out.println("Exaaaaaaaams="+Exams.size());
	for(short h=0;h<Exams.size();h++)
	{
		ExamID=finalUnscheduledbyIDExams.get(h);
		
		for(short g=0;g<Exams.size();g++)
			if(ExamID==Exams.get(g).getID())
			{index=g;
			
			break;
			}
	
		UnscheduledbyExams.add(Exams.get(index));
		
		
	}
	
	List<Short> studntsExam1=new ArrayList<>();
	List<Short> studntsExam2=new ArrayList<>();
	
	
	
	
	Exams.clear();
	Exams.addAll(UnscheduledbyExams);
	
	//for(int i=0;i<Exams.size();i++)
		//System.out.println("free period of Exams("+Exams.get(i).getID()+")="+Exams.get(i).freePeriods);
	
	return Exams;
}
	

}//End Class
