package com;

public class Zhui {
    public static void main(String[] args) {
        double[][] nums = {
                {2, -1, 0, 0, 3},
                {-1, 3, -2, 0, 1},
                {0, -2, 4, -2, 0},
                {0, 0, -3, 5, -5}
        };
        double[] ls = new double[nums.length];
        double[] us = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                ls[i] = nums[i][i];
                us[i] = nums[i][i + 1] / ls[i];
            } else {
                ls[i] = nums[i][i] - nums[i][i - 1] * us[i - 1];
                us[i] = nums[i][i + 1] / ls[i];
            }
        }

        double[] ys = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                ys[i] = nums[i][nums.length] / ls[i];
            else ys[i] = (nums[i][nums.length] - nums[i][i - 1] * ys[i - 1]) / ls[i];
        }
        double[] xs = new double[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                xs[i] = ys[i];
            } else xs[i] = ys[i] - us[i] * xs[i + 1];
        }
        for (int i = 0; i < xs.length; i++) {
            System.out.print(xs[i] + ", ");
        }
    }
}
