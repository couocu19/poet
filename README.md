# poet

## 接口文档汇总

url前缀：http://118.31.12.175:8081/

- ### 发送验证码

  ```json
  说明:通过输入手机号获取短信验证码
  url:前缀+get_code
  参数:memPhone(注册手机号)
  返回数据:
  (发送成功)
  {
      true
  }
  (发送失败)
  {
      false
  }
  ```

- ### 注册

  ```json
  说明：通过手机接收到的验证码校验以及设置的密码注册账号，返回一个随机生成的账号(类似于qq号)
  url:前缀+user/register.do
  参数:memPhone,code,password
  注意：先调用上面第一个接口发送验证码然后调用第二个接口完成注册
  返回示例：
  {
      "msg": "ok",
      "accountNumber": "70287269"
  }
  {
      "msg": "error"
  }
  ```

- ### 登录

  ```json
  说明：通过生成的账号和设置的密码进行登录
  url:前缀+user/login.do
  参数:accountNumber(账号),password(密码)
  注意：用户的用户名初始为注册手机号，可以在注册成功之后修改
  返回示例:
  {   
      "msg": "ok",
      "currentUser":{
      "id": 3,
      "accountNumber": "70287269",
      "name": "18634587269",
      "phone": "18634587269",    
      "password": null,
      "header": null,
      "signature": null,
      "grades": 1
      }
  }
  ```

  