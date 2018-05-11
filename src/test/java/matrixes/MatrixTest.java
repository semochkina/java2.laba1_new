package matrixes;

import interfaces.IMatrix;
import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void test() {

        IMatrix matrix = new Matrix(2);
        Assert.assertEquals(matrix.getCell(1,0), 0, 0.001);
        matrix.setCell(0, 0, 1);
        matrix.setCell(0, 1, 2);
        matrix.setCell(1, 0, 3);
        matrix.setCell(1, 1, 2);
        Assert.assertEquals(matrix.getCell(1,0), 3, 0.001);

        IMatrix iMatrix = new Matrix(2);
        Assert.assertEquals(iMatrix.getCell(1,0), 0, 0.001);
        iMatrix.setCell(0, 0, 1);
        iMatrix.setCell(0, 1, 2);
        iMatrix.setCell(1, 0, 3);
        iMatrix.setCell(1, 1, 2);

        Assert.assertEquals(matrix, iMatrix);
        Assert.assertEquals(matrix.hashCode(), iMatrix.hashCode());

        System.out.println("\nThe input matrix");
        System.out.println(iMatrix);
        double determinant = iMatrix.getDeterminant();

        System.out.println("\ndeterminant this matrix = " + determinant);

        Assert.assertEquals(determinant, -4., 0.001);

        Assert.assertNotEquals(matrix, iMatrix);
        Assert.assertNotEquals(matrix.hashCode(), iMatrix.hashCode());
    }
}
