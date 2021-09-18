package bt.bhudadapter.event;

import bt.bhudadapter.event.obj.Agent;
import bt.bhudadapter.event.obj.CombatEvent;
import bt.bhudadapter.event.obj.ImguiEvent;
import bt.bhudadapter.event.types.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Parser to convert received byte data to event instances.
 *
 * Set a listener via {@link #setEventListener(BHudEventListener) setEventListener} to receive events.
 */
public class BHudEventParser
{
    // bitmap flags to check which parts of the message have been sent
    protected static final byte COMBATEVENT_PRESENT = 1;
    protected static final byte SRC_PRESENT = 2;
    protected static final byte DST_PRESENT = 4;
    protected static final byte SKILLNAME_PRESENT = 8;

    /**
     * Processes the incoming raw byte data and converts it to actual events which are then passed to the set listener.
     *
     * @param data The raw message byte data read from the connection to B-HUD.
     * @param listener The listener instance that should receive parsed events.
     * @return Data that should be sent back to B-HUD. If you dont want to send anything, return null.
     */
    public byte[] parse(byte[] data, BHudEventListener listener)
    {
        byte[] returnData = null;

        if (data.length > 0 && listener != null)
        {
            byte eventType = data[0];

            if (eventType == EventType.IMGUI)
            {
                ImguiEvent event = new ImguiEvent();

                // message only contains one byte of actual data
                // flag to show if we are currently in character select or loading screen
                event.setCharSelectOrLoading(data[1] != 0);

                returnData = listener.onImguiEvent(event);
            }
            else if (eventType == EventType.AREA_COMBAT || eventType == EventType.LOCAL_COMBAT)
            {
                // first byte tells us which values will be present
                // ranges from 0000 = no values present to 1111 = event, src, dst and skillname present
                byte presentDataBitMap = data[1];

                // start with an offset of 2 to account for the event type and bitmap
                int offset = 2;

                CombatEvent event = new CombatEvent();

                if ((presentDataBitMap & COMBATEVENT_PRESENT) == COMBATEVENT_PRESENT)
                {
                    offset = parseCombatEvent(event, data, offset);
                }

                if ((presentDataBitMap & SRC_PRESENT) == SRC_PRESENT)
                {
                    offset = parseSrcAgent(event, data, offset);
                }

                if ((presentDataBitMap & DST_PRESENT) == DST_PRESENT)
                {
                    offset = parseDstAgent(event, data, offset);
                }

                if ((presentDataBitMap & SKILLNAME_PRESENT) == SKILLNAME_PRESENT)
                {
                    offset = parseSkillname(event, data, offset);
                }

                event.setId(getLong(data, offset));
                offset += 8;

                event.setRevision(getLong(data, offset));
                offset += 8;

                if (eventType == EventType.AREA_COMBAT)
                {
                    returnData = listener.onAreaCombatEvent(event);
                }
                else if (eventType == EventType.LOCAL_COMBAT)
                {
                    returnData = listener.onLocalCombatEvent(event);
                }
            }
        }

        // returned byte data will be sent back to bhud
        return returnData;
    }

    protected int parseCombatEvent(CombatEvent event, byte[] data, int offset)
    {
        event.setTime(getLong(data, offset));
        offset += 8;
        event.setSrcAgent(getLong(data, offset));
        offset += 8;
        event.setDstAgent(getLong(data, offset));
        offset += 8;
        event.setValue(getInt(data, offset));
        offset += 4;
        event.setBuffDmg(getInt(data, offset));
        offset += 4;
        event.setOverStackValue(getInt(data, offset));
        offset += 4;
        event.setSkillId(getInt(data, offset));
        offset += 4;
        event.setSrcInstId(getShort(data, offset));
        offset += 2;
        event.setDstInstId(getShort(data, offset));
        offset += 2;
        event.setSrcMasterInstId(getShort(data, offset));
        offset += 2;
        event.setDstMasterInstId(getShort(data, offset));
        offset += 2;
        event.setIff(IFF.forValue(Byte.toUnsignedInt(data[offset])));
        offset += 1;
        event.setBuff(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setResult(CombatResult.forValue(Byte.toUnsignedInt(data[offset])));
        offset += 1;
        event.setActivation(Activation.forValue(Byte.toUnsignedInt(data[offset])));
        offset += 1;
        event.setBuffRemove(BuffRemove.forValue(Byte.toUnsignedInt(data[offset])));
        offset += 1;
        event.setIsNinety(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setIsFifty(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setIsMoving(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setStatechange(StateChange.forValue(Byte.toUnsignedInt(data[offset])));
        offset += 1;
        event.setIsFlanking(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setIsShields(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setIsOffcycle(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setPad61(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setPad62(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setPad63(Byte.toUnsignedInt(data[offset]));
        offset += 1;
        event.setPad64(Byte.toUnsignedInt(data[offset]));
        offset += 1;

        return offset;
    }

    protected int parseSrcAgent(CombatEvent event, byte[] data, int offset)
    {
        Agent src = new Agent();
        offset = parseAgent(src, data, offset);
        event.setSrc(src);

        return offset;
    }

    protected int parseDstAgent(CombatEvent event, byte[] data, int offset)
    {
        Agent dst = new Agent();
        offset = parseAgent(dst, data, offset);
        event.setDst(dst);

        return offset;
    }

    protected int parseAgent(Agent agent, byte[] data, int offset)
    {
        long nameLength = getLong(data, offset);
        offset += 8;
        agent.setName(getString(data, offset, (int)nameLength));
        offset += nameLength;
        agent.setId(getLong(data, offset));
        offset += 8;
        agent.setProf(getInt(data, offset));
        offset += 4;
        agent.setElite(getInt(data, offset));
        offset += 4;
        agent.setSelf(getInt(data, offset));
        offset += 4;
        agent.setTeam(getShort(data, offset));
        offset += 2;

        return offset;
    }

    protected int parseSkillname(CombatEvent event, byte[] data, int offset)
    {
        long nameLength = getLong(data, offset);
        offset += 8;
        event.setSkillName(getString(data, offset, (int)nameLength));
        offset += nameLength;

        return offset;
    }

    protected long getLong(byte[] data, int offset)
    {
        return getByteBuffer(data, offset, 8).getLong();
    }

    protected int getInt(byte[] data, int offset)
    {
        return getByteBuffer(data, offset, 4).getInt();
    }

    protected short getShort(byte[] data, int offset)
    {
        return getByteBuffer(data, offset, 2).getShort();
    }

    protected ByteBuffer getByteBuffer(byte[] data, int offset, int length)
    {
        var buffer = ByteBuffer.wrap(data, offset, offset + length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer;
    }

    protected String getString(byte[] data, int offset, int length)
    {
        char[] chars = new char[length];

        for (int i = offset; i < offset + length; i++)
        {
            chars[i - offset] = (char)data[i];
        }

        return new String(chars);
    }
}