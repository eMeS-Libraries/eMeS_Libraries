package lv.emes.libraries.communication.tcp_ip.server;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.net.SocketException;
import java.util.Random;

/**
 * Contains common operations for incoming message handling. Used in <b>MS_TcpServerCore</b>.
 *
 * @author eMeS
 * @version 1.2.
 * @see MS_TcpIPServerCore
 */
class MS_MessageHandler implements Runnable {

    //	static Logger log = Logger.getLogger(MS_MessageHandler.class.getName());
    private final int DEFAULT_THREAD_SLEEP_TIME = 250;
    private MS_ClientOfServer client;
    private MS_TcpIPServerCore server;

    /**
     * Saves references of linked client and server sockets.
     * Also sets I/O handlers so in run()
     *
     * @param client client that is connected to server.
     * @param server TCP/IP server.
     */
    public MS_MessageHandler(MS_ClientOfServer client, MS_TcpIPServerCore server) {
        this.client = client;
        this.server = server;
    }

    /**
     * This methods serves for listening of client messages to server.
     * It includes common responses to each client that are defined in <b>server.onIncomingClientMessage</b>.
     * <p>Method ends work only if server is stopped or client is disconnected.
     */
    @Override
    public void run() {
        while (server.isRunning() && client.isConnected()) {
            //Every message is read as UTF-8 String.
            try {
                Thread.sleep(new Random().nextInt(DEFAULT_THREAD_SLEEP_TIME));
                String msg = client.getIn().readUTF();
                server.onIncomingClientMessage(msg, client); //calls central method of server message reading
            } catch (SocketException e) {
                client.disconnect();
            } catch (UTFDataFormatException exc) {
                if (server.onDataFormatException != null)
                    try {
                        server.onDataFormatException.doOnEvent(exc);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            } catch (IOException exc) {
                if (server.onIOException != null)
                    try {
                        server.onIOException.doOnEvent(exc);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                client.disconnect();
            } catch (Exception e) { //if another exception then just exit thread
                server.getClients().remove(client.id);
                return;
            }
        } //while ends here
        server.getClients().remove(client.id);
        try {
            client.disconnect();
        } catch (Exception ignored) {
        }
    }

}
