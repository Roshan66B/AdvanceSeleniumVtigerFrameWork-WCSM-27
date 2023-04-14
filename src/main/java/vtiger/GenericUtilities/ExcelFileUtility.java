package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of generic methods related to excel file
 * @author Asus
 *
 */
public class ExcelFileUtility {

	/**
	 * This method will read data from excel file
	 * @param SheetName
	 * @param rowNo
	 * @param celNo
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcel(String SheetName, int rowNo, int celNo) throws EncryptedDocumentException, IOException
	{
		/**
		 * This method will read data from excel sheet
		 */
		FileInputStream fis=new FileInputStream(IConstantsUtility.excelfilePath);
		Workbook wb=WorkbookFactory.create(fis);
		String value = wb.getSheet(SheetName).getRow(rowNo).getCell(celNo).getStringCellValue();
		wb.close();
		return value;
	}
	
	/**
	 * This method will write data into excel
	 * @param SheetName
	 * @param rowNo
	 * @param celNo
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeIntoExcel(String SheetName, int rowNo, int celNo, String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantsUtility.excelfilePath);
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(SheetName).createRow(rowNo).createCell(celNo).setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream(IConstantsUtility.excelfilePath);
		wb.write(fos);
		wb.close();
	}
	
	/**
	 * This method will read data from excel sheet and return it to data provider
	 * @param SheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readDataFromExcelToDataProvider(String SheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantsUtility.excelfilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet=wb.getSheet(SheetName);
		int lastRow = sheet.getLastRowNum();
		int lastCell=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[lastRow][lastCell];
		for(int i=0;i<lastRow;i++) //row
		{
			for(int j=0;j<lastCell;j++) //cell
			{
				data[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
						
			}
		}
		return data;
	}
}
