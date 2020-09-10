package com.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.pojo.Events;
import com.pojo.Role;
import com.twu.Main;

public class UserController {
    public static void UserRoot(List<Events> eventList) {
        System.out.println("请输入您的昵称：");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Role user = new Role(name);
        UserFunction(eventList, user);
    }

    /*
    用户事件
    */
    private static void UserFunction(List<Events> eventList, Role user) {
        Scanner sc = new Scanner(System.in);
        if (eventList != null && eventList.size() != 0) {
            System.out.println("你好，"+ user.getName() +"，你可以：\n1.查看热搜排行榜\n2.给热搜事件投票\n3.购买热搜\n4.添加热搜\n5.退出");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    //查看热搜排行榜
                    check_hotSearch(eventList);
                    UserFunction(eventList, user);
                    break;
                case 2:
                    //给热搜事件投票
                    vote_hotSearch(eventList, user);
                    UserFunction(eventList, user);
                    break;
                case 3:
                    //购买热搜
                    buy_hotSearch(eventList);
                    UserFunction(eventList, user);
                    break;
                case 4:
                    //添加热搜
                    add_hotSearch(eventList, false);
                    UserFunction(eventList, user);
                    break;
                case 5:
                    //退出
                    Main.choose_function(eventList);
                    break;
            }
        }else {
            System.out.println("你好，" + user.getName() + "，暂无热搜数据，你可以：\n1.添加热搜\n2.退出");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    //添加热搜
                    add_hotSearch(eventList, false);
                    UserFunction(eventList, user);
                    break;
                case 2:
                    //退出
                    Main.choose_function(eventList);
                    break;
            }
        }
    }



    /*
    添加热搜事件
    */
    static void add_hotSearch(List<Events> eventList, boolean flag) {
        System.out.println("请输入你要添加的热搜事件的名字：");
        Scanner sc = new Scanner(System.in);
        String description = sc.nextLine();
        //判断该热搜是否已经存在
        String res = isExist(eventList,description);
        //如果存在的话不添加
        if(!res.equals("not")) {
            System.out.println("该热搜已存在于热搜列表中！");
        }else {
            //如果不存在的话添加新的热搜事件
            Events events = new Events();
            events.setRank(eventList.size());//设置热搜排名
            events.sethotSearchDescription(description);//输入热搜描述
            if(flag) {
                events.setSuperHot(true);//设计超级热搜
            }
            eventList.add(events);
            System.out.println("添加成功");
        }
    }



    /**
     * 给热搜事件投票
     */
    private static void vote_hotSearch(List<Events> eventList, Role user) {
        //输入需要投票的热搜事件
        System.out.println("请输入你要投票的热搜名称:");
        Scanner sc = new Scanner(System.in);
        String description=sc.nextLine();
        //判断该热搜是否存在
        String res = isExist(eventList,description);
        //如果热搜存在可以投票
        if(!res.equals("not")) {
            System.out.println(String.format("请输入你要投票的热搜票数：(你目前还有%d)",user.getVotes()));
            //校验投票票数,判断能投票不
            int vote = sc.nextInt();
            if(vote>user.getVotes()) {
                System.out.println("投票失败！");
            }else {
                user.setVotes(user.getVotes()-vote);
                //判断是否为超级热搜
                if(eventList.get(Integer.parseInt(res)).isSuperHot()) {
                    eventList.get(Integer.parseInt(res)).setHeat(2*vote);
                }else {
                    eventList.get(Integer.parseInt(res)).setHeat(vote);
                }
                System.out.println("投票成功！");
            }
        }else {
            System.out.println("抱歉，您输入的热搜名称不存在！\n投票失败！");
        }
    }


    /**
    购买热搜
    */
    private static void buy_hotSearch(List<Events> eventList) {
        //输入你要购买的热搜事件
        System.out.println("请输入你要购买的热搜名称:");
        Scanner sc = new Scanner(System.in);
        String description=sc.nextLine();
        //判断该热搜是否存在
        String res = isExist(eventList,description);
        if(!res.equals("not")) {
            //输入需要购买的热搜排名
            System.out.println("请输入你要购买的热搜排名：");
            int rank = sc.nextInt();
            //输入需要购买的热搜金额
            System.out.println("请输入你要购买的热搜金额：");
            int price = sc.nextInt();
            //判断该金额是否能购买到该位置
            if(price<1||eventList.get(rank-1).getPrice()>price) {
                System.out.println("购买失败");
            }else if(eventList.get(rank-1).getPrice()>0) {
                eventList.get(Integer.parseInt(res)).setPrice(price);
                eventList.set(rank-1, eventList.get(Integer.parseInt(res)));
                eventList.remove(Integer.parseInt(res));
                System.out.println("购买成功");
            }else {
                eventList.get(Integer.parseInt(res)).setPrice(price);
                hotSearchSort(eventList);
                System.out.println("购买成功");
            }
        }else {
            //如果该热搜不存在，不能购买
            System.out.println("抱歉，您输入的热搜名称不存在！\n购买失败！");
        }
    }

    /*检查热搜排行榜*/
    private static void check_hotSearch(List<Events> eventList) {
        if (eventList != null && eventList.size() > 0) {
            //给热搜事件排名
            UserController.hotSearchSort(eventList);
            //输出热搜排行榜
            for (int i = 0; i < eventList.size(); i++) {
                System.out.println(String.format("%d   %s   %d", i + 1, eventList.get(i).gethotSearchDescription(), eventList.get(i).getHeat()));
            }
        } else {
            System.out.println("抱歉，暂无热搜数据");
        }
    }


    /**
    给热搜事件排序
    */
    public static void hotSearchSort(List<Events> eventList) {
        Collections.sort(eventList, new Comparator<Events>() {
            @Override
            public int compare(Events event1, Events event2) {
                if(event1.getPrice()>event2.getPrice()) {
                    return -1;
                }else if(event1.getPrice()<event2.getPrice()) {
                    return 1;
                }else {
                    if(event1.getHeat()>event2.getHeat()) {
                        return -1;
                    }else if(event1.getHeat()<event2.getHeat()) {
                        return 1;
                    }else {
                        return 0;
                    }
                }
            }
        });
    }

    /*判断是否热搜是否存在*/
    private static String isExist(List<Events> eventList, String description) {
        boolean isExist = false;
        if(eventList!=null) {
            for(int i=0;i<eventList.size();i++) {
                if(eventList.get(i).gethotSearchDescription().equals(description)) {
                    return i+"";
                }
            }
        }
        return "not";
    }
}