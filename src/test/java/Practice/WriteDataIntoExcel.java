package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcel {

	public static void main(String[] args) throws Throwable 
	{
		//Step 1: Load the file into file input stream in java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 2: Create a workbook
		Workbook wb=WorkbookFactory.create(fis);
		
		//Step 3: Get inside the sheet
		Sheet sheet=wb.getSheet("Organization");
		
		//Step 4: Get the used row
		Row row=sheet.getRow(1);
		
		//Step 5: Create a cell
		Cell cell=row.createCell(6);
		
		//Step 6: Write data into cell
		cell.setCellValue("Basalingol");
		
		//Step 7: Open the file in java write format and write into workbook
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		wb.write(fos);
		System.out.println("Data added");
		wb.close();
	}

}
