import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

public class ObjectWriter {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	
	public ObjectWriter (File file) throws FileNotFoundException, IOException {
		this.file=file;

		fos = new FileOutputStream(file);
		oos = new ObjectOutputStream (fos);
		
	}


	public FileOutputStream getFos() {
		return fos;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}


	public void writeTheObject(Object object) throws IOException {
		oos.writeObject(object);
	}
	

	
	public <T> void updateDataList (List<T> list) throws IOException {
		
		for (T x : list) {
			oos.writeObject(x);

		}
		fos.close();
		oos.close();
	}
	
}
