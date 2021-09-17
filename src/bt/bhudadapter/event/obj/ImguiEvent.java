package bt.bhudadapter.event.obj;

public class ImguiEvent
{
    private boolean isCharSelectOrLoading;

    public boolean isCharSelectOrLoading()
    {
        return isCharSelectOrLoading;
    }

    public void setCharSelectOrLoading(boolean charSelectOrLoading)
    {
        isCharSelectOrLoading = charSelectOrLoading;
    }

    @Override
    public String toString()
    {
        return "ImguiEvent{" +
                "isCharSelectOrLoading=" + isCharSelectOrLoading +
                '}';
    }
}