package endpoints;
import static io.restassured.RestAssured.*;

import api.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Order;
import pojo.User;

public class Methods {
	
//GET ALL BOOKS FROM THE LIST	
public static Response getAllBooks() {
	
Response r=given().get(Routes.booksURL);
return r;
}

public static Response ApiAuthentication(User user) {
	
Response r=given().contentType("application/json")
.accept(ContentType.JSON).body(user).post(Routes.authUrl);
return r;
}

public static String getBearerToken(User user) {
	
Response r=Methods.ApiAuthentication(user);
String token=r.jsonPath().get("accessToken").toString();
return token;
}

public static Response orderAbook(User user,Order order) {
	
String token=Methods.getBearerToken(user);
Response r=given().header("Authorization", "Bearer " + token). 
body(order).contentType("application/json").
accept(ContentType.JSON).post(Routes.submitOrdUrl);
return r;
	
}


}
