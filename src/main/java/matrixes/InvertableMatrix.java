package matrixes;

import interfaces.IInvertableMatrix;

public class InvertableMatrix extends Matrix implements IInvertableMatrix {

    public InvertableMatrix(int size) throws MatrixOutOfBoundException {
        super(size);
        for (int i = 0; i < size*size; i++) {
            super.setCell(i,i,1.);
        }
    }

    public InvertableMatrix (Matrix matrix) throws MyMatrixException {
        super(matrix);
        if (Math.abs(matrix.getDeterminant())<1e-7) {
            throw new MyMatrixException("det == 0");
        }
    }
//    private double getCellNew(int x, int y) {
//        return matrix[x * size * 2 + y + size];
//    }

    @Override
    public void setCell(int x, int y, double value) throws MatrixOutOfBoundException {
        double temp = getCell(x,y);
        super.setCell(x,y,value);
        if (Math.abs(getDeterminant())<1e-6) {
            super.setCell(x, y, temp);
        } else {
            super.setCell(x, y, value);
        }
    }

    // izmenyaem soderjimoe v stroke (iz stroki X vychislyaetsya stroka Y * na soderjimoe yacheyki [X, Y])
    private void subtractionRow(int x, int y) throws MatrixOutOfBoundException {
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
    private void perestavlyaemStroki(int nLine1, int nLine2) throws MatrixOutOfBoundException {
        for (int i = 0; i < getSize() - 1; i++) {
            double currValue = getCell(nLine1, i);
            setCell(nLine1, i, getCell(nLine2, i));
            setCell(nLine2, i, currValue);
        }
    }

    @Override
    public InvertableMatrix calculateMatrixInverse() throws MyMatrixException {
        InvertableMatrix my = new InvertableMatrix(this);
        InvertableMatrix unit = new InvertableMatrix(getSize());
        // privodim k edinichnoy matricy
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (my.getCell(i, i) == 0) {
                // nulevoy element na glavnoy diagonali
                // ishem druguyu stroku s ne nulevym znacheniem v dannom stolbce
                int n = 0;
                for (int j = i + 1; j < size; j++) {
                    if (my.getCell(j, i) != 0) {
                        n = j;
                        break;
                    }
                }
                if (n == 0) {
                    // net stolbca bez 0, na glavnoy diagonali budet 0, opredelitel' == 0
//				cout << "0 in row " << i << " in all colums, determinant == 0" << endl;
                    throw new MyMatrixException("det == 0");
                }
                // perestavlyaem stroki
                my.perestavlyaemStroki(i, n);
                unit.perestavlyaemStroki(i,n);
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
        return unit;
    }

    @Override
    public String toString() {
        int size = getSize();
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++)
                    sb.append(getCell(i, j)).append("\t");
                sb.append("\t|\t\t");
                for (int j = size; j < size * 2; j++)
                    sb.append(getCell(i, j)).append("\t");
                sb.append("\n");
            }
        } catch (MatrixOutOfBoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
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
