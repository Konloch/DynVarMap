package com.konloch.dynvarmap;

import java.util.Objects;

/**
 * A VarMap field contains an object value.
 *
 * @author Konloch
 * @since 08/25/2020
 */
public class DynVarField
{
    protected Object value;
    
    /**
     * Constructs a new DynVarField with a supplied variable value.
     */
    public DynVarField()
    {
        this(null);
    }
    
    /**
     * Constructs a new DynVarField with a supplied variable value.
     *
     * @param value any Object as the variable value
     */
    public DynVarField(Object value)
    {
        this.value = value;
    }
    
    /**
     * Set the value supplied and return the current variable.
     *
     * @param value any Object as the variable value
     * @return this instance for method chaining
     */
    public DynVarField set(Object value)
    {
        this.value = value;
        return this;
    }
    
    /**
     * Set the value supplied and return the current variable value
     *
     * @param value any Object as the variable value
     * @param <T> the variable value Type
     * @return the variable value before anything has been changed
     */
    public <T> T setThenGet(Object value)
    {
        set(value);
        
        return get();
    }
    
    /**
     * Set the value supplied if it does not equal the supplied equals value
     *
     * @param equals any Object that it should compare against
     * @param value
     * @param <T> the variable value Type
     * @return the variable value before anything has been changed
     */
    public <T> T setThenGetIfEquals(Object equals, Object value)
    {
        T originalValue = get();
        
        if(originalValue != null && originalValue.equals(equals))
            set(value);
        
        return get();
    }
    
    /**
     * Set the value supplied if it does not equal the supplied equals value
     *
     * @param equals any Object that it should compare against
     * @param <T> the variable value Type
     * @return the variable value before anything has been changed
     */
    public <T> T setThenGetIfNotEquals(Object equals, Object value)
    {
        T originalValue = get();
        
        if(originalValue == null || !originalValue.equals(equals))
            set(value);
    
        return get();
    }
    
    
    /**
     * Get the variable value.
     *
     * @param <T> the variable value Type
     * @return the variable value cast to the supplied class.
     */
    public <T> T get()
    {
        return (T) value;
    }
    
    /**
     * Get the variable value and cast to a specific class.
     *
     * @param c any Class to cast as
     * @param <T> the variable value Type
     * @return the variable value cast to the supplied class.
     */
    public <T> T get(Class<T> c)
    {
        return (T) value;
    }
    
    /**
     * Grab the current variable value, set the value supplied, then return the grabbed value.
     *
     * @param <T> the variable value Type
     * @return the variable value before anything has been changed
     */
    public <T> T getThenSet(Object value)
    {
        T originalValue = get();

        set(value);

        return originalValue;
    }
    
    /**
     * Grab the current variable value, set the value supplied if it does equal the supplied equals value.
     *
     * @param equals any Object that it should compare against
     * @param <T> the variable value Type
     * @return the variable value before anything has been changed
     */
    public <T> T getThenSetIfEquals(Object equals, Object value)
    {
        T originalValue = get();

        if(originalValue != null && originalValue.equals(equals))
            set(value);

        return originalValue;
    }
    
    /**
     * Grab the current variable value, set the value supplied if it does not equal the supplied equals value.
     *
     * @param equals any Object that it should compare against
     * @param <T> the variable value Type
     * @return the variable value before anything has been changed
     */
    public <T> T getThenSetIfNotEquals(Object equals, Object value)
    {
        T originalValue = get();

        if(originalValue == null || !originalValue.equals(equals))
            set(value);

        return originalValue;
    }
    
    /**
     * Returns true if the supplied Object does equal the variable value.
     *
     * @param equals any Object that it should compare against
     * @param <T> the variable value Type
     * @return results of the comparison
     */
    public <T> boolean ifEquals(Object equals)
    {
        T originalValue = get();

        return originalValue != null && originalValue.equals(equals);
    }
    
    /**
     * Returns true if the supplied Object does not equal the variable value.
     *
     * @param equals any Object that it should compare against
     * @param <T> the variable value Type
     * @return results of the comparison
     */
    public <T> boolean ifNotEquals(Object equals)
    {
        T originalValue = get();

        return originalValue == null || !originalValue.equals(equals);
    }
    
    /**
     * Set the variable value if the current value does equal the supplied value.
     *
     * @param equals any Object that it should compare against
     * @param value any Object that it should set as
     * @param <T> the variable value Type
     * @return results of the comparison
     */
    public <T> boolean setIfEquals(Object equals, Object value)
    {
        T originalValue = get();

        if(originalValue != null && originalValue.equals(equals))
        {
            set(value);
            return true;
        }

        return false;
    }
    
    /**
     * Set the variable value if the current value does not equal the supplied value.
     *
     * @param equals any Object that it should compare against
     * @param value any Object that it should set as
     * @param <T> the variable value Type
     * @return results of the comparison
     */
    public <T> boolean setIfNotEquals(Object equals, Object value)
    {
        T originalValue = get();

        if(originalValue == null || !originalValue.equals(equals))
        {
            set(value);
            return true;
        }

        return false;
    }
    
    /**
     * Preforms an addition.
     *
     * @param value the value to add with
     * @return this instance for method chaining
     */
    public DynVarField add(Object value)
    {
        //handle null case
        if(this.value == null)
        {
            this.value = value;
        }
        
        //concat on strings
        else if(this.value instanceof String)
        {
            this.value = ((String) this.value + value);
        }
        
        //add on integer
        else if(this.value instanceof Integer)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((int) this.value + (long) value);
            else if(value instanceof Integer)
                this.value = ((int) this.value + (int) value);
            else if(value instanceof Double)
                this.value = ((int) this.value + (double) value);
            else if(value instanceof Float)
                this.value = ((int) this.value + (float) value);
            else if(value instanceof Byte)
                this.value = ((int) this.value + (byte) value);
            else if(value instanceof Short)
                this.value = ((int) this.value + (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((int) this.value + (int) value);
        }
        
        //add on long
        else if(this.value instanceof Long)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((long) this.value + (long) value);
            else if(value instanceof Integer)
                this.value = ((long) this.value + (int) value);
            else if(value instanceof Double)
                this.value = ((long) this.value + (double) value);
            else if(value instanceof Float)
                this.value = ((long) this.value + (float) value);
            else if(value instanceof Byte)
                this.value = ((long) this.value + (byte) value);
            else if(value instanceof Short)
                this.value = ((long) this.value + (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((long) this.value + (long) value);
        }
        
        //add on double
        else if(this.value instanceof Double)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((double) this.value + (long) value);
            else if(value instanceof Integer)
                this.value = ((double) this.value + (int) value);
            else if(value instanceof Double)
                this.value = ((double) this.value + (double) value);
            else if(value instanceof Float)
                this.value = ((double) this.value + (float) value);
            else if(value instanceof Byte)
                this.value = ((double) this.value + (byte) value);
            else if(value instanceof Short)
                this.value = ((double) this.value + (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((double) this.value + (double) value);
        }
        
        //add on float
        else if(this.value instanceof Float)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((float) this.value + (long) value);
            else if(value instanceof Integer)
                this.value = ((float) this.value + (int) value);
            else if(value instanceof Double)
                this.value = ((float) this.value + (double) value);
            else if(value instanceof Float)
                this.value = ((float) this.value + (float) value);
            else if(value instanceof Byte)
                this.value = ((float) this.value + (byte) value);
            else if(value instanceof Short)
                this.value = ((float) this.value + (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((float) this.value + (float) value);
        }
        
        //add on byte
        else if(this.value instanceof Byte)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((byte) this.value + (long) value);
            else if(value instanceof Integer)
                this.value = ((byte) this.value + (int) value);
            else if(value instanceof Double)
                this.value = ((byte) this.value + (double) value);
            else if(value instanceof Float)
                this.value = ((byte) this.value + (float) value);
            else if(value instanceof Byte)
                this.value = ((byte) this.value + (byte) value);
            else if(value instanceof Short)
                this.value = ((byte) this.value + (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((byte) this.value + (byte) value);
        }
        
        //add on short
        else if(this.value instanceof Short)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((short) this.value + (long) value);
            else if(value instanceof Integer)
                this.value = ((short) this.value + (int) value);
            else if(value instanceof Double)
                this.value = ((short) this.value + (double) value);
            else if(value instanceof Float)
                this.value = ((short) this.value + (float) value);
            else if(value instanceof Byte)
                this.value = ((short) this.value + (byte) value);
            else if(value instanceof Short)
                this.value = ((short) this.value + (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((short) this.value + (short) value);
        }
        return this;
    }
    
    /**
     * Preforms a mathematics subtraction.
     *
     * @param value the value to subtract with
     * @return this instance for method chaining
     */
    public DynVarField subtract(Object value)
    {
        //subtract on integer
        if(this.value instanceof Integer)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((int) this.value - (long) value);
            else if(value instanceof Integer)
                this.value = ((int) this.value - (int) value);
            else if(value instanceof Double)
                this.value = ((int) this.value - (double) value);
            else if(value instanceof Float)
                this.value = ((int) this.value - (float) value);
            else if(value instanceof Byte)
                this.value = ((int) this.value - (byte) value);
            else if(value instanceof Short)
                this.value = ((int) this.value - (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((int) this.value - (int) value);
        }
        
        //subtract on long
        else if(this.value instanceof Long)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((long) this.value - (long) value);
            else if(value instanceof Integer)
                this.value = ((long) this.value - (int) value);
            else if(value instanceof Double)
                this.value = ((long) this.value - (double) value);
            else if(value instanceof Float)
                this.value = ((long) this.value - (float) value);
            else if(value instanceof Byte)
                this.value = ((long) this.value - (byte) value);
            else if(value instanceof Short)
                this.value = ((long) this.value - (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((long) this.value - (long) value);
        }
        
        //subtract on double
        else if(this.value instanceof Double)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((double) this.value - (long) value);
            else if(value instanceof Integer)
                this.value = ((double) this.value - (int) value);
            else if(value instanceof Double)
                this.value = ((double) this.value - (double) value);
            else if(value instanceof Float)
                this.value = ((double) this.value - (float) value);
            else if(value instanceof Byte)
                this.value = ((double) this.value - (byte) value);
            else if(value instanceof Short)
                this.value = ((double) this.value - (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((double) this.value - (double) value);
        }
        
        //subtract on float
        else if(this.value instanceof Float)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((float) this.value - (long) value);
            else if(value instanceof Integer)
                this.value = ((float) this.value - (int) value);
            else if(value instanceof Double)
                this.value = ((float) this.value - (double) value);
            else if(value instanceof Float)
                this.value = ((float) this.value - (float) value);
            else if(value instanceof Byte)
                this.value = ((float) this.value - (byte) value);
            else if(value instanceof Short)
                this.value = ((float) this.value - (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((float) this.value - (float) value);
        }
        
        //subtract on byte
        else if(this.value instanceof Byte)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((byte) this.value - (long) value);
            else if(value instanceof Integer)
                this.value = ((byte) this.value - (int) value);
            else if(value instanceof Double)
                this.value = ((byte) this.value - (double) value);
            else if(value instanceof Float)
                this.value = ((byte) this.value - (float) value);
            else if(value instanceof Byte)
                this.value = ((byte) this.value - (byte) value);
            else if(value instanceof Short)
                this.value = ((byte) this.value - (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((byte) this.value - (byte) value);
        }
        
        //subtract on short
        else if(this.value instanceof Short)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((short) this.value - (long) value);
            else if(value instanceof Integer)
                this.value = ((short) this.value - (int) value);
            else if(value instanceof Double)
                this.value = ((short) this.value - (double) value);
            else if(value instanceof Float)
                this.value = ((short) this.value - (float) value);
            else if(value instanceof Byte)
                this.value = ((short) this.value - (byte) value);
            else if(value instanceof Short)
                this.value = ((short) this.value - (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((short) this.value - (short) value);
        }
        
        return this;
    }
    
    /**
     * Preforms a mathematics multiplication.
     *
     * @param value the value to multiply against
     * @return this instance for method chaining
     */
    public DynVarField multiply(Object value)
    {
        //multiply on integer
        if(this.value instanceof Integer)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((int) this.value * (long) value);
            else if(value instanceof Integer)
                this.value = ((int) this.value * (int) value);
            else if(value instanceof Double)
                this.value = ((int) this.value * (double) value);
            else if(value instanceof Float)
                this.value = ((int) this.value * (float) value);
            else if(value instanceof Byte)
                this.value = ((int) this.value * (byte) value);
            else if(value instanceof Short)
                this.value = ((int) this.value * (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((int) this.value * (int) value);
        }
        
        //multiply on long
        else if(this.value instanceof Long)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((long) this.value * (long) value);
            else if(value instanceof Integer)
                this.value = ((long) this.value * (int) value);
            else if(value instanceof Double)
                this.value = ((long) this.value * (double) value);
            else if(value instanceof Float)
                this.value = ((long) this.value * (float) value);
            else if(value instanceof Byte)
                this.value = ((long) this.value * (byte) value);
            else if(value instanceof Short)
                this.value = ((long) this.value * (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((long) this.value * (long) value);
        }
        
        //multiply on double
        else if(this.value instanceof Double)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((double) this.value * (long) value);
            else if(value instanceof Integer)
                this.value = ((double) this.value * (int) value);
            else if(value instanceof Double)
                this.value = ((double) this.value * (double) value);
            else if(value instanceof Float)
                this.value = ((double) this.value * (float) value);
            else if(value instanceof Byte)
                this.value = ((double) this.value * (byte) value);
            else if(value instanceof Short)
                this.value = ((double) this.value * (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((double) this.value * (double) value);
        }
        
        //multiply on float
        else if(this.value instanceof Float)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((float) this.value * (long) value);
            else if(value instanceof Integer)
                this.value = ((float) this.value * (int) value);
            else if(value instanceof Double)
                this.value = ((float) this.value * (double) value);
            else if(value instanceof Float)
                this.value = ((float) this.value * (float) value);
            else if(value instanceof Byte)
                this.value = ((float) this.value * (byte) value);
            else if(value instanceof Short)
                this.value = ((float) this.value * (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((float) this.value * (float) value);
        }
        
        //multiply on byte
        else if(this.value instanceof Byte)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((byte) this.value * (long) value);
            else if(value instanceof Integer)
                this.value = ((byte) this.value * (int) value);
            else if(value instanceof Double)
                this.value = ((byte) this.value * (double) value);
            else if(value instanceof Float)
                this.value = ((byte) this.value * (float) value);
            else if(value instanceof Byte)
                this.value = ((byte) this.value * (byte) value);
            else if(value instanceof Short)
                this.value = ((byte) this.value * (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((byte) this.value * (double) value);
        }
        
        //multiply on short
        else if(this.value instanceof Short)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((short) this.value * (long) value);
            else if(value instanceof Integer)
                this.value = ((short) this.value * (int) value);
            else if(value instanceof Double)
                this.value = ((short) this.value * (double) value);
            else if(value instanceof Float)
                this.value = ((short) this.value * (float) value);
            else if(value instanceof Byte)
                this.value = ((short) this.value * (byte) value);
            else if(value instanceof Short)
                this.value = ((short) this.value * (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((float) this.value * (float) value);
        }
        
        return this;
    }
    
    /**
     * Preforms a mathematics division.
     *
     * @param value the value to divide against
     * @return this instance for method chaining
     */
    public DynVarField divide(Object value)
    {
        //divide on integer
        if(this.value instanceof Integer)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((int) this.value / (long) value);
            else if(value instanceof Integer)
                this.value = ((int) this.value / (int) value);
            else if(value instanceof Double)
                this.value = ((int) this.value / (double) value);
            else if(value instanceof Float)
                this.value = ((int) this.value / (float) value);
            else if(value instanceof Byte)
                this.value = ((int) this.value / (byte) value);
            else if(value instanceof Short)
                this.value = ((int) this.value / (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((int) this.value / (int) value);
        }
        
        //divide on long
        else if(this.value instanceof Long)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((long) this.value / (long) value);
            else if(value instanceof Integer)
                this.value = ((long) this.value / (int) value);
            else if(value instanceof Double)
                this.value = ((long) this.value / (double) value);
            else if(value instanceof Float)
                this.value = ((long) this.value / (float) value);
            else if(value instanceof Byte)
                this.value = ((long) this.value / (byte) value);
            else if(value instanceof Short)
                this.value = ((long) this.value / (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((long) this.value / (long) value);
        }
        
        //divide on double
        else if(this.value instanceof Double)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((double) this.value / (long) value);
            else if(value instanceof Integer)
                this.value = ((double) this.value / (int) value);
            else if(value instanceof Double)
                this.value = ((double) this.value / (double) value);
            else if(value instanceof Float)
                this.value = ((double) this.value / (float) value);
            else if(value instanceof Byte)
                this.value = ((double) this.value / (byte) value);
            else if(value instanceof Short)
                this.value = ((double) this.value / (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((double) this.value / (double) value);
        }
        
        //divide on float
        else if(this.value instanceof Float)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((float) this.value / (long) value);
            else if(value instanceof Integer)
                this.value = ((float) this.value / (int) value);
            else if(value instanceof Double)
                this.value = ((float) this.value / (double) value);
            else if(value instanceof Float)
                this.value = ((float) this.value / (float) value);
            else if(value instanceof Byte)
                this.value = ((float) this.value / (byte) value);
            else if(value instanceof Short)
                this.value = ((float) this.value / (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((float) this.value / (float) value);
        }
        
        //divide on byte
        else if(this.value instanceof Byte)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((byte) this.value / (long) value);
            else if(value instanceof Integer)
                this.value = ((byte) this.value / (int) value);
            else if(value instanceof Double)
                this.value = ((byte) this.value / (double) value);
            else if(value instanceof Float)
                this.value = ((byte) this.value / (float) value);
            else if(value instanceof Byte)
                this.value = ((byte) this.value / (byte) value);
            else if(value instanceof Short)
                this.value = ((byte) this.value / (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((byte) this.value / (byte) value);
        }
        
        //divide on short
        else if(this.value instanceof Short)
        {
            //handle the edgecases of autoboxing
            if(value instanceof Long)
                this.value = ((short) this.value / (long) value);
            else if(value instanceof Integer)
                this.value = ((short) this.value / (int) value);
            else if(value instanceof Double)
                this.value = ((short) this.value / (double) value);
            else if(value instanceof Float)
                this.value = ((short) this.value / (float) value);
            else if(value instanceof Byte)
                this.value = ((short) this.value / (byte) value);
            else if(value instanceof Short)
                this.value = ((short) this.value / (short) value);
            else //SHOTGUN (this will most likely fail so when it does replace it with something that does not)
                this.value = ((short) this.value / (short) value);
        }
        
        return this;
    }
    
    /**
     * Preforms a bitwise xor operation.
     *
     * @param value the value to xor against
     * @return this instance for method chaining
     */
    public DynVarField bitwiseXor(Object value)
    {
        if(this.value instanceof Integer)
            this.value = ((int)this.value ^ (int)value);
        else if(this.value instanceof Long)
            this.value = ((long)this.value ^ (long)value);
        
        return this;
    }
    
    /**
     * Returns the variable value as a String.
     *
     * @return the variable value as a String
     */
    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
    
    /**
     * Generates a hashcode.
     *
     * @return the results of the generated hashcode
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }
    
    /**
     * Returns true if the arguments are equal to each other and false otherwise.
     *
     * @param o an object
     * @return true if the arguments are equal to each other and false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynVarField that = (DynVarField) o;
        return Objects.equals(value, that.value);
    }
    
    /**
     * Returns true if any of the arguments are equal to each other and false otherwise.
     *
     * @param o an array of objects
     * @return true if the arguments are equal to each other and false otherwise
     */
    public boolean equals(Object... o)
    {
        for(Object obj : o)
            if(equals(obj))
                return true;
        
        return false;
    }
}