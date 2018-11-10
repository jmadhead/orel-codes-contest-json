package controllers.contest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import play.libs.Json;
import play.mvc.Result;

public class ContestController extends play.mvc.Controller {

  private static MessageDigest md5;

  static {
    try {
      md5 = MessageDigest.getInstance("md5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public CompletableFuture<Result> test() throws NoSuchAlgorithmException, CloneNotSupportedException {
    JsonNode request = request().body().asJson();
    return CompletableFuture.supplyAsync(() -> {
      ObjectNode response = Json.newObject();
      response.set("id", request.get("id"));

      String firstName = request.get("first_name").asText();
      String lastName = request.get("last_name").asText();

      try {
        firstName += md5(firstName);
        lastName += md5(lastName);
        response.put("first_name", firstName);
        response.put("last_name", lastName);
        response.put("current_time", LocalDateTime.now().toString());
        response.put("say", "Java is best");

        return ok(response);
      } catch (CloneNotSupportedException e) {
        return internalServerError(e.getMessage());
      }
    });
  }

  private static String md5(String val) throws CloneNotSupportedException {
    return new String(((MessageDigest) md5.clone()).digest(val.getBytes()));
  }

}
