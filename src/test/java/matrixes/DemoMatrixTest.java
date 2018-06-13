package matrixes;

//import interfaces.IDemoMatrix;
import matrixes.Matrix;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class DemoMatrixTest {
    private static final double DELTA = 0.0001;

    @Test
    public void test2x2() throws MatrixOutOfBoundException {
        DemoMatrix matrix2 = new DemoMatrix(2);


        // заполняем матрицу из файла
        try (InputStream inputStream = DemoMatrixTest.class.getResourceAsStream("/matrix2x2.txt")) {
            matrix2.readFromStream(inputStream);
        } catch (Exception ex) {
            Assert.fail("Error read file - " + ex.getMessage());
        }

        // выполняем методы класса
        System.out.println(matrix2);
        Assert.assertEquals(matrix2.getCell(0, 0), 1.0, DELTA);
        final double sumAll = matrix2.sumAll();
        Assert.assertEquals(sumAll, 8.0, DELTA);
        matrix2.setCell(0, 0, 12.34);
        Assert.assertEquals(matrix2.getCell(0, 0), 12.34, DELTA);

        // изменяем матрицу
        try (InputStream inputStream = new ByteArrayInputStream("1,5 2,0\n3,0 4,0".getBytes())) {
            matrix2.readFromStream(inputStream);
        } catch (Exception ex) {
            Assert.fail("Error read from ByteArrayInputStream - " + ex.getMessage());
        }
        System.out.println(matrix2);
        Assert.assertEquals(matrix2.getCell(0, 0), 1.5, DELTA);

        // матрица сериализуется в файл
        try (OutputStream outputStream = new FileOutputStream(new File("./temp.txt"))) {
            matrix2.writeToStream(outputStream);
        } catch (Exception ex) {
            Assert.fail("Error write temp file - " + ex.getMessage());
        }

        // матрица десериализуется из файла
        DemoMatrix matrix2New = new DemoMatrix(2);
        Assert.assertNotEquals(matrix2, matrix2New);
        try (final InputStream inputStream = new FileInputStream("./temp.txt")) {
            matrix2New.readFromStream(inputStream);
        } catch (Exception ex) {
            Assert.fail("Error read temp file - " + ex.getMessage());
        }
        Assert.assertEquals(matrix2, matrix2New);
    }

    @Test
    public void test3x3() throws MatrixOutOfBoundException {
        DemoMatrix matrix3 = new DemoMatrix(3);

        // заполняем матрицу из файла
        try (InputStream inputStream = DemoMatrixTest.class.getResourceAsStream("/matrix2x2.txt")) {
            matrix3.readFromStream(inputStream);
            Assert.fail("Error");
        } catch (Exception ex) {
            // ожидаемая ошибка - не хватает параметров
        }

        try (InputStream inputStream = DemoMatrixTest.class.getResourceAsStream("/matrix3x3.txt")) {
            matrix3.readFromStream(inputStream);
        } catch (Exception ex) {
            Assert.fail("Error read file - " + ex.getMessage());
        }

        // выполняем методы класса
        System.out.println(matrix3);
        Assert.assertEquals(matrix3.getCell(0, 0), 1.1, DELTA);
        final double sumAll = matrix3.sumAll();
        Assert.assertEquals(sumAll, 19.8, DELTA);
        matrix3.setCell(0, 0, 12.34);
        Assert.assertEquals(matrix3.getCell(0, 0), 12.34, DELTA);

        // изменяем матрицу
        try (InputStream inputStream = new ByteArrayInputStream(("1,5 2,0 3,0\n" +
                "3,5 4,0 1,0\n" +
                "3,1 4,2 1,3").getBytes())) {
            matrix3.readFromStream(inputStream);
        } catch (Exception ex) {
            Assert.fail("Error read from ByteArrayInputStream - " + ex.getMessage());
        }
        System.out.println(matrix3);
        Assert.assertEquals(matrix3.getCell(0, 0), 1.5, DELTA);
    }
}
/*
5. Написать класс DemoMatrix с методами:
- вывести матрицу в текстовый поток в виде квадратной таблицы,
- прочитать матрицу из потока,
- посчитать сумму всех элементов матрицы,

- метод main, в котором:
- создаются объекты написанных классов,
- матрицы заполняются из файлов,
- выполняются методы классов,
- измененные матрицы выводятся на консоль,
- одна из матриц сериализуется в файл, затем - десериализуется.
В методе main обрабатываются исключения: выход индекса за границы и исключения
потоков ввода/вывода.
 */