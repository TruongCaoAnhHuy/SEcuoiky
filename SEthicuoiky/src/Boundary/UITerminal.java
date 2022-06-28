package Boundary;

import java.util.Scanner;

import Control.DepositController;
import Control.LoginController;
import Control.RefundCardController;
import Control.BookingBusController;

public class UITerminal {
    Scanner sc = new Scanner(System.in);
    LoginBookingCardUI loginRentalCardUI;
    LogoutUI logoutUI;
    RegisterUI registerUI;
    DepositUI depositUI;
    BookingBusUI bookingBusUI;
    RefundUI refundUI;

    public void systemBookingBus() throws InterruptedException {
        System.out.println("Welcome to the System Booking BusSmart!!");
        int choice;
        int choice1;
        do {
            Thread.sleep(1500);
            this.displayOptions(1);
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case (1):
                    loginRentalCardUI = new LoginBookingCardUI();
                    loginRentalCardUI.handleLogin();
                    while (loginRentalCardUI.getLoginController().getBookingCards().checkLoggedIn()) {
                        Thread.sleep(1500);
                        this.displayOptions(2);
                        System.out.print("Your choice: ");
                        choice1 = sc.nextInt();
                        switch (choice1) {
                            case (1):
                                depositUI = new DepositUI(
                                        new DepositController(loginRentalCardUI.getLoginController().getBookingCards()));
                                depositUI.handleDeposit();
                                break;
                            case (2):
                                bookingBusUI = new BookingBusUI(new BookingBusController(loginRentalCardUI.getLoginController().getBookingCards()));
                                bookingBusUI.handleRentBike();
                                break;
                            case (3):
                                refundUI = new RefundUI(new RefundCardController(
                                        loginRentalCardUI.getLoginController().getBookingCards()));
                                if (refundUI.handleRefund() == 0) {
                                    break;
                                }
                            case (0):
                                logoutUI = new LogoutUI(
                                        new LoginController(loginRentalCardUI.getLoginController().getBookingCards()));
                                logoutUI.handleLogout();
                                sc.nextLine();
                                break;
                            default:
                                System.out.println("Unknow command.");
                                break;
                        }

                    }
                    break;
                case (2):
                    registerUI = new RegisterUI();
                    registerUI.handleRegister();
                    break;
                case (0):
                    System.out.println("Exit the program successfully!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknow command.");
            }

        } while (choice != 0);
        System.out.println("hghgghg");
    }

    private void displayOptions(int i) {
        if (i == 1) {
            System.out.println("============Main Screen=============");
            System.out.println("== 1. Login                       ==");
            System.out.println("== 2. Register Card               ==");
            System.out.println("== 0. Exit                        ==");
        } else {
            System.out.println("================Menu================");
            System.out.println("== 1. Deposit                     ==");
            System.out.println("== 2. Booking Bus                 ==");
            System.out.println("== 3. Refund Card                 ==");
            System.out.println("== 0. Logout                      ==");

        }
        System.out.println("====================================");
    }
}
