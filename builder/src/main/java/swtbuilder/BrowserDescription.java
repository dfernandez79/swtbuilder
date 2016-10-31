package swtbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;

public class BrowserDescription extends AbstractControlDescription<BrowserDescription, Browser> {
    private String url;
    private final List<EventListenerLambda<ProgressEvent, Browser>> progressCompletionListeners =
            new ArrayList<>();
    private final Map<String, BrowserFunctionLambda> functions = new HashMap<>();

    public BrowserDescription() {
        super(Browser::new);
    }

    @Override
    protected void setUpControl(Browser control, ControlRefs refs) {
        if (url != null) {
            control.setUrl(url);
        }

        progressCompletionListeners.forEach(listener -> control.addProgressListener(new ProgressAdapter() {
            @Override
            public void completed(ProgressEvent event) {
                listener.handleEvent(event, control, refs);
            }
        }));

        for (Map.Entry<String, BrowserFunctionLambda> entry : functions.entrySet()) {
            new BrowserFunction(control, entry.getKey()) {
                @Override
                public Object function(Object[] arguments) {
                    return entry.getValue().apply(arguments);
                }
            };
        }
    }

    public BrowserDescription onCompleted(EventListenerLambda<ProgressEvent, Browser> handler) {
        progressCompletionListeners.add(handler);
        return this;
    }

    public BrowserDescription onCompleted(BiConsumerEventListenerLambda<ProgressEvent, Browser> handler) {
        progressCompletionListeners.add(handler);
        return this;
    }

    public BrowserDescription onCompleted(ConsumerEventListenerLambda<ProgressEvent, Browser> handler) {
        progressCompletionListeners.add(handler);
        return this;
    }

    public BrowserDescription onCompleted(NoArgsEventListenerLambda<ProgressEvent, Browser> handler) {
        progressCompletionListeners.add(handler);
        return this;
    }

    public BrowserDescription executeOnCompleted(String js) {
        return onCompleted((evt, browser) -> browser.execute(js));
    }

    public BrowserDescription function(String name, BrowserFunctionLambda fn) {
        functions.put(name, fn);
        return this;
    }

    public BrowserDescription url(String url) {
        this.url = url;
        return this;
    }
}
