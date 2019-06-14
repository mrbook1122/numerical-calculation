package com;

public class ThreeYangtiao {
    public static void main(String[] args) {
        double[][] nums = {
                {-1, 0, 1, 2},
                {0, 0.5, 2, 1.5}
        };
        double bian1 = 0.5, bian2 = -0.5;
        double[] hs = new double[nums[0].length - 1];
        for (int i = 0; i < hs.length; i++) {
            hs[i] = nums[0][i + 1] - nums[0][i];
        }
        double[] us = new double[nums[0].length - 1];
        for (int i = 1; i < us.length; i++) {
            us[i] = hs[i - 1] / (hs[i - 1] + hs[i]);
        }
        double[] rs = new double[nums[0].length - 1];
        for (int i = 1; i < rs.length; i++) {
            rs[i] = 1 - us[i];
        }
        double[] ds = new double[nums[0].length];
        for (int i = 1; i < ds.length - 1; i++) {
            double a = 6 / (hs[i - 1] + hs[i]);
            double b = (nums[1][i + 1] - nums[1][i]) / hs[i] - (nums[1][i] - nums[1][i - 1]) / hs[i - 1];
            ds[i] = a * b;
        }

        //边界条件1
        ds[0] = (6 / hs[0]) * ((nums[1][1] - nums[1][0]) / hs[0] - bian1);
        ds[ds.length - 1] = (6 / hs[ds.length - 2]) * (bian2 - (nums[1][nums[1].length - 1] -
                nums[1][nums[1].length - 2]) / hs[ds.length - 2]);
        double[][] num = {
                {2, 1, 0, 0, ds[0]},
                {us[1], 2, rs[1], 0, ds[1]},
                {0, us[2], 2, rs[2], ds[2]},
                {0, 0, 1, 2, ds[3]}
        };
        double[] ms = zhui(num);

//        for (int i = 0; i < ms.length; i++) {
//            System.out.println(ms[i]);
//        }

        double x = 0.5;
        double result = 0;
        if (x > nums[0][1] && x < nums[0][2]) {
            double i = (1 / (6 * hs[1])) * (ms[2] - ms[1]) * x * x * x;
            double j = (1 / (2 * hs[1])) * (ms[1] * nums[0][2] - ms[2] * nums[0][1]) * x * x;
            double k = ((1 / (2 * hs[1])) * (ms[2] * nums[0][1] * nums[0][1] -
                    ms[1] * nums[0][2] * nums[0][2]) + (1 / hs[1]) * (nums[1][2] - nums[1][1]) +
                    (hs[1] / 6) * (ms[1] - ms[2])) * x;
            double m = (1 / (6 * hs[1])) * (ms[1] * nums[0][2] * nums[0][2] * nums[0][2]
                    - ms[2] * nums[0][1] * nums[0][1] * nums[0][1]) + (1 / hs[1]) * (nums[1][1] *
                    nums[0][2] - nums[1][2] * nums[0][1]) + (hs[1] / 6) * (nums[0][1] * ms[2] -
                    nums[0][2] * ms[1]);
            result = i + j + k + m;
        }
        System.out.println(result);

    }

    private static double[] zhui(double[][] nums) {
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
        return xs;
    }
}
