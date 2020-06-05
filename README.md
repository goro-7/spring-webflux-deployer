# Spring-Webflux-Tools
Simply use github webhook and spring boot devtools to create automatic deployment for your 
spring webflux application. On your push to remote branch, application is refreshed with new
code using devtools hot reload and retarts.
Changes propogation from your local to remote server is
- easy
- fast
- simple
- minimum refresh rate

  
## Pre requisites
- This is packaged as maven dependency and works with Java 14
- Your remote/server should have git
- Your remote/server should maven installed

## How to use ?
- add following repository to your pom.xml of maven project
`<repository>
    <id>github</id>
    <name>grsdev7 maven github repo</name>
    <url>https://maven.pkg.github.com/goro-79/artifacts</url>
</repository>`

- add following to your pom dependencies
    `<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
        <relativePath/>
    <dependency>
    `
- define following functional api endpoint to your main spring boot application class or configuration class
    `
    @Bean
    RouterFunction<ServerResponse> routers() {
        return RefreshFns.router();
    }`
    
**Note** - you can check demo app here - https://github.com/goro-79/spring-webflux-deployer. 


## Continuous Deployer
  A tool which exposes web api which when called, fetches latest code from git and
  redeploys application
