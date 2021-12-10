package crickBuzz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CrickBuzzz {
@Test
public void crick() {
	WebDriver driver =new ChromeDriver();
	driver.get("https://www.cricbuzz.com/live-cricket-scorecard/36332/aus-vs-eng-1st-test-the-ashes-2021-22");
	String score = driver.findElement(By.xpath("//div[.='Bowler' and @class='cb-col cb-col-40 text-bold']/ancestor::div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr']/descendant::div[@class='cb-col cb-col-8 text-right text-bold']")).getText();
	System.out.println(score);
}
}
