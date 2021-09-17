package bt.bhudadapter.event.obj;

import bt.bhudadapter.event.types.*;

import java.util.Objects;

public class CombatEvent
{
    private long id;
    private long revision;
    private long time;
    private long srcAgent;
    private long dstAgent;
    private int value;
    private int buffDmg;
    private int overStackValue;
    private int skillId;
    private String skillName;
    private int srcInstId;
    private int dstInstId;
    private int srcMasterInstId;
    private int dstMasterInstId;
    private IFF iff;
    private int buff;
    private CombatResult result;
    private Activation activation;
    private BuffRemove buffRemove;
    private int isNinety;
    private int isFifty;
    private int isMoving;
    private StateChange statechange;
    private int isFlanking;
    private int isShields;
    private int isOffcycle;
    private int pad61;
    private int pad62;
    private int pad63;
    private int pad64;
    private Agent src;
    private Agent dst;

    public long getTime()
    {
        return time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public long getSrcAgent()
    {
        return srcAgent;
    }

    public void setSrcAgent(long srcAgent)
    {
        this.srcAgent = srcAgent;
    }

    public long getDstAgent()
    {
        return dstAgent;
    }

    public void setDstAgent(long dstAgent)
    {
        this.dstAgent = dstAgent;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getBuffDmg()
    {
        return buffDmg;
    }

    public void setBuffDmg(int buffDmg)
    {
        this.buffDmg = buffDmg;
    }

    public int getOverStackValue()
    {
        return overStackValue;
    }

    public void setOverStackValue(int overStackValue)
    {
        this.overStackValue = overStackValue;
    }

    public int getSkillId()
    {
        return skillId;
    }

    public void setSkillId(int skillId)
    {
        this.skillId = skillId;
    }

    public String getSkillName()
    {
        return skillName;
    }

    public void setSkillName(String skillName)
    {
        this.skillName = skillName;
    }

    public int getSrcInstId()
    {
        return srcInstId;
    }

    public void setSrcInstId(int srcInstId)
    {
        this.srcInstId = srcInstId;
    }

    public int getDstInstId()
    {
        return dstInstId;
    }

    public void setDstInstId(int dstInstId)
    {
        this.dstInstId = dstInstId;
    }

    public int getSrcMasterInstId()
    {
        return srcMasterInstId;
    }

    public void setSrcMasterInstId(int srcMasterInstId)
    {
        this.srcMasterInstId = srcMasterInstId;
    }

    public int getDstMasterInstId()
    {
        return dstMasterInstId;
    }

    public void setDstMasterInstId(int dstMasterInstId)
    {
        this.dstMasterInstId = dstMasterInstId;
    }

    public IFF getIff()
    {
        return iff;
    }

    public void setIff(IFF iff)
    {
        this.iff = iff;
    }

    public int getBuff()
    {
        return buff;
    }

    public void setBuff(int buff)
    {
        this.buff = buff;
    }

    public CombatResult getResult()
    {
        return result;
    }

    public void setResult(CombatResult result)
    {
        this.result = result;
    }

    public Activation getActivation()
    {
        return activation;
    }

    public void setActivation(Activation activation)
    {
        this.activation = activation;
    }

    public BuffRemove getBuffRemove()
    {
        return buffRemove;
    }

    public void setBuffRemove(BuffRemove isBuffRemove)
    {
        this.buffRemove = isBuffRemove;
    }

    public int getIsNinety()
    {
        return isNinety;
    }

    public void setIsNinety(int isNinety)
    {
        this.isNinety = isNinety;
    }

    public int getIsFifty()
    {
        return isFifty;
    }

    public void setIsFifty(int isFifty)
    {
        this.isFifty = isFifty;
    }

    public int getIsMoving()
    {
        return isMoving;
    }

    public void setIsMoving(int isMoving)
    {
        this.isMoving = isMoving;
    }

    public StateChange getStatechange()
    {
        return statechange;
    }

    public void setStatechange(StateChange statechange)
    {
        this.statechange = statechange;
    }

    public int getIsFlanking()
    {
        return isFlanking;
    }

    public void setIsFlanking(int isFlanking)
    {
        this.isFlanking = isFlanking;
    }

    public int getIsShields()
    {
        return isShields;
    }

    public void setIsShields(int isShields)
    {
        this.isShields = isShields;
    }

    public int getIsOffcycle()
    {
        return isOffcycle;
    }

    public void setIsOffcycle(int isOffcycle)
    {
        this.isOffcycle = isOffcycle;
    }

    public int getPad61()
    {
        return pad61;
    }

    public void setPad61(int pad61)
    {
        this.pad61 = pad61;
    }

    public int getPad62()
    {
        return pad62;
    }

    public void setPad62(int pad62)
    {
        this.pad62 = pad62;
    }

    public int getPad63()
    {
        return pad63;
    }

    public void setPad63(int pad63)
    {
        this.pad63 = pad63;
    }

    public int getPad64()
    {
        return pad64;
    }

    public void setPad64(int pad64)
    {
        this.pad64 = pad64;
    }

    public Agent getSrc()
    {
        return src;
    }

    public void setSrc(Agent src)
    {
        this.src = src;
    }

    public Agent getDst()
    {
        return dst;
    }

    public void setDst(Agent dst)
    {
        this.dst = dst;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getRevision()
    {
        return revision;
    }

    public void setRevision(long revision)
    {
        this.revision = revision;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CombatEvent that = (CombatEvent)o;
        return id == that.id && revision == that.revision && time == that.time && srcAgent == that.srcAgent && dstAgent == that.dstAgent && value == that.value && buffDmg == that.buffDmg && overStackValue == that.overStackValue && skillId == that.skillId && srcInstId == that.srcInstId && dstInstId == that.dstInstId && srcMasterInstId == that.srcMasterInstId && dstMasterInstId == that.dstMasterInstId && iff == that.iff && buff == that.buff && result == that.result && activation == that.activation && buffRemove == that.buffRemove && isNinety == that.isNinety && isFifty == that.isFifty && isMoving == that.isMoving && statechange == that.statechange && isFlanking == that.isFlanking && isShields == that.isShields && isOffcycle == that.isOffcycle && pad61 == that.pad61 && pad62 == that.pad62 && pad63 == that.pad63 && pad64 == that.pad64 && Objects
                .equals(skillName, that.skillName) && Objects.equals(src, that.src) && Objects.equals(dst, that.dst);
    }

    @Override
    public int hashCode()
    {
        return Objects
                .hash(id, revision, time, srcAgent, dstAgent, value, buffDmg, overStackValue, skillId, skillName, srcInstId, dstInstId, srcMasterInstId, dstMasterInstId, iff, buff, result,
                      activation,
                      buffRemove, isNinety, isFifty, isMoving, statechange, isFlanking, isShields, isOffcycle, pad61, pad62, pad63, pad64, src, dst);
    }

    @Override
    public String toString()
    {
        return "CombatEvent{" +
                "id=" + id +
                ", revision=" + revision +
                ", time=" + time +
                ", srcAgent=" + srcAgent +
                ", dstAgent=" + dstAgent +
                ", value=" + value +
                ", buffDmg=" + buffDmg +
                ", overStackValue=" + overStackValue +
                ", skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                ", srcInstId=" + srcInstId +
                ", dstInstId=" + dstInstId +
                ", srcMasterInstId=" + srcMasterInstId +
                ", dstMasterInstId=" + dstMasterInstId +
                ", iff=" + iff +
                ", buff=" + buff +
                ", result=" + result +
                ", isActivation=" + activation +
                ", isBuffRemove=" + buffRemove +
                ", isNinety=" + isNinety +
                ", isFifty=" + isFifty +
                ", isMoving=" + isMoving +
                ", isStatechange=" + statechange +
                ", isFlanking=" + isFlanking +
                ", isShields=" + isShields +
                ", isOffcycle=" + isOffcycle +
                ", pad61=" + pad61 +
                ", pad62=" + pad62 +
                ", pad63=" + pad63 +
                ", pad64=" + pad64 +
                ", src=" + src +
                ", dst=" + dst +
                '}';
    }
}