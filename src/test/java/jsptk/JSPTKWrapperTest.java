package jsptk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.testng.annotations.Test;

public class JSPTKWrapperTest {

    @Test
    public void testMCEP() throws Exception{
        double[] sp = JSPTKProvider.providerSP();
        double[] ref= JSPTKProvider.providerMCEP();
        double[] test = JSPTKWrapper.mcep(sp, 49, 0.55, 2, 30,
                                          0.001, 2, -1e-08, 1e-06, 0);
        assertThat(test).containsExactly(ref, within(1e-4));
    }

    @Test
    public void testMC2B() throws Exception{
        double[] mgc = JSPTKProvider.providerMGC();
        double[] ref = JSPTKProvider.providerB();
        double[] test = JSPTKWrapper.mc2b(mgc, 0.55);
        assertThat(test).containsExactly(ref, within(1e-6));
    }

    @Test
    public void testFreqt() throws Exception{
        double[] mgc = JSPTKProvider.providerMGC();
        double[] ref = JSPTKProvider.providerFREQT();
        double[] test = JSPTKWrapper.freqt(mgc, 511, 0.55);
        assertThat(test).containsExactly(ref, within(1e-6));
    }

    @Test
    public void testC2ACR() throws Exception{
        double[] mgc = JSPTKProvider.providerMGC();
        double[] test = JSPTKWrapper.c2acr(mgc, 0, 512);
        assertThat(test[0]).isCloseTo(0.01000931, within(1e-6));
    }

    @Test
    public void testFFTR() throws Exception{
        double[] freqt = JSPTKProvider.providerFREQT();
        double[] ref = JSPTKProvider.providerSP();
        double[] test = JSPTKWrapper.fftr(freqt).real;
        assertThat(test).containsExactly(ref, within(1e-4));
    }

}
