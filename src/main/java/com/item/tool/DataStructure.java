package com.item.tool;

public class DataStructure {

    /**
     * 数组转稀疏数组
     * @param objects
     */
    public static String arrayToSparse(int[][] objects,int value){
//        for (Object[] obj:objects) {
//
//        }
        int sum=0;
        int[][] obj=new int[3][3];
        obj[0][0]=objects.length;
        obj[1][0]=objects[0].length;
        for (int i=0;i<objects.length;i++){
            for (int j=0;j<objects[0].length;j++){
                if (objects[i][j]!=value){
//                    1   3
                    sum++;
                    obj[0][sum]=i;
                    obj[1][sum]=j;
                    obj[2][sum]=objects[i][j];
                }
            }
        }
        obj[2][0]=sum;

        for (int[] objs:obj){
            for (int o:objs){
                System.out.print(o+"   ");
            }
            System.out.println();
        }
        return null;
    }

}
