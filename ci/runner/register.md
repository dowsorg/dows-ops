docker build -t gitlab-runner:16.8.1-j17m3s5 .
docker tag gitlab-runner:16.8.1-j17m3s5 192.168.111.103:88/gitlab-runner:16.8.1-j17m3s5
docket push 192.168.111.103:88/gitlab-runner:16.8.1-j17m3s5



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


docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "GR1348941JNi_b-KsdNZ4UcrxSqBt" \
--executor "shell" \
--description "shdy_shell" \
--tag-list "shdy_shell" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"

docker run --rm -v /srv/gitlab-runner/config:/etc/gitlab-runner gitlab/gitlab-runner register \
--non-interactive \
--url "https://gitlab.com/" \
--registration-token "$PROJECT_REGISTRATION_TOKEN" \
--executor "docker" \
--docker-image alpine:latest \
--description "docker-runner" \
--maintenance-note "Free-form maintainer notes about this runner" \
--tag-list "docker,aws" \
--run-untagged="true" \
--locked="false" \
--access-level="not_protected"





docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "Z6DZL2dtNDU8bBPfsJs9" \
--executor "docker" \
--docker-image docker:latest \
--description "shdy docker executor" \
--tag-list "shdy" \
--run-untagged "false" \
--locked "false" \
--access-level "not_protected"


docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "Z6DZL2dtNDU8bBPfsJs9" \
--executor "shell" \
--description "shdy shell executor" \
--tag-list "cicd" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"