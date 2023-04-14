package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFilePractice {

	public static void main(String[] args) throws Throwable 
	{
		//Step 1: Load the file in Java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 2: Create a workbook
		Workbook wb=WorkbookFactory.create(fis);
		
		//Step 3: Get control over sheet
		Sheet sheet=wb.getSheet("Organization");
		
		//Step 4: Get control over row
		Row row=sheet.getRow(1);
		
		//Step 5: Get control over cell
		Cell cell=row.getCell(2);
		
		//Step 6: Read data inside the cell
		String value = cell.getStringCellValue();
		System.out.println(value);
	}

}
