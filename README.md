### 使用原生的websocket - 基础版的 web项目 war包 prototype-websocket
1. 创建项目 - webapp/WEB-INF
   -webapp/index.jsp
2. 使用插件的方式启动web容器 - 使用 jetty 插件
   ```xml
           <!-- jetty 服务器插件 -->
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.14.v20181114</version>
            </plugin>
    ```
3. pom.xml修改 <packaging>war</packaging>
4. 引入依赖,支持java-websocket
    ```xml
               <dependency>
                   <groupId>javax</groupId>
                   <artifactId>javaee-api</artifactId>
                   <version>8.0</version>
               </dependency>
    ```
5. 配置jetty
    - edit configurations...
    - add - maven
    - commandLine => jetty:run
    - 选中 prototype-websocket 项目
6. 启动jetty
7. 访问 localhost:8080/index.jsp
8. 这里jetty有个编码的问题 == TODO


### shadow-simple-websocket
1. 项目中结合了多种实现websocket的技术方案
2. 推荐使用 wsFourStomp 方案
3. 测试用例可用打开各个项目下的html进行测试
4. 启动项目即可进行前后端联合测试，端口 8080



