import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Cortex_M0_SysTick_Test
{

    /**
     * Default constructor for test class Cortex_M0_SysTicTest
     */
    public Cortex_M0_SysTick_Test()
    {

    }

    @Test
    public void disable_test()
    {
        SysTick cortexM01 = new SysTick();
        cortexM01.setSourceInternal();
        cortexM01.setRVR(1);
        cortexM01.setCVR(4); //0
        cortexM01.setEnable();
        cortexM01.tickInternal(); //CVR = RVR
        assertEquals(1, cortexM01.getCVR());
        cortexM01.setRVR(0);
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(0, cortexM01.getRVR());
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(0, cortexM01.getRVR());
        cortexM01.setRVR(2);
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(2, cortexM01.getRVR());
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(2, cortexM01.getCVR());
        cortexM01.tickInternal();
        assertEquals(1, cortexM01.getCVR());
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
    }

    @Test
    public void testReload()
    {
        SysTick cortexM01 = new SysTick();
        cortexM01.setSourceInternal();
        cortexM01.setCVR(4);
        cortexM01.setRVR(4);
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(4, cortexM01.getRVR());
        for (int i=0; i<5; i++)
            cortexM01.tickInternal();
        assertEquals(4, cortexM01.getRVR());
        assertEquals(true, cortexM01.isCountFlag());
        cortexM01.getCSR();
        assertEquals(false, cortexM01.isCountFlag());
    }

    @Test
    public void testFlags()
    {
        SysTick cortexM01 = new SysTick();
        cortexM01.setSourceInternal();
        assertEquals(false, cortexM01.isInterruptFlag());
        assertEquals(false, cortexM01.isCountFlag());
        assertEquals(false, cortexM01.isEnableFlag());
        cortexM01.setEnable();
        cortexM01.setInterruptEnable();
        assertEquals(true, cortexM01.isInterruptFlag());
        assertEquals(false, cortexM01.isCountFlag());
        assertEquals(true, cortexM01.isEnableFlag());
        cortexM01.setCVR(4);
        cortexM01.setRVR(1);
        cortexM01.tickInternal();
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(true, cortexM01.isCountFlag());
        cortexM01.getCSR();
        assertEquals(false, cortexM01.isCountFlag());
    }


    @Test
    public void testRVR()
    {
        SysTick cortexM01 = new SysTick();
        cortexM01.setSourceInternal();
        cortexM01.setCVR(0);
        cortexM01.setRVR(0);
        assertEquals(0, cortexM01.getRVR());
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        cortexM01.setRVR(10);
        assertEquals(10, cortexM01.getRVR());
        cortexM01.setRVR(-1);
        assertEquals((1<<24)-1, cortexM01.getRVR());
        cortexM01.setRVR((1<<24)+1);
        assertEquals(1, cortexM01.getRVR());
    }


    @Test
    public void testInputs()
    {
        SysTick cortexM01 = new SysTick();
        cortexM01.setSourceInternal();
        cortexM01.setCVR(0);
        cortexM01.setRVR(5);
        assertEquals(0, cortexM01.getCVR());
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(5, cortexM01.getCVR());
        assertEquals(false, cortexM01.isCountFlag());
        cortexM01.setSourceExternal();
        for(int i=0;i<5;++i)
            cortexM01.tickInternal();
        assertEquals(5, cortexM01.getCVR());
        assertEquals(false, cortexM01.isCountFlag());
        cortexM01.setSourceInternal();
        for(int i=0;i<5;++i)
            cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(true, cortexM01.isCountFlag());
        cortexM01.setCVR(10);
        assertEquals(0, cortexM01.getCVR());
        assertEquals(false, cortexM01.isCountFlag());
    }


    @Test
    public void testInt()
    {
        SysTick cortexM01 = new SysTick();
        cortexM01.setSourceInternal();
        cortexM01.setCVR(0);
        cortexM01.setRVR(1);
        cortexM01.setEnable();
        cortexM01.setInterruptEnable();
        assertEquals(false, cortexM01.isInterrupt()); //jak zliczy do zera to ma wysłać przerwanie
        cortexM01.tickInternal();
        cortexM01.tickInternal();
        assertEquals(true, cortexM01.isCountFlag());
        assertEquals(0, cortexM01.getCVR());
        assertEquals(true, cortexM01.isInterrupt());
        cortexM01.tickInternal();
        assertEquals(1, cortexM01.getCVR());
    }

}







