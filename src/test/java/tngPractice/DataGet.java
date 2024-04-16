package tngPractice;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

public class DataGet {
	
@DataProvider(name="getData")
public String[] dataGet() {
	
String names[]=new String[50];
for(int a=0;a<50;a++) {
Faker f=new Faker();
String name=f.beer().name();
names[a]=name;
}

return names;
}
}
