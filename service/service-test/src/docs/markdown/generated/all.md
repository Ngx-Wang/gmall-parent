# Api Documentation


<a name="overview"></a>
## Overview
Api Documentation


### Version information
*Version* : 1.0


### License information
*License* : Apache 2.0  
*License URL* : http://www.apache.org/licenses/LICENSE-2.0  
*Terms of service* : urn:tos


### URI scheme
*Host* : 127.0.0.1:8001  
*BasePath* : /


### Tags

* base-category-1-controller : Base Category 1 Controller
* base-category-2-controller : Base Category 2 Controller
* base-category-3-controller : Base Category 3 Controller
* basic-error-controller : Basic Error Controller
* test-controller : Test Controller




<a name="paths"></a>
## Paths

<a name="getcategory1usingget"></a>
### GetCategory1
```
GET /admin/product/getCategory1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* base-category-1-controller


<a name="getcategory2usingget"></a>
### GetCategory2
```
GET /admin/product/getCategory2/{category1Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**category1Id**  <br>*required*|category1Id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* base-category-2-controller


<a name="getcategory3usingget"></a>
### GetCategory3
```
GET /admin/product/getCategory3/{category2Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**category2Id**  <br>*required*|category2Id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* base-category-3-controller


<a name="errorusingpost"></a>
### error
```
POST /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* basic-error-controller


<a name="errorusingget"></a>
### error
```
GET /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* basic-error-controller


<a name="errorusingput"></a>
### error
```
PUT /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* basic-error-controller


<a name="errorusingdelete"></a>
### error
```
DELETE /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `*/*`


#### Tags

* basic-error-controller


<a name="errorusingpatch"></a>
### error
```
PATCH /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* basic-error-controller


<a name="errorusinghead"></a>
### error
```
HEAD /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* basic-error-controller


<a name="errorusingoptions"></a>
### error
```
OPTIONS /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* basic-error-controller


<a name="test1usingpost"></a>
### test1
```
POST /service/test/test1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* test-controller


<a name="test1usingget"></a>
### test1
```
GET /service/test/test1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* test-controller


<a name="test1usingput"></a>
### test1
```
PUT /service/test/test1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* test-controller


<a name="test1usingdelete"></a>
### test1
```
DELETE /service/test/test1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `*/*`


#### Tags

* test-controller


<a name="test1usingpatch"></a>
### test1
```
PATCH /service/test/test1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* test-controller


<a name="test1usinghead"></a>
### test1
```
HEAD /service/test/test1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* test-controller


<a name="test1usingoptions"></a>
### test1
```
OPTIONS /service/test/test1
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[全局统一返回结果](#afd1b7c86c88652432e9c08004366c43)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* test-controller




<a name="definitions"></a>
## Definitions

<a name="modelandview"></a>
### ModelAndView

|Name|Schema|
|---|---|
|**empty**  <br>*optional*|boolean|
|**model**  <br>*optional*|object|
|**modelMap**  <br>*optional*|< string, object > map|
|**reference**  <br>*optional*|boolean|
|**status**  <br>*optional*|enum (100 CONTINUE, 101 SWITCHING_PROTOCOLS, 102 PROCESSING, 103 CHECKPOINT, 200 OK, 201 CREATED, 202 ACCEPTED, 203 NON_AUTHORITATIVE_INFORMATION, 204 NO_CONTENT, 205 RESET_CONTENT, 206 PARTIAL_CONTENT, 207 MULTI_STATUS, 208 ALREADY_REPORTED, 226 IM_USED, 300 MULTIPLE_CHOICES, 301 MOVED_PERMANENTLY, 302 FOUND, 302 MOVED_TEMPORARILY, 303 SEE_OTHER, 304 NOT_MODIFIED, 305 USE_PROXY, 307 TEMPORARY_REDIRECT, 308 PERMANENT_REDIRECT, 400 BAD_REQUEST, 401 UNAUTHORIZED, 402 PAYMENT_REQUIRED, 403 FORBIDDEN, 404 NOT_FOUND, 405 METHOD_NOT_ALLOWED, 406 NOT_ACCEPTABLE, 407 PROXY_AUTHENTICATION_REQUIRED, 408 REQUEST_TIMEOUT, 409 CONFLICT, 410 GONE, 411 LENGTH_REQUIRED, 412 PRECONDITION_FAILED, 413 PAYLOAD_TOO_LARGE, 413 REQUEST_ENTITY_TOO_LARGE, 414 URI_TOO_LONG, 414 REQUEST_URI_TOO_LONG, 415 UNSUPPORTED_MEDIA_TYPE, 416 REQUESTED_RANGE_NOT_SATISFIABLE, 417 EXPECTATION_FAILED, 418 I_AM_A_TEAPOT, 419 INSUFFICIENT_SPACE_ON_RESOURCE, 420 METHOD_FAILURE, 421 DESTINATION_LOCKED, 422 UNPROCESSABLE_ENTITY, 423 LOCKED, 424 FAILED_DEPENDENCY, 425 TOO_EARLY, 426 UPGRADE_REQUIRED, 428 PRECONDITION_REQUIRED, 429 TOO_MANY_REQUESTS, 431 REQUEST_HEADER_FIELDS_TOO_LARGE, 451 UNAVAILABLE_FOR_LEGAL_REASONS, 500 INTERNAL_SERVER_ERROR, 501 NOT_IMPLEMENTED, 502 BAD_GATEWAY, 503 SERVICE_UNAVAILABLE, 504 GATEWAY_TIMEOUT, 505 HTTP_VERSION_NOT_SUPPORTED, 506 VARIANT_ALSO_NEGOTIATES, 507 INSUFFICIENT_STORAGE, 508 LOOP_DETECTED, 509 BANDWIDTH_LIMIT_EXCEEDED, 510 NOT_EXTENDED, 511 NETWORK_AUTHENTICATION_REQUIRED)|
|**view**  <br>*optional*|[View](#view)|
|**viewName**  <br>*optional*|string|


<a name="view"></a>
### View

|Name|Schema|
|---|---|
|**contentType**  <br>*optional*|string|


<a name="afd1b7c86c88652432e9c08004366c43"></a>
### 全局统一返回结果

|Name|Description|Schema|
|---|---|---|
|**code**  <br>*optional*|返回码|integer (int32)|
|**data**  <br>*optional*|返回数据|object|
|**message**  <br>*optional*|返回消息|string|
|**ok**  <br>*optional*||boolean|





