# 哈夫曼编码示例

```
=========== Set Weight ===========
char:T, weight:1
char:e, weight:1
char:g, weight:1
char:., weight:1
char:h, weight:2
char:i, weight:2
char:t, weight:2
char:p, weight:2
char:r, weight:2
char:s, weight:3
char: , weight:4
char:a, weight:4
=========== Print Node ===========
value=T, encode=00000000000
value=e, encode=00000000001
value=g, encode=0000000001
value=., encode=000000001
value=h, encode=00000001
value=i, encode=0000001
value=t, encode=000001
value=p, encode=00001
value=r, encode=0001
value=s, encode=001
value= , encode=01
value=a, encode=1
=========== Encode Text ===========
Input:This is a test paragraph.
Encode:00000000000000000010000001001010000001001011010000010000000000100100000101000011000110000000001000110000100000001000000001
```
