package com.konloch.dynvarmap.vars;

/**
 * @author Konloch
 * @since 08/27/2020
 */
public class DynVarTime extends DynVarLong
{
    /**
     * Set the value supplied.
     *
     * @param value any Object as the variable value
     * @return this instance for method chaining
     */
    @Override
    public DynVarTime set(Object value)
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
    public Long get()
    {
        return super.get();
    }
    
    /**
     * Get the variable value as a primitive
     *
     * @return the variable value as a primitive.
     */
    public long getTime()
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
    public Long getThenSet(Object value)
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
    public Long getThenSetIfEquals(Object equals, Object value)
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
    public Long getThenSetIfNotEquals(Object equals, Object value)
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
    public DynVarTime add(Object value)
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
    public DynVarTime subtract(Object value)
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
    public DynVarTime multiply(Object value)
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
    public DynVarTime divide(Object value)
    {
        super.divide(value);
        return this;
    }
    
    /**
     * Set the value as the current time in milliseconds.
     */
    public void setNow()
    {
        set(getNow());
    }
    
    /**
     * How many milliseconds should be deducted from the current time
     *
     * @param offset milliseconds that it should be offset from
     */
    public void setNow(long offset)
    {
        set(getNow() - offset);
    }
    
    /**
     * Returns the current time in milliseconds.
     *
     * @return the time in milliseconds.
     */
    public long getNow()
    {
        return System.currentTimeMillis();
    }
    
    /**
     * Check if a specific time has passed yet.
     *
     * @param time any DynVarTime
     * @return true if the time has passed
     */
    public boolean hasPassed(DynVarTime time)
    {
        return hasPassed(time.getTime());
    }
    
    /**
     * Check if a specific time has passed yet and reset if it has.
     *
     * @param time any DynVarTime
     * @return true if the time has passed
     */
    public boolean hasPassedReset(DynVarTime time)
    {
        return hasPassedReset(time.getTime());
    }
    
    /**
     * Check if a specific time has passed yet.
     *
     * @param time any DynVarTime
     * @return true if the time has passed
     */
    public boolean hasPassed(long time)
    {
        return getNow() - get() > time;
    }
    
    /**
     * Check if a specific time has passed yet and reset if it has.
     *
     * @param time any DynVarTime
     * @return true if the time has passed
     */
    public boolean hasPassedReset(long time)
    {
        boolean passed = hasPassed(time);

        if(passed)
            setNow();

        return passed;
    }
}