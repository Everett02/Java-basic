package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrator {
    public static void startAdmin(){
        ArrayList<Student> students = new ArrayList<>();
        loop: while (true) {
            System.out.println("-----------Welcome to the system----------");
            System.out.println("1.Add a student");
            System.out.println("2.Delete a student");
            System.out.println("3.Modify a student");
            System.out.println("4.Search students");
            System.out.println("5.Quit");
            System.out.println("Choose your option:");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose){
                case "1" -> addStudent(students);
                case "2" -> deleteStudent(students);
                case "3" -> modifyStudent(students);
                case "4" -> searchStudent(students);
                case "5" -> {
                    System.out.println("Quit");
                    break loop;
                }
                default -> System.out.println("Wrong input");
            }
        }
    }

    // addStudent
    public static void addStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        String id = null;
        while (true){
            System.out.println("Please type in student id:");
            id = sc.next();
            if (contains(list, id)){
                System.out.println("Id exists, please type in again:");
            }else {break;}
        }
        System.out.println("Please type in student name:");
        String name = sc.next();
        System.out.println("Please type in student age:");
        int age = sc.nextInt();
        System.out.println("Please type in student address:");
        String address = sc.next();
        Student s = new Student(id, name, age, address);
        list.add(s);
        System.out.println("Add student successful.");
    }

    // deleteStudent
    public static void deleteStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in id to be deleted");
        String id = sc.next();
        int index = getIndex(list, id);
        if (index >= 0){list.remove(index);
            System.out.println("id:" + id + "deleted");
        }else{
            System.out.println("Id not exists, deletion fails");
        }
    }

    // modifyStudent
    public static void modifyStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in id to modify:");
        String id = sc.next();
        int index = getIndex(list, id);
        if(index == -1){
            System.out.println("id:" + "not exists, please type in again");
            return;
        }
        Student stu = list.get(index);
        System.out.println("Please type in modified name:");
        String newName = sc.next();
        stu.setName(newName);
        System.out.println("Please type in modified age:");
        String newAge = sc.next();
        stu.setName(newAge);
        System.out.println("Please type in modified address:");
        String newAddress = sc.next();
        stu.setName(newAddress);
        System.out.println("Modification succeed");
    }

    // searchStudent
    public static void searchStudent(ArrayList<Student> list){
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
        }
    }

    //If id exist
    public static boolean contains(ArrayList<Student> list, String id){
//        for (int i = 0; i < list.size(); i++) {
//            Student stu = list.get(i);
//            String sid = stu.getId();
//            if (sid.equals(id)){
//                return true;
//            }
//        }
//        return false;
        return getIndex(list, id) >= 0;
    }
    public static int getIndex(ArrayList<Student> list, String id){
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String sid = stu.getId();
            if(sid.equals(id)){
                return i;
            }
        }
        return -1;
    }
}
