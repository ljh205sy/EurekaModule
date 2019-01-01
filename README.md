# EurekaModule
测试EUREKA， JWT,  PROVIDER, COMSUMER
1.获取token：post请求
http://localhost:8000/oauth/token
{"accessKey":"accessKey", "secretKey":"123"}

2.1 调用provider ，get请求
http://localhost:5555/hello1?name=aaaa
Authorization 
eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJhY2Nlc3NLZXkiLCJleHAiOjE1NDY0MjY2MzR9.kozNLOqHgLS23pKG0EDuvlNURU4Ynf9PQ6TJe8X6OK8KSzpOlvL7YnyggvRSEA42jVvNjvTRM4a7NeTY4HQVOrZXr-2J8pMa02W_tfoqGB7VMGDfW-1ensG5C44GzSvcuVPIVGblcQO80RHPQhwD7SDavcntaGvBs2umoRDMmTg

3. 3.Feign调用 ，get请求
http://localhost:7777/auth-test
Authorization
eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJhY2Nlc3NLZXkiLCJleHAiOjE1NDY0MjY2MzR9.kozNLOqHgLS23pKG0EDuvlNURU4Ynf9PQ6TJe8X6OK8KSzpOlvL7YnyggvRSEA42jVvNjvTRM4a7NeTY4HQVOrZXr-2J8pMa02W_tfoqGB7VMGDfW-1ensG5C44GzSvcuVPIVGblcQO80RHPQhwD7SDavcntaGvBs2umoRDMmTg

