package timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConflictRefresh {
static	List<Student> e1=null;
static	List<Student> e2=null;
List<Integer> temp_ConflictWith=new ArrayList<>();
static int key=0;	
public static int [][]conflictMatrix;
public static void setCM(List<Exam> exams){
	conflictMatrix=new int[exams.size()][exams.size()];
	
	for(int i=0;i<exams.size();i++)
		{e1=exams.get(i).enrolled;
		
		for(int j=0;j<exams.size();j++)	
           {e2=exams.get(j).enrolled;
           if(i==j)
        	   conflictMatrix[i][j]=exams.get(j).getSize();
           else
           { conflictMatrix[i][j]=  computeConflict();
        //   if(conflictMatrix[i][j]!=0)
          // exams.get(i).ConflictWith.add(exams.get(j).getID());
           }
           
					
           }
		
		
		}




	computeConflictwithsize(exams) ;



}

public static int computeConflict(){
int NOconflict=0;
	for(int i=0;i<e1.size();i++)
	{key=e1.get(i).getID();
	NOconflict+=BinarySearch();
	}
	return NOconflict;
	} 

public static int BinarySearch(){
	
	int low = 0;
	  int high = e2.size() - 1;
	  while (high >= low) 
		  {   int mid = (low + high) / 2;
	    if (key < e2.get(mid).getID())
	      high = mid - 1;
	    else if (key == e2.get(mid).getID())
	      return 1;
	    else
	      low = mid + 1;
	  }
	  return 0;
	


}

public static void computeConflictwithsize(List<Exam> exams) {
	short size=0;
	for(int i=0;i<exams.size();i++)
	{size=0;
		for( int j=0;j<exams.size();j++)
		{ 
		if(conflictMatrix[i][j]!=0)
			size++;
		}
    exams.get(i).ConflictWithSize=size; 
	//System.out.println(exams.get(i).ConflictWithStudents);
	}
	

	
}




public static void computeConflictwithStudents(List<Exam> exams) {
	Scanner dd=new Scanner(System.in);
int size=0;
	for(int i=0;i<exams.size();i++)
	{	size=0;
		for( int j=0;j<exams.size();j++)
		{ 
		size+=conflictMatrix[i][j];
		}
    exams.get(i).ConflictWithStudents=size; 
	//System.out.println(exams.get(i).ConflictWithStudents);
	}
	

}

}