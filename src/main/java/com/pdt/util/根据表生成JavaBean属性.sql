
    --指定数据库
    use 数据库
    go
    --表名
    declare @tableName nvarchar(50);
    set @tableName='表名字';
    declare @colName nvarchar(50), @colType nvarchar(50);
    declare @prefix nvarchar(50);
    set @prefix='private ';
    declare syscolCur cursor for select syscolumns.name, systypes.name from syscolumns inner join sysobjects on syscolumns.id = sysobjects.id
    inner join systypes on syscolumns.xtype=systypes.xtype
    where sysobjects.type='U' and sysobjects.name=@tableName
    order by syscolumns.colid;
    open syscolCur;
    fetch next from syscolCur into @colName, @colType;
    --根据表字段名和数据类型生成java bean的属性
    --java bean属性名和表字段名一致
    --表数据类型和java bean属性的数据类型对应关系为：
    --| 表数据类型 | java bean属性数据类型 |
    --| bit | boolean |
    --| int | int |
    --| datetime | Timestamp |
    --| nvarchar | String |
    while @@FETCH_STATUS=0
    begin
    if @colType='int'
    begin
    print @prefix + 'int ' + @colName + ';'
    end;
    if @colType='nvarchar'
    begin
    print @prefix + 'String ' + @colName + ';'
    end;
    if @colType='bit'
    begin
    print @prefix + 'boolean ' + @colName + ';'
    end;
    if @colType='datetime'
    begin
    print @prefix + 'Timestamp ' + @colName + ';'
    end;
    fetch next from syscolCur into @colName, @colType;
    end;
    close syscolCur;
    deallocate syscolCur;
 




