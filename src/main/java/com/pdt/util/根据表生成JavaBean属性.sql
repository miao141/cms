
    --ָ�����ݿ�
    use ���ݿ�
    go
    --����
    declare @tableName nvarchar(50);
    set @tableName='������';
    declare @colName nvarchar(50), @colType nvarchar(50);
    declare @prefix nvarchar(50);
    set @prefix='private ';
    declare syscolCur cursor for select syscolumns.name, systypes.name from syscolumns inner join sysobjects on syscolumns.id = sysobjects.id
    inner join systypes on syscolumns.xtype=systypes.xtype
    where sysobjects.type='U' and sysobjects.name=@tableName
    order by syscolumns.colid;
    open syscolCur;
    fetch next from syscolCur into @colName, @colType;
    --���ݱ��ֶ�����������������java bean������
    --java bean�������ͱ��ֶ���һ��
    --���������ͺ�java bean���Ե��������Ͷ�Ӧ��ϵΪ��
    --| ���������� | java bean������������ |
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
 




