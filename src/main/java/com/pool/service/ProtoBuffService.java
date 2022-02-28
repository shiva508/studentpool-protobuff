package com.pool.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import com.pool.proto.Complex.ComplexMessage;
import com.pool.proto.Complex.DummyMessage;
import com.pool.proto.DayOfWeek.DayOfTheWeek;
import com.pool.proto.DayOfWeek.EnumMessage;
import com.pool.proto.Sample.SimpleMessage;

public class ProtoBuffService {
	public void verifyProtoBuff() {
		SimpleMessage.Builder builder = SimpleMessage.newBuilder();
		builder.setId(1).setName("Shiva").setIsSimple(true);
		builder.addSampleList(1).addSampleList(2).addSampleList(3).addAllSampleList(Arrays.asList(4, 5, 6));
		System.out.println(builder.toString());

		// GET SIMPLE MESSAGE
		SimpleMessage simpleMessage = builder.build();
		simpleMessage.getId();
		simpleMessage.getName();
		try {
			FileOutputStream outputStream = new FileOutputStream("simple_message.bin");
			simpleMessage.writeTo(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] messageByte = simpleMessage.toByteArray();

		try {
			FileInputStream fileInputStream = new FileInputStream("simple_message.bin");
			SimpleMessage messageFromFile = SimpleMessage.parseFrom(fileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void protoBuffEnum() {
		EnumMessage.Builder builder = EnumMessage.newBuilder();
		builder.setId(508);
		builder.setDayOfTheWeek(DayOfTheWeek.FRIDAY);
		EnumMessage enumMessage = builder.build();
		System.out.println(enumMessage.toString());
	}

	public void complexProtoBuff() {
		Integer id = 508;
		String name = "Shiva";
		DummyMessage dummyMessage = createDummyMessage(508, "Shiva");
		DummyMessage dummyMessage2 = createDummyMessage(408, "Dasari");
		ComplexMessage.Builder builder = ComplexMessage.newBuilder();
		builder.setOneDummy(dummyMessage).addAllMultiDummy(Arrays.asList(dummyMessage,dummyMessage2));
	}

	private DummyMessage createDummyMessage(Integer id, String name) {
		DummyMessage.Builder dummyBuilder = DummyMessage.newBuilder();
		DummyMessage dummyMessage = dummyBuilder.setId(id).setName(name).build();
		return dummyMessage;
	}
}
