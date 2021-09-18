package bt.bhudadapter.event.types;

public enum Activation
{
    /** not used - not this kind of event */
    NONE,
    /** activation without quickness */
    NORMAL,
    /** activation with quickness */
    QUICKNESS,
    /** cancel with reaching channel time */
    CANCEL_FIRE,
    /** cancel without reaching channel time */
    CANCEL_CANCEL,
    /** animation completed fully */
    RESET,
    /** unknown or invalid, ignore */
    UNKNOWN;

    public static Activation forValue(int value)
    {
        for (Activation act : Activation.values())
        {
            if (act.ordinal() == value)
            {
                return act;
            }
        }

        return UNKNOWN;
    }
}