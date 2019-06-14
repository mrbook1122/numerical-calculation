package com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Gaosi {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("gaosi.txt");
        InputStreamReader reader = new InputStreamReader(inputStream);
        double[][] numbers = new double[][]{
                {1, 2, 3, 1},
                {5, 4, 10, 0},
                {3, -0.1, 1, 2}
        };
        double[] nums = new double[10];
        for (int m = 0; m < numbers.length - 1; m++) {
            for (int k = m; k < numbers.length; k++) {
                double d = numbers[k][k];
                int l = k;
                for (int i = k + 1; i < numbers.length; i++) {
                    if (Math.abs(numbers[i][k]) > Math.abs(d)) {
                        d = numbers[i][k];
                        l = i;
                    }
                }
                if (d == 0) {
                    System.out.println("奇异");
                    return;
                }
                if (l != k) {
                    for (int j = k; j < numbers.length; j++) {
                        double t = numbers[l][j];
                        numbers[l][j] = numbers[k][j];
                        numbers[k][j] = t;
                    }
                    double t = numbers[l][numbers[0].length - 1];
                    numbers[l][numbers[0].length - 1] = numbers[k][numbers[0].length - 1];
                    numbers[k][numbers[0].length - 1] = t;
                }
            }
            for (int i = m + 1; i < numbers.length; i++) {
                double temp = numbers[i][m] / numbers[m][m];
                for (int j = m; j < numbers[0].length; j++) {
                    numbers[i][j] = numbers[i][j] - temp * numbers[m][j];
                }
            }
        }

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (i == numbers.length - 1)
                nums[i] = numbers[i][i + 1] / numbers[i][i];
            else {
                for (int j = 0; j < numbers[0].length - 2 - i; j++) {
                    numbers[i][numbers[0].length - 1] -= numbers[i][numbers[0].length - 2 - j] * nums[numbers.length - j - 1];
                }
                nums[i] = numbers[i][numbers[0].length - 1] / numbers[i][i];
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(nums[i] + ", ");
        }
    }
}
