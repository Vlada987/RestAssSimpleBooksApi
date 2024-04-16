package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import endpoints.Methods;
import io.restassured.response.Response;
import pojo.Order;
import pojo.User;

public class TestClass {
	
String namesExpected="[The Russian, Just as I Am, The Vanishing Half, The Midnight Library, Untamed, Viscount Who Loved Me]";	
	
	
	
//@Test(description="Testing the statusCode for endpoint with all books")
public void testGetAllBooks() {
	
Response r=Methods.getAllBooks();
r.then().log().all();
String names=r.jsonPath().get("name").toString();
System.out.println("#NAMES OF BOOKS#  "+names);
String ids=r.jsonPath().get("id").toString();
int idsLenght=ids.length();
String []idsArray= ids.split("");
String lastNum=idsArray[idsLenght-2];
System.out.println("#LAST ID NUMBER IN LIST#  "+lastNum);
int lastNumInt=Integer.valueOf(lastNum);
int statusCode=r.getStatusCode();
Assert.assertEquals(statusCode, 200);
Assert.assertEquals(lastNumInt, 6);
Assert.assertEquals(names,namesExpected); 

}



//@Test(description="API Authentication test,and presence of Bearer token")
public void testAutenticateAndGetToken() {

Faker f=new Faker();
User user=new User();
user.setClientName(f.name().firstName());
user.setClientEmail(f.internet().emailAddress());
Response r=Methods.ApiAuthentication(user);
r.then().log().all();
String token=r.jsonPath().get("accessToken").toString();
int statusCode=r.getStatusCode();
int tokenLenght=token.length();
Assert.assertEquals(statusCode, 201);
Assert.assertEquals(tokenLenght, 64);
}


@Test(description="Order the Book test")
public void orderTheBook() {
	
User user=new User();
Order order=new Order(); 
Faker f =new Faker();
user.setClientName(f.name().firstName());
user.setClientEmail(f.internet().emailAddress());
order.setBookId(1);
order.setCustomerName(user.getClientName());
Response res=Methods.orderAbook(user, order);
res.then().log().all();
String orderID=res.jsonPath().get("orderId").toString();
Boolean orderIDb=(!orderID.isEmpty());
Boolean orderCreated=res.jsonPath().get("created");
Assert.assertEquals(orderCreated, true);
Assert.assertEquals(orderIDb, true);

}

}
