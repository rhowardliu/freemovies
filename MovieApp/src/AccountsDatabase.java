import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AccountsDatabase {

	private File excelData = new File("data.xlsx");
	private Workbook w;
	private int userIndex;
	private Sheet accountsData;
	
	public AccountsDatabase(String enteredID) throws IOException {
		
	try {
		w = Workbook.getWorkbook(excelData);
		accountsData = w.getSheet("Accounts");
		Cell usercell = accountsData.findCell(enteredID);
		userIndex = usercell.getRow();
	} catch (BiffException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		
	}
	
	public int getIndex() {
		return userIndex;
	}
	
	public String getStatus() {
		Cell statuscell = accountsData.getCell(userIndex, 1);
		return statuscell.getContents();
	}
	
	public String getPassword() {
		Cell statuscell = accountsData.getCell(userIndex, 2);
		return statuscell.getContents();
	}
	
		
}