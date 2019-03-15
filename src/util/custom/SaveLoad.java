package util.custom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface SaveLoad
{
	public static void save(String filename, Object obj) throws FileNotFoundException, IOException
	{
		FileOutputStream outStream;
		ObjectOutputStream objFile;
		outStream = new FileOutputStream(filename);
		objFile = new ObjectOutputStream(outStream);
		
		objFile.writeObject(obj);
		objFile.close();
	}
	
	public static <E> E load(String filename, E obj) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		FileInputStream inStream;
		ObjectInputStream objFile;
		inStream = new FileInputStream(filename);
		objFile = new ObjectInputStream(inStream);
		
		E savedObj = (E) objFile.readObject();
		objFile.close();
		return savedObj;
	}
}
