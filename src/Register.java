public class Register
{
    int value;

    boolean isInterrupt = false;

    public void setRegister(int value)
    {
        this.value = value;
    }

    public int getRegister()
    {
        return value;
    }

    public void setBitValue(int bit ,boolean valueBit)
    {
        if(valueBit)
        {
            setRegister(getRegister() | (int)(Math.pow(2, bit)));
        }
        else
        {
            setRegister(getRegister() & (0xFFFFFFFF - (int)(Math.pow(2, bit))));
        }
    }

    public boolean getBitValue(int bit)
    {
        int value = getRegister() & (int)(Math.pow(2, bit));
        return value == (int)(Math.pow(2, bit));
    }

    public void hexValue()
    {
        System.out.println(Integer.toHexString(getRegister()));
    }

    public boolean getIsInterrupt()
    {
        return isInterrupt;
    }
}
