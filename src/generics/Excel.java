package generics;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {

	public static String getCellValue(String xlpath,String sheet,int row,int cell)
	{
		String cv="";
		try{
		FileInputStream fis=new FileInputStream(xlpath);
		Workbook wb=WorkbookFactory.create(fis);
		cv=wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cv;
	}
	
	public static int getRowCount(String xlpath,String sheet)
	{
		int rc=0;
		try{
			FileInputStream fis=new FileInputStream(xlpath);
			Workbook wb=WorkbookFactory.create(fis);
			rc=wb.getSheet(sheet).getLastRowNum();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rc;
	}
}
