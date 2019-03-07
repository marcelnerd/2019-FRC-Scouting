package com.example.a2019frcscouting;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;

public class StatAnalyser {

    // This method calculates and returns the mean and standard deviation for a given column
    // the first element of the returned array is the mean, and the second element of the returned array is standard deviation
    public double[] getStats(double[] values){
        double sd = StatUtils.variance(values);
        double mean = StatUtils.mean(values);

        return new double[] {mean, sd};
    }

    public double getSisgnificance(double value, double mean, double sd){
        NormalDistribution nd = new NormalDistribution();
        double z = (value - mean) / sd;

        double p = nd.cumulativeProbability(z);

        return p;
    }

    

}