package Entity;

import com.google.gson.JsonObject;

import Boundary.StoredFiles;

public class Bus {
    private static StoredFiles buss = new StoredFiles("D:\\SEcuoiky\\SEthicuoiky\\buss.json");
    private String numberPlate = "0000";
    private String route;
    private String color;
    private String manufacturer;
    private String status = "Ready"; //   Sẵn sàng/Đang cho thuê

    public Bus(){

    }


    public Bus(String numberPlate, String route, String color, String manufacturer, String status) {
        this.numberPlate = numberPlate;
        this.route = route;
        this.color = color;
        this.manufacturer = manufacturer;
    }

    public void showInfor(){
        System.out.printf("%-15s %-12s %-12s %-14s %-18s ",this.numberPlate,this.route,this.color,this.manufacturer,this.status);
    }

    public static JsonObject convertToJsonObject(Bus bus){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("numberPlate", bus.getNumberPlate());
        jsonObject.addProperty("route", bus.getRoute());
        jsonObject.addProperty("status", bus.checkStatus());
        return jsonObject;
    }

    public static Bus convertToObject(JsonObject jsonObject){
        Bus bus = new Bus();
        bus.setNumberPlate(jsonObject.get("numberPlate").getAsString());
        bus.setStatus(jsonObject.get("status").getAsString());
        if(bus.getNumberPlate().equals("0000") == false){
            bus.setRoute(jsonObject.get("route").getAsString());
        }
        return bus;
    }

    public static StoredFiles getBuss() {
        return buss;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String checkStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
