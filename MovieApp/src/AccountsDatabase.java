import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

public class AccountsDatabase {

	protected File excelData = new File("accounts.xlsx");
	protected Workbook w;
	protected int userIndex;
	protected Sheet accountsData;
	
	public AccountsDatabase(String enteredID) throws IOException {
		
	try {

		w = Workbook.getWorkbook(excelData);
		accountsData = w.getSheet("Accounts");
		Cell usercell = accountsData.findCell(enteredID);
		userIndex = usercell.getRow();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		
	}
	
	public int getIndex() {
		return userIndex;
	}
	
	public String getStatus() {
		Cell statuscell = accountsData.getCell(1, userIndex);
		return statuscell.getContents();
	}
	
	public String getPassword() {
		Cell statuscell = accountsData.getCell(2, userIndex);
		return statuscell.getContents();
	}
	
		
}