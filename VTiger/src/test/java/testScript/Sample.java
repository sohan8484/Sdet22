package testScript;

import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;

public class Sample {
	public static void main(String[] args) throws Throwable {
		ExcelUtility eLib=new ExcelUtility();
		 eLib.writeExcelData("test", 1, 1, "driver");
	}
}
