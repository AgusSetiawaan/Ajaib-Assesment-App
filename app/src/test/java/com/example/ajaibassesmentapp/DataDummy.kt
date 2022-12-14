package com.example.ajaibassesmentapp

import com.example.ajaibassesmentapp.data.GithubUserData
import com.example.ajaibassesmentapp.response.GithubUserDetailResponse
import com.example.ajaibassesmentapp.response.GithubUserResponse
import com.google.gson.annotations.SerializedName

object DataDummy {

    fun generateListGithubUserResponse(): List<GithubUserResponse>{
        val list = mutableListOf<GithubUserResponse>()
        for(i in 0..1){
            val user = GithubUserResponse(
                gistsUrl = "gist $i",
                reposUrl = "repos $i",
                followingUrl = "following $i",
                followersUrl = "followers $i",
                starredUrl = "starred $i",
                login = "username $i",
                type = "type $i",
                url = "url $i",
                subscriptionsUrl = "subscription $i",
                receivedEventsUrl = "receivedEventsUrl $i",
                avatarUrl = "avatarUrl $i",
                eventsUrl = "eventsUrl $i",
                htmlUrl = "htmlUrl $i",
                siteAdmin = true,
                id = i,
                gravatarId = "gravatar $i",
                nodeId = "node $i",
                organizationsUrl = "organizationsUrl $i"
            )
            list.add(user)
        }
        return list
    }
    
    fun generateListDetailGithubResponseUser1(): GithubUserDetailResponse{
        return GithubUserDetailResponse(
            gistsUrl = "gist 1",
            reposUrl = "repos 1",
            followingUrl = "following 1",
            followersUrl = "followers 1",
            starredUrl = "starred 1",
            login = "username 1",
            type = "type 1",
            url = "url 1",
            subscriptionsUrl = "subscription 1",
            receivedEventsUrl = "receivedEventsUrl 1",
            avatarUrl = "avatarUrl 1",
            eventsUrl = "eventsUrl 1",
            htmlUrl = "htmlUrl 1",
            siteAdmin = true,
            id = 1,
            gravatarId = "gravatar 1",
            nodeId = "node 1",
            organizationsUrl = "organizationsUrl 1",
            bio = "bio 1",
            blog = "blog 1",
            company = "company 1",
            createdAt = "createdAt 1", 
            email = "email 1",
            followers = 0,
            following = 0,
            hireable = false,
            location = "location 1",
            name = "name 1",
            publicGists = 0,
            publicRepos = 0,
            twitterUsername = "twitter 1",
            updatedAt = "updatedAt 1"
        )
    }

    fun generateListDetailGithubResponseUser0(): GithubUserDetailResponse{
        return GithubUserDetailResponse(
            gistsUrl = "gist 0",
            reposUrl = "repos 0",
            followingUrl = "following 0",
            followersUrl = "followers 0",
            starredUrl = "starred 0",
            login = "username 0",
            type = "type 0",
            url = "url 0",
            subscriptionsUrl = "subscription 0",
            receivedEventsUrl = "receivedEventsUrl 0",
            avatarUrl = "avatarUrl 0",
            eventsUrl = "eventsUrl 0",
            htmlUrl = "htmlUrl 0",
            siteAdmin = true,
            id = 1,
            gravatarId = "gravatar 0",
            nodeId = "node 0",
            organizationsUrl = "organizationsUrl 0",
            bio = "bio 0",
            blog = "blog 0",
            company = "company 0",
            createdAt = "createdAt 0",
            email = "email 0",
            followers = 0,
            following = 0,
            hireable = false,
            location = "location 0",
            name = "name 0",
            publicGists = 0,
            publicRepos = 0,
            twitterUsername = "twitter 0",
            updatedAt = "updatedAt 0"
        )
    }

    fun generateListDetailGithubUserData(): List<GithubUserData>{
        val list = mutableListOf<GithubUserData>()

        for(i in 0..1){
            val data = GithubUserData(
                id = i,
                username = "username $i",
                name = "name $i",
                company = "company $i",
                location = "location $i",
                email = "email $i",
                photoUrl = "avatarUrl $i",
                followers = 0,
                following = 0,
            )
            list.add(data)
        }

        return list
    }
}