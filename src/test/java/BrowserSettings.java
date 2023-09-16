import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserSettings {

    public BrowserSettings() {
        Configuration.remote = "http://192.168.1.4:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("enableVNC", true);
        // desiredCapabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = desiredCapabilities;

    }

}
