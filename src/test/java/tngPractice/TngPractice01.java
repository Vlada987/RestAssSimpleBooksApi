package tngPractice;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TngPractice01 {
	
int a=1;
int b=23;
int c;


//@BeforeClass()
public void beforeM() {
	
	
}



//@Test(dataProvider="getData", dataProviderClass=DataGet.class)
public void test01(String []p) {
	
for(String p1:p) {
System.out.println("############ "+p1);	
}

}


}
