from multiprocessing import Condition
from queue import PriorityQueue
import sys

def Conditions(dictnode,goal):
    if dictnode==goal:
        return 0
    return 1
def findRoute(source, goal, tree, h):
    created_nodes,f,visited,explored,max_node,count=initialize(source,goal,dict)
    while not f.empty():
        count,p,dictnode=fun(f,count,explored,goal,max_node)
        if p==0: break
        if p==1: continue
        explored.append(dictnode)
        for i in tree[dictnode]:
            created_nodes+=1  
            if i not in visited:
                visited[i]=(dictnode,tree[dictnode][i]+visited[dictnode][1])
            f.put((tree[dictnode][i]+visited[dictnode][1]+h[i],i))
    route = []
    distance = "infinity"
    distance,route=checkgoal(visited,tree,distance,goal,source)
    return route,explored,created_nodes,distance,count


def checkgoal(visited,dict,distance,goal,source):
    route=[]
    if goal in visited:
        distance = 0.0
        c_node = goal
        while c_node != source:
            distance += dict[visited[c_node][0]][c_node]
            route.append(c_node)
            c_node = visited[c_node][0]
    return distance,route

def execute_file(file_input):
    dict = {}
    with open(file_input,'r') as fp:
       lines = fp.readlines()
    fp.close()
    for line in lines[:-1]:
        lis = line.split()
        # insert data into dictionary
        m,n = lis[0],lis[1]
        k = int(lis[2])
        if(m in dict):dict[m][n] = k
        else:dict[m] = {n:k}
        if(n in dict):dict[n][m] = k
        else:dict[n] = {m:k}
    return dict

def execute_heuristic(file_input):
    h = {}
    with open(file_input,'r') as fp:
       lines = fp.readlines()
    fp.close()
    for line in lines[:-1]:
        data = line.split()
        m,n=data[0],data[1]
        h[m] = int(n)
    return h

def checkin_visited(i,visited,dict,dictnode):
    m=0
    if i not in visited:
        m=dict[dictnode][i]
        m=m+visited[dictnode][1]
        visited[i]=(dictnode,m)
    return visited

def fun(f,count,explored_nodes,goal,max_nodes):
    max_nodes=max(max_nodes,len(f.queue))
    _,dictnode = f.get()
    count+=1
    if Conditions(dictnode,goal) == 0:
        return count,0,dictnode
    if dictnode in explored_nodes:
        return count,1,dictnode
    return count,2,dictnode
def initialize(source,goal,dict):
    f=PriorityQueue()
    f.put((0,source))
    visited = {}
    visited[source] = ("",0)
    explored_nodes = []
    max_nodes ,count,created_nodes=0,0,1
    return created_nodes,f,visited,explored_nodes,max_nodes,count
def ucs(source, goal, dict):
    created_nodes,f,visited,explored_nodes,max_nodes,count=initialize(source,goal,dict)
    while not f.empty():
        count,p,dictnode=fun(f,count,explored_nodes,goal,max_nodes)
        if p==0: break
        if p==1: continue
        explored_nodes.append(dictnode)
        for i in dict[dictnode]:    
            created_nodes+=1
            k=dict[dictnode][i]
            k=k+visited[dictnode][1]
            f.put((k,i))
            visited=checkin_visited(i,visited,dict,dictnode)
    distance = "infinity"
    distance,route=checkgoal(visited,dict,distance,goal,source)
    return route,explored_nodes,created_nodes,distance,count

def main(arguments):
    inputs=arguments[1]
    source=arguments[2]
    destination=arguments[3]
    m=len(arguments)
    # print(inputs)
    dict=execute_file(inputs)
    # informed search
    if m==4:
        route,ne, ng, dist,nodes = ucs(source,destination,dict)
    #informed search
    else:
        h = execute_heuristic(arguments[4])
        route,ne, ng, dist, nodes = findRoute(source,destination,dict,h)
    print('Nodes Popped',nodes)
    print('Nodes Expanded:' ,len(ne))
    print('Nodes Generated: ',ng)
    print('Distance: ', dist)
    print('Route:')
    count=0
    for p in route[::-1]:
        count+=1
        print(f'{source} to {p}, {dict[source][p]} km')
        source = p
    if count==0:
        print("None")
    return 
        
main(sys.argv)

