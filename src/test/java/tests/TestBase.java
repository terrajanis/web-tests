package tests;

import appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;


@Listeners (MyTestListener.class)
public class TestBase {

    public static ThreadLocal<ApplicationManager> tlApp = new ThreadLocal<>();
    public ApplicationManager app;


    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        tlApp.set(app);

        app.init();
        context.setAttribute("app", app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.stop(); app = null; }));
    }
    }




