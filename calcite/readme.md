## fh dmx demo

自定义sql:

```mdx
use cube_name;
scope([Account].[c01], [Year].[2022]);
    [PRODUCT].[02] = [PRODUCT].[01] * 0.8;
end scope;
```

语法：

```sql
USE cubename;
scope()
```

## 参考文档

[Calcite中定制自已SQL解析器]https://blog.csdn.net/ccllcaochong1/article/details/93367343()

