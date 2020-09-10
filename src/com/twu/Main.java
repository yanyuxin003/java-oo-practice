package com.twu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.controller.ManagerController;
import com.controller.UserController;
import com.pojo.Events;


public class Main {
    public static void main(String[] args) {
        List<Events>eventList = new ArrayList<Events>();
        choose_function(eventList);
    }

    /*
    *选择功能
    */
    public static void choose_function(List<Events> eventList) {
        UserController userController = new UserController();
        ManagerController managerController = new ManagerController();
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎来到热搜排行榜，你是？\n1.用户\n2.管理员\n3.退出");
        int Tag = sc.nextInt();
        switch(Tag){
            case 1:
                userController.UserRoot(eventList);
                break;
            case 2:
                managerController.managerRoot(eventList);
                break;
            case 3:
                break;
        }

    }

}
