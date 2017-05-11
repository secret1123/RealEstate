package data;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by AnLu on
 * 2017/5/9 14:24.
 * RealEstate
 */
public class Spider implements Runnable {

    private static final String URL = "http://bj.lianjia.com/ershoufang/";
    private String areaName;

    public Spider(String areaName) {
        this.areaName = areaName;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(URL).cookie("lianjia_uuid", "c51d30c6-4dbf-4d52-b1de-de01f6493f72").get();
        Elements elements = document.select("div[data-role=ershoufang]").first().select("a");
        for (Element element : elements) {
            String areaName = element.attr("href").replaceAll("(ershoufang|/)", "");
            Thread thread = new Thread(new Spider(areaName));
            thread.start();
        }
    }

    @Override
    public void run() {
        try {
            Document areaDocument = Jsoup.connect(URL.concat(areaName)).cookie("lianjia_uuid", "c51d30c6-4dbf-4d52-b1de-de01f6493f72").get();
            int totalHouses = Integer.parseInt(areaDocument.select("h2[class*=total fl]").first().child(0).text());
            int pages = (int) Math.ceil(totalHouses / 30d);
            for (int i = 0; i < pages; i++) {
                page(areaName, i + 1);
                System.out.println(areaName + "\t\tpage:" + (i + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void page(String areaName, int page) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/bj/" + areaName, true))) {
            Document document = Jsoup.connect(URL.concat(areaName) + "/pg" + page).cookie("lianjia_uuid", "c51d30c6-4dbf-4d52-b1de-de01f6493f72").get();
            Elements elements = document.select("li[class=clear]");
            for (Element element : elements) {
                String imageUrl = element.childNode(0).attr("href");
                String id = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
                String region = element.select("a[data-el=region]").first().text();
                String totalPrice = element.select("div[class=totalPrice]").first().child(0).text();
                Element houseInfoElement = element.select("div[class=houseInfo]").first();
                String houseInfo = houseInfoElement.childNode(2).toString();
                String unitPrice = element.select("div[class=unitPrice]").first().attr("data-price");
                writer.write(id + "@" + region + "@" + houseInfo + "@" + totalPrice + "@" + unitPrice + "\n");
            }
        } catch (SocketTimeoutException e) {
            System.out.println("---socket time out:" + areaName + ",page:" + page);
            page(areaName, page);
        } catch (HttpStatusException e) {
            System.out.println("---http status code:" + areaName + ",page:" + page);
            page(areaName, page);
        } catch (IOException e) {
            System.out.println("---io exception:" + areaName + ",page:" + page);
            page(areaName, page);
        }
    }
}
