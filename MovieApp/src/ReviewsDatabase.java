import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ReviewsDatabase {
	public static int noOfMovies;
	public File reviewData;
	protected WritableWorkbook wb;
	
	public ReviewsDatabase() throws IOException {
	reviewData = new File("reviews.xlsx");
	 wb = Workbook.createWorkbook(reviewData);
	 noOfMovies=wb.getNumberOfSheets();
	}
	
	public WritableSheet getReviewSheet (String moviename) {
		return wb.getSheet(moviename);
	}
	
	public void addMovieToBeReviewed(String moviename) {
		
		int sheetnumber = noOfMovies;
		wb.createSheet("moviename", sheetnumber);
		noOfMovies++;
	}
	

	
	public class MovieReviews{
		int noOfReviews;
		double avgrating;
		ArrayList<Double> rating[];
		ArrayList<String> review[];
		
		public MovieReviews() {
			
		}
	}
	
	
}
