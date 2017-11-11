import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjectReader {
	private File file;
	FileInputStream fis;
	ObjectInputStream ois;
	public ObjectReader(File file) throws FileNotFoundException, IOException{
		this.file=file;
		fis = new FileInputStream(file);
		try {
		ois = new ObjectInputStream(fis);
		}catch(EOFException e) {
		}
	}
		
		
		
	public <T> List<T> initialiseDataList(List<T> list) throws EOFException, ClassNotFoundException, IOException{
		
		
		try{
			while (true) {
				try {
			list.add((T)ois.readObject());
				}catch(EOFException e) {
					break;
				}
			}
		}catch(NullPointerException e) {
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if (ois!=null)
				ois.close();
		}
		
		return list;
	}
}