# :books: AL01 Class별 설명서  

<h3>Class “AppController”</h3>
<b>주요 알고리즘</b><br>
실질적으로 app을 control한다.
Appcontroller 클래스에 run 함수를 만들었으며 run함수는 AppView클래스의 함수를 호출하여 사용자에게 전달하고자 하는 메시지를 보여주도록 한다.

<b>함수 설명서</b><br>
AppController 클래스에는 AppController생성자와 run함수가 존재한다.
생성자에는 아직 아무런 코드를 적용하지 않았고 run함수는 사용자에게 메시지를 보여 주도록 
AppView클래스의 outputLine함수를 호출한다.
# 
<h3>Static class "AppViewer”</h3>
<b>함수 설명서</b><br>
 App의 입출력을 담당한다.
**AppController와만 관계를 맺고 model 클래스와는 완전히 독립적이어야 한다.
주요 알고리즘
scanner클래스를 호출하여 객체 생성
AppView 생성자
outputLine함수를 호출하는 경우 모니터에 매개변수로 받은 string이 줄바꿈 포함하여 출력된다.
output함수를 호출하는 경우 모니터에 매개변수로 받은 string이 출력된다.

<b>자료구조</b><br>
AppView는 Static Class.
객체 실체를 필요하지 않는 class다. 즉, new를 사용하여 객체를 생성할 필요가 없는 class.
java언어에서는 문법적으로 static class를 선언하는 방법을 제공하지 않기에 class를 ,final 로 선언하여 상속을 막는다.  Final로 선언하면 상속을 막을 수 있다. 
생성자는 private으로 선언하여 외부 사용자의 instance객체의 생성을 막는다.
class안의 모든 함수와 변수는 static으로 선언한다. 
