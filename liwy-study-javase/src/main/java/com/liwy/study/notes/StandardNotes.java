package com.liwy.study.notes;

import java.util.Date;

/**
 * Created by liwy on 2017/4/6.
 */
public class StandardNotes {
    private String title;
    private Date nowTime;

    /**
     * 对数组进行快速排序并返回数组字符串
     * @param array
     * @param l
     * @param r
     */
    public void quickSort(int[] array,int l, int r) {
        if(l<r) {
            int temp = array[l];//取出该分组的第一个数为基准数
            int i = l; //定义由左向右移动的指针
            int j = r; //定义由右向左移动的指针
            while(i<j) {//如果两个移动指针i和j没有靠近，继续交换

                //从右向左进行一次交换
                while(i<j && array[j]>=temp) { //如果j指针指向的数大于等于基准数
                    j--;  //j指针左移
                }
                if(i<j) {
                    array[i]=array[j];  //如果j指针指向的数小于基准数，则与基准数交换，以至于基准数右边的数全大于等于基准数
                    i++;//i指针右移
                }

                //从左向右进行一次交换
                while(i<j && array[i]<temp) {//如果i指针指向的数小于基准数
                    i++;//i指针右移
                }
                if(i<j) {
                    array[j]=array[i]; //如果i指针指向的数大于等于基准数，则与基准数交换，以至于基准数左边的数全小于基准数
                    j--;
                }
            }
            array[i] = temp;//经过多次交换后，使得基准数的左边都是小于他的，右边的数都是大于等于他的；这样基准数的位置就是正确的了

            //再将基准数左右两边的数分为两个数组分别进行以上的逻辑处理
            quickSort(array,l,i-1);
            quickSort(array,i+1,r);
        }
    }
}
