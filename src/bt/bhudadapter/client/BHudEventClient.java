package bt.bhudadapter.client;

import bt.bhudadapter.event.BHudEventListener;
import bt.bhudadapter.event.BHudEventParser;
import bt.remote.socket.RawClient;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BHudEventClient extends RawClient
{
    protected BHudEventParser eventParser;

    /**
     * Creates a new instance and attemots to set up a client.
     *
     * This constructor will try to find the Guild Wars 2 process with the given path to determine the required port.
     *
     * To actually start reading data the {@link #start()} method needs to be called.
     *
     * @param gw2Path The absolute path to the Guild Wars 2 exe.
     * @throws IOException
     */
    public BHudEventClient(String gw2Path) throws IOException
    {
        super("127.0.0.1", findPort(gw2Path));

        // messages are not processed on separate threads
        setSingleThreadProcessing(true);

        // reconnect with infinite tries
        autoReconnect(-1);

        setupDefaultEventParser();
    }

    public void setEventListener(BHudEventListener listener)
    {
        if (this.eventParser != null)
        {
            this.eventParser.setEventListener(listener);
        }
    }

    protected void setupDefaultEventParser()
    {
        this.eventParser = new BHudEventParser();
        setByteProcessor(this.eventParser);
    }

    @Override
    protected void setupDefaultDataReader()
    {
        // defining how data is read from the stream and what part of the data is given to the processing
        setDataReader(in -> {
            byte[] data = new byte[8];
            int bytes = 0;
            int messageLength = 0;

            try
            {
                // read first 8 bytes as message header
                bytes = in.read(data, 0, 8);
                var buffer = ByteBuffer.wrap(data);
                buffer.order(ByteOrder.LITTLE_ENDIAN);

                // header contains length of the rest of the message
                messageLength = buffer.getInt();
                data = new byte[messageLength];

                // read remaining bytes of message
                bytes = in.read(data, 0, messageLength);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return data;
        });
    }

    protected static int findPort(String gw2Path)
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
}