package com.sparta.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

public class GitHubApi {
    public static int commentId;

    public static RequestSpecification getAllCommentsFromRepoRequestSpec() {
        return getBaseSpecBuilder(GitHubConfig.getGetAllCommentsPath())
                .addPathParams(Map.of(
                        "owner", GitHubConfig.getOwner(),
                        "repo", GitHubConfig.getRepoName()
                )).build();
    }

    public static RequestSpecification getCommentByIdRequestSpec(int commentId) {
        return getBaseSpecBuilder(GitHubConfig.getGetCommentPath())
                .addPathParams(Map.of(
                        "owner", GitHubConfig.getOwner(),
                        "repo", GitHubConfig.getRepoName(),
                        "comment_id", commentId
                )).build();
    }

    public static RequestSpecification postCommentRequestSpec(String commentBody) {
        return getBaseSpecBuilder(GitHubConfig.getPostCommentPath())
                .addPathParams(Map.of(
                        "owner", GitHubConfig.getOwner(),
                        "repo", GitHubConfig.getRepoName(),
                        "commit_sha", GitHubConfig.getCommitSha()
                ))
                .setContentType(ContentType.JSON)
                .setBody(commentBody)
                .build();
    }

    public static RequestSpecification deleteCommentRequestSpec(int commentId) {
        return getCommentByIdRequestSpec(commentId);
    }

    public static void resetCommentsInRepo() {
        deleteAllCommentsFromRepo();
        GitHubApi.commentId =
                RestAssured
                        .given(postCommentRequestSpec("{\"body\":\"Commit comment test\"}"))
                        .when()
                        .post()
                        .then()
                        .assertThat().statusCode(201)
                        .extract().jsonPath().get("id");
    }

    public static void deleteAllCommentsFromRepo() {
        Response allCommentsResponse = RestAssured
                .given()
                    .spec(getAllCommentsFromRepoRequestSpec())
                .when()
                    .get()
                .then()
                    .log().all()
                    .extract().response();

        List<Integer> ids = allCommentsResponse.jsonPath().getList("id");

        for (var commentId : ids) {
            RestAssured
                    .given(deleteCommentRequestSpec(commentId))
                    .when()
                    .delete()
                    .then()
                    .assertThat().statusCode(204);
        }
    }

    private static RequestSpecBuilder getBaseSpecBuilder(String path) {
        return new RequestSpecBuilder()
                .setBaseUri(GitHubConfig.getBaseUri())
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/vnd.github+json",
                        "Authorization", "Bearer " + GitHubConfig.getBearerToken(),
                        "X-GitHub-Api-Version", "2022-11-28"
                ));
    }
}
