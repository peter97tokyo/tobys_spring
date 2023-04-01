package springbook.learningtest.spring.ioc.bean;

public class Hello {
	String name;
	Printer printer;
	
	public String sayHello() {
		return "Hello" + name; // 프로퍼티로 DI 받은 이름을 이용해 간단한 인사문구 만들기
	}
	
	public void print() {
		this.printer.print(sayHello());
	}
	/*
	 * DI에 의해 의존 오브젝트로 제공받은 Printer 타입의 오브젝트에게 출력 작업을 위임한다. 구체적으로 어떤
	 * 방식으로 출력하는지 상관하지 않는다. 또한 어떤 방식으로 출력하도록 변경해도 Hello의 코드는 수정할 필요가 없다.
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
}
