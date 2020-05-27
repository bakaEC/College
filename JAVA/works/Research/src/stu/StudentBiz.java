package stu;

public class StudentBiz {

    String []names = new String[30];

    public void addName(String name){
        for(int i =0 ;i< names.length; i++) {
            if(names[i]==null){
                names[i] = name;
                break;
            }
        }
    }

    public void showNames(){
        System.out.println("本班学生：");
        for (int i = 0; i < names.length; i++) {
            if(names[i] != null ){
                System.out.print(names[i]+"\t");
            }
        }
    }

    public boolean searchName(int start ,int end , String name){
        boolean find = false;
        for (int i = start; i <= end; i++) {
            if (names[i].equals(name)) {
                find = true;
                break;
            }
        }
        return find;
    }
    
}