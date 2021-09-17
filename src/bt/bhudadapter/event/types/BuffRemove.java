package bt.bhudadapter.event.types;

public enum BuffRemove
{
    // not used - not this kind of event
    NONE,
    // all stacks removed
    ALL,
    // single stack removed. disabled on server trigger, will happen for each stack on cleanse
    SINGLE,
    // autoremoved by ooc or allstack (ignore for strip/cleanse calc, use for in/out volume)
    MANUAL;

    public static BuffRemove forValue(int value)
    {
        for (BuffRemove rem : BuffRemove.values())
        {
            if (rem.ordinal() == value)
            {
                return rem;
            }
        }

        return NONE;
    }
}