# Vaadin Detect UI still present

This example uses the @Push functionality to asynchronously detect if a UI is still responsive. Vaadin has not inbuilt function to achieve this as there is no reliable way to detect a browser UI close event. But we can check that a screen is there before we need to use it.

This method injects java script as a RPC that simple triggers an @Callable. This forces a round trip from the server -> UI -> server. It is then up to the program to supply the functionality to manage the potential network latency issues / debouncing.

The UIReferenceManger is session scoped. The browser to close connects to the session scoped bean and the browser for monitoring allows triggering of the detection function then reading the result back asynchronously.