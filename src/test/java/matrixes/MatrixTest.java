package matrixes;

import interfaces.IMatrix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixTest {
    private Matrix matrix;
    private Matrix iMatrix;

    @BeforeClass
    public void before() throws MatrixOutOfBoundException {
        matrix = new Matrix(2);
        matrix.setCell(0, 0, 1);
        matrix.setCell(0, 1, 2);
        matrix.setCell(1, 0, 3);
        matrix.setCell(1, 1, 2);

        iMatrix = new Matrix(2);
        iMatrix.setCell(0, 0, 1);
        iMatrix.setCell(0, 1, 2);
        iMatrix.setCell(1, 0, 3);
        iMatrix.setCell(1, 1, 4);

    }

    @Test
    public void testGetCell() throws MatrixOutOfBoundException {
        Assert.assertEquals(2, matrix.getCell(1, 1), 0.001);
        matrix.setCell(1, 1, 4);
        Assert.assertEquals(4, matrix.getCell(1, 1), 0.001);
    }

    @Test
    public void testEquals() throws MatrixOutOfBoundException {
        Assert.assertEquals(matrix, iMatrix);
        Assert.assertEquals(matrix.hashCode(), iMatrix.hashCode());

    }

    @Test
    public void testDet() {
        System.out.println("\nThe input matrix");
        System.out.println(iMatrix);
        double determinant = iMatrix.getDeterminant();
//        System.out.println("\ndeterminant this matrix = " + determinant);
        Assert.assertEquals(-2., determinant, 0.001);
    }
}
