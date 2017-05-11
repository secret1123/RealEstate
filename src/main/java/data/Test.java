package data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by AnLu on
 * 2017/5/11 14:22.
 * RealEstate
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://bj.lianjia.com/ershoufang/").cookie("lianjia_uuid", "c51d30c6-4dbf-4d52-b1de-de01f6493f72").get();
        System.out.println("[");
        for (Element element : document.select("div[data-role] a[href*=ershoufang]")) {
            System.out.println("'" + element.text() + "',");
        }
        System.out.println("]");

        System.out.println("---");
        for (Element element : document.select("div[data-role] a[href*=ershoufang]")) {
            System.out.println(element.attr("href").replaceAll("(ershoufang|/)", ""));
        }
    }
}
