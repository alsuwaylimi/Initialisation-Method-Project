package timetable;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class PrintXls {
	
	
	public static List<PrintResults>  experiment=new ArrayList<>();


	public static void PrintXlss(List<PrintResults> experiment) throws WriteException, IOException{
		
		
		        File file=new File("firstexcel.xls");
		        if(!file.exists())
		        {
		        	createExcel();      
		        } 
		        	writingExcel();	         
	}

	public static void createExcel() throws IOException, WriteException {

		    	try{
		        WritableWorkbook workbook = Workbook.createWorkbook(new File("firstexcel.xls"));
		        workbook.createSheet("firstexcel.xls",0);
		        workbook.write();
		        workbook.close();
		    	} 
		    	catch (Exception e){
		    		e.printStackTrace();
		    	}
		      
		    }
		    
		    public static void writingExcel(){
			try{
				
		        Workbook aWorkBook = Workbook.getWorkbook(new File("firstexcel.xls"));
		        WritableWorkbook aCopy = Workbook.createWorkbook(new File("firstexcel.xls"), aWorkBook);
		        WritableSheet aCopySheet = aCopy.getSheet(0);
		        
		       // Label label0 =  new Label(0, 0, "Instance");
		        Label label0 =  new Label(0, 0, experiment.get(0).getInstanceName());

		        Label label1 =  new Label(1, 0, "Approach");
		        
		        Label label2 =  new Label(2, 0, "Cost Value");
		        Label label3 =  new Label(3, 0, "Time");
		        
		        aCopySheet.addCell((WritableCell) label0);
		        aCopySheet.addCell((WritableCell) label1);
		        aCopySheet.addCell((WritableCell) label2);
		        aCopySheet.addCell((WritableCell) label3);
		        
		        for(int i=1; i<=experiment.size();i++){
		        	int k=0;
					aCopySheet.addCell((WritableCell) new Label(0, i, experiment.get(k).getInstanceName()));
			    	   k++;
		        }
		        
		        for(int i=1; i<=experiment.size();i++){
		        	int k=0;
					aCopySheet.addCell((WritableCell) new Label(1, i, experiment.get(k).getApproahc()));
			    	   k++;
		        }
		        
		        for(int i=1; i<=experiment.size();i++){
		        	int k=0;
					aCopySheet.addCell((WritableCell) new Label(2, i, experiment.get(k).getCostValue()));
			    	   k++;
		        }
		        
		        for(int i=1; i<=experiment.size();i++){
		        	int k=0;
					aCopySheet.addCell((WritableCell) new Label(3, i, experiment.get(k).getTime()));
			    	   k++;
		        }



		        aCopy.write();
		        aCopy.close();

			} catch (Exception e){
	    		e.printStackTrace();
	    	}
			
			


	}
	
	
	
	
	

}
