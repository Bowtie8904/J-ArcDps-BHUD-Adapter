package bt.bhudadapter.event.types;

public enum StateChange
{
    // not used - not this kind of event
    NONE,
    // src_agent entered combat, dst_agent is subgroup
    ENTERCOMBAT,
    // src_agent left combat
    EXITCOMBAT,
    // src_agent is now alive
    CHANGEUP,
    // src_agent is now dead
    CHANGEDEAD,
    // src_agent is now downed
    CHANGEDOWN,
    // src_agent is now in game tracking range
    SPAWN,
    // src_agent is no longer being tracked
    DESPAWN,
    // src_agent has reached a health marker. dst_agent = percent * 10000 (eg. 99.5% will be 9950)
    HEALTHUPDATE,
    // log start. value = server unix timestamp **uint32**. buff_dmg = local unix timestamp. src_agent = 0x637261 (arcdps id)
    LOGSTART,
    // log end. value = server unix timestamp **uint32**. buff_dmg = local unix timestamp. src_agent = 0x637261 (arcdps id)
    LOGEND,
    // src_agent swapped weapon set. dst_agent = current set id (0/1 water, 4/5 land)
    WEAPSWAP,
    // src_agent has had it's maximum health changed. dst_agent = new max health
    MAXHEALTHUPDATE,
    // src_agent will be agent of "recording" player
    POINTOFVIEW,
    // src_agent will be text language
    LANGUAGE,
    // src_agent will be game build
    GWBUILD,
    // src_agent will be sever shard id
    SHARDID,
    // src_agent is self, dst_agent is reward id, value is reward type. these are the wiggly boxes that you get
    REWARD,
    // combat event that will appear once per buff per agent on logging start (zero duration, buff==18)
    BUFFINITIAL,
    // src_agent changed, cast float* p = (float*)&dst_agent, access as x/y/z (float[3])
    POSITION,
    // src_agent changed, cast float* v = (float*)&dst_agent, access as x/y/z (float[3])
    VELOCITY,
    // src_agent changed, cast float* f = (float*)&dst_agent, access as x/y (float[2])
    FACING,
    // src_agent change, dst_agent new team id
    TEAMCHANGE,
    // src_agent is an attacktarget, dst_agent is the parent agent (gadget type), value is the current targetable state
    ATTACKTARGET,
    // dst_agent is new target-able state (0 = no, 1 = yes. default yes)
    TARGETABLE;

    public static StateChange forValue(int value)
    {
        for (StateChange state : StateChange.values())
        {
            if (state.ordinal() == value)
            {
                return state;
            }
        }

        return NONE;
    }
}