/*
 * Copyright (c) 2016, John
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.mlbean.mathObjects;

import org.junit.Test;
import static org.junit.Assert.*;
import com.mlbean.testObjects.*;

/**
 *
 * @author John
 */
public class MatrixTest {

    @Test
    public void createTwoByThreeArray() {
        double[] testData = {1, 2, 3, 4, 5, 6};
        Matrix testMat = new Matrix(2, 3, testData);
        assertEquals(1.0, testMat.get(0,0), 0.01);
        assertEquals(2.0, testMat.get(0,1), 0.01);
        assertEquals(3.0, testMat.get(0,2), 0.01);
        assertEquals(4.0, testMat.get(1,0), 0.01);
        assertEquals(5.0, testMat.get(1,1), 0.01);
        assertEquals(6.0, testMat.get(1,2), 0.01);
        assertEquals(3, testMat.getWidth());
        assertEquals(2, testMat.getHeight());
    }
    
    @Test
    public void createTwoByThreeDoubleArray() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}};
        Matrix testMat = new Matrix(testData);
        assertEquals(1.0, testMat.get(0,0), 0.01);
        assertEquals(2.0, testMat.get(0,1), 0.01);
        assertEquals(3.0, testMat.get(0,2), 0.01);
        assertEquals(4.0, testMat.get(1,0), 0.01);
        assertEquals(5.0, testMat.get(1,1), 0.01);
        assertEquals(6.0, testMat.get(1,2), 0.01);
        assertEquals(3, testMat.getWidth());
        assertEquals(2, testMat.getHeight());
    }
    
    @Test
    public void transpostTwoByThree() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}};
        Matrix testMat = new Matrix(testData);
        testMat = testMat.transpose();
        assertEquals(3, testMat.getHeight());
        assertEquals(2, testMat.getWidth());        
        assertEquals(1.0, testMat.get(0,0), 0.01);
        assertEquals(2.0, testMat.get(1,0), 0.01);
        assertEquals(3.0, testMat.get(2,0), 0.01);
        assertEquals(4.0, testMat.get(0,1), 0.01);
        assertEquals(5.0, testMat.get(1,1), 0.01);
        assertEquals(6.0, testMat.get(2,1), 0.01);
    }
    
    @Test
    public void testScalarMultiplication() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}};
        Matrix testMat = new Matrix(testData);
        testMat = testMat.multiply(7);
        assertEquals(7.0, testMat.get(0,0), 0.01);
        assertEquals(14.0, testMat.get(0,1), 0.01);
        assertEquals(21.0, testMat.get(0,2), 0.01);
        assertEquals(28.0, testMat.get(1,0), 0.01);
        assertEquals(35.0, testMat.get(1,1), 0.01);
        assertEquals(42.0, testMat.get(1,2), 0.01);
    }
    
    @Test
    public void testMatrixMultiplication() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}};
        Matrix testMat = new Matrix(testData);
        double[][] testDataTwo = {{1, 2}, {3, 4}, {5, 6}};
        Matrix testMatTwo = new Matrix(testDataTwo);
        Matrix product = testMat.multiply(testMatTwo);
        assertEquals(2, product.getHeight());
        assertEquals(2, product.getWidth());
        assertEquals(8 + 20 + 36, product.get(1, 1), 0.01);
    }
    
    @Test
    public void testMatrixMultiplicationTwo() {
        double[][] testData = {{1.4534883720930234, -0.19767441860465124}, {-0.1976744186046512, 0.03488372093023257}};
        double[][] testDataTwo = {{1.0, 1.0, 1.0}, {3.0, 4.0, 10.0}};
        double[][] prodData = {{0.860465116279070,0.662790697674418,-0.523255813953489},{-0.0930232558139535,-0.0581395348837209,0.151162790697674}};
        assertEquals(new Matrix(prodData), new Matrix(testData).multiply(new Matrix(testDataTwo)));
    }
    
    @Test
    public void testTrace() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix testMat = new Matrix(testData);
        assertEquals(1 + 5 + 9, testMat.trace(), 0.01);
    }
    
    @Test
    public void rowEchelon() {
        double[][] testData = {{1, 7, 1},{2, 4, 3},{7, 1, 4}};
        Matrix testMat = new Matrix(testData);
        testMat = testMat.rowEchelonForm();
        double[][] trueData = {{1, 7, 1}, {0, 1, -0.1}, {0, 0, 1}};
        Matrix trueMat = new Matrix(trueData);
        assertEquals(testMat, trueMat);
    }
    
    @Test
    public void reducedRowEchelon() {
        double[][] testData = {{1, 7, 1}, {0, 1, -0.1}, {0, 0, 1}};
        Matrix testMat = new Matrix(testData);
        testMat = testMat.reducedRowEchelonForm();
        double[][] trueData = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        Matrix trueMat = new Matrix(trueData);
        assertEquals(testMat, trueMat);
    }
    
    @Test
    public void testInverse() {
        double[][] testData = {{1, 3, 3},{1, 4, 3},{1, 3, 4}};
        Matrix testMat = new Matrix(testData);
        double[][] trueData = {{7, -3, -3}, {-1, 1, 0}, {-1, 0, 1}};
        Matrix trueMat = new Matrix(trueData);
        assertEquals(testMat.inverse(), trueMat);
    }
    
    @Test
    public void testEqualsWhenEqual() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] testDataTwo = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix mat = new Matrix(testData);
        Matrix matTwo = new Matrix(testDataTwo);
        assertEquals(mat, matTwo);
    }
    
    @Test
    public void testEqualsWhenDimMismatch() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] testDataTwo = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {3, 4, 5}};
        Matrix mat = new Matrix(testData);
        Matrix matTwo = new Matrix(testDataTwo);
        assertFalse(mat.equals(matTwo));
    }
    
    @Test
    public void testEqualsWhenNotEqual() {
        double[][] testData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] testDataTwo = {{1, 1, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix mat = new Matrix(testData);
        Matrix matTwo = new Matrix(testDataTwo);
        assertFalse(mat.equals(matTwo));
    }
    
    @Test
    public void testEmptyMatrixMult() {
        assert(mathObjects.emptyMatrix.multiply(mathObjects.emptyMatrix).equals(mathObjects.emptyMatrix));
    }
    
    @Test
    public void testNegativesAndFloats() {
        Matrix result = mathObjects.floatsAndNegatives.multiply(mathObjects.floatsAndNegatives);
        Matrix actual = new Matrix(new double[][]{{3.1290468e9,-9.093927e10},{-3.441191e6,3.22902908e9}});
    }
}
