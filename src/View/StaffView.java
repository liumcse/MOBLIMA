package View;

import View.staff.Login;


public class StaffView extends View {
    /**
     * Login
     */
    @Override
    protected void start() {
        intent(this, new Login());
    }
}
