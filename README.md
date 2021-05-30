## KafkaConsumer-Storm
## 打包方式
 * 基于spring-boot-maven-plugin
   * mvn clean
   * mvn package
   * 最后这个包
     * 本地执行java -jar XXX.jar可以正常运行
     * 但是上传到nimbus上执行，./bin/storm jar full.jar com.KafkaConsumer_Storm.App rickytest， 就会报错
     * 到nimbus上运行 java -jar full.jar rickytest 可以看到上传成功，但是版本不对，需要单独解决
     * 部署到storm1.2.3的环境上，topology上传成功了