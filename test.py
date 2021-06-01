import sys
input = sys.stdin.readline

N = int(input())
S = int(input())
M = [input().rstrip() for _ in range(N)]


# 인덱스저장
l1 = [[]*N for _ in range(N)]
l2 = [[]*N for _ in range(N)]
temp = []


for i in (0, N):
    for j in (0, N):
        for a in (0, i):
            temp.append(M[i-a][j])
            l1[i].append(temp)
        temp = []


if len(l1) == 0 or sum(l1) < S:
    l1.append(M[i][j])
elif sum(l1) == S:
    for _ in l1:
        print(l1.pop(0))
else:
    l1.pop(0)
