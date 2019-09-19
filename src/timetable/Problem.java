package timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem implements Cloneable{
private static short W2R=0;
private static short W2D=0;
private static short WPS=1;
public static short periodSpread=0;
private static short NonMix=0;
private static short LargestExams=0;
private static short LastPeriods=0;
private static short FLweight=0;
public static short totalConflictExams;
public  List<Student> Students= new ArrayList<>();
public   List<Exam> Exams= new ArrayList<>();

public   List<Period> Periods= new ArrayList<>();
public  List<ItcRoom> Rooms= new ArrayList<>();
public static Constraint constraint;
List<freePathObject> freeConflict=new ArrayList<>();
public short ConflictAfter=0;
public static short isComplete =0;

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
// the following are constructors 
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Problem (){}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Problem (byte dummy) throws FileNotFoundException{
	
	readData();
	setFrondload();
	setPeriodsLate();
	sort_allEntolledExam();
	ConflictMatrix.setCM(Exams);
	setConflictAfterForEachExam();
	setConflictWithSize_ForAllExam();
	setTotalConflictExams();
	orderExamsByID();
}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


//##############################################################################################################
//the following is copying object of Problem by value not by Reference
//##############################################################################################################



public Problem Copy() {
	  Problem pro = new Problem();

	  //Exam ExamPro=new Exam();
	  
	  // Exams list******************************************************************
	 
	  for(short i=0;i<this.Exams.size();i++)
	  {	  Exam ExamPro=new Exam();
ExamPro.setExamId(this.Exams.get(i).getID());
ExamPro.setDuration(this.Exams.get(i).getduration());	  
ExamPro.setFrontLoad(this.Exams.get(i).getFrontLoad());	  
ExamPro.setRoomExclusive(this.Exams.get(i).getRoomExclusive());
ExamPro.setSize(this.Exams.get(i).getSize());
/*ExamPro.set2RViolation(this.Exams.get(i).get2RViolation());
ExamPro.set2DViolation(this.Exams.get(i).get2DViolation());
ExamPro.setPSViolation(this.Exams.get(i).getPSViolation());
ExamPro.setFLViolation(this.Exams.get(i).getFLViolation());
ExamPro.setPPViolation(this.Exams.get(i).getPPViolation());
ExamPro.setRPViolation(this.Exams.get(i).getRPViolation());
ExamPro.setNMViolation(this.Exams.get(i).getNMViolation());
ExamPro.setTotalPenalty(this.Exams.get(i).getTotalPenalty());*/
ExamPro.conflictAfter=this.Exams.get(i).conflictAfter;
ExamPro.setAfterList(this.Exams.get(i).get_afterList());
ExamPro.setBeforeList(this.Exams.get(i).get_beforeList());
ExamPro.FreePathWith.addAll(this.Exams.get(i).FreePathWith);
ExamPro.setCoinList(this.Exams.get(i).get_CoinList());
ExamPro.setExclusionList(this.Exams.get(i).get_exclusionList());
ExamPro.ConflictWith=this.Exams.get(i).ConflictWith;
ExamPro.ConflictWithSize=this.Exams.get(i).ConflictWithSize;
ExamPro.ConflictWithStudents=this.Exams.get(i).ConflictWithStudents;
ExamPro.setPeroid(new Period(this.Exams.get(i).getPeriod().getID(),this.Exams.get(i).getPeriod().getDay(),this.Exams.get(i).getPeriod().getDuration(),this.Exams.get(i).getPeriod().getPenalty(),this.Exams.get(i).getPeriod().getLate()));
//if(this.Exams.get(i).getRoom()!=(null))
ExamPro.setRoom(new ItcRoom( this.Exams.get(i).getRoom().getID(), this.Exams.get(i).getRoom().getCapacity(),this.Exams.get(i).getRoom().getPenalty(),this.Exams.get(i).getRoom().getRoomExclusive(),this.Exams.get(i).getRoom().getFreeSize()));

ExamPro.enrolled=this.Exams.get(i).enrolled;
//for(short j=0;j<this.Exams.get(i).get_enrolledSize();j++)
	//ExamPro.add_enrolledStudent(new Student(this.Exams.get(i).get_AllStudentEnrolled().get(j).getID()));
 
ExamPro.after=this.Exams.get(i).after;
ExamPro.before=this.Exams.get(i).before;
ExamPro.coin=this.Exams.get(i).coin;
ExamPro.exclusion=this.Exams.get(i).exclusion;

	  pro.Exams.add(ExamPro);
	  
	  }
	  // Rooms
for(byte i=0;i<this.Rooms.size();i++){
	ItcRoom RoomsPro=new ItcRoom();
RoomsPro.setRoomID(this.Rooms.get(i).getID());
RoomsPro.setRoomCapacity(this.Rooms.get(i).getCapacity());
RoomsPro.setRoomPenalty(this.Rooms.get(i).getPenalty());
RoomsPro.setRoomFreeSize(this.Rooms.get(i).getFreeSize());
RoomsPro.setRoomExclusive(this.Rooms.get(i).getRoomExclusive());

for(short j=0;j<this.Rooms.get(i).getRoomExams().size();j++)
{RoomsPro.add_roomExamS(this.Rooms.get(i).getRoomExams().get(j));}	

pro.Rooms.add(RoomsPro);
}


// Periods
for(byte i=0;i<this.Periods.size();i++)
{Period periodPro=new Period();
for(short pe=0;pe<this.Periods.get(i).get_allPeriodExamS().size();pe++)
	periodPro.add_periodExamS(this.Periods.get(i).get_allPeriodExamS().get(pe));


for(byte j=0;j<this.Periods.get(i).get_allperiodRoomS().size();j++)
{ List<Exam> roomExams=new ArrayList<>();
for(short re=0;re<this.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().size();re++)
	roomExams.add(this.Periods.get(i).get_allperiodRoomS().get(j).getRoomExams().get(re));
	
	periodPro.add_periodRoomS(new ItcRoom(this.Periods.get(i).get_allperiodRoomS().get(j).getID(),
			                              this.Periods.get(i).get_allperiodRoomS().get(j).getCapacity(),
			                              this.Periods.get(i).get_allperiodRoomS().get(j).getPenalty(),
			                              this.Periods.get(i).get_allperiodRoomS().get(j).getFreeSize(),
			                              this.Periods.get(i).get_allperiodRoomS().get(j).getRoomExclusive(),
			                             roomExams
			                              ));
}	
periodPro.setPeriodID(this.Periods.get(i).getID());
periodPro.setPeriodDay(this.Periods.get(i).getDay());
periodPro.setLate(this.Periods.get(i).getLate());
periodPro.setPenalty(this.Periods.get(i).getPenalty());
periodPro.setPeriodDuration(this.Periods.get(i).getDuration());

pro.Periods.add(periodPro);
			               }
// Students
short examid;
for(short i=0;i<this.Students.size();i++)
{examid=0;
	Student studentPro=new Student();
	studentPro.setID(this.Students.get(i).getID());

for(short j=0;j<this.Students.get(i).get_EaxmList().size();j++)
	{
	for(short e=0;e<pro.Exams.size();e++)  {
		   //System.out.println("defghjkl"+pro.Exams.size());
	examid=0;
	if (pro.Exams.get(e).getID()==this.Students.get(i).get_EaxmList().get(j).getID())
	{   examid=e;
break;

}
	
	}
	studentPro.get_EaxmList().add(pro.Exams.get(examid));
	}
pro.Students.add(studentPro);
}

pro.constraint=this.constraint;






	  /*
	  
for(short i=0;i<this.Periods.size();i++)
	pro.Periods.add(copyPeriod(this.Periods.get(i)));





for(short i=0;i<this.Students.size();i++)
	pro.Students.add(copyStudent(this.Students.get(i)));
for(short i=0;i<this.Rooms.size();i++)
	pro.Rooms.add(copyRoom(this.Rooms.get(i)));
for(short i=0;i<this.Exams.size();i++)
	pro.Exams.add(copyExam(this.Exams.get(i),true,true,true));

	  return pro;
	}
public static Exam copyExam(Exam exam, boolean copyPeriod, boolean copyRoom, boolean copyPeriodRooms){
	Exam copyExam=new Exam();
	copyExam.setExamId(exam.getID());
	
	if(copyRoom==true && copyPeriodRooms==true)
	copyExam.setRoom(copyRoom(exam.getRoom()));

	
	if( copyPeriod==true && copyPeriodRooms==true)
	copyExam.setPeroid(copyPeriod(exam.getPeriod()));
		
	
	
	copyExam.setFrontLoad(exam.getFrontLoad());	
	copyExam.setDuration(exam.getduration());
	copyExam.setRoomExclusive(exam.getRoomExclusive());
	copyExam.setSize(exam.getSize());
	copyExam.setAfterList(exam.get_afterList());
	copyExam.setBeforeList(exam.get_beforeList());
	copyExam.setCoinList(exam.get_CoinList());
	copyExam.setExclusionList(exam.get_exclusionList());
copyExam.setEnrolledList(exam.get_AllStudentEnrolled());
	return copyExam; 
}

public static Period copyPeriod(Period period)
{Period copyPeriod=new Period();
copyPeriod.setPeriodID(period.getID());
copyPeriod.setPeriodDay(period.getDay());
copyPeriod.setLate(period.getLate());
copyPeriod.setPenalty(period.getPenalty());
for(short i=0;i<period.get_allperiodRoomS().size();i++)
copyPeriod.add_periodRoomS(copyRoom(period.get_allperiodRoomS().get(i)));
for(short i=0;i<period.get_allPeriodExamS().size();i++)
copyPeriod.add_periodExamS(copyExam(period.get_allPeriodExamS().get(i),false,true,true));
return copyPeriod;	
}

public static ItcRoom copyRoom(ItcRoom room)

{
ItcRoom copyRoom=new ItcRoom();
copyRoom.setRoomID(room.getID());
copyRoom.setRoomCapacity(room.getCapacity());
copyRoom.setRoomPenalty(room.getPenalty());
for(short i=0;i<room.getRoomExams().size();i++)
	copyRoom.add_roomExamS(copyExam(room.getRoomExams().get(i),true,false,false));

return copyRoom;
	
}

public static Student copyStudent(Student student)
{Student copyStudent=new Student();
copyStudent.setID(student.getID());
for(short i=0;i<student.get_EaxmList().size();i++)
	copyStudent.add_exam(copyExam(student.get_EaxmList().get(i),true,true,true));

return copyStudent;
*/
 	
return pro;
}


//##############################################################################################################


//============================================================================================================
// the following are set methods
//============================================================================================================

public static void setW2R(short w2R){
	W2R=w2R;
}

//============================================================================================================
public void setW2D(short w2D){
	W2D=w2D;
	}

//============================================================================================================
public void setWPS(short wPS){
	WPS=wPS;
	}

//============================================================================================================
public static void setPeriodSpread(short PeriodSpread){
    periodSpread=PeriodSpread;
	}

//============================================================================================================

public  void readData() throws FileNotFoundException
{
	File file=new File("dataSet.exam");
	Scanner read=new Scanner(file);
//-------------------------------------------------------------------------------------------------------------	
//[Exams]
	
	short Exam_cont=0;// used to point to next exam line in the inputfile and also give its value to the ID of exam
	short durationExam=0;
	short student_id;
	String studentsExam="";// used to read all exam line as String
	boolean isStudentNotFound=true;// used to add a new student to Students list if he is still not found (isStudentNotFound=true)and add the current exam ID to his exam list (S_timeTable) and if isStudentNotFound value is false then it means the student is already added and so add the current exam ID to his exam list   
	String NoExamLine=read.nextLine();// used to read the first line that contains number of exams in the input file as String
	String NoExam_string=NoExamLine.replaceAll("\\D+","");// read the first line and remove all the nonDigits character in order to convert it to short number later on 
	short NoExam_int= Short.parseShort(NoExam_string);// stores the number of exams as short by converting from String to short 
	isComplete = NoExam_int;
	short Student_index=0;// stores the student's index in the list (S_timeTable) if he is found in the list
	
	
while(Exam_cont<NoExam_int)// read all the lines of exams just depending on number of Exams provided by the first line and stored in NoExam_int variable  
{
	studentsExam=read.nextLine();// read the next lines which first value contains the duration of the exam and the rest contains the Students' ID enrolled in the Exam  
	String []tokens=studentsExam.split("[,]",0);// creates array named tokens that stores all the values of the current line by used split method 
	durationExam=Short.parseShort(tokens[0].trim());// read the first value in the line (duration of the Exam) and convert it to short number 
	Exams.add(new Exam(Exam_cont,durationExam,(short)(tokens.length-1)));// add the current Exam to the list by create Exam object contains some information (ID exam, Exam Duration, number of students enrolled in this Exam"i.e. Size of the Exam")
	
 for(short j=1;j<tokens.length;j++)// read all the rest values that contains all the enrolled students' ID in the current exam (i.e current line)
	 {  student_id=Short.parseShort(tokens[j].trim());
		Exams.get(Exam_cont).add_enrolledStudent(student_id);
	 if(Students.isEmpty())
		     {			  
			  Students.add(new Student(student_id));// add the current student to list of Students if the list Student is empty
			  Students.get(0).add_exam(Exams.get(Exam_cont));
			 // Students.get(0).add_exam(new Exam(Exam_cont,durationExam));// add the current exam to the timetable of current student 
		         
	         }
	 else
	   		  {   for(short k=0;k<Students.size();k++)// search for the current student if he is already added to the list.  if not, the value of isStudentNotFound remains "true" in order to add in the later staatment  
		            { if(Students.get(k).getID()==student_id)
		                { isStudentNotFound=false;
                          Student_index=k;// if the student is in the list, we want to know his index in the list in order to add the current exam to him in the later statement "  else {Students.get(Student_index).add_exam(new Exam(Exam_cont,durationExam));}   "   
		                  break;
		                }
		            }
		     				  
		  if(isStudentNotFound)
		     { Students.add(new Student(student_id));
		       //Students.get(Students.size()-1).add_exam(new Exam(Exam_cont,durationExam));// adding the current exam to the current student who is just added to the list, so his index will be (Students.size()-1) 
		     Students.get(Students.size()-1).add_exam(Exams.get(Exam_cont));
		     }
		 
		  else 
		     { 
			  
			  Students.get(Student_index).add_exam(Exams.get(Exam_cont));
			  isStudentNotFound=true;// the default case that the next student is not added yet.	  
		     }		
		 
		   
		
	   }} // end for that reads all the enrolled student in the current line (i.e exam )
 Exam_cont++;
}// end of the while that reads all the exam lines and will stop at the last Exam line 


//-------------------------------------------------------------------------------------------------------------
//[Periods]
//the next lines preparing to read the details of the periods provided to the Examination session

String NoPeriodLine=read.nextLine();
String NoPeriod_string=NoPeriodLine.replaceAll("\\D+","");
byte NoPeriod_int= Byte.parseByte(NoPeriod_string);
byte Period_cont=0;// counter gives IDs to the periods 
byte PeriodDay=0;// contains the number of the current period day
short PeriodDuration=0;// contains the length  of the current period duration 
short PeriodPenalty=0;// contains the value of the current period day
String PeriodDetails="";// contains the information of the current period as string
ArrayList<String> testDate= new ArrayList<>();// contains the dates of periods and it is used to increment the number of day if the date of the current period is not found 
byte setDay=0; // counter gives number of day to the current period ((the number of days start at zero))

while(Period_cont<NoPeriod_int)
{ PeriodDetails=read.nextLine();
  String []tokens=PeriodDetails.split("[,]",0);

  if(testDate.contains(tokens[0].trim()))// tokens[0] always contains the date of period, based on that, if the list contains the date , then it means the current period is still in the same day with the immediately previous period 
       {PeriodDay=setDay;}
  else
       {testDate.add(tokens[0].trim());
     	setDay++;
	    PeriodDay=setDay;}

   PeriodDuration=Short.parseShort(tokens[2].trim());// tokens[2] always read the duration of current period 
   PeriodPenalty=Short.parseShort(tokens[3].trim());// // tokens[3] always read the penalty of current period
   
   Periods.add(new Period(Period_cont, PeriodDay, PeriodDuration, PeriodPenalty) );// creates an object for the current period and adds to the list Periods
   
   Period_cont++; 
}

//-------------------------------------------------------------------------------------------------------------
// [Rooms]
//the next lines preparing to read the details of the Rooms provided to the Examination session

String NoRoomLine=read.nextLine();
String NoRoom_string=NoRoomLine.replaceAll("\\D+","");
byte NoRoom_int= Byte.parseByte(NoRoom_string);
byte Room_cont=0;// counter gives IDs to the periods
String RoomDetails="";// contains the information of the current room as string
short RoomCapacity=0;
short RoomPenalty=0;
while(Room_cont<NoRoom_int)
{ RoomDetails=read.nextLine();
  String []tokens=RoomDetails.split("[,]",0);
RoomCapacity=Short.parseShort(tokens[0].trim());
RoomPenalty=Short.parseShort(tokens[1].trim());
Rooms.add(new ItcRoom(Room_cont, RoomCapacity, RoomPenalty));
for(byte i=0;i<Periods.size();i++)
Periods.get(i).add_periodRoomS(new ItcRoom(Room_cont, RoomCapacity, RoomPenalty));
Room_cont++;
}


// ordering the rooms increasingly by their capacities
//**************************************************************************
//for(short p=0;p<Periods.size();p++)
	
//{
	//for(short r=0;r<Periods.get(p).get_allperiodRoomS().size();r++)
		//System.out.print(r+" "); 

boolean large=false;
byte indexr=0;
for(byte p=0;p<Periods.size();p++)
{
	for(byte i=1;i<Periods.get(p).get_allperiodRoomS().size();i++)
{indexr=i;
	
		for(byte j=(byte) (i-1);j>=0;j--)
{if(Periods.get(p).get_allperiodRoomS().get(i).getCapacity()<Periods.get(p).get_allperiodRoomS().get(j).getCapacity())
			{
	large=true;
			indexr=j;
			}

else
{	break;
}

		
}
if(large==true)
		{Periods.get(p).get_allperiodRoomS().add(indexr,Periods.get(p).get_allperiodRoomS().get(i));
		Periods.get(p).get_allperiodRoomS().remove(i+1);
		large=false;
}}

}










//}

//test ordering of periods rooms

/*
    for(short p=0;p<Periods.size();p++)
    {System.out.println("period No="+p);
	for(short i=0;i<Periods.get(0).get_allperiodRoomS().size();i++) 
		System.out.println(Periods.get(0).get_allperiodRoomS().get(i).getID());
	}
*/

//End of ordering the rooms increasingly by their capacities









/*
for(short i=0;i<Periods.size();i++)
Periods.get(i).set_PeriodRooms(Rooms);
*/
//-------------------------------------------------------------------------------------------------------------
//[PeriodHardConstraints]
//the next lines preparing to read the details of the Room HardConstraints provided to the Examination session

read.nextLine();
String PeriodHardCons_Line="";
short examNo1=0;
short examNo2=0;
Exam exam1=null;
Exam exam2=null;
String typeOfConstraint="";
short index=0;
constraint=new Constraint();

while(true)
 {PeriodHardCons_Line=read.nextLine().trim();
  if(!PeriodHardCons_Line.equals("[RoomHardConstraints]"))
	 {      String []tokens=PeriodHardCons_Line.split("[,]",0);
		    examNo1=Short.parseShort(tokens[0].trim());
		    typeOfConstraint=tokens[1].trim();
		    examNo2=Short.parseShort(tokens[2].trim());
		
		
		for(short i=0;i<Exams.size();i++)
		   { if(Exams.get(i).getID()==examNo1)
		         {exam1=Exams.get(i);}			      
			 if ( Exams.get(i).getID()==examNo2)
			     exam2=Exams.get(i);
		   }//end if .i.e end searching           
		

		
		if(typeOfConstraint.equals("EXAM_COINCIDENCE"))
		   { if(!exam1.get_CoinList().contains(exam2) & !exam1.equals(exam2))
			   exam1.get_CoinList().add(exam2);
		   for(short i=0;i<exam2.get_CoinList().size();i++)
			   {if(!exam1.equals(exam2.get_CoinList().get(i))& !exam1.get_CoinList().contains(exam2.get_CoinList().get(i)))
			     exam1.get_CoinList().add(exam2.get_CoinList().get(i));
			   
			   if(!exam2.get_CoinList().get(i).equals(exam1) & ! exam2.get_CoinList().get(i).get_CoinList().contains(exam1))
				   exam2.get_CoinList().get(i).add_Coin(exam1);
			   }
		   
		   //for exam2
		   
		   
		   if(!exam2.get_CoinList().contains(exam1)& !exam2.equals(exam1))
			   exam2.get_CoinList().add(exam1);
		   for(short i=0;i<exam1.get_CoinList().size();i++)
			   {if(!exam2.equals(exam1.get_CoinList().get(i))& !exam2.get_CoinList().contains(exam1.get_CoinList().get(i)))
			     exam2.get_CoinList().add(exam1.get_CoinList().get(i));
			   
			   if(!exam1.get_CoinList().get(i).equals(exam2) & ! exam1.get_CoinList().get(i).get_CoinList().contains(exam2))
				   exam1.get_CoinList().get(i).add_Coin(exam2);
			   }
			constraint.add_listCoin(new Constraint(exam1,exam2));
		   }
			/*if(!exam1.get_CoinList().isEmpty())
			for(short i=0;i<exam1.get_CoinList().size();i++)
			{if(!exam1.get_CoinList().get(i).get_CoinList().contains(exam2))
			    exam1.get_CoinList().get(i).get_CoinList().add(exam2);}
		   
		   for(short i=0;i<exam1.get_CoinList().size();i++)
			{if(!exam2.get_CoinList().contains(exam1.get_CoinList().get(i)))
			    exam2.get_CoinList().add(exam1.get_CoinList().get(i));}
		   
		   if(!exam1.get_CoinList().contains(exam2))	        	
		        exam1.add_Coin(exam2); 
		 //***************************************exam1********************
		   if(!exam2.get_CoinList().isEmpty())
				for(short i=0;i<exam2.get_CoinList().size();i++)
				{if(!exam2.get_CoinList().get(i).get_CoinList().contains(exam1))
				    exam2.get_CoinList().get(i).get_CoinList().add(exam1);}
			   
		   for(short i=0;i<exam2.get_CoinList().size();i++)
			{if(!exam1.get_CoinList().contains(exam2.get_CoinList().get(i)))
			    exam1.get_CoinList().add(exam2.get_CoinList().get(i));}
			   
			   if(!exam2.get_CoinList().contains(exam1))	        	
			        exam2.add_Coin(exam1); 
			   */
			   
		   //lll
		    //if(!exam2.get_CoinList().contains(exam1))	        	
		        //exam2.add_Coin(exam1);           	
		    
		
      	
		if(typeOfConstraint.equals("AFTER"))
	      { 
			exam1.add_After(exam2);
			exam2.add_Before(exam1);
            constraint.add_listAfter(new Constraint(exam1,exam2));
          }
	  
		if(typeOfConstraint.equals("EXCLUSION"))
        { 
      	    exam1.add_Exclusion(exam2);
      	    exam2.add_Exclusion(exam1);
            constraint.add_listExclusion(new Constraint(exam1,exam2));
        }
	  }       		
	
  else
		break;
 }

Exam e;

for(int m=0;m<Exams.size();m++)
	for(int c=0;c<Exams.get(m).get_CoinList().size();c++)
		for(int a =0;a<Exams.get(m).get_afterList().size();a++)
			{//System.out.println(Exams.get(m).getID()+"  "+m);
			e=Exams.get(Exams.get(m).get_CoinList().get(c).getID());
			if(!e.get_afterList().contains(Exams.get(m).get_afterList().get(a)));
             e.get_afterList().add(Exams.get(m).get_afterList().get(a));
			}


for(int m=0;m<Exams.size();m++)
	for(int c=0;c<Exams.get(m).get_CoinList().size();c++)
		for(int a =0;a<Exams.get(m).get_beforeList().size();a++)
			{e=Exams.get(Exams.get(m).get_CoinList().get(c).getID());
			if(!e.get_beforeList().contains(Exams.get(m).get_beforeList().get(a)));
             e.get_beforeList().add(Exams.get(m).get_beforeList().get(a));
			}

//-------------------------------------------------------------------------------------------------------------
//[[RoomHardConstraints]]
//the next lines preparing to read the details of the periods HardConstraints provided to the Examination session

String RoomHardCons_Line="";
short examExclNo=0;
Exam examExcl=null;
while(true)
{   RoomHardCons_Line=read.nextLine();
	if(!RoomHardCons_Line.equals("[InstitutionalWeightings]"))
	  {
		String []tokens=RoomHardCons_Line.split("[,]",0);		
	    examExclNo=Short.parseShort(tokens[0].trim());
	    //System.out.println(examExclNo);    
	   for(short i=0;i<Exams.size();i++)
	   {  if(Exams.get(i).getID()==examExclNo)
	   { examExcl=Exams.get(i);}
	   } 
        examExcl.setRoomExclusive(true);
        constraint.add_ListRoomExclusive(examExcl);
      }

	else
	break;
}	

//-------------------------------------------------------------------------------------------------------------
//the next lines preparing to read the details of the Weights of Soft Constraints provided to the Examination session
String weight="";

//-------------------------------------------------------------------------------------------------------------
//TWOINAROW
weight=read.nextLine();
String []Rtokens=weight.split("[,]",0);
if(Rtokens[0].equals("TWOINAROW"))
	W2R=Short.parseShort(Rtokens[1].trim());

//-------------------------------------------------------------------------------------------------------------
//TWOINADAY
weight=read.nextLine();
String []Dtokens=weight.split("[,]",0);
if (Dtokens[0].equals("TWOINADAY"))
	W2D=Short.parseShort(Dtokens[1].trim());

//-------------------------------------------------------------------------------------------------------------
//PERIODSPREAD
weight=read.nextLine();
String []PStokens=weight.split("[,]",0);
if (PStokens[0].equals("PERIODSPREAD"))
	periodSpread=Short.parseShort(PStokens[1].trim());

//-------------------------------------------------------------------------------------------------------------
//NONMIXEDDURATIONS
weight=read.nextLine();
String []NonMixtokens=weight.split("[,]",0);
if (NonMixtokens[0].equals("NONMIXEDDURATIONS"))
	NonMix=Short.parseShort(NonMixtokens[1].trim());

//-------------------------------------------------------------------------------------------------------------
// FRONTLOAD
weight=read.nextLine();
String []FLoadtokens=weight.split("[,]",0);
if (FLoadtokens[0].equals("FRONTLOAD"))
	LargestExams=Short.parseShort(FLoadtokens[1].trim());
LastPeriods=Short.parseShort(FLoadtokens[2].trim());
FLweight=Short.parseShort(FLoadtokens[3].trim());

//the following line to know the size of conflict matrix

read.close();
}

//============================================================================================================
//set the first n largest exams true i.e Exam.FrontLoad=true;
//this solution for just testing and we must take the second solution 
public  void setFrondload()
{
	
//soltion suitable just for dataSet 
//for(short i=0;i<Exams.size();i++)
	//if(Exams.get(i).getSize()>15)
//Exams.get(i).setFrontLoad(true);

//Second Solution==============Remove the above solution and remove the remark sign below
boolean large=false;
short index=0;
	for(short i=1;i<Exams.size();i++)
{index=i;
	
		for(short j=(short)(i-1);j>=0;j--)
{if(Exams.get(i).getSize()>Exams.get(j).getSize())
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

for(short i=0;i<LargestExams;i++)	
	Exams.get(i).setFrontLoad(true);
//end of the second solution that is true for our work 	

}//end of method setFrontLoad


//============================================================================================================
//set Periods late
public  void setPeriodsLate()
{if(LastPeriods>=Periods.size())
{for(byte i=0;i<Periods.size()-1;i++)
	Periods.get(i).setLate();
}
else
for(byte i=(byte) (Periods.size()-1);i>((Periods.size()-1)-LastPeriods);i--)
	Periods.get(i).setLate();
}

//============================================================================================================

//set TotalConflictExams
public  void setTotalConflictExams(){
	for(short i=0;i<Exams.size();i++)
		totalConflictExams+=Exams.get(i).ConflictWith.size();
	//System.out.println(totalConflictExams);
}


//============================================================================================================


public void setConflictWithSize_ForAllExam()
{
for(short i=0;i<Exams.size();i++)
	Exams.get(i).ConflictWithSize=(short) Exams.get(i).ConflictWith.size();
}



//============================================================================================================
public void setConflictAfterForEachExam()
{
for(short i=0;i<Exams.size();i++)
	{freeConflict.clear();
	//ConflictAfter=0;
	computeConflictAfter(Exams.get(i));
Exams.get(i).conflictAfter=(short)Exams.get(i).FreePathWith.size();}

   // for(short y=0;y<Exams.get(2).FreePathWith.size();y++)
    //		System.out.println("fg="+Exams.get(2).FreePathWith.get(y));

     //    Exams.get(i).conflictAfter=ConflictAfter;
     
	//}
   //  for(short i=0;i<Exams.get(0).ConflictWith.size();i++)
    //	 System.out.println(Exams.get(0).ConflictWith.get(i));
     
}

// not use yet
public void computeConflictAfter(Exam e){
	
for(short j=0;j<Exams.size();j++)
{	if(j!=e.getID())
	if(!e.ConflictWith.contains(j))
		freeConflict.add(new freePathObject(j));
}

if(freeConflict.size()>0){
//    ConflictAfter++;
//	maxConflictAfter(e);}
	
	short count=0;
short max=0;
short maxExam=0;

for(short n=0;n<freeConflict.size();n++)
	{count=0;
	for(short k=0;k<freeConflict.size();k++)
	{	if( ConflictMatrix.conflictMatrix[freeConflict.get(n).getExamID()][freeConflict.get(k).getExamID()]==0)
			{
			freeConflict.get(n).incrementNoFreeConflict();
			}
	}
	}
// decreasing order freeConflict by NoFreeWith**********************************************************

boolean large=false;
int index=0;
	for(short i=1;i<freeConflict.size();i++)
{index=i;
	
		for(int j=i-1;j>=0;j--)
{if(freeConflict.get(i).getNoFreeWith()>freeConflict.get(j).getNoFreeWith())
{
	large=true;
			index=j;
			}

else
{	break;
}

		
}
if(large==true)
		{freeConflict.add(index,freeConflict.get(i));
		freeConflict.remove(i+1);
		large=false;
}}

// decreasing order freeConflict by NoFreeWith**********************************************************
	


	//{//System.out.println(Exams.get(j).enrolled.get(i).getID());}
	//System.out.println("***************************");}
	
//	System.out.println("zerooooo="+ConflictMatrix.conflictMatrix[9][0]);



while(freeConflict.size()>0)
{maxExam=(short)freeConflict.get(0).getExamID();
e.FreePathWith.add(maxExam);

	short k=1;
	while(k<freeConflict.size())
	{
	

		if(ConflictMatrix.conflictMatrix[maxExam][freeConflict.get(k).getExamID()]!=0)
			{
			freeConflict.remove(k);k--;
			}
	k++;	
	}


	
	freeConflict.remove(0);   
	if(freeConflict.size()>0)
	maxExam=(short)freeConflict.get(0).getExamID();


   



}}}



/*
public void setConflictAfterForEachExam()
{

for(short i=0; i<Exams.size();i++)
	{freeConflict.clear();
	ConflictAfter=0;
	computeConflictAfter(i);
	Exams.get(i).conflictAfter=ConflictAfter;
	System.out.println("Exam("+i+")="+Exams.get(i).conflictAfter);
	
	//System.out.println("lkl"+Exams.get(i).conflictAfter);
	}

}

public void computeConflictAfter(short i){
	
for(short j=0;j<Exams.size();j++)
//freeConflict.clear();
	if(ConflictMatrix.conflictMatrix[i][j]==0)
		freeConflict.add(j);
//for(short t=0;t<freeConflict.size();t++)
//System.out.print("bgfb"+freeConflict.get(t));	
//}
if(freeConflict.size()>0){
    ConflictAfter++;
	maxConflictAfter();}

}

public void maxConflictAfter(){ 
	
	short count=0;
short max=0;
short maxExam=0;

for(short n=0;n<freeConflict.size();n++)
	{count=0;
	for(short k=0;k<freeConflict.size();k++)
		if(ConflictMatrix.conflictMatrix[freeConflict.get(n)][freeConflict.get(k)]==0)
			count++;
     
	if (count>max)
	{ max=count;	
		maxExam=freeConflict.get(n);
		
	}
	}


//System.out.println("index="+freeConflict.indexOf(maxExam));
	if(max>0)
	{
		ConflictAfter++;
		short k=0;
		while(k<freeConflict.size()){
			if(freeConflict.get(k)!=maxExam)
			if(ConflictMatrix.conflictMatrix[maxExam][freeConflict.get(k)]==1)
				{freeConflict.remove(k);k--;}
		k++;	
		}
		freeConflict.remove(freeConflict.indexOf(maxExam));   
		//for(short t=0;t<freeConflict.size();t++)
		//	System.out.println("element"+freeConflict.get(t));
		if(freeConflict.size()>0)
		  maxConflictAfter();
	}
	
       
	
	}

*/

//============================================================================================================
// sorting enrolled list at each exam preparing for binary search in order to generate conflict matrix
public void sort_allEntolledExam(){
	
	for(short e=0;e<Exams.size();e++)
	{
			  boolean smaller=false;
    short index=0;
    	for(short i=1;i<Exams.get(e).enrolled.size();i++)
    {
   		index=i;
    	
    		for(short j=(short)(i-1);j>=0;j--)
    {if(Exams.get(e).enrolled.get(i).getID()<Exams.get(e).enrolled.get(j).getID())
    			{
    	smaller=true;
    			index=j;
    			}
    else
    {	break;    }
    }
    if(smaller==true)
    		{Exams.get(e).enrolled.add(index,Exams.get(e).enrolled.get(i));
    		Exams.get(e).enrolled.remove(i+1);
    		smaller=false;
    }}	}
	//for(short j=0;j<Exams.size();j++)
	//{for(short i=0;i<Exams.get(j).enrolled.size();i++)
	//{//System.out.println(Exams.get(j).enrolled.get(i).getID());}
	//System.out.println("***************************");}
	
	}

//============================================================================================================
//reording Exams by their IDs
public  void orderExamsByID()
{
	boolean large=false;
	short index=0;
		for(short i=1;i<Exams.size();i++)
	{index=i;
		
			for(short j=(short)(i-1);j>=0;j--)
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

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// the following methods return ArrayLists
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public static short getW2R(){
	return W2R;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static short getW2D(){
	return W2D;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static short getWPS(){
	return WPS;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static short getPeriodSpread(){
	return periodSpread;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static short getNonMixDuration(){
	return NonMix;
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static short getLargeExam(){
	return LargestExams;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static short getLastPeriod(){
	return LastPeriods;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static short getFrontLoadWeight(){
	return FLweight;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public List<Exam> getExams(){
	return Exams;
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public List<Period> getPeriods(){
	return Periods;
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public List<ItcRoom> getRooms(){
	return Rooms;
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public Problem get()
{
	return this;
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




//************************************************************************************************************
// the following methods are printing methods to test some array list and variables
//************************************************************************************************************
public void print_allExamsInfo()
{Scanner nn=new Scanner(System.in);
 for(short i=0; i<Exams.size(); i++)
    {System.out.println("index("+i+")=> ID="+Exams.get(i).getID()+", Duration="+Exams.get(i).getduration()+
	           ", Size="+Exams.get(i).getSize()+", ConflictWithSize="+Exams.get(i).ConflictWithSize+
	           ", Room=Free path size="+Exams.get(i).conflictAfter+
	           ", Room="+Exams.get(i).getRoom().getID()+
	           ", Period="+Exams.get(i).getPeriod().getID()+", Exclusive="+Exams.get(i).getRoomExclusive()+
	           ", FrontLoad="+Exams.get(i).getFrontLoad()+
	           ", totalPenalty="+Exams.get(i).getTotalPenalty());          
    System.out.print("coincedence with=");
    for(short c=0;c<Exams.get(i).get_CoinList().size();c++)
    	System.out.print(" "+Exams.get(i).get_CoinList().get(c).getID());
         
    System.out.println();
    System.out.print("Exclusion with=");
    for(short e=0;e<Exams.get(i).get_exclusionList().size();e++)
    System.out.print(" "+Exams.get(i).get_exclusionList().get(e).getID());
    System.out.println();
    System.out.print("After=");
    for(short a=0;a<Exams.get(i).get_afterList().size();a++)
    	System.out.print(" "+Exams.get(i).get_afterList().get(a).getID());
    System.out.println();	
    System.out.print("Before=");
    for(short b=0;b<Exams.get(i).get_beforeList().size();b++)
    	System.out.print(" "+Exams.get(i).get_beforeList().get(b).getID());
    System.out.println();
    System.out.print("Conflict With=");
    for(short c=0;c<Exams.get(i).ConflictWith.size();c++)
   	 System.out.print(" "+Exams.get(i).ConflictWith.get(c));
    System.out.println();
    System.out.print("Free path With Size("+Exams.get(i).FreePathWith.size()+")=");
    for(short c=0;c<Exams.get(i).FreePathWith.size();c++)
      	 System.out.print(" "+Exams.get(i).FreePathWith.get(c));
    System.out.println( "\n************************************************************************************************");
	if((i+1)%50==0)
		nn.nextLine();
		
    }	
}

//************************************************************************************************************
public void print_InformationOfSpeceficExam(short IDExam)
{
	
	
	System.out.println("all information of Exam No("+IDExam+"):");
	short index=0;
	for(short i=0;i<Exams.size();i++)
	{if(Exams.get(i).getID()==IDExam)
	{index=i;
	break;	
	}	}
	

    System.out.println("index("+index+")=> ID="+Exams.get(index).getID()+", Duration="+Exams.get(index).getduration()+
	           ", Size="+Exams.get(index).getSize()+", ConflictWithSize="+Exams.get(index).ConflictWithSize+", Room="+Exams.get(index).getRoom().getID()+
	           ", Period="+Exams.get(index).getPeriod().getID()+", Exclusive="+Exams.get(index).getRoomExclusive()+
	           ", FrontLoad="+Exams.get(index).getFrontLoad());          
    
    
 System.out.print("coincedence with=");
 for(short c=0;c<Exams.get(index).get_CoinList().size();c++)
 	System.out.print(" "+Exams.get(index).get_CoinList().get(c).getID());
      
 System.out.println();
 System.out.print("Exclusion with=");
 for(short e=0;e<Exams.get(index).get_exclusionList().size();e++)
 System.out.print(" "+Exams.get(index).get_exclusionList().get(e).getID());
 System.out.println();
 System.out.print("After=");
 for(short a=0;a<Exams.get(index).get_afterList().size();a++)
 	System.out.print(" "+Exams.get(index).get_afterList().get(a).getID());
 System.out.println();	
 System.out.print("Before=");
 for(short b=0;b<Exams.get(index).get_beforeList().size();b++)
 	System.out.print(" "+Exams.get(index).get_beforeList().get(b).getID());
 System.out.println();
 System.out.print("Conflict With=");
 for(short c=0;c<Exams.get(index).ConflictWith.size();c++)
	 System.out.print(" "+Exams.get(index).ConflictWith.get(c));
 System.out.println( "************************************************************************************************");

}	

//************************************************************************************************************
public  void print_allStudentsinAllExams()
{
	for(short i=0;i<Exams.size();i++)
		{System.out.print("ExamNo("+Exams.get(i).getID()+")=");
		
		List<Student> enroll=Exams.get(i).get_AllStudentEnrolled();
			  for(short k=0;k<enroll.size();k++)
				  System.out.print(enroll.get(k).getID()+", ") ;
			
				System.out.println();	
			}
	
	}

//************************************************************************************************************	
public  void Print_allStudentsOfSpecificExam(short IDExam
){
	System.out.println("Students enrolled in Exam No("+IDExam+"):");
	short index=0;
	for(short i=0;i<Exams.size();i++)
	{if(Exams.get(i).getID()==IDExam)
	{index=i;
	break;	
	}}
		
	for(short i=0;i<Exams.get(index).getSize();i++)
		System.out.println("Student ID="+Exams.get(index).get_AllStudentEnrolled().get(i).getID());
}

//************************************************************************************************************
public void print_allExamsOfSpecificStudent(short IDstudent)
{
	System.out.println("all Exams information of Student ID("+IDstudent+"):");
	short index=0;
	for(short i=0;i<Students.size();i++)
		{if(Students.get(i).getID()==IDstudent)
		{index=i;
		break;
			
		}}
for( short i=0;i<Students.get(index).get_EaxmList().size();i++)
	print_InformationOfSpeceficExam(Students.get(index).get_EaxmList().get(i).getID());

}

//************************************************************************************************************
 public  void print_allPeriodsInfo()
{
	 for(byte i=0;i<Periods.size();i++)
	 { System.out.println("ID="+ Periods.get(i).getID()+",  Day="+Periods.get(i).getDay()+
			 ", Duration="+Periods.get(i).getDuration()+", Penalty="+Periods.get(i).getPenalty()+
			 ", Late="+Periods.get(i).getLate());}
	 
	 
	 }

//************************************************************************************************************
public void Print_allInformationOFSpecificPeriod(int PeriodID)
{List<ItcRoom> rooms=new ArrayList<>();
rooms=Periods.get(PeriodID).get_allperiodRoomS();
System.out.println("Number of room="+rooms.size());
for(byte i=0;i<rooms.size();i++)	
{	System.out.println("room ID("+rooms.get(i).getID()+"):");
	for(short j=0;j<rooms.get(i).getRoomExams().size();j++)
		System.out.println("ExamID="+rooms.get(i).getRoomExams().get(j).getID());
}}

//************************************************************************************************************
 public  void print_allstudentsIDs()
{
		for(short i=0;i<Students.size();i++)
			System.out.println("index("+i+")=>"+" student.ID="+Students.get(i).getID());
	}
	
//************************************************************************************************************
public static void print_allWeightsInfo()
{		
System.out.println("W2R="+ W2R+", W2D="+W2D+", WPS="+WPS+", PeriodSpreadS="+periodSpread+", NonMixDuration="+NonMix+", number of First Largest Exams="+LargestExams+", Last Period="+LastPeriods+", Front Load weight="+FLweight);

}

//************************************************************************************************************



}