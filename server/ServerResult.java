package server;

import java.io.Serializable;

public class ServerResult implements Serializable {

    public String result = "no result";

    public ServerResult(String s) {
        result = s;
    }

}
