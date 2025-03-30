package myforum.client.business.receivers;

import java.io.IOException;

public interface Receiver<T> {
    public void respond(T response) throws IOException;
}
