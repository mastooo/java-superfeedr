import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPConnection;
import org.superfeedr.OnNotificationHandler;
import org.superfeedr.Superfeedr;
import org.superfeedr.onSubUnsubscriptionHandler;
import org.superfeedr.extension.notification.ItemExtension;
import org.superfeedr.extension.notification.SuperfeedrEventExtension;

/*
Small Sample/example that you can start to use to track Superfeedr.
*/
class ExampleProgram {
    public static void main(final String[] args) throws Exception {
        
        List<URL> urls = new ArrayList<URL>();
        // List of urls you want to track
        urls.add(new URL("http://superfeedr.com/dummy.xml"));
        urls.add(new URL("http://blog.superfeedr.com/atom.xml"));
        urls.add(new URL("http://twitter.com/statuses/user_timeline/43417156.rss"));
        
        // Debugging will display a window that allows you to see incoming/outcoming traffic
        XMPPConnection.DEBUG_ENABLED = true;
        // Use your Superfeedr credentials
        Superfeedr feedr = new Superfeedr("loggin", "password", "superfeedr.com");
        
        // Adds notification Handler. The onNotification method will be called with a SuperfeedrEventExtension object.
        feedr.addOnNotificationHandler(new OnNotificationHandler() {
            public void onNotification(final SuperfeedrEventExtension event) {
                // Displays the feed url, the HTTP status and the Log. (See Superfeedr Doc for more info)
                System.err.println(event.getStatus().getFeedURL());
                System.err.println(event.getStatus().getHttpExtension().getCode());
                System.err.println(event.getStatus().getHttpExtension().getInfo());
                // And now the items
                if (event.getItems().getItemsCount() == 0) {
                    System.out.println("No items");
                } else
                // For each item
                for (ItemExtension item : event.getItems().getItems()) {
                    // Display it's title and link
                    System.out.println(item.getEntry().getTitle());
                    System.out.println(item.getEntry().getLink());
                }
                System.out.println("\n\n");
            }
        });
        
        // Actually perform the subscription, and calls the second argument when performed
        // You can obviously do 'unsubscribe' too.
        feedr.subscribe(urls, new OnSubUnsubscriptionHandler() {
            public void onSubUnsubscription() {
                // Called upon valid subscription/unsubscription.
            }
            public void onError(String infos) {
                // Called when error
                System.err.println(infos);
            }
        });
        
        while (true) {
            Thread.sleep(1000);
        }
    }
}