package springbook.learningtest.spring.ioc.bean;

import java.nio.Buffer;

public class StringPrinter implements Printer{
	private StringBuffer Buffer = new StringBuffer();
	@Override
	public void print(String message) {
		this.Buffer.append(message); // Printer 인터페이스의 메소드, 내장 버퍼에 메시지를 추가 해준다.
	}
	@Override
	public String toString() {
		return super.toString(); // 내장 버퍼에 추가해둔 메시지를 스트링으로 가져온다.
	}
}
