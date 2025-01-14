/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix.expressParser;

import java.util.InputMismatchException;
import java.util.Random;

/**
 *
 * @author GBEMIRO
 */
public class Matrix {

    /**
     * The simple name used to label this Matrix object.
     *
     */
    private String name;
    /**
     * the data array used to create this Matrix object
     */
    private double array[][];
    /**
     * attribute used to compute the detMultiplier of the Matrix object.
     */
    private static double det = 0;

    /**
     *
     * @param rows The number of row in the Matrix.
     * @param cols The number of columns in the Matrix.
     */
    public Matrix(int rows, int cols) {
        this("NEW");
        array = new double[rows][cols];
    }//end constructor

    /**
     * @param name the simple name used to identify used by the user to label
     * this Matrix object.
     *
     * Assigns name unknown to the Matrix object and a 2D array that has just a
     * row and a column
     *
     */
    public Matrix(String name) {
        this.name = name;
        this.array = new double[][]{{0}};
    }

    /**
     *
     * @param array the data array used to create this Matrix object
     */
    public Matrix(double[][] array) {
        this("NEW");
        setArray(array);
    }//end constructor

    /**
     *
     * @param matrix constructs a new Matrix object having similar properties to
     * the one passed as argument, but holding no reference to its data.
     */
    public Matrix(Matrix matrix) {
        this("NEW");
        double arr[][] = new double[matrix.getRows()][matrix.getCols()];
        /* Copy the array of the Matrix object parameter into
         * a separate array object and use for the
         * Matrix object about to be created.
         * This ensures that the new Matrix object
         * has no connection to the one from which it is created
         * so that it is a true twin or duplicate of the other one.
         * The user is free to perform operations on this one without fear
         * that it will cause changes in the other one.
         *
         *
         */

        for (int row = 0; row < matrix.getRows(); row++) {
            for (int col = 0; col < matrix.getCols(); col++) {
                double val = matrix.array[row][col];
                arr[row][col] = val;
            }//end inner loop
        }//end outer loop
        this.array = arr;
    }//end constructor

    /**
     *
     * @return the number of row in this matrix object
     */
    public int getRows() {
        return array.length;
    }

    /**
     *
     * @return the number of columns in this matrix object
     */
    public int getCols() {
        return array[0].length;
    }

    /**
     *
     * @param array sets the data of this matrix
     */
    public void setArray(double[][] array) {

        if (array.length > 0) {
            this.array = new double[array.length][array[0].length];
            for (int row = 0; row < array.length; row++) {
                for (int col = 0; col < array[0].length; col++) {

                    this.array[row][col] = array[row][col];
                }//end inner loop
            }//end outer loop
        } else {
            this.array = new double[][]{{0}, {0}};
        }
    }//end method

    /**
     *
     * @return the data of this matrix
     */
    public double[][] getArray() {
        return this.array;
    }

    public double getElem(int row,int col){
        return array[row][col];
    }

    /**
     *
     * @param det the detMultiplier attribute of objects of this class
     */
    private static void setDet(double det) {
        Matrix.det = det;
    }

    /**
     *
     * @return the detMultiplier
     */
    private static double getDet() {
        return det;
    }

    /**
     *
     * @param name set's the simple name used to identify used by the user to
     * label this Matrix object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the simple name used to identify used by the user to label this
     * Matrix object.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param row The row whose contents we desire.
     * @return the contents of the row.
     */
    private double[] getRow(int row) {
        if (row < getRows()) {
            return array[row];
        }
        throw new IndexOutOfBoundsException("Bad Index, " + row);
    }//end method.

    /**
     *
     * @param row1 The first row.
     * @param row2 The second row.
     *
     * <b color='red'> BE CAREFUL!!!!<br/>
     * THIS METHOD ACTS ON THE MATRIX OBJECT ON WHICH IT IS CALLED AND MODIFIES
     * IT!
     * </b>
     *
     */
    public void swapRow(int row1, int row2) {
        for (int column = 0; column < getCols(); column++) {
            double v1 = array[row1][column];
            double v2 = array[row2][column];
            double v3 = v1;
            array[row1][column] = v2;
            array[row2][column] = v3;
        }//end for
    }//end method.

    /**
     *
     * @param col1 The first row.
     * @param col2 The second row.
     *
     * <b color='red'> BE CAREFUL!!!!<br/>
     * THIS METHOD ACTS ON THE MATRIX OBJECT ON WHICH IT IS CALLED AND MODIFIES
     * IT!
     * </b>
     *
     */
    public void swapColumn(int col1, int col2) {
        for (int row = 0; row < getRows(); row++) {
            double v1 = array[row][col1];
            double v2 = array[row][col2];
            double v3 = v1;
            array[row][col1] = v2;
            array[row][col2] = v3;
        }//end for
    }//end method.

    /**
     * Fills this matrix object with values
     */
    public void fill() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        for (int row = 0; row < getRows(); row++) {

            for (int column = 0; column < getCols(); column++) {
                array[row][column] = scanner.nextDouble();
            }
        }
    }//end method fill.

    /**
     *
     * @param matrice the matrix to be added to this one. The operation is (
     * this + matrice )
     * @return a Matrix containing the product matrix.
     */
    public Matrix add(Matrix matrice) {

        double array1[][] = matrice.array;

        double matrix[][] = new double[getRows()][getCols()];

        if (getRows() == matrice.getCols() && getCols() == matrice.getRows()) {
            for (int row = 0; row < getRows(); row++) {
                for (int column = 0; column < getCols(); column++) {
                    matrix[row][column] = (array[row][column]) + (array1[row][column]);
                }//end inner for
            }//end outer for

        }//end if
        else {
             System.out.println( "ERROR IN MATRIX INPUT!!");
        }

        return new Matrix(matrix);
    }//end method add

    /**
     *
     * @param matrice the matrix to be subtracted from this one. The operation
     * is ( this - matrice )
     * @return a Matrix containing the product matrix.
     */
    public Matrix subtract(Matrix matrice) {

        double array1[][] = matrice.array;

        double matrix[][] = new double[getRows()][getCols()];

        if (getRows() == matrice.getCols() && getCols() == matrice.getRows()) {
            for (int row = 0; row < getRows(); row++) {

                for (int column = 0; column < getCols(); column++) {

                    matrix[row][column] = (array[row][column]) - (array1[row][column]);

                }//end inner for

            }//end outer for

        }//end ifghjjk
        else {
             System.out.println( "ERROR IN MATRIX INPUT!!");
        }

        return new Matrix(matrix);
    }//end method subtract

    /**
     *
     * @param scalar the constant to be multiplied with this matrix The
     * operation is ( scalar X matrice )
     * @return an array containing the product matrix.
     */
    public Matrix scalarMultiply(double scalar) {
        double matrix[][] = new double[getRows()][getCols()];
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getCols(); column++) {
                matrix[row][column] = scalar * (array[row][column]);
            }//end inner for
        }//end outer for
        return new Matrix(matrix);
    }//end method scalarMultiply

    /**
     *
     * @param scalar the constant to be multiplied with this matrix The
     * operation is ( matrice/scalar )
     * @return an array containing the scaled matrix.
     */
    public Matrix scalarDivide(double scalar) {
        double matrix[][] = new double[getRows()][getCols()];
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getCols(); column++) {
                matrix[row][column] = (array[row][column]) / scalar;
            }//end inner for
        }//end outer for
        return new Matrix(matrix);
    }//end method scalarDivide

    /**
     *
     * The operation of matrix multiplication. For this method to run, The
     * pre-multiplying matrix must have its number of columns equal to the
     * number of row in the pre-multiplying one.
     *
     * The product matrix is one that has its number of columns equal to the
     * number of columns in the pre-multiplying matrix, and its row equal to
     * that in the post-multiplying matrix.
     *
     *
     * So if the operation is A X B = C, and A is an m X n matrix while B is an
     * r X p matrix, then r = n is a necessary condition for the operation to
     * occur. Also, C is an m X p matrix.
     *
     * @param matrice1 the matrix to be pre-multiplying the other one. The
     * operation is ( matrice1 X matrice2 )
     * @param matrice2 the post-multiplying matrix
     * @return a new Matrix object containing the product matrix of the
     * operation matrice1 X matrice2
     */
    public static Matrix multiply(Matrix matrice1, Matrix matrice2) {

        Matrix m = new Matrix(matrice1.getRows(), matrice2.getCols());

        if (matrice1.getCols() == matrice2.getRows()) {

            for (int i = 0; i < matrice1.getRows(); i++) {
                for (int row = 0; row < matrice2.getCols(); row++) {
                    double sum = 0;
                    for (int column = 0; column < matrice1.getCols(); column++) {
                        sum += matrice1.array[i][column] * matrice2.array[column][row];
                    }//end inner for

                    m.array[i][row] = sum;
                }//end outer for
            }//end outermost for

        }//end if
        else {
             System.out.println( "ERROR IN MATRIX INPUT!!");
        }

        return m;
    }

    /**
     *
     * @param matrice the matrix to be multiplied by this one. This operation
     * modifies this matrix and changes its data array into that of the product
     * matrix The operation is ( this X matrice )
     */
    public void multiply(Matrix matrice) {
        setArray(Matrix.multiply(this, matrice).array);
    }

    /**
     *
     * @param mat the matrix to raise to a given power
     * @param pow the power to raise this matrix to
     * @return the
     */
    public static Matrix pow(Matrix mat, int pow) {
        Matrix m = new Matrix(mat.array);
        for (int i = 0; i < pow - 1; i++) {
            m = Matrix.multiply(m, mat);

        }
        return m;
    }

    public static Matrix power(Matrix mat, int pow) {
        assert (pow >= 0);
        switch (pow) {
            case 0:
                return unitMatrix(mat.getRows(), mat.getCols());
            case 1:
                return mat;
            default:
                /**
                 * n=3:
                 * mul(power(mat,2),mat)---mul(mul(power(mat,1),mat),mat)--mul(
                 * mul(mat,mat),mat )---mul(mat2,mat)-->mat3
                 */
                return multiply(power(mat, pow - 1), mat);

        }

    }

    /**
     *
     * @return a unit matrix of the same dimension as this matrix object
     */
    public Matrix unitMatrix() {
        double matrix[][] = new double[getRows()][getCols()];

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getCols(); column++) {
                if (column == row) {
                    matrix[row][column] = 1;
                } else {
                    matrix[row][column] = 0;
                }

            }//end inner for loop
        }//end outer for loop
        return new Matrix(matrix);
    }//end method unitMatrix

    /**
     *
     * @param rowSize the number of row that the unit matrix will have
     * @param colSize the number of columns that the unit matrix will have
     * @return a unit matrix having number of row = rowSize and number of
     * columns=colSize.
     */
    public static Matrix unitMatrix(int rowSize, int colSize) {
        double matrix[][] = new double[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < colSize; column++) {
                if (column == row) {
                    matrix[row][column] = 1;
                } else {
                    matrix[row][column] = 0;
                }

            }//end inner for loop
        }//end outer for loop
        return new Matrix(matrix);
    }//end method unitMatrix

    /**
     *
     * @param mat the Matrix object that we wish to construct a unit matrix of
     * similar dimensions for.
     * @return a unit matrix of equal dimensions as this unit matrix.
     */
    public static Matrix unitMatrix(Matrix mat) {
        int rowSize = mat.getRows();
        int colSize = mat.getCols();
        double matrix[][] = new double[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < colSize; column++) {
                if (column == row) {
                    matrix[row][column] = 1;
                } else {
                    matrix[row][column] = 0;
                }

            }//end inner for loop
        }//end outer for loop
        return new Matrix(matrix);
    }//end method unitMatrix

    /**
     * Place the first Matrix object side by side with the second one passed as
     * argument to this method. The result is a new matrix where:
     *
     * if 3 4 5 7 5 9 mat1 = 2 3 1 and mat2 = 4 2 6 1 6 7 5 7 3 in a new matrix
     * object (mat).
     *
     *
     * e.g 3 4 5 7 5 9 2 3 1 4 2 6 1 6 7 5 7 3 A necessary condition for this
     * method to run is that the 2 objects must have an equal number of row. IF
     * THIS CONDITION IS NOT MET, THE METHOD RETURNS A ZERO MATRIX.
     *
     *
     * @param mat1 the first Matrix object
     * @param mat2 the second Matrix object that we column join with this one
     * @return a new Matrix object that contains this Matrix object placed side
     * by side with the Matrix object passed as argument.
     */
    public static Matrix columnJoin(Matrix mat1, Matrix mat2) {

        Matrix join = new Matrix(mat1.getRows(), mat1.getCols() + mat2.getCols());
        if (mat1.getRows() == mat2.getRows()) {
            int columnextender = 0;
            for (int row = 0; row < mat1.getRows(); row++) {

                for (int col = 0; col < join.getCols(); col++) {
                    if (col < mat1.getCols()) {
                        columnextender = 0;//reset to zero
                        join.array[row][col] = mat1.array[row][col];
                    } else if (col >= mat1.getCols()) {
                        join.array[row][col] = mat2.array[row][columnextender];
                        columnextender++;
                    }
                }//end inner for
            }//end outer for

        }//end if
        return join;
    }

    /**
     *
     * @param value The value to insert
     * @param row The row where the value is to be inserted.
     * @param column The column where the value is to be inserted.
     */
    public void update(double value, int row, int column) {
        if (row < getRows() && column < getCols()) {
            array[row][column] = value;
        }
    }

    /**
     * Place the first Matrix object side by side with the second one passed as
     * argument to this method. The result is a new matrix where:
     *
     * if 3 4 5 7 5 9 mat1 = 2 3 1 and mat2 = 4 2 6 1 6 7 5 7 3 in a new matrix
     * object (mat).
     *
     *
     * e.g 3 4 5 2 3 1 1 6 7 7 5 9 4 2 6 5 7 3
     *
     * A necessary condition for this method to run is that the 2 objects must
     * have an equal number of columns. IF THIS CONDITION IS NOT MET, THE METHOD
     * RETURNS A ZERO MATRIX.
     *
     * @param mat1 the first Matrix object
     * @param mat2 the second Matrix object that we row join with this one
     * @return a new Matrix object that contains the first Matrix object
     * argument placed top to bottom with the second Matrix object argument.
     */
    public static Matrix rowJoin(Matrix mat1, Matrix mat2) {

        Matrix join = new Matrix(mat1.getRows() + mat2.getRows(), mat1.getCols());
        if (mat1.getCols() == mat2.getCols()) {
            int rowextender = 0;
            for (int row = 0; row < join.getRows(); row++) {
                for (int col = 0; col < join.getCols(); col++) {
                    if (row < mat1.getRows()) {
                        join.array[row][col] = mat1.array[row][col];
                    } else if (row >= mat1.getRows()) {
                        join.array[row][col] = mat2.array[rowextender][col];
                    }

                }//end inner for
                if (row >= mat1.getRows()) {
                    rowextender++;
                }
            }//end outer for

        }//end if
        return join;
    }//end method rowJoin

    /**
     * Deletes all the specified number of columns from the Matrix object
     * starting from the end of the Matrix object
     *
     * @param column the number of columns to remove from the Matrix object.
     * This method will take the object that calls it and perform this operation
     * on it. So it modifies the Matrix object that calls it. Be careful, as
     * data will be lost.
     *
     * e.g if 3 4 5 6 7 8 9 2 1 8 1 4 7 0 A = 3 3 2 1 5 7 1
     *
     * then the call:
     *
     * A.columnDeleteFromEnd(3)
     *
     * will delete the last three columns in this object leaving:
     *
     * 3 4 5 6
     * A = 2 1 8 1
     * 3 3 2 1
     *
     *
     */
    public void columnDeleteFromEnd(int column) {
        if (column >= 0 && column <= getCols()) {
            Matrix matrix = new Matrix(this.getRows(), this.getCols() - column);
            for (int row = 0; row < getRows(); row++) {
                for (int col = 0; col < matrix.getCols(); col++) {
                    matrix.array[row][col] = this.array[row][col];
                }//end inner for
            }//end outer for
            this.setArray(matrix.array);
        } else {
             System.out.println( "COLUMN VALUE SHOULD "
                    + "RANGE FROM ZERO TO THE NUMBER OF COLUMNS IN THIS MATRIX.");
        }
    }//end method columnDeleteFromEnd

    /**
     * Deletes all the specified number of columns from the Matrix object from
     * the beginning of the Matrix object
     *
     * @param column the number of columns to remove from the Matrix object's
     * beginning. This method will take the object that calls it and perform
     * this operation on it. So it modifies the Matrix object that calls it. Be
     * careful, as data will be lost.
     *
     * e.g if 3 4 5 6 7 8 9 2 1 8 1 4 7 0 A = 3 3 2 1 5 7 1
     *
     * then the call:
     *
     * A.columnDeleteFromStart(3)
     *
     * will delete the last three columns in this object leaving:
     *
     * 6 7 8 9
     * A= 1 4 7 0 1 5 7 1
     *
     *
     */
    public void columnDeleteFromStart(int column) {
        if (column >= 0 && column <= getCols()) {
            Matrix matrix = new Matrix(this.getRows(), this.getCols() - column);
            for (int row = 0; row < getRows(); row++) {
                int counter = 0;
                for (int col = column; col < getCols(); col++, counter++) {
                    matrix.array[row][counter] = this.array[row][col];
                }//end inner for
            }//end outer for

            this.setArray(matrix.array);
        } else {
             System.out.println( "COLUMN VALUE SHOULD "
                    + "RANGE FROM ZERO TO THE NUMBER OF COLUMNS IN THIS MATRIX.");
        }
    }//end method columnDeleteFromStart

    /**
     * Deletes the specified number of row from the end of the Matrix object
     *
     * @param numOfRows the number of row to remove from the Matrix object's
     * beginning. This method will take the object that calls it and perform
     * this operation on it. So it modifies the Matrix object that calls it. Be
     * careful, as data will be lost.
     *
     * e.g if 3 4 5 6 2 1 8 1 A = 3 3 2 1 7 8 9 2 4 7 0 5 5 7 1 8 then the call:
     *
     * A.rowDeleteFromEnd(3)
     *
     * will delete the last three row in this object leaving:
     *
     *
     * 3 4 5 6
     * 2 1 8 1
     * A = 3 3 2 1
     *
     *
     */
    public void rowDeleteFromEnd(int numOfRows) {
        if (numOfRows >= 0 && numOfRows <= getRows()) {
            Matrix matrix = new Matrix(this.getRows() - numOfRows, this.getCols());
            for (int row = 0; row < matrix.getRows(); row++) {
                for (int col = 0; col < getCols(); col++) {
                    matrix.array[row][col] = this.array[row][col];
                }//end inner for

            }//end outer for

            this.setArray(matrix.array);
        } else {
             System.out.println( "NUMBER OF ROWS TO BE DELETED SHOULD "
                    + "RANGE FROM ZERO TO (AND INCLUDING) THE NUMBER OF ROWS IN THIS MATRIX.");
        }
    }//end method rowDeleteFromEnd

    /**
     * Deletes the specified number of row from the beginning of the Matrix
     * object
     *
     * @param numOfRows the number of row to remove from the Matrix object's
     * beginning. This method will take the object that calls it and perform
     * this operation on it. So it modifies the Matrix object that calls it. Be
     * careful, as data will be lost.
     *
     * e.g if 3 4 5 6 2 1 8 1 A = 3 3 2 1 7 8 9 2 4 7 0 5 5 7 1 8 then the call:
     *
     * A.rowDeleteFromStart(3)
     *
     * will delete the last three row in this object leaving:
     *
     *
     * A = 7 8 9 2
     * 4 7 0 5
     * 5 7 1 8
     *
     *
     */
    public void rowDeleteFromStart(int numOfRows) {
        if (numOfRows >= 0 && numOfRows <= getRows()) {
            Matrix matrix = new Matrix(this.getRows() - numOfRows, this.getCols());
            int counter = 0;
            for (int row = numOfRows; row < getRows(); row++, counter++) {

                for (int col = 0; col < getCols(); col++) {
                    matrix.array[counter][col] = this.array[row][col];
                }//end inner for

            }//end outer for

            this.setArray(matrix.array);
        } else {
            System.out.println("NUMBER OF ROWS TO BE DELETED SHOULD "
                    + "RANGE FROM ZERO TO (AND INCLUDING) THE NUMBER OF ROWS IN THIS MATRIX.");
        }
    }//end method rowDeleteFromStart

    /**
     *
     * @return an upper triangular matrix obtained by row reduction.
     */
    public Matrix reduceToTriangularMatrix() {

        Matrix mat = new Matrix(this);
        //Now we row-reduce.

        for (int row = 0; row < mat.getRows(); row++) {
            if (row >= this.getCols()) {
                break;
            }
            double val = mat.array[row][row];

            /**
             * The division coefficient must not be zero. If zero, search for a
             * lower row, and swap.
             */
            if (val == 0.0) {

                for (int rw = row; rw < mat.getRows(); rw++) {
                    val = mat.array[rw][row];

                    if (val != 0.0) {
                        mat.swapRow(row, rw);
                        break;
                    }//end if

                }//end for loop

                if (val == 0.0) {
                    throw new InputMismatchException("EQUATION CANNOT BE SOLVED");
                }
            }//end if

            for (int col = row; col < mat.getCols(); col++) {
                mat.array[row][col] /= val;
            }//end inner for loop

            for (int rowed = row + 1; rowed < mat.getRows(); rowed++) {
                double mul = mat.array[rowed][row];
                for (int coled = row; coled < mat.getCols(); coled++) {
                    mat.array[rowed][coled] = mat.array[rowed][coled] - mul * mat.array[row][coled];
                }//end for

            }//end for

        }//end outer for loop

        return mat;
    }//end method

    /**
     *
     * @return an upper triangular matrix obtained by row reduction.
     */
    public Matrix reduceToRowEchelonMatrix() {

        Matrix mat = new Matrix(this);
        //Now we row-reduce.

        for (int row = 0; row < mat.getRows(); row++) {
            if (row >= this.getCols()) {
                break;
            }
            double val = mat.array[row][row];

            /**
             * The division coefficient must not be zero. If zero, search for a
             * lower row, and swap.
             */
            if (val == 0.0) {

                for (int rw = row; rw < mat.getRows(); rw++) {
                    val = mat.array[rw][row];

                    if (val != 0.0) {
                        mat.swapRow(row, rw);
                        break;
                    }//end if

                }//end for loop

                if (val == 0.0) {
                    throw new InputMismatchException("EQUATION CANNOT BE SOLVED");
                }
            }//end if


            for (int rowed = row + 1; rowed < mat.getRows(); rowed++) {
                double mul = mat.array[rowed][row];
                for (int coled = row; coled < mat.getCols(); coled++) {
                    mat.array[rowed][coled] = val * mat.array[rowed][coled] - mul * mat.array[row][coled];
                }//end for

            }//end for

        }//end outer for loop

        return mat;
    }//end method

    /**
     * Used to solve a system of simultaneous equations placed in this Matrix
     * object.
     *
     * @return a Matrix object containing the solution matrix.
     */
    public Matrix solveEquation() {
        return Matrix.solveEquation(this);
    }//end method solnMatrix

    /**
     * Used to solve a system of simultaneous equations placed in the Matrix
     * object.
     *
     * @param matrix The row X row+1 matrix, containing the system of linear
     * equations
     * @return a Matrix object containing the solution matrix.
     */
    public static Matrix solveEquation(Matrix matrix) {
        Matrix solnMatrix = new Matrix(matrix.getRows(), 1);

        Matrix matrixLoader = matrix.reduceToTriangularMatrix();
        // System.out.println( matrixLoader  );

        if (matrix.getRows() == matrix.getCols() - 1) {
            //Back-Substitution Algorithm.
            double sum = 0;//summation variable
            int counter = 1;
            for (int row = matrixLoader.getRows() - 1; row >= 0; row--) {
                for (int col = row + 1; col < matrixLoader.getCols(); col++) {

                    if (col < matrixLoader.getCols() - 1) {
                        sum += (matrixLoader.array[row][col] * solnMatrix.array[col][0]);//sum the product of each coefficient and its unknown's value in the solution matrix
                    } else if (col == matrixLoader.getCols() - 1) {
                        sum = matrixLoader.array[row][col] - sum;//end of summing.Now subtract the sum from the last entry on the row
                    }
                }//end inner loop
                solnMatrix.array[matrixLoader.getRows() - counter][0] = sum / matrixLoader.array[row][row];
                counter++;// increment counter
                sum = 0;//reset sum
            }//end outer loop

        }//end if
        else {
            throw new IndexOutOfBoundsException("Invalid System Of Linear Equations");
        }//end else

        return solnMatrix;
    }//end method solnMatrix

    /**
     *
     * @return the transpose of this Matrix object. Does not modify this matrix
     * object but generates the transpose of this Matrix object as another
     * Matrix object.
     */
    public Matrix transpose() {
        double matrix[][] = new double[getCols()][getRows()];

        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                matrix[col][row] = array[row][col];
            }//end inner for

        }//end outer for

        return new Matrix(matrix);
    }//end method transpose

    /**
     *
     * @param i the row on which the element whose minor is needed lies.
     * @param j the column on which the element whose minor is needed lies.
     * @return the minor of this matrix relative to this element.
     */
    public Matrix minor(int i, int j) {

        double matrix[][] = new double[getRows() - 1][getCols() - 1];

        for (int row = 0; row < getRows(); row++) {

            for (int column = 0; column < getCols(); column++) {
                if (row < i && column < j) {
                    matrix[row][column] = array[row][column];
                } else if (row < i && column > j) {
                    matrix[row][column - 1] = array[row][column];
                } else if (row > i && column < j) {
                    matrix[row - 1][column] = array[row][column];
                } else if (row > i && column > j) {
                    matrix[row - 1][column - 1] = array[row][column];
                }
            }//end inner for

        }//end outer for

        return new Matrix(matrix);
    }//end method minor

    /**
     *
     * @return true if this Matrix object is a square matrix.
     */
    public boolean isSquareMatrix() {
        return getRows() == getCols();
    }

    /**
     *
     * @return true if this Matrix object can represent a system of equations
     * solvable by reduction to triangular form and subsequent evaluation.
     */
    public boolean isSystemOfEquations() {
        return getRows() == getCols() - 1;
    }

    /**
     *
     * @param m a 2 X 2 matrix
     * @return the detMultiplier of this matrix
     */
    private static double $2X2determinant(Matrix m) {
        return m.array[0][0] * m.array[1][1] - m.array[1][0] * m.array[0][1];
    }

    /**
     * @param m the matrix whose top row is to be multiplied by a scalar
     * Multiplies the top row of a matrix by a scalar. This is an important
     * operation during the evaluation of a detMultiplier.
     */
    private static Matrix topRowScalarMultiply(Matrix m, double scalar) {

        for (int col = 0; col < m.getCols(); col++) {
            m.array[0][col] *= scalar;
        }
        return new Matrix(m.array);
    }


    /**
     * Sarus' rule for computing determinants.
     * This technique is too slow and memory intensive for large
     * matrices..n>=10.
     * Please use the determ() instance method. It uses a O(cube_n)
     * algorithm as against this method's O(n!)
     * @param m the Matrix object whose detMultiplier is desired.
     * @return the detMultiplier of the matrix
     */
    private static double det(Matrix m) {
        //must be square matrix
        if (m.getRows() == m.getCols()) {

            if (m.getRows() == 2) {
                return $2X2determinant(m);
            }//end else
            else {
                for (int col = 0; col < m.getCols(); col++) {
                    double topRow = m.array[0][col] * Math.pow(-1, col);
                    Matrix mat = topRowScalarMultiply(m.minor(0, col), topRow);

                    if (mat.getRows() > 2) {
                        det(mat);
                    } else {
                        det += $2X2determinant(mat);
                    }

                }//end for

                return det;
            }//end else

        }//end if
        else {
            return Double.POSITIVE_INFINITY;
        }//end else

    }//end method detMultiplier

    /**
     *
     * @return the determinant of this matrix using a row reduction
     * technique.
     */
    public double determinant() {
        return this.determ();
    }

    /**
     * Fills the matrix with randomly generated values between 1 and 101.
     */
    public void randomFill() {
        Random ran = new Random();
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                array[row][col] = 1 + (double) ran.nextInt(101);

            }//end inner for

        }//end outer for

    }//end method randomFill

    /**
     * Fills the matrix with randomly generated values between 1 and n
     *
     * @param n the maximum possible size of the integer numbers generated.
     */
    public void randomFill(int n) {
        Random ran = new Random();
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                array[row][col] = 1 + ran.nextInt(n);

            }//end inner for

        }//end outer for

    }//end method randomFill

    /**
     *
     * @param matrixValue A string that is to be checked if it conforms to valid
     * syntax for representing a matrix in this software.
     * @return true if the command string is a valid matrix.e.g
     * [2,1,4:5,3,-2:4,4,5] value.
     */
    public static boolean isMatrixValue(String matrixValue) {
        MatrixValueParser matrixValueParser = new MatrixValueParser(matrixValue);

        boolean isValid = matrixValueParser.isValid();

        return isValid;
    }

    /**
     *
     * @return a string representation of the matrix in row and columns.
     */
    @Override
    public String toString() {
        String output = "\n";
        String appender = "";
        for (int row = 0; row < getRows(); row++) {

            for (int column = 0; column < getCols(); column++) {

                if (column < getCols()) {
                    appender += String.format("%7s%3s", array[row][column], ",");
                }
                if (column == getCols() - 1) {
                    appender = appender.substring(0, appender.length() - 1);
                    appender += "          \n";
                }
                output += appender;
                appender = "";
            }
        }

        return output;
    }//end method toString

    /**
     *
     * @param row The row in this Matrix object to be converted into a new
     * Matrix object. This operation generates a new Matrix object which is a
     * row matrix.
     */
    public Matrix getRowMatrix(int row) {
        double[][] arr = new double[1][getCols()];
        for (int col = 0; col < getCols(); col++) {
            arr[0][col] = array[row][col];
        }//end for
        return new Matrix(arr);
    }

    /**
     *
     * @param column The column to be converted into a new Matrix object. This
     * operation generates a new Matrix object which is a column matrix.
     */
    public Matrix getColumnMatrix(int column) {
        double[][] arr = new double[getRows()][1];
        for (int row = 0; row < getRows(); row++) {
            arr[row][0] = array[row][column];
        }//end for
        return new Matrix(arr);
    }

    /**
     * Computes the inverse of this Matrix object. This technique should never
     * be used in practise as it is a mere proof of concept. It computes the
     * inverse by computing the roots of the equations and then reverse
     * engineering the form A.x = B to get the inverse matrix.
     *
     * @return the inverse of the Matrix as another Matrix object.
     */
    public Matrix oldInverse() {

        Matrix m = new Matrix(this);
        Matrix unit = m.unitMatrix();
        Matrix inverse = new Matrix(new double[m.getRows()][m.getCols()]);
        if (m.isSquareMatrix()) {
            for (int rows = 0; rows < m.getCols(); rows++) {
                Matrix c = Matrix.columnJoin(m, unit.getColumnMatrix(rows));
                inverse = Matrix.columnJoin(inverse, c.solveEquation());
            }//end for
        }//end if
        inverse.columnDeleteFromStart(m.getRows());
        return inverse;
    }

    /**
     * Row reduction technique used to compute the inverse of the matrix. Always
     * use this technique.
     *
     * @return the inverse of the Matrix as another Matrix object.
     */
    public Matrix inverse() {

        Matrix m = new Matrix(this);
        if (m.isSquareMatrix()) {
            Matrix unit = m.unitMatrix();
            Matrix inverse = Matrix.columnJoin(this, unit);

            int rows = inverse.getRows();
            int cols = inverse.getCols();
            for (int row = 0; row < rows; row++) {

                double pivot = inverse.array[row][row];

                /**
                 * The division coefficient must not be zero. If zero, search
                 * for a lower row, and swap.
                 */
                if (pivot == 0.0) {

                    for (int rw = row; rw < rows; rw++) {
                        pivot = inverse.array[rw][row];

                        if (pivot != 0.0) {
                            inverse.swapRow(row, rw);
                            break;
                        }//end if

                    }//end for loop

                    if (pivot == 0.0) {
                        throw new InputMismatchException("INVERSE DOES NOT EXISTS!");
                    }
                }//end if   

                for (int col = row; col < cols; col++) {
                    inverse.array[row][col] /= pivot;
                }//end inner for loop

                for (int rw = row + 1; rw < rows; rw++) {
                    double newRowMultiplier = -1 * inverse.array[rw][row];
                    for (int col = row; col < cols; col++) {
                        inverse.array[rw][col] = newRowMultiplier * inverse.array[row][col] + inverse.array[rw][col];
                    }
                }//end inner for loop

            }//end for
            //////////////Now reduce upwards from the right border of the main matrix...on the partition between the 2 matrices.

            for (int row = rows - 1; row >= 0; row--) {

                for (int rw = row - 1; rw >= 0; rw--) {

                    double newRowMultiplier = -1 * inverse.array[rw][row];
                    /**
                     * The division coefficient must not be zero. If zero,
                     * search for a lower row, and swap.
                     */
                    if (newRowMultiplier == 0.0) {
                        continue;
                    }//end if  
                    for (int col = row; col < cols; col++) {
                        inverse.array[rw][col] = newRowMultiplier * inverse.array[row][col] + inverse.array[rw][col];
                    }
                }

            }//end for

            inverse.columnDeleteFromStart(m.getRows());
            return inverse;
        }//end if
        return null;
    }

    /**
     * Row reduction technique used to compute the determinant of this matrix.
     * The other method using recursion is not worth it above n = 10;
     * The memory consumed by the process and the time used to compute it is 
     * incomparable to this method's performance.
     *
     * @return the inverse of the Matrix as another Matrix object.
     */
    public double determ() {

        double detMultiplier = 1;


        Matrix mat= new Matrix(this);
        //Now we row-reduce.
        int rows = mat.getRows();
        int cols = mat.getCols();


        if (rows == cols) {
            for (int row = 0; row < rows; row++) {

                double pivot = mat.array[row][row];

                /**
                 * The division coefficient must not be zero. If zero, search
                 * for a lower row, and swap.
                 */
                if (pivot == 0.0) {

                    for (int rw = row; rw < rows; rw++) {
                        pivot = mat.array[rw][row];

                        if (pivot != 0.0) {
                            mat.swapRow(row, rw);
                            detMultiplier *= -1;
                            break;
                        }//end if

                    }//end for loop

                    if (pivot == 0.0) {
                        throw new InputMismatchException("INVERSE DOES NOT EXISTS!");
                    }
                }//end if   
                for (int col = row; col < cols; col++) {
                    mat.array[row][col] /= pivot;
                }//end inner for loop
                detMultiplier*=pivot;
                for (int rw = row + 1; rw < rows; rw++) {
                    double newRowMultiplier = -1 * mat.array[rw][row];
                    for (int col = row; col < cols; col++) {
                        mat.array[rw][col] = newRowMultiplier * mat.array[row][col] + mat.array[rw][col];
                    }

                }//end inner for loop

            }//end for

            for(int row=0;row<rows;row++){
                detMultiplier *= mat.array[row][row];
            }//end for

        }

        return detMultiplier;
    }

    /**
     *
     * @param args The command line args
     */
    public static void main(String... args) {

        Matrix m1 = new Matrix(4, 4);
        double array[][] = {{1, -8, 2, 5}, {4, 8, 2, 4}, {6, 5, 2, 1}, {2, 1, 6, 8}};
        m1.array = array;
        System.out.printf("Matrix: %s\nDeterminant:\n %f\n", m1.toString(), det(m1));
        Matrix triMatrix = m1.reduceToRowEchelonMatrix();

        System.out.printf("Echelon-matrix of above: %s\n", triMatrix.toString());

        int rows = 11;
        int cols = 11;
        Matrix m = new Matrix(rows, cols);
        m.randomFill(20);

        //System.out.println("Matrix: \n" + m);
        System.out.println("Processing begins.");

        double t0 = System.nanoTime();

        double det = det(m);// 

        double t1 = System.nanoTime() - t0;
        System.out.printf("Old method for determinant gives %4f in %4f %2s \n", det, (t1 * 1.0E-6), "ms");

        double t2 = System.nanoTime();

        double det_1 = m.determ();
        double t3 = System.nanoTime() - t2;

        System.out.printf("New method for determinant gives %4f in %4f %2s \n", det_1, (t3 * 1.0E-6), "ms"); 
        
        
/*
        Matrix m1 = new Matrix( new double[][]{{5, -6, 8, 9}, {3,1,0,6}, {2,10,4,5}, {16,12,2,4}});

        System.out.println("--------------------------Matrix:\n" + m1);
        System.out.println("Inverse: " + m1.inverse());

        Matrix m2 = new Matrix(2, 2);
        m2.randomFill(20);

        Matrix m3 = new Matrix(m2);
        System.out.println("OLD INVERSE METHOD ");

        System.out.println("NEW MATRIX: " + m2);

        Matrix m4 = m2.oldInverse();
        System.out.println("INVERSE: " + m4);

        System.out.println("Product using old method: M X 1/M: " + Matrix.multiply(m2, m4));

        System.out.println("NEW INVERSE METHOD ");

        System.out.println("NEW MATRIX: " + m3);

        Matrix m5 = m3.inverse();
        System.out.println("INVERSE: " + m5);

        System.out.println("Product using new method: M X 1/M: " + Matrix.multiply(m3, m5));*/

        /**
         * double t0 = System.nanoTime(); Matrix m = new Matrix(3024,3025);
         * double t1 = System.nanoTime() - t0; System.out.println( "Creating the
         * matrix in "+ (t1 * 1.0E-6)+" ms" ); t0 = System.nanoTime();
         * m.randomFill(22000); t1 = System.nanoTime() - t0;
         *
         * System.out.println( "Filling the matrix in "+ (t1 * 1.0E-6)+" ms" );
         * //System.out.print( m );
         *
         * t0 = System.nanoTime(); Matrix soln = m.solveEquation(); t1 =
         * System.nanoTime() - t0; System.out.print( "Solved the matrix in "+
         * (t1 * 1.0E-6)+" ms" ); //System.out.print( soln );
         *
         * //Matrix a = new
         * MatrixValueParser("[2,4,5:3,9.939,45.2:1,4,2:]").getMatrix();
         * //Matrix b = new
         * MatrixValueParser("[-2,-4,-5:-3,-9.939,-45.2:-1,-4,-2:]").getMatrix();
         *
         * //System.out.print( Matrix.pow(a, 5) );
         */
    }

}//end class Matrix
