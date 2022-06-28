package Entity;

import com.google.gson.JsonObject;
import Boundary.StoredFiles;

public class BookingCard extends Card{
    private static StoredFiles bookingCards = new StoredFiles("D:\\SEcuoiky\\SEthicuoiky\\bookingCards");
    private String username;
    private Integer password;
    private String email;
    private String numberPlate = "0000";
    private boolean loggedIn = false;

    private BankCard bankCard;
    private Bus bus;

    public BookingCard() {

    }

    public BookingCard(String iD, String name, String phoneNumber, Integer balance, String username, Integer password,
            String email,BankCard bankCard) {
        super(iD, name, phoneNumber, balance);
        this.bankCard = bankCard;
        this.username = username;
        this.password = password;
        this.email = email;
        bus = new Bus();
        this.loggedIn = true;   
    }

    public void logout() {
        this.setiD(null);
        this.setName(null);
        this.setPhoneNumber(null);
        this.setBalance(null);
        this.loggedIn = false;
        this.username = null;
        this.password = null;
        this.email = null;
        this.numberPlate = null;
        this.bankCard = null;
        this.bus = null;
    }

    public static JsonObject convertToJsonObject(BookingCard bookingCard){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", bookingCard.getUsername());
        jsonObject.addProperty("password", bookingCard.getPassword());
        jsonObject.addProperty("email", bookingCard.getEmail());
        jsonObject.addProperty("iD", bookingCard.getiD());
        jsonObject.addProperty("name", bookingCard.getName());
        jsonObject.addProperty("phoneNumber", bookingCard.getPhoneNumber());
        jsonObject.addProperty("balance", bookingCard.getBalance());
        jsonObject.addProperty("numberPlate", bookingCard.getNumberPlate());
        jsonObject.add("bus", Bus.convertToJsonObject(bookingCard.getBus()));
        jsonObject.add("bankCard", BankCard.convertToJsonObject(bookingCard.getBankCard()));
        return jsonObject;
    }

    public static BookingCard convertToObject(JsonObject jsonObject){
        BookingCard bookingCard = new BookingCard();
        bookingCard.setUsername(jsonObject.get("username").getAsString());
        bookingCard.setPassword(jsonObject.get("password").getAsInt());
        bookingCard.setEmail(jsonObject.get("email").getAsString());
        bookingCard.setiD(jsonObject.get("iD").getAsString());
        bookingCard.setName(jsonObject.get("name").getAsString());
        bookingCard.setPhoneNumber(jsonObject.get("phoneNumber").getAsString());
        bookingCard.setBalance(jsonObject.get("balance").getAsInt());
        bookingCard.setNumberPlate(jsonObject.get("numberPlate").getAsString());
        bookingCard.setBankCard(BankCard.convertToObject(jsonObject.get("bankCard").getAsJsonObject()));
        bookingCard.setBus(Bus.convertToObject(jsonObject.get("bus").getAsJsonObject()));
        return bookingCard;
    }

    public boolean checkBalance(){
        if(this.getBalance()>=1000000){
            return true;
        }
        return false;
    }

    public int checkTypeCard(){
        if(this.getiD().equals("123")){
            return 1;
        }else{
            return 2;
        }
    }

    public static StoredFiles getBookingCards() {
        return bookingCards;
    }

    public Bus getBus() {
        return bus;
    }
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }



    public Integer getPassword() {
        return password;
    }
    public void setPassword(Integer password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }



    public String getEmail() {
        return email;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean checkLoggedIn() {
        return loggedIn;
    }
}
