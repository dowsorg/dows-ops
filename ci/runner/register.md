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