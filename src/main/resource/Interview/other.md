# 1. toString 性能优化比较
|使用技术|	平均操作次数/秒|
|:--:|:--:|
|用’+’连接字符串|	142.075,167|
|String builder|	141.463,438|
|Objects.toString|	140.791,365|
|Guava|	110.111,808|
|ToStringBuilder (append)|	75.165,552|
|ToStringBuilder| (reflectionToString)	34.930,630|
|ReflectionToStringBuilder|	23.204,479|