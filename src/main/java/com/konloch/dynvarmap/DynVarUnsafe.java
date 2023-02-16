package com.konloch.dynvarmap;

import java.util.Map;

/**
 * Not recommended for use, but it's here for those who need it.
 *
 * @author Konloch
 * @since 01/16/2021
 */
public class DynVarUnsafe
{
	/**
	 * Used to access the DynVarMap getFields function.
	 *
	 * @param map any DynVarMap
	 * @return the field map
	 */
	public static Map<String, DynVarField> getFields(DynVarMap map)
	{
		return map.getFields();
	}
	
	/**
	 * Used to access the DynVarMap putDirect function.
	 *
	 * @param map any DynVarMap
	 * @param key any String as the variable name
	 * @param value any Object as the variable value
	 * @param <T> the variable value Type
	 * @return the previous value associated with key, or null if there was no mapping for key
	 */
	public static <T> T putDirect(DynVarMap map, String key, Object value)
	{
		return map.putDirect(key, value);
	}
	
	/**
	 * Used to access the DynVarMap getDirect function.
	 *
	 * @param map any DynVarMap
	 * @param key any String as the variable name
	 * @return the DynVarField instance if the key exists, or it will return null
	 */
	public static DynVarField getDirect(DynVarMap map, String key)
	{
		return map.getDirect(key);
	}
}