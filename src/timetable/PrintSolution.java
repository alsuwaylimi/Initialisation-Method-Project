package timetable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class PrintSolution {
	
	public static final File file=new File("Solution.sln");
	
	public  static void printing(Problem sol) throws FileNotFoundException, UnsupportedEncodingException{
		 PrintWriter p=new PrintWriter(file,"UTF-8");
		 p.print(sol.Exams.get(0).getPeriod().getID()+", "+sol.Exams.get(0).getRoom().getID()+"\r\n");
		 for(int i=1;i<sol.Exams.size();i++)
	p.append(sol.Exams.get(i).getPeriod().getID()+", "+sol.Exams.get(i).getRoom().getID()+"\r\n");
		 p.close();
}
	


}
