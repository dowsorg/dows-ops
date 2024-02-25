package org.dows.ops.gitlab;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

import java.util.List;

public class GitlabClient {

    public void getProjects() throws GitLabApiException {
        GitLabApi gitLabApi = new GitLabApi("http://your.gitlab.server.com", "YOUR_PERSONAL_ACCESS_TOKEN");

        // Get the list of projects your account has access to
        List<Project> projects = gitLabApi.getProjectApi().getProjects();
    }

}
