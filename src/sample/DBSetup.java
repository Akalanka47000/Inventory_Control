package sample;
import com.mongodb.DB;
import com.mongodb.MongoClient;
public class DBSetup {
    public static DB database;
    public static MongoClient mongoClient = new MongoClient("localhost", 27017);

    public static void init() {
        database = mongoClient.getDB("InventoryControl");
        database.createCollection("LoginDetails", null);
    }
    public static void initCategory() {
        database = mongoClient.getDB("InventoryControl");
        database.createCollection("Categories", null);
    }
    public static void initProductsAndStocks() {
        database = mongoClient.getDB("InventoryControl");
        database.createCollection("Product Details", null);
    }
}
