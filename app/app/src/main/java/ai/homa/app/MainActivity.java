package ai.homa.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                String js =
                    "(function() {" +
                    "  function hideBase44() {" +
                    "    var elements = document.querySelectorAll('*');" +
                    "    for (var i = 0; i < elements.length; i++) {" +
                    "      var el = elements[i];" +
                    "      var text = (el.innerText || '').trim();" +
                    "      if (text === 'Edit with Base44' || text === 'Edit with\\nBase44') {" +
                    "        el.style.display = 'none';" +
                    "      }" +
                    "    }" +
                    "  }" +
                    "  hideBase44();" +
                    "  var observer = new MutationObserver(hideBase44);" +
                    "  observer.observe(document.documentElement, {childList:true, subtree:true});" +
                    "})();";

                view.evaluateJavascript(js, null);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl("https://capable-aria-chat-flow.base44.app");

        setContentView(webView);
    }
}
