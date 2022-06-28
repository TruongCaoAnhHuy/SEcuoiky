package Boundary;

import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Control.BookingBusController;
import Entity.Bus;

public class BookingBusUI {
    private BookingBusController bookingBusController;
    private static Scanner sc = new Scanner(System.in);
    
    public BookingBusUI(BookingBusController bookingBusController) {
        this.bookingBusController = bookingBusController;
    }

    public void handleRentBike() {
        this.showListBookingBus();
        int index = bookingBusInputs();
        bookingBusController.BookingBus(index);
    }

    public int bookingBusInputs(){
        System.out.print("Enter the ID of the bus you want to book: ");
        return sc.nextInt();
    } 

    public void showListBookingBus() {
        JsonObject jsonObject = new JsonObject();
        JsonArray memory = Bus.getBuss().getAll();
        System.out.println(
                "*****************************************************************************************");
        System.out.println(
                "**                                Bus Booking List                                  **");                
        System.out.println(
                "**-------------------------------------------------------------------------------------**");
        System.out.printf("%-4s %-5s %-15s %-12s %-12s %-14s %-18s %-3s\n","**","ID","NumberPlate","Type","Status","**");
        System.out.println(
                "**-------------------------------------------------------------------------------------**");
        for (int i = 0; i < Bus.getBuss().getAll().size(); i++) {
            System.out.printf("%-4s %-6d", "**", i);
            bookingBusController.showBus(i, jsonObject, memory);
            System.out.printf("%-4s\n", "**");
        }
        System.out.println(
                "*****************************************************************************************");
    }
}
