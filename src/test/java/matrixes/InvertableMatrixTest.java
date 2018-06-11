package matrixes;

import interfaces.IInvertableMatrix;
import matrixes.InvertableMatrix;
import org.junit.Assert;
import org.junit.Test;

public class InvertableMatrixTest {

    @Test
    public void test() {

        IInvertableMatrix iInvertableMatrix = new InvertableMatrix(2);
        Assert.assertEquals(iInvertableMatrix.getCell(1,0), 0, 0.001);
        iInvertableMatrix.setCell(0, 0, 1);
        iInvertableMatrix.setCell(0, 1, 2);
        iInvertableMatrix.setCell(1, 0, 3);
        iInvertableMatrix.setCell(1, 1, 2);
        Assert.assertEquals(iInvertableMatrix.getCell(1,0), 3, 0.001);


        System.out.println("\nThe input matrix");
        System.out.println(iInvertableMatrix);

        System.out.println("\nObratnaya matrica:");
        Assert.assertEquals(iInvertableMatrix.getCell(0,0), 1, 0.001);
        Assert.assertTrue(iInvertableMatrix.calculateMatrixInverse());
        System.out.println(iInvertableMatrix);
        Assert.assertEquals(iInvertableMatrix.getCellNew(0,0), -0.5, 0.001);
    }

}
