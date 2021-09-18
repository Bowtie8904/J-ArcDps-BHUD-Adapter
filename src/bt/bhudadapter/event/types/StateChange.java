package bt.bhudadapter.event.types;

public enum StateChange
{
    /** not used - not this kind of event */
    NONE,
    /** src_agent entered combat, dst_agent is subgroup */
    ENTERCOMBAT,
    /** src_agent left combat */
    EXITCOMBAT,
    /** src_agent is now alive */
    CHANGEUP,
    /** src_agent is now dead */
    CHANGEDEAD,
    /** src_agent is now downed */
    CHANGEDOWN,
    /** src_agent is now in game tracking range */
    SPAWN,
    /** src_agent is no longer being tracked */
    DESPAWN,
    /** src_agent has reached a health marker. dst_agent = percent * 10000 (eg. 99.5% will be 9950) */
    HEALTHUPDATE,
    /** log start. value = server unix timestamp **uint32**. buff_dmg = local unix timestamp. src_agent = 0x637261 (arcdps id) */
    LOGSTART,
    /** log end. value = server unix timestamp **uint32**. buff_dmg = local unix timestamp. src_agent = 0x637261 (arcdps id) */
    LOGEND,
    /** src_agent swapped weapon set. dst_agent = current set id (0/1 water, 4/5 land) */
    WEAPSWAP,
    /** src_agent has had it's maximum health changed. dst_agent = new max health */
    MAXHEALTHUPDATE,
    /** src_agent will be agent of "recording" player */
    POINTOFVIEW,
    /** src_agent will be text language */
    LANGUAGE,
    /** src_agent will be game build */
    GWBUILD,
    /** src_agent will be sever shard id */
    SHARDID,
    /** src_agent is self, dst_agent is reward id, value is reward type. these are the wiggly boxes that you get */
    REWARD,
    /** combat event that will appear once per buff per agent on logging start (zero duration, buff==18) */
    BUFFINITIAL,
    /** src_agent changed, cast float* p = (float*)&dst_agent, access as x/y/z (float[3]) */
    POSITION,
    /** src_agent changed, cast float* v = (float*)&dst_agent, access as x/y/z (float[3]) */
    VELOCITY,
    /** src_agent changed, cast float* f = (float*)&dst_agent, access as x/y (float[2]) */
    FACING,
    /** src_agent change, dst_agent new team id */
    TEAMCHANGE,
    /** src_agent is an attacktarget, dst_agent is the parent agent (gadget type), value is the current targetable state */
    ATTACKTARGET,
    /** dst_agent is new target-able state (0 = no, 1 = yes. default yes) */
    TARGETABLE,
    /** src_agent is map id  (not in realtime api) */
    MAPID,
    /** internal use, won't see anywhere */
    REPLINFO,
    /** src_agent is agent with buff, dst_agent is the stackid marked active */
    STACKACTIVE,
    /** src_agent is agent with buff, value is the duration to reset to (also marks inactive), pad61- is the stackid */
    STACKRESET,
    /** src_agent is agent, dst_agent through buff_dmg is 16 byte guid (client form, needs minor rearrange for api form) */
    GUILD,
    /** is_flanking = probably invuln, is_shields = probably invert, is_offcycle = category, pad61 = stacking type, pad62 = probably resistance, src_master_instid = max stacks, overstack_value = duration cap (not in realtime) */
    BUFFINFO,
    /** (float*)&time[8]: type attr1 attr2 param1 param2 param3 trait_src trait_self, (float*)&src_instid[2] = buff_src buff_self, is_flanking = !npc, is_shields = !player, is_offcycle = break, overstack = value of type determined by pad61 (none/number/skill) (not in realtime, one per formula) */
    BUFFFORMULA,
    /** (float*)&time[4]: recharge range0 range1 tooltiptime (not in realtime) */
    SKILLINFO,
    /** src_agent = action, dst_agent = at millisecond (not in realtime, one per timing) */
    SKILLTIMING,
    /** src_agent is agent, value is u16 game enum (active, recover, immune, none) (not in realtime api) */
    BREAKBARSTATE,
    /** src_agent is agent, value is float with percent (not in realtime api) */
    BREAKBARPERCENT,
    /** (char*)&time[32]: error string (not in realtime api) */
    ERROR,
    /** src_agent is agent, value is the id (volatile, game build dependent) of the tag */
    TAG,
    /** src_agent is at barrier percent. dst_agent = percent * 10000 (eg. 99.5% will be 9950) (not in realtime api) */
    BARRIERUPDATE,
    /** with arc ui stats reset (not in log), src_agent = npc id of active log */
    STATRESET,
    /** cbtevent with statechange byte set to this */
    EXTENSION,
    /** cbtevent with statechange byte set to this */
    APIDELAYED,
    /** unknown or invalid, ignore */
    UNKNOWN;

    public static StateChange forValue(int value)
    {
        for (StateChange state : StateChange.values())
        {
            if (state.ordinal() == value)
            {
                return state;
            }
        }

        return UNKNOWN;
    }
}