package com.gysoft.jdbc.test.bean;

import com.gysoft.jdbc.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 矩阵类
 *
 * @author 周宁
 * @Date 2019-05-28 16:31
 */
@Data
@Table(name = "tb_arr")
public class Matrix implements Serializable {

    private double[][] arr;
    private int row;
    private int column;

    /**
     * 构造函数
     *
     * @param arr
     */
    public Matrix(double[][] arr) {
        this.arr = arr;
        //列数
        column = arr[0].length;
        //行数
        row = arr.length;
        for (int i = 0; i < this.row; i++) {
            if (arr[i].length != this.column) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.setLength(sb.length() - 1);
            sb.append(System.getProperty("line.separator"));
        }
        System.out.println();
        return sb.toString();
    }

    /**
     * 获取指定位置的数值对应矩阵中的aij
     *
     * @param i
     * @param j
     * @return double
     */
    public double get(int i, int j) {
        if (i >= 0 && i < row) {
            if (j >= 0 && j < column) {
                return arr[i][j];
            } else {
                throw new IndexOutOfBoundsException("j=" + j);

            }
        } else {
            throw new IndexOutOfBoundsException("i=" + i);
        }
    }

    /**
     * 行数
     *
     * @return int
     */
    public int rowDimension() {
        return row;
    }

    /**
     * 列数
     *
     * @return int
     */
    public int columnDimension() {
        return column;
    }

    /**
     * 获取某一行
     *
     * @param rowNum 行数
     * @return double[]
     */
    public double[] anRow(int rowNum) {
        return arr[rowNum];
    }

    /**
     * 获取某一列
     *
     * @param columnNum 列数
     * @return double[]
     */
    public double[] anColumn(int columnNum) {
        return this.getAT().anRow(columnNum);
    }

    /**
     * 获得转秩后的矩阵
     * @return 转秩的矩阵
     */
    public Matrix getAT() {
        int h = arr.length;
        int v = arr[0].length;
        // 创建和A行和列相反的转置矩阵
        double[][] A_T = new double[v][h];
        // 根据A取得转置矩阵A_T
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < v; j++) {
                A_T[j][i] = arr[i][j];
            }
        }
        return new Matrix(A_T);
    }

    public static void main(String[] args) {
        double[][] doubles = new double[][]{{1,2,3},{4,5,6}};
        Matrix matrix = new Matrix(doubles);
        System.out.println(matrix);
        System.out.println(matrix.getAT());
        System.out.println(Arrays.toString(matrix.anColumn(1)));
    }

}
