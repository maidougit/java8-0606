mysql 性能优化

### 1. 哪些列上适合添加索引
<pre>
  - 较频繁的作为查询条件字段应该创建索引
  - 唯一性太差的字段不适合单独创建索引，即使频繁作为查询条件
  - 更新非常频繁的字段不适合创建索引
  - 不会出现在WHERE子句中的字段不该创建索引
 </pre>
### 2.为什么使用数据索引能提高效率 
<pre> 1. 数据索引的存储是有序的
 2. 在有序的情况下，通过索引查询一个数据是无需遍历索引记录的
 3. 极端情况下，数据索引的查询效率为二分法查询效率，趋近于 log2(N)
</pre>


### 3. SQL优化步骤
<pre>
 1.通过show status 命令了解各种SQL的执行效率， 
   命令 :  show [session | global] status;  
   for example : show status like 'Com_%'; 
   important  : Com_select/Com_insert/Com_update/Com_delte。
 2. 定位执行效率较低的SQL语句
   命令 : show processlist or  show full  processlist;
   column : id | user | Host | db | Command | Time | State | Info |   
 3. 执行计划
   explain select * from table; 
 4. 通过show profile 分析SQL
   命令 : show profile 
   Column : | Status | Duration |  
 5. 确定问题并采取相应的优化措施
    1. 定期分析和检查表
       for example : analyze table tbl_name;
                     check table tbl_name;
    2. 定期优化表
        for example : optimize table tbl_name;   
    3. 优化insert语句
      1. 如果从同一客户端插入很多行，应该尽量使用多个值表一次性插入；
      2. 如果从不同客户端插入很多行，可以使用insert delayed语句先把数据放在内存的队列中，并不真正写入磁盘，比每条语句分别插入快得多；                        
      3. 当从一个文本文件装载一个表时，使用load data infile，这通常比使用很多insert 语句快20倍；
      4. 如果在MyISAM表中进行批量插入，可以通过增加bulk_insert_buffer_size变量值的方法来提高速度。
    4. 优化 order by语句
      MySQL中有两种排序方式，第一种通过有序索引顺序扫描直接返回有效数据，不需要额外的排序，操作效率较高；第二种对返回的数据进行排序，也就是常说的Filesort排序，所有不是通过索引直接返回排序结果的排序都是filesort排序。 
      优化目标：尽量通过索引直接返回有序数据，减少额外的排序。 
      通过创建合适的索引能减少filesort出现，但是某些情况下，条件限制不能让filesort消失，那就需要想办法加快filesort的操作。filesort有两种排序算法，一种是一次扫描算法（较快），二种是两次扫描算法。适当加大系统变量max_length_for_sort_data的值，
      能够让MySQL选择更优化的filesort排序算法；适当加大sort_buffer_size排序区，尽量让排序在内存中完成，而不是通过创建临时表放在文件中进行。尽量只使用必要的字段，select具体的字段名称，而不是select * 选择所有字段，这样可以减少排序区的使用，提高SQL性能。  
    5. 优化group by 语句 
      MySQL默认对所有group by col1,col2…的字段进行排序，可以指定order by null禁止排序。
    6. 优化嵌套查询 
      子查询的效率不如连接查询（join），因为MySQL不需要在内存中创建临时表来完成这个在逻辑上需要两个步骤的查询工作。
    7. 优化or 查询
      对于含有or的查询子句，如果要利用索引，则or之间的每个条件列都必须使用索引；如果没有索引，可以考虑增加索引。 
      MySQL在处理含有or的查询时，实际上对or的各个字段分别查询后的结果进行了union操作。
    8. 优化分页查询 
      ·第一种 在索引上完成排序分页操作，然后根据主键关联回原表查询所需要的其他列的内容； 
      ·第二种 在排序字段不会出现重复值的情况下，新增一个参数记录上次查询的最后一条记录，将limit m,n转化成limit n.
    9. 使用SQL提示 
      就是在SQL语句中加入一些认为提示，让MySQL按照特定方案执行，以达到优化操作的目的。 
       ·use index 指定MySQL参考的索引而忽略别的索引 
       ·ignore index 让MySQL忽略某个或某些索引 
       ·force index 强制MySQL使用某个特定的索引
    10.  其他 
       ·使用REGEXP，比如代替like. 
       ·使用rand()提取随机行 
       ·表的字段尽量不使用自增长变量，在高并发的情况下可能会对MySQL的效率有较大影响。
    11. 参考链接 : [https://blog.csdn.net/sinat_23080035/article/details/52802569](#参考链接)               
</pre>