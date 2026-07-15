package com.sparta.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GitHubConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream stream = GitHubConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties"))
        {
            if (stream != null) {
                props.load(stream);
            } else {
                throw new IOException("Unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUri() {
        return props.getProperty("github.base_uri");
    }

    public static String getRepoName() {
        return props.getProperty("github.repo_name");
    }

    public static String getOwner() {
        return props.getProperty("github.owner");
    }

    public static String getBearerToken() {
        String token = props.getProperty("github.bearer_token");
        if (token == null || token.isEmpty() || token.equals("YOUR_GITHUB_TOKEN_HERE")) {
            token = System.getenv("GITHUB_TOKEN");
        }
        return token;
    }

    public static String getCommitSha() {
        return props.getProperty("github.commit_sha");
    }

    public static String getGetAllCommentsPath() {
        return props.getProperty("github.api_path.get_all_comments");
    }

    public static String getGetCommentPath() {
        return props.getProperty("github.api_path.get_comment");
    }

    public static String getPostCommentPath() {
        return props.getProperty("github.api_path.post_comment");
    }

    public static String getDeleteCommentPath() {
        return props.getProperty("github.api_path.delete_comment");
    }
}
