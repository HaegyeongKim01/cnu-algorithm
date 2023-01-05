# Cost Spanning tree

Cost Spanning tree는 트리에 존재하는 edge들의 합
Minimum cost spanning tree는 cost spanning tree중 최소값을 가지는 트리
이를 해결하는 알고리즘   
Kruskal’s Algorithm    
Prims’s Algorithm    
Solin’s Algorithm    
T: Tree dege 집합    
G: garph    

## :bulb: Kruskal Algorithm 
Theorem 1 : spanning tree가 존재한다면 Kruskal Algorithm은 반드시 하나의 spanning tree를 찾는다.    
Threorem 2 : Kruskal Algorithm 의 spanning tree는 minimum cost spanning tree다.    
 → “Exchange Argument” ( For Greedy Algorithm )    
proof1) 우리가 알고있는 그래프가 undirected graph라면 그 그래프가 connected되어있다면 Kruskal’s Algorithm은 spanning tree를 찾아낸다, 갖고 있다. 
undirected graph가 spanning tree를 가진다면 ⇒ connected되어있어야 한다.
-
T가 empty 부터 시작, cost의 감소화하지 않는 순서로 add또는 discard판단을 한다.    
discard하는 edge는 cycle을 형성하는 edge들, cycle 형성하지 않는 edge들은 add
n-1개의 edge를 넣었다면 … T가 n-1 edge를 가진다면 …    
T에 n-1개의 edge를 가지고, connected고 cycle이 없다면 =⇒ G의 spanning tree다. 
(cycle이 없기에 connected다)
=⇒ Spanning tree찾아내면 connected다.
proof2) kruskal에서 찾아낸 spanning tree는 반드시 minimum cost spanning tree다.    
minimum cost spanning tree가 아니라는 것을 증명하면 모순이 발생해서 이로 인해 증
명을 한다.    
증명) graph G는 n개의 vertices를 가지면서 weighted, undirected, connected인
graph다.    
T는 G로 부터 얻어내는 spanning tree라하자. T는 여러개의 MCTs 중 하나다.
주어진 그래의 MCST 를 T와 비교해보면 겹치는 edge가 있을 수 있다. 겹치는게 가장
많은 MCST를 찾을 수 있다. 가장 많이 겹치는 개수를 k라하자