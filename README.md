# yiueil-bdf
基础开发框架

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
            <groupId>cn.yiueil</groupId>
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
            <groupId>cn.yiueil</groupId>
            <artifactId>yl-bdf-web</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

并在你的spring中添加配置的引入

```xml
<import resource="spring/yl-bdf-webmvc.xml"/>
```