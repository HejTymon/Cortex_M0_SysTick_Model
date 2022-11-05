public interface Cortex_M0_SysTick_Interface
{
    public void setRVR(int value);
    public void setCVR(int value);
    public void setCSR(int value);

    public int getCVR();
    public int getRVR();
    public int getCSR();

    public void reset(int value);
    public void setEnable();
    public void setDisable();
    public void setSourceExternal();
    public void setSourceInternal();
    public void setInterruptEnable();
    public void setInterruptDisable();

    public boolean getEnabled();
    public boolean getInterrupt();
    public boolean getSource();
    public boolean getCountFlag();

    public void tickInternal();
    public void tickExternal();

    public boolean isCountFlag();
    public boolean isEnableFlag();
    public boolean isInterruptFlag();
    public boolean isInterrupt();
}
