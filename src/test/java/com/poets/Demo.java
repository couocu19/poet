package com.poets;


public class Demo {

    public static void main(String[] args) {
        //System.out.println(initChinese());

        Demo d = new Demo();
        int max = 30;
        int min = 1;
        int ran = (int)(min+Math.random()*(max-min));
       // String phone = "18634587269";
       // System.out.println(phone.substring(7,11));
//        System.out.println(ran);
//        System.out.println(Integer.MAX_VALUE);

        System.out.println(d.getGrades(2));

    }

    private int getGrades(int sum){
        if(sum == 0)
            return 1;
        int s = sum*5;
        int a;
        int i;
        for(i =1;;i++){
            a = 5*i*(i+1);
            if(s==a) {
                i++;
                break;
            }
            if(a>s)
                break;
        }
        return (i);
    }


}
