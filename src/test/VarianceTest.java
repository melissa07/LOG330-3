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
        variance = null;
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

    @Test
    public void testCorrelationNulleaFaible() {
        double corr = 0.152;
        Assert.assertTrue(variance.interpreterCorr(corr).equals("Correlation nulle a faible"));
    }

    @Test
    public void testCorrelationFaibleaMoyenne() {
        double corr = 0.20;
        Assert.assertTrue(variance.interpreterCorr(corr).equals("Correlation faible a moyenne"));
    }

    @Test
    public void testCorrelationMoyenneaForte() {
        double corr = 0.44;
        Assert.assertTrue(variance.interpreterCorr(corr).equals("Correlation moyenne a forte"));
    }

    @Test
    public void testCorrelationForteaTresForte() {
        double corr = 0.89;
        Assert.assertTrue(variance.interpreterCorr(corr).equals("Correlation forte a tres forte"));
    }

    @Test
    public void testCorrelationTresForteAParfaite() {
        double corr = 0.96;
        Assert.assertTrue(variance.interpreterCorr(corr).equals("Correlation tres forte a parfaite"));
    }


    @Test(expected = java.lang.ArithmeticException.class)
    public void testRegressioNDivisionParZero() {
            Variance.corrCol1.clear();
            Variance.corrCol2.clear();

            Variance.corrCol1.add(0.0);
            Variance.corrCol2.add(0.0);

    }


}