package com.konloch;

import com.konloch.disklib.DiskReader;
import com.konloch.dynvarmap.DynVarMap;
import com.konloch.dynvarmap.DynVarField;
import com.konloch.dynvarmap.serializer.DynVarSerializer;

public class DynVARTest
{
	public static void main(String[] args)
	{
		DynVarMap map = new DynVarMap();
		DynVarSerializer serializer = new DynVarSerializer("./temp.ini", map, false);
		if(!serializer.load())
		{
			//map.put("weed", "blunted studios");
			map.getVarTime("var1").setNow();
			map.getVarLong("var2").set(-1);
			map.getVarDouble("doubletest").set(3);
			
			//save map
			serializer.save();
			//clear map
			map.clear();
			//load to map
			serializer.load();
		}
		
		DynVarField field = map.get("keyGoesHere");
		field.set("Java API").add(" ").add("Limitations Suck");
		System.out.println(field.get(String.class));
		
		//Integer i0 = map.get("key.goes.here2", "1").get(); //tight-type control
		int i1 = map.get("key.goes.here").add(50).get(); //< automatic casting when possible
		int i2 = map.get("key.goes.here").subtract(100).get(Integer.class); //< full dynamic casting support
		int i3 = map.getVarInt("key.goes.here").set(1).multiply(2).getInt(); //< full primitive support
		map.getVarInt("key.goes.here").set(0); //reset everything since it serializes now!! :D
		
        boolean b1 = map.getVarLong("key.AKA.variable.name.goes.here").add(1).equals(10, 35, 72, 194, 603); //< feature packed to make rapid application development FAST
        boolean b2 = map.getVarLong("key.AKA.variable.name.goes.here").set(0).add(1).add(9).setIfEquals(10, 99); //< reactive-style method chaining
        boolean b3 = map.getVarTime("key.AKA.variable.name.goes.here").hasPassedReset(30_000); //< custom "type" support such as system time
		
		System.out.println("MATH RESULTS: " + i1 +", " + i2 +", " + i3);
		
		map.getVarString("testStrings", "value");
		
		System.out.println(map.getVarTime("var1").getTime());
		System.out.println(map.getVarLong("var2").get());
		System.out.println(map.getVarDouble("doubletest").getDouble());
		
		System.out.println(map.getVarInt("saveTest").add(1).get());
		System.out.println("TEST-A: "+map.getVarInt("testA", 10).add(1).get());
		System.out.println(""+map.get("test").get());
		System.out.println(""+map.get("testB").get());
		System.out.println(map.getVarInt("testC", 10).add(1).get());
		serializer.save();
		
		System.out.println();
		System.out.println("Serialized the fields as:");
		try
		{
			for(String line : DiskReader.read(serializer.getFile()))
				System.out.println(line);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			serializer.getFile().delete();
		}
	}
}
