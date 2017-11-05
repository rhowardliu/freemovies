import java.io.IOException;

import jxl.Cell;

import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class MovieGoerDatabase extends AccountsDatabase{

	public MovieGoerDatabase(String enteredID) throws IOException {
		super(enteredID);
		}
	
	public String getEmail() {
		Cell emailcell = accountsData.getCell(userIndex, 3);
		return emailcell.getContents();
	}
	
	public String getPhoneNumber() {
		Cell phonecell = accountsData.getCell(userIndex, 4);
		return phonecell.getContents();
	}
	
	public void writeReview(int userIndex, double rating, String review) {
		
	}
	

}
