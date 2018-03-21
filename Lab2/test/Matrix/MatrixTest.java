package Matrix;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class MatrixTest {
    private double[][] tablica;

    @Before
    public void TwoDeminsionalTable(){
        tablica = new double[4][];
        tablica[0] = new double[] {1,2,3};
        tablica[1] = new double[] {7,8,0};
        tablica[2] = new double[] {4,9,10};
        tablica[3] = new double[] {5,10,1};
    }

    @Test
    public void MatrixWithRowAndCols(){
        Matrix macierz = new Matrix(4,5);
        assertEquals(macierz.rows,4);
        assertEquals(macierz.cols,5);
        assertEquals(macierz.data.length,20);
    }
    @Test
    public void MatrixWithArray(){
        double [][]tab = new double[4][];
        tab[0] = new double[] {1};
        tab[1] = new double[] {3,5};
        tab[2] = new double[] {1,5,10};
        tab[3] = new double[] {5,10,15};
        Matrix macierz = new Matrix(tab);
        assertEquals(macierz.cols,3);
        assertEquals(macierz.data[1],0,0);
        assertEquals(macierz.data[2],0,0);
        assertEquals(macierz.data[5],0,0);
    }

    @Test
    public void asArray() throws Exception {

        Matrix macierz = new Matrix(tablica);
        for(int i=0; i<4 ; i++){
            for(int j=0 ; j<3 ; j++){
                assertEquals(macierz.asArray()[i][j],tablica[i][j],0);
            }
        }
    }

    @Test
    public void get() throws Exception {
        Matrix macierz = new Matrix(tablica);
        assertEquals(macierz.get(3,2),9,0);
    }

    @Test
    public void set() throws Exception {
        Matrix macierz = new Matrix(tablica);
        macierz.set(3,2,38.8);
        assertEquals(macierz.get(3,2),38.8,0);
    }

    @Test
    public void testtoString() throws Exception {
        Matrix macierz = new Matrix(tablica);
        Map<Character,Integer> mapa = new HashMap<Character, Integer>();
        mapa.put('[',0);
        mapa.put(',',0);
        mapa.put(']',0);
        char znak;
        for(int i=0 ; i<macierz.toString().length() ; ++i){
            znak = macierz.toString().charAt(i);
            if(mapa.containsKey(znak))
                mapa.put(znak,mapa.get(znak)+1);
        }
        assertEquals(mapa.get('['),5,0);
        assertEquals(mapa.get(']'),5,0);
    }

    @Test(expected = RuntimeException.class)
    public void reshape() throws Exception {
        Matrix macierz = new Matrix(3,4);
        macierz.reshape(10,2);
    }

    @Test
    public void addSingleNumber() throws Exception {
        Matrix macierz = new Matrix(tablica);
        for(int i=0 ; i<macierz.data.length ; i++)
            assertEquals(macierz.data[i]+10.5,macierz.add(10.5).data[i],0.01);
    }

    @Test
    public void subSingleNumber() throws Exception {
        Matrix macierz = new Matrix(tablica);
        macierz = macierz.sub(macierz);
        assertEquals(macierz.frobenius(),0.0,0.0);
    }

    @Test
    public void mulBySingleNumber() throws Exception {
        Matrix macierz = new Matrix(tablica);
        Matrix macierz2 = macierz.mul(-1);
        macierz = macierz.add(macierz2);
        assertEquals(macierz.frobenius(),0,0);
    }

    @Test
    public void divBySingleNumber() throws Exception {
        Matrix macierz = new Matrix(tablica);
        for(int i=0 ; i<macierz.data.length ; i++)
            assertEquals(macierz.data[i]*0.1,macierz.div(10).data[i],0.01);
    }


    @Test
    public void dot() throws Exception {
        Matrix mat1 = new Matrix(tablica);
        double[][] tab2 = new double[][] {{4,8},{3,2},{2,1}};
        Matrix mat2= new Matrix(tab2);
        double[][] tab3 = new double[][] {{16,15},{52,72},{63,60},{52,61}};
        Matrix result = new Matrix(tab3);
        for(int i=0 ; i<result.data.length ; i++)
            assertEquals(mat1.dot(mat2).data[i],result.data[i],0.01);
    }

    @Test(expected = RuntimeException.class)
    public void div() throws Exception {
        Matrix macierz = new Matrix(tablica);
        macierz = macierz.div(macierz);
        for(int i=0 ; i<macierz.data.length ; i++)
            assertEquals(macierz.data[i],1,0);
    }

    @Test
    public void addMatrix()throws Exception{
        Matrix mat1 = new Matrix(tablica);
        double[][] tab2 = new double[][] {{1,1,1},{1,1,1},{1,1,1},{1,1,1}};
        Matrix mat2 = new Matrix(tab2);
        Matrix result = mat1.add(mat2);
        for(int i=0 ; i<result.data.length ; i++)
            assertEquals(result.data[i],mat1.data[i]+1,0);
    }

    @Test
    public void subMatrix() throws Exception{
        Matrix mat1 = new Matrix(tablica);
        double[][] tab2 = new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        Matrix mat2 = new Matrix(tab2);
        Matrix result = mat1.sub(mat2);
        for (int i = 0; i < result.data.length; i++)
            assertEquals(result.data[i], mat1.data[i] - 1, 0);
    }

    @Test
    public void mulMatrixByMatrix() throws Exception{
        Matrix mat1 = new Matrix(tablica);
        double[][] tab2 = new double[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        Matrix mat2 = new Matrix(tab2);
        Matrix result = mat1.mul(mat2);
        for (int i = 0; i < result.data.length; i++)
            assertEquals(result.data[i], mat1.data[i]*2, 0);
    }

    @Test
    public void getTransposition()throws Exception{
        /*metoda get i set nie jest zmieniona i nadal jako pierwszy element macierzy przyjmuje (1,1), a nie (0,0).
        Obiecuje ze na nastepny raz to zmienie*/
        Random generator = new Random();
        int m = generator.nextInt(100);
        int n = generator.nextInt(100);
        Matrix matrixTest = new Matrix(m,n);
        for(int i=0 ; i<m ; i++){
            for(int j = 0 ; j<n ; j++)
                matrixTest.set(i+1,j+1, generator.nextDouble()*10);
        }

        assertEquals(matrixTest.rows,matrixTest.getTransposition().cols,0);
        assertEquals(matrixTest.cols,matrixTest.getTransposition().rows);
        for(int i=0 ; i<matrixTest.data.length ; i++){
            assertEquals(matrixTest.data[i],matrixTest.getTransposition().getTransposition().data[i],0);
        }

        Matrix multiMatrix = matrixTest.getTransposition().dot(matrixTest);
        double res1 = 0;
        double res2 = 0;
        for(int i=0 ; i<matrixTest.data.length ; i++){
            res1 = res1 + matrixTest.data[i] * matrixTest.data[i];
        }
        for(int i=0 ; i<multiMatrix.rows ; i++){
            res2 = res2 + multiMatrix.get(i+1,i+1);
        }

        assertEquals(res1,res2,0.001);
    }

    }