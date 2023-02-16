package com.konloch.dynvarmap.vars;

import com.konloch.dynvarmap.DynVarField;

/**
 * @author Konloch
 * @since 08/25/2020
 */
public class DynVarFloat extends DynVarField
{
    /**
     * Set the value supplied.
     *
     * @param value any Object as the variable value
     * @return this instance for method chaining
     */
    @Override
    public DynVarFloat set(Object value)
    {
        super.set(value);
        return this;
    }
    
    /**
     * Get the variable value as an Object
     *
     * @return the variable value as an Object.
     */
    @Override
    public Float get()
    {
        Object o = super.get();
    
        //failsafe and recast the type to avoid cast exceptions
        if(o instanceof Integer)
            o = (float)((int) o);
        else if(o instanceof Byte)
            o = (float)((byte) o);
        else if(o instanceof Short)
            o = (float)((short) o);
        else if(o instanceof Long)
            o = (float)((long) o);
        else if(o instanceof Double)
            o = (float)((double) o);
        
        return (Float) o;
    }
    
    /**
     * Get the variable value as a primitive
     *
     * @return the variable value as a primitive.
     */
    public float getFloat()
    {
        return get();
    }
    
    /**
     * Grab the current variable value, set the value supplied, then return the grabbed value.
     *
     * @param value any Object to set as the variable value
     * @return the variable value before anything has been changed
     */
    @Override
    public Float getThenSet(Object value)
    {
        return super.getThenSet(value);
    }
    
    /**
     * Grab the current variable value, set the value supplied if it does equal the supplied equals value.
     *
     * @param equals any Object that it should compare against
     * @param value any Object to set as the variable value
     * @return the variable value before anything has been changed
     */
    @Override
    public Float getThenSetIfEquals(Object equals, Object value)
    {
        return super.getThenSetIfEquals(equals, value);
    }
    
    /**
     * Grab the current variable value, set the value supplied if it does not equal the supplied equals value.
     *
     * @param equals any Object that it should compare against
     * @param value any Object to set as the variable value
     * @return the variable value before anything has been changed
     */
    @Override
    public Float getThenSetIfNotEquals(Object equals, Object value)
    {
        return super.getThenSetIfNotEquals(equals, value);
    }
    
    /**
     * Preforms an addition.
     *
     * @param value the value to add with
     * @return this instance for method chaining
     */
    @Override
    public DynVarFloat add(Object value)
    {
        super.add(value);
        return this;
    }
    
    /**
     * Preforms a mathematics subtraction.
     *
     * @param value the value to subtract with
     * @return this instance for method chaining
     */
    @Override
    public DynVarFloat subtract(Object value)
    {
        super.subtract(value);
        return this;
    }
    
    /**
     * Preforms a mathematics multiplication.
     *
     * @param value the value to multiply against
     * @return this instance for method chaining
     */
    @Override
    public DynVarFloat multiply(Object value)
    {
        super.multiply(value);
        return this;
    }
    
    /**
     * Preforms a mathematics division.
     *
     * @param value the value to divide against
     * @return this instance for method chaining
     */
    @Override
    public DynVarFloat divide(Object value)
    {
        super.divide(value);
        return this;
    }
}