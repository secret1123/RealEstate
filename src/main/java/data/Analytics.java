package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by AnLu on
 * 2017/5/11 09:43.
 * RealEstate
 */
public class Analytics {
    private static final String[] AREA_FILE = {
            "dongcheng",
            "xicheng",
            "chaoyang",
            "haidian",
            "fengtai",
            "shijingshan",
            "tongzhou",
            "changping",
            "daxing",
            "yizhuangkaifaqu",
            "shunyi",
            "fangshan",
            "mentougou",
            "pinggu",
//            "huairou",
            "miyun",
            "yanqing",
            "yanjiao"
    };

    private static int counter;
    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        for (String areaFileName:AREA_FILE){
            getData(areaFileName);
        }
        System.out.println(counter);
        System.out.println(set.size());
    }

    private static void getData(String areaFileName){
        List<Integer> totalPriceList = new ArrayList<>();
        List<Integer> unitPriceList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/"+areaFileName))){
            String line;
            while ((line = reader.readLine())!=null){
                if (set.contains(line)){
                    System.out.println(areaFileName+":"+line);
                }
                set.add(line);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

