package data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AnLu on
 * 2017/5/9 14:24.
 * RealEstate
 */
public class Spider {

    private static final String URL="http://bj.lianjia.com/ershoufang/dongcheng/pg";
    private static int counter;

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(URL).cookie("lianjia_uuid","c51d30c6-4dbf-4d52-b1de-de01f6493f72").get();
        int total = Integer.parseInt(document.select("h2[class=total fl]").first().child(0).text());
        int pages = (int)Math.ceil(total/30d);
        for (int i = 0; i < pages; i++) {
            System.out.println("page:" + (i+1));
            page(i+1);
        }
    }

    private static void page(int page){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test",true))){
            Document document = Jsoup.connect(URL+page).cookie("lianjia_uuid","c51d30c6-4dbf-4d52-b1de-de01f6493f72").get();
            Elements elements = document.select("li[class=clear]");
            for (Element element : elements) {
                String region = element.select("a[data-el=region]").first().text();
                String totalPrice = element.select("div[class=totalPrice]").first().child(0).text();
                Element houseInfoElement = element.select("div[class=houseInfo]").first();
                String houseInfo = houseInfoElement.childNode(2).toString();
                String unitPrice = element.select("div[class=unitPrice]").first().attr("data-price");
                writer.write(region+"@"+houseInfo+"@"+totalPrice+"@"+unitPrice+"\n");
                System.out.println("\t"+"counter:"+ ++counter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
