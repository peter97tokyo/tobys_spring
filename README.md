#공부 환경

Computer : macbook

Tool : eclipse

Java : openJdk_1.8

Tomcat : 8.5

book : 토비의 스프링 3.1

Start_date : 2023-03-20 22:00:00

###루트 애플리케이션 컨텍스트 등록(1장-77p)

스프링은 웹  애플리케이션의 시작과 종료 시  발생하는 이벤트를 처리하는 리스너인 ServletContextListener를 이용한다.  
ServletContextListener 인터페이스를 구현한 리스너는 웹 애플리케이션 전체에 적용가능한 DB 연결 기능이나 로깅 같은 서비스를 만드는 데 유용하게 쓰인다.  
사용 경로, 사용법 web.xml(1번)
* 애플리케이션 컨텍스트 클래스 : XmlWebApplicationContext
* XML 설정 파일 위치 : WEB-INF/applicationContext.xml

루트 애플리케이션 컨텍스트는 웹 애플리케이션의 WEB-INF 폴더 내에 있는 applicationContext.xml 파일을 디폴트 설정파일로 사용한다.  
컨텍스트 클래스와 설정파일 위치는 서블릿 컨텍스트 파라미터를 선언해서 변경할 수 있다. ContextLoaderListener가 이용할 파라미터를 context-param 항목 안에 넣어주면 디폴트 설정 대신 파라미터로 지정한 내용이 적용된다.  
사용 경로, 사용법 web.xml(2번)  

web.xml(2번)으로 진행하면 디폴트 설정 파일은 무시되고 파라미터로 제공된 설정파일을 사용하게 됩니다. 

서블릿 리소스 패스 대신 클래스패스로부터 설정파일을 찾게 할 수도 있다. ex) param-value => classpath:applicationContext.xml  
다음의 방법으로 애플리케이션의 규모가 커지면서 등록해야 할 빈이 많아지면 빈 설정을 여러개의 파일로 쪼개서 관리하는 게 편리할 수 있다.
/WEB-INF/**/*Context.xml

###contextClass(1장-79p)

contextLoaderListener가 자동으로 생성하는 컨텍스트의 클래스는 기본적으로 XmlWebApplicationContext다. 이를 다른 애플리케이션
컨텍스트 구현 클래스로 변경하고 싶으면 contextClass 파라미터를 이용해 지정해주면 된다. 여기에 사용될 컨텍스트는 반드시 
WebApplicationContext 인터페이스를 구현해야 한다.  
XmlWebApplicationContext 외에 현재 스프링이 제공하는 대체 가능한 컨텍스트 클래스는 AnnotationConfigWebApplicationContext다.  
이 클래스는 XML 설정 대신 소스코드 내의 애노테이션 선언과 특별하게 만들어진 자바 코드를 설정 메타정보를 활용하는 것이다.
사용경로, 사용법 web.xml(3번)  
AnnotationConfigWebApplicationContext를 컨텍스트 클래스로 사용할 때는 contextConfigLocation 파라미터를 반드시 선언해줘야 한다.

###서블릿 애플리케이션 컨텍스트 등록(1장-80p)
스프링의 웹 기능을 지원하는 프론트 컨트롤러 서블릿은 DispatcherServlet이다. web.xml에 등록해서 사용할 수 있다.  
서블릿의 이름을 다르게 하면 하나의 웹 애플리케이션에서 여러개의 DispatcherServlet을 등록할 수 있다. 각각의 DispatcherServlet은 서블릿이 초기화될 때 자신만의 컨텍스트를 생성하고 초기화한다.
사용경로, 사용법 web.xml(4번)








