## Paths
### printWelcome
```
GET /
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|BodyParameter|model|model|false|object||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|404|Not Found|No Content|
|200|OK|string|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* hello-controller

### 获取所有管理员
```
GET /admins
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|404|Not Found|No Content|
|200|OK|Admin array|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* 管理员模块

