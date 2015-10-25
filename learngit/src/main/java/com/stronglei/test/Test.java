package com.stronglei.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		ByteBuf bb = Unpooled.buffer();
		
		System.out.println(bb.retain());
		System.out.println(bb.capacity());
		System.out.println(bb);
		bb.writeByte(5);
		bb.writeInt(5);
		byte a = bb.readByte();
		System.out.println(bb);
		System.out.println(a);
	}

}
