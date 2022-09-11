package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Student admin system");
            System.out.println("Please choose your operation: 1.Sign in 2.Sign Up 3.Forget Password? 4.Quit");
            String choose = sc.next();
            switch (choose){
                case "1" -> signIn(list);
                case "2" -> signUp(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("Thanks for using, goodbye");
                    System.exit(0);
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void forgetPassword(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in username");
        String username = sc.next();
        boolean flag = contains(list, username);
        if (!flag) {
            System.out.println("Username is not registered.");
            return;
        }
        System.out.println("Please type in registerID");
        String registerID = sc.next();
        System.out.println("Please type in your phonenumber");
        String phoneNumber = sc.next();

        int index = findIndex(list, username);
        User user = list.get(index);
        if(!(user.getRegisterID().equalsIgnoreCase(registerID) && user.getPhoneNumber().equals(phoneNumber))){
            System.out.println("Some information is wrong, cannot proceed the request");
            return;
        }
        String password;
        while (true) {
            System.out.println("Please type in new password");
            password = sc.next();
            System.out.println("Please type in new password again");
            String againPassword = sc.next();
            if(password.equals(againPassword)){
                System.out.println("Success");
                break;
            }else{
                System.out.println("Password not matched please type in again");
            }
        }
        user.setPassword(password);
        System.out.println("Password changed");

    }

    private static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    private static void signUp(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        String registerID;
        String phoneNumber;
        while (true) {
            System.out.println("Please type in Username");
            username = sc.next();
            boolean flag1 = checkUsername(username);
            if(!flag1){
                System.out.println("Username invalid, please type again:");
                continue;
            }
            boolean flag2 = contains(list, username);
            if (flag2){
                System.out.println("Username repeat, please type again:");
            }else {
                System.out.println("Username is valid");
                break;
            }
        }
        while (true) {
            System.out.println("Please type in password:");
            password = sc.next();
            System.out.println("Please type in password again");
            String password2 = sc.next();
            if (!password.equals(password2)){
                System.out.println("Password not match, please type again");
            }else {
                System.out.println("Password is valid");
                break;
            }
        }
        while (true) {
            System.out.println("Please type in invitation number");
            registerID = sc.next();
            boolean flag = checkregisterID(registerID);
            if (flag){
                System.out.println("RegisterID is valid");
                break;
            }else {
                System.out.println("RegisterID is not valid");
            }
        }
        while (true) {
            System.out.println("Please type in phone number");
            phoneNumber = sc.next();
            boolean flag = checkPhoneNummber(phoneNumber);
            if (flag){
                System.out.println("Phone Number is valid");
                break;
            }else {
                System.out.println("Phone Number is not valid");
            }
        }
        User u = new User(username, password, registerID, phoneNumber);
        list.add(u);
        System.out.println("Sign Up is successful");
    }

    private static boolean checkPhoneNummber(String phoneNumber) {
        return phoneNumber.matches("[1-9][0-9]{2}-[0-9]{3}-[0-9]{4}");
    }

    private static boolean checkregisterID(String registerID) {
        return registerID.matches("[1-9][0-9]{1,17}");
    }

    private static boolean contains(ArrayList<User> list, String username) {
        for (User user : list) {
            String rightUsername = user.getUsername();
            if (rightUsername.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkUsername(String username){
        return username.matches("[a-zA-Z]\\w{2,14}");
    }

    private static String getCode(){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add(((char)('A' + i)));
        }
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
        int number = r.nextInt(10);
        sb.append(number);
        char[] arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        return new String(arr);
    }

    private static void signIn(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in username");
        String username = sc.next();
        boolean flag = contains(list, username);
        if(!flag){
            System.out.println("Username is not valid, please sign up first");
            return;
        }
        System.out.println("Please type in password");
        String password = sc.next();
        while (true) {
            String random_code = getCode();
            System.out.println("Verification code is " + random_code);
            System.out.println("Please type in verification code");
            String code = sc.next();
            if (code.equalsIgnoreCase(random_code)){
                System.out.println("Verification code matches");
                break;
            }else{
                System.out.println("Code is not matched");
            }
        }
        User userInfo = new User(username, password, null, null);
        boolean result = checkUserInfo(list, userInfo);
        if (result){
            System.out.println("Success");
            Administrator a = new Administrator();
            a.startAdmin();
        }else{
            System.out.println("Fail, please check username or password");
        }
    }

    private static boolean checkUserInfo(ArrayList<User> list, User userInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(userInfo.getUsername()) && user.getPassword().equals(userInfo.getPassword())){
                return true;
            }
        }
        return false;
    }
}
