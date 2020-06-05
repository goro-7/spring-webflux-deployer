# Spring-Webflux-Tools
Simply use github webhook and spring boot devtools to create automatic deployment for your 
spring webflux application. On your push to remote branch, application is refreshed with new
code using devtools hot reload and retarts.
Changes propogation from your local to remote server is
- easy
- fast
- simple
- minimum refresh rate

## List of tools

- Continuous Deployer
  A tool which exposes web api which when called, fetches latest code from git and
  redeploys application
  
## Pre requisites
- This is packaged as maven dependency and works with Java 14
- Your remote/server should have git
- Your remote/server should maven installed