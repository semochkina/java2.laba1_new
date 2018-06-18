package matrixes;

import interfaces.IInvertableMatrix;
import matrixes.InvertableMatrix;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvertableMatrixTest {

    @Test
    public void testDiagonal() throws MyMatrixException {

        InvertableMatrix iInvertableMatrix = new InvertableMatrix(new Matrix(new double[]{7.0,4.0,5.0,3.0}));
        System.out.println("\nThe input matrix");
        System.out.println(iInvertableMatrix);

        IInvertableMatrix iInvertableMatrixNew = iInvertableMatrix.calculateMatrixInverse();
        InvertableMatrix multiply = new InvertableMatrix( iInvertableMatrix.mult(iInvertableMatrixNew));
        System.out.println("\nObratnaya matrica:");
        System.out.println(iInvertableMatrixNew);
        assertEquals(new InvertableMatrix(multiply.getSize()), multiply);
        System.out.println(multiply);
    }

    @Test
    public void test() throws MyMatrixException {

        InvertableMatrix iInvertableMatrix = new InvertableMatrix(new Matrix(new double[]{1.0,2.0,3.0,4.0}));
//        assertEquals(iInvertableMatrix.getCell(1,0), 0, 0.001);
//        assertEquals(iInvertableMatrix.getCell(1,0), 3, 0.001);


        System.out.println("\nThe input matrix");
        System.out.println(iInvertableMatrix);

        System.out.println("\nObratnaya matrica:");
        IInvertableMatrix iInvertableMatrixNew = iInvertableMatrix.calculateMatrixInverse();
        Assert.assertTrue(iInvertableMatrixNew != null);
        InvertableMatrix multiply = new InvertableMatrix(iInvertableMatrix.mult(iInvertableMatrixNew));
        System.out.println(iInvertableMatrixNew);
        assertEquals(new InvertableMatrix(multiply.getSize()), multiply);

        assertEquals(iInvertableMatrixNew.getCell(0,0), -2, 0.001);
    }

}
