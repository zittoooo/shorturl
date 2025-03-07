# shorturl

1. 요구 사항

* URL(이하 oriUrl) 을 요청 받아 짧은 URL(이하 shortUrl) 을 응답한다.
* shortUrl 은 8 Character 이내로 생성한다.
* shortUrl 은 중복되지 않는다.
* 동일한 oriUrl 요청은 동일한 shortUrl 을 응답한다.
* shortUrl 을 요청 받아 원래 oriUrl 을 응답한다.
* shortUrl 요청 수를 기록한다.
* 등록된 oriUrl, shortUrl, 요청 수를 조회 할 수 있다.


| HTTP Method | Endpoint          | Description               | 예시                                                  |
|------------|-------------------|---------------------------|-----------------------------------------------------|
| **POST**   | `/url/shorten?OriginUrl=` | oriUrl을 요청 받아 짧은 url 응답한다.| http://localhost:8080/url/shorten?originUrl={oriUrl} |
| **GET**    | `/url/{shortUrl}` | shortUrl을 요청 받아 원래 oriUrl을 응답한다. | http://localhost:8080/url/{shortUrl}                |
| **GET**    | `/url`            | 등록된 oriUrl, shortUrl, 요청 수를 조회 할 수 있다.| http://localhost:8080/url                           |


2. 접근과정

동일한 oriUrl 요청에 대해 항상 동일한 shortUrl을 반환하고, 중복을 방지하기 위해 해시를 사용하고 Base62 인코딩 방식도 고려했습니다.

그러나 해시 기반 방식은 디코딩이 불가능하다는 한계가 있었습니다.

그래서 oriUrl의 고유 ID 값을 이용하여 Base62 방식으로 인코딩 및 디코딩하는 방법을 적용했습니다.
