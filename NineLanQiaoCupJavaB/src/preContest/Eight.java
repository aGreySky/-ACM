package preContest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/*小明维护着一个程序员论坛。现在他收集了一份"点赞"日志，日志共有N行。其中每一行的格式是：
    ts id

    表示在ts时刻编号id的帖子收到一个"赞"。

    现在小明想统计有哪些帖子曾经是"热帖"。如果一个帖子曾在任意一个长度为D的时间段内收到不少于K个赞，小明就认为这个帖子曾是"热帖"。
    
    具体来说，如果存在某个时刻T满足该帖在[T, T+D)这段时间内(注意是左闭右开区间)收到不少于K个赞，该帖就曾是"热帖"。
    
    给定日志，请你帮助小明统计出所有曾是"热帖"的帖子编号。
    
    【输入格式】 第一行包含三个整数N、D和K。 以下N行每行一条日志，包含两个整数ts和id。
    
    对于50%的数据，1 <= K <= N <= 1000 
    对于100%的数据，1 <= K <= N <= 100000
    0 <= ts<= 100000 0 <= id <= 100000
    
    【输出格式】 按从小到大的顺序输出热帖id。每个id一行。
    
    【输入样例】 7 10 2
    0 1 
    0 10 
    10 10 
    10 1 
    9 1 
    100 3 
    100 3
    
    【输出样例】 1 3
    
    资源约定： 峰值内存消耗（含虚拟机） < 256M CPU消耗 < 1000ms
    
    请严格按要求输出，不要画蛇添足地打印类似：“请您输入...” 的多余内容。
    
    所有代码放在同一个源文件中，调试通过后，拷贝提交该源码。 不要使用package语句。不要使用jdk1.7及以上版本的特性。
主类的名字必须是：Main，否则按无效代码处理。*/
public class Eight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//帖子数
        int D = sc.nextInt();//时间间隔
        int K = sc.nextInt();//点赞数
        HashMap tieList = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int time = sc.nextInt();
            int id = sc.nextInt();
            if (tieList.containsKey(id)) {
                ArrayList item = (ArrayList) tieList.get(id);
                item.add(time);
            } else {
                ArrayList timeList = new ArrayList<>();
                timeList.add(time);
                tieList.put(id, timeList);
            }
        }

        //迭代hashMap
        Iterator it = tieList.entrySet().iterator();
        while (it.hasNext()) {
            //获得的是Map.Entry对象
            Map.Entry entry = (Entry) it.next();
            int id = (int) entry.getKey();
            ArrayList<Integer> timeList = (ArrayList) entry.getValue();
            if (timeList.size() < K) {
                continue;
            }
            Collections.sort(timeList);
            int start = 0;
            int end = timeList.size();
            while (start <= end - K) {
                if (timeList.get(start + K - 1) - timeList.get(start) < D) {
                    System.out.print(id + " ");
                    break;
                }
                start++;
            }

        }
    }
}
