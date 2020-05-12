# poet

## 接口文档汇总

url前缀：http://118.31.12.175:8081/

## 一.用户模块接口

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

- ### 修改个人信息

  ```json
  说明：修改昵称/个性签名
  url:/user/update.do
  参数：name(昵称),signature(个性签名)
  注意：可以只传其中一个参数
  返回示例：
  {
  "msg": "ok",
  "user":{
  "id": 1,
  "accountNumber": "22457269",
  "name": "楚楚1",
  "phone": "18634587269",
  "password": "null",
  "header": "http://icou.life/Fk1aYOyY_-g73JfvB5Qn2Irfwh9T",
  "signature": "楚楚每天都很美",
  "grades": 1
  }
  }
  ```

- ### 上传/修改头像

  ```json
  url:/user/upload_header.do
  参数: file(所要上传的头像文件)
  返回示例：
  {
  "msg": "ok",
  "userId": 1,
  "url": "http://icou.life/FiC2RdqsJHQMkmMCtFNfJeCakdjz"
  }
  ```

- ### 添加装扮

  ```json
  说明：传入各个部位对应的装扮参数(用整数来表示),将其存入数据库
  url:/user/addClothes.do
  参数：hair,dress,background.face
  说明：以上四个参数均为整型，代表装扮部位对应的标号
  返回示例：
  "msg": "ok",
  "cloth":{
      "id": 3,
      "userId": 1,
      "hair": 1,
      "dress": 6,
      "background": 1,
      "face": 2
      }
  }
  ```

- ### 删除某个装扮

  ```json
  url:/user/deleteClothes.do
  参数：id(某个装扮对应的id)
  返回示例:
  {   
      "msg": "ok"
  }
  ```

- ### 退出登录

  ```json
  url:/user/logout.do
  返回示例:
  {
      ok
  }
  ```

## 二.诗词模块接口

- ### 每日分享

  ```json
  说明：每次刷新随机分享一首诗词
  url:/poet/ran_share.do
  返回示例:
  {   
      "msg": "ok",
      "poet":{
          "sid": 234211,
          "author": "杨公远",
          "title": "月下看白莲",
          "paragraphs": "十里荷花带月看，花和月色一般般。\",\"祇应舞彻霓裳曲，宫女三千下广寒。\"",
          "dynasty": "宋代"
      }
  }
  ```

- ### 关键词搜索

  ```json
  说明：用户键入想要搜索的诗人名称/诗词名称/诗词中的某个诗句，返回相关综合的结果集
  url:/poet/select.do
  参数:key(关键字)
  注意：如果搜索的关键字是诗人，就返回这个诗人的信息以及这个诗人的前20首诗；如果搜索的是诗词名，就返回这首诗的相关信息；如果是诗句,就返回这个诗句所在的诗词；返回的诗句因为可能有多条，所以内容就截取了前两句，具体的诗句可以点进去看详细信息。
  
  返回示例：
  (关键字为‘静夜思’)
  {
  "msg": "ok",
  "author":[],
  "poets":[
      {    
      "sid": 258870,
      "author": "沈佺期",
      "title": "同工部李侍郎适访司马子微",
      "paragraphs": "紫微降天仙，丹地投云藻。\"",
      "dynasty": "唐代"
      },
      {
      "sid": 261988,
      "author": "李白",
      "title": "静夜思",
      "paragraphs": "牀前看月光，疑是地上霜。\"",
      "dynasty": "唐代"
      }
  ]
  }
  
  (关键字为‘王维’)
  {
  "msg": "ok",
  "author":[
      {    
      "mid": 8995,
      "name": "王维",
      "dynasty": "唐代",
      "header": "http://118.31.12.175:8081/images/10.JPG"
   }
  ],
  "poets":[
      {
      "sid": 254811,
      "author": "王维",
      "title": "横吹曲辞 陇头吟",
      "paragraphs": "长安少年游侠客，夜上戍楼看太白。\"",
      "dynasty": "唐代"
  },
      {
      "sid": 254828,
      "author": "王维",
      "title": "横吹曲辞 出塞",
      "paragraphs": "居延城外猎天骄，白草连天野火烧。\"",
      "dynasty": "唐代"
  },
      {
      "sid": 255072,
      "author": "王维",
      "title": "相和歌辞 从军行",
      "paragraphs": "吹角动行人，喧喧行人起。\"",
      "dynasty": "唐代"
  },
     ......
     
      }
  
  
  ```

- ### 查看某一个诗词

  ```json
  url:/poet/get_poet.do
  参数：id(某一个诗词对应的id)
  返回示例：
  {
  "msg": "ok",
  "poet":{
  "sid": 99993,
  "author": "李弥逊",
  "title": "游梅坡席上杂酬 其五",
  "paragraphs": "白玉千株暗，青云一径斜。\",\"从来少车马，恐是阿环家。\"",
  "dynasty": "宋代"
  }
  }
  ```

- ### 查看某一个诗人

  ```json
  url:/poet/get_author.do
  参数：id(某一个诗人对应的id)
  返回示例：
  {
  "msg": "ok",
  "author":{
  "mid": 333,
  "id": "e43023c8-de3e-478d-9863-dd7548a0f6b7",
  "name": "魏野",
  "desc": "魏野（九六○～一○二○），字仲先，号草堂居士，陕州陕县（今属河南）人（《东都事略》卷一一八作蜀人）。一生不仕，居陕县东郊。真宗大中祥符四年（一○一一）被荐征召，力辞不赴。广交僧道隠者，与当时名流寇准、王旦等亦有诗赋往还。天禧三年（一○一九）十二月九日卒，年六十。卒后赠秘书省著作郎。有《草堂集》，生前已行于世。死后，其子魏闲总其诗重编为《钜鹿东观集》十卷。《宋史》卷四五七、《东都事略》卷一一八有传。　魏野诗，前十卷以一九二五年贵池刘氏影宋刊本《钜鹿东观集》为底本，校以宋绍定元年严陵郡斋刻本（残四～六卷，以明抄本配足，简称明抄配宋本，藏北京图书馆）、旧山楼藏清钞本（简称旧山楼本，藏北京大学图书馆）、张蓉镜藏清钞本（简称张本）、影印文渊阁《四库全书》本（简称四库本）、章钰藏清钞本（简称章本）、宋筠录温忠翰校跋本（简称温校）、赵氏峭帆楼一九一四年刊本（简称赵本）、傅增湘批校本（简称傅校）、陈思辑《两宋名贤小集》（清初钞本，收《草堂集》三卷，简称草堂集本）等。另从《草堂集》、《全芳备祖》等书辑得集外诗，附于卷末。"
  }
  }
  ```

  ## 三.收藏模块接口
  
- ### 收藏/取消收藏诗词

  ```json
  url:/collect/collect.do
  参数：id(诗词对应的id)
  说明：对于同一首诗词，第一次发送请求收藏成功；第二次发送请求即取消收藏；以此类推~
  返回示例:
  {
  "msg": "ok",
  "info":{    
      "id": 2,
      "poetId": 999,
      "userId": 1,
      "type": 1,
      "isCanceled": true
   }
  }
  {
  "msg": "ok",
  "info": "canceled"
  }
  
  ```

- ### 背诵诗词

  ```json
  url:/collect/remember.do
  参数：poetId(诗词对应的id)
  返回示例：
  {   
      "msg": "ok"
      "升级":2
  }
  ```

- ### 取消背诵

  ```json
  url:/collect/cancelRemember.do
  参数：poetId(诗词对应的id)
  说明:取消之后，该诗词自动返回到收藏列表。
  返回示例：
  {   
      "msg": "ok"
  }
  ```

- ### 查看已收藏诗词列表

  ```json
  url:/collect/getCollects.do
  返回示例：
  {
  "msg": "ok",
  "poet":[    
      {
      "sid": 9996,
      "author": "夏竦",
      "title": "句 其三",
      "paragraphs": "雪度维杨腊，花逢北国春。\"",
      "dynasty": "宋代"
  },
      {
      "sid": 10000,
      "author": "夏竦",
      "title": "句 其七",
      "paragraphs": "似法阴爻呈六穗，或符阳数效三岐。\"",
      "dynasty": "宋代"
  },
      {
      "sid": 765,
      "author": "许坚",
      "title": "题扇",
      "paragraphs": "哦吟但写胸中妙，饮酒能忘身后名。\"",
      "dynasty": "宋代"
  }
  ]
  }
  ```

- ### 查看已背诵列表

  ```json
  url:/collect/getRemember.do
  返回示例：
  {
  "msg": "ok",
  "poet":[    
     {
     "sid": 58,
     "author": "陈抟",
     "title": "喜英公大师挂锡太华",
     "paragraphs": "暗喜莲峰作近隣，拨开云雾见师频。\"",
     "dynasty": "宋代"
  }
  ]
  }
  ```




## 5.12更新

- ### 背诵诗词+用户升级

  升级机制：采用"背诵积分制来对用户进行升级"，每背诵一首诗积累积分为5分，然后积分对应的级别分别为：

  ```json
  0分：1级(默认级别)
  10分：2级
  30分：3级
  60分：4级
  100分：5级
  ……
  规律为：每两个级别的差值形成等差数列，即级别1和级别2相差10分，级别3和级别2相差20分，级别4和级别3相差30分……以此类推。
  升级机制：用户每背诵一首诗，累加一次积分，并判断是否满足升级条件，如果满足条件，返回msg提示信息以及升级后的级别；如果不满足条件，则直接返回msg
  
  url:/collect/remember.do
  参数：poetId(诗词对应的id)
  返回示例：
  (达到升级条件)
  {   
      "msg": "ok"
      "升级":2
  }
  (未达到升级条件)
  {   
      "msg": "ok"
  }
  
  
  ```

  