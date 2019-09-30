package com.youxiu326;

public class BisearchTest {

    public static void main(String[] args) {

        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11};


    }

    //二分查找法(折半查找法)
    public static int halfSearch(int[] arr,int number){
        int min =0;  //最小下标
        int max =arr.length-1;   //最大下标
        int mid = 0;  //中间下标
        while (min<max){
            //没找到,更新范围继续找
            mid = (min+max)/2;
            if (arr[mid]>number){   //number在mid的左边
                max = mid-1;  //改变最大下标
            }else if(arr[mid]<number){  //number在mid的右边
                min = mid+1;  //改变最小下标
            }else{
                return  mid;
            }
        }
        return -1;
    }


} 