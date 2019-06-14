package com;

public class Lage {
    public static void main(String[] args) {
        double[][] nums = {
                {1.1275, 1.1503, 1.1735, 1.1972},
                {0.1191, 0.13954, 0.15932, 0.17903}
        };
        double y = 0;
        double x = 1.13;
        for (int i = 0; i < nums[0].length; i++) {
            double t = 1;
            for (int j = 0; j < nums[0].length; j++) {
                if (j != i) {
                    t *= (x - nums[0][j]) / (nums[0][i] - nums[0][j]);
                }
            }
            y += nums[1][i] * t;
        }
        System.out.println(y);
    }
}
