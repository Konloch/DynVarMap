package com.konloch;

import com.konloch.dynvarmap.DynVarMap;

/**
 * @author Konloch
 * @since 2/15/2023
 */
public class DynVarExample
{
	public static void main(String[] args)
	{
		DynVarMap dynvar = new DynVarMap();
		
		//automatic casting when possible
		int i1 = dynvar.get("key.AKA.variable.name.goes.here").add(50).get();
  
		//full dynamic casting support
		int i2 = dynvar.get("key.AKA.variable.name.goes.here").subtract(100).get(Integer.class);
		
		//full primitive support
        int i3 = dynvar.getVarInt("key.AKA.variable.name.goes.here").set(1).multiply(2).getInt();
		
		//feature packed to make rapid application development FAST
        boolean b1 = dynvar.getVarLong("key.AKA.variable.name.goes.here").add(1).equals(10, 35, 72, 194, 603);
		
		//full reactive-style programming support
        boolean b2 = dynvar.getVarLong("key.AKA.variable.name.goes.here").add(1).setIfEquals(10, 0);
		
		//custom "type" support such as system time
        boolean b3 = dynvar.getVarTime("key.AKA.variable.name.goes.here").hasPassedReset(30_000);
	}
}
