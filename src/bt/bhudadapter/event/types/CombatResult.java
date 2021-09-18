package bt.bhudadapter.event.types;

public enum CombatResult
{
    /** good physical hit */
    NORMAL,
    /** physical hit was crit */
    CRIT,
    /** physical hit was glance */
    GLANCE,
    /** physical hit was blocked eg. mesmer shield 4 */
    BLOCK,
    /** physical hit was evaded, eg. dodge or mesmer sword 2 */
    EVADE,
    /** physical hit interrupted something */
    INTERRUPT,
    /** physical hit was "invlun" or absorbed eg. guardian elite */
    ABSORB,
    /** physical hit missed */
    BLIND,
    /** physical hit was killing hit */
    KILL,
    /** not a damage strike, target was downed by skill by */
    DOWN,
    /** not a damage strike, target had value of breakbar damage dealt */
    BREAKBAR,
    /** not a damage strike, on-activation event (src hit dst if damaging buff) */
    ACTIVATION,
    /** unknown or invalid, ignore */
    UNKNOWN;

    public static CombatResult forValue(int value)
    {
        for (CombatResult res : CombatResult.values())
        {
            if (res.ordinal() == value)
            {
                return res;
            }
        }

        return UNKNOWN;
    }
}