docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://gitlab:8929/" \
--registration-token "Droh625-TZoh_-dehyXW" \
--executor "shell" \
--description "java description" \
--tag-list "sit,uat,prd" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"


docker exec gitlab-runner-jms-docker gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "GR1348941JNi_b-KsdNZ4UcrxSqBt" \
--executor "shell" \
--description "shdy_shell" \
--tag-list "shdy_shell" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"