package stu;

public class Customers {
    String[] names = new String[30];

    public void addName(String name) {
        for (int i = 0; i < names.length - 1; i++) {
            if (names[i] == null) {
                names[i] = name;
                break;
            }
        }
    }

    public void showNames() {
        System.out.println("用户名：");
        for (int i = 0; i < names.length - 1; i++) {
            if (names[i] != null) {
                System.out.print(names[i] + "\t");
            } else {
                break;
            }
        }
    }

    public boolean editName(String oldName, String newName) {
        boolean find = false;
        for (int i = 0; i < names.length - 1; i++) {
            if (names[i].equals(oldName)) {
                names[i] = newName;
                find = true;
                break;
            }
        }
        return find;
    }

    public boolean searchName(int start, int end, String name) {
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
