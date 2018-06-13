package interfaces;

import matrixes.InvertableMatrix;
import matrixes.MatrixOutOfBoundException;
import matrixes.MyMatrixException;

public interface IInvertableMatrix extends IMatrix {

    // poluchit' element s zadannymi indeksami
//    double getCell(int x, int y);

    // izmenit' element s zadannymi indeksami
//    void setCell(int x, int y, double value);

    // vychislit' obratnuyu matricu, vozvrashaet FALSE esli matrica vyrojdena
    IInvertableMatrix calculateMatrixInverse() throws MyMatrixException;

    // vyvodit matricu
//    void outMatrix();

//    double getCellNew(int x, int y);
}