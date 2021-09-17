package bt.bhudadapter.event.types;

public enum IFF
{
    // green vs green, red vs red
    FRIEND,
    // green vs red
    FOE,
    // something very wrong happened
    UNKNOWN;

    public static IFF forValue(int value)
    {
        for (IFF iff : IFF.values())
        {
            if (iff.ordinal() == value)
            {
                return iff;
            }
        }

        return UNKNOWN;
    }
}