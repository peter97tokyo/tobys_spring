# 느낀점 및 이것저것  
1. 1장 80페이지 이전에 내용도 깃에 있었지만 공부를 하다가 방통대, 회사 일로 장기간 공부안하고 다시 시작하고자 했으나 왜 이렇게 작성 했지란 생각으로인해 다시 시작했습니다.
2. 토비의 스프링을 그대로 배껴온 내용도 있지만 최대한 읽고 이해를 하여 자신만의 글로 만들려고 노력했습니다.

### 공부 환경

Computer : macbook

Tool : eclipse

Java : openJdk_1.8

Tomcat : 8.5

book : 토비의 스프링 3.1

Start_date : 2023-03-20 22:00:00

# 루트 애플리케이션 컨텍스트 등록(1장-77p)

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

# contextClass(1장-79p)

contextLoaderListener가 자동으로 생성하는 컨텍스트의 클래스는 기본적으로 XmlWebApplicationContext다. 이를 다른 애플리케이션
컨텍스트 구현 클래스로 변경하고 싶으면 contextClass 파라미터를 이용해 지정해주면 된다. 여기에 사용될 컨텍스트는 반드시 
WebApplicationContext 인터페이스를 구현해야 한다.  
XmlWebApplicationContext 외에 현재 스프링이 제공하는 대체 가능한 컨텍스트 클래스는 AnnotationConfigWebApplicationContext다.  
이 클래스는 XML 설정 대신 소스코드 내의 애노테이션 선언과 특별하게 만들어진 자바 코드를 설정 메타정보를 활용하는 것이다.
사용경로, 사용법 web.xml(3번)  
AnnotationConfigWebApplicationContext를 컨텍스트 클래스로 사용할 때는 contextConfigLocation 파라미터를 반드시 선언해줘야 한다.

# 서블릿 애플리케이션 컨텍스트 등록(1장-80p)
스프링의 웹 기능을 지원하는 프론트 컨트롤러 서블릿은 DispatcherServlet이다. web.xml에 등록해서 사용할 수 있다.  
서블릿의 이름을 다르게 하면 하나의 웹 애플리케이션에서 여러개의 DispatcherServlet을 등록할 수 있다. 각각의 DispatcherServlet은 서블릿이 초기화될 때 자신만의 컨텍스트를 생성하고 초기화한다.
사용경로, 사용법 web.xml(4번)

# IoC/DI를 위한 빈 설정 메타정보 작성

IoC 컨테이너의 가장 기본적인 역할은 코드를 대신해서 애플리케이션을 구성하는 오브젝트를 생성하고 관리하는 것이다.  
IoC 컨테이너가 직접 사용하는 BeanDefinition은 순수한 오브젝트로 표현되는 빈 생성 정보다. 스프링에서 자주 사용되는 빈의 등록 방법은 크게 다섯 가지가 있다.

### 1. XML: Bean Tag
bean tag는 beans라는 루트 엘리먼트를 갖는 xml 문서에 포함된다. 기본적으로 다음과 같이 id와 class가 필요하고 id는 생략이 가능하다.  
bean은 다른 bean tag 안에 property tag로 선언할 수 있다. id가 없고 name이 필요하며 다른 빈에서는 참조할수 없다. property tag는 DI이긴 하지만 특정 빈과 강한 결합을 가지고 등록되는 경우에 내부 빈을 사용한다.

### 2. XML: 네임스페이스와 전용 태그
스프링의 빈을 분류하자면 크게 애플리케이션의 핵심 코드를 담은 컴포넌트와 서비스 또는 컨테이너 설정을 위한 빈으로 구분할 수 있다. 두개 모두 bean이라는 태그로 등록 돼서 컨테이너 의해 만들어지고 관리될 수 있지만 성격이 크게 다르다. 문제는 이렇게 애플리케이션 로직을 담은 빈이 동일하게
bean tag 안에 있기 때문에 구분이 잘되지 않는다. 매우 범용적인 bean tag 와 property tag로 선언되기 때문에 한눈에 빈의 등록이 어떤 의도 의미를 갖고 있는지 파악하기 어렵다.

그래서 스프링은 이런 기술적인 설정과 기반 서비스를 빈으로 등록할 때를 위해 의미가 잘드러나는 네임스페이스와 태그를 가진 설정 방법을 제공한다.
네임스페이스와 전용 태그, 전용 애트리뷰트를 이용해 선언했기 때문에 내용이 분명이 드러나고 선언 자체도 깔끔해진다. 런타임 시점에서야 필요한 프로퍼티가 설정되어 있는지를 확인할 수 있는 bean 방식의 선언과 달리 aop:pointcut은 aop 스키마가 있기 때문에 XML 문서 편집 중에도 즉시 
애트리뷰트의 타입과 필수 사용 여부 등을 검증할 수 있다는 장점이 있다.




