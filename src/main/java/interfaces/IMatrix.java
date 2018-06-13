package interfaces;

import matrixes.MatrixOutOfBoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IMatrix {

    // poluchit' element s zadannymi indeksami
    double getCell(int x, int y) throws MatrixOutOfBoundException;

    // izmenit' element s zadannymi indeksami
    void setCell(int x, int y, double value) throws MatrixOutOfBoundException;

    // vychislit' opredelitel' matricy (metodom Gaussa)
    double getDeterminant();

    int getSize();
//    // vyvodit matricu
//    void outMatrix();
//
//    // compare classes
//    boolean equals(Object o);
//
//    // hash-code - unikal'noe znachenie any object
//    int hashCode();


}
