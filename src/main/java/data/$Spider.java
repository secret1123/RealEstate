package data;

/**
 * Created by AnLu on
 * 2017/5/10 09:08.
 * RealEstate
 */
public class $Spider {
    private static final String[] AREA = {
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
    private static final int[] PAGES = {
            35,
            37,
            100,
            100,
            70,
            21,
            47,
            94,
            42,
            6,
            19,
            14,
            14,
            1,
//            0,
            1,
            1,
            100
    };

//    private static final String proxy_ip="192.168.31.132";
//    private static final int proxy_port=;
    private static final String LIANJIA_URL="http://bj.lianjia.com/ershoufang/";
    private static final String REGEXP="region\">([\u4e00-\u9fbb]+)</a>([0-9\u4e00-\u9fbb|\\s.]+)</div>[a-zA-Z0-9\u4e00-\u9fbb\\s=\"<>/_:().-]+totalPrice\"><span>(\\d+)[a-zA-Z0-9\u4e07\\s=\"<>/+data-price=\"(\\d+)";
//    private static final String UA = "Mozilla/5.0(Macintosh;Inter Mac"
    private static final String COOKIE = "all-lj=c28812af28ef34a41ba2474a2b5c52c2; lianjia_uuid=c51d30c6-4dbf-4d52-b1de-de01f6493f72; select_city=110000; introduce=1; select_nation=1; _smt_uid=590d1cf1.16e59611; lianjia_ssid=4ef8e226-c2cd-42ce-b3d0-261e64381254";
    private static int counter;

    public static void main(String[] args) {
        for (int i = 0; i < AREA.length; i++) {
            counter=0;
            System.out.println("area:"+AREA[i]);
            for (int j = 0; j < PAGES[i]; j++) {
                System.out.println("\t page:"+(j+1));

            }
        }
    }
}
