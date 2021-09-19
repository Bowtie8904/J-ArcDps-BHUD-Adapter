package bt.bhudadapter.client;

import bt.bhudadapter.event.BHudEventListener;
import bt.bhudadapter.event.BHudEventParser;
import bt.remote.socket.RawClient;
import bt.remote.socket.data.ByteProcessor;
import bt.remote.socket.data.RawDataReader;
import bt.runtime.evnt.Dispatcher;
import bt.types.Killable;
import bt.utils.Null;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BHudEventClient implements ByteProcessor, RawDataReader, Killable
{
    protected RawClient client;
    protected BHudEventParser eventParser;
    protected BHudEventListener listener;

    /**
     * Creates a new instance.
     *
     * This constructor will try to find the Guild Wars 2 process with the given path to determine the required port.
     *
     * To actually setup a socket and start reading data the {@link #start()} method needs to be called.
     *
     * @param gw2Path The absolute path to the Guild Wars 2 exe.
     */
    public BHudEventClient(String gw2Path)
    {
        this.client = new RawClient("127.0.0.1", findPort(gw2Path));
        // messages are not processed on separate threads
        this.client.setSingleThreadProcessing(true);

        // reconnect with 3 tries
        this.client.autoReconnect(3);

        this.client.setByteProcessor(this);
        this.client.setDataReader(this);

        setupDefaultEventParser();
    }

    /**
     * Starts the connection to the B-Hud socket.
     */
    public void start()
    {
        this.client.start();
    }

    /**
     * Gets the {@link Dispatcher} used to distribute events of the client.
     *
     * These events are network connection related and have nothing to do with arcdps events.
     *
     * <p>
     * Possible events:
     * <ul>
     * <li>{@link bt.remote.socket.evnt.ConnectionSuccessfull} initial connection to the host succeeded</li>
     * <li>{@link bt.remote.socket.evnt.ConnectionFailed} initial connection to the host failed</li>
     * <li>{@link bt.remote.socket.evnt.ConnectionLost} previously established connection was lost</li>
     * <li>{@link bt.remote.socket.evnt.ReconnectStarted} reconnect efforts were started</li>
     * <li>{@link bt.remote.socket.evnt.ReconnectFailed} reconnect efforts failed</li>
     * <li>{@link bt.remote.socket.evnt.ReconnectSuccessfull} reconnect efforts were successful</li>
     * <li>{@link bt.remote.socket.evnt.ReconnectAttempt} a specific reconnect attempt was started</li>
     * <li>{@link bt.remote.socket.evnt.ReconnectAttemptFailed} a specific reconnect attempt failed</li>
     * <li>{@link bt.remote.socket.evnt.ClientKilled} the client was destroyed through a call to {@link #kill()}</li>
     * <li>{@link bt.remote.socket.evnt.UnspecifiedException} less specific exception was thrown somewhere in this class</li>
     * </ul>
     * </p>
     *
     */
    public Dispatcher getEventDispatcher()
    {
        return this.client.getEventDispatcher();
    }

    /**
     * Sets the listener that will receive events from this client.
     *
     * @param listener
     */
    public void setEventListener(BHudEventListener listener)
    {
        this.listener = listener;
    }

    /**
     * Sets up the parser for incoming byte data.
     *
     * Override if you want to use your own {@link BHudEventParser}.
     */
    protected void setupDefaultEventParser()
    {
        this.eventParser = new BHudEventParser();
    }

    @Override
    public byte[] read(DataInputStream in) throws IOException
    {
        byte[] data = new byte[8];
        int bytes = 0;
        int messageLength = 0;

        // read first 8 bytes as message header
        bytes = in.read(data, 0, 8);
        var buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        // header contains length of the rest of the message
        messageLength = buffer.getInt();
        data = new byte[messageLength];

        // read remaining bytes of message
        bytes = in.read(data, 0, messageLength);

        return data;
    }

    protected int findPort(String gw2Path)
    {
        var process = ProcessHandle.allProcesses()
                                   .filter(p ->p.info().command().isPresent())
                                   .filter(p -> p.info().command().get().equalsIgnoreCase(gw2Path))
                                   .findAny().orElse(null);

        if (process != null)
        {
            // setting first 2 bits of 2 byte pid to 1
            // required since b-hud will dynamically use a port relative to the attached gw2 process
            return (int)(process.pid() | 1 << 14 | 1 << 15);
        }
        else
        {
            throw new IllegalArgumentException("Could not find process of Guild Wars 2. Please make sure '" + gw2Path + "' is the correct path.");
        }
    }

    @Override
    public byte[] process(byte[] incoming)
    {
        return this.eventParser.parse(incoming, this.listener);
    }

    @Override
    public void kill()
    {
        Null.checkKill(this.client);
    }
}