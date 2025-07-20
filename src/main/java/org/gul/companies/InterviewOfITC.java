package org.gul.companies;

public class InterviewOfITC {

    //{6,9,1,18}
    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;
        int[] array = {6, 9, 1, 18};
        //9,6,1,18
        //1,6,9,18
        int length = array.length;
        for (int i = 0; i < length; i++) {
            System.out.println("i-->"+i);
            for (int j = i + 1; j < length; j++) {
                System.out.println("i,j---->"+i+":"+j);
                if(array[i]>array[j]){
                    int temp = array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }
        for (int i : array){
            System.out.println(i);
        }

    }


}