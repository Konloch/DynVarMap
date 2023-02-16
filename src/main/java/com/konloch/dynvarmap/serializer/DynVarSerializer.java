package com.konloch.dynvarmap.serializer;

import com.konloch.disklib.DiskReader;
import com.konloch.disklib.DiskWriter;
import com.konloch.disklib.GZipDiskReader;
import com.konloch.disklib.GZipDiskWriter;
import com.konloch.dynvarmap.DynVarField;
import com.konloch.dynvarmap.DynVarMap;
import com.konloch.dynvarmap.vars.*;
import com.konloch.util.FastStringUtils;

import java.util.ArrayList;
import java.io.File;

/**
 * Serializes a DynVarMap object with optional gzip compression / decompression.
 *
 * @author Konloch
 * @since 01/15/2021
 */
public class DynVarSerializer
{
	private final File file;
	private final boolean gzipMode;
	private final DynVarMap map;
	
	/**
	 * Constructs a new DynVarSerializer instance.
	 *
	 * @param file any String path to read and write to
	 * @param map any DynVarMap object
	 */
	public DynVarSerializer(String file, DynVarMap map) {
		this(file, map, false);
	}
	
	/**
	 * Constructs a new DynVarSerializer instance.
	 *
	 * @param file any String path to read and write to
	 * @param map any DynVarMap object
	 * @param gzipMode if true it will compress / decompress the data with GZip during reading and writing
	 */
	public DynVarSerializer(String file, DynVarMap map, boolean gzipMode) {
		this(new File(file), map, gzipMode);
	}
	
	/**
	 * Constructs a new DynVarSerializer instance.
	 *
	 * @param file any File object to read and write to
	 * @param map any DynVarMap object
	 */
	public DynVarSerializer(File file, DynVarMap map) {
		this(file, map, false);
	}
	
	/**
	 * Constructs a new DynVarSerializer instance.
	 *
	 * @param file any File object to read and write to
	 * @param map any DynVarMap object
	 * @param gzipMode if true it will compress / decompress the data with GZip during reading and writing
	 */
	public DynVarSerializer(File file, DynVarMap map, boolean gzipMode) {
		this.file = file;
		this.gzipMode = gzipMode;
		this.map = map;
	}
	
	/**
	 * Load the DynVarMap file from disk.
	 *
	 * @return true if it was successful
	 */
	public boolean load()
	{
		try
		{
			ArrayList<String> lines = gzipMode ? GZipDiskReader.read(file) : DiskReader.read(file);
			
			load(lines);
			return true;
		}
		catch (java.nio.file.NoSuchFileException e)
		{
			//ignore
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Parse the String ArrayList and store the variables.
	 *
	 * @param lines any String ArrayList containing a DynVarMap serialized data
	 */
	public void load(ArrayList<String> lines)
	{
		for (String line : lines)
		{
			try
			{
				if (line.startsWith("//") || line.startsWith("#") || !line.contains("="))
					continue;
				
				String[] split = FastStringUtils.split(line, "=", 2);
				
				if(split.length != 2)
					continue;
				
				String key = split[0];
				String value = split[1];
				
				if (value == null)
					continue;
				
				if (key == null || key.isEmpty() || value.isEmpty())
					continue;
				
				//define type by variable declaration
				if (key.startsWith("^") && FastStringUtils.isBoolean(value)) //boolean
					map.put(key.substring(1), Boolean.parseBoolean(value));
				else if (key.startsWith(">>") && FastStringUtils.isFloat(value)) //float
					map.put(key.substring(2), Float.parseFloat(value));
				else if (key.startsWith(">") && FastStringUtils.isDouble(value)) //double
					map.put(key.substring(1), Double.parseDouble(value));
				else if (key.startsWith("$$") && FastStringUtils.isLong(value)) //long
					map.put(key.substring(2), Long.parseLong(value));
				else if (key.startsWith("$") && FastStringUtils.isInteger(value)) //int
					map.put(key.substring(1), Integer.parseInt(value));
				else if (key.startsWith("&")) //String
					map.put(key.substring(1), value);
				else //support for hand-crafted configuration files
				{
					if (FastStringUtils.isBoolean(value))
						map.put(key, Boolean.parseBoolean(value));
					else if (FastStringUtils.isInteger(value))
						map.put(key, Integer.parseInt(value));
					else if (FastStringUtils.isDouble(value))
						map.put(key, Double.parseDouble(value));
					else if (FastStringUtils.isFloat(value))
						map.put(key, Float.parseFloat(value));
					else if (FastStringUtils.isShort(value))
						map.put(key, Short.parseShort(value));
					//else if (FastStringUtils.isNull(value))
					//	map.put(key, null);
					else
						map.put(key, value);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Serialize the DynVarMap fields to String format.
	 *
	 * @return the serialized DynVarMap fields
	 */
	public String saveToString()
	{
		StringBuilder sb = new StringBuilder();
		boolean sent = false;
		for (String key : map.keySet())
		{
			DynVarField field = map.get(key);
			String toWrite = "";
			
			if(sent)
				toWrite += "\n";
			else
				sent = true;
			
			//define type by variable declaration
			if(field instanceof DynVarBoolean)
				toWrite += "^" + key + "=" + field.get();
			else if(field instanceof DynVarFloat)
				toWrite += ">>" + key + "=" + field.get();
			else if(field instanceof DynVarDouble)
				toWrite += ">" + key + "=" + field.get();
			else if(field instanceof DynVarLong)
				toWrite += "$$" + key + "=" + field.get();
			else if(field instanceof DynVarInteger)
				toWrite += "$" + key + "=" + field.get();
			else if(field instanceof DynVarString)
				toWrite += "&" + key + "=" + field.get();
			else //just write and hope the deserializer can figure it out
				toWrite = key + "=" + field.get();
			
			sb.append(toWrite);
		}
		
		return sb.toString();
	}
	
	/**
	 * Save the DynVarMap instance to disk.
	 *
	 * @return true if successful
	 */
	public boolean save()
	{
		try
		{
			String s = saveToString();
			
			if(gzipMode)
				GZipDiskWriter.write(file, s);
			else
				DiskWriter.write(file, s);
			
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Returns the map instance
	 *
	 * @return the DynVarMap instance
	 */
	public DynVarMap getMap()
	{
		return map;
	}
	
	/**
	 * Returns the File
	 *
	 * @return the File
	 */
	public File getFile()
	{
		return file;
	}
	
	/**
	 * Returns if it's in GZip mode
	 *
	 * @return true if in Gzip mode
	 */
	public boolean isGzipMode()
	{
		return gzipMode;
	}
}