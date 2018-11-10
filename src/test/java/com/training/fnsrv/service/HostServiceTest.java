package com.training.fnsrv.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.training.fnsrv.model.Host;
import com.training.fnsrv.protobuff.gen.HostProto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HostServiceTest {
    @Autowired
    private HostService hostService;
    private Host testHost;
    //TODO: find better solution for this path
    private static final String modelFileName = "./src/test/java/com/training/fnsrv/service/host_test_model.json";

    @Before
    public void hostServiceTestSave() {
        Gson gson = new Gson();
        FileReader modelFile = null;

        try {
            modelFile = new FileReader(modelFileName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        JsonReader reader = new JsonReader(modelFile);
        testHost = gson.fromJson(reader, Host.class);

        hostService.save(testHost);
    }

    @Test
    public void hostServiceTestGetById() {
        boolean testFlag = false;

        for (Host host: hostService.getAll()) {
            if (host.getRequestId() == testHost.getRequestId()) {
                testFlag = true;
            }
        }

        assertEquals("Can't find saved Host object", true, testFlag);
    }

    @Test
    public void hostServiceTestProtoSerializeById() {
        HostProto.Host hostProto = hostService.protoSerializeById(testHost.getRequestId());

        Host deserializeProtoHost = hostService.protoDeserialize(hostProto);

        assertEquals("Host object is not the same after ProtoBuf serialization/deserialization",
                testHost, deserializeProtoHost);
    }

    @Test
    public void hostServiceTestProtoSerializeJsonById() {
        HostProto.Host hostProto = hostService.protoSerializeViaJsonById(testHost.getRequestId());

        Host deserializeProtoHost = hostService.protoDeserialize(hostProto);

        assertEquals("Host object is not the same after ProtoBuf serialization/deserialization",
                testHost, deserializeProtoHost);
    }

    @After
    public void hostDeleteByIdServiceTest() {
        hostService.remove(testHost);
    }
}



