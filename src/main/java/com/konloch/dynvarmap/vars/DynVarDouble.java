package com.konloch.dynvarmap.vars;

import com.konloch.dynvarmap.DynVarField;

/**
 * @author Konloch
 * @since 08/25/2020
 */
public class DynVarDouble extends DynVarField
{
    /**
     * Set the value supplied.
     *
     * @param value any Object as the variable value
     * @return this instance for method chaining
     */
    @Override
    public DynVarDouble set(Object value)
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
    public Double get()
    {
        Object o = super.get();
    
        //failsafe and recast the type to avoid cast exceptions
        if(o instanceof Integer)
            o = (double)((int) o);
        else if(o instanceof Byte)
            o = (double)((byte) o);
        else if(o instanceof Short)
            o = (double)((short) o);
        else if(o instanceof Long)
            o = (double)((int) o);
        else if(o instanceof Float)
            o = (double)((int) o);
    
        return (double) o;
    }
    
    /**
     * Get the variable value as a primitive
     *
     * @return the variable value as a primitive.
     */
    public double getDouble()
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
    public Double getThenSet(Object value)
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
    public Double getThenSetIfEquals(Object equals, Object value)
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
    public Double getThenSetIfNotEquals(Object equals, Object value)
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
    public DynVarDouble add(Object value)
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
    public DynVarDouble subtract(Object value)
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
    public DynVarDouble multiply(Object value)
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
    public DynVarDouble divide(Object value)
    {
        super.divide(value);
        return this;
    }
}