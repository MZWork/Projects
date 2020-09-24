package com.company;

import java.util.*;
import java.util.concurrent.TimeUnit;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.interfaces.*;
import ru.pflb.mq.dummy.implementation.*;


/*import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;*/




public class Main {

    public static void main(String[] args) throws DummyException, InterruptedException {
        List<String> messageList = Arrays.asList( "Раз", "Два", "Три");
        Iterator<String> iter = messageList.iterator();
        ConnectionImpl connection = new ConnectionImpl();
        SessionImpl session = (SessionImpl) connection.createSession(true);
        DestinationImpl destination = (DestinationImpl) session.createDestination("Test1");
        ProducerImpl producer = new ProducerImpl(destination);
        while (iter.hasNext()) {
            producer.send(iter.next());
            TimeUnit.SECONDS.sleep(2);
        }
        session.close();
        connection.close();


    }
}
