/**
 * 
 */
package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

/**
 * @Author Akash Naykude
 * 04-Mar-2024
 */
public class DataSupplierClass extends BaseClass
{

	public DataSupplierClass() throws Exception {}
	
	@DataProvider(name="dataSupplier")
	public String[][] dataSupplier() throws Exception
	{
		File file=new File("C:\\Users\\Admin\\eclipse-workspace\\MyHybridFramework\\TestData\\dataProvider.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		int noOfRows=sheet.getPhysicalNumberOfRows();
		int noOfCols=sheet.getRow(0).getLastCellNum();
		
		String data[][]=new String [noOfRows-1][noOfCols];
		
		for(int i=0;i<noOfRows-1;i++)
		{
			for(int j=0;j<noOfCols;j++)
			{
				DataFormatter df=new DataFormatter();
				data[i][j]=df.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}
		fis.close();
		workbook.close();
		
		return data;
	}
	
}
