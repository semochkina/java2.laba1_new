package matrixes;

import interfaces.IInvertableMatrix;

public class InvertableMatrix extends Matrix implements IInvertableMatrix {

    private int size;
    private double[] matrix;

    public InvertableMatrix(int size) {
        super(size);
        this.size = size;
        matrix = new double[size*size*2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size*2; j++) {
                setCell(i, j, (i == j - size) ? 1. : 0.);
            }
        }
    }

    @Override
    public double getCell(int x, int y) {
        return matrix[x * size * 2 + y];
    }

    @Override
    public double getCellNew(int x, int y) {
        return matrix[x * size * 2 + y + size];
    }

    @Override
    public void setCell(int x, int y, double value) {
        if (-0.0000000001 < value && value < 0.0000000001) {
            value = 0.;
        }
        matrix[x * size * 2 + y] = value;
    }

    // izmenyaem soderjimoe v stroke (iz stroki X vychislyaetsya stroka Y * na soderjimoe yacheyki [X, Y])
    private void subtractionRow(int x, int y){
        int size = getSize();
        if (getCell(x, y) != 0) {
            double kf = getCell(x, y) * -1;
            // setCell(x, y, 0);
            for (int k = y; k < size * 2; k++)
                setCell(x, k, getCell(x, k) + getCell(y, k) * kf);
            System.out.println("Row " + (x + 1) + " += Row " + (y + 1) + " * " + kf);
//            outMatrix();
        }
    }


    // perestavlyaem stroki matrica
    private void perestavlyaemStroki(int nLine1, int nLine2){
        for (int i = 0; i < getSize() - 1; i++) {
            double currValue = getCell(nLine1, i);
            setCell(nLine1, i, getCell(nLine2, i));
            setCell(nLine2, i, currValue);
        }
    }

    @Override
    public boolean calculateMatrixInverse() {
        // privodim k edinichnoy matricy
        for (int i = 0; i < getSize(); i++) {
            if (getCell(i, i) == 0) {
                // nulevoy element na glavnoy diagonali
                // ishem druguyu stroku s ne nulevym znacheniem v dannom stolbce
                int n = 0;
                for (int j = i + 1; j < getSize(); j++) {
                    if (getCell(j, i) != 0) {
                        n = j;
                        break;
                    }
                }
                if (n == 0) {
                    // net stolbca bez 0, na glavnoy diagonali budet 0, opredelitel' == 0
//				cout << "0 in row " << i << " in all colums, determinant == 0" << endl;
                    return false;
                }
                // perestavlyaem stroki
                perestavlyaemStroki(i, n);
                System.out.println("0 in diagonal, change " + (i + 1) + " and " + (n + 1) + " rows");
//                outMatrix();
            }

            // na osnovnoy diagonali delaem 1
            double divider = getCell(i, i);
            if (divider != 1) {
                for (int j = 0; j < getSize() * 2; j++)
                    setCell(i, j, getCell(i, j) / divider);
//			setCell(i, i, 1);
                System.out.println("Row " + (i + 1) + " /= " + divider);
//                outMatrix();
            }
            for (int j = i + 1; j < getSize(); j++)
                subtractionRow(j, i);
        }
        for (int i = getSize() - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                subtractionRow(j, i);
            }
        }
        return true;
    }

//    @Override
//    public void outMatrix() {
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++)
//                System.out.print(getCell(i, j) + "\t");
//            System.out.print("\t|\t\t");
//            for (int j = size; j < size * 2; j++)
//                System.out.print(getCell(i, j) + "\t");
//            System.out.println();
//        }
//    }
}
