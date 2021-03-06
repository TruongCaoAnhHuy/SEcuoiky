package Boundary;

import Control.LoginController;

public class LogoutUI {
    private LoginController loginController;

    public LogoutUI(LoginController loginController) {
        this.loginController = loginController;
    }
    
    public void handleLogout() throws InterruptedException {
        System.out.println("Logout...");
        Thread.sleep(1500);
        loginController.logOut();
        System.out.println("[LOGGED_OUT] You have been logged out");
    }
}
