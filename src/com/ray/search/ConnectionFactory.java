package com.ray.search;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Ray on 2017/4/3.
 */
public class ConnectionFactory {
    private static TransportClient client;

    @Test
    public void testConnect() throws UnknownHostException {
        client = getClient();
        GetResponse response = client.prepareGet("client", "client1", "1").get();
        client.close();
    }

    public static TransportClient getClient() throws UnknownHostException {
        if(client == null){
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        }
        return client;
    }

    @Test
    public void testGet() throws UnknownHostException {
        client = getClient();
        GetResponse response = client.prepareGet("zhihu", "question", "2").get();
        System.out.print(response.getField("questionContent"));

        System.out.print(response.getField("url"));
    }

}
