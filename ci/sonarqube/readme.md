[1] bootstrap checks failed. You must address the points described in the following [1] lines before starting Elasticsearch. For more information see [https://www.elastic.co/guide/en/elasticsearch/reference/8.11/bootstrap-checks.html]
bootstrap check failure [1] of [1]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]; for more information see [https://www.elastic.co/guide/en/elasticsearch/reference/8.11/_maximum_map_count_check.html]
ERROR: Elasticsearch did not exit normally - check the logs at /opt/sonarqube/logs/sonarqube.log
2024.03.04 07:30:16 WARN  es[][o.e.n.Node] unexpected exception while waiting for http server to close
java.util.concurrent.ExecutionException: java.lang.IllegalStateException: Can't move to stopped state when not started
at java.util.concurrent.FutureTask.report(Unknown Source) ~[?:?]
at java.util.concurrent.FutureTask.get(Unknown Source) ~[?:?]
at org.elasticsearch.node.Node.prepareForClose(Node.java:1776) ~[elasticsearch-8.11.0.jar:?]
at org.elasticsearch.bootstrap.Elasticsearch.shutdown(Elasticsearch.java:468) ~[elasticsearch-8.11.0.jar:?]
at java.lang.Thread.run(Unknown Source) ~[?:?]
Caused by: java.lang.IllegalStateException: Can't move to stopped state when not started
at org.elasticsearch.common.component.Lifecycle.canMoveToStopped(Lifecycle.java:128) ~[elasticsearch-8.11.0.jar:?]
at org.elasticsearch.common.component.AbstractLifecycleComponent.stop(AbstractLifecycleComponent.java:73) ~[elasticsearch-8.11.0.jar:?]
at org.elasticsearch.node.Node.lambda$prepareForClose$59(Node.java:1768) ~[elasticsearch-8.11.0.jar:?]
at java.util.concurrent.FutureTask.run(Unknown Source) ~[?:?]

直接起容器报错：max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
#修改宿主机的环境变量
# sudo vi /etc/sysctl.conf
vm.max_map_count=655360
sudo sysctl -p

