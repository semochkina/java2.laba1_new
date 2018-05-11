package matrixes;

import interfaces.IMatrix;

import java.util.Arrays;

public class Matrix implements IMatrix {

    private boolean flagCache = false;
    private double memDeterminant;
    // Размерность матрицы
    private int size;
    // Матрица
    private double[] matrix;

    public Matrix(int size) {
        this.size = size;
        matrix = new double[size*size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i] = 0;
            }
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public double getCell(int x, int y) {
        return matrix[x * size + y];
    }

    @Override
    public void setCell(int x, int y, double value) {
        matrix[x * size + y] = value;
        flagCache = false;
    }

    // perestavlyaem stroki matrica
    private void perestavlyaemStroki(int nLine1, int nLine2){
        for (int i = 0; i < size - 1; i++) {
            double currValue = getCell(nLine1, i);
            setCell(nLine1, i, getCell(nLine2, i));
            setCell(nLine2, i, currValue);
        }
    }

    @Override
    public double getDeterminant() {
        if (flagCache) {
            return memDeterminant;
        }
        // opredelitel'
        double value = 1;
        // privodim k verhney treugol'noy matricy
        for (int i = 0; i < size - 1; i++) {
            if (getCell(i, i) == 0) {
                // nulevoy element na glavnoy diagonali
                // ishem druguyu stroku s ne nulevym znacheniem v dannom stolbce
                int n = 0;
                for (int j = i + 1; j < size; j++) {
                    if (getCell(j, i) != 0) {
                        n = j;
                        break;
                    }
                }
                if (n == 0) {
                    // net stolbca bez 0, na glavnoe diagonali budet 0, opredelitel' == 0
                    System.out.println("0 in row " + i + " in all colums, determinant == 0");
                    return 0;
                }
                // perestavlyaem stroki
                perestavlyaemStroki(i, n);
                // pri perestanovki strok menyaetsya znak
                value *= -1;
                System.out.println("Change rows  " + i + " -> " + n);
            }
            // Operaciya dobavleniya k odnoy iz strok matricy drugoy stroki, umnojennoy na nekotoroe chislo, ne menyaet opredelitel'
            // k ostal'nym strokam dobavlyaem tekushuyu s takim koefficientom, chtoby v dannoy kolonke byl 0
            for (int j = i + 1; j < size; j++) {
                if (getCell(j, i) == 0) {
                    System.out.println("don't change row number " + j);
                } else {
                    double kf = getCell(j, i) / getCell(i, i) * -1;
                    // setCell(j, i, 0);
                    for (int k = i; k < size; k++)
                        setCell(j, k, getCell(j, k) + getCell(i, k) * kf);
                    System.out.println("N row = " + j + ", kf == " + kf);
                }
            }
            value *= getCell(i, i);
        }
        value *= getCell(size - 1, size - 1);
        memDeterminant = value;
        flagCache = true;
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(this.getCell(i, j)).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        return flagCache == matrix1.flagCache &&
               Double.compare(matrix1.memDeterminant, memDeterminant) == 0 &&
               size == matrix1.size &&
               Arrays.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (flagCache ? 1 : 0);
        temp = Double.doubleToLongBits(memDeterminant);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + size;
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}
