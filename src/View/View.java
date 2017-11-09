package View;

public abstract class View {
    protected View prevView;  // NULL by default

    protected abstract void start();

    protected void destroy() {
        if (prevView == null) System.exit(1);  // exit when nowhere to return
        else prevView.start();
    }

    protected void intent(View v, View u) {
        u.prevView = v;
        u.start();
    }
}
