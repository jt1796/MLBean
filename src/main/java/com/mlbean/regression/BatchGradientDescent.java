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
package com.mlbean.regression;

import com.mlbean.dataObjects.DataRow;
import com.mlbean.dataObjects.DataSet;
import com.mlbean.mathObjects.Vector;

/**
 *
 * @author John
 */
public class BatchGradientDescent extends Regression {
    
    public BatchGradientDescent(DataSet dataSet, int iterations, double stepSize) {
        super(dataSet, iterations, stepSize);
    }
    
    public void execute() {
        double coeff_zero = 0;
        double[] t_coeff = new double[dataSet.getDataWidth() - 1];
        for(int i = 0; i < iterations; i++) {
            double newCoeffZero = 0;
            double[] newCoeff = new double[t_coeff.length];
            double sum = 0;
            for(DataRow dataRow : dataSet) {
                double label = dataRow.getLabel().getNumericValue();
                sum += stepSize * (modDotProduct(coeff_zero, t_coeff, dataRow) - label);
            }
            newCoeffZero = coeff_zero - sum;
            for(int omega = 0; omega < t_coeff.length; omega++) {
                sum = 0;
                for(DataRow dataRow : dataSet) {
                    double label = dataRow.getLabel().getNumericValue();
                    sum += stepSize * (modDotProduct(coeff_zero, t_coeff, dataRow) - label) * dataRow.getNonLabel(omega).getNumericValue();
                }
                newCoeff[omega] = t_coeff[omega] - sum;
            }
            coeff_zero = newCoeffZero;
            t_coeff = newCoeff;
        }
        double[] fullCoeffs = new double[dataSet.getDataWidth()];
        fullCoeffs[0] = coeff_zero;
        for(int i = 1; i < fullCoeffs.length; i++) {
            fullCoeffs[i] = t_coeff[i - 1];
        }
        coeff = new Vector(fullCoeffs.length, fullCoeffs);
    }
}
