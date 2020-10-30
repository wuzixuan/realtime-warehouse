package com.zixuan.others;

public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组
        int chessArr1[][] = new int[6][7];
        chessArr1[0][3] = 22;
        chessArr1[0][6] = 15;
        chessArr1[1][1] = 11;
        chessArr1[1][5] = 17;
        chessArr1[2][3] = -6;
        chessArr1[3][5] = 39;
        chessArr1[4][0] = 91;
        chessArr1[5][2] = 28;
        // 输出原始的二维数组
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //1.遍历原始数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组,包含四列
        // 第一列存储数据是第几个非0数据；
        // 第二列存储该数据在原始数组中的行号
        // 第三列存储该数据在原始数据中的列号
        // 第四列存储该数据的值
        int sparseArr[][] = new int[sum + 1][3];

        // 给第0行赋值，记录原始数组的行、列、非0数据个数
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        System.out.println("原始行："+chessArr1.length +" 原始列：" + chessArr1[0].length);

        // 遍历二维数组，将非0的值存储到 sparseArr中
        // count 用于记录是该数据是第几个非0数据
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("得到稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();



        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //2. 读取稀疏数组除第[0]行外的所有数据，并赋给第一步中创建的数组。
        for(int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //打印测试
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
