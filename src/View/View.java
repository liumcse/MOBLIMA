package View;

/**
 * This is the base class for all other View classes.
 */
public abstract class View {
    /** The {@code View} before this. */
    public View prevView;  // NULL by default

    /** This method is the first method to be called when transition using intent. */
    protected abstract void start();

    /** This method is to destroy the current {@code View} by going back to the start of the last {@code View}. */
    protected void destroy() {
        if (prevView == null) System.exit(1);  // exit when nowhere to return
        else prevView.start();
    }

    /**
     * This method is used to transit from one {@code View} to another.
     * @param v the prevView of u
     * @param u the {@code View} to be started
     */
    protected void intent(View v, View u) {
        u.prevView = v;
        u.start();
    }

    /**
     * This method is to get the last {@code View} before this.
     * @return the last {@code View}
     */
    protected View getPrevView() {
        return prevView;
    }
}
