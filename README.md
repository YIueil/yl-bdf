# yiueil-bdf
基础开发框架

## 增加模块步骤
- 创建模块
- pom添加部署配置
```xml
<profiles>
    <profile>
        <id>snapshot</id>
        <distributionManagement>
            <repository>
                <id>local-repo-snapshot</id>
                <name>本地发布仓库地址</name>
                <url>file://${basedir}/../maven-repo</url>
            </repository>
        </distributionManagement>
    </profile>
    <profile>
        <id>release</id>
        <distributionManagement>
            <repository>
                <id>github-repo</id>
                <name>GitHub Apache Maven Packages</name>
                <url>https://maven.pkg.github.com/YIueil/yl-bdf</url>
            </repository>
        </distributionManagement>
    </profile>
</profiles>
```

## 发布模块步骤
- maven环境中勾选snapshot通过deploy打包提交代码会将内容发布到github-pages中进行管理
- maven环境中勾选release通过deploy打包将将发布到github-packages中进行管理(推荐, 需要再maven setting中配置 access key)

## 使用yl-bdf
pom.xml

```xml
    <repositories>
        <repository>
            <id>github-rich-repo</id>
            <name>YIueil's Maven Repository on Github</name>
            <url>https://yiueil.github.io/yl-bdf/maven-repo/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>cc.yiueil</groupId>
            <artifactId>yl-bdf-all</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

## 使用yl-bdf-web

```xml
    <repositories>
        <repository>
            <id>github-rich-repo</id>
            <name>YIueil's Maven Repository on Github</name>
            <url>https://yiueil.github.io/yl-bdf/maven-repo/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>cc.yiueil</groupId>
            <artifactId>yl-bdf-web</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

并在你的spring中添加配置的引入

```xml
    <import resource="spring/yl-bdf-spring.xml"/>
```