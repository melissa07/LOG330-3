import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class VarianceTest {
    Variance variance = null;
    ArrayList<Double> listNumbers = null;

    @Before
    public void setUp() throws Exception {
        variance = new Variance();
        listNumbers = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        listNumbers = null;
    }

    // Moyenne
    @Test
    public void testMoyenneBorneInferieure() {
        listNumbers.addAll(Arrays.asList(1.3, 2.5, 3.5, 4.5, 5.5, Double.MIN_VALUE-1.0));
        Variance.listNumbers = listNumbers;
        Assert.assertTrue(Double.isNaN(variance.calcAverage()));
    }

    @Test(expected = AssertionError.class)
    public void testMoyenneBorneSuperieure() {
        listNumbers.addAll(Arrays.asList(1.3, 2.5, 3.5, 4.5, 5.5, Double.MAX_VALUE+1.0));
        Variance.listNumbers = listNumbers;
        Assert.assertTrue(Double.isNaN(variance.calcAverage()));
    }

    @Test(expected = NumberFormatException.class)
    public void testMoyenneValeurInvalide() {
        variance.readFile();
    }


    // Variance
    @Test
    public void testVarianceBorneInferieure() {
        listNumbers.addAll(Arrays.asList(1.3, 2.5, 3.5, 4.5, 5.5, Double.MIN_VALUE-1.0));
        Variance.listNumbers = listNumbers;
        Assert.assertTrue(Double.isNaN(variance.calcAverage()));
    }

    @Test(expected = AssertionError.class)
    public void testVarianceBorneSuperieure() {
        listNumbers.addAll(Arrays.asList(1.3, 2.5, 3.5, 4.5, 5.5, Double.MAX_VALUE+1.0));
        Variance.listNumbers = listNumbers;
        double aver = variance.calcAverage();
        Assert.assertTrue(Double.isNaN(variance.calcVariance(aver)));
    }

    @Test(expected = NumberFormatException.class)
    public void testVarianceValeurInvalide() {
        variance.readFile();
    }


    // Ecart type
    @Test
    public void testEcartTypeBorneInferieure() {
        listNumbers.addAll(Arrays.asList(1.3, 2.5, 3.5, 4.5, 5.5, Double.MIN_VALUE-1.0));
        Variance.listNumbers = listNumbers;
        double mean = variance.calcAverage();
        double var = variance.calcVariance(mean);
        Assert.assertTrue(Double.isNaN(variance.calcStandardDev(var)));
    }

    @Test(expected = AssertionError.class)
    public void testEcartTypeBorneSuperieure() {
        listNumbers.addAll(Arrays.asList(1.3, 2.5, 3.5, 4.5, 5.5, Double.MAX_VALUE+1.0));
        Variance.listNumbers = listNumbers;
        Assert.assertTrue(Double.isNaN(variance.calcAverage()));
    }

    @Test(expected = NumberFormatException.class)
    public void testEcartTypeValeurInvalide() {
        variance.readFile();
    }

}