import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class avengers {


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C://webdriver//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String sat = "https://in.bookmyshow.com/buytickets/avengers-endgame-bengaluru/movie-bang-ET00100668-MT/20190427#!seatlayout";
        String sun = "https://in.bookmyshow.com/buytickets/avengers-endgame-bengaluru/movie-bang-ET00100668-MT/20190428#!seatlayout";
        driver.get(sat);
        boolean check = false;

        outloop:
        while (true)
        {
            if (check)
            {
                driver.navigate().to(sat);
                check=false;
            }
            else
            {
                driver.navigate().to(sun);
                check=true;
            }
            int size = driver.findElements(By.xpath("//div[@class='listing-info']")).size();
            if (size>0)
            {
                for (WebElement element1:driver.findElements(By.xpath("//div[@class='listing-info']")))
                {
                    new Actions(driver).moveToElement(element1);
                    String venue = element1.getText();
                    if(venue.contains("PVR") && venue.contains("Koramangala"))
                        break outloop;
                }
            }

            if (size>5)
                break;

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //driver.navigate().refresh();
        }

        System.out.println("tickets r out");
        final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.hand");
        while(true) {
            if (runnable != null) runnable.run();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
