package bt.bhudadapter.event;

import bt.bhudadapter.event.obj.CombatEvent;
import bt.bhudadapter.event.obj.ImguiEvent;

/**
 * Interface to listen to events from B-HUD.
 */
public interface BHudEventListener
{
    /**
     * Called when an Imgui event is received from B-HUD.
     *
     * @param e The received event.
     * @return Data that should be sent back to B-HUD. If you dont want to send anything, return null.
     */
    public byte[] onImguiEvent(ImguiEvent e);

    /**
     * Called when a local combat event is received from B-HUD.
     *
     * @param e The received event.
     * @return Data that should be sent back to B-HUD. If you dont want to send anything, return null.
     */
    public byte[] onLocalCombatEvent(CombatEvent e);

    /**
     * Called when an area combat event is received from B-HUD.
     *
     * @param e The received event.
     * @return Data that should be sent back to B-HUD. If you dont want to send anything, return null.
     */
    public byte[] onAreaCombatEvent(CombatEvent e);
}
