package com.konloch.dynvarmap;

import com.konloch.dynvarmap.vars.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * A Dynamic Variable System with null safety built by design. We've removed any form
 * of null happening to create the most laid-back programming API possible.
 *
 * @author Konloch
 * @since 08/25/2020
 */
public class DynVarMap
{
    private static final String defaultStringValue = "";
    private static final boolean defaultBooleanValue = false;
    private static final int defaultIntegerValue = 0;
    private static final long defaultLongValue = 0L;
    private static final byte defaultByteValue = 0;
    private static final short defaultShortValue = 0;
    private static final double defaultDoubleValue = 0D;
    private static final float defaultFloatValue = 0F;
    
    private final Map<String, DynVarField> fields;

    public DynVarMap()
    {
        fields = new LinkedHashMap<>();
    }
    
    /**
     * Returns the DynVarField associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value of null and
     * return the DynVaField instance for that variable.
     *
     * @param key any String as the variable name
     * @return the DynVarField instance that was removed
     */
    public DynVarField get(String key)
    {
        return get(key, null);
    }
    
    /**
     * Returns the DynVarField associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value of null and
     * return the DynVaField instance for that variable.
     *
     * @param key any String as the variable name
     * @param defaultValue any Object as the variable default value
     * @return the DynVarField instance that was removed
     */
    public DynVarField get(String key, Object defaultValue)
    {
        DynVarField field = getDirect(key);
        if(field == null)
        {
            DynVarField newField = new DynVarField();
            if(defaultValue != null)
                newField.set(defaultValue);
            
            putDirect(key, newField);
            return newField;
        }

        return field;
    }
    
    /**
     * Remove a variable from the map.
     *
     * @param key any String as the variable name
     * @return the DynVarField instance that was removed
     */
    public DynVarField remove(String key)
    {
        return fields.remove(key);
    }
    
    /**
     * Returns a specified type supported variable value.
     *
     * @param key any String as the variable name
     * @param <T> the variable value Type
     * @return the variable value
     */
    public <T> T getValue(String key)
    {
        DynVarField field = getDirect(key);
        if(field == null)
            return null;

        return field.get();
    }
    
    /**
     * Returns the DynVarInteger associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarInteger instance, if not it will create and insert a new instance and return that
     */
    public DynVarInteger getVarInt(String key)
    {
        return getVarInt(key, defaultIntegerValue);
    }
    
    /**
     * Returns the DynVarInteger associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any int as the variable default value
     * @return if the variable exists it will return the DynVarInteger instance, if not it will create and insert a new instance and return that
     */
    public DynVarInteger getVarInt(String key, int defaultValue)
    {
        DynVarField field = getDirect(key);

        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarInteger);
        if (isNull || !instanceOf)
        {
            DynVarInteger integer = new DynVarInteger();

            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                integer.set(field.get());
            else
                integer.set(defaultValue);

            fields.put(key, integer);
            return integer;
        }

        return (DynVarInteger) field;
    }
    
    /**
     * Returns an int variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public int getInt(String key)
    {
        return getInt(key, defaultIntegerValue);
    }
    
    /**
     * Returns an int variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any int as the variable default value
     * @return the variable value
     */
    public int getInt(String key, int defaultValue)
    {
        return getVarInt(key, defaultValue).getInt();
    }
    
    /**
     * Returns the DynVarLong associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarLong instance, if not it will create and insert a new instance and return that
     */
    public DynVarLong getVarLong(String key)
    {
        return getVarLong(key, defaultLongValue);
    }
    
    /**
     * Returns the DynVarLong associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any long as the variable default value
     * @return if the variable exists it will return the DynVarLong instance, if not it will create and insert a new instance and return that
     */
    public DynVarLong getVarLong(String key, long defaultValue)
    {
        DynVarField field = getDirect(key);

        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarLong);
        if (isNull || !instanceOf)
        {
            DynVarLong longInteger = new DynVarLong();

            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                longInteger.set(field.get());
            else
                longInteger.set(defaultValue);

            fields.put(key, longInteger);
            return longInteger;
        }

        return (DynVarLong) field;
    }
    
    /**
     * Returns a long variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public long getLong(String key)
    {
        return getLong(key, defaultLongValue);
    }
    
    /**
     * Returns a long variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any long as the variable default value
     * @return the variable value
     */
    public long getLong(String key, long defaultValue)
    {
        return getVarLong(key, defaultValue).get();
    }
    
    /**
     * Returns the DynVarInteger associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarInteger instance, if not it will create and insert a new instance and return that
     */
    public DynVarByte getVarByte(String key)
    {
        return getVarByte(key, defaultByteValue);
    }
    
    /**
     * Returns the DynVarByte associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any byte as the variable default value
     * @return if the variable exists it will return the DynVarByte instance, if not it will create and insert a new instance and return that
     */
    public DynVarByte getVarByte(String key, byte defaultValue)
    {
        DynVarField field = getDirect(key);
        
        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarByte);
        if (isNull || !instanceOf)
        {
            DynVarByte b = new DynVarByte();
            
            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                b.set(field.get());
            else
                b.set(defaultValue);
            
            fields.put(key, b);
            return b;
        }
        
        return (DynVarByte) field;
    }
    
    /**
     * Returns a byte variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public byte getByte(String key)
    {
        return getByte(key, defaultByteValue);
    }
    
    /**
     * Returns a byte variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any byte as the variable default value
     * @return the variable value
     */
    public byte getByte(String key, byte defaultValue)
    {
        return getVarByte(key, defaultValue).getByte();
    }
    
    /**
     * Returns the DynVarShort associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarShort instance, if not it will create and insert a new instance and return that
     */
    public DynVarShort getVarShort(String key)
    {
        return getVarShort(key, defaultShortValue);
    }
    
    /**
     * Returns the DynVarShort associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any byte as the variable default value
     * @return if the variable exists it will return the DynVarByte instance, if not it will create and insert a new instance and return that
     */
    public DynVarShort getVarShort(String key, short defaultValue)
    {
        DynVarField field = getDirect(key);
        
        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarShort);
        if (isNull || !instanceOf)
        {
            DynVarShort b = new DynVarShort();
            
            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                b.set(field.get());
            else
                b.set(defaultValue);
            
            fields.put(key, b);
            return b;
        }
        
        return (DynVarShort) field;
    }
    
    /**
     * Returns a short variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public short getShort(String key)
    {
        return getShort(key, defaultShortValue);
    }
    
    /**
     * Returns a short variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any short as the variable default value
     * @return the variable value
     */
    public short getShort(String key, short defaultValue)
    {
        return getVarShort(key, defaultValue).getShort();
    }
    
    /**
     * Returns the DynVarDouble associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarDouble instance, if not it will create and insert a new instance and return that
     */
    public DynVarDouble getVarDouble(String key)
    {
        return getVarDouble(key, defaultDoubleValue);
    }
    
    /**
     * Returns the DynVarDouble associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any double as the variable default value
     * @return if the variable exists it will return the DynVarDouble instance, if not it will create and insert a new instance and return that
     */
    public DynVarDouble getVarDouble(String key, double defaultValue)
    {
        DynVarField field = getDirect(key);
        
        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarDouble);
        if (isNull || !instanceOf)
        {
            DynVarDouble d = new DynVarDouble();
            
            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                d.set(field.get());
            else
                d.set(defaultValue);
            
            fields.put(key, d);
            return d;
        }
        
        return (DynVarDouble) field;
    }
    
    /**
     * Returns a double variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public double getDouble(String key)
    {
        return getDouble(key, defaultDoubleValue);
    }
    
    /**
     * Returns a double variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any double as the variable default value
     * @return the variable value
     */
    public double getDouble(String key, double defaultValue)
    {
        return getVarDouble(key, defaultValue).getDouble();
    }
    
    /**
     * Returns the DynVarFloat associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarFloat instance, if not it will create and insert a new instance and return that
     */
    public DynVarFloat getVarFloat(String key)
    {
        return getVarFloat(key, defaultFloatValue);
    }
    
    /**
     * Returns the DynVarFloat associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any float as the variable default value
     * @return if the variable exists it will return the DynVarFloat instance, if not it will create and insert a new instance and return that
     */
    public DynVarFloat getVarFloat(String key, float defaultValue)
    {
        DynVarField field = getDirect(key);
        
        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarFloat);
        if (isNull || !instanceOf)
        {
            DynVarFloat f = new DynVarFloat();
            
            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                f.set(field.get());
            else
                f.set(defaultValue);
            
            fields.put(key, f);
            return f;
        }
        
        return (DynVarFloat) field;
    }
    
    /**
     * Returns a float variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public float getFloat(String key)
    {
        return getFloat(key, defaultFloatValue);
    }
    
    /**
     * Returns a float variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any float as the variable default value
     * @return the variable value
     */
    public float getFloat(String key, float defaultValue)
    {
        return getVarFloat(key, defaultValue).getFloat();
    }
    
    /**
     * Returns the DynVarTime associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarTime instance, if not it will create and insert a new instance and return that
     */
    public DynVarTime getVarTime(String key)
    {
        return getVarTime(key, System.currentTimeMillis());
    }
    
    /**
     * Returns the DynVarTime associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any long as the variable default value
     * @return if the variable exists it will return the DynVarTime instance, if not it will create and insert a new instance and return that
     */
    public DynVarTime getVarTime(String key, long defaultValue)
    {
        DynVarField field = getDirect(key);

        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarTime);
        if (isNull || !instanceOf)
        {
            DynVarTime time = new DynVarTime();

            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                time.set(field.get());
            else
                time.set(defaultValue);

            fields.put(key, time);
            return time;
        }

        return (DynVarTime) field;
    }
    
    /**
     * Returns a Time variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public long getTime(String key)
    {
        return getTime(key, System.currentTimeMillis());
    }
    
    /**
     * Returns a Time variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any long as the variable default value
     * @return the variable value
     */
    public long getTime(String key, long defaultValue)
    {
        return getVarTime(key, defaultValue).getTime();
    }
    
    /**
     * Returns the DynVarBoolean associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarBoolean instance, if not it will create and insert a new instance and return that
     */
    public DynVarBoolean getVarBoolean(String key)
    {
        return getVarBoolean(key, defaultBooleanValue);
    }
    
    /**
     * Returns the DynVarBoolean associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any boolean as the variable default value
     * @return if the variable exists it will return the DynVarBoolean instance, if not it will create and insert a new instance and return that
     */
    public DynVarBoolean getVarBoolean(String key, boolean defaultValue)
    {
        DynVarField field = getDirect(key);

        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarBoolean);
        if (isNull || !instanceOf)
        {
            DynVarBoolean bool = new DynVarBoolean();

            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                bool.set(field.get());
            else
                bool.set(defaultValue);

            fields.put(key, bool);
            return bool;
        }

        return (DynVarBoolean) field;
    }
    
    /**
     * Returns a boolean variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public boolean getBoolean(String key)
    {
        return getBoolean(key, defaultBooleanValue);
    }
    
    /**
     * Returns a boolean variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any boolean as the variable default value
     * @return the variable value
     */
    public boolean getBoolean(String key, boolean defaultValue)
    {
        return getVarBoolean(key, defaultValue).getBoolean();
    }
    
    /**
     * Gets the current variable value from the supplied key,
     * flips the value, stores it, then returns the current value.
     *
     * @param key any String as the variable name
     * @return the variable value
     */
    public boolean flipBoolean(String key)
    {
        return flipBoolean(key, defaultBooleanValue);
    }
    
    /**
     * Gets the current variable value from the supplied key,
     * flips the value, stores it, then returns the current value.
     *
     * @param key any String as the variable name
     * @param defaultValue any boolean as the variable default value
     * @return the variable value
     */
    public boolean flipBoolean(String key, boolean defaultValue)
    {
        boolean current = getBoolean(key, defaultValue);
        put(key, !current);
        
        return getBoolean(key, defaultValue);
    }
    
    /**
     * Returns the DynVarString associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @return if the variable exists it will return the DynVarString instance, if not it will create and insert a new instance and return that
     */
    public DynVarString getVarString(String key)
    {
        return getVarString(key, defaultStringValue);
    }
    
    /**
     * Returns the DynVarString associated with the provided key, if the key doesn't exist
     * it will automatically create a new field using the default value and return that.
     *
     * @param key any String as the variable name
     * @param defaultValue any String as the variable default value
     * @return if the variable exists it will return the DynVarString instance, if not it will create and insert a new instance and return that
     */
    public DynVarString getVarString(String key, String defaultValue)
    {
        DynVarField field = getDirect(key);
        
        boolean isNull = field == null;
        boolean instanceOf = !isNull && (field instanceof DynVarString);
        if (isNull || !instanceOf)
        {
            DynVarString string = new DynVarString();
            
            //if instanceOf, set the current new value to be the same as the old value
            if(!isNull)
                string.set(field.get());
            else
                string.set(defaultValue);
            
            fields.put(key, string);
            return string;
        }
        
        return (DynVarString) field;
    }
    
    /**
     * Returns a String variable value from the map.
     *
     * @param key any String as the variable name
     * @return the variable value if it exists, or it will create the variable and return the default value
     */
    public String getString(String key)
    {
        return getString(key, defaultStringValue);
    }
    
    /**
     * Returns a String variable value from the map.
     *
     * @param key any String as the variable name
     * @param defaultValue any String as the variable default value
     * @return the variable value if it exists, or it will create the variable and return the default value
     */
    public String getString(String key, String defaultValue)
    {
        return getVarString(key, defaultValue).getString();
    }
    
    /**
     * Get directly from the map bypassing the null safety.
     * To access this function use DynVarUnsafe.
     *
     * @param key any String as the variable name
     * @return the DynVarField instance if the key exists, or it will return null
     */
    protected DynVarField getDirect(String key)
    {
        return fields.get(key);
    }
    
    /**
     * Store a field and variable.
     *
     * @param key any String as the variable name
     * @param value any Object as the variable value
     * @return the map instance for method chaining
     */
    public DynVarMap put(String key, Object value)
    {
        if(value instanceof Integer)
            getVarInt(key).set(value);
        else if(value instanceof Byte)
            getVarLong(key).set(value);
        else if(value instanceof Short)
            getVarLong(key).set(value);
        else if(value instanceof Double)
            getVarDouble(key).set(value);
        else if(value instanceof Float)
            getVarFloat(key).set(value);
        else if(value instanceof Boolean)
            getVarBoolean(key).set(value);
        else if(value instanceof String)
            getVarString(key).set(value);
        else
            get(key).set(value);
        
        return this;
    }
    
    /**
     * Store a field and variable.
     *
     * @param key any String as the variable name
     * @param value any Object as the variable value
     * @return the map instance for method chaining
     */
    public DynVarMap set(String key, Object value)
    {
        put(key, value);
        return this;
    }
    
    /**
     * Returns the object value you have just set, also adds directly bypassing the
     * automatic null check. To access this function use DynVarUnsafe.
     *
     * @param key any String as the variable name
     * @param value any Object as the variable value
     * @param <T> the variable value Type
     * @return the previous value associated with key, or null if there was no mapping for key
     */
    protected <T> T putDirect(String key, Object value)
    {
        return (T) fields.put(key, (DynVarField) value);
    }
    
    /**
     * Iterate through the fields inside the map
     * @param action any BiConsumer to process
     * @return the map instance for method chaining
     */
    public DynVarMap forEach(BiConsumer<String, Object> action)
    {
        fields.forEach(action);
        return this;
    }
    
    /**
     * Get the current count of variables in the map.
     *
     * @return the current count of variables in the map.
     */
    public int getSize()
    {
        return fields.size();
    }
    
    /**
     * Get the current count of variables in the map.
     *
     * @return the current count of variables in the map.
     */
    public int getLength()
    {
        return fields.size();
    }
    
    /**
     * Check if the map contains an existing variable key.
     *
     * @param key any String as the variable name
     * @return true if the map contains the the variable key
     */
    public boolean containsKey(String key)
    {
        return fields.containsKey(key);
    }
    
    /**
     * Returns true if the map is empty.
     *
     * @return true if the map is empty.
     */
    public boolean isEmpty()
    {
        return fields.isEmpty();
    }
    
    /**
     * Clear the map
     */
    public void clear()
    {
        fields.clear();
    }
    
    /**
     * Returns a String Set of the keys contained in the map.
     *
     * @return String Set of the keys contained in the map.
     */
    public Set<String> keySet()
    {
        return fields.keySet();
    }
    
    /**
     * Access the field map, to access this function use DynVarUnsafe.
     *
     * @return the field map
     */
    protected Map<String, DynVarField> getFields()
    {
        return fields;
    }
    
    /**
     * Alert that this is a library
     *
     * @param args program launch arguments
     */
    public static void main(String[] args)
    {
        throw new RuntimeException("Incorrect usage");
    }
}